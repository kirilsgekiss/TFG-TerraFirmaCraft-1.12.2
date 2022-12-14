/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.spawner;

import java.util.*;
import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.types.ICreatureTFC;
import net.dries007.tfc.objects.entity.animal.*;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

/*
 * TFC entity spawning mechanics
 * Only works in tfc type worlds
 */
@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = MOD_ID)
public final class WorldEntitySpawnerTFC
{
    /**
     * Handles livestock cooldown time
     * Supplier so we get the updated config value
     */
    public static final Map<Class<? extends EntityLiving>, Supplier<Integer>> LIVESTOCK;

    static
    {
        LIVESTOCK = new HashMap<>();
        LIVESTOCK.put(EntityAlpacaTFC.class, () -> ConfigTFC.Animals.ALPACA.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityCamelTFC.class, () -> ConfigTFC.Animals.CAMEL.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityChickenTFC.class, () -> ConfigTFC.Animals.CHICKEN.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityCowTFC.class, () -> ConfigTFC.Animals.COW.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityDonkeyTFC.class, () -> ConfigTFC.Animals.DONKEY.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityDuckTFC.class, () -> ConfigTFC.Animals.DUCK.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityGoatTFC.class, () -> ConfigTFC.Animals.GOAT.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityGrouseTFC.class, () -> ConfigTFC.Animals.GROUSE.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityHorseTFC.class, () -> ConfigTFC.Animals.HORSE.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityLlamaTFC.class, () -> ConfigTFC.Animals.LLAMA.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityMuskOxTFC.class, () -> ConfigTFC.Animals.MUSKOX.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityOcelotTFC.class, () -> ConfigTFC.Animals.OCELOT.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityPigTFC.class, () -> ConfigTFC.Animals.PIG.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityQuailTFC.class, () -> ConfigTFC.Animals.QUAIL.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntitySheepTFC.class, () -> ConfigTFC.Animals.SHEEP.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityWolfTFC.class, () -> ConfigTFC.Animals.WOLF.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityYakTFC.class, () -> ConfigTFC.Animals.YAK.elder * ICalendar.TICKS_IN_DAY);
        LIVESTOCK.put(EntityZebuTFC.class, () -> ConfigTFC.Animals.ZEBU.elder * ICalendar.TICKS_IN_DAY);
    }

    public static void init()
    {
        EnumCreatureType.MONSTER.maxNumberOfCreature = ConfigTFC.General.DIFFICULTY.mobSpawnCount;
        EnumCreatureType.CREATURE.maxNumberOfCreature = ConfigTFC.General.DIFFICULTY.animalSpawnCount;
        // Using enum helper to add creature types adds more issues than resolve.
        // Although it worked in dev and with only minor mods, I had too much trouble with a larger modpack
    }


    /**
     * Experimental: Handles wild livestock respawning
     * This event runs after CheckSpawn, which means you can safely assume that all other restrictions passed (biome, temp, rainfall, etc)
     */
    @SubscribeEvent
    public static void onLivestockRespawn(LivingSpawnEvent.SpecialSpawn event)
    {
        World worldIn = event.getWorld();
        EntityLiving entity = (EntityLiving) event.getEntity();

        event.getWorld().getBiome(new BlockPos(event.getX(), event.getY(), event.getZ())).getSpawnableList(EnumCreatureType.CREATURE);

        if (LIVESTOCK.containsKey(entity.getClass()))
        {
            event.setResult(Event.Result.ALLOW); // Always cancel vanilla's spawning since we take it from here
            AnimalRespawnWorldData data = AnimalRespawnWorldData.get(worldIn);
            ChunkPos pos = new ChunkPos(new BlockPos(event.getX(), event.getY(), event.getZ()));
            long lastSpawnTick = data.getLastRespawnTick(entity, pos);
            long deltaTicks = CalendarTFC.PLAYER_TIME.getTicks() - lastSpawnTick;
            long cooldown = LIVESTOCK.get(entity.getClass()).get();
            if (lastSpawnTick <= 0 || cooldown <= deltaTicks)
            {
                data.setLastRespawnTick(entity, pos, CalendarTFC.PLAYER_TIME.getTicks());
                int centerX = (int) event.getX();
                int centerZ = (int) event.getZ();
                int diameterX = 16;
                int diameterZ = 16;
                //noinspection ConstantConditions
                doGroupSpawning(EntityRegistry.getEntry(entity.getClass()), worldIn, centerX, centerZ, diameterX, diameterZ, worldIn.rand);
            }
        }
    }

    /**
     * **Modified version from vanilla's {@link net.minecraft.world.WorldEntitySpawner}
     * Called during chunk generation to spawn initial creatures.
     * Spawns group of animals together
     *
     * @param centerX   The X coordinate of the point to spawn mobs around.
     * @param centerZ   The Z coordinate of the point to spawn mobs around.
     * @param diameterX The X diameter of the rectangle to spawn mobs in
     * @param diameterZ The Z diameter of the rectangle to spawn mobs in
     */
    public static void performWorldGenSpawning(World worldIn, Biome biomeIn, int centerX, int centerZ, int diameterX, int diameterZ, Random randomIn)
    {
        final BlockPos chunkBlockPos = new BlockPos(centerX, 0, centerZ);

        final float temperature = ClimateTFC.getAvgTemp(worldIn, chunkBlockPos);
        final float rainfall = ChunkDataTFC.getRainfall(worldIn, chunkBlockPos);
        final float floraDensity = ChunkDataTFC.getFloraDensity(worldIn, chunkBlockPos);
        final float floraDiversity = ChunkDataTFC.getFloraDiversity(worldIn, chunkBlockPos);

        // Spawns only one group
        ForgeRegistries.ENTITIES.getValuesCollection().stream()
            .filter(x -> {
                if (ICreatureTFC.class.isAssignableFrom(x.getEntityClass()))
                {
                    Entity ent = x.newInstance(worldIn);
                    if (ent instanceof ICreatureTFC)
                    {
                        int weight = ((ICreatureTFC) ent).getSpawnWeight(biomeIn, temperature, rainfall, floraDensity, floraDiversity);
                        return weight > 0 && randomIn.nextInt(weight) == 0;
                    }
                }
                return false;
            }).findFirst()
            .ifPresent(entityEntry -> doGroupSpawning(entityEntry, worldIn, centerX, centerZ, diameterX, diameterZ, randomIn));
    }

    private static void doGroupSpawning(EntityEntry entityEntry, World worldIn, int centerX, int centerZ, int diameterX, int diameterZ, Random randomIn)
    {
        List<EntityLiving> group = new ArrayList<>();
        EntityLiving creature = (EntityLiving)entityEntry.newInstance(worldIn);
        if (!(creature instanceof ICreatureTFC))
        {
            return; // Make sure to not crash
        }
        ICreatureTFC creatureTFC = (ICreatureTFC) creature;
        int fallback = 5; // Fallback measure if some mod completely deny this entity spawn
        int individuals = Math.max(1, creatureTFC.getMinGroupSize()) + randomIn.nextInt(creatureTFC.getMaxGroupSize() - Math.max(0, creatureTFC.getMinGroupSize() - 1));
        while (individuals > 0)
        {
            int j = centerX + randomIn.nextInt(diameterX);
            int k = centerZ + randomIn.nextInt(diameterZ);
            BlockPos blockpos = worldIn.getTopSolidOrLiquidBlock(new BlockPos(j, 0, k));
            creature.setLocationAndAngles((float) j + 0.5F, blockpos.getY(), (float) k + 0.5F, randomIn.nextFloat() * 360.0F, 0.0F);
            if (creature.getCanSpawnHere()) // fix entities spawning inside walls
            {
                if (net.minecraftforge.event.ForgeEventFactory.canEntitySpawn(creature, worldIn, j + 0.5f, (float) blockpos.getY(), k + 0.5f, null) == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY)
                {
                    if (--fallback > 0)
                    {
                        continue;
                    }
                    else
                    {
                        break; // Someone doesn't want me to spawn :(
                    }
                }
                fallback = 5;
                // Spawn pass! let's continue
                worldIn.spawnEntity(creature);
                group.add(creature);
                creature.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(creature)), null);
                if (--individuals > 0)
                {
                    //We still need to spawn more
                    creature = (EntityLiving)entityEntry.newInstance(worldIn);
                    creatureTFC = (ICreatureTFC) creature;
                }
            }
            else
            {
                if (--fallback <= 0) //Trying to spawn in water or inside walls too many times, let's break
                {
                    break;
                }
            }
        }
        // Apply the group spawning mechanics!
        creatureTFC.getGroupingRules().accept(group, randomIn);
    }
}

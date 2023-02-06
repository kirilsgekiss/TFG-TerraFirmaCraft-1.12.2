/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity.animal;

import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.Constants;
import net.dries007.tfc.api.types.IPredator;
import net.dries007.tfc.objects.TFCLootTables;
import net.dries007.tfc.objects.entity.ai.TFCEntityAIAttackMelee;
import net.dries007.tfc.objects.entity.ai.TFCEntityAIStandAttack;
import net.dries007.tfc.objects.entity.ai.TFCEntityAIWanderHuntArea;
import net.dries007.tfc.util.climate.BiomeHelper;
import net.dries007.tfc.world.classic.biomes.TFCBiomes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TFCEntityBlackBear extends TFCEntityGrizzlyBear implements IPredator, TFCEntityAIStandAttack.IEntityStandAttack {
    private static final int DAYS_TO_ADULTHOOD = 240;

    @SuppressWarnings("unused")
    public TFCEntityBlackBear(World worldIn) {
        this(worldIn, Gender.valueOf(Constants.RNG.nextBoolean()), getRandomGrowth(DAYS_TO_ADULTHOOD, 0));
    }

    public TFCEntityBlackBear(World worldIn, Gender gender, int birthDay) {
        super(worldIn, gender, birthDay);
        this.setSize(1.4F, 1.7F);
    }

    @Override
    public int getSpawnWeight(Biome biome, float temperature, float rainfall, float floraDensity, float floraDiversity) {
        BiomeHelper.BiomeType biomeType = BiomeHelper.getBiomeType(temperature, rainfall, floraDensity);
        if (!TFCBiomes.isOceanicBiome(biome) && !TFCBiomes.isBeachBiome(biome) &&
                (biomeType == BiomeHelper.BiomeType.TEMPERATE_FOREST)) {
            return TFCConfig.Animals.BLACK_BEAR.rarity;
        }
        return 0;
    }

    @Override
    protected void initEntityAI() {
        EntityAIWander wander = new TFCEntityAIWanderHuntArea(this, 1.0D);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new TFCEntityAIStandAttack<>(this, 1.2D, 2.0D, TFCEntityAIAttackMelee.AttackBehavior.DAYLIGHT_ONLY).setWanderAI(wander));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, wander);
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));


        int priority = 2;
        for (String input : TFCConfig.Animals.BLACK_BEAR.huntCreatures) {
            ResourceLocation key = new ResourceLocation(input);
            EntityEntry entityEntry = ForgeRegistries.ENTITIES.getValue(key);
            if (entityEntry != null) {
                Class<? extends Entity> entityClass = entityEntry.getEntityClass();
                if (EntityLivingBase.class.isAssignableFrom(entityClass)) {
                    //noinspection unchecked
                    this.targetTasks.addTask(priority++, new EntityAINearestAttackableTarget<>(this, (Class<EntityLivingBase>) entityClass, false));
                }
            }
        }
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return TFCLootTables.ANIMALS_BLACK_BEAR;
    }
}

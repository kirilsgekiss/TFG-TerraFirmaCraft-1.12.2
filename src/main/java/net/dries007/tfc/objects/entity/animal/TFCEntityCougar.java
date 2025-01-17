/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity.animal;

import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.Constants;
import net.dries007.tfc.api.types.IPredator;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.objects.TFCLootTables;
import net.dries007.tfc.objects.entity.ai.TFCEntityAIAttackMelee;
import net.dries007.tfc.objects.entity.ai.TFCEntityAIWanderHuntArea;
import net.dries007.tfc.util.climate.BiomeHelper;
import net.dries007.tfc.world.classic.biomes.TFCBiomes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TFCEntityCougar extends TFCEntityPanther implements IPredator {
    private static final int DAYS_TO_ADULTHOOD = 160;

    @SuppressWarnings("unused")
    public TFCEntityCougar(World worldIn) {
        this(worldIn, Gender.valueOf(Constants.RNG.nextBoolean()),
                getRandomGrowth(DAYS_TO_ADULTHOOD, 0));
    }

    public TFCEntityCougar(World worldIn, Gender gender, int birthDay) {
        super(worldIn, gender, birthDay);
        this.setSize(1.1F, 1.1F);
    }

    @Override
    public int getSpawnWeight(Biome biome, float temperature, float rainfall, float floraDensity, float floraDiversity) {
        BiomeHelper.BiomeType biomeType = BiomeHelper.getBiomeType(temperature, rainfall, floraDensity);
        if (!TFCBiomes.isOceanicBiome(biome) && !TFCBiomes.isBeachBiome(biome) &&
                (biomeType == BiomeHelper.BiomeType.TEMPERATE_FOREST)) {
            return TFCConfig.Animals.COUGAR.rarity;
        }
        return 0;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return TFCSounds.ANIMAL_COUGAR_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TFCSounds.ANIMAL_COUGAR_DEATH;
    }

    @Override
    protected void initEntityAI() {
        EntityAIWander wander = new TFCEntityAIWanderHuntArea(this, 1.0D);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new TFCEntityAIAttackMelee<>(this, 1.2D, 1.25D, TFCEntityAIAttackMelee.AttackBehavior.NIGHTTIME_ONLY).setWanderAI(wander));
        this.tasks.addTask(4, new EntityAIFollowParent(this, 1.1D));
        this.tasks.addTask(5, wander); // Move within hunt area
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        // Avoid players at daytime
        this.tasks.addTask(4, new EntityAIAvoidEntity<>(this, EntityPlayer.class, 16.0F, 1.0D, 1.25D));

        int priority = 2;
        for (String input : TFCConfig.Animals.COUGAR.huntCreatures) {
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

    @Override
    protected SoundEvent getAmbientSound() {
        return Constants.RNG.nextInt(100) < 5 ? TFCSounds.ANIMAL_COUGAR_CRY : TFCSounds.ANIMAL_COUGAR_SAY;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return TFCLootTables.ANIMALS_COUGAR;
    }
}

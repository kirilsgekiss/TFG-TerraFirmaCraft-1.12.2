/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity.animal;

import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.Constants;
import net.dries007.tfc.api.types.ILivestock;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.objects.TFCLootTables;
import net.dries007.tfc.util.calendar.TFCCalendar;
import net.dries007.tfc.util.climate.BiomeHelper;
import net.dries007.tfc.world.classic.biomes.TFCBiomes;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TFCEntityYak extends TFCEntityCow implements ILivestock {
    @SuppressWarnings("unused")
    public TFCEntityYak(World worldIn) {
        this(worldIn, Gender.valueOf(Constants.RNG.nextBoolean()), getRandomGrowth(TFCConfig.Animals.YAK.adulthood, TFCConfig.Animals.YAK.elder));
    }

    public TFCEntityYak(World worldIn, Gender gender, int birthDay) {
        super(worldIn, gender, birthDay);
        this.setSize(1.4F, 1.4F);
    }

    @Override
    public int getSpawnWeight(Biome biome, float temperature, float rainfall, float floraDensity, float floraDiversity) {
        BiomeHelper.BiomeType biomeType = BiomeHelper.getBiomeType(temperature, rainfall, floraDensity);
        if (!TFCBiomes.isOceanicBiome(biome) && !TFCBiomes.isBeachBiome(biome) &&
                (biomeType == BiomeHelper.BiomeType.TAIGA)) {
            return TFCConfig.Animals.YAK.rarity;
        }
        return 0;
    }

    @Override
    public void birthChildren() {
        int numberOfChildren = TFCConfig.Animals.YAK.babies;
        for (int i = 0; i < numberOfChildren; i++) {
            TFCEntityYak baby = new TFCEntityYak(this.world, Gender.valueOf(Constants.RNG.nextBoolean()), (int) TFCCalendar.PLAYER_TIME.getTotalDays());
            baby.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
            baby.setFamiliarity(this.getFamiliarity() < 0.9F ? this.getFamiliarity() / 2.0F : this.getFamiliarity() * 0.9F);
            this.world.spawnEntity(baby);
        }
    }

    @Override
    public long gestationDays() {
        return TFCConfig.Animals.YAK.gestation;
    }

    @Override
    public double getOldDeathChance() {
        return TFCConfig.Animals.YAK.oldDeathChance;
    }

    @Override
    public float getAdultFamiliarityCap() {
        return 0.35F;
    }

    @Override
    public int getDaysToAdulthood() {
        return TFCConfig.Animals.YAK.adulthood;
    }

    @Override
    public int getDaysToElderly() {
        return TFCConfig.Animals.YAK.elder;
    }

    @Override
    public long getProductsCooldown() {
        return Math.max(0, TFCConfig.Animals.YAK.milkTicks + getMilkedTick() - TFCCalendar.PLAYER_TIME.getTicks());
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return TFCSounds.ANIMAL_YAK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TFCSounds.ANIMAL_YAK_DEATH;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return TFCSounds.ANIMAL_YAK_SAY;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return TFCLootTables.ANIMALS_YAK;
    }

    @Override
    // Equivalent sound
    protected void playStepSound(BlockPos pos, Block blockIn) {
        playSound(SoundEvents.ENTITY_COW_STEP, 0.14F, 0.9F);
    }

}

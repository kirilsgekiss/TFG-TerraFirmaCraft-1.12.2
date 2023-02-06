/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity.animal;

import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.Constants;
import net.dries007.tfc.api.capability.egg.CapabilityEgg;
import net.dries007.tfc.api.capability.egg.IEgg;
import net.dries007.tfc.api.types.ILivestock;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.objects.TFCLootTables;
import net.dries007.tfc.util.calendar.TFCCalendar;
import net.dries007.tfc.util.climate.BiomeHelper;
import net.dries007.tfc.world.classic.biomes.TFCBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;


@ParametersAreNonnullByDefault
public class TFCEntityQuail extends TFCEntityChicken implements ILivestock {
    public TFCEntityQuail(World worldIn) {
        this(worldIn, Gender.valueOf(Constants.RNG.nextBoolean()), getRandomGrowth(TFCConfig.Animals.QUAIL.adulthood, TFCConfig.Animals.QUAIL.elder));
    }

    public TFCEntityQuail(World worldIn, Gender gender, int birthDay) {
        super(worldIn, gender, birthDay);
        this.setSize(0.7F, 0.7F);
    }

    @Override
    public int getSpawnWeight(Biome biome, float temperature, float rainfall, float floraDensity, float floraDiversity) {
        BiomeHelper.BiomeType biomeType = BiomeHelper.getBiomeType(temperature, rainfall, floraDensity);
        if (!TFCBiomes.isOceanicBiome(biome) && !TFCBiomes.isBeachBiome(biome) &&
                (biomeType == BiomeHelper.BiomeType.TEMPERATE_FOREST)) {
            return TFCConfig.Animals.QUAIL.rarity;
        }
        return 0;
    }

    @Override
    public int getDaysToAdulthood() {
        return TFCConfig.Animals.QUAIL.adulthood;
    }

    @Override
    public int getDaysToElderly() {
        return TFCConfig.Animals.QUAIL.elder;
    }

    @Override
    public List<ItemStack> getProducts() {
        List<ItemStack> eggs = new ArrayList<>();
        ItemStack egg = new ItemStack(Items.EGG);
        if (this.isFertilized()) {
            IEgg cap = egg.getCapability(CapabilityEgg.CAPABILITY, null);
            if (cap != null) {
                TFCEntityQuail chick = new TFCEntityQuail(this.world);
                chick.setFamiliarity(this.getFamiliarity() < 0.9F ? this.getFamiliarity() / 2.0F : this.getFamiliarity() * 0.9F);
                cap.setFertilized(chick, TFCConfig.Animals.QUAIL.hatch + TFCCalendar.PLAYER_TIME.getTotalDays());
            }
        }
        eggs.add(egg);
        return eggs;
    }

    @Override
    public long getProductsCooldown() {
        return Math.max(0, TFCConfig.Animals.QUAIL.eggTicks + getLaidTicks() - TFCCalendar.PLAYER_TIME.getTicks());
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return TFCSounds.ANIMAL_QUAIL_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TFCSounds.ANIMAL_QUAIL_DEATH;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return TFCSounds.ANIMAL_QUAIL_SAY;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return TFCLootTables.ANIMALS_QUAIL;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        // Same sound, no need to create another
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.14F, 0.9F);
    }

    @Override
    public double getOldDeathChance() {
        return TFCConfig.Animals.QUAIL.oldDeathChance;
    }
}

/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.types;

import net.dries007.tfc.objects.blocks.plants.*;
import net.dries007.tfc.util.calendar.TFCCalendar;
import net.dries007.tfc.util.calendar.Month;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class Plant extends IForgeRegistryEntry.Impl<Plant> {
    private final int[] stages;
    private final int numStages;
    private final float minGrowthTemp;
    private final float maxGrowthTemp;
    private final float minTemp;
    private final float maxTemp;
    private final float minRain;
    private final float maxRain;
    private final int minSun;
    private final int maxSun;
    private final int maxHeight;
    private final int minWaterDepth;
    private final int maxWaterDepth;
    private final double movementMod;

    private final PlantType plantType;
    private final Material material;
    private final boolean isClayMarking;
    private final boolean isSwampPlant;
    private final Optional<String> oreDictName;

    /**
     * Addon mods that want to add plants should subscribe to the registry event for this class
     * They also must put (in their mod) the required resources in /assets/tfc/...
     * <p>
     * Plant world generation is determined dynamically based on valid temperature and rainfall values
     * <p>
     * Valid average biome temperatures are those that fall within the range
     * plus or minus one quarter of the plants full temperature range
     * <p>
     * Example: Lotus
     * Full temperature range: 10-50
     * Average temp: 30 ( (10+50)/2 )
     * Difference between max and min temps: 40 (50-10)
     * One quarter of this range: 10 (40/4)
     * Worldgen temp range: 20-40 (30 +- 10)
     *
     * @param name          the ResourceLocation registry name of this plant
     * @param plantType     the type of plant
     * @param isClayMarking if this plant marks clay deposits
     * @param isSwampPlant  if this plant only spawns in swamps
     * @param minGrowthTemp min growing temperature
     * @param maxGrowthTemp max growing temperature
     * @param minTemp       min temperature
     * @param maxTemp       max temperature
     * @param minRain       min rainfall
     * @param maxRain       max rainfall
     * @param minSun        min light level on worldgen
     * @param maxSun        max light level on worldgen
     * @param maxHeight     max height for double+ plants
     * @param minWaterDepth min water depth for water plants on worldgen
     * @param maxWaterDepth max water depth for water plants on worldgen
     * @param movementMod   modifier for player X/Z movement through this plant
     * @param oreDictName   if not empty, the Ore Dictionary entry for this plant
     */
    public Plant(@Nonnull ResourceLocation name, PlantType plantType, int[] stages, boolean isClayMarking, boolean isSwampPlant, float minGrowthTemp, float maxGrowthTemp, float minTemp, float maxTemp, float minRain, float maxRain, int minSun, int maxSun, int maxHeight, int minWaterDepth, int maxWaterDepth, double movementMod, String oreDictName) {
        this.stages = stages;
        this.minGrowthTemp = minGrowthTemp;
        this.maxGrowthTemp = maxGrowthTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.minRain = minRain;
        this.maxRain = maxRain;
        this.minSun = minSun;
        this.maxSun = maxSun;
        this.maxHeight = maxHeight;
        this.minWaterDepth = minWaterDepth;
        this.maxWaterDepth = maxWaterDepth;
        this.movementMod = movementMod;

        this.plantType = plantType;
        this.isClayMarking = isClayMarking;
        this.isSwampPlant = isSwampPlant;
        this.material = plantType.getPlantMaterial();
        this.oreDictName = Optional.ofNullable(oreDictName);

        HashSet<Integer> hashSet = new HashSet<>();
        for (int stage : stages) {
            hashSet.add(stage);
        }
        this.numStages = hashSet.size() <= 1 ? 1 : hashSet.size() - 1;

        setRegistryName(name);
    }

    public Plant(@Nonnull ResourceLocation name, PlantType plantType, int[] stages, boolean isClayMarking, boolean isSwampPlant, float minGrowthTemp, float maxGrowthTemp, float minTemp, float maxTemp, float minRain, float maxRain, int minSun, int maxSun, int maxHeight, double movementMod, String oreDictName) {
        this(name, plantType, stages, isClayMarking, isSwampPlant, minGrowthTemp, maxGrowthTemp, minTemp, maxTemp, minRain, maxRain, minSun, maxSun, maxHeight, 0, 0, movementMod, oreDictName);
    }

    public double getMovementMod() {
        return movementMod;
    }

    public boolean getIsClayMarking() {
        return isClayMarking;
    }

    public boolean getIsSwampPlant() {
        return isSwampPlant;
    }

    public boolean isValidLocation(float temp, float rain, int sunlight) {
        return isValidTemp(temp) && isValidRain(rain) && isValidSunlight(sunlight);
    }

    public boolean isValidTemp(float temp) {
        return getTempValidity(temp) == PlantValidity.VALID;
    }

    public boolean isValidTempForWorldGen(float temp) {
        return Math.abs(temp - getAvgTemp()) < Float.sum(maxTemp, -minTemp) / 4f;
    }

    public boolean isValidRain(float rain) {
        return getRainValidity(rain) == PlantValidity.VALID;
    }

    public boolean isValidSunlight(int sunlight) {
        return minSun <= sunlight && maxSun >= sunlight;
    }

    public boolean isValidFloatingWaterDepth(World world, BlockPos pos, IBlockState water) {
        int depthCounter = minWaterDepth;
        int maxDepth = maxWaterDepth;

        for (int i = 1; i <= depthCounter; ++i) {
            if (world.getBlockState(pos.down(i)) != water && world.getBlockState(pos.down(i)).getMaterial() != Material.CORAL)
                return false;
        }

        while (world.getBlockState(pos.down(depthCounter)) == water || world.getBlockState(pos.down(depthCounter)).getMaterial() == Material.CORAL) {
            depthCounter++;
        }
        return (maxDepth > 0) && depthCounter <= maxDepth + 1;
    }

    public int getValidWaterDepth(World world, BlockPos pos, IBlockState water) {
        int depthCounter = minWaterDepth;
        int maxDepth = maxWaterDepth;

        if (depthCounter == 0 || maxDepth == 0) return -1;

        for (int i = 1; i <= depthCounter; ++i) {
            if (world.getBlockState(pos.down(i)) != water) return -1;
        }

        while (world.getBlockState(pos.down(depthCounter)) == water) {
            depthCounter++;
            if (depthCounter > maxDepth + 1) return -1;
        }
        return depthCounter;
    }

    @SuppressWarnings("unused")
    public float getMinGrowthTemp() {
        return minGrowthTemp;
    }

    @SuppressWarnings("unused")
    public float getMaxGrowthTemp() {
        return maxGrowthTemp;
    }

    public int getStageForMonth(Month month) {
        return stages[month.ordinal()];
    }

    public int getStageForMonth() {
        return getStageForMonth(TFCCalendar.CALENDAR_TIME.getMonthOfYear());
    }

    public int getNumStages() {
        return numStages;
    }

    public boolean isValidGrowthTemp(float temp) {
        return minGrowthTemp <= temp && maxGrowthTemp >= temp;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public Optional<String> getOreDictName() {
        return oreDictName;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String toString() {
        return getRegistryName().getPath();
    }

    @Nonnull
    public PlantType getPlantType() {
        return plantType;
    }

    @Nonnull
    public Material getMaterial() {
        return material;
    }

    public IBlockState getWaterType() {
        if (plantType == PlantType.FLOATING_SEA || plantType == PlantType.WATER_SEA || plantType == PlantType.TALL_WATER_SEA || plantType == PlantType.EMERGENT_TALL_WATER_SEA) {
            return ChunkGenTFC.SEA_WATER;
        } else {
            return ChunkGenTFC.WATER;
        }
    }

    public int getAgeForWorldgen(Random rand, float temp) {
        return rand.nextInt(Math.max(1, Math.min(Math.round(2.5f + ((temp - minGrowthTemp) / minGrowthTemp)), 4)));
    }

    public boolean canBePotted() {
        return plantType == PlantType.STANDARD || plantType == PlantType.CACTUS || plantType == PlantType.CREEPING || plantType == PlantType.TALL_PLANT || plantType == PlantType.DRY || plantType == PlantType.DESERT || plantType == PlantType.MUSHROOM;
    }

    public final TFCEnumPlantType getEnumPlantTypeTFC() {
        switch (plantType) {
            case DESERT:
            case DESERT_TALL_PLANT:
                if (isClayMarking) return TFCEnumPlantType.DESERT_CLAY;
                else return TFCEnumPlantType.NONE;
            case DRY:
            case DRY_TALL_PLANT:
                if (isClayMarking) return TFCEnumPlantType.DRY_CLAY;
                else return TFCEnumPlantType.DRY;
            case REED:
            case TALL_REED:
                return TFCEnumPlantType.FRESH_BEACH;
            case REED_SEA:
            case TALL_REED_SEA:
                return TFCEnumPlantType.SALT_BEACH;
            case WATER:
            case TALL_WATER:
            case EMERGENT_TALL_WATER:
                return TFCEnumPlantType.WATER;
            case WATER_SEA:
            case TALL_WATER_SEA:
            case EMERGENT_TALL_WATER_SEA:
                return TFCEnumPlantType.SEA_WATER;
            default:
                if (isClayMarking) return TFCEnumPlantType.CLAY;
                else return TFCEnumPlantType.NONE;
        }
    }

    public PlantValidity getTempValidity(float temp) {
        if (temp < minTemp) {
            return PlantValidity.COLD;
        }
        if (temp > maxTemp) {
            return PlantValidity.HOT;
        }
        return PlantValidity.VALID;
    }

    public PlantValidity getRainValidity(float rain) {
        if (rain < minRain) {
            return PlantValidity.DRY;
        }
        if (rain > maxRain) {
            return PlantValidity.WET;
        }
        return PlantValidity.VALID;
    }

    private float getAvgTemp() {
        return Float.sum(minTemp, maxTemp) / 2f;
    }

    public enum PlantValidity {
        COLD,
        HOT,
        DRY,
        WET,
        VALID
    }

    // todo: switch usages to interface from enum, it will make custom plants by addons easier down the line. It's also a better design
    public enum PlantType implements IPlantType {
        STANDARD(TFCBlockPlant::new),
        TALL_PLANT(TFCBlockTallPlant::new),
        CREEPING(TFCBlockCreepingPlant::new),
        HANGING(TFCBlockHangingPlant::new),
        FLOATING(TFCBlockFloatingWater::new),
        FLOATING_SEA(TFCBlockFloatingWater::new),
        DESERT(TFCBlockPlant::new),
        DESERT_TALL_PLANT(TFCBlockTallPlant::new),
        DRY(TFCBlockPlant::new),
        DRY_TALL_PLANT(TFCBlockTallPlant::new),
        CACTUS(TFCBlockCactus::new),
        SAGUARO_CACTUS(TFCBlockSaguaroCactus::new),
        SHORT_GRASS(TFCBlockShortGrass::new),
        TALL_GRASS(TFCBlockTallGrass::new),
        EPIPHYTE(TFCBlockEpiphyte::new),
        REED(TFCBlockPlant::new),
        REED_SEA(TFCBlockPlant::new),
        TALL_REED(TFCBlockTallPlant::new),
        TALL_REED_SEA(TFCBlockTallPlant::new),
        WATER(TFCBlockWaterPlant::new),
        WATER_SEA(TFCBlockWaterPlant::new),
        TALL_WATER(TFCBlockTallWaterPlant::new),
        TALL_WATER_SEA(TFCBlockTallWaterPlant::new),
        TALL_WATER_GRASS(TFCBlockTallGrassWater::new),
        EMERGENT_TALL_WATER(TFCBlockEmergentTallWaterPlant::new),
        EMERGENT_TALL_WATER_SEA(TFCBlockEmergentTallWaterPlant::new),
        MUSHROOM(TFCBlockMushroom::new);

        private final Function<Plant, TFCBlockPlant> supplier;

        PlantType(@Nonnull Function<Plant, TFCBlockPlant> supplier) {
            this.supplier = supplier;
        }

        @Override
        public TFCBlockPlant create(Plant plant) {
            return supplier.apply(plant);
        }

        @Override
        public Material getPlantMaterial() {
            switch (this) {
                case CACTUS:
                    return Material.CACTUS;
                case HANGING:
                case SHORT_GRASS:
                case TALL_GRASS:
                    return Material.VINE;
                case WATER:
                case WATER_SEA:
                case TALL_WATER:
                case TALL_WATER_SEA:
                case EMERGENT_TALL_WATER:
                case EMERGENT_TALL_WATER_SEA:
                    return Material.CORAL;
                default:
                    return Material.PLANTS;
            }
        }
    }

    public enum TFCEnumPlantType {
        CLAY,
        DESERT_CLAY,
        DRY_CLAY,
        DRY,
        FRESH_BEACH,
        SALT_BEACH,
        WATER,
        SEA_WATER,
        NONE;

        public String toString() {
            return name();
        }
    }
}

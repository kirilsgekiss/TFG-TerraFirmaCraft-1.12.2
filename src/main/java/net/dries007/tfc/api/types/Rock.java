/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.types;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.util.FallingBlockManager.Specification;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;


public class Rock extends IForgeRegistryEntry.Impl<Rock> {
    @GameRegistry.ObjectHolder("tfc:granite")
    public static final Rock GRANITE = Helpers.getNull();
    @GameRegistry.ObjectHolder("tfc:basalt")
    public static final Rock BASALT = Helpers.getNull();
    @GameRegistry.ObjectHolder("tfc:rhyolite")
    public static final Rock RHYOLITE = Helpers.getNull();
    @GameRegistry.ObjectHolder("tfc:limestone")
    public static final Rock LIMESTONE = Helpers.getNull();

    private final RockCategory rockCategory;
    private final ResourceLocation textureLocation;
    private final boolean isFluxStone;
    private final boolean isNaturallyGenerating;

    public Rock(@Nonnull ResourceLocation name, @Nonnull RockCategory rockCategory, boolean isFluxStone, boolean isNaturallyGenerating) {
        //noinspection ConstantConditions
        if (rockCategory == null)
            throw new IllegalArgumentException("Rock category is not allowed to be null (on rock " + name + ")");

        setRegistryName(name);
        this.rockCategory = rockCategory;
        this.textureLocation = new ResourceLocation(MOD_ID, "textures/blocks/stonetypes/raw/" + name.getPath() + ".png");
        this.isFluxStone = isFluxStone;
        this.isNaturallyGenerating = isNaturallyGenerating;
    }

    public Rock(@Nonnull ResourceLocation name, @Nonnull RockCategory rockCategory, boolean isFluxStone) {
        this(name, rockCategory, isFluxStone, true);
    }

    public Rock(@Nonnull ResourceLocation name, @Nonnull ResourceLocation categoryName, boolean isFluxStone) {
        //noinspection ConstantConditions
        this(name, TFCRegistries.ROCK_CATEGORIES.getValue(categoryName), isFluxStone, true);
    }

    /**
     * Used for knapping GUI
     *
     * @return a texture resource location
     */
    public ResourceLocation getTexture() {
        return textureLocation;
    }

    public RockCategory getRockCategory() {
        return rockCategory;
    }

    public boolean isFluxStone() {
        return isFluxStone;
    }

    public boolean isNaturallyGenerating() {
        return isNaturallyGenerating;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String toString() {
        return getRegistryName().getPath();
    }

    public enum Type {
        RAW(Material.ROCK, false, Specification.COLLAPSABLE),
        MOSSY_RAW(Material.ROCK, false, Specification.COLLAPSABLE),
        MUD(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        MUD_BRICKS(Material.ROCK, false, null),
        ANVIL(Material.ROCK, false, Specification.COLLAPSABLE),
        SPIKE(Material.ROCK, false, null),
        SMOOTH(Material.ROCK, false, Specification.COLLAPSABLE),
        COBBLE(Material.ROCK, false, new Specification(true, () -> TFCSounds.ROCK_SLIDE_SHORT)),
        BRICKS(Material.ROCK, false, null),
        SAND(Material.SAND, false, Specification.VERTICAL_AND_HORIZONTAL),
        GRAVEL(Material.SAND, false, Specification.VERTICAL_AND_HORIZONTAL),
        DIRT(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        DRY_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        CLAY(Material.CLAY, false, Specification.VERTICAL_ONLY),
        CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),

        COARSE_DIRT(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        PODZOL(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        LOAMY_SAND(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_LOAMY_SAND(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        LOAMY_SAND_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        LOAMY_SAND_PODZOL(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SANDY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SANDY_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SANDY_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        COARSE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SILTY_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SILTY_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SILTY_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SILTY_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILT_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SILT_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SILT_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SILT_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SILT(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SILT(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SILT_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SILT_PODZOL(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        DRY_LOAMY_SAND_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        DRY_SANDY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        DRY_SANDY_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SANDY_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        DRY_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SILTY_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SILTY_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SILT_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        DRY_SILT_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        HUMUS(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_HUMUS(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_AND_HORIZONTAL),
        DRY_HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_AND_HORIZONTAL),
        CLAY_HUMUS(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        CLAY_HUMUS_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_CLAY_HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_ONLY),
        COARSE_CLAY_HUMUS(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_LOAMY_SAND_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_SANDY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_SANDY_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SANDY_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SILTY_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SILTY_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SILT_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_SILT_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_CLAY_HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_ONLY),

        ROOTED_DIRT(Material.GROUND, false, Specification.VERTICAL_ONLY),

        BOG_IRON(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        BOG_IRON_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        DRY_BOG_IRON_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_BOG_IRON_GRASS(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),
        BOG_IRON_PODZOL(Material.GRASS, true, Specification.VERTICAL_AND_HORIZONTAL),

        // Earthenware Clays
        EARTHENWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        EARTHENWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_EARTHENWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SANDY_EARTHENWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_EARTHENWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_EARTHENWARE_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_EARTHENWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SANDY_EARTHENWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_EARTHENWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_EARTHENWARE_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        EARTHENWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_EARTHENWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        EARTHENWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        EARTHENWARE_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        COARSE_EARTHENWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        EARTHENWARE_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_EARTHENWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SILTY_EARTHENWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SILTY_EARTHENWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_EARTHENWARE_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_EARTHENWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SILTY_EARTHENWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SILTY_EARTHENWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_EARTHENWARE_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SANDY_EARTHENWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_EARTHENWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_EARTHENWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SILTY_EARTHENWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        EARTHENWARE_CLAY_HUMUS(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        EARTHENWARE_CLAY_HUMUS_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_EARTHENWARE_CLAY_HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_ONLY),
        COARSE_EARTHENWARE_CLAY_HUMUS(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SANDY_EARTHENWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_EARTHENWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_EARTHENWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SILTY_EARTHENWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_ONLY),

        // Kaolinite Clays
        KAOLINITE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        KAOLINITE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_KAOLINITE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SANDY_KAOLINITE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_KAOLINITE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_KAOLINITE_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_KAOLINITE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SANDY_KAOLINITE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_KAOLINITE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_KAOLINITE_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        KAOLINITE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_KAOLINITE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        KAOLINITE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        KAOLINITE_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        COARSE_KAOLINITE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        KAOLINITE_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_KAOLINITE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SILTY_KAOLINITE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SILTY_KAOLINITE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_KAOLINITE_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_KAOLINITE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SILTY_KAOLINITE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SILTY_KAOLINITE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_KAOLINITE_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SANDY_KAOLINITE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_KAOLINITE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_KAOLINITE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SILTY_KAOLINITE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        KAOLINITE_CLAY_HUMUS(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        KAOLINITE_CLAY_HUMUS_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_KAOLINITE_CLAY_HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_ONLY),
        COARSE_KAOLINITE_CLAY_HUMUS(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SANDY_KAOLINITE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_KAOLINITE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_KAOLINITE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SILTY_KAOLINITE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_KAOLINITE_CLAY_HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_ONLY),

        // Stoneware Clays
        STONEWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        STONEWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_STONEWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SANDY_STONEWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_STONEWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_STONEWARE_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_STONEWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SANDY_STONEWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SANDY_STONEWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SANDY_STONEWARE_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        STONEWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_STONEWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        STONEWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        STONEWARE_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        COARSE_STONEWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        STONEWARE_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_STONEWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SILTY_STONEWARE_CLAY(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SILTY_STONEWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_STONEWARE_CLAY_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_STONEWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        COARSE_SILTY_STONEWARE_CLAY_LOAM(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SILTY_STONEWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SILTY_STONEWARE_CLAY_LOAM_PODZOL(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SANDY_STONEWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_STONEWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_STONEWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SILTY_STONEWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        STONEWARE_CLAY_HUMUS(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        STONEWARE_CLAY_HUMUS_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        DRY_STONEWARE_CLAY_HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_ONLY),
        COARSE_STONEWARE_CLAY_HUMUS(Material.GROUND, false, Specification.VERTICAL_AND_HORIZONTAL),
        SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SANDY_STONEWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_STONEWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_STONEWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SILTY_STONEWARE_CLAY_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS(Material.GRASS, true, Specification.VERTICAL_ONLY),
        SPARSE_STONEWARE_CLAY_HUMUS_GRASS(Material.GROUND, true, Specification.VERTICAL_ONLY),

        // Farmland
        FARMLAND(Material.GROUND, false, Specification.VERTICAL_ONLY),
        LOAMY_SAND_FARMLAND(Material.GROUND, false, Specification.VERTICAL_ONLY),
        SANDY_LOAM_FARMLAND(Material.GROUND, false, Specification.VERTICAL_ONLY),
        LOAM_FARMLAND(Material.GROUND, false, Specification.VERTICAL_ONLY),
        SILT_LOAM_FARMLAND(Material.GROUND, false, Specification.VERTICAL_ONLY),
        SILT_FARMLAND(Material.GROUND, false, Specification.VERTICAL_ONLY),
        HUMUS_FARMLAND(Material.GROUND, false, Specification.VERTICAL_ONLY),

        // Path
        PATH(Material.GROUND, false, Specification.VERTICAL_ONLY),
        LOAMY_SAND_PATH(Material.GROUND, false, Specification.VERTICAL_ONLY),
        SANDY_LOAM_PATH(Material.GROUND, false, Specification.VERTICAL_ONLY),
        LOAM_PATH(Material.GROUND, false, Specification.VERTICAL_ONLY),
        SILT_LOAM_PATH(Material.GROUND, false, Specification.VERTICAL_ONLY),
        SILT_PATH(Material.GROUND, false, Specification.VERTICAL_ONLY),
        HUMUS_PATH(Material.GROUND, false, Specification.VERTICAL_ONLY);

        public final Material material;
        public final boolean isGrass;

        /*
        private final FallingBlockType gravType;

        RockTFCF(Material material, FallingBlockType gravType, boolean isGrass)
        {
            this.material = material;
            this.gravType = gravType;
            this.isGrass = isGrass;
        }
        */

        @Nullable
        private final Specification fallingSpecification;

        Type(Material material, boolean isGrass, @Nullable Specification fallingSpecification) {
            this.material = material;
            this.isGrass = isGrass;
            this.fallingSpecification = fallingSpecification;
        }

        public boolean canFall() {
            return fallingSpecification != null;
        }

        public boolean canFallHorizontal() {
            return fallingSpecification != null && fallingSpecification.canFallHorizontally();
        }

        public boolean canFallHorizontally() {
            return fallingSpecification != null && fallingSpecification.canFallHorizontally();
        }

        public Type getNonGrassVersion() {
            if (!isGrass) return this;
            switch (this) {
                case GRASS:
                case ROOTED_DIRT:
                case SPARSE_GRASS:
                case PODZOL:
                    return DIRT;
                case DRY_GRASS:
                    return DIRT;
                case CLAY_GRASS:
                case SPARSE_CLAY_GRASS:
                case DRY_CLAY_GRASS:
                case CLAY_PODZOL:
                    return CLAY;

                case BOG_IRON_GRASS:
                case DRY_BOG_IRON_GRASS:
                case SPARSE_BOG_IRON_GRASS:
                case BOG_IRON_PODZOL:
                    return BOG_IRON;
                case SPARSE_LOAMY_SAND_GRASS:
                case DRY_LOAMY_SAND_GRASS:
                case LOAMY_SAND_GRASS:
                case LOAMY_SAND_PODZOL:
                case LOAMY_SAND_PATH:
                    return LOAMY_SAND;
                case SPARSE_SANDY_LOAM_GRASS:
                case DRY_SANDY_LOAM_GRASS:
                case SANDY_LOAM_GRASS:
                case SANDY_LOAM_PODZOL:
                case SANDY_LOAM_PATH:
                    return SANDY_LOAM;
                case SPARSE_SANDY_CLAY_LOAM_GRASS:
                case DRY_SANDY_CLAY_LOAM_GRASS:
                case SANDY_CLAY_LOAM_GRASS:
                case SANDY_CLAY_LOAM_PODZOL:
                    return SANDY_CLAY_LOAM;
                case SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
                case DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
                case SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
                case SANDY_EARTHENWARE_CLAY_LOAM_PODZOL:
                    return SANDY_EARTHENWARE_CLAY_LOAM;
                case SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
                case DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
                case SANDY_KAOLINITE_CLAY_LOAM_GRASS:
                case SANDY_KAOLINITE_CLAY_LOAM_PODZOL:
                    return SANDY_KAOLINITE_CLAY_LOAM;
                case SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS:
                case DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS:
                case SANDY_STONEWARE_CLAY_LOAM_GRASS:
                case SANDY_STONEWARE_CLAY_LOAM_PODZOL:
                    return SANDY_STONEWARE_CLAY_LOAM;
                case SPARSE_SANDY_CLAY_GRASS:
                case DRY_SANDY_CLAY_GRASS:
                case SANDY_CLAY_GRASS:
                case SANDY_CLAY_PODZOL:
                    return SANDY_CLAY;
                case SPARSE_SANDY_EARTHENWARE_CLAY_GRASS:
                case DRY_SANDY_EARTHENWARE_CLAY_GRASS:
                case SANDY_EARTHENWARE_CLAY_GRASS:
                case SANDY_EARTHENWARE_CLAY_PODZOL:
                    return SANDY_EARTHENWARE_CLAY;
                case SPARSE_SANDY_KAOLINITE_CLAY_GRASS:
                case DRY_SANDY_KAOLINITE_CLAY_GRASS:
                case SANDY_KAOLINITE_CLAY_GRASS:
                case SANDY_KAOLINITE_CLAY_PODZOL:
                    return SANDY_KAOLINITE_CLAY;
                case SPARSE_SANDY_STONEWARE_CLAY_GRASS:
                case DRY_SANDY_STONEWARE_CLAY_GRASS:
                case SANDY_STONEWARE_CLAY_GRASS:
                case SANDY_STONEWARE_CLAY_PODZOL:
                    return SANDY_STONEWARE_CLAY;
                case SPARSE_LOAM_GRASS:
                case DRY_LOAM_GRASS:
                case LOAM_GRASS:
                case LOAM_PODZOL:
                case LOAM_PATH:
                    return LOAM;
                case SPARSE_CLAY_LOAM_GRASS:
                case DRY_CLAY_LOAM_GRASS:
                case CLAY_LOAM_GRASS:
                case CLAY_LOAM_PODZOL:
                    return CLAY_LOAM;
                case SPARSE_EARTHENWARE_CLAY_LOAM_GRASS:
                case DRY_EARTHENWARE_CLAY_LOAM_GRASS:
                case EARTHENWARE_CLAY_LOAM_GRASS:
                case EARTHENWARE_CLAY_LOAM_PODZOL:
                    return EARTHENWARE_CLAY_LOAM;
                case SPARSE_KAOLINITE_CLAY_LOAM_GRASS:
                case DRY_KAOLINITE_CLAY_LOAM_GRASS:
                case KAOLINITE_CLAY_LOAM_GRASS:
                case KAOLINITE_CLAY_LOAM_PODZOL:
                    return KAOLINITE_CLAY_LOAM;
                case SPARSE_STONEWARE_CLAY_LOAM_GRASS:
                case DRY_STONEWARE_CLAY_LOAM_GRASS:
                case STONEWARE_CLAY_LOAM_GRASS:
                case STONEWARE_CLAY_LOAM_PODZOL:
                    return STONEWARE_CLAY_LOAM;
                case SPARSE_SILTY_CLAY_GRASS:
                case DRY_SILTY_CLAY_GRASS:
                case SILTY_CLAY_GRASS:
                case SILTY_CLAY_PODZOL:
                    return SILTY_CLAY;
                case SPARSE_SILTY_EARTHENWARE_CLAY_GRASS:
                case DRY_SILTY_EARTHENWARE_CLAY_GRASS:
                case SILTY_EARTHENWARE_CLAY_GRASS:
                case SILTY_EARTHENWARE_CLAY_PODZOL:
                    return SILTY_EARTHENWARE_CLAY;
                case SPARSE_SILTY_KAOLINITE_CLAY_GRASS:
                case DRY_SILTY_KAOLINITE_CLAY_GRASS:
                case SILTY_KAOLINITE_CLAY_GRASS:
                case SILTY_KAOLINITE_CLAY_PODZOL:
                    return SILTY_KAOLINITE_CLAY;
                case SPARSE_SILTY_STONEWARE_CLAY_GRASS:
                case DRY_SILTY_STONEWARE_CLAY_GRASS:
                case SILTY_STONEWARE_CLAY_GRASS:
                case SILTY_STONEWARE_CLAY_PODZOL:
                    return SILTY_STONEWARE_CLAY;
                case SPARSE_SILTY_CLAY_LOAM_GRASS:
                case DRY_SILTY_CLAY_LOAM_GRASS:
                case SILTY_CLAY_LOAM_GRASS:
                case SILTY_CLAY_LOAM_PODZOL:
                    return SILTY_CLAY_LOAM;
                case SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
                case DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
                case SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
                case SILTY_EARTHENWARE_CLAY_LOAM_PODZOL:
                    return SILTY_EARTHENWARE_CLAY_LOAM;
                case SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
                case DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
                case SILTY_KAOLINITE_CLAY_LOAM_GRASS:
                case SILTY_KAOLINITE_CLAY_LOAM_PODZOL:
                    return SILTY_KAOLINITE_CLAY_LOAM;
                case SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS:
                case DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS:
                case SILTY_STONEWARE_CLAY_LOAM_GRASS:
                case SILTY_STONEWARE_CLAY_LOAM_PODZOL:
                    return SILTY_STONEWARE_CLAY_LOAM;
                case SPARSE_SILT_LOAM_GRASS:
                case DRY_SILT_LOAM_GRASS:
                case SILT_LOAM_GRASS:
                case SILT_LOAM_PODZOL:
                case SILT_LOAM_PATH:
                    return SILT_LOAM;
                case SPARSE_SILT_GRASS:
                case DRY_SILT_GRASS:
                case SILT_GRASS:
                case SILT_PODZOL:
                case SILT_PATH:
                    return SILT;
                case EARTHENWARE_CLAY_GRASS:
                case SPARSE_EARTHENWARE_CLAY_GRASS:
                case DRY_EARTHENWARE_CLAY_GRASS:
                case EARTHENWARE_CLAY_PODZOL:
                    return EARTHENWARE_CLAY;
                case KAOLINITE_CLAY_GRASS:
                case SPARSE_KAOLINITE_CLAY_GRASS:
                case DRY_KAOLINITE_CLAY_GRASS:
                case KAOLINITE_CLAY_PODZOL:
                    return KAOLINITE_CLAY;
                case STONEWARE_CLAY_GRASS:
                case SPARSE_STONEWARE_CLAY_GRASS:
                case DRY_STONEWARE_CLAY_GRASS:
                case STONEWARE_CLAY_PODZOL:
                    return STONEWARE_CLAY;
                case HUMUS_GRASS:
                case SPARSE_HUMUS_GRASS:
                case DRY_HUMUS_GRASS:
                case HUMUS_PATH:
                    return HUMUS;
                case CLAY_HUMUS_GRASS:
                case SPARSE_CLAY_HUMUS_GRASS:
                case DRY_CLAY_HUMUS_GRASS:
                    return CLAY_HUMUS;
                case EARTHENWARE_CLAY_HUMUS_GRASS:
                case SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS:
                case DRY_EARTHENWARE_CLAY_HUMUS_GRASS:
                    return EARTHENWARE_CLAY_HUMUS;
                case KAOLINITE_CLAY_HUMUS_GRASS:
                case SPARSE_KAOLINITE_CLAY_HUMUS_GRASS:
                case DRY_KAOLINITE_CLAY_HUMUS_GRASS:
                    return KAOLINITE_CLAY_HUMUS;
                case STONEWARE_CLAY_HUMUS_GRASS:
                case SPARSE_STONEWARE_CLAY_HUMUS_GRASS:
                case DRY_STONEWARE_CLAY_HUMUS_GRASS:
                    return STONEWARE_CLAY_HUMUS;
            }
            throw new IllegalStateException("Someone forgot to add enum constants to this switch case...");
        }

        public Type getGrassVersion(Type spreader) {
            if (!spreader.isGrass) throw new IllegalArgumentException("Non-grass can't spread.");
            // System.out.println(this + " " + spreader);
            switch (this) {
                case DIRT:
                    return spreader == DRY_GRASS ? DRY_GRASS : GRASS;
                case CLAY:
                    return CLAY_GRASS;
                case BOG_IRON:
                    if (isDryGrass(spreader))
                        return DRY_BOG_IRON_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_BOG_IRON_GRASS;
                    else
                        return BOG_IRON_GRASS;
                case LOAMY_SAND:
                    if (isDryGrass(spreader))
                        return DRY_LOAMY_SAND_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_LOAMY_SAND_GRASS;
                    else
                        return LOAMY_SAND_GRASS;
                case SANDY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_SANDY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SANDY_LOAM_GRASS;
                    else
                        return SANDY_LOAM_GRASS;
                case SANDY_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_SANDY_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SANDY_CLAY_LOAM_GRASS;
                    else
                        return SANDY_CLAY_LOAM_GRASS;
                case SANDY_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_SANDY_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SANDY_CLAY_GRASS;
                    else
                        return SANDY_CLAY_GRASS;
                case LOAM:
                    if (isDryGrass(spreader))
                        return DRY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_LOAM_GRASS;
                    else
                        return LOAM_GRASS;
                case CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_CLAY_LOAM_GRASS;
                    else
                        return CLAY_LOAM_GRASS;
                case SILTY_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_SILTY_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SILTY_CLAY_GRASS;
                    else
                        return SILTY_CLAY_GRASS;
                case SILTY_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_SILTY_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SILTY_CLAY_LOAM_GRASS;
                    else
                        return SILTY_CLAY_LOAM_GRASS;
                case SILT_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_SILT_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SILT_LOAM_GRASS;
                    else
                        return SILT_LOAM_GRASS;
                case SILT:
                    if (isDryGrass(spreader))
                        return DRY_SILT_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SILT_GRASS;
                    else
                        return SILT_GRASS;
                case HUMUS:
                    if (isDryGrass(spreader))
                        return DRY_HUMUS_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_HUMUS_GRASS;
                    else
                        return HUMUS_GRASS;
                case CLAY_HUMUS:
                    if (isDryGrass(spreader))
                        return DRY_CLAY_HUMUS_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_CLAY_HUMUS_GRASS;
                    else
                        return CLAY_HUMUS_GRASS;
                case EARTHENWARE_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_EARTHENWARE_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_EARTHENWARE_CLAY_GRASS;
                    else
                        return EARTHENWARE_CLAY_GRASS;
                case SANDY_EARTHENWARE_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS;
                    else
                        return SANDY_EARTHENWARE_CLAY_LOAM_GRASS;
                case SANDY_EARTHENWARE_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_SANDY_EARTHENWARE_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SANDY_EARTHENWARE_CLAY_GRASS;
                    else
                        return SANDY_EARTHENWARE_CLAY_GRASS;
                case EARTHENWARE_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_EARTHENWARE_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_EARTHENWARE_CLAY_LOAM_GRASS;
                    else
                        return EARTHENWARE_CLAY_LOAM_GRASS;
                case SILTY_EARTHENWARE_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_SILTY_EARTHENWARE_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SILTY_EARTHENWARE_CLAY_GRASS;
                    else
                        return SILTY_EARTHENWARE_CLAY_GRASS;
                case SILTY_EARTHENWARE_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS;
                    else
                        return SILTY_EARTHENWARE_CLAY_LOAM_GRASS;
                case EARTHENWARE_CLAY_HUMUS:
                    if (isDryGrass(spreader))
                        return DRY_EARTHENWARE_CLAY_HUMUS_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS;
                    else
                        return EARTHENWARE_CLAY_HUMUS_GRASS;
                case KAOLINITE_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_KAOLINITE_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_KAOLINITE_CLAY_GRASS;
                    else
                        return KAOLINITE_CLAY_GRASS;
                case SANDY_KAOLINITE_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS;
                    else
                        return SANDY_KAOLINITE_CLAY_LOAM_GRASS;
                case SANDY_KAOLINITE_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_SANDY_KAOLINITE_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SANDY_KAOLINITE_CLAY_GRASS;
                    else
                        return SANDY_KAOLINITE_CLAY_GRASS;
                case KAOLINITE_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_KAOLINITE_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_KAOLINITE_CLAY_LOAM_GRASS;
                    else
                        return KAOLINITE_CLAY_LOAM_GRASS;
                case SILTY_KAOLINITE_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_SILTY_KAOLINITE_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SILTY_KAOLINITE_CLAY_GRASS;
                    else
                        return SILTY_KAOLINITE_CLAY_GRASS;
                case SILTY_KAOLINITE_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS;
                    else
                        return SILTY_KAOLINITE_CLAY_LOAM_GRASS;
                case KAOLINITE_CLAY_HUMUS:
                    if (isDryGrass(spreader))
                        return DRY_KAOLINITE_CLAY_HUMUS_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_KAOLINITE_CLAY_HUMUS_GRASS;
                    else
                        return KAOLINITE_CLAY_HUMUS_GRASS;
                case STONEWARE_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_STONEWARE_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_STONEWARE_CLAY_GRASS;
                    else
                        return STONEWARE_CLAY_GRASS;
                case SANDY_STONEWARE_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS;
                    else
                        return SANDY_STONEWARE_CLAY_LOAM_GRASS;
                case SANDY_STONEWARE_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_SANDY_STONEWARE_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SANDY_STONEWARE_CLAY_GRASS;
                    else
                        return SANDY_STONEWARE_CLAY_GRASS;
                case STONEWARE_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_STONEWARE_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_STONEWARE_CLAY_LOAM_GRASS;
                    else
                        return STONEWARE_CLAY_LOAM_GRASS;
                case SILTY_STONEWARE_CLAY:
                    if (isDryGrass(spreader))
                        return DRY_SILTY_STONEWARE_CLAY_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SILTY_STONEWARE_CLAY_GRASS;
                    else
                        return SILTY_STONEWARE_CLAY_GRASS;
                case SILTY_STONEWARE_CLAY_LOAM:
                    if (isDryGrass(spreader))
                        return DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS;
                    else
                        return SILTY_STONEWARE_CLAY_LOAM_GRASS;
                case STONEWARE_CLAY_HUMUS:
                    if (isDryGrass(spreader))
                        return DRY_STONEWARE_CLAY_HUMUS_GRASS;
                    else if (isSparseGrass(spreader))
                        return SPARSE_STONEWARE_CLAY_HUMUS_GRASS;
                    else
                        return STONEWARE_CLAY_HUMUS_GRASS;
            }
            return null;
            // throw new IllegalArgumentException("You cannot get grass from rock types.");
        }

        public static boolean isDryGrass(Type grass) {
            if (!grass.isGrass) throw new IllegalArgumentException("Non-grass can't spread.");

            else if
            (
                    grass == DRY_BOG_IRON_GRASS ||
                            grass == DRY_LOAMY_SAND_GRASS ||
                            grass == DRY_SANDY_LOAM_GRASS ||
                            grass == DRY_SANDY_CLAY_LOAM_GRASS ||
                            grass == DRY_SANDY_CLAY_GRASS ||
                            grass == DRY_LOAM_GRASS ||
                            grass == DRY_CLAY_LOAM_GRASS ||
                            grass == DRY_SILTY_CLAY_GRASS ||
                            grass == DRY_SILTY_CLAY_LOAM_GRASS ||
                            grass == DRY_SILT_LOAM_GRASS ||
                            grass == DRY_SILT_GRASS ||
                            grass == DRY_HUMUS_GRASS ||
                            grass == DRY_CLAY_HUMUS_GRASS
            ) return true;
            return false;
        }

        public static boolean isSparseGrass(Type grass) {
            if (!grass.isGrass) throw new IllegalArgumentException("Non-grass can't spread.");

            else if
            (
                    grass == SPARSE_BOG_IRON_GRASS ||
                            grass == SPARSE_GRASS ||
                            grass == SPARSE_CLAY_GRASS ||
                            grass == SPARSE_LOAMY_SAND_GRASS ||
                            grass == SPARSE_SANDY_LOAM_GRASS ||
                            grass == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                            grass == SPARSE_SANDY_CLAY_GRASS ||
                            grass == SPARSE_LOAM_GRASS ||
                            grass == SPARSE_CLAY_LOAM_GRASS ||
                            grass == SPARSE_SILTY_CLAY_GRASS ||
                            grass == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                            grass == SPARSE_SILT_LOAM_GRASS ||
                            grass == SPARSE_SILT_GRASS ||
                            grass == SPARSE_HUMUS_GRASS ||
                            grass == SPARSE_CLAY_HUMUS_GRASS
            ) return true;
            return false;
        }

        @Nullable
        public Specification getFallingSpecification() {
            return fallingSpecification;
        }
    }

}

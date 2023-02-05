/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.stone;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class TFCBlockRockSlab extends BlockSlab {
    public static final PropertyEnum<Variant> VARIANT = PropertyEnum.create("variant", Variant.class);
    public final Block modelBlock;
    protected Half halfSlab;

    private TFCBlockRockSlab(Rock rock, Type type) {
        this(TFCBlockRockVariant.get(rock, type));
        Block c = TFCBlockRockVariant.get(rock, type);
        //noinspection ConstantConditions
        setHarvestLevel(c.getHarvestTool(c.getDefaultState()), c.getHarvestLevel(c.getDefaultState()));
        useNeighborBrightness = true;
    }

    private TFCBlockRockSlab(Block block) {
        super(block.getDefaultState().getMaterial());
        IBlockState state = blockState.getBaseState();
        if (!isDouble()) state = state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        setDefaultState(state.withProperty(VARIANT, Variant.DEFAULT));
        this.modelBlock = block;
        setLightOpacity(255);
    }

    @Override
    public String getTranslationKey(int meta) {
        return super.getTranslationKey();
    }

    @Override
    public IProperty<?> getVariantProperty() {
        return VARIANT; // why is this not null-tolerable ...
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return Variant.DEFAULT;
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, Variant.DEFAULT);

        if (!this.isDouble()) {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
            i |= 8;
        }

        return i;
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos) {
        return modelBlock.getBlockHardness(blockState, worldIn, pos);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(halfSlab);
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getExplosionResistance(Entity exploder) {
        return modelBlock.getExplosionResistance(exploder);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(halfSlab);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
    }

    @SuppressWarnings("deprecation")
    @Override
    public SoundType getSoundType() {
        return modelBlock.getSoundType();
    }

    public enum Variant implements IStringSerializable {
        DEFAULT;

        @Override
        public String getName() {
            return "default";
        }
    }

    public static class Double extends TFCBlockRockSlab {
        private static final Map<Rock, EnumMap<Type, Double>> ROCK_MAP = new HashMap<>();

        public static Double get(Rock rock, Type type) {
            return ROCK_MAP.get(rock).get(type);
        }


        public Double(Rock rock, Type type) {
            super(rock, type);

            if (!ROCK_MAP.containsKey(rock))
                ROCK_MAP.put(rock, new EnumMap<>(Type.class));
            ROCK_MAP.get(rock).put(type, this);

            // No oredict, because no item.
        }

        @Override
        public boolean isDouble() {
            return true;
        }
    }

    public static class Half extends TFCBlockRockSlab {
        private static final Map<Rock, EnumMap<Rock.Type, Half>> ROCK_MAP = new HashMap<>();

        public static Half get(Rock rock, Rock.Type type) {
            return ROCK_MAP.get(rock).get(type);
        }


        public final Double doubleSlab;

        public Half(Rock rock, Rock.Type type) {
            super(rock, type);

            if (!ROCK_MAP.containsKey(rock))
                ROCK_MAP.put(rock, new EnumMap<>(Type.class));
            ROCK_MAP.get(rock).put(type, this);

            doubleSlab = Double.get(rock, type);
            doubleSlab.halfSlab = this;
            halfSlab = this;
            OreDictionaryHelper.register(this, "slab");
            OreDictionaryHelper.registerRockType(this, type, "slab");
        }


        @Override
        public boolean isDouble() {
            return false;
        }
    }
}

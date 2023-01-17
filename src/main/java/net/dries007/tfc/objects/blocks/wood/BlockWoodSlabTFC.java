package net.dries007.tfc.objects.blocks.wood;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.client.CustomStateMap;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public abstract class BlockWoodSlabTFC extends BlockSlab implements IWoodHandler {

    public static final PropertyEnum<BlockWoodSlabTFC.Variant> VARIANT = PropertyEnum.create("variant", BlockWoodSlabTFC.Variant.class);
    public final Block modelBlock;
    protected BlockWoodSlabTFC.Half halfSlab;

    private static final ResourceLocation MODEL_LOCATION_HALF = new ResourceLocation(MOD_ID, "wood/slab/pattern");
    private static final ResourceLocation MODEL_LOCATION_FULL = new ResourceLocation(MOD_ID, "wood/slab/pattern_double");

    private Tree wood;

    private BlockWoodSlabTFC(Tree wood)
    {
        this(BlockPlanksTFC.get(wood));
        Block c = BlockPlanksTFC.get(wood);
        this.wood = wood;
        //noinspection ConstantConditions
        setHarvestLevel(c.getHarvestTool(c.getDefaultState()), c.getHarvestLevel(c.getDefaultState()));
        useNeighborBrightness = true;
        Blocks.FIRE.setFireInfo(this, 5, 20);
    }

    private BlockWoodSlabTFC(Block block)
    {
        super(block.getDefaultState().getMaterial());
        IBlockState state = blockState.getBaseState();
        if (!isDouble()) state = state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
        setDefaultState(state.withProperty(VARIANT, BlockWoodSlabTFC.Variant.DEFAULT));
        this.modelBlock = block;
        setLightOpacity(255);
    }

    @Override
    public Tree getWood() {
        return wood;
    }

    @Override
    public String getTranslationKey(int meta)
    {
        return super.getTranslationKey();
    }

    @Override
    public IProperty<?> getVariantProperty()
    {
        return VARIANT; // why is this not null-tolerable ...
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack)
    {
        return BlockWoodSlabTFC.Variant.DEFAULT;
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockWoodSlabTFC.Variant.DEFAULT);

        if (!this.isDouble())
        {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;

        if (!this.isDouble() && state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP)
        {
            i |= 8;
        }

        return i;
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return modelBlock.getBlockHardness(blockState, worldIn, pos);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(halfSlab);
    }

    @SuppressWarnings("deprecation")
    @Override
    public float getExplosionResistance(Entity exploder)
    {
        return modelBlock.getExplosionResistance(exploder);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(halfSlab);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return this.isDouble() ? new BlockStateContainer(this, VARIANT) : new BlockStateContainer(this, HALF, VARIANT);
    }

    @SuppressWarnings("deprecation")
    @Override
    public SoundType getSoundType()
    {
        return modelBlock.getSoundType();
    }

    public enum Variant implements IStringSerializable
    {
        DEFAULT;

        @Override
        public String getName()
        {
            return "default";
        }
    }

    public static class Double extends BlockWoodSlabTFC
    {
        private static final Map<Tree, Double> WOOD_MAP = new HashMap<>();
        public static Double get(Tree wood)
        {
            return WOOD_MAP.get(wood);
        }

        public Double(Tree wood)
        {
            super(wood);
            if (WOOD_MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
            // No oredict, because no item.
        }

        @Override
        public boolean isDouble()
        {
            return true;
        }
    }

    public static class Half extends BlockWoodSlabTFC
    {
        private static final Map<Tree, Half> WOOD_MAP = new HashMap<>();
        public static Half get(Tree wood)
        {
            return WOOD_MAP.get(wood);
        }

        public final Double doubleSlab;

        public Half(Tree wood)
        {
            super(wood);
            if (WOOD_MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
            doubleSlab = Double.get(wood);
            doubleSlab.halfSlab = this;
            halfSlab = this;
            OreDictionaryHelper.register(this, "slab");
            OreDictionaryHelper.register(this, "slab", "wood");
            OreDictionaryHelper.register(this, "slab", "wood", wood);
        }

        @Override
        public boolean isDouble()
        {
            return false;
        }

        @SideOnly(Side.CLIENT)
        public void onModelRegister()
        {
            ModelLoader.setCustomStateMapper(this, new CustomStateMap.Builder().customPath(MODEL_LOCATION_HALF).ignore(BlockWoodSlabTFC.VARIANT).build());
            ModelLoader.setCustomStateMapper(this.doubleSlab, new CustomStateMap.Builder().customPath(MODEL_LOCATION_FULL).ignore(BlockWoodSlabTFC.VARIANT).build());

            for (IBlockState state : this.getBlockState().getValidStates()) {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), this.getMetaFromState(state), new ModelResourceLocation(MODEL_LOCATION_HALF, "normal"));
            }
        }
    }
}

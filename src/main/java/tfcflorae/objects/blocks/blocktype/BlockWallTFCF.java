package tfcflorae.objects.blocks.blocktype;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import net.dries007.tfc.api.types.Rock.*;
import net.dries007.tfc.api.types.Rock;

import net.dries007.tfc.api.types.Rock.Type;
import tfcflorae.util.OreDictionaryHelper;

public class BlockWallTFCF  extends BlockWall
{
    private static final Map<Rock, EnumMap<Type, BlockWallTFCF>> TABLE = new HashMap<>();

    public static BlockWallTFCF get(Rock rock, Type rockTFCF)
    {
        return TABLE.get(rock).get(rockTFCF);
    }

    public BlockRockVariant parent;

    public BlockWallTFCF(BlockRockVariant modelBlock)
    {
        super(modelBlock);

        /*
        if (!TABLE.containsKey(modelBlock.rock))
            TABLE.put(modelBlock.rock, new EnumMap<>(Type.class));
        TABLE.get(modelBlock.rock).put(modelBlock.rock, this);

        parent = modelBlock;
        OreDictionaryHelper.register(this, "wall");
        OreDictionaryHelper.registerRockType(this, modelBlock.rock, "wall");*/
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        items.add(new ItemStack(this));
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return 0;
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }
}

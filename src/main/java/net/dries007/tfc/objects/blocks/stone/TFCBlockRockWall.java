/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.stone;

import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

//todo: actually by-pass the variant? or would it be worth adding a mossy texture for nice looking walls
public class TFCBlockRockWall extends BlockWall {
    private static final Map<Rock, EnumMap<Type, TFCBlockRockWall>> TABLE = new HashMap<>();

    public static TFCBlockRockWall get(Rock rock, Type type) {
        return TABLE.get(rock).get(type);
    }

    public final BlockRockVariant parent;

    public TFCBlockRockWall(BlockRockVariant modelBlock) {
        super(modelBlock);

        if (!TABLE.containsKey(modelBlock.rock))
            TABLE.put(modelBlock.rock, new EnumMap<>(Type.class));
        TABLE.get(modelBlock.rock).put(modelBlock.type, this);

        parent = modelBlock;
        OreDictionaryHelper.register(this, "wall");
        OreDictionaryHelper.registerRockType(this, modelBlock.type, "wall");
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }
}

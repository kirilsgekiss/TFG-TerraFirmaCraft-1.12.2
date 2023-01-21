/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.rock;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.dries007.tfc.api.types.Rock.*;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.util.OreDictionaryHelper;

@ParametersAreNonnullByDefault
public class TFCBlockRockStairs extends BlockStairs
{
    private static final Map<Rock, EnumMap<Type, TFCBlockRockStairs>> ROCK_TABLE = new HashMap<>();

    public static TFCBlockRockStairs get(Rock rock, Type type)
    {
        return ROCK_TABLE.get(rock).get(type);
    }

    public TFCBlockRockStairs(Rock rock, Type type)
    {
        super(TFCBlockRockVariant.get(rock, type).getDefaultState());

        if (!ROCK_TABLE.containsKey(rock))
            ROCK_TABLE.put(rock, new EnumMap<>(Type.class));
        ROCK_TABLE.get(rock).put(type, this);

        Block baseBlock = TFCBlockRockVariant.get(rock, type);
        //noinspection ConstantConditions
        setHarvestLevel(baseBlock.getHarvestTool(baseBlock.getDefaultState()), baseBlock.getHarvestLevel(baseBlock.getDefaultState()));
        useNeighborBrightness = true;
        OreDictionaryHelper.register(this, "stair");
        OreDictionaryHelper.registerRockType(this, type, "stair");
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        // Prevents cobble stairs from falling
    }

    @Override
    public void onPlayerDestroy(World worldIn, BlockPos pos, IBlockState state)
    {
        // Prevents chiseled smooth stone stairs from collapsing
    }

    @Override
    public void onBlockAdded(@Nonnull World worldIn, @Nonnull BlockPos pos, IBlockState state)
    {
        // Prevents cobble stairs from falling
    }
}

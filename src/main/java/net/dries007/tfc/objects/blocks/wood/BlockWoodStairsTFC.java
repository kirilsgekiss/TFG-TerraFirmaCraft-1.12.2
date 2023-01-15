package net.dries007.tfc.objects.blocks.wood;

import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import java.util.HashMap;
import java.util.Map;

public class BlockWoodStairsTFC extends BlockStairs implements IWoodHandler {

    private static final Map<Tree, BlockWoodStairsTFC> WOOD_MAP = new HashMap<>();
    public static BlockWoodStairsTFC get(Tree wood)
    {
        return WOOD_MAP.get(wood);
    }

    private final Tree wood;
    public BlockWoodStairsTFC(Tree wood)
    {
        super(BlockPlanksTFC.get(wood).getDefaultState());
        if (WOOD_MAP.put(wood, this) != null)
        {
            throw new IllegalStateException("There can only be one.");
        }

        this.wood = wood;
        Block baseBlock = BlockPlanksTFC.get(wood);
        //noinspection ConstantConditions
        setHarvestLevel(baseBlock.getHarvestTool(baseBlock.getDefaultState()), baseBlock.getHarvestLevel(baseBlock.getDefaultState()));
        useNeighborBrightness = true;

        OreDictionaryHelper.register(this, "stair");
        OreDictionaryHelper.register(this, "stair", "wood");
        OreDictionaryHelper.register(this, "stair", "wood", wood);

        Blocks.FIRE.setFireInfo(this, 5, 20);
    }

    @Override
    public Tree getWood() {
        return wood;
    }
}

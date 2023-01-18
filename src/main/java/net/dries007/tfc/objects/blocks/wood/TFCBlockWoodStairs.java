package net.dries007.tfc.objects.blocks.wood;

import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.client.model.IHasModel;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class TFCBlockWoodStairs extends BlockStairs implements IHasModel, IWoodHandler {

    private final ResourceLocation MODEL_LOCATION = new ResourceLocation(MOD_ID, "wood/stairs");
    private static final Map<Tree, TFCBlockWoodStairs> WOOD_MAP = new HashMap<>();
    public static TFCBlockWoodStairs get(Tree wood)
    {
        return WOOD_MAP.get(wood);
    }

    private final Tree wood;
    public TFCBlockWoodStairs(Tree wood)
    {
        super(TFCBlockPlanks.get(wood).getDefaultState());
        if (WOOD_MAP.put(wood, this) != null)
        {
            throw new IllegalStateException("There can only be one.");
        }

        this.wood = wood;
        Block baseBlock = TFCBlockPlanks.get(wood);
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

    @SideOnly(Side.CLIENT)
    public void onModelRegister() {
        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @NotNull
            protected ModelResourceLocation getModelResourceLocation(@NotNull IBlockState state)
            {
                return new ModelResourceLocation(MODEL_LOCATION, this.getPropertyString(state.getProperties()));
            }
        });

        for (IBlockState state : this.getBlockState().getValidStates()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), this.getMetaFromState(state), new ModelResourceLocation(MODEL_LOCATION, "normal"));
        }
    }
}

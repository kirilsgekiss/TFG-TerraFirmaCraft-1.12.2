/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.wood;

import java.util.HashMap;
import java.util.Map;

import net.dries007.tfc.api.types.Wood;
import net.dries007.tfc.api.util.IWoodHandler;
import net.dries007.tfc.client.CustomStateMap;
import net.dries007.tfc.client.model.IHasModel;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;

import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class TFCBlockFenceGate extends BlockFenceGate implements IHasModel, IWoodHandler
{
    private final ResourceLocation MODEL_LOCATION = new ResourceLocation(MOD_ID, "wood/fence_gate");
    private static final Map<Wood, TFCBlockFenceGate> MAP = new HashMap<>();

    public static TFCBlockFenceGate get(Wood wood)
    {
        return MAP.get(wood);
    }

    private final Wood wood;

    public TFCBlockFenceGate(Wood wood)
    {
        super(BlockPlanks.EnumType.OAK);
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        this.wood = wood;
        setHarvestLevel("axe", 0);
        setHardness(2.0F); // match vanilla
        setResistance(15.0F);
        OreDictionaryHelper.register(this, "fence", "gate", "wood");
        //noinspection ConstantConditions
        OreDictionaryHelper.register(this, "fence", "gate", "wood", wood.getRegistryName().getPath());
        Blocks.FIRE.setFireInfo(this, 5, 20);
    }

    @Override
    public Wood getWood() {
        return wood;
    }

    @Override
    public void onModelRegister()
    {
        ModelLoader.setCustomStateMapper(this, new CustomStateMap.Builder().customPath(MODEL_LOCATION).ignore(BlockFenceGate.POWERED).build());

        for (IBlockState state : this.getBlockState().getValidStates()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), this.getMetaFromState(state), new ModelResourceLocation(MODEL_LOCATION, "inventory"));
        }
    }
}

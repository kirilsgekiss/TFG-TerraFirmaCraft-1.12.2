/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.wood;

import java.util.HashMap;
import java.util.Map;

import net.dries007.tfc.client.model.IHasModel;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.init.Blocks;

import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import org.jetbrains.annotations.NotNull;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class BlockFenceGateTFC extends BlockFenceGate implements IHasModel, IWoodHandler
{
    private final ResourceLocation MODEL_LOCATION = new ResourceLocation(MOD_ID, "wood/fence_gate/pattern");
    private static final Map<Tree, BlockFenceGateTFC> MAP = new HashMap<>();

    public static BlockFenceGateTFC get(Tree wood)
    {
        return MAP.get(wood);
    }

    private final Tree wood;

    public BlockFenceGateTFC(Tree wood)
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
    public Tree getWood() {
        return wood;
    }

    @Override
    public void onModelRegister() {
        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @NotNull
            protected ModelResourceLocation getModelResourceLocation(@NotNull IBlockState state)
            {
                return new ModelResourceLocation(MODEL_LOCATION, "normal");
            }
        });

        for (IBlockState state : this.getBlockState().getValidStates()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), this.getMetaFromState(state), new ModelResourceLocation(MODEL_LOCATION, "inventory"));
        }
    }


}

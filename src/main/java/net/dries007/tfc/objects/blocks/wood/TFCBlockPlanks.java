/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.wood;

import net.dries007.tfc.api.types.Wood;
import net.dries007.tfc.api.util.IWoodHandler;
import net.dries007.tfc.client.model.IHasModel;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class TFCBlockPlanks extends Block implements IHasModel, IWoodHandler {
    private final ResourceLocation MODEL_LOCATION = new ResourceLocation(MOD_ID, "wood/planks");

    private static final Map<Wood, TFCBlockPlanks> MAP = new HashMap<>();

    public static TFCBlockPlanks get(Wood wood) {
        return MAP.get(wood);
    }

    private final Wood wood;

    public TFCBlockPlanks(Wood wood) {
        super(Material.WOOD);
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        this.wood = wood;
        setSoundType(SoundType.WOOD);
        setHardness(2.0F).setResistance(5.0F);
        setHarvestLevel("axe", 0);
        OreDictionaryHelper.register(this, "plank", "wood");
        //noinspection ConstantConditions
        OreDictionaryHelper.register(this, "plank", "wood", wood.getRegistryName().getPath());
        Blocks.FIRE.setFireInfo(this, 5, 20);
    }

    @Override
    public Wood getWood() {
        return wood;
    }

    @Override
    public void onModelRegister() {
        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @NotNull
            protected ModelResourceLocation getModelResourceLocation(@NotNull IBlockState state) {
                return new ModelResourceLocation(MODEL_LOCATION, this.getPropertyString(state.getProperties()));
            }
        });

        for (IBlockState state : this.getBlockState().getValidStates()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), this.getMetaFromState(state), new ModelResourceLocation(MODEL_LOCATION, "normal"));
        }
    }
}

/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.wood;

import net.dries007.tfc.api.types.Wood;
import net.dries007.tfc.api.util.IWoodHandler;
import net.dries007.tfc.client.CustomStateMap;
import net.dries007.tfc.client.model.IHasModel;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.HashMap;
import java.util.Map;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class TFCBlockWoodPressurePlate extends BlockPressurePlate implements IWoodHandler, IHasModel {
    private final ResourceLocation MODEL_LOCATION = new ResourceLocation(MOD_ID, "wood/pressure_plate");
    private static final Map<Wood, TFCBlockWoodPressurePlate> MAP = new HashMap<>();

    public static TFCBlockWoodPressurePlate get(Wood wood) {
        return MAP.get(wood);
    }

    private final Wood wood;

    public TFCBlockWoodPressurePlate(Wood wood) {
        super(Material.WOOD, Sensitivity.EVERYTHING);
        this.wood = wood;
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        setHardness(0.5F);
        setSoundType(SoundType.WOOD);
        Blocks.FIRE.setFireInfo(this, 5, 20);

        OreDictionaryHelper.register(this, "pressure_plate_wood");
    }

    @Override
    public Wood getWood() {
        return wood;
    }

    @Override
    public void onModelRegister() {
        ModelLoader.setCustomStateMapper(this, new CustomStateMap.Builder().customPath(MODEL_LOCATION).build());

        for (IBlockState state : this.getBlockState().getValidStates()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), this.getMetaFromState(state), new ModelResourceLocation(MODEL_LOCATION, "normal"));
        }
    }
}

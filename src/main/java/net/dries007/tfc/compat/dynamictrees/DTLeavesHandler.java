package net.dries007.tfc.compat.dynamictrees;

import com.ferreusveritas.dynamictrees.ModConstants;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.cells.ICellKit;
import com.ferreusveritas.dynamictrees.blocks.LeavesPaging;
import com.ferreusveritas.dynamictrees.blocks.LeavesProperties;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLeaves;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class DTLeavesHandler {
    public static Map<String, LeavesProperties> leavesPropertiesMap = new HashMap<>();
    public static Map<String, ICellKit> treeTypeMap = new HashMap<String, ICellKit>()
    {
        {
            put("acacia", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "acacia")));
            put("ash", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("aspen", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("blackwood", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "darkoak")));
            put("chestnut", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("douglas_fir", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "conifer")));
            put("hickory", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("kapok", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("maple", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("oak", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("flowering_oak", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("dark_oak", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("palm", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "palm")));
            put("pine", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "conifer")));
            put("rosewood", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("sequoia", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "conifer")));
            put("spruce", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "conifer")));
            put("norway_spruce", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "conifer")));
            put("japanese_spruce", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "conifer")));
            put("sycamore", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("birch", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("silver_birch", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("white_cedar", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("white_elm", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("willow", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("weeping_willow", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("hevea_benthamiana", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("hevea_braziliensis", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("hevea_camargoana", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("hevea_camporium", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("hevea_guianensis", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("hevea_microphylla", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("hevea_nitida", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("hevea_pauciflora", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("hevea_rigidifolia", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("hevea_spruceana", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("sapodilla", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
            put("castilla", TreeRegistry.findCellKit(new ResourceLocation(ModConstants.MODID, "deciduous")));
        }
    };

    public static void register()
    {
        for (TFCBlockLeaves leafBlock : TFCBlocks.getAllBlockLeaves())
        {
            LeavesProperties leavesProperties = new LeavesProperties(leafBlock.getDefaultState(), treeTypeMap.get(leafBlock.tree.toString()));

            leavesPropertiesMap.put(leafBlock.tree.toString(), leavesProperties);

            LeavesPaging.getNextLeavesBlock(MOD_ID, leavesProperties);
        }
    }
}

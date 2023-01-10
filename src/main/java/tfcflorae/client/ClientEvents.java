package tfcflorae.client;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import tfcflorae.client.render.animal.RendererSilkMoth;
import net.dries007.tfc.objects.entity.animal.EntitySilkMoth;

import static tfcflorae.TFCFlorae.TFCFLORAE_MODID;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = TFCFLORAE_MODID)
public class ClientEvents 
{
    public static void preInit()
    {
        //RenderingRegistry.registerEntityRenderingHandler(EntityBoatTFCF.class, RenderBoatTFCF::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySilkMoth.class, RendererSilkMoth.FACTORY);
    }
}

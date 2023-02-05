package net.dries007.tfc.client.render.animal;

import net.dries007.tfc.client.model.animal.ModelSilkMoth;
import net.dries007.tfc.objects.entity.animal.TFCEntitySilkMoth;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class RendererSilkMoth extends RenderLiving<TFCEntitySilkMoth> implements IRenderFactory {
    public static final IRenderFactory<TFCEntitySilkMoth> FACTORY = RendererSilkMoth::new;

    private RendererSilkMoth(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelSilkMoth(), 0.1F);
    }

    @Override
    protected ResourceLocation getEntityTexture(TFCEntitySilkMoth entity) {
        return new ResourceLocation(MOD_ID, "textures/entity/animal/silk_moth.png");
    }

    @Override
    public Render createRenderFor(RenderManager manager) {
        return manager.getEntityClassRenderObject(TFCEntitySilkMoth.class);
    }
}

/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client.render.animal;

import net.dries007.tfc.client.model.animal.ModelWildebeestTFC;
import net.dries007.tfc.objects.entity.animal.TFCEntityWildebeest;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

@SideOnly(Side.CLIENT)
@ParametersAreNonnullByDefault
public class RenderWildebeestTFC extends RenderLiving<TFCEntityWildebeest> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/animal/huntable/wildebeest.png");

    public RenderWildebeestTFC(RenderManager manager) {
        super(manager, new ModelWildebeestTFC(), 0.7F);
    }

    @Override
    protected float handleRotationFloat(TFCEntityWildebeest wildebeest, float par2) {
        return 1.0f;
    }

    @Override
    protected ResourceLocation getEntityTexture(TFCEntityWildebeest entity) {
        return TEXTURE;
    }
}

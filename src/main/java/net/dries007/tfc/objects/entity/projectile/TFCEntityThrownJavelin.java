/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class TFCEntityThrownJavelin extends TFCEntityThrownWeapon {
    public TFCEntityThrownJavelin(World world) {
        super(world);
    }

    public TFCEntityThrownJavelin(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public TFCEntityThrownJavelin(World world, EntityLivingBase shooter) {
        super(world, shooter);
    }
}

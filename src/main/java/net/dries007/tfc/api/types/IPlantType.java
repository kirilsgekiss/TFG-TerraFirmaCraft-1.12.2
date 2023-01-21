/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.api.types;

import net.dries007.tfc.objects.blocks.plants.TFCBlockPlant;
import net.minecraft.block.material.Material;

public interface IPlantType
{
    TFCBlockPlant create(Plant plant);

    Material getPlantMaterial();
}

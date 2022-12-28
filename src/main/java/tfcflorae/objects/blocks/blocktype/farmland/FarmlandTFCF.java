package tfcflorae.objects.blocks.blocktype.farmland;

import net.dries007.tfc.api.types.Rock;

import net.dries007.tfc.objects.blocks.stone.BlockRockVariantFallable;
import net.dries007.tfc.api.types.Rock.Type;

public abstract class FarmlandTFCF extends BlockRockVariantFallable
{
    public FarmlandTFCF(Type rockTFCF, Rock rock)
    {
        super(rockTFCF, rock);
    }
}

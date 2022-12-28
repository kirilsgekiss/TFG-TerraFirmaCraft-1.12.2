package tfcflorae.objects.blocks.blocktype.farmland;

import net.dries007.tfc.api.types.Rock;

import tfcflorae.objects.blocks.blocktype.BlockRockVariantFallableTFCF;
import net.dries007.tfc.api.types.Rock.Type;

public abstract class FarmlandTFCF extends BlockRockVariantFallableTFCF
{
    public FarmlandTFCF(Type rockTFCF, Rock rock)
    {
        super(rockTFCF, rock);
    }
}

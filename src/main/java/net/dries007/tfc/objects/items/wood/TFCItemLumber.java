/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items.wood;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.api.types.Wood;
import net.dries007.tfc.api.util.IWoodHandler;
import net.minecraft.item.ItemStack;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.items.TFCItem;
import net.dries007.tfc.util.OreDictionaryHelper;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TFCItemLumber extends TFCItem implements IWoodHandler
{
    private static final Map<Wood, TFCItemLumber> MAP = new HashMap<>();

    public static TFCItemLumber get(Wood wood)
    {
        return MAP.get(wood);
    }

    public static ItemStack get(Wood wood, int amount)
    {
        return new ItemStack(MAP.get(wood), amount);
    }

    private final Wood wood;

    public TFCItemLumber(Wood wood)
    {
        this.wood = wood;
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        setMaxDamage(0);
        OreDictionaryHelper.register(this, "lumber");
        //noinspection ConstantConditions
        OreDictionaryHelper.register(this, "lumber", wood.getRegistryName().getPath());
    }

    @Override
    public Wood getWood() {
        return wood;
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack)
    {
        return Weight.VERY_LIGHT;
    }
}

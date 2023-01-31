package org.labellum.mc.dynamictreestfc.proxy;

import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.world.classic.worldgen.trees.dt.TreeGenDynamic;
import net.dries007.tfc.objects.blocks.ModBlocks;
import net.dries007.tfc.objects.blocks.wood.tree.TFCTrees;

public class CommonProxy
{
    public void preInit()
    {
        ModBlocks.preInit();
        TFCTrees.preInit();
    }

    public void init()
    {
        replaceGenerators();
    }

    private void replaceGenerators()
    {
        for (Tree tree : TFCRegistries.TREES.getValuesCollection())
        {
            tree.setTreeGenerator(new TreeGenDynamic());
        }
    }
}

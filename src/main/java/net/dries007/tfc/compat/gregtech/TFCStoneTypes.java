package net.dries007.tfc.compat.gregtech;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.ore.StoneType;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.stone.BlockRockRaw;
import net.dries007.tfc.types.DefaultRocks;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;

public class TFCStoneTypes {

    @SuppressWarnings("ConstantConditions")
    public static void registerStoneTypes()
    {
        StoneType STONE = new StoneType(0, "stone", SoundType.STONE, OrePrefix.ore, Materials.Stone,
                () -> Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE),
                state -> state.getBlock() instanceof BlockStone && state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE, true);

        StoneType TFC_DIORITE = new StoneType(1, "tfc_diorite", SoundType.STONE, OrePrefix.oreDiorite, Materials.Diorite,
                () -> BlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.DIORITE), Rock.Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof BlockRockRaw && state == BlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.DIORITE), Rock.Type.RAW).getDefaultState(), false);
    }

}

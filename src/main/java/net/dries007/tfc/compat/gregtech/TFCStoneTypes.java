package net.dries007.tfc.compat.gregtech;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.ore.StoneType;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.compat.gregtech.materials.TFCMaterials;
import net.dries007.tfc.compat.gregtech.oreprefix.TFCOrePrefix;
import net.dries007.tfc.objects.blocks.stone.TFCBlockRockRaw;
import net.dries007.tfc.types.DefaultRocks;
import net.minecraft.block.SoundType;

public class TFCStoneTypes {

    @SuppressWarnings("ConstantConditions")
    public static void registerStoneTypes() {
        new StoneType(0, "tfc_marble", SoundType.STONE, OrePrefix.oreMarble, Materials.Marble,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.MARBLE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.MARBLE), Type.RAW).getDefaultState(), true);

        new StoneType(1, "tfc_basalt", SoundType.STONE, OrePrefix.oreBasalt, Materials.Basalt,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.BASALT), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.BASALT), Type.RAW).getDefaultState(), true);

        new StoneType(2, "tfc_andesite", SoundType.STONE, OrePrefix.oreAndesite, Materials.Andesite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.ANDESITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.ANDESITE), Type.RAW).getDefaultState(), true);

        new StoneType(3, "tfc_diorite", SoundType.STONE, OrePrefix.oreDiorite, Materials.Diorite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.DIORITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.DIORITE), Type.RAW).getDefaultState(), true);

        new StoneType(4, "tfc_granite", SoundType.STONE, OrePrefix.oreGranite, Materials.Granite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.GRANITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.GRANITE), Type.RAW).getDefaultState(), true);

        new StoneType(5, "tfc_quartzite", SoundType.STONE, TFCOrePrefix.oreQuartzite, Materials.Quartzite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.QUARTZITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.QUARTZITE), Type.RAW).getDefaultState(), true);

        new StoneType(6, "tfc_soapstone", SoundType.STONE, TFCOrePrefix.oreSoapstone, Materials.Soapstone,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SOAPSTONE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SOAPSTONE), Type.RAW).getDefaultState(), true);

        new StoneType(7, "tfc_rocksalt", SoundType.STONE, TFCOrePrefix.oreRockSalt, Materials.RockSalt,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.ROCKSALT), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.ROCKSALT), Type.RAW).getDefaultState(), true);

        new StoneType(8, "tfc_breccia", SoundType.STONE, TFCOrePrefix.oreBreccia, TFCMaterials.Breccia,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.BRECCIA), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.BRECCIA), Type.RAW).getDefaultState(), true);

        new StoneType(9, "tfc_catlinite", SoundType.STONE, TFCOrePrefix.oreCatlinite, TFCMaterials.Catlinite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.CATLINITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.CATLINITE), Type.RAW).getDefaultState(), true);

        new StoneType(10, "tfc_chalk", SoundType.STONE, TFCOrePrefix.oreChalk, TFCMaterials.Chalk,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.CHALK), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.CHALK), Type.RAW).getDefaultState(), true);

        new StoneType(11, "tfc_chert", SoundType.STONE, TFCOrePrefix.oreChert, TFCMaterials.Chert,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.CHERT), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.CHERT), Type.RAW).getDefaultState(), true);

        new StoneType(12, "tfc_claystone", SoundType.STONE, TFCOrePrefix.oreClaystone, TFCMaterials.Claystone,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.CLAYSTONE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.CLAYSTONE), Type.RAW).getDefaultState(), true);

        new StoneType(13, "tfc_conglomerate", SoundType.STONE, TFCOrePrefix.oreConglomerate, TFCMaterials.Conglomerate,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.CONGLOMERATE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.CONGLOMERATE), Type.RAW).getDefaultState(), true);

        new StoneType(14, "tfc_dacite", SoundType.STONE, TFCOrePrefix.oreDacite, TFCMaterials.Dacite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.DACITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.DACITE), Type.RAW).getDefaultState(), true);

        new StoneType(15, "tfc_dolomite", SoundType.STONE, TFCOrePrefix.oreDolomite, TFCMaterials.Dolomite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.DOLOMITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.DOLOMITE), Type.RAW).getDefaultState(), true);

        new StoneType(16, "tfc_gabbro", SoundType.STONE, TFCOrePrefix.oreGabbro, TFCMaterials.Gabbro,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.GABBRO), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.GABBRO), Type.RAW).getDefaultState(), true);

        new StoneType(17, "tfc_gneiss", SoundType.STONE, TFCOrePrefix.oreGneiss, TFCMaterials.Gneiss,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.GNEISS), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.GNEISS), Type.RAW).getDefaultState(), true);

        new StoneType(18, "tfc_soapstone", SoundType.STONE, TFCOrePrefix.oreKomatiite, TFCMaterials.Komatiite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.KOMATIITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.KOMATIITE), Type.RAW).getDefaultState(), true);

        new StoneType(19, "tfc_limestone", SoundType.STONE, TFCOrePrefix.oreLimestone, TFCMaterials.Limestone,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.LIMESTONE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.LIMESTONE), Type.RAW).getDefaultState(), true);

        new StoneType(20, "tfc_mudstone", SoundType.STONE, TFCOrePrefix.oreMudstone, TFCMaterials.Mudstone,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.MUDSTONE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.MUDSTONE), Type.RAW).getDefaultState(), true);

        new StoneType(21, "tfc_novaculite", SoundType.STONE, TFCOrePrefix.oreNovaculite, TFCMaterials.Novaculite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.NOVACULITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.NOVACULITE), Type.RAW).getDefaultState(), true);

        new StoneType(22, "tfc_peridotite", SoundType.STONE, TFCOrePrefix.orePeridotite, TFCMaterials.Peridotite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.PERIDOTITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.PERIDOTITE), Type.RAW).getDefaultState(), true);

        new StoneType(23, "tfc_phyllite", SoundType.STONE, TFCOrePrefix.orePhyllite, TFCMaterials.Phyllite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.PHYLLITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.PHYLLITE), Type.RAW).getDefaultState(), true);

        new StoneType(24, "tfc_porphyry", SoundType.STONE, TFCOrePrefix.orePorphyry, TFCMaterials.Porphyry,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.PORPHYRY), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.PORPHYRY), Type.RAW).getDefaultState(), true);

        new StoneType(25, "tfc_rhyolite", SoundType.STONE, TFCOrePrefix.oreRhyolite, TFCMaterials.Rhyolite,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.RHYOLITE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.RHYOLITE), Type.RAW).getDefaultState(), true);

        new StoneType(26, "tfc_sandstone", SoundType.STONE, TFCOrePrefix.oreSandstone, TFCMaterials.Sandstone,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SANDSTONE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SANDSTONE), Type.RAW).getDefaultState(), true);

        new StoneType(27, "tfc_schist", SoundType.STONE, TFCOrePrefix.oreSchist, TFCMaterials.Schist,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SCHIST), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SCHIST), Type.RAW).getDefaultState(), true);

        new StoneType(28, "tfc_shale", SoundType.STONE, TFCOrePrefix.oreShale, TFCMaterials.Shale,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SHALE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SHALE), Type.RAW).getDefaultState(), true);

        new StoneType(29, "tfc_siltstone", SoundType.STONE, TFCOrePrefix.oreSiltstone, TFCMaterials.Siltstone,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SILTSTONE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SILTSTONE), Type.RAW).getDefaultState(), true);

        new StoneType(30, "tfc_slate", SoundType.STONE, TFCOrePrefix.oreSlate, TFCMaterials.Slate,
                () -> TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SLATE), Type.RAW).getDefaultState(),
                state -> state.getBlock() instanceof TFCBlockRockRaw && state == TFCBlockRockRaw.get(TFCRegistries.ROCKS.getValue(DefaultRocks.SLATE), Type.RAW).getDefaultState(), true);
    }

}

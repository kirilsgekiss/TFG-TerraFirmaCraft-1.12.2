package net.dries007.tfc.mixins.gregtech;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.util.IBlockOre;
import gregtech.client.model.IModelSupplier;
import gregtech.common.blocks.BlockOre;
import net.dries007.tfc.compat.gregtech.oreprefix.TFCOrePrefix;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nonnull;

@Mixin(value = BlockOre.class, remap = false)
public abstract class BlockOreMixin extends Block implements IBlockOre, IModelSupplier {

    public BlockOreMixin(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
    }

    @Inject(method = "getDrops", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getDrops(Lnet/minecraft/util/NonNullList;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;I)V", ordinal = 0), remap = false, cancellable = true)
    private void onGetDrops(@Nonnull NonNullList<ItemStack> drops, @Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState state, int fortune, CallbackInfo ci) {
        ItemStack stack = ((BlockOre) state.getBlock()).getItem(state);
        MaterialStack materialStack = OreDictUnifier.getMaterial(stack);

        if (materialStack != null) {
            drops.add(OreDictUnifier.get(TFCOrePrefix.oreChunk, materialStack.material));
        }

        ci.cancel();
    }
}

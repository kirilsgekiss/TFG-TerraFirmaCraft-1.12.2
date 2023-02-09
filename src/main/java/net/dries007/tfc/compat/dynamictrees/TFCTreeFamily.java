package net.dries007.tfc.compat.dynamictrees;

import com.ferreusveritas.dynamictrees.ModConstants;
import com.ferreusveritas.dynamictrees.blocks.BlockBranch;
import com.ferreusveritas.dynamictrees.blocks.BlockRooty;
import com.ferreusveritas.dynamictrees.blocks.LeavesProperties;
import com.ferreusveritas.dynamictrees.items.Seed;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenFlareBottom;
import com.ferreusveritas.dynamictrees.systems.featuregen.FeatureGenVine;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeFamily;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockBranchBasic;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockBranchThick;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLog;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLogDT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.dries007.tfc.compat.dynamictrees.dropcreators.DTDropCreatorLog;

public class TFCTreeFamily extends TreeFamily {
    public boolean hasConiferVariants = false;
    private boolean thick = false;

    @Override
    public boolean isThick() {
        return thick;
    }

    public void setThick(boolean thick) {
        this.thick = thick;
    }

    public TFCTreeFamily(ResourceLocation name, Tree tree) {
        super(name);

        switch (getName().getPath()) {
            case "sequoia":
            case "kapok":
                setThick(true);
                //redo this after setting Thick, so get the right branch
                setDynamicBranch(createBranch());
        }
    }

    // need to have ItemStack be BlockLogTFC, but have the tree log be
    // BlockLogDTTFC
    public TreeFamily setPrimitiveLog(IBlockState primLog) {
        TFCBlockLogDT primLogBlock = (TFCBlockLogDT) primLog.getBlock();
        TFCBlockLog log = TFCBlockLog.get(primLogBlock.tree);
        ItemStack stack = new ItemStack(Item.getItemFromBlock(log));
        return super.setPrimitiveLog(primLog, stack);
    }

    //Species need not be created as a nested class.  They can be created after the tree has already been constructed.
    public class TreeTFCSpecies extends Species {
        public TreeTFCSpecies(TFCTreeFamily treeFamily, LeavesProperties prop) {
            super(treeFamily.getName(), treeFamily, prop);
            setupStandardSeedDropping();
            remDropCreator(new ResourceLocation(ModConstants.MODID, "logs"));
            addDropCreator(new DTDropCreatorLog(treeFamily.getName().getPath())); // need our own because stacksize

            switch (treeFamily.getName().getPath()) {
                case "kapok":
                    addGenFeature(new FeatureGenVine().setQuantity(8).setMaxLength(32).setRayDistance(32));//Generate Vines
                    //intentional fall through to set thick parameters for kapok too
                case "sequoia":
                    addGenFeature(new DTFeatureGenMound(2));//Place a 3x3 of dirt under thick trees
                    setSoilLongevity(36);//Grows for a while so it can actually get tall
                    addGenFeature(new FeatureGenFlareBottom());//Flare the bottom

            }
        }

        @Override

        public BlockRooty getRootyBlock(World world, BlockPos rootPos) {
            return TFCBlocks.blockRootyDirt;
        }

        public float getSignalEnergy() {
            return signalEnergy;
        }

        @Override
        public Species generateSeed() {
            Seed seed = new Seed("seed/" + getRegistryName().getPath());
            setSeedStack(new ItemStack(seed));
            return this;
        }

        //TFC style.
        @Override
        public boolean canBoneMeal() {
            return false;
        }
    }

    @Override
    public void createSpecies() {
        setCommonSpecies(new TreeTFCSpecies(this, DTLeavesHandler.leavesPropertiesMap.get(getName().toString())));
        getCommonSpecies().generateSeed();
    }

/* //comment out for now
    @Override
    public List<Item> getRegisterableItems(List<Item> itemList) {
        return itemList;
    }*/

    @Override
    public BlockBranch createBranch() {
        String branchName = "branch/" + getName().getPath();
        return isThick() ? new TFCBlockBranchThick(branchName) : new TFCBlockBranchBasic(branchName);
    }
}

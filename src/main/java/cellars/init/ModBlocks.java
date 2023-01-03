package cellars.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cellars.blocks.*;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block CELLAR_WALL = new CellarWall("cellar_wall", Material.ROCK);
    public static final Block CELLAR_DOOR = new CellarDoor("cellar_door", Material.ROCK);
    public static final Block CELLAR_SHELF = new BlockCellarShelf("cellar_shelf", Material.WOOD);
    public static final Block ICE_BUNKER = new BlockIceBunker("ice_bunker", Material.ROCK);

    public static final Block FREEZE_DRYER = new FreezeDryer("freeze_dryer", Material.ROCK);

    public static final Block CELLAR_AIR = new InfectedAir("infected_air", Material.AIR);
}

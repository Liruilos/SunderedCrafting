package net.grallarius.sunderedcrafting.block;

import net.grallarius.sunderedcrafting.block.firepit.BlockFirePit;
import net.grallarius.sunderedcrafting.block.workbench.ModWorkbenchBL;
import net.grallarius.sunderedcrafting.block.workbench.ModWorkbenchBR;
import net.grallarius.sunderedcrafting.block.workbench.ModWorkbenchUL;
import net.grallarius.sunderedcrafting.block.workbench.ModWorkbenchUR;
import net.minecraft.block.material.Material;

public class ModBlocks {

    public static BlockBase testBlock = new BlockBase(Material.WOOD, "ore_copper");

    public static BlockBranch branch = new BlockBranch("branch");
    public static ItemRock rock = new ItemRock("rock");
    public static BlockRock blockRock = new BlockRock("block_rock");

    public static BlockFirePit firePit = new BlockFirePit("firepit");

    public static ModWorkbenchBL workbenchBL = new ModWorkbenchBL("mod_workbench");
    public static ModWorkbenchBR workbenchBR = new ModWorkbenchBR("mod_workbench_invis");
    public static ModWorkbenchUL workbenchUL = new ModWorkbenchUL("mod_workbench_invis");
    public static ModWorkbenchUR workbenchUR = new ModWorkbenchUR("mod_workbench_invis");


    public static void preInit() {
        testBlock.register();

        branch.register();
        rock.register();
        blockRock.register();

        firePit.register();

        workbenchBL.register();
    }



}

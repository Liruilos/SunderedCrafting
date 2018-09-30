package net.grallarius.sunderedcrafting.block;

import net.grallarius.sunderedcrafting.block.firepit.BlockFirePit;
import net.minecraft.block.material.Material;

public class ModBlocks {

    public static BlockBase testBlock = new BlockBase(Material.WOOD, "ore_copper");

    public static BlockBranch branch = new BlockBranch("branch");
    public static ItemRock rock = new ItemRock("rock");
    public static BlockRock blockRock = new BlockRock("block_rock");

    public static BlockFirePit firePit = new BlockFirePit("firepit");


    public static void preInit() {
        testBlock.register();

        branch.register();
        rock.register();
        blockRock.register();

        firePit.register();
    }



}

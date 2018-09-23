package net.grallarius.sunderedcrafting.block;

import net.minecraft.block.material.Material;

public class ModBlocks {

    public static BlockBase testBlock = new BlockBase(Material.WOOD, "ore_copper");

    public static void preInit() {
        testBlock.register();
    }



}

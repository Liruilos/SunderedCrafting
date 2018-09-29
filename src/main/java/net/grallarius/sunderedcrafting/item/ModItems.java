package net.grallarius.sunderedcrafting.item;

import net.grallarius.sunderedcrafting.SunderedCrafting;
import net.grallarius.sunderedcrafting.item.tool.ItemModSword;

public class ModItems {

    public static ItemOreChunk ingotCopper = new ItemOreChunk("ore_chunk_copper", "ingotCopper");
    public static ItemModSword copperSword = new ItemModSword(SunderedCrafting.copperToolMaterial, "copper_sword");



    public static void preInit() {
                ingotCopper.register();
                copperSword.register();
    }


}

package net.grallarius.sunderedcrafting.item;

import net.grallarius.sunderedcrafting.SunderedCrafting;
import net.grallarius.sunderedcrafting.item.tool.ItemModSword;

public class ModItems {

    public static ItemOreChunk oreChunkCopper = new ItemOreChunk("ore_chunk_copper", "ingotCopper");
    public static ItemModSword copperSword = new ItemModSword(SunderedCrafting.copperToolMaterial, "copper_sword");

    public static ItemBase plantFibre = new ItemBase("plant_fibre");



    public static void preInit() {
                oreChunkCopper.register();
                copperSword.register();

                plantFibre.register();
    }


}

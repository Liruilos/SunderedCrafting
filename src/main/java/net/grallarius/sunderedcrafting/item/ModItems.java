package net.grallarius.sunderedcrafting.item;

import net.grallarius.sunderedcrafting.SunderedCrafting;
import net.grallarius.sunderedcrafting.item.tool.ItemModSword;

public class ModItems {

    public static ItemOreChunk ingotCopper = new ItemOreChunk("ingot_copper", "ingotCopper");
//    public static ItemModSword copperSword = new ItemModSword(SunderedCrafting.copperToolMaterial, "copper_sword");

    public static ItemBase ingotWobble = new ItemBase("wobble");


    public static void preInit() {
                ingotCopper.register();
                ingotWobble.register();
//                copperSword.register();
    }

//    public static void registerModels() {
//        ingotCopper.register();
//        copperSword.register();
//        ingotWobble.register();
//    }
}

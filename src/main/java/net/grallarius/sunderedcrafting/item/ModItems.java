package net.grallarius.sunderedcrafting.item;

import net.grallarius.sunderedcrafting.SunderedCrafting;
import net.grallarius.sunderedcrafting.item.tool.ItemMallet;
import net.grallarius.sunderedcrafting.item.tool.ItemModSword;

public class ModItems {

    public static ItemOreChunk oreChunkCopper = new ItemOreChunk("ore_chunk_copper", "ingotCopper");
    public static ItemOreChunk oreChunkTin = new ItemOreChunk("ore_chunk_tin", "ingotTin");
    public static ItemOreChunk oreChunkGold = new ItemOreChunk("ore_chunk_gold", "ingotGold");
    public static ItemOreChunk oreChunkZinc = new ItemOreChunk("ore_chunk_zinc", "ingotZinc");
    public static ItemOreChunk oreChunkLead = new ItemOreChunk("ore_chunk_lead", "ingotLead");
    public static ItemOreChunk oreChunkSilver = new ItemOreChunk("ore_chunk_silver", "ingotSilver");
    public static ItemOreChunk oreChunkPlatinum = new ItemOreChunk("ore_chunk_platinum", "ingotPLatinum");
    public static ItemOreChunk oreChunkIron = new ItemOreChunk("ore_chunk_iron", "ingotIron");

    public static ItemModSword copperSword = new ItemModSword(SunderedCrafting.copperToolMaterial, "copper_sword");
    public static ItemMallet woodenMallet = new ItemMallet("wooden_mallet");

    public static ItemBase plantFibre = new ItemBase("plant_fibre");
    public static ItemModLeather rawHide = new ItemModLeather("raw_hide");
    public static ItemModLeather treatedLeather = new ItemModLeather("treated_leather");
    public static ItemModLeather curedLeather = new ItemModLeather("cured_leather");



    public static void preInit() {
                oreChunkCopper.register();
                oreChunkTin.register();
                oreChunkGold.register();
                oreChunkZinc.register();
                oreChunkLead.register();
                oreChunkSilver.register();
                oreChunkPlatinum.register();
                oreChunkIron.register();

                copperSword.register();
                woodenMallet.register();

                plantFibre.register();
                rawHide.register();
                treatedLeather.register();
                curedLeather.register();
    }


}

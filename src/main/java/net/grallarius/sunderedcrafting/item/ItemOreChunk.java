package net.grallarius.sunderedcrafting.item;

import net.minecraftforge.oredict.OreDictionary;

public class ItemOreChunk extends ItemBase{

    private String oreName;

    public ItemOreChunk(String name, String oreName) {
        super(name);

        this.oreName = oreName;
    }

    public void initOreDict() {
        OreDictionary.registerOre(oreName, this);
    }
}
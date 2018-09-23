package net.grallarius.sunderedcrafting.block;

import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class BlockOre extends BlockBase {

    private String oreName;

    public BlockOre(String name, String oreName) {
        super(Material.ROCK, name);

        this.oreName = oreName;

        setHardness(3f);
        setResistance(5f);
    }

    public void initOreDict() {
        OreDictionary.registerOre(oreName, this);
    }

}
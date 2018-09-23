package net.grallarius.sunderedcrafting.item.tool;

import net.grallarius.sunderedcrafting.InvModel;
import net.grallarius.sunderedcrafting.SunderedCrafting;

import static net.grallarius.sunderedcrafting.SunderedCrafting.ITEM_REGISTRY;

public class ItemModSword extends net.minecraft.item.ItemSword {

    private String name;

    public ItemModSword(ToolMaterial material, String name) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
        setCreativeTab(SunderedCrafting.creativeTab);
    }

    public void register() {
        ITEM_REGISTRY.register(this);
        InvModel.add(this, name);
    }
}
package net.grallarius.sunderedcrafting.item;

import net.grallarius.sunderedcrafting.InvModel;
import net.grallarius.sunderedcrafting.SunderedCrafting;
import net.minecraft.item.Item;

import static net.grallarius.sunderedcrafting.SunderedCrafting.ITEM_REGISTRY;

public class ItemBase extends Item {

    protected String name;

    public ItemBase(String name) {
        this.name = name;
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(SunderedCrafting.creativeTab);
        }

    public void register() {
        ITEM_REGISTRY.register(this);
        InvModel.add(this, name);
    }

}


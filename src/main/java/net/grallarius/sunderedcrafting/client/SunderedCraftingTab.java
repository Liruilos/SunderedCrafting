package net.grallarius.sunderedcrafting.client;

import net.grallarius.sunderedcrafting.SunderedCrafting;
import net.grallarius.sunderedcrafting.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SunderedCraftingTab extends CreativeTabs{

    public SunderedCraftingTab() {
        super(SunderedCrafting.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return  new ItemStack(ModItems.oreChunkCopper);
    }

}

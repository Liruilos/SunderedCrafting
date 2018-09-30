package net.grallarius.sunderedcrafting.proxy;

import net.grallarius.sunderedcrafting.block.ModBlocks;
import net.grallarius.sunderedcrafting.entity.ModEntities;
import net.grallarius.sunderedcrafting.item.ModItems;
import net.grallarius.sunderedcrafting.recipe.ModRecipes;
import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ModBlocks.preInit();
        ModItems.preInit();
    }

    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
        ModEntities.init();
        ModEntities.register();
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public String localize(String unlocalized, Object... args) {
        return I18n.translateToLocalFormatted(unlocalized, args);
    }

    public void registerRenderers() {
    }

}

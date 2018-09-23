package net.grallarius.sunderedcrafting.proxy;

import net.grallarius.sunderedcrafting.InvModel;
//import net.grallarius.sunderedcrafting.SunderedCrafting;
//import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
//import net.minecraft.item.Item;
//import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends ServerProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        InvModel.register();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public String localize(String unlocalized, Object... args) {
        return I18n.format(unlocalized, args);
    }

    @Override
    public void registerRenderers() {
    }

}
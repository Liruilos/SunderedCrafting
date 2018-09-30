package net.grallarius.sunderedcrafting.entity;

import net.grallarius.sunderedcrafting.SunderedCrafting;
import net.grallarius.sunderedcrafting.client.renderer.RenderRock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    private static final ResourceLocation stoneRockTexture = new ResourceLocation( "SunderedCrafting:textures/items/rock.png");

    public static void init()
    {

    }

    public static void register()
    {
        EntityRegistry.registerModEntity(stoneRockTexture, EntityRock.class, "rock", 4, SunderedCrafting.instance, 80, 3, true);
    }

    public static void registerRenders()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityRock.class, new RenderRock(Minecraft.getMinecraft().getRenderManager()));
    }

    public static void registerRender(Block block)
    {

    }
}

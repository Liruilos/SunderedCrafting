package net.grallarius.sunderedcrafting;

import net.grallarius.sunderedcrafting.client.SunderedCraftingTab;
import net.grallarius.sunderedcrafting.network.PacketRequestUpdateFirepit;
import net.grallarius.sunderedcrafting.network.PacketRequestUpdateTanningRack;
import net.grallarius.sunderedcrafting.network.PacketUpdateFirepit;
import net.grallarius.sunderedcrafting.network.PacketUpdateTanningRack;
import net.grallarius.sunderedcrafting.proxy.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.registries.IForgeRegistry;

@Mod(modid = SunderedCrafting.MODID, name = SunderedCrafting.NAME, version = SunderedCrafting.VERSION)
public class SunderedCrafting
{
    public static final String MODID = "sunderedcrafting";
    public static final String NAME = "Sundered Crafting";
    public static final String VERSION = "1.0";

    public static IForgeRegistry<Block> BLOCK_REGISTRY = GameRegistry.findRegistry(Block.class);
    public static IForgeRegistry<Item> ITEM_REGISTRY  = GameRegistry.findRegistry(Item.class);

    public static SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(SunderedCrafting.MODID);

    public static final SunderedCraftingTab creativeTab = new SunderedCraftingTab();
    public static final Item.ToolMaterial copperToolMaterial = EnumHelper.addToolMaterial("COPPER", 2, 500, 6, 2,14);

    @Mod.Instance(MODID)
    public static SunderedCrafting instance;

    @SidedProxy(serverSide = "net.grallarius.sunderedcrafting.proxy.ServerProxy",
                clientSide = "net.grallarius.sunderedcrafting.proxy.ClientProxy",
            modId = MODID)
    public static ServerProxy proxy;

    private static byte packetId = 0;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        wrapper.registerMessage(new PacketUpdateFirepit.Handler(), PacketUpdateFirepit.class, packetId++, Side.CLIENT);
        wrapper.registerMessage(new PacketRequestUpdateFirepit.Handler(), PacketRequestUpdateFirepit.class, packetId++, Side.SERVER);
        wrapper.registerMessage(new PacketUpdateTanningRack.Handler(), PacketUpdateTanningRack.class, packetId++, Side.CLIENT);
        wrapper.registerMessage(new PacketRequestUpdateTanningRack.Handler(), PacketRequestUpdateTanningRack.class, packetId++, Side.SERVER);

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }



}

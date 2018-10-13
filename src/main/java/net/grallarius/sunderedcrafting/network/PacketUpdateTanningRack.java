package net.grallarius.sunderedcrafting.network;

import io.netty.buffer.ByteBuf;
import net.grallarius.sunderedcrafting.block.tanningrack.TileEntityTanningRack;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateTanningRack implements IMessage {
    private BlockPos pos;
    private ItemStack stack;

    public PacketUpdateTanningRack(BlockPos pos, ItemStack stack) {
        this.pos = pos;
        this.stack = stack;
    }

    public PacketUpdateTanningRack(TileEntityTanningRack te) {
        this(te.getPos(), te.inventory.getStackInSlot(0));
    }

    public PacketUpdateTanningRack() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        ByteBufUtils.writeItemStack(buf, stack);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        stack = ByteBufUtils.readItemStack(buf);
    }

    public static class Handler implements IMessageHandler<PacketUpdateTanningRack, IMessage> {

        @Override
        public IMessage onMessage(PacketUpdateTanningRack message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                TileEntityTanningRack te = (TileEntityTanningRack)Minecraft.getMinecraft().world.getTileEntity(message.pos);
                te.inventory.setStackInSlot(0, message.stack);
            });
            return null;
        }

    }
}

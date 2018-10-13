package net.grallarius.sunderedcrafting.network;

import io.netty.buffer.ByteBuf;
import net.grallarius.sunderedcrafting.block.tanningrack.TileEntityTanningRack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketRequestUpdateTanningRack implements IMessage {
    private BlockPos pos;
    private int dimension;

    public PacketRequestUpdateTanningRack(BlockPos pos, int dimension) {
        this.pos = pos;
        this.dimension = dimension;
    }

    public PacketRequestUpdateTanningRack(TileEntityTanningRack tep) {
        this(tep.getPos(), tep.getWorld().provider.getDimension());
    }

    public PacketRequestUpdateTanningRack() {
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeInt(dimension);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        dimension = buf.readInt();
    }

    public static class Handler implements IMessageHandler<PacketRequestUpdateTanningRack, PacketUpdateTanningRack> {

        @Override
        public PacketUpdateTanningRack onMessage(PacketRequestUpdateTanningRack message, MessageContext ctx) {
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(message.dimension);
            TileEntityTanningRack te = (TileEntityTanningRack) world.getTileEntity(message.pos);
            if (te != null) {
                return new PacketUpdateTanningRack(te);
            } else {
                return null;
            }
        }

    }
}

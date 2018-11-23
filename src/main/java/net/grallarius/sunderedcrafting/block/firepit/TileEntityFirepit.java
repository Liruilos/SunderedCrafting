package net.grallarius.sunderedcrafting.block.firepit;

import net.grallarius.sunderedcrafting.SunderedCrafting;
import net.grallarius.sunderedcrafting.network.PacketRequestUpdateFirepit;
import net.grallarius.sunderedcrafting.network.PacketUpdateFirepit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityFirepit extends TileEntity implements ITickable {

    public int facing;
    public boolean islit;

    public ItemStackHandler inventory = new ItemStackHandler(2) {

        @Override
        protected void onContentsChanged(int slot){
            if (!world.isRemote) {
                SunderedCrafting.wrapper.sendToAllAround(new PacketUpdateFirepit(TileEntityFirepit.this), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64));
            }
        }

    };

    @Override
    public void update() {
        if (world.isRemote) {
            SunderedCrafting.wrapper.sendToServer(new PacketRequestUpdateFirepit(this));
        }
        if (!world.isRemote){
            //TODO this changes the value but not the state for some reason
            this.world.setBlockState(this.pos, this.world.getBlockState(pos).withProperty(BlockFirePit.ISLIT, this.islit));
        }
        markDirty();
    }

    @Override
    public void onLoad() {
        if (world.isRemote) {
            SunderedCrafting.wrapper.sendToServer(new PacketRequestUpdateFirepit(this));
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
        compound.setInteger("facing", getFacing());
        compound.setBoolean("islit", getLit());
        return super.writeToNBT(compound);
    }


    @Override
    public void readFromNBT(NBTTagCompound compound) {
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));
        facing = compound.getInteger("facing");
        islit = compound.getBoolean("islit");
        setLit(islit);
        super.readFromNBT(compound);
    }

    private void notifyBlockUpdate() {
        final IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        notifyBlockUpdate();
    }

    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return (oldState.getBlock() != newState.getBlock());
    }

    public int getFacing() { return facing; }

    public void setFacing(int facing) { this.facing = facing; }

    public boolean getLit() { return islit; }

    public void setLit(boolean islit) { this.islit = islit; }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getPos(), getPos().add(1, 2, 1));
    }
}

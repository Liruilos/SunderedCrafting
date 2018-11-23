package net.grallarius.sunderedcrafting.block.tanningrack;

import net.grallarius.sunderedcrafting.block.BlockTileEntity;
import net.grallarius.sunderedcrafting.item.ModItems;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class BlockTanningRack extends BlockTileEntity<TileEntityTanningRack> {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyEnum<EnumType> TYPE = PropertyEnum.create("type", EnumType.class);

    public BlockTanningRack(String name){
        super(Material.WOOD, name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TYPE, EnumType.EMPTY));

    }
    @Override
    @Deprecated
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    @Deprecated
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return super.getActualState(state, world, pos).withProperty(TYPE, getType(state, world, pos));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileEntityTanningRack te = getTileEntity(world, pos);
            IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
            ItemStack raw = new ItemStack(ModItems.rawHide);
            ItemStack treated = new ItemStack(ModItems.treatedLeather);
            ItemStack cured = new ItemStack(ModItems.curedLeather);
            ItemStack knife = new ItemStack(ModItems.woodenKnife);
            ItemStack tallow = new ItemStack(ModItems.tallow);

            if (!player.isSneaking() && itemHandler != null) {

                if (player.getHeldItem(hand).isEmpty()) {
                    /**remove items from relevant slot*/
                    if(!itemHandler.getStackInSlot(0).isEmpty()){
                        int amount = itemHandler.getStackInSlot(0).getCount();
                        player.inventory.placeItemBackInInventory(world, itemHandler.extractItem(0, amount,false));
                    }

                } else if(isValidForSlot(player.getHeldItem(hand)) && itemHandler.getStackInSlot(0).isEmpty()) {
                    /**insert items from hand*/
                    ItemStack singleItemFromHand = player.getHeldItem(hand).splitStack(1);
                    int remainder = player.getHeldItem(hand).getCount();
                    ItemStack remainingItems = player.getHeldItem(hand).splitStack(remainder);
                    player.setHeldItem(hand, itemHandler.insertItem(0, singleItemFromHand, false));
                    player.setHeldItem(hand, remainingItems);

                    //alt version for stack limit > 1
/*                    if (itemHandler.getStackInSlot(0).isEmpty()){
                        itemHandler.insertItem(0, player.getHeldItem(hand), false);
                        player.setHeldItem(hand, ItemStack.EMPTY);
                    }
                    if (player.getHeldItem(hand).isItemEqual(itemHandler.getStackInSlot(0))) {
                        int spaceRemaining = 64 - itemHandler.getStackInSlot(0).getCount();
                        ItemStack insertableItems = player.getHeldItem(hand).splitStack(spaceRemaining);
                        itemHandler.insertItem(0, insertableItems, false);
                    }*/
                    state.withProperty(TYPE, getType(state, world, pos));
                    //System.out.println("setting my state to " + getType(state, world, pos));
                    te.markDirty();
                } else if (itemHandler.getStackInSlot(0).isItemEqual(raw) && player.getHeldItem(hand).isItemEqual(knife)){

                    //right click with knife while raw hide is on rack makes treated leather
                    itemHandler.extractItem(0, 1,false);
                    itemHandler.insertItem(0, treated, false);
                    state.withProperty(TYPE, getType(state, world, pos));
                    te.markDirty();

                } else if (itemHandler.getStackInSlot(0).isItemEqual(treated) && player.getHeldItem(hand).isItemEqual(tallow)){

                    //right click with tallow while treated is on rack makes cured leather
                    itemHandler.extractItem(0, 1,false);
                    itemHandler.insertItem(0, cured, false);
                    player.getHeldItem(hand).splitStack(1);
                    state.withProperty(TYPE, getType(state, world, pos));
                    te.markDirty();

                }

                else {return false;}
                te.markDirty();
            }
        }
        return true;
    }

    public static boolean isValidForSlot(ItemStack stack)
    {
        if (stack.isEmpty())
            return false;

        ItemStack allowedStack1 = new ItemStack(ModItems.rawHide);
        ItemStack allowedStack2 = new ItemStack(ModItems.treatedLeather);
        ItemStack allowedStack3 = new ItemStack(ModItems.curedLeather);

        return stack.isItemEqual(allowedStack1) || stack.isItemEqual(allowedStack2) || stack.isItemEqual(allowedStack3);
    }

    @Override
    public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {

        //TODO drop stuff
        TileEntity tanningrack = world.getTileEntity(pos);
        IItemHandler itemHandler = tanningrack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        if(!itemHandler.getStackInSlot(0).isEmpty()){
            EntityItem droppedItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), itemHandler.getStackInSlot(0));
            world.spawnEntity(droppedItem);
        }

        super.breakBlock(world, pos, state);
    }
    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING, TYPE});
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    public EnumType getType(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileEntityTanningRack te = getTileEntity(world, pos);
        IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, state.getValue(FACING));


        if (itemHandler != null){

            ItemStack rawhide = new ItemStack(ModItems.rawHide);
            ItemStack treated = new ItemStack(ModItems.treatedLeather);
            ItemStack cured = new ItemStack(ModItems.curedLeather);

            if (itemHandler.getStackInSlot(0).isItemEqual(rawhide)) {
                return EnumType.RAWHIDE;
            }
            if (itemHandler.getStackInSlot(0).isItemEqual(treated)) {
                return EnumType.TREATED;
            }
            if (itemHandler.getStackInSlot(0).isItemEqual(cured)) {
                return EnumType.CURED;
            }
        }
        return EnumType.EMPTY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public Class<TileEntityTanningRack> getTileEntityClass() {
        return TileEntityTanningRack.class;
    }

    @Nullable
    @Override
    public TileEntityTanningRack createTileEntity(World world, IBlockState state) {
        return new TileEntityTanningRack();
    }

    public enum EnumType implements IStringSerializable {

        EMPTY("empty"),
        RAWHIDE("rawhide"),
        TREATED("treated"),
        CURED("cured");

        private final String name;

        EnumType(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}

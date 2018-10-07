package net.grallarius.sunderedcrafting.block.workbench;

import net.grallarius.sunderedcrafting.block.BlockBase;
import net.grallarius.sunderedcrafting.block.BlockTileEntity;
import net.grallarius.sunderedcrafting.block.ModBlocks;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Random;

public class ModWorkbenchBL extends BlockTileEntity<TileEntityWorkbench> {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;



    public ModWorkbenchBL(String name){
        super(Material.WOOD, name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

    }
    @Override
    @Deprecated
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    public void onBlockPlacedBy(final World world, final BlockPos pos, final IBlockState state, final EntityLivingBase placer, final ItemStack stack) {
        if (world.isAirBlock(pos.up())) {
            world.setBlockState(pos.up(), ModBlocks.workbenchUL.getDefaultState().withProperty(FACING, state.getValue(FACING)));
        }
        BlockPos posRight = pos.offset(state.getValue(FACING).rotateY());
        if (world.isAirBlock(posRight)){
            world.setBlockState(posRight, ModBlocks.workbenchBR.getDefaultState().withProperty(FACING, state.getValue(FACING)));
        }
        if (world.isAirBlock(posRight.up())){
            world.setBlockState(posRight.up(), ModBlocks.workbenchUR.getDefaultState().withProperty(FACING, state.getValue(FACING)));
        }
        //TODO add checks in canplaceblockat or similar to see if all 4 locations are air first

    }

    @Override
    public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {
/*        TileEntity workbench = world.getTileEntity(pos);
        IItemHandler itemHandler = workbench.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);

        for (int i = 0; i < 2; i++) {
            if(!itemHandler.getStackInSlot(i).isEmpty()){
                EntityItem droppedItem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), itemHandler.getStackInSlot(i));
                world.spawnEntity(droppedItem);
            }
        }*/

        if (world.getBlockState(pos.up()).getBlock() == ModBlocks.workbenchUL) {
            world.setBlockToAir(pos.up());
        }
        //TODO make sure it removes the other blocks on right side

        super.breakBlock(world, pos, state);
    }

    /*    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        System.out.println("BL's neighbour has changed! Up is: " + world.getBlockState(pos.up()).getBlock());
        if (world.getBlockState(pos.up()).getBlock() != ModBlocks.workbenchUL) {
            ((World) world).setBlockToAir(pos);
        }
    }*/


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
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

/*    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getByNameOrId("sunderedcrafting:workbench_inventory");
    }*/

    @Override
    public Class<TileEntityWorkbench> getTileEntityClass() {
        return TileEntityWorkbench.class;
    }

    @Nullable
    @Override
    public TileEntityWorkbench createTileEntity(World world, IBlockState state) {
        return new TileEntityWorkbench();
    }

}

package net.grallarius.sunderedcrafting.block.firepit;

import net.grallarius.sunderedcrafting.block.BlockTileEntity;
import net.grallarius.sunderedcrafting.block.tanningrack.BlockTanningRack;
import net.grallarius.sunderedcrafting.block.tanningrack.TileEntityTanningRack;
import net.grallarius.sunderedcrafting.item.ModItems;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.lwjgl.Sys;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockFirePit extends BlockTileEntity<TileEntityFirepit> {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public static final PropertyBool ISLIT = PropertyBool.create("islit");

    protected static final AxisAlignedBB BOUNDBOX = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 1.0D, 0.8D);

    public BlockFirePit(String name){
        super(Material.ROCK, name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ISLIT, false));
    }

    @Override
    @Deprecated
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
    }

    @Override
    @Deprecated
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        return super.getActualState(state, world, pos).withProperty(ISLIT, getLit(state, world, pos));
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityFirepit te = getTileEntity(world, pos);

        if (!world.isRemote) {
            if (player.getHeldItem(hand).isEmpty() && !state.getValue(ISLIT)) {
                state.withProperty(ISLIT, true);
                te.setLit(true);
                System.out.println("changing state to lit");
                te.markDirty();
                return true;
            }
        }
        System.out.println("i didn't change my state");
        te.markDirty();
        return false;
    }

    @Override
    @Deprecated
    public int getLightValue(IBlockState state){
        return state.getValue(ISLIT) ? 8 : 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if ((boolean) stateIn.getValue(ISLIT)) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D + 0.3D;
            double d2 = (double) pos.getZ() + 0.5D;
            double d3 = rand.nextDouble() * 0.6D - 0.3D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);

        }
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, ISLIT});
    }

    @Override
    public Class<TileEntityFirepit> getTileEntityClass() {
        return TileEntityFirepit.class;
    }

    @Nullable
    @Override
    public TileEntityFirepit createTileEntity(World world, IBlockState state) {
        return new TileEntityFirepit();
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getFront(meta));
    }

    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }
    public boolean getLit(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileEntityFirepit te = getTileEntity(world, pos);
        //IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, state.getValue(FACING));

        return te.islit;
    }

    @Override
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDBOX;
    }
}

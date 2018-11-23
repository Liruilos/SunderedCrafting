package net.grallarius.sunderedcrafting.block.firepit;

import net.grallarius.sunderedcrafting.block.BlockTileEntity;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockFirePit extends BlockTileEntity<TileEntityFirepit> {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

    public static final PropertyBool ISLIT = PropertyBool.create("islit");

    protected static final AxisAlignedBB BOUNDBOX = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 1.0D, 0.8D);

    public BlockFirePit(String name){
        super(Material.ROCK, name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ISLIT, false));
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


    @Override
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDBOX;
    }
}

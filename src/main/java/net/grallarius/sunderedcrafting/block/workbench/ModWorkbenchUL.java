package net.grallarius.sunderedcrafting.block.workbench;

import net.grallarius.sunderedcrafting.block.ModBlocks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import static net.grallarius.sunderedcrafting.SunderedCrafting.BLOCK_REGISTRY;

public class ModWorkbenchUL extends ModWorkbenchBL {

    protected static final AxisAlignedBB BOX_NORTH_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.0D, 1.0D, 0.8D, 0.3D);
    protected static final AxisAlignedBB BOX_SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.7D, 0.8D, 0.8D, 1.0D);
    protected static final AxisAlignedBB BOX_WEST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.3D, 0.8D, 0.8D);
    protected static final AxisAlignedBB BOX_EAST_AABB = new AxisAlignedBB(0.7D, 0.0D, 0.2D, 1.0D, 0.8D, 1.0D);

    public ModWorkbenchUL(String name){
        super(name);

        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public void register(Item item) {
        BLOCK_REGISTRY.register(this);
    }

    @Override
    public void register() {
        register(new ItemBlock(this).setRegistryName(getRegistryName()));
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    @Override
    public IBlockState getActualState(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
        // derive facing from block below
        if (world.getBlockState(pos.down()) == ModBlocks.workbenchBL) {
            return state.withProperty(FACING, world.getBlockState(pos.down()).getValue(FACING));
        } else {
            return state;
        }
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {

        if (world.getBlockState(pos.down()).getBlock() == ModBlocks.workbenchBL) {
            world.destroyBlock(pos.down(), true);
        }
        world.setBlockToAir(pos);
    }


    @Override
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        switch (state.getValue(FACING))
        {
            case NORTH:
                return BOX_NORTH_AABB;
            case SOUTH:
                return BOX_SOUTH_AABB;
            case WEST:
                return BOX_WEST_AABB;
            case EAST:
            default:
                return BOX_EAST_AABB;
        }
    }

}

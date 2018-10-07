package net.grallarius.sunderedcrafting.block.workbench;

import net.grallarius.sunderedcrafting.block.ModBlocks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import static net.grallarius.sunderedcrafting.SunderedCrafting.BLOCK_REGISTRY;

public class ModWorkbenchBR extends ModWorkbenchBL {

    public ModWorkbenchBR(String name){
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

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    @Override
    public IBlockState getActualState(final IBlockState state, final IBlockAccess world, final BlockPos pos) {
        // derive facing from block to left TODO make this less shit
        if (world.getBlockState(pos.north()) == ModBlocks.workbenchBL) {
            return state.withProperty(FACING, world.getBlockState(pos.down()).getValue(FACING));
        } if (world.getBlockState(pos.east()) == ModBlocks.workbenchBL) {
            return state.withProperty(FACING, world.getBlockState(pos.down()).getValue(FACING));
        } if (world.getBlockState(pos.south()) == ModBlocks.workbenchBL) {
            return state.withProperty(FACING, world.getBlockState(pos.down()).getValue(FACING));
        } if (world.getBlockState(pos.west()) == ModBlocks.workbenchBL) {
            return state.withProperty(FACING, world.getBlockState(pos.down()).getValue(FACING));
        } else {
            return state;
        }
    }
}

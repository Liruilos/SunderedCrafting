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
import net.minecraft.world.World;

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

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }


    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public void breakBlock(final World world, final BlockPos pos, final IBlockState state) {

        BlockPos posLeft = pos.offset(state.getValue(FACING).rotateYCCW());

        if (world.getBlockState(posLeft).getBlock() == ModBlocks.workbenchBL) {
            world.destroyBlock(posLeft, true);
        }
        world.setBlockToAir(pos);
    }



}

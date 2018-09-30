package net.grallarius.sunderedcrafting.block.firepit;

import net.grallarius.sunderedcrafting.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.EnumFacing;

public class BlockFirePit extends BlockDirectional {

    public static final PropertyBool ISLIT = PropertyBool.create("islit");

    public BlockFirePit(String name){
        super(Material.ROCK, name);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ISLIT, false));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, ISLIT});
    }
}

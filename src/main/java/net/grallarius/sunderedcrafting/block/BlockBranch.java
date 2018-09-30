package net.grallarius.sunderedcrafting.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockBranch extends BlockBase {

    public BlockBranch(String name){
        super(Material.WOOD, name);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
{
    return Item.getByNameOrId("stick");
}

    @Override
    public int quantityDropped(Random rand) {return 1 + rand.nextInt(3); }

}

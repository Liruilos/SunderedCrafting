package net.grallarius.sunderedcrafting.item.tool;

import net.grallarius.sunderedcrafting.item.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMallet extends ItemBase {
    public ItemMallet(ToolMaterial material, String name){
        super(name);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        Block block = worldIn.getBlockState(pos).getBlock();

        if (!(block instanceof BlockWorkbench))
        {
            return EnumActionResult.PASS;
        }
        else
        {
            if (worldIn.getBlockState(pos.up()).getBlock() instanceof BlockAir)
            {
                //TODO do checks to see if full workbench can be made here
            }

            return EnumActionResult.SUCCESS;
        }
    }
}

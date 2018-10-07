package net.grallarius.sunderedcrafting.item.tool;

import net.grallarius.sunderedcrafting.block.firepit.BlockFirePit;
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
    public ItemMallet(String name){
        super(name);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        Block block = worldIn.getBlockState(pos).getBlock();

        if (!(block instanceof BlockFirePit))
        {

            return EnumActionResult.PASS;
        }
        else
        {




            if (worldIn.getBlockState(pos.up()).getBlock() instanceof BlockAir)
            {
                Block blockNorth = worldIn.getBlockState(pos.north()).getBlock();
                Block blockNorthUp = worldIn.getBlockState(pos.north().up()).getBlock();
                Block blockEast = worldIn.getBlockState(pos.east()).getBlock();
                Block blockEastUp = worldIn.getBlockState(pos.east().up()).getBlock();
                Block blockSouth = worldIn.getBlockState(pos.south()).getBlock();
                Block blockSouthUp = worldIn.getBlockState(pos.south().up()).getBlock();
                Block blockWest = worldIn.getBlockState(pos.west()).getBlock();
                Block blockWestUp = worldIn.getBlockState(pos.west().up()).getBlock();

                //System.out.println("is a firepit with air above");
                System.out.println("block to the north is: " + blockNorth + "block north and up is: " + blockNorthUp);

                if (blockNorth instanceof BlockFirePit && blockNorthUp instanceof BlockAir){
                    //TODO add workbench blocks with facing E or W depending on player?
                    System.out.println("player= " + player.getHorizontalFacing() + "   facing= " + facing);

                }
                //TODO do checks to see if full workbench can be made here
            }

            return EnumActionResult.SUCCESS;
        }
    }
}

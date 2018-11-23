package net.grallarius.sunderedcrafting.item.tool;

import net.grallarius.sunderedcrafting.item.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemKnife extends ItemBase {

    public ItemKnife(String name){
        super(name);
    }

    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {

        return EnumActionResult.PASS;
    }
}

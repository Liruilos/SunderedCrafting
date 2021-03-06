package net.grallarius.sunderedcrafting.block.firepit;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotFuel extends SlotItemHandler {

    private final IItemHandler itemHandler;
    private final int index;

    public SlotFuel(IItemHandler itemHandler, int index, int xPosition, int yPosition){
        super(itemHandler, index, xPosition, yPosition);
        this.itemHandler = itemHandler;
        this.index = index;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack)
    {
        if (stack.isEmpty())
            return false;
        /*		System.out.println("burn time of item is: " + TileEntityFurnace.getItemBurnTime(stack) );*/

        //TODO will need to update this for non-vanilla fuel items at some point
        return TileEntityFurnace.getItemBurnTime(stack) != 0;
    }
}

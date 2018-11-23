package net.grallarius.sunderedcrafting.block.tanningrack;

import net.grallarius.sunderedcrafting.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotTanningRack extends SlotItemHandler {
    private final IItemHandler itemHandler;
    private final int index;

    public SlotTanningRack (IItemHandler itemHandler, int index, int xPosition, int yPosition){
        super(itemHandler, index, xPosition, yPosition);
        this.itemHandler = itemHandler;
        this.index = index;
    }

    @Override
    public int getSlotStackLimit() { return 1; }

    @Override
    public int getItemStackLimit(@Nonnull ItemStack stack) {return 1;}

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack)
    {
        if (stack.isEmpty())
            return false;

        ItemStack allowedStack1 = new ItemStack(ModItems.rawHide);
        ItemStack allowedStack2 = new ItemStack(ModItems.treatedLeather);
        ItemStack allowedStack3 = new ItemStack(ModItems.curedLeather);

        if (!stack.isItemEqual(allowedStack1) || !stack.isItemEqual(allowedStack2) || !stack.isItemEqual(allowedStack3)){
            return false;
        }

        IItemHandler handler = this.getItemHandler();
        ItemStack remainder;
        if (handler instanceof IItemHandlerModifiable)
        {
            IItemHandlerModifiable handlerModifiable = (IItemHandlerModifiable) handler;
            ItemStack currentStack = handlerModifiable.getStackInSlot(index);

            handlerModifiable.setStackInSlot(index, ItemStack.EMPTY);

            remainder = handlerModifiable.insertItem(index, stack, true);

            handlerModifiable.setStackInSlot(index, currentStack);
        }
        else
        {
            remainder = handler.insertItem(index, stack, true);
        }
        return remainder.isEmpty() || remainder.getCount() < stack.getCount();
    }

}

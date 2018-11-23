package net.grallarius.sunderedcrafting.block.firepit;

import net.grallarius.sunderedcrafting.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotFirepit extends SlotItemHandler {

    private final IItemHandler itemHandler;
    private final int index;

    public SlotFirepit(IItemHandler itemHandler, int index, int xPosition, int yPosition){
        super(itemHandler, index, xPosition, yPosition);
        this.itemHandler = itemHandler;
        this.index = index;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack)
    {
        if (stack.isEmpty())
            return false;

        ItemStack allowedStack1 = new ItemStack(Item.getByNameOrId("stick"));
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

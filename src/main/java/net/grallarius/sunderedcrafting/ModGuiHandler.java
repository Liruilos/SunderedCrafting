package net.grallarius.sunderedcrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler {
    public static final int KNAPPING = 0;


    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
/*            case PEDESTAL:
                return new ContainerPedestal(player.inventory, (TileEntityPedestal)world.getTileEntity(new BlockPos(x, y, z)));
            case WINDOWBOX:
                return new ContainerWindowbox(player.inventory, (TileEntityWindowbox)world.getTileEntity(new BlockPos(x, y, z)));
            case FLOWERBED:
                return new ContainerFlowerbed(player.inventory, (TileEntityFlowerbed)world.getTileEntity(new BlockPos(x, y, z)));
            case DENSEFLOWERBED:
                return new ContainerDenseFlowerbed(player.inventory, (TileEntityDenseFlowerbed)world.getTileEntity(new BlockPos(x, y, z)));*/
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
/*            case PEDESTAL:
                return new GuiPedestal(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            case WINDOWBOX:
                return new GuiWindowbox(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            case FLOWERBED:
                return new GuiFlowerbed(getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            case DENSEFLOWERBED:
                return new GuiDenseFlowerbed(getServerGuiElement(ID, player, world, x, y, z), player.inventory);*/

            default:
                return null;
        }
    }
}

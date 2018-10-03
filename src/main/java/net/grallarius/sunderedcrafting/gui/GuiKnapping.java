package net.grallarius.sunderedcrafting.gui;

import net.grallarius.sunderedcrafting.SunderedCrafting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiKnapping extends GuiScreen {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(SunderedCrafting.MODID, "textures/gui/gui_stone_knapping.png");
    private GuiButton buttonDone;
    private int guiImageHeight = 209;
    private int guiImageWidth = 81;

    public GuiKnapping(){

    }
    @Override
    public void initGui()
    {
        // DEBUG
        System.out.println("Gui Knapping initGUI()");
        buttonList.clear();
        Keyboard.enableRepeatEvents(true);

        buttonDone = new GuiButton(0, width / 2 + 2, 4 + guiImageHeight,

                98, 20, I18n.format("gui.done", new Object[0]));

        buttonList.add(buttonDone);

    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        buttonDone.visible = true;
    }

    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int parWidth, int parHeight, float p_73863_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(BG_TEXTURE);
        int offsetFromScreenLeft = (width - guiImageWidth ) / 2;


        drawTexturedModalRect(offsetFromScreenLeft, 2, 0, 0, guiImageWidth, guiImageHeight);

        super.drawScreen(parWidth, parHeight, p_73863_3_);
    }

    /**
     * Called when a mouse button is pressed and the mouse is moved around.
     * Parameters are : mouseX, mouseY, lastButtonClicked &
     * timeSinceMouseClick.
     */
    @Override
    protected void mouseClickMove(int parMouseX, int parMouseY, int parLastButtonClicked, long parTimeSinceMouseClick) {}

    @Override
    protected void actionPerformed(GuiButton parButton)
    {
        if (parButton == buttonDone)
        {
            // You can send a packet to server here if you need server to do
            // something
            mc.displayGuiScreen((GuiScreen)null);
        }
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat
     * events
     */
    @Override
    public void onGuiClosed()
    {

    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in
     * single-player
     */
    @Override
    public boolean doesGuiPauseGame()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    static class AxeButton extends GuiButton
    {


        public AxeButton(int parButtonId, int parPosX, int parPosY)
        {
            super(parButtonId, parPosX, parPosY, 23, 13, "");
        }

        // Draws this button to the screen
        @Override
        public void drawButton(Minecraft mc, int parX, int parY, float partialTicks) {
            if (visible)
            {
                boolean isButtonPressed = ((parX >= this.x)

                        && (parY >= this.y)

                        && (parX < (this.x + width))

                        && (parY < (this.y + height)));

                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                mc.getTextureManager().bindTexture(BG_TEXTURE);
                int textureX = 151;
                int textureY = 3;
                int posX = this.x;
                int posY = this.y;

                if (isButtonPressed)
                {
                    textureX += 11;
                }

                GlStateManager.scale(2, 2, 2);
                drawTexturedModalRect(posX/2, posY/2, textureX, textureY, 23, 13);
                GlStateManager.scale(0.5, 0.5, 0.5);
            }
        }
    }
}

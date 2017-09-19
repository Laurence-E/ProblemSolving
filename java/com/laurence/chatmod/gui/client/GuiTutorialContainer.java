package com.laurence.chatmod.gui.client;

//import java.awt.Color;
//
//import com.laurence.chatmod.ChatMod;
//
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.inventory.GuiContainer;
//import net.minecraft.inventory.Container;
//import net.minecraft.util.ChatComponentTranslation;
//import net.minecraft.util.ResourceLocation;
//
//public class GuiTutorialContainer extends GuiContainer {
//
//    public GuiTutorialContainer(Container inventorySlotsIn) {
//		super(inventorySlotsIn);
//	}
//
//	private static final ResourceLocation texture = new ResourceLocation(ChatMod.MODID, "textures/gui/container.png");
//
//    @Override
//    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
//        fontRendererObj.drawString(new ChatComponentTranslation("tile.tutorial_container.name").getFormattedText(), 5, 5, Color.darkGray.getRGB());
//    }
//
//    @Override
//    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
//        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
//        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
//    }
//}

package com.laurence.chatmod.gui;

import com.laurence.chatmod.ChatMod;
import com.laurence.chatmod.gui.client.GuiTutorial;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ChatModGuiHandler implements IGuiHandler {
	
	private EntityPlayer toPlayer;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == ChatMod.PromptGuiID)
		{
			player.addChatComponentMessage(new ChatComponentText("Opening chat Gui"));
			return new GuiTutorial(getToPlayer(), player);
		}
		
		return null;
	}
	
	public EntityPlayer getToPlayer() {
		return this.toPlayer;
	}
	
	public void setToPlayer(EntityPlayer toPlayer) {
		this.toPlayer = toPlayer;
	}

}

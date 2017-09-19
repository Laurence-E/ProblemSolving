package com.laurence.chatmod.commands;

import com.laurence.chatmod.ChatMod;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandHandler {
	
	private ChatMod instance;
	
	public CommandHandler(ChatMod instance) {
		this.instance = instance;
	}
	
	@EventHandler
	public void onServerLoad(FMLServerStartingEvent event) { //Event for when Minecraft is loading
		
		event.registerServerCommand(new HeyCommand()); //Adds the /hey command - creating a new class for the command
		
	}
	
}

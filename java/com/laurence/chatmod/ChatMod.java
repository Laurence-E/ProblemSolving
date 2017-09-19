package com.laurence.chatmod;

import com.laurence.chatmod.commands.CommandHandler;
import com.laurence.chatmod.gui.ChatModGuiHandler;
import com.laurence.chatmod.gui.client.GuiOverlayTutorial;
import com.laurence.chatmod.lib.ProxyCommon;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = ChatMod.MODID, version = ChatMod.VERSION) //Links the mod to its modid and version below - so that these will appear in game
public class ChatMod //Main class for the mod
{
    public static final String MODID = "chatmod"; //Set the modid (same as it is in the packages)
    public static final String VERSION = "1.0"; //Set the mod version
    
    //Create an instance for the mod so that it can be accessed from other Java classes
    @Instance(MODID)
    public static ChatMod instance;
    
    //Creates the proxy classes which will render sounds and textures
    @SidedProxy(serverSide="com.laurence.chatmod.lib.ProxyCommon", clientSide="com.laurence.chatmod.lib.ProxyClient")
    public static ProxyCommon proxy;
    
    //Create variables for handling new commands
    private CommandHandler commandHandler;
    private MinecraftServer server;
    
    //GUI IDs:
    public final static int PromptGuiID = 27;
    
    /*
     * Pre event - for before forge starts up.
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	//Config config = new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + "additional/path/to/your/config.cfg"));
    	
    	this.commandHandler = new CommandHandler(this);
    	
    	//Register the event handler for the new command etc..
    	NetworkRegistry.INSTANCE.registerGuiHandler(instance, new ChatModGuiHandler());
    	MinecraftForge.EVENT_BUS.register(new ChatModEventHandler());
    }
    
    /*
     * Event called while the game server loads. 
     * Used for adding new commands into the game.
     * [Commands are server-side]
     */
    @EventHandler
    public void onServerLoad(FMLServerStartingEvent event)
    {
        this.server = event.getServer(); //Create an instance of the Minecraft server
        
        commandHandler.onServerLoad(event); //Run the main method for the command handler - adding the new command.
        
    }
    
    
    /*
     * Getter method for the command handler class
     */
    public CommandHandler getCommandHandler()
    {
        return this.commandHandler;
    }
    
    /*
     * Getter method for the Minecraft game server instance
     */
    public MinecraftServer getServer()
    {
        return this.server;
    }
    
    /*
     * During forge loading - render textures and handle the proxies
     */
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//Run the methods inside the proxy classes - these will render any new sounds / textures
    	proxy.registerRenders();
    	proxy.registerSounds();
    }
    
    /*
     * Post event - for after forge has loaded onto the game.
     * Forge methods can be used here.
     */
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	//Register the new chat interface (GUI)
    	MinecraftForge.EVENT_BUS.register(new GuiOverlayTutorial());
    	
    }
    
}

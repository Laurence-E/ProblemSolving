package com.laurence.chatmod.lib;

public class ProxyClient extends ProxyCommon { //Client side proxy class - used to render any client side features only
	
	//Override the registerSounds method from the ProxyCommon class - so that anything registered there can also be added to here.
	@Override
	public void registerSounds() {
		
	}
	
	//Override the registerRenders method from the ProxyCommon class.
	@Override
	public void registerRenders() {
		
	}
	
}

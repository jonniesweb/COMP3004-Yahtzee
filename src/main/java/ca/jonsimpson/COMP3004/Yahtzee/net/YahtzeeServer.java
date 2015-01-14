package ca.jonsimpson.COMP3004.Yahtzee.net;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class YahtzeeServer {
	
	public static void main(String[] args) throws Exception {
		
		// start a registry on the default port
		Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		
		// create a rmi server
		ServerCommandServiceImpl commandService = new ServerCommandServiceImpl();
		System.out.println("CommandService server running");
		
		// bind the CommandService to the registry
		registry.rebind(ServerCommandService.LOOKUPNAME, commandService);
		System.out.println("CommandService server ready");
		
	}
}

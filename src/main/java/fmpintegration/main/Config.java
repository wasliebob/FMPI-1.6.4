package fmpintegration.main;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fmpintegration.api.FMPIntegration;

public class Config {

	public static void loadConfig(FMLPreInitializationEvent e){
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		generateBanned(config);
		fullCube = config.get(Configuration.CATEGORY_GENERAL, "fullCube", false, "Set this to true to only register blocks which are a full (1x1x1) cube").getBoolean(fullCube);
		numbering = config.get(Configuration.CATEGORY_GENERAL, "numbering", true, "Set this to true to register blocks which have a number in them (without this only the block with 1 in it will get registered)").getBoolean(numbering);

		config.save();
	}
	public static boolean fullCube;
	public static boolean numbering;

	public static void generateBanned(Configuration config){
		Property bannedMods = config.get(Configuration.CATEGORY_GENERAL, "bannedMods", "");
		bannedMods.comment = "List of banned mods";
		if(bannedMods != null){
		String[] bannedArray = bannedMods.getString().split(",");
	    if(bannedArray != null && bannedArray.length > 0){
	    	for(String s : bannedArray){
	    		if(s != null)
	    			FMPIntegration.banMod(s);
	    	}
	    }
		}
	}
}
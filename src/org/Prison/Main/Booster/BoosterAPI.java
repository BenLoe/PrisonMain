package org.Prison.Main.Booster;

import org.Prison.Main.Files;

public class BoosterAPI {

	
	public static boolean isActivated(){
		BoosterManager.checkBooster();
		if (Files.getDataFile().getBoolean("BoosterEnabled")){
			return true;
		}
		return false;
	}
	
	public static double getBooster(){
		if (isActivated()){
			return Files.getDataFile().getDouble("BoosterMultiply");
		}
		return 0;
	}
}

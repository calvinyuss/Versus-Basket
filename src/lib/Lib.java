package lib;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
public class Lib {
	public static void clscr(){
	    //Clears Screen in java
//	    try {
//	        if (System.getProperty("os.name").contains("Windows"))
//	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//	        else
//	            Runtime.getRuntime().exec("clear");
//	    } catch (IOException | InterruptedException ex) {}
		for(int i=0;i<=30;i++) {
			System.out.println();
		}
	}
	
	public static void pressAnyKeyToContinue()
	 { 
	        System.out.println("Press Enter key to continue...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e){}  
	 }
	
	public static String getKey(Map<String, String[][]> account_datas, String team_name) {
		for(Entry<String, String[][]> entry : account_datas.entrySet()) {
			if(entry.getValue()[1][0] == team_name) {
				return entry.getKey();
			}
		}
		return null;
	}
}

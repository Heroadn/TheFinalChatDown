package heroadn.maverickhunter.TheFinalChatDown.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
	
	 /*
	 * Metodos Responsavel por Registrar um 
     * log em um arquivo de texto  com nome de log.txt
     */
	public static String regLog(String log,String path) {
		try {
			File file = new File(path);	
			FileWriter write = new FileWriter(file,true);
			PrintWriter pw = new PrintWriter(write);
			
			pw.println(log);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return log;
	}
}

package mypag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadFile {
	/**
	 * @param args
	 */
	private File myFile;
	private FileInputStream fis;
	private InputStreamReader isr;
	private BufferedReader br;
	private int lineCount;
	private String strout[];
	
	public ReadFile(String fileAddr) throws FileNotFoundException, UnsupportedEncodingException {
			myFile = new File(fileAddr);
			fis = new FileInputStream(myFile);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);
	}
	public int readText() throws IOException{
			String str;
			//strout = new String[10];
		 	while ((str = br.readLine()) != null) {
		 		strout[lineCount] = str;
		 		lineCount++; 
		    }
		 	br.close();	
		 	return lineCount;	 		
	}
	public String[] getStrout() {
		return strout;
	}
			
	}

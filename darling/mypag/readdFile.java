package mypag;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.Locale;



public class readdFile {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		File myFile = new File("D:\\aaaaa.txt");
		FileInputStream fis = new FileInputStream(myFile);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);		
		String str;
		String strr[] = new String[10];
	 	int lineCount = 0;
	 	
	 	while ((str = br.readLine()) != null) {
	 		strr[lineCount] = str;
	 		lineCount++; 
	    }
	 	br.close();	
	 	//System.out.println(lineCount);
	 		 	
	 	Locale thaiLocale = new Locale("th");
	 	BreakIterator boundary = BreakIterator.getWordInstance(thaiLocale);
	 	for (int i = 0 ; i < lineCount ; i++){			
	 		boundary.setText(strr[i]);	
	 		strr[i] = printEachForward(boundary,strr[i]);
	 		//System.out.println(strr[i]);
	 		
	    }   
			
	}
	public static String printEachForward(BreakIterator boundary, String source) {
	    StringBuffer strout = new StringBuffer();
	    int start = boundary.first();
	    for (int end = boundary.next();
	    		end != BreakIterator.DONE;
	    		start = end, end = boundary.next()) {
	    			    			
	    	    strout.append(source.substring(start, end)+"|");
	    }

	    String input = strout.toString();     
	    return input;
	    }      
}

package mypag;

import java.text.BreakIterator;
import java.util.Locale;

public class WrapToken {

	/**
	 * @param args
	 */
	private String[][] stroutToken;
	private String[] strout;
	private Locale thaiLocale;
	private BreakIterator boundary;
	private int paragCount;
	
	public WrapToken(String[] strout,int lineCount) {
		strout = this.strout;
		paragCount = lineCount;
		thaiLocale = new Locale("th");
		boundary = BreakIterator.getWordInstance(thaiLocale);
	}
	
 	public void wrapInLine() {
 		StringBuffer sb = new StringBuffer();
	    int start = boundary.first();
 		for (int i = 0 ; i < paragCount ; i++){	
 			int j = 0;
 	 		for (int end = boundary.next();
 	 		    		end != BreakIterator.DONE;
 	 		    		start = end, end = boundary.next()) {
 	 		    		j++;	    			
 	 		    	    sb.append(strout[i].substring(start, end)+"|");
 	 		    	    stroutToken[i][j] = sb.toString();    
 	 		    }
 	 		 
		}  
 	} 
	public String[][] stroutToken() {
		return stroutToken;
	}   
}
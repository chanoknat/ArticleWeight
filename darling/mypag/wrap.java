package mypag;
import java.text.BreakIterator;
import java.util.Locale;

public class wrap {
	private int countParagraph;
	public wrap(int cp){
		filePath = fp;		
	}
	public void printEachForward(BreakIterator boundary, String source) {
		String strr[][] = new String[3][];
		String str;
		//String strr[][] = new String[3][];
		Locale thaiLocale = new Locale("th");
		BreakIterator boundary = BreakIterator.getWordInstance(thaiLocale);
		
 
	 	int lineCount = 0;
	 	
	 	 while ((str = br.readLine()) != null) {
	 	    lineCount++; 	
	 		boundary.setText(str);	
	 		str = printEachForward(boundary,str);
	 		strr[lineCount-1] = new String[2];
	 		strr[lineCount-1][0] = str;
	 		
	 	    }
	 	
	 	
	 	
  }  
  


}


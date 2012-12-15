package healthy;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;


public class FrequencyWord {
	private FrequencyText t;
	private Set<Entry<String, Integer>> e;
	private String fileAddress;
	private String textInFile;
	private String textWrapped;
	private int countPara;
	private String[] textSpecPara;
	public FrequencyWord(String fileAddress) throws IOException {
		this.fileAddress = fileAddress; 
		ReadWriteTextFileWithEncoding test = new ReadWriteTextFileWithEncoding(fileAddress, "UTF-8");
	    textInFile = test.read();
	    //System.out.print(textInFile);
	    this.wrapText();
	    this.wrapParagraph();
	    this.calParaFrequency();
	    this.calFrequency();
	    this.eliminateNotMeanWord() ;
	}
	public String getFileAddress() {
		return fileAddress;
	}
	
	public void mapNotMeanWord() {
		HashMap<String,Integer> notMeanWordMap = new HashMap<String,Integer>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream("D://test1.txt"), "UTF-8");
			while (scanner.hasNextLine()){
				notMeanWordMap.put(scanner.nextLine(),new Integer(0));  
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally{
	    	scanner.close();
	    }	    
	}
	
	
	public void wrapText() {
	//	public void printEachForward(BreakIterator boundary, String source) {    
		 Locale thaiLocale = new Locale("th");
		// String input = textInFile;
		 BreakIterator boundary = BreakIterator.getWordInstance(thaiLocale);
		 boundary.setText(textInFile);

		 StringBuffer aftertext = new StringBuffer();
		 int start = boundary.first();
		 Boolean check = false;
		 for (int end = boundary.next();
		         end != BreakIterator.DONE;
		         start = end, end = boundary.next()) {
			 	
			 	if(textInFile.substring(start, end).equals(System.getProperty("line.separator"))){ 
			 		if(check==true){
			 			aftertext.append(textInFile.substring(start, end)+">");		
			 		 }		
			 		check=true;
			 	} 
			 	else {
			 		 check=false;
			 		 aftertext.append(textInFile.substring(start, end)+"|");
			 	}
		  }
		 //System.out.println(aftertext);
		 textWrapped = aftertext.toString();
		 textWrapped = textWrapped.replace ("?","");
	}
	public void wrapParagraph() {

		StringTokenizer str_tokenn = new StringTokenizer (textWrapped, ">" );
        textSpecPara = new String[str_tokenn.countTokens()] ;
        textSpecPara = textWrapped.split(">");
        //for (String tt : textSpecPara) {
        	//System.out.println(tt);  	
        //}
        countPara = textSpecPara.length;
        //System.out.println(countParagraph);
	}
	public void calParaFrequency() {
	
		for (int p = 0 ; p < countPara ; p++) {
			
			StringTokenizer str_token = new StringTokenizer (textSpecPara[p] , "|" );
			String[] x = new String[str_token.countTokens()] ;
			int j = 0;
			while ( str_token.hasMoreTokens() ){
				x[j] = str_token.nextToken().trim();
				j++;
			}
		
			t = new FrequencyText();
			for (String text : x) {
                t.chekSeqText(text);
			}

			//Set<Entry<String, Integer>> 
			e = t.getMapSeqText().entrySet();
			for (Entry<String, Integer> item : e) {
                System.out.println("key : " + item.getKey() + " | value : "
                                + item.getValue().toString());
			}
			
			System.out.println("----------------end of para"+p+"------------------");
		}
	}
	public void calFrequency() {

		StringTokenizer str_token = new StringTokenizer (textWrapped.replace (">",""), "|" );
        String[] x = new String[str_token.countTokens()] ;
        
		int j = 0;
		
		while ( str_token.hasMoreTokens() ){
				x[j] = str_token.nextToken().trim();
				j++;
		}
		
		t = new FrequencyText();
        for (String text : x) {
                t.chekSeqText(text);
        }

       //Set<Entry<String, Integer>> 
        e = t.getMapSeqText().entrySet();
        for (Entry<String, Integer> item : e) {
                System.out.println("key : " + item.getKey() + " | value : "
                                + item.getValue().toString());
        }
		
	}
	public void eliminateNotMeanWord() throws IOException {
		 ArrayList<String> notMeanWordList = new ArrayList<String>();
		 Scanner scanner = new Scanner(new FileInputStream("D://test1.txt"), "UTF-8");
		    try {
		      while (scanner.hasNextLine()){
		    	  notMeanWordList.add(scanner.nextLine());
		    	  
		      }
		    }
		    finally{
		      scanner.close();
		    }
		
		    
         Iterator<String> it = notMeanWordList.iterator();
         
        	 while(it.hasNext()){
        		 String iii = it.next();
        		 System.out.println(iii);
				if (t.getMapSeqText().containsKey(iii)) {
					 System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
					 System.out.println(">>>>>>>>>>>>>>>>>>>>>>" +iii);
				 	t.eliminateText(iii);
        		 }
    	     }
	         System.out.println("------------------------------------");
             
             for (Entry<String, Integer> item : e) {
                     System.out.println("key : " + item.getKey() + " | value : "
                                     + item.getValue().toString());
             }
    }
}

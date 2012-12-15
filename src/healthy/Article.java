package healthy;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class Article {
	private String fileAddress;
	private HashMap<String,Integer> notMeanWordMap;
	private ArrayList<HashMap<String,Integer>> paraList ;
	
	private HashMap<String, Integer> meanWordFreqMap;
	private int paraCount;
	private String textInFile;
	private HashMap<String,Float> meanWordWeightMap;
	
	public Article(String fileAddress) throws IOException {
		this.fileAddress = fileAddress; 
		
		ReadWriteTextFileWithEncoding test = new ReadWriteTextFileWithEncoding(fileAddress, "UTF-8");
	    textInFile = test.read();	    
	    
	    this.mapNotMeanWord();
	    this.calFrequencyMeanWord();
	    this.calParaFrequency();
	    this.calFactorWeight();
	    System.out.print(paraCount); 
	}
	public String getFileAddress() {
		return fileAddress;
	}
	/** Read file of stopWord list ,then record each stopWord(1line:1word) in the "notMeanWordMap" hashMap  */
	public void mapNotMeanWord() {
		notMeanWordMap = new HashMap<String,Integer>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream("D://test1.txt"), "UTF-8");
			while (scanner.hasNextLine()){
				//System.out.println(scanner.nextLine());
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
	
	/**count paragraph(1line:1paragraph:n) then new record found meanWord in "meanWordFreqMap" hashMap that is contained "paraList" arrayList
	 * (paraList record data of each paragraph)
	 * ("meanWordFreqMap" hashMap 1:1 paragraph)
	 * ("paraList"arrayList 1:n "meanWordFreqMap" hashMap|paragraph)
	 *  */
	public void calFrequencyMeanWord() {

		 Locale thaiLocale = new Locale("th");
		
		 BreakIterator boundary = BreakIterator.getWordInstance(thaiLocale);
		 boundary.setText(textInFile);
		
		 int start = boundary.first();
		 Boolean check = false;
		 
		 paraCount = 0;
		 paraList = new ArrayList<HashMap<String,Integer>>(); 
		 
		 meanWordFreqMap = new HashMap<String, Integer>();
		 paraList.add(meanWordFreqMap);	
		 paraCount++;
		 
		 for (int end = boundary.next();
		         end != BreakIterator.DONE;
		         start = end, end = boundary.next()) {
			 	
			 	if(textInFile.substring(start, end).equals(System.getProperty("line.separator"))){ 
			 		if(check==true){
			 			// Paragraph Segmentation ( wrap as 1 paragraph : create 1 meanWordFreqMap )
			 			 paraList.add(meanWordFreqMap);	
			 			 paraCount++;
			 			 
			 			 meanWordFreqMap = new HashMap<String, Integer>();
			 		 }		
			 	    check=true;
			 	} else {
			 			//count the frequency of found meanWord in each paragraph : record frequency value in "meanWordFreqMap" hashMap 
			 		  	check=false;
			 		  	String word = textInFile.substring(start, end);
			 		  	if (!word.equals(" ")) {
			 				Integer vCountText;
			 				//check : Is this word a member in stopWord class? -> y:don't interest  
			 				if (notMeanWordMap.containsKey(word) == false){
			 					//check :  Is this meanWord the key in "meanWordFreqMap" hashMap? -> y:its value add 1 | n:record this meanWord as a new member
			 					if (meanWordFreqMap.containsKey(word)) {
			 						vCountText = meanWordFreqMap.get(word);
			 						vCountText += 1;
			 						meanWordFreqMap.put(word, vCountText);
			 					} else {
			 						meanWordFreqMap.put(word, new Integer(1));
			 					}
			 				} //else { ;}
			 			//else { ;}
			 		  	}
			 	} 
		 } //end of for loop	 	 
	}
	
	
	public void calParaFrequency() {
		    ListIterator<HashMap<String,Integer>> it = paraList.listIterator();
		    int o = 1;
		    while (it.hasNext()) {
		    	int maxValue = 0;	
		    	meanWordFreqMap = it.next();
		    	
		    	Set<Entry<String, Integer>> s = meanWordFreqMap.entrySet();
		    	for (Map.Entry<String, Integer> item : s) {
		    		System.out.println("key : " + item.getKey() + " | value : "
                                + item.getValue().toString());
		    		
		    		if(maxValue < item.getValue().intValue()){
		    			maxValue = item.getValue().intValue();
		    		}
		    	}
		    meanWordFreqMap.put("maxVal", new Integer(maxValue));
		    System.out.println(maxValue);	
			System.out.println("----------------end of para "+ o++ +"------------------");
		    } 
	}
	public void calFactorWeight() {
		// term_Weight = each_index_of_meanWordFreqMap x log(paraCount/paraCountMatchWord)
	    ListIterator<HashMap<String,Integer>> its = paraList.listIterator();
	    int o = 1;
		
		float maxVal;
	    float value;    
        float standardFreq;

        double totalParagraph = Double.valueOf(paraCount);
        double matchParagraph = Double.valueOf(paraCount);
	    Double logValues = new Double(Math.log(totalParagraph/3.0));
	    //System.out.println(logValues);
	    float logValue = logValues.floatValue();
	    //System.out.println(logValue);
	    
	    while (its.hasNext()) {
	    	meanWordFreqMap = its.next();
	    	maxVal = meanWordFreqMap.get("maxVal").floatValue();
	    	System.out.println(maxVal);
	    	
	    	Set<Entry<String, Integer>> s = meanWordFreqMap.entrySet();
	    	for (Map.Entry<String, Integer> item : s) {
	    		
	    		value = item.getValue().floatValue();
	    		standardFreq = (value/maxVal)*logValue;
	    		//System.out.println(standardFreq);
	    		//standardFreq = standardFreq;
	    		System.out.println(item.getKey());
	    		System.out.println(standardFreq);
	    		//meanWordWeightMap.put(item.getKey(),new Float(standardFreq));
	    		}
	    		System.out.println("----------------end of para "+ o++ +"------------------");
	    	}
	    
	
	
	    } 
	}

	



	
	


package mypag;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class findWordMatch {

	/**
	 * @param args
	 */
	public findWordMatch(String )
	    String input = strout.toString();   
	    String r[] = input.split("\\p{Punct}");
	    
	    Pattern p = Pattern.compile("การ");
	    Matcher m;
	    int findCount = 0;
	    
	    for( int i = 0; i < r.length; i++ ) {
		    m = p.matcher(r[i]);
	    	System.out.println("["+i+"] >> "+ r[i]);
	    	
	        if(m.find()){
	           findCount++;
		       System.out.println("find" + i);
	         } 	
	    }
	       	System.out.println(" ");
	    
	  //StringTokenizer st = new StringTokenizer(strout, "|");

	System.out.println(r[3]);
	  

	    
	    //boolean b = m.matches();
	BufferedReader fi = new BufferedReader(new FileReader("th.txt"));
	String line;
	BreakIterator boundary =
	BreakIterator.getWordInstance(new Locale("th"));
	SortedMap words = new TreeMap();
	while ((line = fi.readLine()) != null) {
	boundary.setText(line);
	int start = boundary.first();
	int end = boundary.next();
	while (end != BreakIterator.DONE) {
	String word = line.substring(start, end);
	Object c = words.get(word);
	int cnt = c == null ? 1 : (((Integer) c).intValue() + 1);
	words.put(word, new Integer(cnt));
	start = end;
	end = boundary.next();
	}
	}
	fi.close();
	System.out.println(words);
}

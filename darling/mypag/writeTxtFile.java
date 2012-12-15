package mypag;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class writeTxtFile {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File myFile = new File("D:\\vv.txt");
		myFile.createNewFile();
		
		//FileReader fr = new FileReader(f);
					
		FileOutputStream fos = new FileOutputStream(myFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		PrintWriter pw = new PrintWriter(osw);
		
		pw.println("จากวัตถุประสงค์ของระบบที่ว่า ต้องการพัฒนาระบบเพื่อปรับปรุงการแสดงรายการผลลัพธ์จากการสืบค้นข้อมูล");
		pw.println("สำหรับการดูแลสุขภาพ โดยระบบทำการคัดเลือกย่อหน้าที่เป็นใจความสำคัญหนึ่งๆของแต่ละข้อมูลจริง");
		pw.println("จากนั้นระบบทำการจัดลำดับการแสดงผลของแต่ละย่อหน้าที่ผ่านการคัดเลือก"); 
		pw.close();
	}

}

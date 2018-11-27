package jy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.junit.Test;

public class NK {
	/**
	 * Neast-2018-1
	 */
	@Test
	public void C01(){
		try{
			System.out.println("input a number:");
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			sc.close();
			StringBuffer sb = new StringBuffer();
			while(n != 0){
				if(n % 2 == 1){
					sb.append("1");
					n = (n - 1) / 2;
				}
				else{
					sb.append("2");
					n = (n - 2) / 2;
				}
			}
			System.out.println("result:");
			String s = sb.reverse().toString();
			s = s.replaceAll("(.{1})", "$1->");
			System.out.println(s.substring(0, s.length() - 2));
		}
		catch(Exception e){
			System.out.println("输入数据不正确");
		}
	}

	/**
	 * 
	 */
	@Test
	public void C02() throws Exception{
		File file = new File("D:/test.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream os = new FileOutputStream(file);
		String str = "liuchaoooo is a sb";
		os.write(str.getBytes());
		os.close();
	}
}

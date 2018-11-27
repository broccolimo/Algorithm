package jy;

import org.junit.Test;

public class Testt {

	@Test
	public void C1(){
		try{
			System.out.println("try");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			System.out.println("finally");
		}
		System.out.println("main");
	}

}

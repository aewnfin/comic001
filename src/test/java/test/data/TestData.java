package test.data;

import org.junit.Test;

public class TestData {

	@Test
	public void showData() {
		try {
			String string="11 ";
			System.out.println("%"+string.trim()+"%");
			System.out.println("false");
		} catch (NullPointerException e) {
			
		}finally {
			System.out.println("success");
		}
		System.out.println("success");

	}
}

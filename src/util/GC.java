package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.service.MemberService;

//GC stands for Global Constant 
public class GC {
	public static final String URL = "jdbc:sqlserver://xase7q8rgb.database.windows.net:1433;database=iTV";
	public static final String USERNAME = "iTVSoCool@xase7q8rgb";
	public static final String PASSWORD = "iTVisgood911";

	public static final String DATASOURCE = "java:comp/env/jdbc/iTV";

	public static void main(String[] args) {
		//這裡應該只是讓一開始倒入的前四筆假資料有會員頭像
		MemberService service = new MemberService();
		File[] file = { new File("C:\\iTV\\img\\pikachu.jpeg"), new File("C:\\iTV\\img\\charmander.jpg"),
				new File("C:\\iTV\\img\\squirtle.jpg"), new File("C:\\iTV\\img\\bulbasaur.jpg") };
		try {
			for (int i = 0; i < file.length; i++) {
				FileInputStream fis = new FileInputStream(file[i]);
				int size = fis.available();
				byte[] photo = new byte[size];
				int counter = 0;
				while (fis.read(photo, 0, photo.length) != -1) {
					counter += 1;
					System.out.println(counter);
				}
				fis.read(photo);
				service.changePhoto(i + 1, photo);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

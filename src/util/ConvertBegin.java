package util;

import java.io.File;
import java.io.IOException;

public class ConvertBegin extends Thread { 
	String[] ff; 

	public void run() { 
		
		while(true) { 
		String folderpath = "/volume1/Tomcat/video/"; 
		// String path = null; 
		File f = new File (folderpath); 

			if (f.isDirectory()) { 
			ff = f.list(); 
			int i = 0; 
				System.out.println(ff[0]);
				
				while (i <ff.length) { 
				try {
					new ConvertVideo(folderpath + ff[i]).beginConvert();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				System.out.println(i);
				i++; 
				}
			 
				ff = null; 
			} 
			
			f = null;
	
			try { 
			Thread.sleep(10000); 
			} catch (InterruptedException e) { 
			// If that fails restart the thread 
			this.start(); 
			} 
			
		} 
	}
}



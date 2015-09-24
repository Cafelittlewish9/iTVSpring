package util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class ConvertVideo {
	private Date dt; 
	private long begintime; 
	private String PATH; 
	private String filerealname; // file name does not include extension 
	private String filename; // include the extension 
	private String videofolder = "C:\\Spring\\tomcat8\\webapps\\video\\"; // other format video directory 
	private String mp4folder = "C:\\Spring\\tomcat8\\webapps\\mp4\\"; // flv video directory 
	private String ffmpegpath = "C:\\Users\\Student\\Desktop\\ffmpeg\\bin\\ffmpeg.exe"; // ffmpeg.exe directory 
	private String mencoderpath = "C:\\convert\\ffmpeg\\mencoder"; // mencoder directory 
	private String mp4RealPath = "C:\\Spring\\tomcat8\\webapps\\mp4\\"; // Screenshots of video directory; 
	private String imageRealPath = "C:\\Spring\\tomcat8\\webapps\\img\\"; // screenshot of the store directory 
	
	public ConvertVideo() {} 
	public ConvertVideo(String path) { 
	PATH = path; 
	} 
	public String getPATH() { 
	return PATH; 
	}
	public void setPATH(String path) { 
	PATH = path; 
	} 
	
	public boolean beginConvert() throws IOException { 
		filerealname = PATH.substring (0, PATH.lastIndexOf("."));
		//Conver.OutShowLog.append("---- received documents ("+ PATH +") need to be converted -------------------------- ") ; 
		
//		if (!checkfile(PATH)) {
//		//Conver.OutShowLog.append(PATH + "file does not exist" + ""); 
//		return false; 
//		} 
		
//		dt = new Date(); 
//		begintime = dt.getTime(); 
		//Conver.OutShowLog.append("---- turning file ("+ PATH +")--------------------------"); 
		
		if (processImg()) { 
//		Date dt2 = new Date(); 
		//Conver.OutShowLog.append("conversion successful"); 
//		long endtime = dt2.getTime(); 
//		long timecha = (endtime - begintime); 
//		String totaltime = sumTime (timecha); 
		//Conver.OutShowLog.append("spent:" + totaltime + ""); 
		if (process()) { 
		//Conver.OutShowLog.append("Download successful"); 
		} else {
		//Conver.OutShowLog.append("Download failed a"); 
		}
		PATH = null; 
		return true; 
		} else { 
		PATH = null; 
		return false; 
		} 
	} 
	
	private boolean process() throws IOException { 
		int type = checkContentType(); 
		boolean status = false; 
		
		if (type == 0) {
		// Status = processFLV (PATH); // directly to the files to flv files 
//			System.out.println(type);
		status = processMp4(PATH); 
		} else if (type == 1) { 
		status = processMove(PATH);
		}
//		} else if (type == 2) { 
//		String avifilepath = processAVI(type);
//			System.out.println(type);
//		if (avifilepath == null) 
//		return false; 
//		// Avi files have not been 
//		else { 
//		//System.out.println("kaishizhuang"); 
//		status = processFLV(avifilepath); // the avi to flv 
//		} 
//		} 
		return status; 
	}
	
	private int checkContentType() { 
		String type = PATH.substring (PATH.lastIndexOf(".") + 1, PATH.length()).toLowerCase();
		 
		// FFmpeg can parse the format: (asx, asf, mpg, wmv, 3gp, mp4, mov, avi, flv, etc.) 
			   if (type.equals("avi")) { 
		return 0;
		} else if (type.equals("mkv")) { 
		return 1; 
		} else if (type.equals("mpg")) { 
		return 0; 
		} else if (type.equals("3gp")) { 
		return 1; 
		} else if (type.equals("mov")) { 
		return 1; 
		} else if (type.equals("asf")) { 
		return 1; 
		} else if (type.equals("asx")) { 
		return 1; 
		} else if (type.equals("flv")) { 
		return 1; 
		} else if (type.equals("mp4")) { 
		return 1; 
		}
		// On the ffmpeg can not resolve the file format (wmv9, rm, rmvb, etc.) 
		// Can start using other tools (mencoder) convert avi (ffmpeg can be resolved) format. 
		  else if (type.equals("wmv9")) { 
		return 2; 
		} else if (type.equals("rm")) { 
		return 2; 
		} else if (type.equals("rmvb")) { 
		return 2; 
		} 
		return 9; 
	}
	
	private boolean processMove(String path) throws IOException {
//			if (!checkfile(PATH)) { 
//			System.out.println (oldfilepath + "is not file"); 
//			return false; 
//			}
//			String cmd = "mv C:\\Java\\apache-tomcat-8\\webapps\\video\\\\" + path;
//			String cmd2 = " C:\\Java\\apache-tomcat-8\\webapps\\mp4";
//			Process p = Runtime.getRuntime().exec(cmd+cmd2);
		
			List commend = new java.util.ArrayList (); 
			commend.add (ffmpegpath); 
			commend.add ("-i"); 
			commend.add (videofolder + path);  
			commend.add("-vcodec");
			commend.add("copy");
			commend.add("-acodec");
			commend.add("libvo_aacenc");
			commend.add("-ac");
			commend.add("2");
			commend.add("-y");
			commend.add (mp4folder + filerealname + ".mp4"); 
			
			try { 
				ProcessBuilder builder = new ProcessBuilder (); 
				builder.command (commend); 
				System.out.println(commend);
				Process p = builder.start(); 
				doWaitFor(p); 
				p.destroy(); 
				deleteFile(videofolder + path); 
				return true; 
				} catch (Exception e) { 
				e.printStackTrace (); 
				return false; 
				}  
	}
	
	private boolean processMp4 (String oldfilepath) throws IOException {
//		if (!checkfile(PATH)) { 
//		System.out.println (oldfilepath + "is not file"); 
//		return false; 
//		}
		List commend = new java.util.ArrayList (); 
		commend.add (ffmpegpath); 
		commend.add ("-i"); 
		commend.add (videofolder + oldfilepath);  
		commend.add("-vcodec");
		commend.add("libx264");
		commend.add("-acodec");
		commend.add("libvo_aacenc");
		commend.add("-ac");
		commend.add("2");
		commend.add("-y");
		commend.add (mp4folder + filerealname + ".mp4"); 
		
//		String cmd = ffmpegpath + " -i " + videofolder + oldfilepath;
//		String cmd2 = " -vcodec libx264 -acodec libvo_aacenc -ac 2 " + mp4folder + filerealname + ".mp4";
//		Process p = Runtime.getRuntime().exec(cmd+cmd2);
		
		try {
		ProcessBuilder builder = new ProcessBuilder (); 
		String cmd = commend.toString(); 
		builder.command(commend);
		// Builder.redirectErrorStream (true); 
		Process p = builder.start(); 
		doWaitFor(p); 
		p.destroy(); 
		deleteFile(videofolder + oldfilepath); 
		return true; 
		} catch (Exception e) { 
		e.printStackTrace (); 
		return false; 
		} 
}
	
	private boolean checkfile(String path) {
		File file = new File(path); 
		if (!file.isFile()) { 
		return false; 
		} else { 
		return true; 
		} 
	}
	
	public void deleteFile(String filepath) { 
		File file = new File (filepath); 
		if ((videofolder+PATH).equals(filepath)) { 
		if (file.delete()) { 
		//System.out.println("File" + filepath + "deleted"); 
		} 
		} else { 
		if (file.delete()) { 
		//System.out.println("File" + filepath + "deleted"); 
		} 
		File filedelete2 = new File(videofolder+PATH); 
		if (filedelete2.delete()) { 
		//System.out.println("file" + PATH + "Deleted"); 
		} 
		} 
}
	
	public boolean processImg () throws IOException { 
		// System.out.println (newfilename + "->" + newimg); 
		
		
		List commend = new java.util.ArrayList (); 
		commend.add (ffmpegpath); 
		commend.add ("-i"); 
		commend.add (videofolder + PATH); 
		commend.add ("-y");
		commend.add ("-f");
		commend.add ("image2"); 
		commend.add ("-ss"); 
		commend.add ("35"); 
		commend.add ("-t"); 
		commend.add ("00:00:01"); 
		commend.add ("-s"); 
		commend.add ("570x320"); 
		commend.add (imageRealPath + filerealname + ".jpg");
		
//		String cmd = ffmpegpath + " -i " + mp4RealPath + filerealname +".mp4";
//		String cmd2 = " -y -f image2 -ss 15 -t 00:00:01 -s 240x135 " + imageRealPath + filerealname + ".jpg";
//		Process p = Runtime.getRuntime().exec(cmd+cmd2);
        
		try { 
			ProcessBuilder builder = new ProcessBuilder (); 
			builder.command (commend); 
			Process p = builder.start(); 
			doWaitFor(p);
			return true; 
			} catch (Exception e) { 
			e.printStackTrace (); 
			return false; 
			} 
	}
	
	public int doWaitFor (Process p) {
		InputStream in = null; 
		InputStream err = null; 
		int exitValue = -1; //returned to caller when p is finished 
		try { 
		//System.out.println("comeing"); 
		in = p.getInputStream(); 
		err = p.getErrorStream(); 
		boolean finished = false; //Set to true when p is finished
		while (!finished) { 
		try { 
		while (in.available()> 0) { 
		// Print the output of our system call 
		Character c = new Character((char) in.read()); 
		System.out.print(c); 
		}
		while (err.available()> 0) { 
		// Print the output of our system call 
		Character c = new Character((char) err.read()); 
		System.out.print(c); 
		}
		// Ask the process for its exitValue. If the process 
		// Is not finished, an IllegalThreadStateException 
		// Is thrown. If it is finished, we fall through and 
		// The variable finished is set to true. 
		exitValue = p.exitValue(); 
		finished = true;
		} catch (IllegalThreadStateException e) { 
		// Process is not finished yet; 
		// Sleep a little to save on CPU cycles 
		Thread.currentThread().sleep(5000); 
		} 
		} 
		} catch (Exception e) { 
		// Unexpected exception! Print it out for debugging ... 
		System.err.println("doWaitFor();: unexpected exception -" + e.getMessage()); 
		} finally { 
		try { 
		if (in != null) 
		{ 
		in.close(); 
		}
		} catch (IOException e) { 
		System.out.println(e.getMessage()); 
		}
		if (err != null) 
		{ 
		try { 
		err.close(); 
		} catch (IOException e) { 
		System.out.println(e.getMessage()); 
		} 
		} 
		} 
		// Return completion status to caller 
		return exitValue; 
	}
	
	public String sumTime(long ms) { 
		int ss = 1000; 
		long mi = ss * 60; 
		long hh = mi * 60; 
		long dd = hh * 24;
		long day = ms / dd; 
		long hour = (ms - day * dd) / hh; 
		long minute = (ms - day * dd - hour * hh) / mi; 
		long second = (ms - day * dd - hour * hh - minute * mi) / ss; 
		long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;
		String strDay = day <10? "0" + day + "days": "" + day + "days"; 
		String strHour = hour <10? "0" + hour + "hour": "" + hour + "hours"; 
		String strMinute = minute <10? "0" + minute + "minutes": "" + minute + "minutes"; 
		String strSecond = second <10? "0" + second + "seconds": "" + second + "seconds"; 
		String strMilliSecond = milliSecond <10? "0" + milliSecond: "" + milliSecond; 
		strMilliSecond = milliSecond <100? "0" + strMilliSecond + "ms": "" + strMilliSecond + "ms"; 
		return strDay + "" + strHour + ":" + strMinute + ":" + strSecond + "" + strMilliSecond;
	}
}

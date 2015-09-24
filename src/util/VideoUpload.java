package util;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FilenameUtils;


@WebServlet("/VideoUpload")
public class VideoUpload extends HttpServlet implements Servlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
//		 	String saveDirectory = this.getServletContext().getRealPath("/img");
			String saveDirectory = "C:\\Spring\\tomcat8\\webapps\\video";
		    System.out.println(saveDirectory);
		    // Check that we have a file upload request
		    boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		    System.out.println("isMultipart="+isMultipart+"<br>");
		    
		    
		    
		    // Create a new file upload handler
		    ServletFileUpload upload = new ServletFileUpload();

		    //Create a progress listener
		    ProgressListener progressListener = new ProgressListener(){
		       private long megaBytes = -1;
		       public void update(long pBytesRead, long pContentLength, int pItems) {
		           long mBytes = pBytesRead / 1000000;
		           if (megaBytes == mBytes) {
		               return;
		           }
		           megaBytes = mBytes;
		           System.out.println("We are currently reading item " + pItems);
		           if (pContentLength == -1) {
		               System.out.println("So far, " + pBytesRead + " bytes have been read.");
		           } else {
		               System.out.println("So far, " + pBytesRead + " of " + pContentLength
		                                  + " bytes have been read.");
		           }
		       }
		    };
		    upload.setProgressListener(progressListener);

		    // Parse the request
		    try {
				FileItemIterator iter = upload.getItemIterator(req);
				while (iter.hasNext()) {
				    FileItemStream item = iter.next();
				    String name = item.getFieldName();
				    InputStream stream = item.openStream();
				    if (item.isFormField()) {
				        String value = Streams.asString(stream);
				        System.out.println(name + "=" + value);
				    } else {
				        System.out.println("File field " + name + " with file name "
				            + item.getName() + " detected.");
				        // Process the input stream
				        String fieldName = item.getFieldName();
				        String fileName = item.getName();
				        req.setAttribute("theFile1",fileName.substring (0, fileName.lastIndexOf(".")));
				        String contentType = item.getContentType();
				        System.out.println("fieldName="+fieldName);
				        System.out.println("fileName="+fileName);
				        System.out.println("contentType="+contentType);
				        if (fileName != null && !"".equals(fileName)) {
				            fileName= FilenameUtils.getName(fileName);
				            System.out.println("fileName saved="+fileName);
				            File uploadedFile = new File(saveDirectory, fileName);
				            FileOutputStream uploadedFileStream = 
				                new FileOutputStream(uploadedFile);
				            Streams.copy(stream, uploadedFileStream, true);
				        }
				    }
				}
				resp.setContentType("text/html; charset=UTF-8");
//				req.getRequestDispatcher(
//						"/pages/Videoinfo.jsp").forward(req, resp);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
	}
}
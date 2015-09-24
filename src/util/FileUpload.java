package util;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
//原本想做一個可供影片、雲端檔案、會員照片上傳到資料庫的程式
//但sql指令會依叫用程式而有落差 ，還沒想到怎麼解決
//是否能將INSERT指令寫去GC檔，再寫判斷式將正確指令填入prepareStatement?!
public class FileUpload {
	private DataSource ds;

	public FileUpload() {
		try {
			Context ctx = new InitialContext();
			this.ds = (DataSource) ctx.lookup(GC.DATASOURCE);
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private static final String INSERT="INSERT INTO image VALUES(?)";
	
	public boolean saveFilename(String filename) {
		boolean result = false;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(INSERT);) {
			pstmt.setString(1, filename);
			pstmt.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean saveFile(FileItem item, String path) {
		boolean result = false;
		try {
			item.write(new File(path));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String SELECT_ALL="SELECT * FROM ";
	
	public ResultSet getAll() {
		ResultSet rs = null;
		try (Connection conn=ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL);) {
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}

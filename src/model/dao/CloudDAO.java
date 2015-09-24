package model.dao;

import java.util.List;
import model.vo.CloudVO;

public interface CloudDAO {

	public List<CloudVO> selectAll();

	public List<CloudVO> selectByMemberId(int memberId);

	public List<CloudVO> selectByFileName(int memberId, String fileName);

	public List<CloudVO> selectByTime(int memberId, java.util.Date fromTime, java.util.Date toTime);

	public List<CloudVO> selectByFileType(int memberId, String fileType);

	public List<CloudVO> selectByFileNameAndTime(int memberId, String fileName, java.util.Date fromTime,
			java.util.Date toTime);

	public List<CloudVO> selectByFileNameAndFileType(int memberId, String fileName, String fileType);

	public List<CloudVO> selectByFileTypeAndTime(int memberId, java.util.Date fromTime, java.util.Date toTime,
			String fileType);

	public List<CloudVO> selectByFileNameFileTypeAndTime(int memberId, String fileName, java.util.Date fromTime,
			java.util.Date toTime, String fileType);

	public int insert(CloudVO file);

	public int updateFile(CloudVO file);

	public int updateFileName(int fileId, String fileName, String filePath);

	public int delete(int fileId);

}
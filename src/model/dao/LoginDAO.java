package model.dao;

import java.util.List;

import model.vo.LoginVO;

public interface LoginDAO {

	public List<LoginVO> selectAll(String memberAccount);
	
	public List<LoginVO> selectAll();
	
	public LoginVO select(String memberAccount);

	public int insert(LoginVO bean);//下次push時要檢查

}
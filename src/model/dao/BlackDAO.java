package model.dao;

import java.util.List;

import model.vo.BlackVO;

public interface BlackDAO {

	public int markBlack(BlackVO bean);

	public List<BlackVO> getList(int memberId);

	public int removeBlack(BlackVO bean);

	public int removeAll(int memberId);

}
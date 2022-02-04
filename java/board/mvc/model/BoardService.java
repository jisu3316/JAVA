package board.mvc.model;

import java.util.ArrayList;

import mvc.domain.Board;

public class BoardService {
	//SingleTon Object Model =start
	private BoardDAO dao;
	
	private static final BoardService instance = new BoardService();
	private BoardService() {
		dao = new BoardDAO();
	}
	public static BoardService getInstance() {
		return instance;
	}
	//SingleTon Object Model -end
	public ArrayList<Board> listS(){
		return dao.list();
	}
	public ArrayList<Board> contentlistS(int seq){
		return dao.contentlist(seq);
	}
	
	public boolean insertS(Board dto) {
		return dao.insert(dto);
	}
	
	public boolean updateS(Board dto) {
		
		return dao.update(dto);
	}
	public void deleteS(int seq) {
		dao.delete(seq);
	}
	
}

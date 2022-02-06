package mission.mvc.model;

import java.util.ArrayList;

import mvc.domain.Memlist;

public class ListService {
	private ListDAO dao;
	private static final ListService instance = new ListService();
	private ListService() {
		dao = new ListDAO();
	}
	public static ListService getInstance() {
		return instance;
	}
	
	public ArrayList<Memlist> listS(){
		return dao.list();
	}
	public ArrayList<Memlist> listS(int index_no,int pageselect){
		return dao.list(index_no,pageselect);
	}
	public boolean updateS(Memlist dto) {
		return dao.update(dto);
	}
	public boolean insertS(Memlist dto) {
		return dao.insert(dto);
	}
	public boolean answerS(Memlist dto) {
		return dao.answer(dto);
	}
}

	

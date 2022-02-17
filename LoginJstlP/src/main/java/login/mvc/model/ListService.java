package login.mvc.model;

import java.util.ArrayList;

import mvc.domain.SubList;

public class ListService {
	private ListDAO dao;
	private static final ListService instance = new ListService();
	private ListService() {
		dao = new ListDAO();
	}
	public static ListService getInstance() {
		return instance;
	}
	
	public ArrayList<SubList> listS(){
		return dao.list();
	}
	
	public ArrayList<SubList> searchlistS(String id){
		return dao.searchlist(id);
	}
	public ArrayList<SubList> searchlistS2(String nickname){
		return dao.searchlist2(nickname);
	}
	public ArrayList<SubList> searchlistS3(String subject){
		return dao.searchlist3(subject);
	}
	
	public boolean insertS(SubList dto) {
		return dao.insert(dto);
	}
	
	public boolean updateS(SubList dto) {
		
		return dao.update(dto);
	}
	
	public void deleteS(int seq) {
		dao.delete(seq);
	}
	
}

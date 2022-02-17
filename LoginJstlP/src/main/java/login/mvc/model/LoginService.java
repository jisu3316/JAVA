package login.mvc.model;

import java.util.ArrayList;

import mvc.domain.Login;

public class LoginService {
	private LoginDAO dao;
	private static final LoginService instance = new LoginService();
	private LoginService() {
		dao = new LoginDAO();
	}
	public static LoginService getInstance() {
		return instance;
	}
	public boolean insertS(Login dto) {
		return dao.insert(dto);
	}
	public ArrayList<Login> listS(){
		return dao.list();
	}
	public ArrayList<Login> idS(String id){
		return dao.idlist(id);
	}
	public void deleteS(int seq) {
		dao.delete(seq);
	}
}

package mission.mvc.model;

import java.util.ArrayList;

import mvc.domain.Member;

public class LoginService {
	private LoginDAO dao;
	private static final LoginService instance = new LoginService();
	private LoginService() {
		dao = new LoginDAO();
	}
	public static LoginService getInstance() {
		return instance;
	}
	public ArrayList<Member> emailS(String email){
		return dao.emaillist(email);
	}
	/*public ArrayList<Mem> emailS(String email){
		 ArrayList<Mem> m = dao.emaillist(email);
		 m.setPwd("");
		 return m;
	}
	//�������ڵ� 
	public Member getMemberS(String email) {
      Member m = dao.getMember(email);
      m.setPwd("");
      
      return m;
   }
   
    public int check(String email, String pwd) {
       Member m = dao.getMember(email);
       if(m == null) {
          return NO_ID;//�׷� email�� ���� ȸ���� ����
       }else {
          String dbPwd = m.getPwd();
          if(dbPwd != null) dbPwd = dbPwd.trim();
          
          if(!dbPwd.equals(pwd)) {
             return NO_PWD; //email�� ���������� ����� �ٸ� ��� 
          }else {
             return YES_ID_PWD; //email�� pwd�� ���� 
          }
                
       }
    }*/

}

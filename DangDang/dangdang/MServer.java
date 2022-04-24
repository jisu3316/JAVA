package dangdang;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MServer extends Thread  {
	ServerSocket ss;
	Socket s;
	int port = 4000;
	OneClientModul ocm;
	Vector<OneClientModul> v = new Vector<OneClientModul>();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	ServerGUI svGUI;

	public MServer(){
		svGUI = new ServerGUI();
		svGUI.init();
		connect();
	}
	public void startThread() {
		start();
	}
	public void connect() {
		try{
			ss = new ServerSocket(port);
			pln(port+"번 포트에서 멀티서버 대기중...");
			svGUI.jta.append(port+"번 포트에서 멀티서버 대기중...\n");
			startThread();
			while(true){
				s = ss.accept();//
				if(v.size()==4) {
					s.close();
					continue;
				}
				ocm = new OneClientModul(MServer.this);
				v.add(ocm);
				ocm.start();
			}
			
		}catch(IOException ie){
			pln(port+"번 포트는 이미 사용중임");
		}finally{
			try{
				if(ss != null) ss.close();
			}catch(IOException ie){}
		}
	}
	public void run(){
		try{
			while(true){
				String msg = br.readLine();
				if(msg.equals("option on")){
					String omsg="";
					while(!omsg.equals("option off")){
						p("옵션을 선택해주세요(list / kick / whisper / option off): ");
						omsg=br.readLine();
						if (omsg.equals("list")){
							for (OneClientModul ocm:v){
								pln(ocm.chatId);
							}
						}
						if (omsg.equals("kick")){
							p("누구를 추방하시겠습니까?: ");
							String outId = br.readLine();
							for(Iterator<OneClientModul> iter = v.iterator(); iter.hasNext();){
								OneClientModul tmpocm = iter.next();
								if(tmpocm.chatId.equals(outId)){
									p("삭제전 : "+v.size());
									iter.remove();
									tmpocm.closeAll();
									p("삭제 후: "+v.size());
									p("iter.remove 실행됨 해당 outId:"+outId);
								}
							}
							/*
							for (OneClientModul ocm : v){
								v.removeIf(v->ocm.chatId.equals(outId));
							}*/
						}
						if(omsg.equals("whisper")){
							p("귓속말 대상: ");
							String nickname = br.readLine();
							for (OneClientModul ocm : v){
								if(ocm.chatId.equals(nickname)){
									try{
										String wmsg="";
										while(true){
											wmsg = br.readLine();
											if(wmsg.equals("woff")) break;
											ocm.dos.writeUTF("관리자>>"+ wmsg);
											ocm.dos.flush();
										}
									}catch(IOException ie){
										pln("whisper에서 예외발생");
									}finally{
										
									}
								}
							}
							
						}
						svGUI.jta.append(msg);
						msg="";
						
					}
				}
				//ocm.dos.writeUTF("관리자>> "+msg);
				//ocm.dos.flush();
				ocm.broadcast("관리자>> "+msg);//전체에 전달
			}
		}catch(IOException ie){
		}finally{
			ocm.closeAll();
		}
	}
	class ServerGUI extends JFrame{
		Container cp;
		JTextField chatField;
		JTextArea jta;
		JButton exit;
		JPanel pn = new JPanel();
		JScrollPane jsp;
		/*
		OneClientModul ocm;
		public ServerGUI(OneClientModul ocm) {
			this.ocm = ocm;
		}
		*/
		public void init() {
			cp = getContentPane();
			cp.add(pn);
			pn.setLayout(null);
			chatField = new JTextField(22);
			jta = new JTextArea();
			exit = new JButton("종료");
			chatField.setBounds(0, 175, 400, 25);
			exit.setBounds(0, 200, 400, 100);
			
			jta.setEditable(false);
			
			jsp = new JScrollPane(jta);
			jsp.setBounds(0, 0, 400, 200);
			
			pn.add(chatField);
			pn.add(jsp);
			pn.add(exit);
			jsp.setVisible(true);
			jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
			chatField.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				ocm.broadcast(chatField.getText());
				chatField.setText("");
			}});
			exit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(null,"OK를 누르시면 2초후 종료합니다." ,"종료", JOptionPane.INFORMATION_MESSAGE);
					
					try {
						Thread.sleep(2000);
						System.exit(0);
					}catch(InterruptedException ie) {
						
					}
					
			}});
			/*
			connect.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
			}});
			*/
			setUI();
			chatField.requestFocus();
		}
		public void setUI() {
			setTitle("Server");
			setSize(400, 300);
			setLocation(200, 100);
			setVisible(true);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) {
		MServer ms = new MServer();
	}
}


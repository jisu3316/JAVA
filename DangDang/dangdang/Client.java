package dangdang;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Client extends Thread{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Socket s;
	InputStream is;
	OutputStream os;
	FileReader fr;
	DataInputStream dis;
	DataOutputStream dos;
	public static String chatId, chatPort, chatIp;
	public static StringBuffer sb = new StringBuffer();
	String ip;
	int port;
	LogInUI liu;
	public static int score=0;
	//public boolean flag;
	boolean ctn;

	DangDang dd;
	public Client(DangDang dd) {
		this.dd = dd;
		liu = new LogInUI();
		liu.init();
	}
	public void connect() {
		try{
			s = new Socket(ip, port);
			liu.result.setText("서버와 연결 성공!!");
			is = s.getInputStream();
			os = s.getOutputStream();
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
			start();
		}catch(IOException ie) {
			System.out.println(ie.getMessage());
		}
	}
	
	public class LogInUI extends JFrame {
		Container cp;
		JTextField tfip, tfport, tfid, result;
		JLabel lip, lport, lid;
		JButton submit = new JButton("Log In");
		JPanel pn = new JPanel();
		
		void init() {
			cp = getContentPane();
			cp.add(pn);
			pn.setLayout(new GridLayout(4,1));
			tfip = new JTextField(22);
			tfport = new JTextField(22);
			tfid = new JTextField(22);
			lip = new JLabel("    IP: ");
			lport = new JLabel("    Port: ");
			lid = new JLabel("    ID: ");
			result = new JTextField(15);
			result.setEditable(false);
			tfip.setText("127.0.0.1");
			tfport.setText("4000");
		
			
			pn.add(lip);
			pn.add(tfip);
			pn.add(lport);
			pn.add(tfport);
			pn.add(lid);
			pn.add(tfid);
			pn.add(result);
			pn.add(submit);
			dd.jtf.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
				try{
					String msg = dd.jtf.getText();
					dos.writeUTF(" "+chatId+">> "+msg);
					dos.flush();
					//dd.jta.append(msg);
					dd.jtf.setText("");
				}catch(IOException ie){
				}
				}
			});
			
		
			tfid.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ip=tfip.getText();
					port=Integer.parseInt(tfport.getText());
					chatId=tfid.getText();
					if(!tfid.getText().equals(null)) {
						connect();
					}
				try{
					dos.writeUTF(chatId);
					dos.flush();
					result.setText("서버와 접속 성공");
					//dd.selectTrack(1);
					
					dd.startButton.setVisible(false);
					dd.leftButton.setVisible(true);
					dd.rightButton.setVisible(true);
					dd.background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
					dd.easyButton.setVisible(true);
					dd.hardButton.setVisible(true);
					dd.introMusic.close();
					dd.jta.setVisible(true);
					dd.jtf.setVisible(true);
					dd.readyButton.setVisible(true);
					dd.selectTrack(0);
					setVisible(false);
					dd.isMainScreen= true;
					dd.jsp.setVisible(true);
					
					
					
					return;
				}catch(UnknownHostException ue){
					dd.flag = false;
					result.setText("해당 서버를 찾지 못함");
					setVisible(true);
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null,"4명이 찼거나 잘못된 IP와 Port입니다." ,"접속실패", JOptionPane.INFORMATION_MESSAGE);
					dd.flag = false;
					//result.setText("서버와 접속 실패");
					//setVisible(true);
				}/*finally {
					try {
						if(flag == true) {
							setVisible(false);
						}
						//Thread.sleep(1500);
					}catch(InterruptedException iee){
						iee.getMessage();
					}
				}*/
			
			}});
			submit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ip=tfip.getText();
					port=Integer.parseInt(tfport.getText());
					chatId=tfid.getText();
					if(!tfid.getText().equals(null)) {
						connect();
					}
				try{
					dos.writeUTF(chatId);
					dos.flush();
					result.setText("서버와 접속 성공");
					dd.startButton.setVisible(false);
					dd.leftButton.setVisible(true);
					dd.rightButton.setVisible(true);
					dd.background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
					dd.easyButton.setVisible(true);
					dd.hardButton.setVisible(true);
					dd.readyButton.setVisible(true);
					dd.introMusic.close();
					dd.jta.setVisible(true);
					dd.jtf.setVisible(true);
					dd.selectTrack(0);
					setVisible(false);
					dd.isMainScreen= true;
					dd.jsp.setVisible(true);
					//dd.gameStart(0, "Easy");
					//dd.repaint();
					//dd.backMain();
					
					
					return;
				}catch(UnknownHostException ue){
					dd.flag = false;
					result.setText("해당 서버를 찾지 못함");
					setVisible(true);
				}catch(Exception ee){
					JOptionPane.showMessageDialog(null,"접속실패. IP와 Port를 확인해주세요" ,"예외발생", JOptionPane.INFORMATION_MESSAGE);
					dd.flag = false;
					//result.setText("서버와 접속 실패");
					//setVisible(true);
				}/*finally {
					try {
						if(flag == true) {
							setVisible(false);
						}
						//Thread.sleep(1500);
					}catch(InterruptedException iee){
						iee.getMessage();
					}
				}*/
			
			}});
			
			setUi();
			tfid.requestFocus();
		}
		

		public void setUi() {
			setTitle("UI2");
			setSize(230, 150);
			setLocation(200, 100);
			setVisible(true);
			setResizable(false);
			setLocationRelativeTo(null);
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}
	public void filter(String msg) { 
		//String temp = msg.substring(0, 7);
		if(msg.startsWith("//Start")){
			dd.gameStart(dd.nowSelected, dd.difficulty);
			//dd.repaint();
			ctn = true;
			 }
	}
		
	public void run(){ //listen ( socket -> monitor )
		try{
			while(true){
				String msg = dis.readUTF();
				filter(msg);
				if(ctn==true) {
					ctn = false;
					continue;
				}else {
				dd.jta.append(" "+msg+"\n");
				int pos = dd.jta.getText().length();
				dd.jta.setCaretPosition(pos);
				//dd.jtf.requestFocus();
				//pln(msg);
				}
			}
		}catch(IOException ie){
			JOptionPane.showMessageDialog(null,"4명이 다 찼습니다. OK를 누르시면 2초후 종료합니다." ,"접속실패", JOptionPane.INFORMATION_MESSAGE);
			try{
				Thread.sleep(2000);
				System.exit(0);
			}catch(InterruptedException iee){}
		}finally{
			//closeAll();
		}
	}
}

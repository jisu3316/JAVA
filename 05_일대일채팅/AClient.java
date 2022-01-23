import java.net.*;
import java.io.*;

class AClient extends Thread{
	int port=4000;
	ServerSocket ss;
	Socket s;
	String ip;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	InputStream is; //node 
	DataInputStream dis; //filter 
	OutputStream os; //node 
	DataOutputStream dos; //filter
	String msg;

	AClient(){
		try{
			System.out.print("ip를 입력해주세요:(기본:192.168.0.72)");
			ip=br.readLine();
			if(ip.length()==0) ip="192.168.0.72";
			s= new Socket(ip,port);
			System.out.println("서버("+ip+")와 접속 성공");

			os= s.getOutputStream();
			is = s.getInputStream();
			start();
			speak();		
			pln("1");
		}catch(IOException ie){
			pln("11:"+ie);
			
		}
	}
	public void run(){
		listen();
	}
	void listen(){
		pln("2");
		dis = new DataInputStream(is);
		try{
			while(true){
					String msg = dis.readUTF();
				pln("Server("+ip+")>> " + msg);
					
			}
		}catch(IOException ie){
			pln("22:"+ie);
			
		}
	}
	void speak(){
		pln("3");
		dos = new DataOutputStream(os);
		try{
			while(true){
				 msg= br.readLine();
					dos.writeUTF(msg);
					dos.flush();
			}
		}catch(IOException ie){
			pln("33:"+ie);
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) 
	{
		new AClient();
	}
}

import java.net.*;
import java.io.*;
class  AServer extends Thread{
	int port=4000;
	ServerSocket ss;
	Socket s;
	String ipClient;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	InputStream is; //node 
	DataInputStream dis; //filter 
	OutputStream os; //node 
	DataOutputStream dos; //filter
	AServer(){
		try{
		ss= new ServerSocket(port);
		pln(port+"번 포트에서 서버 대기중...");

		s= ss.accept();
		ipClient = s.getInetAddress().getHostAddress();
		is=s.getInputStream();
		os = s. getOutputStream();
		start();
		speak();
		}catch(IOException ie){
			pln("ie:"+ie);
		}finally{
			try{
				if(s != null) s.close();
				if(ss != null) s.close();
			}catch(IOException ie){}
		}	
	}

	@Override
		public void run(){
		listen();
	}
	void listen(){
		dis = new DataInputStream(is);
		try{
			while(true){
				
				String msg = dis.readUTF();
				pln("client("+ipClient+")>> " + msg);
			}
		}catch(IOException ie){
			pln("ie:"+ie);
		}
	}

	void speak(){
		dos = new DataOutputStream(os);
		try{
			while(true){
				String msg= br.readLine();
					dos.writeUTF(msg);
					dos.flush();
			}
		}catch(IOException ie){
			pln("ie:"+ie);
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
		new AServer();
	}
}

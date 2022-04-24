package dangdang;

import java.io.*;
import java.util.*;
import java.net.*;


//어떤 클라이언트로 부터 받은 메세지를.. 모든 클라이언트들에게 전송해주는 서버측 클래스 
public class OneClientModul extends Thread 
{
	MServer ms;
	Socket s;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	String chatId;
	public static int easyPlayer=0, hardPlayer=0, track0=0, track1=0, track2=0, track3=0, track4=0;
	boolean ctn;
	int iScore;
	public static int uCount = 0;
	int topScore;

	OneClientModul(MServer ms){
		this.ms = ms;
		this.s = ms.s;
		try{
			is = s.getInputStream();
			os = s.getOutputStream();
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
		}catch(IOException ie){}
	}
	public void run(){
		listen();
	}
	public void filter(String msg) { 
				if(msg.length()<7) {
					msg=msg.join(msg, "       ");
				}
			String temp = msg.substring(0, 7);
			if(temp.equals("//Easy ")){
				
				if(msg.substring(7).equals("0")){
					track0++;
					System.out.println("track0 :"+track0);
					broadcast(chatId+"님이 "+ "그건 아마 우리의 잘못은 아닐거야 ");
					broadcast("Easy모드를 선택하셨습니다.");
				}else if(msg.substring(7).equals("1")){
					track1++;
					System.out.println("track1 :"+track1);
					broadcast(chatId+"님이 "+ "회전목마 Easy모드를 선택하셨습니다.");
				}else if(msg.substring(7).equals("2")){
					track2++;
					System.out.println("track2 :"+track2);
					broadcast(chatId+"님이 "+ "비밀번호486 Easy모드를 선택하셨습니다.");
				}else if(msg.substring(7).equals("3")){
					track3++;
					System.out.println("track3 :"+track3);
					broadcast(chatId+"님이 "+ "신호등 Easy모드를 선택하셨습니다.");
				}else if(msg.substring(7).equals("4")){
					track4++;
					System.out.println("track4 :"+track4);
					broadcast(chatId+"님이 "+ "덤디덤디 Easy모드를 선택하셨습니다.");
				}
				 easyPlayer++;
				 //System.out.println("ㄹㄷ완료");
				 ctn = true;
				 //broadcast("easyPlayer: " + easyPlayer);
				 //broadcast("mv.v.size: " + ms.v.size());
				 if(easyPlayer == ms.v.size() && (track0 == ms.v.size() || track1 == ms.v.size() || track2 == ms.v.size() || track3 == ms.v.size() || track4 == ms.v.size())){ 
					 broadcast("[ 모든 참여자들이 준비되었습니다. ]");
					 broadcast("//ReadyAll");
					 broadcast("//자판을 영문으로 바꾸어 진행해주세요!");
					 
					
					 for(int i=3; i>0; i--){
						 try{
							broadcast("[ " + i + "초 후 게임을 시작합니다 .. ]");						 	
						 	Thread.sleep(1000);
						 }catch(InterruptedException ie){}
					 }					
					 broadcast("//Start"); 
					 easyPlayer = 0;
				 }
			}else if(temp.equals("//Hard ")){
				
				if(msg.substring(7).equals("0")){
					track0++;
					System.out.println("track0 :"+track0);
					broadcast(chatId+"님이 "+ "그건 아마 우리의 잘못은 아닐거야");
					broadcast("Hard모드를 선택하셨습니다.");
				}else if(msg.substring(7).equals("1")){
					track1++;
					System.out.println("track1 :"+track1);
					broadcast(chatId+"님이 "+ "회전목마 Hard모드를 선택하셨습니다.");
				}else if(msg.substring(7).equals("2")){
					track2++;
					System.out.println("track2 :"+track2);
					broadcast(chatId+"님이 "+ "비밀번호486 Hard모드를 선택하셨습니다.");
				}else if(msg.substring(7).equals("3")){
					track3++;
					System.out.println("track3 :"+track3);
					broadcast(chatId+"님이 "+ "신호등 Hard모드를 선택하셨습니다.");
				}else if(msg.substring(7).equals("4")){
					track4++;
					System.out.println("track4 :"+track4);
					broadcast(chatId+"님이 "+ "덤디덤디 Hard모드를 선택하셨습니다.");
				}
				 hardPlayer++;
				 //System.out.println("ㄹㄷ완료");
				 ctn = true;
				 //broadcast("hardPlayer: " + hardPlayer);
				// broadcast("mv.v.size: " + ms.v.size());
				 if(hardPlayer == ms.v.size() && (track0 == ms.v.size() || track1 == ms.v.size() || track2 == ms.v.size() || track3 == ms.v.size() || track4 == ms.v.size())){ 
					 broadcast("[ 모든 참여자들이 준비되었습니다. ]");
					 broadcast("//ReadyAll");
					
					 for(int i=3; i>0; i--){
						 try{
							broadcast("[ " + i + "초 후 게임을 시작합니다 .. ]");						 	
						 	Thread.sleep(1000);
						 }catch(InterruptedException ie){}
					 }					
					 broadcast("//Start"); 
					 hardPlayer = 0;
				 }
			}else if(temp.equals("//CEasy")){
				if(msg.substring(7).equals("0")){
					track0--;
					System.out.println("track0 :"+track0);
					broadcast(chatId+"님이 "+ "그건 아마 우리의 잘못은 아닐거야");
					broadcast("easy모드를 취소하셨습니다.");
				}else if(msg.substring(7).equals("1")){
					track1--;
					System.out.println("track1 :"+track1);
					broadcast(chatId+"님이 "+ "회전목마 easy모드를 취소하셨습니다.");
				}else if(msg.substring(7).equals("2")){
					track2--;
					System.out.println("track2 :"+track2);
					broadcast(chatId+"님이 "+ "비밀번호486 easy모드를 취소하셨습니다.");
				}else if(msg.substring(7).equals("3")){
					track3--;
					System.out.println("track3 :"+track3);
					broadcast(chatId+"님이 "+ "신호등 easyt모드를 취소하셨습니다.");
				}else if(msg.substring(7).equals("4")){
					track4--;
					System.out.println("track4 :"+track4);
					broadcast(chatId+"님이 "+ "덤디덤디 easy모드를 취소하셨습니다.");
				}
				 easyPlayer--;
				 //System.out.println("ㄹㄷ취소");
				 ctn = true;
				// broadcast("easyPlayer: " + easyPlayer);
				// broadcast("mv.v.size: " + ms.v.size());			
			}else if(temp.equals("//CHard")){
				
				if(msg.substring(7).equals("0")){
					track0--;
					System.out.println("track0 :"+track0);
					broadcast(chatId+"님이 "+ "그건 아마 우리의 잘못은 아닐거야");
					broadcast("Hard모드를 취소하셨습니다.");
				}else if(msg.substring(7).equals("1")){
					track1--;
					System.out.println("track1 :"+track1);
					broadcast(chatId+"님이 "+ "회전목마 Hard모드를 취소하셨습니다.");
				}else if(msg.substring(7).equals("2")){
					track2--;
					System.out.println("track2 :"+track2);
					broadcast(chatId+"님이 "+ "비밀번호486 Hard모드를 취소하셨습니다.");
				}else if(msg.substring(7).equals("3")){
					track3--;
					System.out.println("track3 :"+track3);
					broadcast(chatId+"님이 "+ "신호등 Hard모드를 취소하셨습니다.");
				}else if(msg.substring(7).equals("4")){
					track4--;
					System.out.println("track4 :"+track4);
					broadcast(chatId+"님이 "+ "덤디덤디 Hard모드를 취소하셨습니다.");
				}
				 hardPlayer--;
				 //System.out.println("ㄹㄷ취소");
				 ctn = true;
				// broadcast("hardPlayer: " + hardPlayer);
				 //broadcast("mv.v.size: " + ms.v.size());
			}else if(temp.equals("//Score")){
				//String temp = msg.substring(0, 7);
				int li = msg.length();
				String score = msg.substring(7,li);
				iScore = Integer.parseInt(score);
				ctn = true;
				ms.v.trimToSize();
				uCount++;
				broadcast(chatId+"님이 곡 선택 창으로 나오셨습니다.");
				broadcast(chatId+"님의 스코어: " + iScore);
				//broadcast("uCount: "+ uCount);
				if(uCount == ms.v.size()){
					for(OneClientModul ocm : ms.v){
						if(topScore <= ocm.iScore){
							topScore = ocm.iScore;
						}
					}
					for(OneClientModul ocm : ms.v){
						if(topScore == ocm.iScore){
							broadcast("==== 우승자<"+ocm.chatId+"> ====SCORE: "+topScore+"");
							uCount = 0;
							topScore = 0;
							track0 = 0;track1 = 0;track2 = 0;track3 = 0;track4 = 0;
						}
					}
				}
		}
	}
			/*
			else if(temp.equals("//Score")){ 
				String tempNick = msg.substring(7);
				//clientInfo.put(tempNick, clientInfo.get(tempNick) + 1);
				broadcast("//Showsc");
				ctn = true;
				
			}else if(temp.equals("//GmEnd")){ 
				broadcast("[ 게임이 종료되었습니다 ]");				
				broadcast(msg);
				readyPlayer = 0;
				ctn = true;
				//gameStart = false;
				
			}*/
	void listen(){ //우선 틀만 만들어 놓자
		String msg = "";
		try{
			chatId = dis.readUTF();
			broadcast(" "+chatId+"님 입장!!(인원: "+ms.v.size()+"/4명)"); //다른 클라이언트들에게 씀
			ms.pln(" "+chatId+"님 입장!!(인원: "+ms.v.size()+"/4명)"); //관리자에게 씀 
			while(true){
				msg = dis.readUTF();
				filter(msg);
				if (ctn == true)
				{
					ctn = false;
					continue;
				}
				broadcast(msg);
				
				ms.pln(msg);
			}
		}catch(IOException ie){
			ms.v.remove(this);
			
				broadcast(" "+chatId+"님 퇴장!!(인원: "+ms.v.size()+"/4명)"); 
				ms.pln(" "+chatId+"님 퇴장!!(인원: "+ms.v.size()+"/4명)"); 
				/*
			
			try {
				ms.s = ms.ss.accept();//
				}catch(IOException iee){}
				ms.ocm = new OneClientModul(ms);
				ms.v.add(ms.ocm);
				ms.ocm.start();
			*/
		}finally{
			closeAll();
		}
	}
	void checkoption(){
	
	}
	void broadcast(String msg){
		try{
		    for(OneClientModul ocm: ms.v){
				ocm.dos.writeUTF(msg);
				ocm.dos.flush();
			}
		}catch(IOException ie){
		}
		ms.svGUI.jta.append(msg+"\n");
		int pos = ms.svGUI.jta.getText().length();
		ms.svGUI.jta.setCaretPosition(pos);
	}
	void closeAll(){
		try{
			if(dis != null) dis.close();
			if(dos != null) dos.close();
			if(is != null) is.close();
			if(os != null) os.close();
			if(s != null) s.close();
		}catch(IOException ie){}
	}
	void removeSelf(){
		ms.v.remove(this);
		closeAll();
		//ms.v.trimToSize();
	}
}












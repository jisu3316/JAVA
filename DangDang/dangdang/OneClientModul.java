package dangdang;

import java.io.*;
import java.util.*;
import java.net.*;


//� Ŭ���̾�Ʈ�� ���� ���� �޼�����.. ��� Ŭ���̾�Ʈ�鿡�� �������ִ� ������ Ŭ���� 
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
					broadcast(chatId+"���� "+ "�װ� �Ƹ� �츮�� �߸��� �ƴҰž� ");
					broadcast("Easy��带 �����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("1")){
					track1++;
					System.out.println("track1 :"+track1);
					broadcast(chatId+"���� "+ "ȸ���� Easy��带 �����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("2")){
					track2++;
					System.out.println("track2 :"+track2);
					broadcast(chatId+"���� "+ "��й�ȣ486 Easy��带 �����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("3")){
					track3++;
					System.out.println("track3 :"+track3);
					broadcast(chatId+"���� "+ "��ȣ�� Easy��带 �����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("4")){
					track4++;
					System.out.println("track4 :"+track4);
					broadcast(chatId+"���� "+ "������� Easy��带 �����ϼ̽��ϴ�.");
				}
				 easyPlayer++;
				 //System.out.println("�����Ϸ�");
				 ctn = true;
				 //broadcast("easyPlayer: " + easyPlayer);
				 //broadcast("mv.v.size: " + ms.v.size());
				 if(easyPlayer == ms.v.size() && (track0 == ms.v.size() || track1 == ms.v.size() || track2 == ms.v.size() || track3 == ms.v.size() || track4 == ms.v.size())){ 
					 broadcast("[ ��� �����ڵ��� �غ�Ǿ����ϴ�. ]");
					 broadcast("//ReadyAll");
					 broadcast("//������ �������� �ٲپ� �������ּ���!");
					 
					
					 for(int i=3; i>0; i--){
						 try{
							broadcast("[ " + i + "�� �� ������ �����մϴ� .. ]");						 	
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
					broadcast(chatId+"���� "+ "�װ� �Ƹ� �츮�� �߸��� �ƴҰž�");
					broadcast("Hard��带 �����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("1")){
					track1++;
					System.out.println("track1 :"+track1);
					broadcast(chatId+"���� "+ "ȸ���� Hard��带 �����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("2")){
					track2++;
					System.out.println("track2 :"+track2);
					broadcast(chatId+"���� "+ "��й�ȣ486 Hard��带 �����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("3")){
					track3++;
					System.out.println("track3 :"+track3);
					broadcast(chatId+"���� "+ "��ȣ�� Hard��带 �����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("4")){
					track4++;
					System.out.println("track4 :"+track4);
					broadcast(chatId+"���� "+ "������� Hard��带 �����ϼ̽��ϴ�.");
				}
				 hardPlayer++;
				 //System.out.println("�����Ϸ�");
				 ctn = true;
				 //broadcast("hardPlayer: " + hardPlayer);
				// broadcast("mv.v.size: " + ms.v.size());
				 if(hardPlayer == ms.v.size() && (track0 == ms.v.size() || track1 == ms.v.size() || track2 == ms.v.size() || track3 == ms.v.size() || track4 == ms.v.size())){ 
					 broadcast("[ ��� �����ڵ��� �غ�Ǿ����ϴ�. ]");
					 broadcast("//ReadyAll");
					
					 for(int i=3; i>0; i--){
						 try{
							broadcast("[ " + i + "�� �� ������ �����մϴ� .. ]");						 	
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
					broadcast(chatId+"���� "+ "�װ� �Ƹ� �츮�� �߸��� �ƴҰž�");
					broadcast("easy��带 ����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("1")){
					track1--;
					System.out.println("track1 :"+track1);
					broadcast(chatId+"���� "+ "ȸ���� easy��带 ����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("2")){
					track2--;
					System.out.println("track2 :"+track2);
					broadcast(chatId+"���� "+ "��й�ȣ486 easy��带 ����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("3")){
					track3--;
					System.out.println("track3 :"+track3);
					broadcast(chatId+"���� "+ "��ȣ�� easyt��带 ����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("4")){
					track4--;
					System.out.println("track4 :"+track4);
					broadcast(chatId+"���� "+ "������� easy��带 ����ϼ̽��ϴ�.");
				}
				 easyPlayer--;
				 //System.out.println("�������");
				 ctn = true;
				// broadcast("easyPlayer: " + easyPlayer);
				// broadcast("mv.v.size: " + ms.v.size());			
			}else if(temp.equals("//CHard")){
				
				if(msg.substring(7).equals("0")){
					track0--;
					System.out.println("track0 :"+track0);
					broadcast(chatId+"���� "+ "�װ� �Ƹ� �츮�� �߸��� �ƴҰž�");
					broadcast("Hard��带 ����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("1")){
					track1--;
					System.out.println("track1 :"+track1);
					broadcast(chatId+"���� "+ "ȸ���� Hard��带 ����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("2")){
					track2--;
					System.out.println("track2 :"+track2);
					broadcast(chatId+"���� "+ "��й�ȣ486 Hard��带 ����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("3")){
					track3--;
					System.out.println("track3 :"+track3);
					broadcast(chatId+"���� "+ "��ȣ�� Hard��带 ����ϼ̽��ϴ�.");
				}else if(msg.substring(7).equals("4")){
					track4--;
					System.out.println("track4 :"+track4);
					broadcast(chatId+"���� "+ "������� Hard��带 ����ϼ̽��ϴ�.");
				}
				 hardPlayer--;
				 //System.out.println("�������");
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
				broadcast(chatId+"���� �� ���� â���� �����̽��ϴ�.");
				broadcast(chatId+"���� ���ھ�: " + iScore);
				//broadcast("uCount: "+ uCount);
				if(uCount == ms.v.size()){
					for(OneClientModul ocm : ms.v){
						if(topScore <= ocm.iScore){
							topScore = ocm.iScore;
						}
					}
					for(OneClientModul ocm : ms.v){
						if(topScore == ocm.iScore){
							broadcast("==== �����<"+ocm.chatId+"> ====SCORE: "+topScore+"");
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
				broadcast("[ ������ ����Ǿ����ϴ� ]");				
				broadcast(msg);
				readyPlayer = 0;
				ctn = true;
				//gameStart = false;
				
			}*/
	void listen(){ //�켱 Ʋ�� ����� ����
		String msg = "";
		try{
			chatId = dis.readUTF();
			broadcast(" "+chatId+"�� ����!!(�ο�: "+ms.v.size()+"/4��)"); //�ٸ� Ŭ���̾�Ʈ�鿡�� ��
			ms.pln(" "+chatId+"�� ����!!(�ο�: "+ms.v.size()+"/4��)"); //�����ڿ��� �� 
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
			
				broadcast(" "+chatId+"�� ����!!(�ο�: "+ms.v.size()+"/4��)"); 
				ms.pln(" "+chatId+"�� ����!!(�ο�: "+ms.v.size()+"/4��)"); 
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












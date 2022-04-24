package dangdang;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{
	
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRouteSpaceBar.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image flareImage;
	private Image judgeImage;

	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 353, 0,null);
		g.drawImage(noteRouteDImage, 457, 0,null);
		g.drawImage(noteRouteFImage, 561, 0,null);
		g.drawImage(noteRouteSpaceImage, 665, 0,null);
		g.drawImage(noteRouteJImage, 972, 0,null);
		g.drawImage(noteRouteKImage, 1076, 0,null);
		g.drawImage(noteRouteLImage, 1180, 0,null);
		g.drawImage(noteRouteLineImage, 453, 0,null);
		g.drawImage(noteRouteLineImage, 557, 0,null);
		g.drawImage(noteRouteLineImage, 661, 0,null);
		g.drawImage(noteRouteLineImage, 968, 0,null);
		g.drawImage(noteRouteLineImage, 1072, 0,null);
		g.drawImage(noteRouteLineImage, 1176, 0,null);
		g.drawImage(gameInfoImage, 353, 660, null);
		g.drawImage(judgementLineImage, 353, 580, null);
		for(int i = 0; i< noteList.size(); i++) {
			Note note = noteList.get(i);
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}else {
				note.screenDraw(g);
			}
		}

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Bold Italic", Font.BOLD, 25));
		g.drawString(titleName, 355, 685);
		g.drawString(difficulty, 1190, 685);
		
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 393 , 610 );
		g.drawString("D", 497 , 610 );
		g.drawString("F", 601 , 610 );
		g.drawString("Space", 780 , 610 );
		g.drawString("J", 1012 , 610 );
		g.drawString("K", 1116 , 610 );
		g.drawString("L", 1220 , 610 );
		
		g.setFont(new Font("Elephant", Font.BOLD, 25));
		Integer IScore =(Integer)Main.score;
		g.drawString(IScore.toString(),760, 685);
		g.drawImage(flareImage,390, 250,null);
		g.drawImage(judgeImage,590, 380,null);
		
		
		
	}
	public void judgeEvent(String judge) {
		if(judge.contentEquals("None")) {
			//flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
			//judgeImage = new ImageIcon(Main.class.getResource("../images/missImage.png")).getImage();
			//missImage넣어줘야함
		}
		if(judge.contentEquals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/missImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
			//missImage넣어줘야함
		}else if(judge.contentEquals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/lateImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
		}else if(judge.contentEquals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/goodImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
		}else if(judge.contentEquals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/greatImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
		}else if(judge.contentEquals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/perfectImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
		}else if(judge.contentEquals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/earlyImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
		}
	}
	public void pressS() {
		judge("S");
		noteRouteSImage =  new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressSpace() {
		judge("Space");
		noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRouteSpaceBarPressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseSpace() {
		noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRouteSpaceBar.png")).getImage();
	}
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	@Override
	public void run() {
		dropNotes();
		System.out.println("드랍노트끝");//점수를 보내는 메소드 
	}
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	public void judge(String input) {
		for (int i = 0 ; i< noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	public void dropNotes() {
		Beat[] beats = null;
		if(titleName.equals("그건아마우리의잘못은아닐거야") && difficulty.equals("Hard")) {
			int startTime = 4420 - Main.REACH_TIME*1000;//4,-480
			int gap = 96;
			beats = new Beat[] {
					new Beat(startTime +gap*9, "S")
					, new Beat(startTime +gap*22, "J")
					, new Beat(startTime +gap*22, "K")
					, new Beat(startTime +gap*28, "Space")
					, new Beat(startTime +gap*34, "J")
					, new Beat(startTime +gap*34, "K")
					, new Beat(startTime +gap*40, "F")
					, new Beat(startTime +gap*46, "F")				
					, new Beat(startTime +gap*50, "J")
					, new Beat(startTime +gap*54, "D")
					, new Beat(startTime +gap*54, "F")
					, new Beat(startTime +gap*54, "J")
					, new Beat(startTime +gap*54, "K")
					, new Beat(startTime +gap*54, "Space")
					, new Beat(startTime +gap*82, "J")
					, new Beat(startTime +gap*82, "L")
					, new Beat(startTime +gap*86, "S")
					, new Beat(startTime +gap*90, "D")
					, new Beat(startTime +gap*100, "S")
					, new Beat(startTime +gap*104, "D")
					, new Beat(startTime +gap*108, "F")
					, new Beat(startTime +gap*112, "J")
					, new Beat(startTime +gap*116, "K")
					, new Beat(startTime +gap*120, "L")
					, new Beat(startTime +gap*124, "Space")
					, new Beat(startTime +gap*128, "Space")
					, new Beat(startTime +gap*132, "J")
					, new Beat(startTime +gap*136, "S")
					, new Beat(startTime +gap*140, "Space")
					, new Beat(startTime +gap*146, "D")
					, new Beat(startTime +gap*152, "F")
					, new Beat(startTime +gap*158, "J")
					, new Beat(startTime +gap*164, "K")
					, new Beat(startTime +gap*170, "L")
					, new Beat(startTime +gap*176, "Space")
					, new Beat(startTime +gap*179, "Space")
					, new Beat(startTime +gap*182, "J")
					, new Beat(startTime +gap*188, "J")
					, new Beat(startTime +gap*194, "Space")
					, new Beat(startTime +gap*200, "D")
					, new Beat(startTime +gap*206, "F")
					, new Beat(startTime +gap*212, "J")
					, new Beat(startTime +gap*215, "K")
					, new Beat(startTime +gap*218, "L")
					, new Beat(startTime +gap*224, "S")
					, new Beat(startTime +gap*230, "K")
					, new Beat(startTime +gap*236, "D")
					, new Beat(startTime +gap*239, "J")
					, new Beat(startTime +gap*245, "F")
					, new Beat(startTime +gap*251, "F")
					, new Beat(startTime +gap*255, "S")
					, new Beat(startTime +gap*261, "D")
					, new Beat(startTime +gap*267, "F")
					, new Beat(startTime +gap*273, "J")
					, new Beat(startTime +gap*279, "K")
					, new Beat(startTime +gap*285, "L")
					, new Beat(startTime +gap*291, "Space")
					, new Beat(startTime +gap*294, "S")
					, new Beat(startTime +gap*294, "D")
					, new Beat(startTime +gap*300, "D")
					, new Beat(startTime +gap*300, "F")
					, new Beat(startTime +gap*306, "J")
					, new Beat(startTime +gap*306, "K")
					, new Beat(startTime +gap*309, "Space")
					, new Beat(startTime +gap*309, "F")
					, new Beat(startTime +gap*309, "J")
					, new Beat(startTime +gap*315, "Space")
					, new Beat(startTime +gap*315, "D")
					, new Beat(startTime +gap*315, "K")
					, new Beat(startTime +gap*321, "Space")
					, new Beat(startTime +gap*321, "S")
					, new Beat(startTime +gap*321, "L")
					, new Beat(startTime +gap*327, "J")
					, new Beat(startTime +gap*330, "F")
					, new Beat(startTime +gap*333, "L")
					, new Beat(startTime +gap*336, "S")
					, new Beat(startTime +gap*342, "J")
					, new Beat(startTime +gap*345, "F")
					, new Beat(startTime +gap*348, "L")
					, new Beat(startTime +gap*351, "S")
					, new Beat(startTime +gap*360, "J")
					, new Beat(startTime +gap*363, "F")
					, new Beat(startTime +gap*366, "L")
					, new Beat(startTime +gap*369, "S")
					, new Beat(startTime +gap*372, "K")
					, new Beat(startTime +gap*375, "D")
					, new Beat(startTime +gap*378, "J")
					, new Beat(startTime +gap*381, "F")
					, new Beat(startTime +gap*384, "L")
					, new Beat(startTime +gap*387, "S")
					, new Beat(startTime +gap*390, "K")
					, new Beat(startTime +gap*393, "D")
					, new Beat(startTime +gap*399, "S")
					, new Beat(startTime +gap*402, "D")
					, new Beat(startTime +gap*405, "F")
					, new Beat(startTime +gap*408, "L")
					, new Beat(startTime +gap*411, "K")
					, new Beat(startTime +gap*414, "J")
					, new Beat(startTime +gap*420, "S")
					, new Beat(startTime +gap*423, "D")
					, new Beat(startTime +gap*426, "F")
					, new Beat(startTime +gap*429, "L")
					, new Beat(startTime +gap*432, "K")
					, new Beat(startTime +gap*435, "J")
					, new Beat(startTime +gap*441, "S")
					, new Beat(startTime +gap*444, "D")
					, new Beat(startTime +gap*447, "F")
					, new Beat(startTime +gap*450, "L")
					, new Beat(startTime +gap*453, "K")
					, new Beat(startTime +gap*456, "J")
					, new Beat(startTime +gap*462, "Space")
					, new Beat(startTime +gap*468, "Space")
					, new Beat(startTime +gap*474, "D")
					, new Beat(startTime +gap*474, "F")
					, new Beat(startTime +gap*474, "J")
					, new Beat(startTime +gap*474, "K")
					, new Beat(startTime +gap*477, "S")
					, new Beat(startTime +gap*477, "F")
					, new Beat(startTime +gap*477, "J")
					, new Beat(startTime +gap*477, "L")
					, new Beat(startTime +gap*483, "Space")
					, new Beat(startTime +gap*489, "Space")
					, new Beat(startTime +gap*495, "Space")
					, new Beat(startTime +gap*501, "Space")
					, new Beat(startTime +gap*510, "L")
					, new Beat(startTime +gap*513, "S")
					, new Beat(startTime +gap*516, "K")
					, new Beat(startTime +gap*519, "D")
					, new Beat(startTime +gap*522, "J")
					, new Beat(startTime +gap*525, "F")		
					, new Beat(startTime +gap*528, "L")
					, new Beat(startTime +gap*531, "S")
					, new Beat(startTime +gap*534, "K")
					, new Beat(startTime +gap*537, "D")
					, new Beat(startTime +gap*541, "J")
					, new Beat(startTime +gap*544, "F")				
					, new Beat(startTime +gap*547, "L")
					, new Beat(startTime +gap*550, "S")
					, new Beat(startTime +gap*553, "K")
					, new Beat(startTime +gap*556, "D")
					, new Beat(startTime +gap*559, "J")
					, new Beat(startTime +gap*562, "F")
					, new Beat(startTime +gap*565, "L")
					, new Beat(startTime +gap*568, "S")
					, new Beat(startTime +gap*571, "K")
					, new Beat(startTime +gap*574, "D")
					, new Beat(startTime +gap*577, "J")
					, new Beat(startTime +gap*580, "F")
					, new Beat(startTime +gap*583, "L")
					, new Beat(startTime +gap*587, "S")
					, new Beat(startTime +gap*590, "K")
					, new Beat(startTime +gap*593, "D")
					, new Beat(startTime +gap*596, "J")
					, new Beat(startTime +gap*599, "F")
					, new Beat(startTime +gap*602, "L")
					, new Beat(startTime +gap*605, "S")
					, new Beat(startTime +gap*608, "K")
					, new Beat(startTime +gap*611, "D")
					, new Beat(startTime +gap*614, "J")
					, new Beat(startTime +gap*617, "F")
					, new Beat(startTime +gap*623, "Space")
					, new Beat(startTime +gap*629, "Space")
					, new Beat(startTime +gap*635, "Space")
			};
		}else if(titleName.equals("그건아마우리의잘못은아닐거야") && difficulty.equals("Easy")) {
			int startTime = 4420 - Main.REACH_TIME*1000;//4,-480
			int gap = 96;
			beats = new Beat[] {
					new Beat(startTime +gap*9, "D")
					,new Beat(startTime +gap*9, "S")
					, new Beat(startTime +gap*22, "J")
					, new Beat(startTime +gap*22, "K")
					, new Beat(startTime +gap*28, "Space")
					, new Beat(startTime +gap*34, "J")
					, new Beat(startTime +gap*34, "K")
					, new Beat(startTime +gap*40, "F")
					, new Beat(startTime +gap*46, "F")				
					, new Beat(startTime +gap*50, "J")
					, new Beat(startTime +gap*54, "D")
					, new Beat(startTime +gap*54, "F")
					, new Beat(startTime +gap*54, "J")
					, new Beat(startTime +gap*54, "K")
					, new Beat(startTime +gap*54, "Space")
					, new Beat(startTime +gap*82, "J")
					, new Beat(startTime +gap*82, "L")
					, new Beat(startTime +gap*86, "S")
					, new Beat(startTime +gap*90, "D")
					, new Beat(startTime +gap*100, "S")
					, new Beat(startTime +gap*104, "D")
					, new Beat(startTime +gap*108, "F")
					, new Beat(startTime +gap*112, "J")
					, new Beat(startTime +gap*116, "K")
					, new Beat(startTime +gap*120, "L")
					, new Beat(startTime +gap*124, "Space")
					, new Beat(startTime +gap*128, "Space")
					, new Beat(startTime +gap*132, "J")
					, new Beat(startTime +gap*136, "J")
					, new Beat(startTime +gap*140, "Space")
					
			};
		}else if(titleName.equals("회전목마")&& difficulty.equals("Hard")) {
			beats = new Beat[] {
					new Beat(1000, "S"),
					new Beat(2000, "D")
					, new Beat(1000, "F")
					, new Beat(5000, "Space")
					, new Beat(3000, "J")
					, new Beat(2500, "K")
					, new Beat(2000, "L")
			};
		}else if(titleName.equals("회전목마")&& difficulty.equals("Easy")) {
			beats = new Beat[] {
					new Beat(1000, "S"),
					new Beat(2000, "D")
					, new Beat(1000, "F")
					, new Beat(5000, "Space")
					, new Beat(3000, "J")
					, new Beat(2500, "K")
					, new Beat(2000, "L")
			};
		}else if(titleName.equals("비밀번호486")&& difficulty.equals("Hard")) {
			beats = new Beat[] {
					new Beat(1000, "S"),
					new Beat(2000, "D")
					, new Beat(1000, "F")
					, new Beat(5000, "Space")
					, new Beat(3000, "J")
					, new Beat(2500, "K")
					, new Beat(2000, "L")
			};
		}else if(titleName.equals("비밀번호486")&& difficulty.equals("Easy")) {
			beats = new Beat[] {
					new Beat(1000, "S"),
					new Beat(2000, "D")
					, new Beat(1000, "F")
					, new Beat(5000, "Space")
					, new Beat(3000, "J")
					, new Beat(2500, "K")
					, new Beat(2000, "L")
			};
		}else if(titleName.equals("신호등")&& difficulty.equals("Hard")) {
	         int startTime = 4000 - Main.REACH_TIME*1000;
	         int gap = 295;
	         beats = new Beat[] {
	               new Beat(startTime +gap*9, "S")
	               , new Beat(startTime +gap*10, "D")
	               , new Beat(startTime +gap*11, "F")
	               , new Beat(startTime +gap*15, "J")
	               , new Beat(startTime +gap*16, "K")
	               , new Beat(startTime +gap*17, "L")
	               , new Beat(startTime +gap*20, "D")
	               , new Beat(startTime +gap*22, "J")
	               , new Beat(startTime +gap*24, "S")
	               , new Beat(startTime +gap*26, "J")
	               , new Beat(startTime +gap*28, "F")
	               , new Beat(startTime +gap*30, "L")
	               , new Beat(startTime +gap*33, "Space")
	               , new Beat(startTime +gap*33, "F")
	               , new Beat(startTime +gap*33, "J")
	               , new Beat(startTime +gap*36, "D")
	               , new Beat(startTime +gap*38, "F")
	               , new Beat(startTime +gap*40, "F")
	               , new Beat(startTime +gap*49, "S")
	               , new Beat(startTime +gap*52, "K")
	               , new Beat(startTime +gap*54, "D")
	               , new Beat(startTime +gap*54, "L")
	               , new Beat(startTime +gap*56, "L")
	               , new Beat(startTime +gap*57, "F")
	               , new Beat(startTime +gap*59, "J")
	               , new Beat(startTime +gap*61, "J")
	               , new Beat(startTime +gap*63, "F")
	               , new Beat(startTime +gap*63, "J")
	               , new Beat(startTime +gap*63, "Space")
	               , new Beat(startTime +gap*70, "K")
	               , new Beat(startTime +gap*72, "L")
	               , new Beat(startTime +gap*73, "Space")
	               , new Beat(startTime +gap*77, "L")
	               , new Beat(startTime +gap*79, "J")
	               , new Beat(startTime +gap*79, "K")
	               , new Beat(startTime +gap*83, "L")
	               , new Beat(startTime +gap*85, "Space")
	               , new Beat(startTime +gap*85, "S")
	               , new Beat(startTime +gap*90, "D")
	               , new Beat(startTime +gap*92, "J")
	               , new Beat(startTime +gap*94, "S")
	               , new Beat(startTime +gap*96, "L")
	               , new Beat(startTime +gap*98, "S")
	               , new Beat(startTime +gap*98, "F")
	               , new Beat(startTime +gap*98, "K")
	               , new Beat(startTime +gap*102, "Space")
	               , new Beat(startTime +gap*102, "D")
	               , new Beat(startTime +gap*106, "J")
	               , new Beat(startTime +gap*108, "K")
	               , new Beat(startTime +gap*110, "J")
	               , new Beat(startTime +gap*112, "S")
	               , new Beat(startTime +gap*118, "F")
	               , new Beat(startTime +gap*120, "F")
	               , new Beat(startTime +gap*120, "Space")
	               , new Beat(startTime +gap*125, "S")
	               , new Beat(startTime +gap*127, "J")
	               , new Beat(startTime +gap*128, "S")
	               , new Beat(startTime +gap*129, "K")
	               , new Beat(startTime +gap*130, "S")
	               , new Beat(startTime +gap*131, "L")
	               , new Beat(startTime +gap*132, "F")
	               , new Beat(startTime +gap*134, "Space")   
	               , new Beat(startTime +gap*135, "Space")   
	               , new Beat(startTime +gap*136, "Space")
	               , new Beat(startTime +gap*137, "Space")
	               , new Beat(startTime +gap*139, "J")
	               , new Beat(startTime +gap*140, "K")
	               , new Beat(startTime +gap*141, "L")
	               , new Beat(startTime +gap*142, "F")
	               , new Beat(startTime +gap*144, "F")
	               , new Beat(startTime +gap*145, "D")
	               , new Beat(startTime +gap*147, "J")
	               , new Beat(startTime +gap*149, "J")
	               , new Beat(startTime +gap*151, "S")
	               , new Beat(startTime +gap*152, "L")
	               , new Beat(startTime +gap*154, "Space")
	               , new Beat(startTime +gap*156, "S")
	               , new Beat(startTime +gap*158, "D")
	               , new Beat(startTime +gap*160, "S")
	               , new Beat(startTime +gap*162, "D")
	               , new Beat(startTime +gap*163, "Space")
	               , new Beat(startTime +gap*165, "L")
	               , new Beat(startTime +gap*167, "L")
	               , new Beat(startTime +gap*169, "K")
	               , new Beat(startTime +gap*171, "J")
	               , new Beat(startTime +gap*173, "S")
	               , new Beat(startTime +gap*173, "L")
	               , new Beat(startTime +gap*175, "J")
	               , new Beat(startTime +gap*175, "D")
	               , new Beat(startTime +gap*177, "K")
	               , new Beat(startTime +gap*177, "Space")
	               , new Beat(startTime +gap*179, "F")
	               , new Beat(startTime +gap*181, "D")
	               , new Beat(startTime +gap*183, "S")
	               , new Beat(startTime +gap*185, "L")
	               , new Beat(startTime +gap*187, "K")
	               , new Beat(startTime +gap*190, "J")
	               , new Beat(startTime +gap*191, "L")
	               , new Beat(startTime +gap*192, "L")
	               , new Beat(startTime +gap*194, "S")
	               , new Beat(startTime +gap*197, "F")
	               , new Beat(startTime +gap*197, "Space")
	               , new Beat(startTime +gap*197, "J")
	         };
	      }else if(titleName.equals("신호등")&& difficulty.equals("Easy")) {
	         int startTime = 4000 - Main.REACH_TIME*1000;
	         int gap = 350;
	         beats = new Beat[] {
	               new Beat(startTime +gap*9, "S")
	               , new Beat(startTime +gap*11, "F")
	               , new Beat(startTime +gap*15, "J") 
	               , new Beat(startTime +gap*17, "L")
	               , new Beat(startTime +gap*20, "D")
	               , new Beat(startTime +gap*22, "J")
	               , new Beat(startTime +gap*24, "F")
	               , new Beat(startTime +gap*26, "L")
	               , new Beat(startTime +gap*26, "Space")
	               , new Beat(startTime +gap*33, "F")
	               , new Beat(startTime +gap*35, "J")
	               , new Beat(startTime +gap*39, "S")
	               , new Beat(startTime +gap*41, "L")
	               , new Beat(startTime +gap*45, "Space")
	               , new Beat(startTime +gap*47, "K")
	               , new Beat(startTime +gap*49, "S")
	               , new Beat(startTime +gap*51, "D")
	               , new Beat(startTime +gap*51, "Space")
	               , new Beat(startTime +gap*55, "J")
	               , new Beat(startTime +gap*57, "F")
	               , new Beat(startTime +gap*59, "J")
	               , new Beat(startTime +gap*63, "Space")
	               , new Beat(startTime +gap*65, "K")
	               , new Beat(startTime +gap*67, "L")
	               , new Beat(startTime +gap*70, "L")
	               , new Beat(startTime +gap*72, "J")
	               , new Beat(startTime +gap*74, "K")
	               , new Beat(startTime +gap*76, "L")
	               , new Beat(startTime +gap*80, "Space")
	               , new Beat(startTime +gap*82, "S")
	               , new Beat(startTime +gap*84, "D")
	               , new Beat(startTime +gap*86, "J")
	               , new Beat(startTime +gap*88, "S")
	               , new Beat(startTime +gap*90, "L")
	               , new Beat(startTime +gap*95, "F")
	               , new Beat(startTime +gap*97, "K")
	               , new Beat(startTime +gap*99, "Space")
	               , new Beat(startTime +gap*101, "D")
	               , new Beat(startTime +gap*105, "J")
	               , new Beat(startTime +gap*117, "J")
	               , new Beat(startTime +gap*119, "S")
	               , new Beat(startTime +gap*121, "F")
	               , new Beat(startTime +gap*124, "Space")
	               , new Beat(startTime +gap*126, "S")
	               , new Beat(startTime +gap*130, "S")
	               , new Beat(startTime +gap*132, "K")
	               , new Beat(startTime +gap*135, "L")
	               , new Beat(startTime +gap*137, "F")
	               , new Beat(startTime +gap*139, "Space")   
	               , new Beat(startTime +gap*143, "Space")
	               , new Beat(startTime +gap*145, "J")
	               , new Beat(startTime +gap*147, "K")
	               , new Beat(startTime +gap*150, "L")
	               , new Beat(startTime +gap*152, "F")
	               , new Beat(startTime +gap*155, "S")
	               , new Beat(startTime +gap*157, "D")
	               , new Beat(startTime +gap*158, "S")
	               , new Beat(startTime +gap*159, "Space")
	               , new Beat(startTime +gap*163, "J")
	               , new Beat(startTime +gap*165, "F")
	               , new Beat(startTime +gap*169, "J")
	               , new Beat(startTime +gap*173, "F")
	               , new Beat(startTime +gap*173, "Space")
	         };
	     }else if(titleName.equals("덤디덤디")&& difficulty.equals("Hard")) {
			beats = new Beat[] {
					new Beat(1000, "S"),
					new Beat(2000, "D")
					, new Beat(1000, "F")
					, new Beat(5000, "Space")
					, new Beat(3000, "J")
					, new Beat(2500, "K")
					, new Beat(2000, "L")
			};
		}else if(titleName.equals("덤디덤디")&& difficulty.equals("Easy")) {
			beats = new Beat[] {
					new Beat(1000, "S"),
					new Beat(2000, "D")
					, new Beat(1000, "F")
					, new Beat(5000, "Space")
					, new Beat(3000, "J")
					, new Beat(2500, "K")
					, new Beat(2000, "L")
			};
		}
			
		/*
		new Beat(1000, "S"),
		new Beat(2000, "D")
		, new Beat(1000, "F")
		, new Beat(5000, "Space")
		, new Beat(3000, "J")
		, new Beat(2500, "K")
		, new Beat(2000, "L")
		*/
		
		int i = 0;
		gameMusic.start();
		while(i< beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note= new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

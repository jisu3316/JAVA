package dangdang;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED)*Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true; //진행여부
	
	public String getNoteType() {
		return noteType;
	}
	public boolean isProceeded() {
		return proceeded;
	}
	public void close() {
		proceeded = false;
	}
	public Note(String noteType) {
		if(noteType.contentEquals("S")) {
			x = 353;
		}
		else if(noteType.contentEquals("D")) {
			x = 457;
		}else if(noteType.contentEquals("F")) {
			x = 561;
		}else if(noteType.contentEquals("Space")) {
			x = 780;
		}else if(noteType.contentEquals("J")) {
			x = 972;
		}else if(noteType.contentEquals("K")) {
			x = 1076;
		}else if(noteType.contentEquals("L")) {
			x = 1180;
		}
		this.noteType = noteType;
	}

	public void screenDraw(Graphics2D g) {
		if(!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y ,null);
		}
		else {
			g.drawImage(noteBasicImage, x, y, null); // 바베큐사진
			//g.drawImage(noteBasicImage, x+203, y, null);
			
		}
	}
	public void drop() {
		y += Main.NOTE_SPEED;//5
		if(y > 620) {
			System.out.println("Miss");
			close();
		}
	}
	public String judge() {
		if(y>620){
			System.out.println("Miss");
			close();
			return "Miss";
		}else if(y >= 613 ) {
			System.out.println("Late");
			close();
			Main.score+=1000;
			Integer IScore =(Integer)Main.score;
			IScore.toString();
			//g.drawString(IScore.toString(),760, 685);
			return "Late";
		}else if(y >= 600 ) {
			System.out.println("Good");
			close();
			Main.score+=3000;
			Integer IScore =(Integer)Main.score;
			IScore.toString();
			return "Good";
		}else if(y >= 587 ) {
			System.out.println("Great");
			close();
			Main.score+=6000;
			return "Great";
		}else if(y >= 573 ) {
			System.out.println("Perfect");
			close();
			Main.score+=15000;
			return "Perfect";
		}else if(y >= 565 ) {
			System.out.println("Great");
			close();
			Main.score+=6000;
			return "Great";
		}else if(y >= 550 ) {
			System.out.println("Good");
			close();
			Main.score+=3000;
			return "Good";
		}else if(y >= 535 ) {
			System.out.println("Early");
			close();
			Main.score+=1000;
			return "Early";
		}else if(y<535) {
			System.out.println("Miss");
			close();
			if(Main.score>3000) {
				Main.score-=3000;
			}
			return "Miss";
		}
		
		return "None";
	}
 	
	
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);//10
					
				}else {
					interrupt();
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

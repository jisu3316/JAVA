package dangdang;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class DangDang extends JFrame{
	
	public Image screenImage;
	public Graphics screenGraphic;
	
	public ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	public ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	public ImageIcon startButtonImage = new ImageIcon(Main.class.getResource("../images/startButton.png"));
	public ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	public Image background = new ImageIcon(Main.class.getResource("../images/introBackground.png")).getImage();
	public Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	public Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	public Image noteRouteImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	public Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	public Image noteRoutePressedImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
	public Image noteRouteSpaceBarImage = new ImageIcon(Main.class.getResource("../images/noteRouteSpaceBar.png")).getImage();
	public Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	
	
	public ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	public ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	public ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	public ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	public ImageIcon readyImage = new ImageIcon(Main.class.getResource("../images/readyButton.png"));
	public ImageIcon readyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/readyButtonEntered.png"));
	
	public ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButton.png"));
	public ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	public ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButton.png"));
	public ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	//public Image titleImage2 = new ImageIcon(Main.class.getResource("../images/titleImage2.png")).getImage();
	//public Image titleImage3 = new ImageIcon(Main.class.getResource("../images/titleImage3.png")).getImage();
	public ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButton.png"));
	public ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	
	public JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.PNG")));
	public JLabel readyButtonEnteredLabel = new JLabel(new ImageIcon(Main.class.getResource("../images/readyButtonEntered.png")));
	
	public JButton exitButton = new JButton(exitButtonBasicImage);
	public JButton leftButton = new JButton(leftButtonBasicImage);
	public JButton rightButton = new JButton(rightButtonBasicImage);
	public JButton startButton = new JButton(startButtonImage);
	public JButton easyButton = new JButton(easyButtonBasicImage);
	public JButton hardButton = new JButton(hardButtonBasicImage);
	public JButton backButton = new JButton(backButtonBasicImage);
	public JButton readyButton  = new JButton(readyImage);
	
	public int mouseX, mouseY;
	
	public Client c;
	public boolean flag=false;
	public boolean isMainScreen = false;
	public boolean isGameScreen = false;
	
	public ArrayList<Track> trackList = new ArrayList<Track>();
	public Image titleImage;
	public Image selectedImage= new ImageIcon(Main.class.getResource("../images/albumImage2.png")).getImage();
	public Music selectedMusic;
	public int nowSelected = 0;
	public String difficulty;
	public Music introMusic = new Music("introMusic.mp3", true);

	
	public static Game game = null;
	
	public Container cp;
	public JTextField jtf;
	public JTextArea jta;
	public JPanel jp;
	public JScrollPane jsp;
	
	
	Client c1;
	public boolean ready;
	public boolean easy,hard;
	
	public DangDang() {
		
		trackList.add(new Track("titleImage1.png", "albumImage1.png", "mainBackImage1.png", "그건아마우리의잘못은아닐거야selected.mp3", "그건아마우리의잘못은아닐거야.MP3","그건아마우리의잘못은아닐거야"));
		trackList.add(new Track("titleImage2.png","albumImage2.png","mainBackImage2.png","회전목마selected.mp3","회전목마.mp3","회전목마"));
		trackList.add(new Track("titleImage3.png","albumImage3.png","mainBackImage3.png","비밀번호486selected.mp3","비밀번호486.mp3","비밀번호486"));
		trackList.add(new Track("titleImage4.png","albumImage4.png","mainBackImage4.png","신호등selected.mp3","신호등.mp3","신호등"));
		trackList.add(new Track("titleImage5.png","albumImage5.png","mainBackImage5.png","덤디덤디selected.mp3","덤디덤디.mp3","덤디덤디"));
		
		cp = getContentPane();
		cp.setLayout(null);
		jta = new JTextArea();
		jta.setEditable(false);
		jta.setBounds(0, 350, 340,300);
		Color bg = new Color(0,0,0,0);
		Color bg2 = new Color(0,0,0,20);
		jta.setBackground(bg);
		jsp=new JScrollPane(jta);
		jsp.setBounds(0, 350, 340,300);
		jsp.setBackground(bg);
		jsp.setBorder(BorderFactory.createEmptyBorder());
		//cp.add(jta);
		cp.add(jsp);
		//jta.setVisible(true); //얘 없었어도 잘만됨
		jsp.setVisible(false);
		
		jtf = new JTextField("") {
		@Override
		public void setBorder(Border border) {
				
			}
		};
		jtf.setBounds(2, 650, 351, 40);
		jtf.setBackground(bg2);
		cp.add(jtf);
		jta.setVisible(false);
		jtf.setVisible(false);
		
		setUndecorated(true);
		setTitle("Rhythm DangDang");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		addKeyListener(new KeyListener());
		
		
		introMusic.start();
		
		readyButton.setVisible(false);
		
		menuBar.setBounds(600, 0, 792, 120);//488
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX()+600;
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		readyButtonEnteredLabel.setBounds(-50, 20, 400, 100);
		readyButton.setBounds(-50, 20, 400, 100);
		readyButtonEnteredLabel.setVisible(false);
		readyButton.setBorderPainted(false);
		readyButton.setContentAreaFilled(false);
		readyButton.setFocusPainted(false);
		readyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				readyButton.setIcon(readyButtonEnteredImage);
				readyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(ready==true) {
					readyButton.setIcon(readyButtonEnteredImage);
					readyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}else {
				readyButton.setIcon(readyImage);
				readyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
				//ready = true;
				//readyButton.setVisible(false);
				//readyButtonEnteredLabel.setVisible(true);
				try{
					if(ready == false) {
						if(easy == true) {
							c1.dos.writeUTF("//Easy "+nowSelected);
							c1.dos.flush();
							jta.append("준비완료\n");
							hardButton.setEnabled(false);
							leftButton.setEnabled(false);
							rightButton.setEnabled(false);
							ready = true;
						}
						if(hard == true ) {
							c1.dos.writeUTF("//Hard "+nowSelected);
							c1.dos.flush();
							jta.append("준비완료\n");
							easyButton.setEnabled(false);
							leftButton.setEnabled(false);
							rightButton.setEnabled(false);
							ready = true;
						}
					}else if (ready = true) {
						if(easy == true) {
							c1.dos.writeUTF("//CEasy"+nowSelected);
							c1.dos.flush();
							jta.append("준비취소\n");
							hardButton.setEnabled(true);
							leftButton.setEnabled(true);
							rightButton.setEnabled(true);
							ready = false;
						}
						if(hard == true ) {
							c1.dos.writeUTF("//CHard"+nowSelected);
							c1.dos.flush();
							jta.append("준비취소\n");
							easyButton.setEnabled(true);
							leftButton.setEnabled(true);
							rightButton.setEnabled(true);
							ready = false;
						}
					}
					return;
				}catch(UnknownHostException ue){
					//setVisible(true);
				}catch(Exception ee){
					ee.getMessage();
					ee.getStackTrace();
					JOptionPane.showMessageDialog(null,"접속실패. IP와 Port를 확인해주세요" ,"예외발생", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		add(readyButton);
		//add(readyButtonEnteredLabel);
		exitButton.setBounds(1175, 5, 102, 102);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
				try {
					Thread.sleep(1000);
				}catch(InterruptedException ie) {
					System.out.println(ie.getMessage());
				}
				System.exit(0);
			}
		});
		add(exitButton);
		add(menuBar);
		
		startButton.setBounds(480, 570, 282, 80);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
				c1 = new Client(DangDang.this);
			}
		});
		add(startButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(150, 150, 100, 150);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(leftButton.isEnabled()) {
					Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
					ButtonEnteredMusic.start();
					selectLeft();
				}
			}
		});
		add(leftButton);
		
		rightButton.setVisible(false);
		rightButton.setBounds(1030, 150, 100, 150);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(rightButton.isEnabled()) {
					Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
					ButtonEnteredMusic.start();
					selectRight();
				}
			}
		});
		add(rightButton);
		
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 100);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(easy == true) {
					easyButton.setIcon(easyButtonEnteredImage);
					easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}else {
					easyButton.setIcon(easyButtonBasicImage);
					easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
				
				if(easyButton.isEnabled()) {
					hard = false;
					easy = true;
					hardButton.setIcon(hardButtonBasicImage);
					setFocusable(true);
					difficulty = "Easy";
				}
			}
		});
		add(easyButton);
		
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 100);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(hard == true) {
					hardButton.setIcon(hardButtonEnteredImage);
					hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}else {
					hardButton.setIcon(hardButtonBasicImage);
					hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
				if(hardButton.isEnabled()) {
					hardButton.setIcon(hardButtonEnteredImage);
					hard = true;
					easy = false;
					easyButton.setIcon(easyButtonBasicImage);
					difficulty = "Hard";
				}
			}
		});
		add(hardButton);
		
		backButton.setVisible(false);
		backButton.setBounds(-50, 20, 400, 100);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music ButtonEnteredMusic = new Music("introPressStartButtonSound.mp3",false);
				ButtonEnteredMusic.start();
				backMain();
			}
		});
		add(backButton);
	}
	public void getClientInfo(Client c1) {
		this.c = c1;
		//flag = true;
		
	}
	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D)screenGraphic);
		g.drawImage(screenImage, 0, 30, null);
	}
	public void screenDraw(Graphics2D g) {
		g.drawImage(background, 0, 0, null); //background를 0,0의 위치에 그려준다. 단순한이미지
		  
		//g.drawImage(new ImageIcon(Main.class.getResource("../images/albumImage1.png", 340, 120,null)));
		
		if(isMainScreen) {
			g.drawImage(selectedImage, 340, 120, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		
		if(isGameScreen) {
			game.screenDraw(g);
			//this.repaint();
			//this.setFocusable(true);
		}
		paintComponents(g); // 항상 고정적으로 있는 이미지 구현
		this.repaint();
		this.setFocusable(true);
	}
	 	public void selectTrack(int nowSelected) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
			titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
			selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
			selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(),true);
			selectedMusic.start();
		
	}
	public void selectLeft() {
		if(nowSelected == 0) {
			nowSelected = trackList.size() -1;
		}else {
			nowSelected--;
		}
		selectTrack(nowSelected);
	}
	public void selectRight() {
		if(nowSelected == trackList.size()-1) {
			nowSelected = 0;
		}else {
			nowSelected++;
		}
		selectTrack(nowSelected);
		
	}
	boolean isFocusable;
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic !=null) {
			selectedMusic.close();
		}
		Main.score = 0 ;
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		menuBar.setVisible(false);
		exitButton.setVisible(false);
		backButton.setVisible(true);
		readyButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		game.start();
		try {
		Thread.sleep(1000);
		this.repaint();
		setFocusable(true);
		}catch(Exception ee) {
		}
		jtf.setEditable(false);
		ready =false;
		readyButton.setIcon(readyImage);
		easyButton.setIcon(easyButtonBasicImage);
		hardButton.setIcon(hardButtonBasicImage);
		easyButton.setEnabled(true);
		hardButton.setEnabled(true);
		//readyButtonEnteredLabel.setVisible(false);
		//readyButton.setIcon(readyImage);
	}
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		menuBar.setVisible(true);
		exitButton.setVisible(true);
		backButton.setVisible(false);
		readyButton.setVisible(true);
		background =  new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
		selectTrack(nowSelected);
		isGameScreen = false;
		game.close();
		jtf.setEditable(true);
		leftButton.setEnabled(true);
		rightButton.setEnabled(true);		
		//setFocusable(false);
		easy = false;
		hard = false;
		try{
			c1.dos.writeUTF("//Score"+Main.score);
			c1.dos.flush();
			return;
		}catch(UnknownHostException ue){
		}catch(Exception ee){
		}
		//ready = false;
		//readyButton.setEnabled(true);
		
		//readyButtonEnteredLabel.setVisible(false);
		//서버에 점수 보냄, 점수 앞에 \\로 표시 -> 서버에서 점수 받음 -> broadcast로 각클라이언트에게 전송
 	}
	public void enterMain() {
		startButton.setVisible(false);
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.png")).getImage();
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		isMainScreen= true;
		try {
		c1.dis.readUTF();
		}catch(Exception ie) {
			JOptionPane.showMessageDialog(null,"4명이 다 찼습니다." ,"접속실패", JOptionPane.INFORMATION_MESSAGE);
			System.exit(1);
		}
	}
}

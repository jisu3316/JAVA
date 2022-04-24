package dangdang;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	@Override
	public void keyPressed(KeyEvent e) {
		if(DangDang.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			DangDang.game.pressS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DangDang.game.pressD();
		}else if(e.getKeyCode() == KeyEvent.VK_F) {
			DangDang.game.pressF();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DangDang.game.pressSpace();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			DangDang.game.pressJ();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
			DangDang.game.pressK();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			DangDang.game.pressL();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(DangDang.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			DangDang.game.releaseS();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DangDang.game.releaseD();
		}else if(e.getKeyCode() == KeyEvent.VK_F) {
			DangDang.game.releaseF();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			DangDang.game.releaseSpace();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			DangDang.game.releaseJ();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
			DangDang.game.releaseK();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			DangDang.game.releaseL();
		}
	}

}

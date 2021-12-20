package dxBallProject;

import java.awt.event.KeyEvent;

public class Paddle extends objects{
	int paddleX;
	int paddleY;
	int paddleXdir;
	public Paddle(int paddleX,int paddleY,int paddleXdir,int width, int height) {
		super(width, height);
		this.paddleX = paddleX;
		this.paddleY = paddleY;
		this.paddleXdir = paddleXdir;
		this.width=width;
		this.height=height;
	}
	public int getPaddleX() {
		return paddleX;
	}
	public void setPaddleX(int paddleX) {
		this.paddleX = paddleX;
	}
	public int getPaddleY() {
		return paddleY;
	}
	public void setPaddleY(int paddleY) {
		this.paddleY = paddleY;
	}
	public int getPaddleXdir() {
		return paddleXdir;
	}
	public void setPaddleXdir(int paddleXdir) {
		this.paddleXdir = paddleXdir;
	}
	
	public void movePaddleWasd(int key) {
		if(key==KeyEvent.VK_A) {
			this.setPaddleXdir(-20);
		}
		if(key==KeyEvent.VK_D) {
			this.setPaddleXdir(+20);
		}
	}
	public void movePaddleArrows(int key) {
		if(key==KeyEvent.VK_LEFT) {
			this.setPaddleXdir(-20);
		}
		if(key==KeyEvent.VK_RIGHT) {
			this.setPaddleXdir(+20);
		}
	}
	public void move() {
		paddleX+=paddleXdir;
	}
}

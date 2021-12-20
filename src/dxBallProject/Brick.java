package dxBallProject;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Brick extends objects{
	int brickX;
	int brickY;
	int HP;
	int colorCode;
	public Brick(int brickX,int brickY,int width, int height,int HP,int colorCode) {
		super(width, height);
		this.brickX = brickX;
		this.brickY = brickY;
		this.width = width;
		this.height = height;
		this.HP=HP;
		this.colorCode=colorCode;
	}
	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int getColorCode() {
		return colorCode;
	}

	public void setColorCode(int colorCode) {
		this.colorCode = colorCode;
	}

	public int getBrickX() {
		return brickX;
	}
	public void setBrickX(int brickX) {
		this.brickX = brickX;
	}
	public int getBrickY() {
		return brickY;
	}
	public void setBrickY(int brickY) {
		this.brickY = brickY;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}

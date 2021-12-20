package dxBallProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Levels {
	Color test = new Color(0, 102, 0);
	ArrayList<Brick> brickDesign = new ArrayList<Brick>();
	int colorCode;
	int HP;

	Image brickPinkImg =Toolkit.getDefaultToolkit().createImage("brickPink.png"); 
	
	Image brickBlueImg =Toolkit.getDefaultToolkit().createImage("brickBlue.png"); 
	
	Image brickGreyImg =Toolkit.getDefaultToolkit().createImage("brickGrey.png"); 
	
	Image brickGreenImg =Toolkit.getDefaultToolkit().createImage("brickGreen.png"); 
	
	Image brickYellowImg =Toolkit.getDefaultToolkit().createImage("brickYellow.png"); 
	
	Image brickBrownImg =Toolkit.getDefaultToolkit().createImage("brickBrown.png"); 
	
	Image brickRedImg =Toolkit.getDefaultToolkit().createImage("brickRed.png"); 
	
	public Levels() {
	}

	public ArrayList<Brick> getBrickDesign() {
		return brickDesign;
	}

	public void setBrickDesign(ArrayList<Brick> brickDesign) {
		this.brickDesign = brickDesign;
	}

	public void drawBrick(Graphics g,dxBall dxball) {
		Color Orange = new Color(255, 102, 0);
		for (int i = 0; i < brickDesign.size(); i++) {
			if (brickDesign.get(i).getColorCode() == 1) {
				g.drawImage(brickYellowImg, brickDesign.get(i).brickX, brickDesign.get(i).brickY, brickDesign.get(i).width, brickDesign.get(i).height,dxball);
			} else if (brickDesign.get(i).getColorCode() == 2) {
				g.drawImage(brickPinkImg, brickDesign.get(i).brickX, brickDesign.get(i).brickY, brickDesign.get(i).width, brickDesign.get(i).height,dxball);
			} else if (brickDesign.get(i).getColorCode() == 3) {
				g.drawImage(brickBlueImg, brickDesign.get(i).brickX, brickDesign.get(i).brickY, brickDesign.get(i).width, brickDesign.get(i).height,dxball);
			} else if (brickDesign.get(i).getColorCode() == 4) {
				g.drawImage(brickGreenImg, brickDesign.get(i).brickX, brickDesign.get(i).brickY, brickDesign.get(i).width, brickDesign.get(i).height,dxball);
			} else if (brickDesign.get(i).getColorCode() == 5) {
				g.drawImage(brickGreyImg, brickDesign.get(i).brickX, brickDesign.get(i).brickY, brickDesign.get(i).width, brickDesign.get(i).height,dxball);
			} else if (brickDesign.get(i).getColorCode() == 6) {
				g.drawImage(brickBrownImg, brickDesign.get(i).brickX, brickDesign.get(i).brickY, brickDesign.get(i).width, brickDesign.get(i).height,dxball);
			} else if (brickDesign.get(i).getColorCode() == 7) {
				g.drawImage(brickRedImg, brickDesign.get(i).brickX, brickDesign.get(i).brickY, brickDesign.get(i).width, brickDesign.get(i).height,dxball);
			}
		}
	}

	public int getColorCode() {
		return colorCode;
	}

	public void setColorCode(int colorCode) {
		this.colorCode = colorCode;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public void createLevel1() {
		int x = 720;
		int y = 540;
		int k = 7;
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j < k; j++) {
				if (i == 9) {
					colorCode = 1;
					HP = 2;
				}
				if (i == 8) {
					colorCode = 6;
					HP = 1;
				}
				if (i == 7) {
					colorCode = 5;
					HP = 1;
				}
				if (i == 3) {
					colorCode = 7;
					HP = 1;
				}
				if (i == 2) {
					colorCode = 5;
					HP = 1;
				}
				if (i == 1) {
					colorCode = 6;
					HP = 1;
				}
				if (i == 0) {
					colorCode = 1;
					HP = 2;
				}
				Brick newBrick = new Brick(x, y, 80, 40, HP, colorCode);
				brickDesign.add(newBrick);
				x += 81;
			}
			if (i == 0) {
				k += 6;
				x = x - (k - 3) * 81;
			} else if (i >= 1 && i <= 2) {
				k += 2;
				x = x - (k - 1) * 81;
			} else if (i >= 3 && i <= 5) {
				x = x - (k) * 81;
			} else if (i >= 6 && i <= 7) {
				k -= 2;
				x = x - (k + 1) * 81;
			} else if (i == 8) {
				k -= 6;
				x = x - (k + 3) * 81;
			}
			y -= 41;
		}
	}
	public void createLevel2() {
		int x = 100;
		int y = 480;
		int k = 21;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < k; j++) {
				if (j == 0 || j == (k - 1) || i == 0) {
					colorCode = 2;
					HP = 2;
				} else if (j == 1 || j == (k - 2) || i == 1) {
					colorCode = 3;
					HP = 1;
				} else if (j == 2 || j == (k - 3) || i == 2) {
					colorCode = 4;
					HP = 1;
				} else if (j == 3 || j == (k - 4) || i == 3) {
					colorCode = 5;
					HP = 1;
				} else {
					colorCode = 1;
					HP = 1;
				}
				Brick newBrick = new Brick(x, y, 80, 40, HP, colorCode);
				brickDesign.add(newBrick);
				x += 81;
			}
			k -= 2;
			x = x - (k + 1) * 81;
			y -= 41;
		}
	}

	public void createLevel3() {
		int xL = 1001;
		int xR = 920;
		int y = 780;
		HP = 1;
		colorCode=5;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Brick leftSide = new Brick(xL, y, 80, 40, HP, colorCode);
				brickDesign.add(leftSide);
				Brick rightSide = new Brick(xR, y, 80, 40, HP, colorCode);
				brickDesign.add(rightSide);
				y -= 41;
			}
			y += 3 * 41;
			xL += 81;
			xR -= 81;
		}
		y += 41;
		xR = 596;
		xL = 1325;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 8; j++) {
				Brick leftSide = new Brick(xL, y, 80, 40, HP, colorCode);
				brickDesign.add(leftSide);
				Brick rightSide = new Brick(xR, y, 80, 40, HP, colorCode);
				brickDesign.add(rightSide);
				y -= 41;
			}
			y += 6 * 41;
			xL += 81;
			xR -= 81;
		}
		xL = 1001;
		xR = 920;
		y = 420;
		HP = 2;
		colorCode = 7;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				Brick leftSide = new Brick(xL, y, 80, 40, HP, colorCode);
				brickDesign.add(leftSide);
				Brick rightSide = new Brick(xR, y, 80, 40, HP, colorCode);
				brickDesign.add(rightSide);
				y -= 41;
			}
			y += 2 * 41;
			xL += 81;
			xR -= 81;
		}
		xL = 1001;
		xR = 920;
		y = 51;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				Brick leftSide = new Brick(xL, y, 80, 40, HP, colorCode);
				brickDesign.add(leftSide);
				Brick rightSide = new Brick(xR, y, 80, 40, HP, colorCode);
				brickDesign.add(rightSide);
				y += 41;
			}
			y -= 2 * 41;
			xL += 81;
			xR -= 81;
		}

	}

	public void createLevel5() {
		int x = 300;
		int y = 640;
		HP = 1;
		colorCode=1;
		for (int i = 0; i < 14; i++) {
			Brick newBrick = new Brick(x, y, 80, 40, HP, colorCode);
			brickDesign.add(newBrick);
			if (i == 0 || i == 6 || i == 12) {
				for (int m = 0; m < 2; m++) {
					for (int j = 0; j < 4; j++) {
						newBrick = new Brick((x + 81), y, 80, 40, HP, colorCode);
						brickDesign.add(newBrick);
						x += 81;
					}
					y -= 41;
					x -= 4 * 81;
				}
				y += 82;
			}
			y -= 41;
		}

		x = 780;
		y = 640;
		colorCode=3;
		for (int i = 0; i < 14; i++) {
			Brick newBrick = new Brick(x, y, 80, 40, HP, colorCode);
			brickDesign.add(newBrick);
			newBrick = new Brick((x + 324), y, 80, 40, HP, colorCode);
			brickDesign.add(newBrick);
			if (i == 11) {
				int tempX = x;
				int tempY = y - 82;
				for (int j = 0; j < 3; j++) {
					for (int m = 0; m < 6; m++) {
						newBrick = new Brick((tempX + 81), (tempY), 80, 40, HP, colorCode);
						brickDesign.add(newBrick);
						tempY += 41;
					}
					tempY -= 82;
					tempX += 81;
				}
			}
			y -= 41;
		}

		x = 1260;
		y = 640;
		int tempX = x + 162;
		int tempY = y;
		colorCode=2;
		Brick newBrick;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < i + 3; j++) {
				newBrick = new Brick(tempX, tempY - 41, 80, 40, HP, colorCode);
				brickDesign.add(newBrick);
				tempY -= 41;
			}
			tempY += 82;
			tempX += 81;
		}
		for (int i = 0; i < 14; i++) {
			if (i == 0 || i == 12) {
				newBrick = new Brick(x, y, 80, 40, HP, colorCode);
				brickDesign.add(newBrick);
				for (int j = 0; j < 2; j++) {
					newBrick = new Brick(x + 81, y - j * 41, 80, 40, HP, colorCode);
					brickDesign.add(newBrick);
				}
				y -= 41;
			} else {
				newBrick = new Brick(x, y, 80, 40, HP, colorCode);
				brickDesign.add(newBrick);
				if (i >= 4 && i <= 9) {
					newBrick = new Brick(x, y, 80, 40, HP, colorCode);
					brickDesign.add(newBrick);
					newBrick = new Brick((x + 324), y, 80, 40, HP, colorCode);
					brickDesign.add(newBrick);
				}
				y -= 41;
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < i + 3; j++) {
				newBrick = new Brick(x+162, y+82, 80, 40, HP, colorCode);
				brickDesign.add(newBrick);
				y += 41;
			}
			y -= 82;
			x += 81;
		}
	}
	public void createLevel4() {
        int x = 120;
        int y = 500;
        colorCode = 1;
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 7; j++) {
                Brick newBrick = new Brick(x, y, 80, 40, 1, colorCode);
                brickDesign.add(newBrick);
                y -= 41;
            }
            if (i%6 == 5 || i%6 == 0  ||i%6 == 1) {
                y += 246;
            }
            else if (i%6 == 2 || i%6 == 3  ||i%6 == 4) {
                y += 328;
            }
            if (colorCode == 6) {
                colorCode = 1;
            }
            colorCode++;
            x+= 81;
        }
    }
}
package dxBallProject;

public class Ball extends objects{
	int ballX;
	int ballY;
	int ballXdir;
	int ballYdir;
	int HP;
	public Ball(int ballX,int ballY,int ballXdir,int ballYdir,int width, int height,int HP) {
		super(width, height);
		this.ballX = ballX;
		this.ballY = ballY;
		this.ballXdir = ballXdir;
		this.ballYdir = ballYdir;
		this.width = width;
		this.height = height;
		this.HP=HP;
	}
	
	public int getBallX() {
		return ballX;
	}

	public void setBallX(int ballX) {
		this.ballX = ballX;
	}

	public int getBallY() {
		return ballY;
	}

	public void setBallY(int ballY) {
		this.ballY = ballY;
	}

	public int getBallXdir() {
		return ballXdir;
	}

	public void setBallXdir(int ballXdir) {
		this.ballXdir = ballXdir;
	}

	public int getBallYdir() {
		return ballYdir;
	}

	public void setBallYdir(int ballYdir) {
		this.ballYdir = ballYdir;
	}

	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public void changeYdir() {
		this.setBallYdir(this.getBallYdir()*-1);
	}
	public void changeXdir() {
		this.setBallXdir(this.getBallXdir()*-1);
	}
	public void changeAllDir() {
		this.setBallYdir(this.getBallYdir()*-1);
		this.setBallXdir(this.getBallXdir()*-1);
	}
	public void boundControl() {
		if(this.getBallY()<=0) {
			this.changeYdir();
			this.setBallY(1);
			this.move();
		}
		else if(this.getBallX()>=1890) {
			this.changeXdir();
			this.setBallX(1889);
			this.move();
		}
		else if(this.getBallX()<=0) {
			this.changeXdir();
			this.setBallX(1);
			this.move();
		}
	
	}
	public boolean loseControl() {
		if(this.getBallY()>1080) {
			this.setHP(this.getHP()-1);
			return false;
		}
		else {
			return true;
		}
}
	public void move() {
		ballX+=ballXdir;
		ballY+=ballYdir;
	}
	
	
}

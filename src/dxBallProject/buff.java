package dxBallProject;

import java.awt.Graphics;
import java.sql.Array;


public class buff extends objects{
	public int buffX;
	public int buffY;
	public int buffNumber;
	public buff(int buffX,int buffY,int width, int height,int buffNumber) {
		super(width, height);
		this.buffX=buffX;
		this.buffY=buffY;
		this.buffNumber=buffNumber;
		this.width=width;
		this.height=height;
	}
	public void paddleSizeUp(Paddle paddle) {
		paddle.setWidth(paddle.getWidth()+50);
	}
	public void paddleSizeDown(Paddle paddle) {
		paddle.setWidth(paddle.getWidth()-30);
	}
	public void hpUp(Ball ball) {
		ball.HP+=2;
	}
	public void hpDown(Ball ball) {
		ball.HP-=2;
	}
	public void fastBall(Ball ball) {
		ball.setBallXdir(ball.getBallXdir()*3);
		ball.setBallYdir(ball.getBallYdir()*3);
		ball.setBallXdir(ball.getBallXdir()/2);
		ball.setBallYdir(ball.getBallYdir()/2);
	}
	public void slowBall(Ball ball) {
		ball.setBallXdir(ball.getBallXdir()/3);
		ball.setBallYdir(ball.getBallYdir()/3);
		ball.setBallXdir(ball.getBallXdir()*2);
		ball.setBallYdir(ball.getBallYdir()*2);
	}
	public void fire() {

	}
}
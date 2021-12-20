package dxBallProject;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.TargetDataLine;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class dxBall extends JPanel implements KeyListener,ActionListener,MouseListener{
	Timer timer = new Timer(20, this);
	
	int score=0;
	
	Random rand = new Random();
	
	public boolean settingsControl = false;//ArrowsControl
	
	public boolean menuBackControl= false;
	
	public boolean startControl=false;
	
	public boolean gameOverControl = false;
	
	public boolean gameStopControl= false;
	
	public boolean levelCompleteControl = false;
	
	public boolean scoreControl = true;
	
	public ArrayList<buff> buffArray = new ArrayList<buff>();
	
	public int removedBrick=0;
	
	int removedBrickControl=5;
	
	public int fireamount=0;
	
	double delayTime = 0;
	
	//double timeholder = 0;
	
	public ArrayList<Fire> FireArrayLeft = new ArrayList<Fire>();
	
	public ArrayList<Fire> FireArrayRight = new ArrayList<Fire>();
	
	public int FireYdir=10;
	
	public boolean firecheck=false;
	
	Paddle paddle = new Paddle(810, 980, 0,300,30);
	
	Ball ball = new Ball(paddle.getPaddleX()+130, 940, 10,10,30,30,6);
		
	Levels currentLevel;
	
	int levelNO= 1;
	
	Image extraLifeImg = Toolkit.getDefaultToolkit().createImage("extraLife.png");
	
	Image minusLifeImg = Toolkit.getDefaultToolkit().createImage("minusLife.png");
	
	Image paddleSizeUpImg = Toolkit.getDefaultToolkit().createImage("paddleSizeUp.png");
	
	Image paddleSizeDownImg = Toolkit.getDefaultToolkit().createImage("paddleSizeDown.png");
	
	Image fastBallImg = Toolkit.getDefaultToolkit().createImage("fastBall.png");
	
	Image slowBallImg = Toolkit.getDefaultToolkit().createImage("slowBall.png");
	
	Image paddleImg =Toolkit.getDefaultToolkit().createImage("paddlePng.png"); 
	
	Image ballImg =Toolkit.getDefaultToolkit().createImage("ball2.png"); 
	
	Image hpImg = Toolkit.getDefaultToolkit().createImage("HP.png");
	
	Image scoreImg = Toolkit.getDefaultToolkit().createImage("score.png");
	
	Image gameOverImg = Toolkit.getDefaultToolkit().createImage("gameOver.png");
	
	Image levelCompleteImg = Toolkit.getDefaultToolkit().createImage("levelCompleteBack.jpg");
	
	Image shootingPaddle=Toolkit.getDefaultToolkit().createImage("shootingPaddle.png");
	
	Image pauseScreenImg = Toolkit.getDefaultToolkit().createImage("pauseScreenImg.png");
	
	public dxBall() {
		currentLevel = new Levels();
		this.levelSelector(levelNO);
		setBackground(Color.black);
		timer.start();
		sounds soundtrack = new sounds();
		soundtrack.playMusicCont("soundtrack.wav");
	}
	public void levelSelector(int levelNO) {
        if (levelNO == 1) {
            currentLevel.createLevel1();
        }
        else if (levelNO == 2) {
            currentLevel.createLevel2();
        }
        else if (levelNO == 3) {
            currentLevel.createLevel3();
        }
        else if (levelNO == 4) {
            currentLevel.createLevel4();
        }
        else if(levelNO == 5) {
        	currentLevel.createLevel5();
        }
    }
	public void paddleBallControl() {
		 Rectangle paddleRect = new Rectangle(paddle.getPaddleX(),paddle.getPaddleY(),paddle.getWidth(),paddle.getHeight());
		 Rectangle ballRect = new Rectangle(ball.getBallX(),ball.getBallY(),ball.getWidth(),ball.getHeight());
		 if(ballRect.intersects(paddleRect)) {
			 //BallVectors
			 ball.setBallYdir(ball.getBallYdir()*-1);
			 ball.setBallXdir(ball.ballXdir-(paddle.paddleXdir)/2);
			
			 //BallPosition
			 ball.setBallY(paddle.paddleY-31);
			
			 //Soundtrack
			 sounds ballPaddleMusic = new sounds();
			 ballPaddleMusic.playMusic("ballPaddleSound.wav");
		 }
		}
	
	public void movePaddleControl() {
		if(paddle.paddleX<=0) {	
			paddle.setPaddleXdir(0);
			paddle.setPaddleX(1);
		}
		else if(paddle.paddleX+paddle.width>=1920) {
			paddle.setPaddleXdir(0);
			paddle.setPaddleX(1910-paddle.getWidth());
		}
	}
	public void ballBrickControl() {
		for( int i=0;i<currentLevel.brickDesign.size();i++) {
			Rectangle brickRect = new Rectangle(currentLevel.brickDesign.get(i).brickX,currentLevel.brickDesign.get(i).brickY,currentLevel.brickDesign.get(i).width,currentLevel.brickDesign.get(i).height);
			if(brickRect.intersectsLine(ball.ballX,
					ball.ballY+15, 
					ball.ballX, 
					ball.ballY+15)) {
				//topun sol sýnýrý
				//BrickHpLOW
				currentLevel.getBrickDesign().get(i).setHP(currentLevel.getBrickDesign().get(i).getHP() - 1);
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("brickBroke.wav");
					removedBrick += 1;
				}
				//BuffCreate//RemovedBrickControl=5 at first
				if (removedBrick==removedBrickControl) {
					int randBuff = rand.nextInt(7);
					buff buff = new buff(currentLevel.brickDesign.get(i).brickX,
							currentLevel.brickDesign.get(i).brickY, 50, 50, randBuff);
					buffArray.add(buff);
					removedBrickControl+=5;
			        }
				//BrickBroke
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					currentLevel.brickDesign.remove(i);
					score+=40;
				}
				//BallDirection
				if(ball.ballXdir<0) {
					ball.changeXdir();
					ball.move();
					break;
				}
				else if(ball.ballXdir>0) {
					ball.changeYdir();
					break;
				}
			}
			else if(brickRect.intersectsLine(ball.ballX+15,
					ball.ballY+ball.height, 
					ball.ballX+15, 
					ball.ballY+ball.height)){
				//topun alt sýnýrý
				//BrickHpLOW
				currentLevel.getBrickDesign().get(i).setHP(currentLevel.getBrickDesign().get(i).getHP() - 1);
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("brickBroke.wav");
					removedBrick += 1;
				}
				//BuffCreate
				if (removedBrick==removedBrickControl) {
					int randBuff = rand.nextInt(7);
					buff buff = new buff(currentLevel.brickDesign.get(i).brickX,
							currentLevel.brickDesign.get(i).brickY, 50, 50, randBuff);
					buffArray.add(buff);
					removedBrickControl+=5;
			        }
				//BrickBroke
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					currentLevel.brickDesign.remove(i);
					score+=40;
				}
				//BallDirection
				ball.changeYdir();
				ball.move();
				break;
			
			}
			else if(brickRect.intersectsLine(ball.ballX+ball.width,
					ball.ballY+15, 
					ball.ballX+ball.width, 
					ball.ballY+15)) {
				//topun sað sýnýrý
				//BallHpLOW
				currentLevel.getBrickDesign().get(i).setHP(currentLevel.getBrickDesign().get(i).getHP() - 1);
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("brickBroke.wav");
					removedBrick += 1;
				}
				//BuffCreate
				if (removedBrick==removedBrickControl) {
					int randBuff = rand.nextInt(7);
					buff buff = new buff(currentLevel.brickDesign.get(i).brickX,
							currentLevel.brickDesign.get(i).brickY, 50, 50,randBuff);
					buffArray.add(buff);
					removedBrickControl+=5;
			        }
				//BrickBroke
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					currentLevel.brickDesign.remove(i);
					score+=40;
				}
				//BallDirection
				if(ball.ballXdir<0) {
					ball.changeYdir();
					ball.move();
					break;
				}
				else if(ball.ballXdir>0) {
					ball.changeXdir();
					ball.move();
					break;
				}
				
			}
			else if(brickRect.intersectsLine(ball.ballX+15,
					ball.ballY, 
					ball.ballX+15, 
					ball.ballY)){
				//topun üst sýnýrý
				//BallHpLOW
				currentLevel.getBrickDesign().get(i).setHP(currentLevel.getBrickDesign().get(i).getHP() - 1);
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("brickBroke.wav");
					removedBrick += 1;
				}
				//BuffCreate
				if (removedBrick==removedBrickControl) {
					int randBuff = rand.nextInt(7);
					buff buff = new buff(currentLevel.brickDesign.get(i).brickX,
							currentLevel.brickDesign.get(i).brickY, 50, 50, randBuff);
					buffArray.add(buff);
					removedBrickControl+=5;
			        }
				//BrickBroke
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					currentLevel.brickDesign.remove(i);
					score+=40;
				}
				//BallDirection
				ball.changeYdir();
				ball.move();
				break;
			}
		}
	}
	public void buffControl() {
		Rectangle paddleRect = new Rectangle(paddle.getPaddleX(),paddle.getPaddleY(),paddle.getWidth(),paddle.getHeight());
		for(int i=0;i<buffArray.size();i++) {
			if(paddleRect.intersects(new Rectangle(buffArray.get(i).buffX,buffArray.get(i).buffY,buffArray.get(i).width,buffArray.get(i).height))){
				if(buffArray.get(i).buffNumber==0) {
					buffArray.get(i).paddleSizeUp(paddle);
					buffArray.remove(i);
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("buffSound.wav");
					break;
				}
				else if(buffArray.get(i).buffNumber==1) {
					buffArray.get(i).paddleSizeDown(paddle);
					buffArray.remove(i);
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("buffSound.wav");
					break;
				}
				else if(buffArray.get(i).buffNumber==2) {
					buffArray.get(i).hpUp(ball);
					buffArray.remove(i);
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("buffSound.wav");
					break;
				}
				else if(buffArray.get(i).buffNumber==3) {
					buffArray.get(i).hpDown(ball);
					buffArray.remove(i);
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("buffSound.wav");
					break;
				}
				else if(buffArray.get(i).buffNumber==4) {
					buffArray.get(i).fastBall(ball);
					buffArray.remove(i);
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("buffSound.wav");
					break;
				}
				else if(buffArray.get(i).buffNumber==5) {
					buffArray.get(i).slowBall(ball);
					buffArray.remove(i);
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("buffSound.wav");
					break;
				}
				else if(buffArray.get(i).buffNumber==6) {
					buffArray.get(i).fire();
					buffArray.remove(i);
					firecheck=true;
					fireamount=0;
					//timeholder=delayTime;
					sounds ballPaddleMusic = new sounds();
					ballPaddleMusic.playMusic("buffSound.wav");
					break;
				}
			}
		}
	}
	public void fireBrickControl() {
		try {
		for(int i=0;i<currentLevel.brickDesign.size();i++) {
			Rectangle brickRect = new Rectangle(currentLevel.brickDesign.get(i).brickX,currentLevel.brickDesign.get(i).brickY,currentLevel.brickDesign.get(i).width,currentLevel.brickDesign.get(i).height);
			for(int a = 0; a < FireArrayLeft.size();a++) {
				Rectangle Firecontrol = new Rectangle(FireArrayLeft.get(a).getX(),FireArrayLeft.get(a).getY(),5,20);
				if(Firecontrol.intersects(brickRect)) {
				currentLevel.getBrickDesign().get(i).setHP(currentLevel.getBrickDesign().get(i).getHP() - 1);	
				
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					sounds fireBrickMusic = new sounds();
					fireBrickMusic.playMusic("explosionSound.wav");
					removedBrick += 1;
				}
				if (removedBrick==removedBrickControl) {
					int randBuff = rand.nextInt(7);
					buff buff = new buff(currentLevel.brickDesign.get(i).brickX,
					currentLevel.brickDesign.get(i).brickY, 50, 50, randBuff);
					buffArray.add(buff);
					removedBrickControl+=5;
			        }
				
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					score+=40;
					FireArrayLeft.remove(a);
					currentLevel.brickDesign.remove(i);
				}
			}
				else {
					if(FireArrayLeft.get(a).getY() == 0)
					FireArrayLeft.remove(a);
				}
			}
			
			for(int z = 0; z < FireArrayRight.size(); z++) {
				Rectangle Firecontrol2 = new Rectangle(FireArrayRight.get(z).getX(),FireArrayRight.get(z).getY(),5,20);
				if(Firecontrol2.intersects(brickRect)) {
				currentLevel.getBrickDesign().get(i).setHP(currentLevel.getBrickDesign().get(i).getHP() - 1);	
				
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					sounds fireBrickMusic = new sounds();
					fireBrickMusic.playMusic("explosionSound.wav");
					removedBrick += 1;
				}
				
				if (removedBrick==removedBrickControl) {
					int randBuff = rand.nextInt(7);
					buff buff = new buff(currentLevel.brickDesign.get(i).brickX,
							currentLevel.brickDesign.get(i).brickY, 50, 50, randBuff);
					buffArray.add(buff);
					removedBrickControl+=5;
			        }
				
				if (currentLevel.brickDesign.get(i).getHP() == 0) {
					score+=40;
					FireArrayRight.remove(z);
					currentLevel.brickDesign.remove(i);
				}
			}
				else {
					if(FireArrayRight.get(z).getY() == 0) {
					FireArrayRight.remove(z);
					}
				}
			}
					
		}
		}
		catch(Exception a) {
			
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		currentLevel.drawBrick(g,this);
		g.setColor(Color.white);
		g.setFont(new Font("OCR A Extended",Font.BOLD,50));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawImage(hpImg, 10, 10, 50, 50, this);
		if(ball.HP<0) {
			g.drawString("0",65,50);
		}
		else {
			g.drawString(""+ball.HP/2,65,50);
		}
		g.drawImage(paddleImg, paddle.paddleX, paddle.paddleY, paddle.width, paddle.height,this);
		g.drawImage(ballImg, ball.getBallX(), ball.getBallY(), ball.width, ball.height,this);
		g.drawImage(scoreImg, 1640, 10,120,40, this);
        g.drawString(""+score,1760,45);
		if(buffArray.size()>0) {
			for(int i=0;i<buffArray.size();i++) {
				if(buffArray.get(i).buffNumber==0) {
					g.drawImage(paddleSizeUpImg, buffArray.get(i).buffX,buffArray.get(i).buffY, 60, 60, this);
				}
				else if(buffArray.get(i).buffNumber==1) {
					g.drawImage(paddleSizeDownImg, buffArray.get(i).buffX,buffArray.get(i).buffY, 60, 60, this);
				}
				else if(buffArray.get(i).buffNumber==2) {
					g.drawImage(extraLifeImg, buffArray.get(i).buffX,buffArray.get(i).buffY, 60, 60, this);
				}
				else if(buffArray.get(i).buffNumber==3) {
					g.drawImage(minusLifeImg, buffArray.get(i).buffX,buffArray.get(i).buffY, 60, 60, this);
				}
				else if(buffArray.get(i).buffNumber==4) {
					g.drawImage(fastBallImg, buffArray.get(i).buffX,buffArray.get(i).buffY, 60, 60, this);
				}
				else if(buffArray.get(i).buffNumber==5) {
					g.drawImage(slowBallImg, buffArray.get(i).buffX,buffArray.get(i).buffY, 60, 60, this);
				}
				else if(buffArray.get(i).buffNumber==6) {
					g.drawImage(shootingPaddle,buffArray.get(i).buffX,buffArray.get(i).buffY, 60, 60, this);
				}
			}
		}
		g.setColor(Color.RED);
		for(int i = 0 ; i < FireArrayLeft.size() ; i++) {
			g.fillRect(FireArrayLeft.get(i).getX(),FireArrayLeft.get(i).getY(),5, 20);
		}
		for(int i = 0 ; i < FireArrayRight.size();i++) {
			g.fillRect(FireArrayRight.get(i).getX(),FireArrayRight.get(i).getY(),5, 20);
		}
		if(levelCompleteControl==true) {
			g.drawImage(levelCompleteImg, 0,0, 1920, 1080, this);
			if(scoreControl ==true) {
				int delaySec = (int)(delayTime)/60;
				score-=delaySec*10;
			}
			scoreControl=false;
			g.setFont(new Font("Cooper Black",Font.BOLD,50));
			g.setColor(Color.white);			
			g.drawString("Score:"+score, 750, 750);
		}
		else if(gameOverControl==true) {
			g.drawImage(gameOverImg, 0, 0, 1920, 1080,this);
			if(scoreControl ==true) {
				int delaySec = (int)(delayTime)/60;
				score-=delaySec*10;
			}
			scoreControl=false;
			g.setFont(new Font("Cooper Black",Font.BOLD,50));
			g.setColor(Color.white);			
			g.drawString("Score:"+score, 750, 750);
		}
		if(gameStopControl==true) {
			g.drawImage(pauseScreenImg, 660, 140, 600, 800,this);
		}
		else if(levelCompleteControl==true&&levelNO==4) {
			//OyunBitiþ-Ekran
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(startControl==false) {
			movePaddleControl();
			paddle.move();
			ball.setBallX(paddle.getPaddleX()+130);
			repaint();
		}
		if(startControl==true) {
			delayTime+=1;
			movePaddleControl();
			paddle.move();
			ball.boundControl();
			ballBrickControl();
			fireBrickControl();
			paddleBallControl();
			ball.move();
			buffControl();
			ball.loseControl();
			if(ball.HP<0) {
				timer.stop();
				buffArray.clear();
				fireamount=0;
				FireArrayLeft.clear();
				FireArrayRight.clear();
				gameOverControl=true;
			}
			if(buffArray.size()>0) {//BuffMove
				for(int i=0;i<buffArray.size();i++) {
					buffArray.get(i).buffY+=5;
				}
			}
			if(ball.loseControl()==false) {
				timer.stop();
				if(ball.HP<0) {
				buffArray.clear();
				timer.stop();
				fireamount=0;
				FireArrayLeft.clear();
				FireArrayRight.clear();
				gameOverControl=true;
				}
				else {
					paddle.setPaddleX(810);
					paddle.setPaddleY(980);
					paddle.setHeight(30);
					paddle.setWidth(300);
					paddle.setPaddleXdir(0);
					ball.setBallX(paddle.getPaddleX()+130);
					ball.setBallY(940);
					buffArray.clear();
					FireArrayRight.clear();
					FireArrayLeft.clear();
					fireamount=0;
					firecheck=false;
					repaint();
					timer.restart();
				}
				startControl=false;
			}
			for(int i =0;i< FireArrayLeft.size();i++) {
				FireArrayLeft.get(i).setY(FireArrayLeft.get(i).getY()-FireYdir);
			}
			for(int i = 0; i < FireArrayRight.size(); i++) {
				FireArrayRight.get(i).setY(FireArrayRight.get(i).getY()-FireYdir);
			}
		}
		if(currentLevel.brickDesign.size()==0) {
			timer.stop();
			levelCompleteControl=true;
			firecheck=false;
		}
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int c= e.getKeyCode();
		if(settingsControl==false) 
		{//Arrows
			paddle.movePaddleArrows(c);
		}
		else if(settingsControl==true) {
			//Wasd
			paddle.movePaddleWasd(c);
		}
		if(c==KeyEvent.VK_SPACE) {
			if(startControl==false) {
			int randomTempX = rand.nextInt(15);
			int randomX = randomTempX-7;
			if(randomX==0) {
				randomTempX = rand.nextInt(15);
				randomX = randomTempX-7;
			}
			ball.setBallXdir(randomX);
			ball.setBallYdir(10);
			startControl=true;
			}
		}
		else if(c==KeyEvent.VK_ENTER) {
			if(gameOverControl==true) {
				levelNO=1;
				this.currentLevel.brickDesign.clear();
				this.levelSelector(levelNO);
	            score=0;
				gameOverControl=false;
				startControl=false;
				paddle.setPaddleX(810);
				paddle.setPaddleY(980);
				paddle.setHeight(30);
				paddle.setWidth(300);
				paddle.setPaddleXdir(0);
				ball.setBallX(paddle.getPaddleX()+130);
				ball.setBallY(940);
				ball.setHP(6);
				ball.setBallXdir(0);
				ball.setBallYdir(0);
				buffArray.clear();
				FireArrayRight.clear();
				FireArrayLeft.clear();
				fireamount=0;
				firecheck=false;
				timer.restart();
				repaint();
			}
		}
		else if(c==KeyEvent.VK_E) {
			if(levelCompleteControl==true||levelNO!=5) {
				delayTime=0;
				levelNO+=1;
	            this.currentLevel.brickDesign.clear();
	            this.levelSelector(levelNO);
	            paddle.setPaddleX(810);
				paddle.setPaddleY(980);
				paddle.setHeight(30);
				paddle.setWidth(300);
				paddle.setPaddleXdir(0);
				ball.setBallX(paddle.getPaddleX()+130);
				ball.setBallY(940);
				ball.setBallXdir(0);
				ball.setBallYdir(0);
				buffArray.clear();
	            levelCompleteControl=false;
	            startControl=false;
	            fireamount=0;
	            firecheck=false;
	            FireArrayRight.clear();
	            FireArrayLeft.clear();
	            repaint();
	            timer.restart();
			}
		}
		else if(c==KeyEvent.VK_ESCAPE) {
			if(gameStopControl==false) {
				gameStopControl=true;
				repaint();
				timer.stop();
			}
			else if(gameStopControl==true) {
				timer.restart();
				gameStopControl=false;
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		if(settingsControl==false) 
		{//Arrows
			if(c==KeyEvent.VK_LEFT||c==KeyEvent.VK_RIGHT) {
				paddle.setPaddleXdir(0);
			}
		}
		else if(settingsControl==true) {
			//Wasd
			if(c==KeyEvent.VK_A||c==KeyEvent.VK_D) {
				paddle.setPaddleXdir(0);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
			int mouseX=e.getX();
			int mouseY=e.getY();
			if(gameStopControl==true) {
				if(mouseX>=772&&mouseX<=1159) {
					if(mouseY>=178&&mouseY<=323) {
						//PlayButton
						timer.restart();
						gameStopControl=false;
					}
					else if(mouseY>=361&&mouseY<=506) {
						//NewGameButton
						levelNO=1;
						this.currentLevel.brickDesign.clear();
						this.levelSelector(levelNO);
			            score=0;
						gameOverControl=false;
						startControl=false;
						paddle.setPaddleX(810);
						paddle.setPaddleY(980);
						paddle.setHeight(30);
						paddle.setWidth(300);
						paddle.setPaddleXdir(0);
						ball.setBallX(paddle.getPaddleX()+130);
						ball.setBallY(940);
						ball.setHP(6);
						ball.setBallXdir(0);
						ball.setBallYdir(0);
						buffArray.clear();
						FireArrayRight.clear();
						FireArrayLeft.clear();
						fireamount=0;
						firecheck=false;
						timer.restart();
						repaint();
						gameStopControl=false;
					}
					
					else if(mouseY>=727&&mouseY<=872) {
						//QuitButton
						 System.exit(0);
					}
				}
				
			}
			else if(firecheck == true)	{
				if(fireamount == 10) {
					firecheck=false;
					fireamount=0;
				}
			FireArrayLeft.add(new Fire(paddle.paddleX,940));
			FireArrayRight.add(new Fire(paddle.paddleX+paddle.width,940));
			fireamount++;
			}
			/*
			else if(firecheck == true)    {
                if(delayTime-timeholder > 100.0) {
                    firecheck=false;
                    delayTime=0;
                    timeholder=0;
                }
            Fire right =new Fire(paddle.paddleX,940);
            Fire left = new Fire(paddle.paddleX+paddle.width,940);
            FireArrayLeft.add(right);
            FireArrayRight.add(left);
            fireamount++;
            }*/
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

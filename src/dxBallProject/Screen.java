package dxBallProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen extends JFrame{
	public Screen() {
		
	}
	public Screen(int a) {
		if(a==1) {
			JLabel labelMain = new JLabel(new ImageIcon("mainBack.png"));
			this.setContentPane(labelMain);
		}
	}
	public static void main(String[]args) {
		
		ImageIcon playButtonImg = new ImageIcon("playButton.jpg");
		ImageIcon quitButtonImg = new ImageIcon("quitButton.jpg");
		ImageIcon gifImg = new ImageIcon("paddleBalGif.gif");
		ImageIcon pauseImg = new ImageIcon("pauseButton.png");
		ImageIcon buttonImg = new ImageIcon("buttonImg.png");
		ImageIcon buttonTickImg = new ImageIcon("buttonTick.png");
		
		Screen screenMenu = new Screen(1);
		screenMenu.setTitle("ALLFX BALL");
		screenMenu.setUndecorated(true);//fullscreen
		screenMenu.setSize(1920,1080);
		screenMenu.setResizable(false);
		screenMenu.setFocusable(false);
		screenMenu.setVisible(true);
		screenMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		JButton tickButton = new JButton(buttonTickImg);
		tickButton.setBounds(630,510,20,20);
		screenMenu.add(tickButton);
		tickButton.setOpaque(false);
		tickButton.setContentAreaFilled(false);
		tickButton.setBorderPainted(false);
		tickButton.setVisible(false);
		
		JButton tickButton2 = new JButton(buttonTickImg);
		tickButton2.setBounds(630,760,20,20);
		screenMenu.add(tickButton2);
		tickButton2.setOpaque(false);
		tickButton2.setContentAreaFilled(false);
		tickButton2.setBorderPainted(false);
		tickButton2.setVisible(true);
		
		JButton playButton = new JButton(playButtonImg);
		playButton.setBounds(140,450,300,120);
		screenMenu.add(playButton);
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		
		JButton exitButton = new JButton(quitButtonImg);
		exitButton.setBounds(140,700,300,120);
		screenMenu.add(exitButton);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		
		JButton settingsButtonWasd = new JButton(buttonImg);
		settingsButtonWasd.setBounds(620,500,40,40);
		screenMenu.add(settingsButtonWasd);
		settingsButtonWasd.setOpaque(false);
		settingsButtonWasd.setContentAreaFilled(false);
		settingsButtonWasd.setBorderPainted(false);
		
		JButton settingsButtonArrows = new JButton(buttonImg);
		settingsButtonArrows.setBounds(620,750,40,40);
		screenMenu.add(settingsButtonArrows);
		settingsButtonArrows.setOpaque(false);
		settingsButtonArrows.setContentAreaFilled(false);
		settingsButtonArrows.setBorderPainted(false);
		
		Screen screenGame= new Screen();
		screenGame.setTitle("ALLFX BALL");
		screenGame.setUndecorated(true);
		screenGame.setResizable(false);
		screenGame.setFocusable(false);
		screenGame.setSize(1920,1080);
		screenGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenGame.requestFocus();
		
		dxBall game = new dxBall();
		game.requestFocus();
		game.addKeyListener(game);
		game.addMouseListener(game);
		game.setFocusable(true);
		game.setFocusTraversalKeysEnabled(false);

		screenGame.add(game);
		screenGame.setVisible(false);
		game.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
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
				if(game.gameStopControl==true) {
					if(mouseX>=772&&mouseX<=1159) {
						if(mouseY>=544&&mouseY<=689) {
							screenGame.setVisible(false);
							screenMenu.setVisible(true);
							game.levelNO=1;
							game.currentLevel.brickDesign.clear();
							game.levelSelector(game.levelNO);
							game.score=0;
				            game.gameOverControl=false;
							game.startControl=false;
							game.paddle.setPaddleX(810);
							game.paddle.setPaddleY(980);
							game.paddle.setHeight(30);
							game.paddle.setWidth(300);
							game.paddle.setPaddleXdir(0);
							game.ball.setBallX(game.paddle.getPaddleX()+130);
							game.ball.setBallY(940);
							game.ball.setHP(6);
							game.ball.setBallXdir(0);
							game.ball.setBallYdir(0);
							game.buffArray.clear();
							game.fireamount=0;
							game.firecheck=false;
							game.FireArrayRight.clear();
							game.FireArrayLeft.clear();
							game.repaint();
							game.timer.restart();
							game.gameStopControl=false;
						}
						
					}}
				
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				screenMenu.setVisible(false);
				screenGame.setVisible(true);
				
			}
			
		});
		exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
		settingsButtonWasd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(game.settingsControl==false) {
					game.settingsControl=true;
					tickButton.setVisible(true);
					tickButton2.setVisible(false);
				}
			}
			
		});
		settingsButtonArrows.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(game.settingsControl==true) {
					game.settingsControl=false;
					tickButton.setVisible(false);
					tickButton2.setVisible(true);
				}
			}
			
		});
	}
}

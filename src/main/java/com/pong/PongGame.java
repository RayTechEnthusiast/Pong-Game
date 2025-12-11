//Class author: Rayan Hashmi
//Date: 12/10/2025 at precicely 11:32pm, 28 minutes before the deadline
//Purpose: Main game logic for PongGame

package com.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PongGame extends JPanel implements MouseMotionListener {
    static int gameWidth = 640;
    static int gameHeight = 480;

    private int mouseYPos;
    private Paddle computerPaddle;
    private Paddle humanPaddle;
    private Ball pongBall;
    private int humanScore;
    private int computerScore;

    private SlowDown slowZone;
    private Speedup fastZone;
    private Wall obstacleWall;

    public PongGame() {
        computerPaddle = new Paddle(610, 240, 50, 10, Color.LIGHT_GRAY);
        humanPaddle = new Paddle(10, 240, 50, 10, Color.LIGHT_GRAY);

        mouseYPos = 0;
        addMouseMotionListener(this);

        pongBall = new Ball(220, 220, 12, 3, Color.ORANGE, 10);

        slowZone = new SlowDown(310, 280, 70, 45);
        fastZone = new Speedup(310, 210, 70, 45);
        obstacleWall = new Wall(320, 75, 150, 12, Color.LIGHT_GRAY);
    }

    public int getHumanScore() {
        return humanScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, gameWidth, gameHeight);

        g.setColor(Color.WHITE);
        g.drawString("User: " + humanScore + " vs AI: " + computerScore, 240, 20);

        pongBall.draw(g);
        computerPaddle.draw(g);
        humanPaddle.draw(g);
        slowZone.draw(g);
        fastZone.draw(g);
        obstacleWall.draw(g);
    }

    public void gameLogic() {
        pongBall.moveBall();
        pongBall.bounceOffwalls(gameHeight - 10, 0);
        humanPaddle.moveY(mouseYPos);
        computerPaddle.moveY(pongBall.getY());

        if (computerPaddle.isTouching(pongBall) || humanPaddle.isTouching(pongBall) || obstacleWall.isTouching(pongBall)) {
            pongBall.reverseX();
        }

        if (slowZone.isTouching(pongBall)) {
            pongBall.setChangeX(pongBall.getChangeX() / 1.2);
        }

        if (fastZone.isTouching(pongBall)) {
            pongBall.setChangeX(pongBall.getChangeX() * 1.2);
        }

        if (pongBall.getX() <= 0) {
            computerScore++;
            pongBall.setX(gameWidth / 3);
            pongBall.sety(gameHeight / 2);
            pongBall.moveBall();
        }

        if (pongBall.getX() >= gameWidth + 10) {
            humanScore++;
            pongBall.setX(gameWidth / 3);
            pongBall.sety(gameHeight / 2);
            pongBall.moveBall();
        }

        pointScored();
    }

    public void pointScored() {
        // optional: could move score logic here
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseYPos = e.getY();
    }
}

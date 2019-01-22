package net.mabdurrahman.src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
 * The Ball Class simulates the ball for the game
 * @author:  MAbdurrahman
 * @date:  19 April 2017
 * @version:  1.0.0
 */
public class Ball {
    //Instance Variables
    private final Color color = new Color(242, 207, 48);//The ball's color
    private final GamePanel gamePanel;
    public static final int DIAMETER = 30;//The ball's diameter
    public int xPosition = 400;//The ball's horizontal position
    public int yPosition = 400;//The ball's vertical position
    public int speed = 5;
    public int xDirection;//The ball's horizontal direction and speed
    public int yDirection;//The ball's vertical direction and speed

    /**
     * Default Ball Constructor - Creates an instance of the ball with no parameter
     */
    public Ball() {
        this.gamePanel = new GamePanel();
        this.xDirection = speed;
        this.yDirection = speed;

    }//end of the Default Ball Constructor
    /**
     * Ball Constructor - Creates an instance of the ball with one parameter of the object
     * GamePanel.
     */
    public Ball(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.xDirection = speed;
        this.yDirection = speed;

    }//end of the Ball Constructor
    /**
     * paint Method - Paints the ball of the dimensions at positions of the X and Y axis
     */
    public void paint(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(color);
        g2d.fillOval(xPosition, yPosition, DIAMETER, DIAMETER);

    }//end of the paint Method
    /**
     * getBounds Method - Gets the position of the ball at the points(x, y) and the rectangle
     * dimensions of the ball.
     * @return Rectangle - Returns a rectangle with x position, y position, width, and height.  In
     * this class the width and height will be equal to the diameter of the ball.
     */
    public Rectangle getBounds() {
        return new Rectangle(xPosition, yPosition, DIAMETER, DIAMETER);

    }//end of the getBounds Method
    /**
     * getCollisionWithPaddle Method - Gets the collisions of the ball and paddle
     * @return Boolean - Returns true, if the rectangular dimensions of the ball contacts the
     * rectangular dimensions of the paddle; otherwise, it returns false.
     */
    public boolean getCollisionWithPaddle() {
        return gamePanel.paddle.getBounds().intersects(getBounds());

    }//end of the getCollisionWithPaddle Method
    /**
     * move Method - Moves the ball along the axis points of (X, Y) within the dimension of the
     * GamePanel.  Also, this method keeps count of the score, totalBricks, and whether the paddle
     * has missed the ball and at the reached the screen height.  Thus, ending the game.
     */
    public void move() {
        xPosition += xDirection;
        yPosition += yDirection;

        if (getCollisionWithPaddle()) {
            yDirection *= -1;

            if(getBounds().x + (getBounds().width / 2 ) >
                    gamePanel.paddle.getBounds().x + gamePanel.paddle.getBounds().width / 2){
                xDirection = Math.abs(xDirection);

            } else {
                xDirection = Math.abs(xDirection) * -1;
            }
        }

        A: for (int i = 0; i < gamePanel.bricks.brickMaker.length; i++) {
            for (int j = 0; j < gamePanel.bricks.brickMaker[i].length; j++) {
                if (gamePanel.bricks.brickMaker[i][j] > 0) {
                    int xPos = (j * gamePanel.bricks.brickWidth);
                    int yPos = (i * gamePanel.bricks.brickHeight + 80);
                    Rectangle brickBounds =
                            new Rectangle(xPos, yPos, gamePanel.bricks.brickWidth, gamePanel.bricks.brickHeight);

                    if (getBounds().intersects(brickBounds)) {
                        gamePanel.bricks.setBrickValue(0, i, j);

                        if (i == 0) {
                            gamePanel.totalBricks--;
                            gamePanel.score += 6;
                        }
                        if (i == 1) {
                            gamePanel.totalBricks--;
                            gamePanel.score += 5;
                        }
                        if (i == 2) {
                            gamePanel.totalBricks--;
                            gamePanel.score += 4;
                        }
                        if (i == 3) {
                            gamePanel.totalBricks--;
                            gamePanel.score += 3;
                        }
                        if (i == 4) {
                            gamePanel.totalBricks--;
                            gamePanel.score += 2;
                        }
                        if ((gamePanel.getScore() == 360) || (gamePanel.getTotalBricks() == 0)) {
                            gamePanel.isPlaying = false;
                        }
                        if (((xPosition + 29) <= brickBounds.x) ||
                                (xPosition + 1) >= (brickBounds.x + brickBounds.width)) {
                            xDirection = -xDirection;

                        } else {
                            yDirection = -yDirection;
                        }

                        break A;
                    }
                }
            }
        }
        if ((xPosition) <= 0) {
            //the ball is on the left side of the screen
            xDirection = -xDirection;
        }
        if ((yPosition) <= 0) {
            //the ball is at the top of the screen
            yDirection = -yDirection;
        }
        if ((xPosition) >= (gamePanel.getWidth() - Ball.DIAMETER)) {
            //the ball is on the right side of the screen
            xDirection = -xDirection;
        }
        if ((yPosition) > (gamePanel.getHeight() - Ball.DIAMETER)) {
            //the ball is at the bottom of the screen
            gamePanel.isPlaying = false;
        }
    }//end of the move Method
}//end of the Ball Class



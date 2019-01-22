package net.mabdurrahman.src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 * The Paddle Class simulates the paddle for game
 * @author:  MAbdurrahman
 * @date:  19 April 2017
 * @version:  1.0.0
 */
public class Paddle {
    //Instance Variables
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int SCREEN_HEIGHT = (int) SCREEN_SIZE.getHeight();
    private static final int SCREEN_WIDTH = (int) SCREEN_SIZE.getWidth();
    private final Color color = new Color(48, 181, 22);
    private final GamePanel gamePanel;
    protected int yPosition = (int) (SCREEN_HEIGHT * .875);
    protected int xPosition = (int) ((SCREEN_WIDTH / 2) - 75);
    protected int paddleVelocity;
    protected int speed = 13;
    public static final int WIDTH = 150;
    public static final int HEIGHT = 20;

    /**
     * Default Paddle Constructor - Creates an instance of the paddle without a parameter
     */
    public Paddle() {
        this.gamePanel = new GamePanel();

    }//end of the Default Paddle Constructor
    /**
     * Paddle Constructor - Creates an instance of the paddle with one parameter
     */
    public Paddle(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }//end of the Paddle Constructor with one parameter
    /**
     * move Method - Moves the paddle within bounds of the game panel
     */
    public void move() {
        if (((xPosition + paddleVelocity) >= 0) &&
                ((xPosition + paddleVelocity) < (gamePanel.getWidth() - WIDTH))) {

            xPosition += paddleVelocity;
        }
    }//end of the move Method
    /**
     * paint Method - Paints the paddle with the color to the dimensions at the position
     */
    public void paint(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(color);
        g2d.fillRect(xPosition, yPosition, WIDTH, HEIGHT);

    }//end of the paint Method
    /**
     * keyPressed Method - Responds to the event of pressing the left and right arrow keys
     */
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            paddleVelocity = -speed;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddleVelocity = speed;

        }
    }//end of the keyPressed Method
    /**
     * keyReleased Method - Responds to the event of releasing the left or right arrow keys
     */
    public void keyReleased(KeyEvent ke) {
        paddleVelocity = 0;

    }//end of the keyReleased Method
    /**
     * getBounds Method - Returns the rectangle's position at points (x, y) and its dimensions of
     * the width and height.
     * @return Rectangle - Returns the rectangle's position along x, y axis and the dimension of the
     * rectangle's width and height.
     */
    public Rectangle getBounds() {
        return new Rectangle(xPosition, yPosition, WIDTH, HEIGHT);

    }//end of the getBounds Method
    /**
     * getVerticalPosition Method - Gets the vertical position the paddle
     * @return Int - Returns the vertical position of the paddle, which in the gamePanel is the
     * screenHelght by divided by 0.875.  The result is cast to an integer.
     */
    public int getVerticalPosition() {
        return yPosition;

    }//end of the getVerticalPosition Method
}//end of the Paddle Class


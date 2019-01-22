package net.mabdurrahman.src;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
 * The BrickMaker Class makes the simulated bricks for the game
 * @author:  MAbdurrahman
 * @date:  19 April 2017
 * @version:  1.0.0
 */
public class BrickMaker {
    //Instance Variables
    private final Color redColor = new Color(194, 40, 10);
    private final Color blueColor = new Color(0, 128, 255);
    private final Color purpleColor = new Color (128, 0, 255);
    private final Color orangeColor = new Color(255, 128, 0);
    private final Color greenColor = new Color(32, 255, 32);
    private final Color blackColor = new Color(54, 54, 52);
    protected int brickMaker[][];
    protected int row;
    protected int col;
    protected int brickWidth;
    protected int brickHeight;
    protected int arc = 10;

    /**
     * BrickMaker Constructor - Creates an instance of the BrickMaker
     * @param  - the number of rows
     * @param - the number of columns
     */
    public BrickMaker(int row, int col) {
        this.row = row;
        this.col = col;
        brickMaker = new int[row][col];

        for (int i = 0; i < brickMaker.length; i++) {
            for (int j = 0; j < brickMaker[i].length; j++) {
                brickMaker[i][j] = 1;
            }
        }

        brickWidth = 79;
        brickHeight = 20;
        arc = 15;

    }//end of the BrickMaker Constructor
    /**
     * paint Method - Paints the brick the brickWidth and brickHeight at the X and Y positions
     * @param  - the graphic two dimensional context
     */
    public void paint(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        for (int i = 0; i < brickMaker.length; i++) {
            for (int j = 0; j < brickMaker[i].length; j++) {
                if (brickMaker[i][j] > 0) {
                    if (i == 0) {
                        g2d.setColor(redColor);
                        g2d.fillRoundRect((j * brickWidth + 5), (i * brickHeight + 80), brickWidth, brickHeight, arc, arc);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.setColor(blackColor);
                        g2d.drawRoundRect((j * brickWidth + 5), (i * brickHeight + 80), brickWidth, brickHeight, arc, arc);
                    }
                    if (i == 1) {
                        g2d.setColor(blueColor);
                        g2d.fillRoundRect((j * brickWidth + 5), (i * brickHeight + 80), brickWidth, brickHeight, arc, arc);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.setColor(blackColor);
                        g2d.drawRoundRect((j * brickWidth + 5), (i * brickHeight + 80), brickWidth, brickHeight, arc, arc);
                    }
                    if (i == 2) {
                        g2d.setColor(purpleColor);
                        g2d.fillRoundRect((j * brickWidth + 5), (i * brickHeight + 80), brickWidth, brickHeight, arc, arc);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.setColor(blackColor);
                        g2d.drawRoundRect((j * brickWidth + 5), (i * brickHeight + 80), brickWidth, brickHeight, arc, arc);
                    }
                    if (i == 3) {
                        g2d.setColor(orangeColor);
                        g2d.fillRoundRect((j * brickWidth + 5), (i * brickHeight + 80), brickWidth, brickHeight, arc, arc);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.setColor(blackColor);
                        g2d.drawRoundRect((j * brickWidth + 5), (i * brickHeight + 80), brickWidth, brickHeight, arc, arc);
                    }
                    if (i == 4 ) {
                        g2d.setColor(greenColor);
                        g2d.fillRoundRect((j * brickWidth + 5), (i * brickHeight + 80), brickWidth, brickHeight, arc, arc);
                        g2d.setStroke(new BasicStroke(3));
                        g2d.setColor(blackColor);
                        g2d.drawRoundRect((j * brickWidth + 5), (i * brickHeight + 80), brickWidth, brickHeight, arc, arc);
                    }
                }
            }
        }
    }//end of the paint Method
    /**
     * setBrickValue Method - Sets the value to the brick at row and column. This determines whether
     * the brick is visible or not
     * @param - the value to be set; if value is one, brick is visible or if value is zero, brick
     * is invisible
     * @param - the row of the brick
     * @param  - the column of the brick
     */
    public void setBrickValue(int value, int row, int col) {
        brickMaker[row][col] = value;

    }//end of the setBrickValue Method
    /**
     * getBounds - Gets the rectangular position of the brick
     * @param  - the row of the brick
     * @param  - the column of the brick
     * @return Rectangle - Returns a rectangle with x position, y position, width, and height.
     */
    public Rectangle getBounds(int row, int col) {
        int brickXPosition = (col * brickWidth);
        int brickYPosition = (row * brickHeight + 80);

        return new Rectangle(brickXPosition, brickYPosition, brickWidth, brickHeight);

    }//end of the getBounds Method
}//end of the BrickMaker Class



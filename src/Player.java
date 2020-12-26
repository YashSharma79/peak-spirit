/*
 * Translation for the player movement
 */


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JOptionPane;

//Check and perform everything in move
public class Player
{
	private World world = new World();
	Random generator = new Random();
	private Rectangle playerRect;
	protected int xDirection,yDirection;
	private int fireX,fireY;
	private int WIDTH = 10,HEIGHT = 10;
	private boolean fire;
	Level1 dynamite;
	/*   public Player(GamePanel game)
    {
    	this.game  = game;
    }*/

	public Player(World world)
	{
		this.world = world;
		dynamite = new Level1();

		//      playerImg = new ImageIcon("~/Pictures/RockClimbing").getImage();
		playerRect = new Rectangle(10,0,10,10);

	}


	public void update()
	{
		move();
	}

	//TRANSLATION used in move() and dynamite()
	public void move()
	{
		if(collision())
			playerRect.y += world.wave;

		//wormhole
		if(interstellar())
			playerRect.x+=550;

		//Move horizontal
		if(playerRect.x + xDirection +  world.wavex > 0 && playerRect.x + xDirection < GamePanel.GWIDTH - 10)
			playerRect.x += xDirection + world.wavex;

		//Move vertical
		if(playerRect.y + yDirection > 0 && playerRect.y + yDirection < 890 )
			playerRect.y += yDirection;	

		//Player goes above the top of the screen
		if(playerRect.y < -12)
		{
			world.gameOver();
		}

		//Successfully crossed
		if(playerRect.x >= 1155)
			levelComplete();
	}

	public void dynamite()
	{
		if(contact())
		{
			Level1.show = true;
			dynamite.repaint();
			dynamite.gameOver();
		}

		//Try to avoid direct values. Also @ move()
		if(playerRect.x + xDirection > 0 && playerRect.x + xDirection < GamePanel.GWIDTH - 10)
			playerRect.x += xDirection;

		if(playerRect.y + yDirection > 0 && playerRect.y + yDirection < 890 )
			playerRect.y += yDirection;	

		if(playerRect.x >= 1188)
		{
			Level1.show = true;
			dynamite.repaint();
			levelComplete();
		}
	}


	private boolean collision() {
		return world.getBounds().intersects(getBounds());
	}

	private boolean interstellar() {
		if(world.time)
		{
			if((playerRect.x >= 565 && playerRect.x <= 600) && playerRect.y >= 500 && playerRect.y <= 550 )
				return true;
		}

		return false;	
	}

	private boolean contact() {
		for(int i = 0;i<60;i++)
		{
			if(playerRect.intersects(Level1.re[i]) )
				return true;
		}

		return false;
	}

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			xDirection = GamePanel.speed;

		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			yDirection = GamePanel.speed;

		else if(e.getKeyCode() == KeyEvent.VK_LEFT)	
			xDirection = - GamePanel.speed;

		else if(e.getKeyCode() == KeyEvent.VK_UP)
			yDirection = - GamePanel.speed;

		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
			fire();

	}

	public void fire()
	{
		fireX += 10;
		fireY += 10;
		fire = true;
	}

	public void keyReleased(KeyEvent e)
	{
		xDirection = 0;
		yDirection = 0;

		if(e.getKeyCode() == KeyEvent.VK_SPACE)
			fire = false;
	}

	public void keyTyped(KeyEvent e)
	{

	}

	public Rectangle getBounds() {
		return new Rectangle(playerRect.x,playerRect.x,100,100);
	}


	public void paint(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(playerRect.x,playerRect.y,WIDTH,HEIGHT);
		if(fire)
			g.drawLine(fireX,fireY,fireX+5,fireY+5);	
	}

	public void levelComplete()
	{
		JOptionPane.showMessageDialog(null,"The Journey will now begin..","Well Done!", JOptionPane.YES_NO_OPTION);
		dynamite.repaint();
		System.exit(0);
	}
}
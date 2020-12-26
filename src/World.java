/*
 * Scaling for waves 
 */

import java.awt.*;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;
public class World
{
	public Rectangle[] blocks; //useful in collision detection

	public int x,y,xDirection,yDirection,big,wave,wavex;

	public boolean time;
	//Map navigation
	static final int PAN_UP = 0,PAN_DOWN = 1,PAN_LEFT = 2,PAN_RIGHT = 3;

	public final int arrayNum =  100; 
	private long thread_count = 0;
	private Random generator = new Random();
	private Color waveColor = new Color(0,50,150);

	int arcX,arcY,arcW,arcH;
	Timer timer;
	int count = 30; 
	int delay = 1000;
	/*ActionListener actionToPreform = new ActionListener() 
{  

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(count > 0)
            count--; 
	}
}
timer = new Timer(delay, actionToPreform);
timer.start();
	 */

	public World()
	{


		blocks = new Rectangle[arrayNum];
		//    blockImg = new ImageIcon("/Users/Pictures/Sky.jpg").getImage();
		//     loadArrays();
		//        isSolid = new boolean[arrayNum];
		//      loadArrays();
	}

	public void moveMap()
	{
		for(Rectangle r: blocks)
		{
			r.x += xDirection;
			r.y += yDirection;
		}
	}

	int xa = 1;
	int ya = 1;

	public void paint(Graphics2D g) {
		thread_count++;
		//Color sky = new Color(151,255, 255);
		wavex = 0;

		g.setColor(new Color(255,235,205));
		g.fillRect(0,0,60,900);
		g.fillRect(1100,0,100,900);
		g.setColor(Color.CYAN);
		g.fillRect(60,0,1080,900);	
		g.setColor(waveColor);

		if(thread_count%10 == 0)
		{
			if(generator.nextInt(100) > 70 )
			{
				big *= 500; //Wave scaled 500 times
				wave = -14;
				wavex = -8;
			}

			else
			{
				big = 1;
				wave = -5;
			}

			arcX = 80 + generator.nextInt(GamePanel.GWIDTH - 60);
			arcY = generator.nextInt(GamePanel.GHEIGHT);
			arcW = big+generator.nextInt(70);
			arcH = GamePanel.GHEIGHT - arcY;

			g.fillArc(arcX,arcY,arcW,arcH,0,180);
		}

		if(thread_count%500 == 0)
			g.fillArc(arcX,arcY,arcW,arcH,270,90);

		if(thread_count > 800 && thread_count < 1000)
		{
			time = true;
			g.setColor(Color.DARK_GRAY);
			g.fillOval(550,500,50,50);
		}

		else
			time = false;
		//		System.out.println(thread_count);

	}

	public Rectangle getBounds() {
		return new Rectangle(arcX,arcY,arcW,arcH);
	}

	public void gameOver() {
		JOptionPane.showMessageDialog(null,"Swallowed in the sea..","Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(0);
	}
}
import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private Image dbImage;
	private Graphics dbg;
	static int speed = 1;//how many pixels to move in one keystroke

	static final int GWIDTH = 1200,GHEIGHT = 900;
	static final Dimension gameDis = new Dimension(GWIDTH,GHEIGHT); //Encapsulating width and height into one object
	//Game variables
	private Thread game;
	private volatile boolean running = false;

	World world;
	Player cr7;
	Level1 dynamites;
	Mountain himalaya;

	//Need to call one by one as one level completes) Also @ gameUpdate() and paint() 
	public GamePanel()
	{
		world = new World();
		cr7 = new Player(world);
		//	dynamites = new Level1();
		//himalaya = new Mountain();

		setPreferredSize(gameDis);
		setFocusable(true);
		requestFocusInWindow();

		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				cr7.keyPressed(e);
			}

			public void keyReleased(KeyEvent e)
			{
				cr7.keyReleased(e);
			}

			public void keyTyped(KeyEvent e)
			{

			}
		}              
				);


	}

	public void run()
	{
		while(running)
		{
			gameUpdate();
			gameRender(); //Image creator
			paintScreen();
		}
	}

	public void gameUpdate()
	{
		if(running && game!=null)
		{
			//	cr7.dynamite();
			//	himalaya.move();
			cr7.update();

		}
	}

	private void gameRender()
	{
		if(dbImage == null)
		{
			dbImage = createImage(GWIDTH,GHEIGHT);
			if(dbImage == null)
			{
				System.err.println("Image is still null");
				return;
			}

			else
				dbg = dbImage.getGraphics();
		}

		//Clear the screen
		dbg.setColor(Color.WHITE);
		dbg.fillRect(1,1,GWIDTH,GHEIGHT);

		//draw game elements
		paint(dbg);
	}

	//Draw all game in this method
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		world.paint(g2d);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//dynamites.paint(g2d);
		//himalaya.paint(g2d);
		cr7.paint(g2d);

		//g2d.setFont(new Font("Verdana", Font.BOLD, 30));
	}

	private void paintScreen()
	{
		Graphics g;
		try
		{
			g = this.getGraphics();
			if(dbImage != null  && g!=null)
			{
				g.drawImage(dbImage,0,0,null);
			}
			Toolkit.getDefaultToolkit().sync();//Sync graphics
			g.dispose();
		}

		catch(Exception e)
		{
			System.err.println(e);
		}
	}

	public void addNotify()
	{
		super.addNotify();
		startGame();
	}

	private void startGame()
	{
		if(game == null || !running)
		{
			game = new Thread(this);
			game.start();
			running = true;
		}
	}

	public void stopGame()
	{
		if(running)
			running = false;
	}

}
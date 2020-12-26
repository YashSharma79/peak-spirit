import java.awt.*;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Level1 extends JPanel
{
	Random generator = new Random();
	public static  Rectangle[] re = new Rectangle[60];
	public static boolean show;

	//Find what to write in a constructor and what not
	public Level1()
	{
		setBackground(Color.GREEN);

		//Dynamites all over
		for(int i = 0;i<50; i++)
		{
			re[i] = new Rectangle(generator.nextInt(GamePanel.GWIDTH),generator.nextInt(GamePanel.GHEIGHT),5,5);
		}

		//Dynamites at the top
		for(int i = 50;i<55; i++)
		{
			re[i] = new Rectangle(30 + generator.nextInt(GamePanel.GWIDTH),generator.nextInt(5),5,5);
		}

		//Dynamites at the bottom
		for(int i = 55;i<60; i++)
		{
			re[i] = new Rectangle(generator.nextInt(GamePanel.GWIDTH),892 + generator.nextInt(5),5,5);
		}
	}

	public void paint(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.GREEN);
		g.fillRect(0,0,GamePanel.GWIDTH,GamePanel.GHEIGHT);
		g.setColor(Color.BLUE);

		//Not working properly
		
			for(Rectangle r:re)
				g.draw(r);
	}	

	public void gameOver() {
		JOptionPane.showMessageDialog(null,"Game Over","Not so subtle", JOptionPane.YES_NO_OPTION);
		repaint();
		System.exit(0); //Terminate JVM
	}
}
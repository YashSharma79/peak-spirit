import java.util.Random;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


@SuppressWarnings("serial")
public class Mountain extends JPanel
{
	private Random generator = new Random();
	private int x,y;
	private Color m_color;

	public Mountain()
	{
		m_color = Color.BLACK;
		setBackground(Color.BLUE);
	}

	public void paint(Graphics g)
	{

		Graphics2D g2d = (Graphics2D) g;
		//world.paint(g2d);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setBackground(Color.BLUE);
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0,0,GamePanel.GWIDTH, GamePanel.GHEIGHT);
		g2d.setColor(m_color);
		//g.drawLine(x1,y1,x2,y2);
		g.drawLine(200,600,250,575);
		g.drawLine(250,575,275,500);
		g.drawLine(275,500,290,475);

		g.drawLine(290,475,325,375);

		g.drawLine(325,375,400,300);
		g.drawLine(400,300,470,295);

		g.drawLine(470,295,480,240);

		g.drawLine(480,240,600,70);
		g.drawLine(600,70,660,60);

		g.drawLine(660,60,720,70);

		g.drawLine(720,70,900,150);

		g.drawLine(900,150,925,300);
		g.setColor(Color.GRAY);
		g2d.fillOval(x,y, 30+generator.nextInt(10),30+generator.nextInt(10));

		//if(fire)

		//g.fillArc(350,200,400,560,120,240);
	}


	public void move() {
		if(x<0)
		{
			x = 650;
			y = 30;
		}
		x -= 7; 
		y+=5;
	}

}


import javax.swing.JFrame;

public class Main extends JFrame
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws InterruptedException
	{
		JFrame game = new JFrame("Peak Spirit");
		GamePanel gp = new GamePanel();
		game.add(gp);
		game.setSize(1200,900);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);

		while (true) {
			gp.gameUpdate();
			gp.repaint();
			Thread.sleep(30);
		}
	}
}
package warcaby.gra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Plansza extends JPanel{

	private static final long serialVersionUID = 1L;

	public Plansza(){
		setDoubleBuffered(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(10, 10, 500, 500);
		g2d.setColor(new Color(255,255,255));
		for(int i=0 ; i<8 ; i++)
		{
			for(int j=0 ; j<8 ; j++)
			{
				g2d.fillRect(12 + 62*i, 12 + 62*j,59,59);
			}
		}
	}
}


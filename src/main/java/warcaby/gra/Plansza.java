package warcaby.gra;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Plansza extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private int tablicaPionkow[][];
	private Integer szerokosc = 650;
	private Integer wysokosc = 600;
	private JButton start, wyjscie;
	private JLabel etykieta;
	private Plansza plansza;

	public Plansza() {
		super("Warcaby");
		tablicaPionkow = new int[8][8];
		setSize(szerokosc, wysokosc);
		setLocationRelativeTo(plansza);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		etykieta = new JLabel("Warcaby");
		etykieta.setBounds(540, 10, 100, 20);
		etykieta.setFont(new Font("SansSerif", Font.BOLD, 16));
		add(etykieta);

		start = new JButton("Start");
		start.setBounds(520, 80, 100, 20);
		add(start);
		start.addActionListener(this);

		wyjscie = new JButton("Wyjœcie");
		wyjscie.setBounds(520, 110, 100, 20);
		add(wyjscie);
		wyjscie.addActionListener(this);

		setVisible(true);

	}

	protected void ustaw() {
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 8; i++) {
				if ((i + j) % 2 == 0)
					tablicaPionkow[i][j] = 1;
			}

		for (int j = 5; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				if ((i + j) % 2 == 0)
					tablicaPionkow[i][j] = 2;
			}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(10, 40, 505, 505);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0)
					g2d.setColor(new Color(255, 120, 0));
				else
					g2d.setColor(new Color(61, 43, 31));
				g2d.fillRect(11 + 63 * i, 41 + 63 * j, 62, 62);
			}
		}
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				if (tablicaPionkow[i][j] == 1)
					g2d.setColor(Color.BLACK);
				else if (tablicaPionkow[i][j] == 2)
					g2d.setColor(Color.WHITE);

				if (tablicaPionkow[i][j] == 1 || tablicaPionkow[i][j] == 2)
					g2d.fillOval(17 + 63 * i, 47 + 63 * j, 50, 50);
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == start) {
			ustaw();
			repaint();
		}
		if (source == wyjscie) {
			int decyzja = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjsc?", "Confirm Dialog",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (decyzja == JOptionPane.YES_OPTION) {
				dispose();
			} 
		}

	}
}

/*
do menu:  UIManager.setLookAndFeel(
UIManager.getSystemLookAndFeelClassName());
*/
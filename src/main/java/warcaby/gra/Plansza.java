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
import javax.swing.JTextPane;

public class Plansza extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	static int tablicaPionkow[][];
	private Integer szerokosc = 650;
	private Integer wysokosc = 600;
	private JButton start, wyjscie, opcje, multiplayer;
	private JLabel napisGlowny, napisWersja;
	private Plansza plansza;
	private JTextPane poleIP;
	private KafelekInformacyjny[][] kafelki;
	protected static Color kolorPlanszy1;
	protected static Color kolorPlanszy2;
	protected static Color kolorPionkow1;
	protected static Color kolorPionkow2;

	public Plansza() {
		super("Warcaby");
		tablicaPionkow = new int[8][8];
		kafelki = new KafelekInformacyjny[8][8];

		setSize(szerokosc, wysokosc);
		setLocationRelativeTo(plansza);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		poleIP = new JTextPane();
		poleIP.setSize(100, 20);

		napisGlowny = new JLabel("WARCABY");
		napisGlowny.setBounds(535, 15, 140, 30);
		napisGlowny.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		napisGlowny.setForeground(Color.BLUE);
		add(napisGlowny);

		napisWersja = new JLabel("Wersja: 0.5");
		napisWersja.setBounds(545, 55, 140, 20);
		napisWersja.setFont(new Font("Calibri", Font.BOLD, 14));
		napisWersja.setForeground(Color.RED);
		add(napisWersja);

		start = new JButton("Start");
		start.setBounds(530, 100, 100, 20);
		start.setFont(new Font("Calibri", Font.BOLD, 13));
		add(start);
		start.addActionListener(this);

		multiplayer = new JButton("Multiplayer");
		multiplayer.setBounds(530, 130, 100, 20);
		multiplayer.setFont(new Font("Calibri", Font.BOLD, 13));
		add(multiplayer);
		multiplayer.addActionListener(this);

		opcje = new JButton("Opcje");
		opcje.setBounds(530, 160, 100, 20);
		opcje.setFont(new Font("Calibri", Font.BOLD, 13));
		add(opcje);
		opcje.addActionListener(this);

		wyjscie = new JButton("Wyjście");
		wyjscie.setBounds(530, 190, 100, 20);
		wyjscie.setFont(new Font("Calibri", Font.BOLD, 13));
		add(wyjscie);
		wyjscie.addActionListener(this);

		kolorPlanszy1 = new Color(255, 120, 0);
		kolorPlanszy2 = new Color(61, 43, 31);
		kolorPionkow1 = Color.BLACK;
		kolorPionkow2 = Color.WHITE;

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				kafelki[i][j] = new KafelekInformacyjny(this, i, j);
				add(kafelki[i][j]);
			}
		}
		setVisible(true);

	}

	protected void ustaw() {
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				tablicaPionkow[i][j] = 0;
			}
		for (int j = 0; j < 3; j++)
			for (int i = 0; i < 8; i++) {
				if ((i + j) % 2 == 0)
					tablicaPionkow[i][j] = 1;
			}

		for (int j = 5; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				if ((i + j) % 2 == 0)
					tablicaPionkow[i][j] = 2;
			}
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
					g2d.setColor(kolorPlanszy1);
				else
					g2d.setColor(kolorPlanszy2);
				g2d.fillRect(11 + 63 * i, 41 + 63 * j, 62, 62);
			}
		}
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				if (tablicaPionkow[i][j] == 3 || tablicaPionkow[i][j] == 4 || tablicaPionkow[i][j] == 5){
					g2d.setColor(Color.YELLOW);
					g2d.drawRect(kafelki[i][j].x+3,kafelki[i][j].y+25, kafelki[i][j].width, kafelki[i][j].height);
				}
				}
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				if (tablicaPionkow[i][j] == 1 || tablicaPionkow[i][j] == 3 )
					g2d.setColor(kolorPionkow1);
				else if (tablicaPionkow[i][j] == 2 || tablicaPionkow[i][j] == 4)
					g2d.setColor(kolorPionkow2);

				if (tablicaPionkow[i][j] !=0 && tablicaPionkow[i][j] != 5)
					g2d.fillOval(17 + 63 * i, 47 + 63 * j, 50, 50);
			}
		g2d.setColor(Color.BLACK);
		g2d.drawRect(550, 493, 50, 50);
		g2d.setFont(new Font("Arial", Font.BOLD, 12));
		g2d.drawString("POLE", 560, 515);
		g2d.drawString(KafelekInformacyjny.tmp, 570, 535);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == start) {
			ustaw();
			repaint();
		}
		if (source == multiplayer) {
			String adresIP = new String();
			int decyzja = JOptionPane.showConfirmDialog(null, "Chcesz rozpoczac rozgrywke sieciow¹?", "Confirm Dialog",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (decyzja == JOptionPane.YES_OPTION) {
				adresIP = JOptionPane.showInputDialog(poleIP, "Wpisz adres IP");
				try {
					if (adresIP.isEmpty()) {
						JOptionPane.showMessageDialog(null, "B³êdny adres lub pusty!");
					} else {
						JOptionPane.showMessageDialog(null, "Rozpoczynamy rozgrywke!");
						ustaw();
						repaint();
					}
				} catch (Exception ignore) {
				}
			}
		}

		if (source == opcje) {
			new Opcje(this);
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

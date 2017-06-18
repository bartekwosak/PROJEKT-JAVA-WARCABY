package warcaby.serwer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/** Klasa Opcje zawiera zmienne oraz metody odpowiedzialne za prawidłowe wyświetlanie okienka opcji gry oraz ich działanie. 
 * W klasie istnieją zmienne będące obiektami klas z pakietu Swing: JFrame, JLabel, JButton oraz JComboBox. Ponadto
 * występuje konstruktor oraz przesłonięta metoda actionPerformed odpowiedzialna za prawidłowe akcje po wciśnięciu 
 * danego przycisku lub innego komponentu. 
 * Klasa implementuje interfejs ActionListener odpowiadający za określenie akcji po wciśnięciu np. danego przycisku.
 * Ponadto dziedziczy ona po klasie JFrame - odpowiedzialnej za tworzenie okna opcji gry.
 * @author Bartłomiej Osak, Tomasz Pasternak
 * @see ActionListener
 * @see JFrame
 */
public class Opcje extends JFrame implements ActionListener {

	/** Zmienna określająca unikalny numer w celu serializacji. */
	private static final long serialVersionUID = 1L;
	/** Zmienna JFrame odwołująca się do okna głównej aplikacji. Wykorzystywana podczas zmiany koloru planszy/pionków w opcjach. */
	protected static JFrame parent; 
	 /** Zmienna JFrame okreslająca okno z opcjami gry. */
	protected JFrame okno; 
	/** Zmienna JLabel używana w celu tworzenia napisu 'Opcje Gry'. */
	protected JLabel napisGlowny;
	/** Zmienna JLabel używana w celu tworzenia napisu 'Styl aplikacji'. */
	protected JLabel napisStyl; 
	/** Zmienna JLabel używana w celu tworzenia napisu 'Wygląd planszy'. */
	protected JLabel napisZmianaWygladu; 
	/** Zmienna JLabel używana w celu tworzenia napisu 'Wygląd pionków'. */
	protected JLabel napisZmianaPionkow; 
	/** Zmienna JButton odpowiedzialna za przycisk w sekcji 'Wygląd planszy' z nazwą 'Domyślny' - ustawia styl planszy na domyślny. */
	protected JButton stylDomyslny; 
	/** Zmienna JButton odpowiedzialna za przycisk w sekcji 'Wygląd planszy' z nazwą 'Beżowo - biała' - ustawia styl planszy na beżowo-białą. */
	protected JButton stylPlanszy1; 
	/** Zmienna JButton odpowiedzialna za przycisk w sekcji 'Wygląd planszy' z nazwą 'Niebiesko - żółta' - ustawia styl planszy na niebiesko-żółtą. */
	protected JButton stylPlanszy2; 
	/** Zmienna JButton odpowiedzialna za przycisk w sekcji 'Wygląd pionków' z nazwą 'Czerwono - żółte' - ustawia styl pionków na czerwono-żółte. */
	protected JButton stylPionkow1; 
	/** Zmienna JButton odpowiedzialna za przycisk w sekcji 'Wygląd pionków' z nazwą 'Zielono - szare' - ustawia styl pionków na zielono - szare. */
	protected JButton stylPionkow2; 
	/** Zmienna JButton odpowiedzialna za przycisk w sekcji 'Wygląd pionków' z nazwą 'Domyślny' - ustawia styl pionków na domyślny. */
	protected JButton stylDomyslnyPionkow; 
	/** Zmienna JComboBox odpowiedzialna za listę z możliwością wyboru ogólnego wyglądu aplikacji - zmiana koloru okienka. */
	protected JComboBox<Object> style; 

	/** Konstruktor klasy Opcje przyjmujący jeden argument.
	 * W konstruktorze definiowane są: rozmiar okna z opcjami gry, jego pozostałe właściwości. Ponadto 
	 * zdefiniowano właściwości napisów poszczególnych sekcji na komponencie JLabel tj. font, rozmiar, wymiary, kolor. 
	 * Zdefiniowano listę wysuwaną umożlwiającą wybór stylu aplikacji. Określono rozmiar, styl.
	 * Ponadto zdefiniowano właściwości poszczególnych przycisków na komponencie JButton - ustalono font, napis oraz wymiar.
	 * Po zdefiniowaniu każdego z użytych komponentów dodano go do okna z opcjami głównymi metodą {@link JFrame#add(java.awt.Component)}.
	 * @param oknoF - argument typu JFrame, czyli okno - główne okno aplikacji.
	 * @see JFrame
	 * @see JLabel
	 * @see JComboBox
	 * @see JButton
	 * @see JFrame#add(java.awt.Component)
	 */
	public Opcje(JFrame oknoF) {
		parent = oknoF;
		okno = new JFrame();
		okno.setTitle("Opcje");
		okno.setSize(650, 300);
		okno.setLocationRelativeTo(okno);
		okno.setLayout(null);
		okno.setResizable(false);

		napisGlowny = new JLabel("OPCJE GRY");
		napisGlowny.setBounds(265, 10, 120, 25);
		napisGlowny.setFont(new Font("Arial", Font.BOLD, 18));
		napisGlowny.setForeground(Color.BLUE);
		okno.add(napisGlowny);

		napisStyl = new JLabel("Styl aplikacji");
		napisStyl.setBounds(10, 70, 100, 20);
		napisStyl.setFont(new Font("Calibri", Font.BOLD, 14));
		napisStyl.setForeground(Color.RED);
		okno.add(napisStyl);

		style = new JComboBox<Object>(new String[] { "Domyślny", "Metal", "Motif", "Nimbus" });
		style.setBounds(225, 90, 200, 20);
		okno.add(style);
		style.addActionListener(this);

		napisZmianaWygladu = new JLabel("Wygląd planszy");
		napisZmianaWygladu.setBounds(10, 130, 120, 20);
		napisZmianaWygladu.setFont(new Font("Calibri", Font.BOLD, 14));
		napisZmianaWygladu.setForeground(Color.RED);
		okno.add(napisZmianaWygladu);

		stylDomyslny = new JButton("Domyślna");
		stylDomyslny.setBounds(100, 160, 150, 20);
		okno.add(stylDomyslny);
		stylDomyslny.addActionListener(this);

		stylPlanszy1 = new JButton("Beżowo - biała");
		stylPlanszy1.setBounds(260, 160, 150, 20);
		okno.add(stylPlanszy1);
		stylPlanszy1.addActionListener(this);

		stylPlanszy2 = new JButton("Niebiesko - żółta");
		stylPlanszy2.setBounds(420, 160, 150, 20);
		okno.add(stylPlanszy2);
		stylPlanszy2.addActionListener(this);

		napisZmianaPionkow = new JLabel("Wygląd pionków");
		napisZmianaPionkow.setBounds(10, 200, 120, 20);
		napisZmianaPionkow.setFont(new Font("Calibri", Font.BOLD, 14));
		napisZmianaPionkow.setForeground(Color.RED);
		okno.add(napisZmianaPionkow);

		stylDomyslnyPionkow = new JButton("Domyślny");
		stylDomyslnyPionkow.setBounds(100, 230, 150, 20);
		okno.add(stylDomyslnyPionkow);
		stylDomyslnyPionkow.addActionListener(this);

		stylPionkow1 = new JButton("Czerwono - żółte");
		stylPionkow1.setBounds(260, 230, 150, 20);
		okno.add(stylPionkow1);
		stylPionkow1.addActionListener(this);

		stylPionkow2 = new JButton("Zielono - szare");
		stylPionkow2.setBounds(420, 230, 150, 20);
		okno.add(stylPionkow2);
		stylPionkow2.addActionListener(this);

		okno.setVisible(true);
	}

	/** Przesłonięta metoda służąca do określania zachowania aplikacji po kliknięciu na dany komponent przez użytkownika.
	 * W metodzie określone są działania dla przycisków oraz listy wysuwanej. Ponadto po wciśnięciu niektórych z przycisków
	 * wyświetlane są okna dialogowe za pomocą JOptionPane. W przypadku zmiany wyglądu aplikacji lub pionków lub planszy
	 * wywoływana jest metoda klasy {@link JFrame#repaint()}, w celu nałożenia zmian na wygląd poszczególnych komponentów.
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 * @see JOptionPane
	 * @see JOptionPane#showMessageDialog(java.awt.Component, Object)
	 * @see JFrame#repaint()
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == stylDomyslny) {
			JOptionPane.showMessageDialog(null, "Zmieniono!");
			Plansza.kolorPlanszy1 = new Color(255, 120, 0);
			Plansza.kolorPlanszy2 = new Color(61, 43, 31);
			parent.repaint();
		}
		if (source == stylPlanszy1) {
			JOptionPane.showMessageDialog(null, "Zmieniono!");
			Plansza.kolorPlanszy1 = new Color(194, 178, 128);
			Plansza.kolorPlanszy2 = new Color(255, 255, 255);
			parent.repaint();
		}
		if (source == stylPlanszy2) {
			JOptionPane.showMessageDialog(null, "Zmieniono!");
			Plansza.kolorPlanszy1 = new Color(98, 98, 251);
			Plansza.kolorPlanszy2 = new Color(254, 254, 142);
			parent.repaint();
		}
		if (source == stylDomyslnyPionkow) {
			JOptionPane.showMessageDialog(null, "Zmieniono!");
			Plansza.kolorPionkow1 = Color.BLACK;
			Plansza.kolorPionkow2 = Color.WHITE;
			parent.repaint();
		}
		if (source == stylPionkow1) {
			JOptionPane.showMessageDialog(null, "Zmieniono!");
			Plansza.kolorPionkow1 = Color.RED;
			Plansza.kolorPionkow2 = Color.YELLOW;
			parent.repaint();
		}
		if (source == stylPionkow2) {
			JOptionPane.showMessageDialog(null, "Zmieniono!");
			Plansza.kolorPionkow1 = Color.GREEN;
			Plansza.kolorPionkow2 = Color.DARK_GRAY;
			parent.repaint();
		}

		if (source == style) {
			String s = (String) style.getSelectedItem();
			if (s == "Domyślny") {
				parent.getContentPane().setBackground(null);
				parent.repaint();
			}
			if (s == "Metal") {
				parent.getContentPane().setBackground(new Color(230, 230, 254));
				parent.repaint();
			}
			if (s == "Motif") {
				parent.getContentPane().setBackground(new Color(219, 247, 151));
				parent.repaint();
			}
			if (s == "Nimbus") {
				parent.getContentPane().setBackground(new Color(254, 254, 142));
				parent.repaint();
			}
		}
	}

}
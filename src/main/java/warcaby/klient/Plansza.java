package warcaby.klient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/** Klasa Plansza zawiera deklaracje oraz definicje wszystkich komponentów tworzących okno główne aplikacji.
 * W klasie zdefiniowano wygląd ogólny okna gry, wygląd planszy, której wygląd można zmienić poprzez opcje gry - {@link warcaby.klient.Opcje}.
 * Ponadto w klasie zadeklarowano zmienne potrzebne do nawiązania połączenia sieciowego z drugim graczem - tu z serwerem. 
 * W klasie ustawiane są również początkowe współrzędne tablicy pionków na szachownicy. 
 * Ponadto określono działanie przycisków - w szczególności przycisk 'Multiplayer' (zdefiniowano wątek, który odczytuje 
 * obiekt ze strumienia umożliwiającego zapis danych do gniazda (w tym przypadku tablica pionków - {@link Plansza#tablicaPionkow}.
 * Klasa implementuje interfejs ActionListener odpowiadający za określenie akcji po wciśnięciu np. danego przycisku.
 * Ponadto implementowany jest interfejs Serializable w celu serializacji obiektu wysyłanego poprzez połączenie sieciowe TCP.
 * Klasa dziedziczy po JFrame - klasie tworzącej główne okno aplikacji.
 * @author Bartłomiej Osak, Tomasz Pasternak
 * @see Serializable
 * @see ActionListener
 * @see JFrame
 */
public class Plansza extends JFrame implements ActionListener, Serializable {
	
	/** Zmienna określająca unikalny numer w celu serializacji. */
	private static final long serialVersionUID = 1L;
	/** Tablica dwuwymiarowa typu int przechowująca współrzędne pionków na szachownicy. */
	protected static int tablicaPionkow[][];
	/** Zmienna typu Integer określająca szerokość okna głównego aplikacji. */
	private Integer szerokosc = 650;
	/** Zmienna typu Integer określająca wysokość okna głównego aplikacji. */
	private Integer wysokosc = 600;
	/** Zmienna JButton odpowiedzialna za przycisk 'Start' odpowiedzialny za uruchomienie rozgrywki lokalnej (bez połączenia sieciowego). 
	 * Aktywacja powoduje rozpoczęcie rozgrywki bez połączenia sieciowego. Gra odbywa się na jednym komputerze oraz na jednym oknie aplikacji 
	 * (bez znaczenia czy jest to okno gry Klienta czy Serwera). Gra może być kontynuuowana przez dwóch graczy lub przez jednego w celu treningu, 
	 * wszystko zgodnie z preferencjami użytkownika. 
	 * */
	private JButton start;
	/** Zmienna JButton odpowiedzialna za przycisk 'Wyjście' odpowiedzialny za wyjście z aplikacji.
	 * W przypadku gry lokalnej (bez połączenia sieciowego) wystąpi okno dialogowe z komunikatem, czy użytkownik na pewno chce wyjść
	 * z aplikacji w celu zabezpieczenia przed losowym zamknięciem aplikacji na wskutek błędnej decyzji użytkownika.
	 * W przypadku połączenia sieciowego zamykane są strumienie oraz gniazda serwerowe oraz niepołączone. 
	 * */
	private JButton wyjscie; 
	/** Zmienna JButton odpowiedzialna za przycisk 'Opcje' odpowiedzialny za uruchomienie opcji gry.
	 * @see warcaby.klient.Opcje 
	 */
	private JButton opcje; 
	/** Zmienna JButton odpowiedzialna za przycisk 'Multiplayer' odpowiedzialny za uruchomienie rozgrywki sieciowej.
	 *  Aktywacja powoduje wyszukiwanie dostępnego serwera - w przypadku powodzenia operacji odczytywany jest
	 *  odbierany obiekt, w tym przypadku tablica pionków. 
	 *  W przypadku niepowodzenia wypisywany jest komunikat o niepowodzeniu w połączeniu z serwerem.
	 * @see ServerSocket
	 * @see Socket
	 */
	private JButton multiplayer;
	/** Zmienna JLabel używana w celu tworzenia napisu 'WARCABY'. */
	private JLabel napisGlowny;
	/** Zmienna JLabel używana w celu tworzenia napisu 'KLIENT'. */
	private JLabel napisWersja;
	/** Obiekt klasy Plansza. */
	private Plansza plansza;
	/** Tablica obiektów klasy Kafelek. Każdy element tablicy określa współrzędne każdego pojedynczego kafelka szachownicy. 
	 *  @see Kafelek
	 */
	private Kafelek[][] kafelki;
	/** Zmienna statyczna typu Color określająca kolor planszy, który jest ustalany poprzez opcje {@link warcaby.klient.Opcje}.
	 *  @see Color
	 */
	protected static Color kolorPlanszy1;
	/** Zmienna statyczna typu Color określająca kolor planszy, który jest ustalany poprzez opcje {@link warcaby.klient.Opcje}.
	 *  @see Color
	 */
	protected static Color kolorPlanszy2;
	/** Zmienna statyczna typu Color określająca kolor pionków, który jest ustalany poprzez opcje {@link warcaby.klient.Opcje}.
	 *  @see Color
	 */
	protected static Color kolorPionkow1;
	/** Zmienna statyczna typu Color określająca kolor pionków, który jest ustalany poprzez opcje {@link warcaby.klient.Opcje}.
	 *  @see Color
	 */
	protected static Color kolorPionkow2;
	/** Zmienna tworząca gniazdo serwerowe powiązane z podanym portem - deklaracja obiektu klasy ServerSocket.
	 *  @see ServerSocket
	 */
	protected ServerSocket serverSocket;
	/** Zmienna tworząca gniazdo powiązane z podanym portem - deklaracja obiektu klasy Socket.
	 *  @see Socket
	 */
	protected Socket socket;
	/** Zmienna tworząca strumień umożliwiający odczyt danych z gniazda sieciowego. 
	 *  @see ObjectInputStream
	 */
	protected static ObjectInputStream ois;
	/** Zmienna tworząca strumień umożliwiający zapis danych do gniazda sieciowego. 
	 *  @see ObjectOutputStream
	 */
	protected static ObjectOutputStream oos;
	/** Zmienna logiczna określająca, czy użytkownik wybrał tryb gry sieciowej poprzez kliknięcie na przycisk 'Multiplayer'.*/
	protected static boolean multi;
	/** Zmienna logiczna określająca ustawiająca się na true w przypadku, gdy pionki zostaną ustawione - sygnał do gry..*/
	protected static boolean gra;

	/** Konstruktor bezparametrowy klasy Plansza odpowiedzialny za inicjalizację komponentów z biblioteki Swing, zmiennych, tablic.
	 * Komponenty definiowane: JButton, JLabel, JFrame - dla tych komponentów ustawiane są wymiary, fonty, kolory.
	 * Po zdefiniowaniu każdego z użytych komponentów dodano go do okna z opcjami głównymi metodą {@link JFrame#add(java.awt.Component)}.
	 * Ponadto definiowane są tablice obiektów tj. tablica obiektów klasy Kafelek, tablica współrzędnych pionków na szachownicy.
	 * Zdefiniowano również zmienne typu Color, które określają kolor planszy lub kolor pionków
	 * @see JButton
	 * @see JFrame
	 * @see JLabel
	 * @see Kafelek
	 * @see Opcje
	 */
	public Plansza() {
		super("Warcaby");
		tablicaPionkow = new int[8][8];
		kafelki = new Kafelek[8][8];
		multi = false;
		gra = false;

		setSize(szerokosc, wysokosc);
		setLocationRelativeTo(plansza);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		napisGlowny = new JLabel("WARCABY");
		napisGlowny.setBounds(535, 15, 140, 30);
		napisGlowny.setFont(new Font("Arial", Font.BOLD, 17));
		napisGlowny.setForeground(Color.BLUE);
		add(napisGlowny);

		napisWersja = new JLabel("KLIENT");
		napisWersja.setBounds(555, 55, 140, 20);
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
				kafelki[i][j] = new Kafelek(this, i, j);
				add(kafelki[i][j]);
			}
		}
		setVisible(true);

	}

	/** Metoda bezparametrowa odpowiedzialna za ustawianie pionków na szachownicy.
	 * Operacja przeprowadzana jest poprzez użycie pętli for oraz rozgraniczenie
	 * dwóch kolorów pionów od siebie - jeden kolor oznaczany jest poprzez 1, 
	 * a drugi poprzez 2. Pole puste poprzez 0. Po poprawnym ustawieniu
	 * zmienna logiczna 'gra' jest ustawiana na wartość TRUE.
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 */
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
		gra = true;
	}
	
	/** Metoda przesłonięta o nazwie paint. Przyjmuje jeden stały parametr typu Graphics.
	 * Metoda wykorzystywana jest do rysowania szachownicy oraz pionków. 
	 * Ponadto w klasie zaadaptowano również rysowanie podświetleń konturów aktywnych kafelków, 
	 * na które możemy przemieścić gracz swój pionek - są to tak zwane pola aktywne do ruchu dla pionka. 
	 * W metodzie rysowany jest również kafelek informacyjny umieszczony w prawym dolnym rogu okna głównego
	 * aplikacji, który informuje użytkownika na jakim polu szachownicy obecnie znajduje się jego kursor myszy.
	 * Rysowanie odbywa się za pomocą komponentu {@link Graphics2D}. Wykorzystywane są funkcje takie jak:
	 * {@link Graphics2D#drawRect(int, int, int, int)},{@link Graphics2D#setColor(Color)}, {@link Graphics2D#fillRect(int, int, int, int)},
	 * {@link Graphics2D#fillOval(int, int, int, int)} itp. 
	 * Metoda jest typu void - nie zwraca żadnej wartości. 
	 * @see Graphics
	 * @see Graphics2D
	 */
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
				if (tablicaPionkow[i][j] == 3 || tablicaPionkow[i][j] == 4 || tablicaPionkow[i][j] == 5
						|| tablicaPionkow[i][j] == 8 || tablicaPionkow[i][j] == 9) {
					g2d.setColor(Color.YELLOW);
					g2d.drawRect(kafelki[i][j].x + 3, kafelki[i][j].y + 25, kafelki[i][j].width, kafelki[i][j].height);
				}
			}
		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++) {
				if (tablicaPionkow[i][j] == 1 || tablicaPionkow[i][j] == 3 || tablicaPionkow[i][j] == 7
						|| tablicaPionkow[i][j] == 9)
					g2d.setColor(kolorPionkow1);
				else if (tablicaPionkow[i][j] == 2 || tablicaPionkow[i][j] == 4 || tablicaPionkow[i][j] == 6
						|| tablicaPionkow[i][j] == 8)
					g2d.setColor(kolorPionkow2);

				if (tablicaPionkow[i][j] == 1 || tablicaPionkow[i][j] == 2 || tablicaPionkow[i][j] == 3
						|| tablicaPionkow[i][j] == 4)
					g2d.fillOval(17 + 63 * i, 47 + 63 * j, 50, 50);
				if (tablicaPionkow[i][j] == 6 || tablicaPionkow[i][j] == 7 || tablicaPionkow[i][j] == 8
						|| tablicaPionkow[i][j] == 9)
					g2d.fillRect(17 + 63 * i, 47 + 63 * j, 50, 50);
			}

		g2d.setColor(Color.BLACK);
		g2d.drawRect(550, 493, 50, 50);
		g2d.setFont(new Font("Arial", Font.BOLD, 12));
		g2d.drawString("POLE", 560, 515);
		g2d.drawString(Kafelek.tmp, 570, 535);

	}

	/** Przesłonięta metoda służąca do określania zachowania aplikacji po kliknięciu na dany komponent przez użytkownika.
	 *  W metodzie tej określono działanie dla poszczególnych przycisków znajdujących się po prawej stronie głównego interfejsu gry.
	 *  W przypadku kliknięcia na przycisk 'Start' wywoływana jest metoda {@link Plansza#ustaw()}, która ustawia 
	 *  pionki na początkowe pozycje. Następnie wywoływana jest zdefiniowana metoda {@link java.awt.Component#repaint()}, celem wyświetlenia
	 *  zmian na interfejsie gry.
	 *  W przypadku kliknięcia na przycisk 'Opcje' tworzona jest nowa instancja klasy {@link Opcje}, poprzez wywołanie konstruktora tej klasy.
	 *  Otworzy się wtedy drugie okno z opcjami gry do wyboru.
	 *  W przypadku kliknięcia na przycsik 'Multiplayer' tworzone jest gniazdo, które jest automatycznie łączone z podanym portem i adresem. 
	 *  Następnie jeśli łączenie przebiegło poprawnie wyświetlany jest komunikat za pomocą okna dialogowego klasy {@link JOptionPane} ze stosowną
	 *  informacją. Następnie inicjalizowane są strumienie zapisu do gniazda lub odczytu z gniazda. Dalej zmienna logiczna o nazwie 'multi' jest
	 *  ustawiana na wartość 'true'. Następnie wywoływana jest metoda {@link Plansza#ustaw()}, która ustawia pionki na początkowe pozycje oraz 
	 *  zdefiniowana metoda {@link java.awt.Component#repaint()}, celem wyświetlenia zmian na interfejsie gry.
	 *  Po funkcji {@link java.awt.Component#repaint()} tworzona jest nowa instancja klasy {@link Thread}, która tworzy nowy wątek. 
	 *  Wątek ma za zadanie odczytywać dane ze strumienia odbierającego dane z gniazda - jest to tablica pionków. W przypadku niepowodzenia w łączeniu się
	 *  z serwerem wypisywany jest stosowny komunikat za pomocą komponentu okna dialogowego klasy {@link JOptionPane}.
	 *  W przypadku kliknięcia na przycisk 'Wyjscie' pojawia się komunikat w postaci okna dialogowego z prośbą potwierdzenia operacji. 
	 *  Gdy użytkownik potwierdzi wolę zamknięcia aplikacji to zamykane są strumienie zapisu oraz odczytu z gniazda sieciowego, zamykane jest gniazdo połączone
	 *  oraz gniazdo serwerowe. 
	 *  @see Plansza#ustaw()
	 *  @see java.awt.Component#repaint()
	 *  @see JOptionPane
	 *  @see Socket
	 *  @see ServerSocket
	 *  @see Thread
	 *  @see Thread#run()
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == start) {
			ustaw();
			repaint();
		}

		if (source == multiplayer) {
			try {
				socket = new Socket("localhost", 5555);
				JOptionPane.showMessageDialog(null, "Połączono z serwerem");
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				multi = true;
				ustaw();
				repaint();
				new Thread() {
					public void run() {
						while (true) {
							try {
								int tmp[][] = (int[][]) ois.readObject();
								if (!tmp.equals(tablicaPionkow)) {
									System.out.println("Zamieniono");
									tablicaPionkow = tmp;
									repaint();
								}
							} catch (ClassNotFoundException | IOException e) {
							}
						}
					}
				}.start();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Nie nawiązano połączenia");
			}
		}

		if (source == opcje) {
			new Opcje(this);
		}

		if (source == wyjscie) {
			int decyzja = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjść?", "Confirm Dialog",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
			if (decyzja == JOptionPane.YES_OPTION) {
				dispose();
				try {
					ois.close();
					oos.close();
					socket.close();
					serverSocket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		}
	}
}

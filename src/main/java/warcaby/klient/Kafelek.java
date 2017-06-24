package warcaby.klient;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Klasa Kafelek zawiera podstawowe zmienne oraz metody obsługi ruchu pionków po
 * szachownicy oraz określa również bicia. Klasa posiada zmienne oraz metody
 * odpowiedzialne za określenie pojedynczego kafelka występującego na
 * szachownicy, dzięki temu można łatwo określić możliwe ruchy dla pionka oraz
 * możliwości bicia. Ponadto w klasie zdefiniowano wystąpienie królówek zgodnie
 * z zasadami gry - warcaby. Zdefiniowano również działanie kafelka
 * informacyjnego, który pokazuje współrzędne kursora użytkownika względem
 * szachownicy - kafelek znajduje się w prawym dolnym rogu głównego okna
 * aplikacji. Klasa Kafelek dziedziczy po klasie {@link javax.swing.JComponent}
 * celem stworzenia obiektu kafelka informacyjnego. Ponadto rozszerzona jest ona
 * poprzez interfejs {@link java.awt.event.MouseListener} celem zdefiniowania
 * akcji myszą - czyli podstawowego kontrolera rozgrywki - poprzez klikanie
 * myszą na odpowiednie pionki możemy je przemieszczać oraz wykonywać bicia.
 * 
 * @author Bartłomiej Osak, Tomasz Pasternak
 * @see javax.swing.JComponent
 * @see java.awt.event.MouseListener
 */
public class Kafelek extends JComponent implements MouseListener {

	/** Zmienna określająca unikalny numer w celu serializacji. */
	private static final long serialVersionUID = 1L;
	/** Zmienna określająca współrzędną x pojedynczego kafelka szachownicy. */
	protected Integer x;
	/** Zmienna określająca współrzędną y pojedynczego kafelka szachownicy. */
	protected Integer y;
	/**
	 * Zmienna określająca wymiar - szerokość pojedynczego kafelka szachownicy.
	 */
	protected Integer width = 62;
	/**
	 * Zmienna określająca wymiar - wysokość pojedynczego kafelka szachownicy.
	 */
	protected Integer height = 62;
	/**
	 * Zmienna określająca współrzędną x klikniętego przez użytkownika
	 * pojedynczego kafelka szachownicy.
	 */
	protected int _i;
	/**
	 * Zmienna określająca współrzędną y klikniętego przez użytkownika
	 * pojedynczego kafelka szachownicy.
	 */
	protected int _j;
	/**
	 * Obiekt klasy StringBuilder mający na celu wyświetlanie aktualnych
	 * współrzędnych kursora myszy na szachownicy na kafelku informacyjnym.
	 * 
	 * @see StringBuilder
	 */
	protected StringBuilder nazwa = new StringBuilder();
	/**
	 * Zmienna typu String inicjalizująca początkową wartość wyświetlaną przez
	 * kafelek informacyjny.
	 */
	protected static String tmp = "A1";
	/** Zmienna JFrame odwołująca się do okna głównej aplikacji. */
	protected static JFrame parent;
	/** Współrzędna x kafelka, na którym może dojść do bicia pionka. */
	protected static int iBicie;
	/** Współrzędna y kafelka, na którym może dojść do bicia pionka. */
	protected static int jBicie;

	/**
	 * Konstruktor klasy Kafelek inicjalizuje zmienne typu JFrame - odwołanie do
	 * okna głównego aplikacji, zmienne określające współrzędne. Ponadto
	 * inicjalizuje interfejs {@link java.awt.event.MouseListener} poprzez
	 * metodę {@link java.awt.Component#addMouseListener}. Ponadto wywoływana
	 * jest metoda ustawNazwe, która ustawia napis aktualnej pozycji kursora
	 * myszy w kafelku informacyjnym.
	 * 
	 * @param f
	 *            - parametr JFrame - główne okno aplikacji
	 * @param i
	 *            - współrzędna x napisu w kafelku informacyjnym
	 * @param j
	 *            - współrzędna y napisu w kafelku informacyjnym
	 * @see java.awt.Component
	 * @see java.awt.event.MouseListener
	 * @see javax.swing.JComponent
	 */
	public Kafelek(JFrame f, int i, int j) {
		parent = f;
		x = 8 + 63 * i;
		y = 15 + 63 * j;
		_i = i;
		_j = j;
		setBounds(x, y, width, height);
		ustawNazwe(i, j);
		addMouseListener(this);
		setLayout(null);
		setDoubleBuffered(false);
		setVisible(true);
	}

	/**
	 * Metoda ustawNazwe ma za zadanie poprawne wyświetlanie informacji na
	 * kafelku informacyjnym. Przyjmuje dwa parametry wywołania. W zależności od
	 * współrzędnych informacja jest wyświetlana poprzez dodanie do zmiennej
	 * StringBuilder nowych treści poprzez metodę append().
	 * 
	 * @param i
	 *            - współrzędna x pojedynczego kafelka na szachownicy.
	 * @param j
	 *            - współrzędna y pojedynczego kafelka na szachownicy.
	 * @see StringBuilder
	 */
	void ustawNazwe(int i, int j) {
		String tmp = null;
		if (i == 0) {
			tmp = "A";
		}
		if (i == 1) {
			tmp = "B";
		}
		if (i == 2) {
			tmp = "C";
		}
		if (i == 3) {
			tmp = "D";
		}
		if (i == 4) {
			tmp = "E";
		}
		if (i == 5) {
			tmp = "F";
		}
		if (i == 6) {
			tmp = "G";
		}
		if (i == 7) {
			tmp = "H";
		}
		nazwa.append(tmp);
		nazwa.append(j + 1);
	}

	/**
	 * Przeciążona metoda toString(), która ma na celu prawidłowe wyświetlanie
	 * informacji na kafelku informacyjnym.
	 */
	@Override
	public String toString() {
		return nazwa + "";
	}

	/**
	 * Metoda przesłonięta pochodząca z interfejsu {@link MouseListener}.
	 * Określa ona zachowanie programu podczas poruszania się kursorem myszy po
	 * oknie aplikacji, czyli gdy kursor myszy najeżdża na dany komponent - w
	 * naszym przypadku komponentem jest okno główne aplikacji - w szczególności
	 * szachownica. W naszym przypadku wykorzystywana jest do aktualizacji
	 * współrzędnych wyświetlanych na kafelku informacyjnym.
	 * 
	 * @see MouseListener
	 * @see MouseListener#mouseEntered(MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		tmp = source.toString();
		parent.repaint(560, 515, 25, 25);
	}

	/**
	 * Metoda przesłonięta pochodząca z interfejsu {@link MouseListener}.
	 * Określa ona zachowanie programu podczas, gdy użytkownik kliknie myszką na
	 * komponent. Funkcja wykorzystywana do opracowania logiki ruchu pionków
	 * oraz bić pionków. Ważne oznaczenia w celu zrozumienia algorytmu ruchu
	 * oraz bić pionów: 0 - oznaczenie pola pustego 1 - oznaczenie pionka
	 * czarnego 2 - oznaczenie pionka białego 3 - oznaczenie wybranego pionka
	 * czarnego (wybrany, czyli kliknięty myszką przez gracza) 4 - oznaczenie
	 * wybranego pionka białego (wybrany, czyli kliknięty myszką przez gracza) 5
	 * - oznaczenie pola pustego, na które można wykonać ruch (przesunąć pionka)
	 * 6 - oznaczenie białej królówki 7 - oznaczenie czarnej królówki 8 -
	 * oznaczenie wybranej białej królówki 9 - oznaczenie wybranej czarnej
	 * królówki Takie rozgraniczenie pozwala na dość czytelne odczytanie
	 * algorytmu ruchu oraz bić. Ponadto w metodzie wysyłany jest obiekt tablicy
	 * pionków poprzez sieć do drugiego gracza. W metodzie zdefiniowano również
	 * warunek zakończenia gry - zakończenie gry sygnalizowane jest specjalnym
	 * komunikatem wyświetlanym w oknie dialogowym.
	 * 
	 * @see MouseListener
	 * @see Plansza
	 * @see Plansza#tablicaPionkow
	 * @see JOptionPane
	 * @see JFrame
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (Plansza.tablicaPionkow[_i][_j] != 5) {
			for (int j = 0; j < 8; j++)
				for (int i = 0; i < 8; i++) {
					if (Plansza.tablicaPionkow[i][j] == 3 || Plansza.tablicaPionkow[i][j] == 4 || Plansza.tablicaPionkow[i][j] == 8 || Plansza.tablicaPionkow[i][j] == 9) {
						Plansza.tablicaPionkow[i][j] -= 2;
					}
					if (Plansza.tablicaPionkow[i][j] == 5) {
						Plansza.tablicaPionkow[i][j] = 0;
					}
				}

			// *****************************************************
			if (Plansza.tablicaPionkow[_i][_j] == 1 || Plansza.tablicaPionkow[_i][_j] == 2 || Plansza.tablicaPionkow[_i][_j] == 6 || Plansza.tablicaPionkow[_i][_j] == 7) {
				iBicie = -2;
				jBicie = -2;
				Plansza.tablicaPionkow[_i][_j] += 2;
				// ******************************************************
				if (Plansza.tablicaPionkow[_i][_j] == 8 || Plansza.tablicaPionkow[_i][_j] == 9) {
					for (int i = 0; i < 4; i++) {
						int tmpX = _i;
						int tmpY = _j;
						while (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8) {
							if (i == 0) {
								tmpX--;
								tmpY--;
							} else if (i == 1) {
								tmpX--;
								tmpY++;
							} else if (i == 2) {
								tmpX++;
								tmpY++;
							} else if (i == 3) {
								tmpX++;
								tmpY--;
							}
							if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
								if (Plansza.tablicaPionkow[tmpX][tmpY] == 0) {
									Plansza.tablicaPionkow[tmpX][tmpY] = 5;
								} else {
									if (((Plansza.tablicaPionkow[tmpX][tmpY] == 1 || Plansza.tablicaPionkow[tmpX][tmpY] == 7) && Plansza.tablicaPionkow[_i][_j] == 8)
											|| (Plansza.tablicaPionkow[tmpX][tmpY] == 2 || Plansza.tablicaPionkow[tmpX][tmpY] == 6) && Plansza.tablicaPionkow[_i][_j] == 9) {
										if (i == 0) {
											tmpX--;
											tmpY--;
										} else if (i == 1) {
											tmpX--;
											tmpY++;
										} else if (i == 2) {
											tmpX++;
											tmpY++;
										} else if (i == 3) {
											tmpX++;
											tmpY--;
										}
										if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
											if (Plansza.tablicaPionkow[tmpX][tmpY] == 0) {
												Plansza.tablicaPionkow[tmpX][tmpY] = 5;
												if (i == 0) {
													tmpX++;
													tmpY++;
												} else if (i == 1) {
													tmpX++;
													tmpY--;
												} else if (i == 2) {
													tmpX--;
													tmpY--;
												} else if (i == 3) {
													tmpX--;
													tmpY++;
												}
												iBicie = tmpX;
												jBicie = tmpY;
											}
									}
									break;
								}
						}
					}
				}
				// ******************************************************
				if (Plansza.tablicaPionkow[_i][_j] == 4) // KLIKNIETY PIONEK
															// BIALY
				{
					if (_i != 0 && _i != 7) {
						if (Plansza.tablicaPionkow[_i - 1][_j - 1] == 0) {
							Plansza.tablicaPionkow[_i - 1][_j - 1] = 5;
						}
						if (Plansza.tablicaPionkow[_i + 1][_j - 1] == 0) {
							Plansza.tablicaPionkow[_i + 1][_j - 1] = 5;
						}

					} else if (_i == 0) {
						if (Plansza.tablicaPionkow[_i + 1][_j - 1] == 0)
							Plansza.tablicaPionkow[_i + 1][_j - 1] = 5;
					} else if (_i == 7) {
						if (Plansza.tablicaPionkow[_i - 1][_j - 1] == 0)
							Plansza.tablicaPionkow[_i - 1][_j - 1] = 5;
					}
					// *****************************************************
					if (_j != 1)
						if (_i != 0 && _i != 1 && _i != 6 && _i != 7) {
							if (Plansza.tablicaPionkow[_i - 2][_j - 2] == 0 && (Plansza.tablicaPionkow[_i - 1][_j - 1] == 1 || Plansza.tablicaPionkow[_i - 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i - 2][_j - 2] = 5;
								iBicie = _i - 1;
								jBicie = _j - 1;
							}
							if (Plansza.tablicaPionkow[_i + 2][_j - 2] == 0 && (Plansza.tablicaPionkow[_i + 1][_j - 1] == 1 || Plansza.tablicaPionkow[_i - 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i + 2][_j - 2] = 5;
								iBicie = _i + 1;
								jBicie = _j - 1;
							}
						} else if (_i == 1 || _i == 0) {
							if (Plansza.tablicaPionkow[_i + 2][_j - 2] == 0 && (Plansza.tablicaPionkow[_i + 1][_j - 1] == 1 || Plansza.tablicaPionkow[_i + 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i + 2][_j - 2] = 5;
								iBicie = _i + 1;
								jBicie = _j - 1;
							}
						} else if (_i == 6 || _i == 7) {
							if (Plansza.tablicaPionkow[_i - 2][_j - 2] == 0 && (Plansza.tablicaPionkow[_i - 1][_j - 1] == 1 || Plansza.tablicaPionkow[_i - 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i - 2][_j - 2] = 5;
								iBicie = _i - 1;
								jBicie = _j - 1;
							}
						}
				}
				// *****************************************************
				if (Plansza.tablicaPionkow[_i][_j] == 3) {
					iBicie = -2;
					jBicie = -2;
					if (_i != 0 && _i != 7) {
						if (Plansza.tablicaPionkow[_i - 1][_j + 1] == 0) {
							Plansza.tablicaPionkow[_i - 1][_j + 1] = 5;
						}
						if (Plansza.tablicaPionkow[_i + 1][_j + 1] == 0) {
							Plansza.tablicaPionkow[_i + 1][_j + 1] = 5;
						}
					} else if (_i == 0) {
						if (Plansza.tablicaPionkow[_i + 1][_j + 1] == 0)
							Plansza.tablicaPionkow[_i + 1][_j + 1] = 5;
					} else if (_i == 7) {
						if (Plansza.tablicaPionkow[_i - 1][_j + 1] == 0)
							Plansza.tablicaPionkow[_i - 1][_j + 1] = 5;
					}
					// *****************************************************
					if (_j != 6) {
						if (_i != 0 && _i != 1 && _i != 6 && _i != 7) {

							if (Plansza.tablicaPionkow[_i - 2][_j + 2] == 0 && (Plansza.tablicaPionkow[_i - 1][_j + 1] == 2 || Plansza.tablicaPionkow[_i - 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i - 2][_j + 2] = 5;
								iBicie = _i - 1;
								jBicie = _j + 1;
							}
							if (Plansza.tablicaPionkow[_i + 2][_j + 2] == 0 && (Plansza.tablicaPionkow[_i + 1][_j + 1] == 2 || Plansza.tablicaPionkow[_i + 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i + 2][_j + 2] = 5;
								iBicie = _i + 1;
								jBicie = _j + 1;
							}
						} else if (_i == 1 || _i == 0) {
							if (Plansza.tablicaPionkow[_i + 2][_j + 2] == 0 && (Plansza.tablicaPionkow[_i + 1][_j + 1] == 2 || Plansza.tablicaPionkow[_i + 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i + 2][_j + 2] = 5;
								iBicie = _i + 1;
								jBicie = _j + 1;
							}
						} else if (_i == 6 || _i == 7) {
							if (Plansza.tablicaPionkow[_i - 2][_j + 2] == 0 && (Plansza.tablicaPionkow[_i - 1][_j + 1] == 2 || Plansza.tablicaPionkow[_i - 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i - 2][_j + 2] = 5;
								iBicie = _i - 1;
								jBicie = _j + 1;
							}
						}
					}
				}
			}
		}
		// *****************************************************
		else {
			for (int j = 0; j < 8; j++)
				for (int i = 0; i < 8; i++) {
					if (Plansza.tablicaPionkow[i][j] == 4) {
						Plansza.tablicaPionkow[i][j] = 0;
						Plansza.tablicaPionkow[_i][_j] = 2;
					}
					if (Plansza.tablicaPionkow[i][j] == 3) {
						Plansza.tablicaPionkow[i][j] = 0;
						Plansza.tablicaPionkow[_i][_j] = 1;
					}
					if (Plansza.tablicaPionkow[i][j] == 8) {
						Plansza.tablicaPionkow[i][j] = 0;
						Plansza.tablicaPionkow[_i][_j] = 6;
					}
					if (Plansza.tablicaPionkow[i][j] == 9) {
						Plansza.tablicaPionkow[i][j] = 0;
						Plansza.tablicaPionkow[_i][_j] = 7;
					}
					if (Plansza.tablicaPionkow[i][j] == 5)
						Plansza.tablicaPionkow[i][j] = 0;
				}
			if ((_i == iBicie + 1 || _i == iBicie - 1) && (_j == jBicie - 1 || _j == jBicie + 1)) {
				Plansza.tablicaPionkow[iBicie][jBicie] = 0;
				iBicie = -2;
				jBicie = -2;
			}
			// *********** KROLOWKA B /*
			if (_j == 0 && Plansza.tablicaPionkow[_i][_j] == 2) {
				Plansza.tablicaPionkow[_i][_j] = 6;
			}
			if (_j == 7 && Plansza.tablicaPionkow[_i][_j] == 1) {
				Plansza.tablicaPionkow[_i][_j] = 7;
			}
			// ********** KROLOWKA B*/
			if (Plansza.multi) {
				try {
					Plansza.oos.writeObject(Plansza.tablicaPionkow);
					Plansza.oos.flush();
					Plansza.oos.reset();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if (Plansza.gra) {
			int b = 0;
			int c = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (Plansza.tablicaPionkow[i][j] == 1 || Plansza.tablicaPionkow[i][j] == 3 || Plansza.tablicaPionkow[i][j] == 7 || Plansza.tablicaPionkow[i][j] == 9)
						c++;
					if (Plansza.tablicaPionkow[i][j] == 2 || Plansza.tablicaPionkow[i][j] == 4 || Plansza.tablicaPionkow[i][j] == 6 || Plansza.tablicaPionkow[i][j] == 8)
						b++;
				}
			}
			if (b == 0 || c == 0)
				JOptionPane.showMessageDialog(null, "Koniec gry!");
		}
		parent.repaint();
	}

	/**
	 * Metoda przesłonięta pochodząca z interfejsu {@link MouseListener} -
	 * niewykorzystywana. Jej obecność jest obowiązkowa w celu poprawnego
	 * zaimplementowania np. metody {@link Kafelek#mouseClicked(MouseEvent)}.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Metoda przesłonięta pochodząca z interfejsu {@link MouseListener} -
	 * niewykorzystywana. Jej obecność jest obowiązkowa w celu poprawnego
	 * zaimplementowania np. metody {@link Kafelek#mouseClicked(MouseEvent)}.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Metoda przesłonięta pochodząca z interfejsu {@link MouseListener} -
	 * niewykorzystywana. Jej obecność jest obowiązkowa w celu poprawnego
	 * zaimplementowania np. metody {@link Kafelek#mouseClicked(MouseEvent)}.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
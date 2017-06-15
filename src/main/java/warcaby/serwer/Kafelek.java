package warcaby.serwer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Kafelek extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;
	protected Integer x;
	protected Integer y;
	protected Integer width = 62;
	protected Integer height = 62;
	protected int _i;
	protected int _j;
	protected StringBuilder name = new StringBuilder();
	protected static String tmp = "A1";
	protected static JFrame parent;
	protected static int iBicie;
	protected static int jBicie;

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
		name.append(tmp);
		name.append(j + 1);
	}

	void updateEnter() {
		parent.repaint(560, 515, 25, 25);
	}

	@Override
	public String toString() {
		return name + "";
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object source = e.getSource();
		tmp = source.toString();
		updateEnter();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Plansza.tablicaPionkow[_i][_j] != 5) {
			for (int j = 0; j < 8; j++)
				for (int i = 0; i < 8; i++) {
					if (Plansza.tablicaPionkow[i][j] == 3 || Plansza.tablicaPionkow[i][j] == 4
							|| Plansza.tablicaPionkow[i][j] == 8 || Plansza.tablicaPionkow[i][j] == 9) {
						Plansza.tablicaPionkow[i][j] -= 2;
					}
					if (Plansza.tablicaPionkow[i][j] == 5) {
						Plansza.tablicaPionkow[i][j] = 0;
					}
				}

			// *****************************************************
			if (Plansza.tablicaPionkow[_i][_j] == 1 || Plansza.tablicaPionkow[_i][_j] == 2
					|| Plansza.tablicaPionkow[_i][_j] == 6 || Plansza.tablicaPionkow[_i][_j] == 7) // KLIKNIETE
																									// POLE
																									// PIONKIEM
			{
				iBicie = -1;
				jBicie = -1;
				Plansza.tablicaPionkow[_i][_j] += 2;
				// ******************************************************
				if (Plansza.tablicaPionkow[_i][_j] == 8 || Plansza.tablicaPionkow[_i][_j] == 9) // KLIKNIETA
																								// KROLOWKA
																								// RUCH
				{
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
									if (((Plansza.tablicaPionkow[tmpX][tmpY] == 1
											|| Plansza.tablicaPionkow[tmpX][tmpY] == 7)
											&& Plansza.tablicaPionkow[_i][_j] == 8)
											|| (Plansza.tablicaPionkow[tmpX][tmpY] == 2
													|| Plansza.tablicaPionkow[tmpX][tmpY] == 6)
													&& Plansza.tablicaPionkow[_i][_j] == 9) {
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
							if (Plansza.tablicaPionkow[_i - 2][_j - 2] == 0
									&& (Plansza.tablicaPionkow[_i - 1][_j - 1] == 1
											|| Plansza.tablicaPionkow[_i - 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i - 2][_j - 2] = 5;
								iBicie = _i - 1;
								jBicie = _j - 1;
							}
							if (Plansza.tablicaPionkow[_i + 2][_j - 2] == 0
									&& (Plansza.tablicaPionkow[_i + 1][_j - 1] == 1
											|| Plansza.tablicaPionkow[_i - 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i + 2][_j - 2] = 5;
								iBicie = _i + 1;
								jBicie = _j - 1;
							}
						} else if (_i == 1 || _i == 0) {
							if (Plansza.tablicaPionkow[_i + 2][_j - 2] == 0
									&& (Plansza.tablicaPionkow[_i + 1][_j - 1] == 1
											|| Plansza.tablicaPionkow[_i + 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i + 2][_j - 2] = 5;
								iBicie = _i + 1;
								jBicie = _j - 1;
							}
						} else if (_i == 6 || _i == 7) {
							if (Plansza.tablicaPionkow[_i - 2][_j - 2] == 0
									&& (Plansza.tablicaPionkow[_i - 1][_j - 1] == 1
											|| Plansza.tablicaPionkow[_i - 1][_j - 1] == 7)) {
								Plansza.tablicaPionkow[_i - 2][_j - 2] = 5;
								iBicie = _i - 1;
								jBicie = _j - 1;
							}
						}
				}
				// *****************************************************
				if (Plansza.tablicaPionkow[_i][_j] == 3) {
					iBicie = -1;
					jBicie = -1;
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

							if (Plansza.tablicaPionkow[_i - 2][_j + 2] == 0
									&& (Plansza.tablicaPionkow[_i - 1][_j + 1] == 2
											|| Plansza.tablicaPionkow[_i - 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i - 2][_j + 2] = 5;
								iBicie = _i - 1;
								jBicie = _j + 1;
							}
							if (Plansza.tablicaPionkow[_i + 2][_j + 2] == 0
									&& (Plansza.tablicaPionkow[_i + 1][_j + 1] == 2
											|| Plansza.tablicaPionkow[_i + 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i + 2][_j + 2] = 5;
								iBicie = _i + 1;
								jBicie = _j + 1;
							}
						} else if (_i == 1 || _i == 0) {
							if (Plansza.tablicaPionkow[_i + 2][_j + 2] == 0
									&& (Plansza.tablicaPionkow[_i + 1][_j + 1] == 2
											|| Plansza.tablicaPionkow[_i + 1][_j + 1] == 6)) {
								Plansza.tablicaPionkow[_i + 2][_j + 2] = 5;
								iBicie = _i + 1;
								jBicie = _j + 1;
							}
						} else if (_i == 6 || _i == 7) {
							if (Plansza.tablicaPionkow[_i - 2][_j + 2] == 0
									&& (Plansza.tablicaPionkow[_i - 1][_j + 1] == 2
											|| Plansza.tablicaPionkow[_i - 1][_j + 1] == 6)) {
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
				iBicie = -1;
				jBicie = -1;
			}
			// *********** KROLOWKA B /*
			if (_j == 0) {
				Plansza.tablicaPionkow[_i][_j] = 6;
			}
			if (_j == 7) {
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
		if (Plansza.game) {
			int b = 0;
			int c = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (Plansza.tablicaPionkow[i][j] == 1 || Plansza.tablicaPionkow[i][j] == 3
							|| Plansza.tablicaPionkow[i][j] == 7 || Plansza.tablicaPionkow[i][j] == 9)
						c++;
					if (Plansza.tablicaPionkow[i][j] == 2 || Plansza.tablicaPionkow[i][j] == 4
							|| Plansza.tablicaPionkow[i][j] == 6 || Plansza.tablicaPionkow[i][j] == 8)
						b++;
				}
			}
			if (b == 0 || c == 0)
				JOptionPane.showMessageDialog(null, "Koniec gry!");
		}
		parent.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
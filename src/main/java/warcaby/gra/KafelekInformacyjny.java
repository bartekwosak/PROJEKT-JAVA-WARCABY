package warcaby.gra;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class KafelekInformacyjny extends JComponent implements MouseListener {

	private static final long serialVersionUID = 1L;
	protected Integer x;
	protected Integer y;
	protected Integer width = 62;
	protected Integer height = 62;
	protected StringBuilder name = new StringBuilder();
	protected static String tmp = "A1";
	protected static JFrame parent;

	public KafelekInformacyjny(JFrame f, int i, int j) {
		parent = f;
		x = 8 + 63 * i;
		y = 15 + 63 * j;
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

	void update() {
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
		update();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
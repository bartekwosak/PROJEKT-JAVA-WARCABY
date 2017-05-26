package warcaby.gra;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Warcaby extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	int width = 650;
	int height = 600;
	JButton start, wyjscie;
	JLabel etykieta;
	
	public Warcaby()
	{
		setSize(width,height);
		setTitle("Warcaby");
		//setLayout(null);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		etykieta = new JLabel("Warcaby");
		etykieta.setBounds(540, 10, 100, 20);
		etykieta.setFont(new Font("SansSerif",Font.BOLD,16));
		add(etykieta);
		
		start = new JButton("Start");
		start.setBounds(520, 80, 100, 20);
		add(start);
		start.addActionListener(this);
		
		wyjscie = new JButton("Wyjscie");
		wyjscie.setBounds(520, 110, 100, 20);
		add(wyjscie);
		wyjscie.addActionListener(this);
		
		add(new Plansza());
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == start)
			System.out.println("hehe");
		if(source == wyjscie)
			dispose();
	}
	
	public static void  main(String args[])
	{
		new Warcaby();
	}
}
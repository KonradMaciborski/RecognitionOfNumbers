import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	byte[][] tablicaWejsciowa = new byte[6][4];
	

	public GUI() throws HeadlessException {

		
		this.setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 4));

		for (int i = 0; i < 24; i++) {
			panel.add(createButton());
		}

		JPanel panel2 = new JPanel();
		JButton buttonOk = new JButton("OK");
		buttonOk.setBackground(Color.GREEN);

		Component[] comp = panel.getComponents();

		buttonOk.addActionListener(e -> {
			int count = 0;

			System.out.println("Macierz wejsciowa: ");
			
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 4; j++) {

					String temp = ((JButton) comp[count]).getText();
					this.tablicaWejsciowa[i][j] = Byte.parseByte(temp);
					System.out.print(this.tablicaWejsciowa[i][j]);
					count++;
				}
				System.out.println();
			}

			this.setVisible(false);
			this.dispose();
		});

		panel2.setLayout(new GridLayout(1, 1));
		panel2.add(buttonOk);
		

		this.getContentPane().add(panel);
		this.getContentPane().add(panel2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(195, 245);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		

	}

	protected static JButton createButton() {

		JButton button = new JButton("0");
		button.setBackground(Color.WHITE);

		button.addActionListener(e -> {
			if (button.getText().equals("0")) {
				button.setText("1");
				button.setBackground(Color.BLACK);
				button.setForeground(Color.WHITE);

			} else if (button.getText().equals("1")) {
				button.setText("0");
				button.setBackground(Color.WHITE);
				button.setForeground(Color.BLACK);
			}
		});

		return button;

	}

	public byte[][] getTablicaWejsciowa() {
		return tablicaWejsciowa;
	}

	public void setTablicaWejsciowa(byte[][] tablicaWejsciowa) {
		this.tablicaWejsciowa = tablicaWejsciowa;
	}

}

import java.awt.Color;

import javax.swing.JPanel;

public class Spot extends JPanel {
	private int x, y;
	private int size;
	private Piece piece;

	public Spot(int x, int y, int size) {
		setLayout(null);
		setBounds(x * size, y * size, size, size);
		this.x = x;
		this.y = y;
		this.size = size;
		if ((x + y) % 2 == 0) {
			setBackground(Color.black);
		} else {
			setBackground(Color.white);
		}
		setVisible(true);
	}

}

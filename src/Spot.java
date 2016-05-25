import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Spot extends JPanel {
	private int x, y, size, borderSize = 10;
	private JLabel label;
	private ImageIcon icon;
	private Piece piece;

	public Spot(int x, int y, int size) {
		setBounds(x * size, y * size, size, size);
		this.x = x;
		this.y = y;
		this.size = size;
		if ((x + y) % 2 == 0) {
			setBackground(Color.gray);
		} else {
			setBackground(Color.white);
		}
		setVisible(true);
		icon = new ImageIcon();
		label = new JLabel(icon);
		add(label);
	}

	public int getPositionX() {
		return x;
	}

	public int getPositionY() {
		return y;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		if (piece != null) {
			icon.setImage(ImageHolder.getImage(piece).getScaledInstance(size - borderSize, size - borderSize, Image.SCALE_DEFAULT));
			label.setVisible(true);
		} else {
			label.setVisible(false);
		}
		this.piece = piece;
		SwingUtilities.updateComponentTreeUI(this);
	}
}

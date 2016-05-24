import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class Board extends JFrame {
	private Spot[][] spots = new Spot[6][6];
	private JPanel contentPane;
	private Spot selectedSpot;

	public Board(int gridSize) {
		setTitle("Chess");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				Spot spot = new Spot(i, j, gridSize);
				spot.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (spot.getPiece() != null && selectedSpot == null) {
							selectedSpot = spot;
							spot.setBorder(BorderFactory.createLineBorder(Color.blue,3));
						} else if (spot.getPiece() == null && selectedSpot != null) {
							movePiece(selectedSpot.getPositionX(), selectedSpot.getPositionY(), spot.getPositionX(),
									spot.getPositionY());
							selectedSpot.setBorder(null);
							selectedSpot = null;
						}
					}
				});
				spots[i][j] = spot;
				getContentPane().add(spot);
			}
		}
	}

	public void setPiece(int x, int y, Piece piece) {
		spots[x][y].setPiece(piece);
	}

	public void movePiece(int fromX, int fromY, int toX, int toY) {
		Piece piece = spots[fromX][fromY].getPiece();
		spots[toX][toY].setPiece(piece);
		spots[fromX][fromY].setPiece(null);
	}
}

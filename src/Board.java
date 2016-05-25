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
	public Spot[][] spots = new Spot[6][6];
	private JPanel contentPane;
	// 当前选中的格子
	private Spot selectedSpot;
	private int lastPlayer = Piece.PLAYER_WHITE;

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
							if (spot.getPiece().getPlayer() == lastPlayer)// 判断所选棋子是否是上个次操作过的玩家的棋子
								return;
							selectedSpot = spot;
							spot.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
						} else if (selectedSpot != null) {
							if (spot.getPiece() == null) {
								move();
							} else if (spot.getPiece().getPlayer() != selectedSpot.getPiece().getPlayer()) {
								move();
							} else if (spot == selectedSpot) {
								selectNone();
								return;
							}
						}
					}

					private void move() {
						if (selectedSpot.getPiece().canBeMove(selectedSpot, spot, Board.this)) {
							movePiece(selectedSpot, spot);
							lastPlayer = spot.getPiece().getPlayer();
						}
						selectNone();
					}
				});
				spots[i][j] = spot;
				getContentPane().add(spot);
			}
		}
	}

	public void selectNone() {
		selectedSpot.setBorder(null);
		selectedSpot = null;
	}

	public void setPiece(int x, int y, Piece piece) {
		spots[x][y].setPiece(piece);
	}

	public void movePiece(Spot fromSpot, Spot toSpot) {
		Piece piece = fromSpot.getPiece();
		toSpot.setPiece(piece);
		fromSpot.setPiece(null);
	}
}

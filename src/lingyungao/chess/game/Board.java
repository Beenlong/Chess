package lingyungao.chess.game;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Board extends JFrame {
	private static final long serialVersionUID = -2100109380385562615L;
	public static final int PLAYER_BLACK = 0;
	public static final int PLAYER_WHITE = 1;
	
	private final int gridSize = 100;
	public Spot[][] spots = new Spot[6][6];
	public int lastPlayer = PLAYER_WHITE;
	private JPanel contentPane;
	// 当前选中的格子
	private Spot selectedSpot;
	private OnPieceRemovedListener onPieceRemovedListener;
	private OnPieceMoveListener onPieceMoveListener;

	public Board() {
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
								return;
							} else {
								if (spot == selectedSpot) {
									selectNone();
									return;
								}
								if (spot.getPiece().getPlayer() != selectedSpot.getPiece().getPlayer()) {
									Piece fromPiece = selectedSpot.getPiece(), removedPiece = spot.getPiece();
									move();
									if (onPieceRemovedListener != null) {
										onPieceRemovedListener.onRemoved(fromPiece, removedPiece);
									}
									return;
								} else {
									selectNone();
									return;
								}
							}
						}
					}

					private void move() {
						if (selectedSpot.getPiece().canBeMove(selectedSpot, spot, Board.this)) {
							if (onPieceMoveListener != null) {
								onPieceMoveListener.onMove(selectedSpot, spot);
							}
							Piece piece = selectedSpot.getPiece();
							spot.setPiece(piece);
							selectedSpot.setPiece(null);
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

	/**
	 * 设置棋子
	 * 
	 * @param x
	 *            设置到的格子的横坐标
	 * @param y
	 *            设置到的格子的纵坐标
	 * @param piece
	 */
	public void setPiece(int x, int y, Piece piece) {
		if (x >= 6 || y >= 6)
			throw new RuntimeException("不能设置到6以上的格子");
		spots[x][y].setPiece(piece);
	}

	public void setOnPieceRemovedListener(OnPieceRemovedListener listener) {
		onPieceRemovedListener = listener;
	}

	public void setOnPieceMovedListener(OnPieceMoveListener listener) {
		onPieceMoveListener = listener;
	}

	public interface OnPieceRemovedListener {
		/**
		 * 棋子被吃掉的时候调用
		 * 
		 * @param fromPiece
		 *            吃掉棋子的棋子
		 * @param removedPiece
		 *            被吃掉的棋子
		 */
		void onRemoved(Piece fromPiece, Piece removedPiece);
	}

	public interface OnPieceMoveListener {
		void onMove(Spot fromSpot, Spot toSpot);
	}
}

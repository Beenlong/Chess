package lingyungao.chess.controller;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import lingyungao.chess.entity.ChessLog;
import lingyungao.chess.game.Board;
import lingyungao.chess.game.Piece;
import lingyungao.chess.game.Spot;

public class Controller extends JFrame {
	private static final Piece WR = new Piece(Piece.TYPE_ROOK, Board.PLAYER_WHITE);
	private static final Piece WK = new Piece(Piece.TYPE_KNIGHT, Board.PLAYER_WHITE);
	private static final Piece WB = new Piece(Piece.TYPE_BISHOP, Board.PLAYER_WHITE);
	private static final Piece BR = new Piece(Piece.TYPE_ROOK, Board.PLAYER_BLACK);
	private static final Piece BK = new Piece(Piece.TYPE_KNIGHT, Board.PLAYER_BLACK);
	private static final Piece BB = new Piece(Piece.TYPE_BISHOP, Board.PLAYER_BLACK);

	private static List<ChessLog> log;
	private static Board board;
	private JPanel contentPane;
	// 是否可以悔棋
	private boolean revertPlayer1 = true;
	private boolean revertPlayer2 = true;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initBoard();
					initController();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	public Controller() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(726, 100, 270, 163);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));

		JButton buttonRevert = new JButton("悔棋");
		buttonRevert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 如果已经悔棋过了就不能再次悔棋了
				if (board.lastPlayer == Board.PLAYER_WHITE && revertPlayer1) {
					revert();
					revertPlayer1 = false;
				} else if (board.lastPlayer == Board.PLAYER_BLACK && revertPlayer2) {
					revert();
					revertPlayer2 = false;
				}

			}

			private void revert() {
				if (log.size() >= 2) {
					ChessLog logPlayer2 = log.get(log.size() - 1);
					ChessLog logPlayer1 = log.get(log.size() - 2);
					board.spots[logPlayer2.getToX()][logPlayer2.getToY()].setPiece(logPlayer2.getToPiece());
					board.spots[logPlayer2.getFromX()][logPlayer2.getFromY()].setPiece(logPlayer2.getFromPiece());
					board.spots[logPlayer1.getToX()][logPlayer1.getToY()].setPiece(logPlayer1.getToPiece());
					board.spots[logPlayer1.getFromX()][logPlayer1.getFromY()].setPiece(logPlayer1.getFromPiece());

					log.remove(log.size() - 1);
					log.remove(log.size() - 1);
				}
			}
		});
		contentPane.add(buttonRevert);

		JButton buttonRestart = new JButton("重新开始");
		buttonRestart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				board.dispose();
				initBoard();
			}
		});
		contentPane.add(buttonRestart);

		JButton buttonFinish = new JButton("结束游戏");
		buttonFinish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(buttonFinish);
	}

	private static void initController() {
		Controller frame = new Controller();
		frame.setVisible(true);
	}

	private static void initBoard() {
		board = new Board();
		board.setVisible(true);
		// 白棋
		board.setPiece(0, 0, WR);
		board.setPiece(1, 0, WK);
		board.setPiece(2, 0, WB);
		board.setPiece(3, 0, WB);
		board.setPiece(4, 0, WK);
		board.setPiece(5, 0, WR);
		// 黑棋
		board.setPiece(0, 5, BR);
		board.setPiece(1, 5, BK);
		board.setPiece(2, 5, BB);
		board.setPiece(3, 5, BB);
		board.setPiece(4, 5, BK);
		board.setPiece(5, 5, BR);

		log = new ArrayList<>();

		board.setOnPieceMovedListener((Spot fromSpot, Spot toSpot) -> {
			log.add(new ChessLog(fromSpot.getPositionX(), fromSpot.getPositionY(), toSpot.getPositionX(), toSpot.getPositionY(),
					fromSpot.getPiece(), toSpot.getPiece()));
		});
	}
}

package lingyungao.chess.controller;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import lingyungao.chess.game.Board;
import lingyungao.chess.game.Board.OnPieceMovedListener;
import lingyungao.chess.game.Piece;
import lingyungao.chess.game.Spot;

public class Test extends JFrame {

	private static Piece wr = new Piece(Piece.TYPE_ROOK, Piece.PLAYER_WHITE);
	private static Piece wk = new Piece(Piece.TYPE_KNIGHT, Piece.PLAYER_WHITE);
	private static Piece wb = new Piece(Piece.TYPE_BISHOP, Piece.PLAYER_WHITE);
	private static Piece br = new Piece(Piece.TYPE_ROOK, Piece.PLAYER_BLACK);
	private static Piece bk = new Piece(Piece.TYPE_KNIGHT, Piece.PLAYER_BLACK);
	private static Piece bb = new Piece(Piece.TYPE_BISHOP, Piece.PLAYER_BLACK);

	public static void main(String[] args) {
		Board board = new Board(100);
		board.setVisible(true);
		// 白棋
		board.setPiece(0, 0, wr);
		board.setPiece(1, 0, wk);
		board.setPiece(2, 0, wb);
		board.setPiece(3, 0, wb);
		board.setPiece(4, 0, wk);
		board.setPiece(5, 0, wr);
		// 黑棋
		board.setPiece(0, 5, br);
		board.setPiece(1, 5, bk);
		board.setPiece(2, 5, bb);
		board.setPiece(3, 5, bb);
		board.setPiece(4, 5, bk);
		board.setPiece(5, 5, br);

		board.setOnPieceMovedListener(new OnPieceMovedListener() {

			@Override
			public void onMoved(Spot fromSpot, Spot toSpot, Piece piece) {
				System.out.println(String.format("棋子从%s移动到%s了", "x:" + fromSpot.getPositionX() + ", y:" + fromSpot.getPositionY(),
						"x:" + toSpot.getPositionX() + ", y:" + toSpot.getPositionY()));

			}
		});
	}
}
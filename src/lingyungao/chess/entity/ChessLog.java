package lingyungao.chess.entity;

import lingyungao.chess.game.Piece;

/**
 * 记录类，用来存放移动记录
 */
public class ChessLog {
	private int fromX,fromY,toX,toY;
	private Piece fromPiece,toPiece;
	public ChessLog(int fromX, int fromY, int toX, int toY, Piece fromPiece, Piece toPiece) {
		super();
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
		this.fromPiece = fromPiece;
		this.toPiece = toPiece;
	}
	public int getFromX() {
		return fromX;
	}
	public int getFromY() {
		return fromY;
	}
	public int getToX() {
		return toX;
	}
	public int getToY() {
		return toY;
	}
	public Piece getFromPiece() {
		return fromPiece;
	}
	public Piece getToPiece() {
		return toPiece;
	}
	
}

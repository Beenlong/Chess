package lingyungao.chess.game;

public class Piece {
	public static final int TYPE_BISHOP = 0;
	public static final int TYPE_KNIGHT = 1;
	public static final int TYPE_ROOK = 2;
	public static final int PLAYER_BLACK = 0;
	public static final int PLAYER_WHITE = 1;
	private int type;
	private int player;

	public Piece(int type, int player) {
		super();
		this.type = type;
		this.player = player;
	}

	public int getType() {
		return type;
	}

	public int getPlayer() {
		return player;
	}

	/**
	 * 移动规则<br>
	 * 判断所选棋子是否能移动到该格子
	 * 
	 * @param board
	 * 
	 * @return 能移动到该格子则返回true，反之false
	 */
	public boolean canBeMove(Spot fromSpot, Spot toSpot, Board board) {
		int betweenX, betweenY;
		betweenX = Math.abs(fromSpot.getPositionX() - toSpot.getPositionX());
		betweenY = Math.abs(fromSpot.getPositionY() - toSpot.getPositionY());
		switch (type) {
		case TYPE_BISHOP:
			if (2 >= betweenX && betweenX > 0 && betweenX - betweenY == 0) {
				if (betweenX == 2 &&
						board.spots[(fromSpot.getPositionX() + toSpot.getPositionX()) / 2][(fromSpot.getPositionY() + toSpot.getPositionY()) / 2].getPiece() != null) {
					break;
				}
				return true;
			}
			break;
		case TYPE_KNIGHT:
			if ((betweenX == 2 && betweenY == 1) || (betweenY == 2 && betweenX == 1))
				return true;
			break;
		case TYPE_ROOK:
			if ((betweenX == 0 ^ betweenY == 0) && (betweenX + betweenY <= 2)) {
				if (betweenX + betweenY == 2 &&
						board.spots[(fromSpot.getPositionX() + toSpot.getPositionX()) / 2][(fromSpot.getPositionY() + toSpot.getPositionY()) / 2].getPiece() != null) {
					break;
				}
				return true;
			}
			break;
		}
		return false;
	}
}

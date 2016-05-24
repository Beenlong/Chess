
public class Piece {
	public static final int TYPE_BISHOP_WHITE = 0;
	public static final int TYPE_BISHOP_BLACK = 1;
	public static final int TYPE_KNIGHT_WHITE = 2;
	public static final int TYPE_KNIGHT_BLACK = 3;
	public static final int TYPE_ROOK_WHITE = 4;
	public static final int TYPE_ROOK_BLACK = 5;

	private int type;

	public Piece(int type) {
		super();
		this.type = type;
	}

	public int getType() {
		return type;
	}

}

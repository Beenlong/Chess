package lingyungao.chess.game;

import java.awt.Image;
import java.awt.Toolkit;

public class ImageHolder {
	private static Image bishop_white, bishop_black, knight_white, knight_black, rook_white, rook_black;

	static {
		init();
	}

	public static Image getImage(Piece piece) {
		switch (piece.getType()) {
		case Piece.TYPE_BISHOP:
			if (piece.getPlayer() == Board.PLAYER_WHITE)
				return bishop_white;
			else
				return bishop_black;
		case Piece.TYPE_KNIGHT:
			if (piece.getPlayer() == Board.PLAYER_WHITE)
				return knight_white;
			else
				return knight_black;
		case Piece.TYPE_ROOK:
			if (piece.getPlayer() == Board.PLAYER_WHITE)
				return rook_white;
			else
				return rook_black;
		}
		return null;
	}

	public static void init() {
		bishop_white = Toolkit.getDefaultToolkit().getImage("icon/bishop_white.png");
		bishop_black = Toolkit.getDefaultToolkit().getImage("icon/bishop_black.png");
		knight_white = Toolkit.getDefaultToolkit().getImage("icon/knight_white.png");
		knight_black = Toolkit.getDefaultToolkit().getImage("icon/knight_black.png");
		rook_white = Toolkit.getDefaultToolkit().getImage("icon/rook_white.png");
		rook_black = Toolkit.getDefaultToolkit().getImage("icon/rook_black.png");
	}
}

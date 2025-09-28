package boardgame;

public class Piece {

	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
		position = null; // Não é necessário setar null pois o Java por padrão já seta null.
	}

	protected Board getBoard() {
		return board;
	}


	
	
}

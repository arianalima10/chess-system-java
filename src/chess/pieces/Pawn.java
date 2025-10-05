package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) { // Qdo for branco anda pra cima ou seja linha a menos
			p.setValues(position.getRow() - 1, position.getColumn());  // verificando uma posição acima existe
			if (getBoard().positionExists(p) // Se a posição acima existir
				&& !getBoard().thereIsAPiece(p)) { // Se a posição acima estiver vazia
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 2, position.getColumn()); // verificando duas posição acima existe
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) // Verifica se duas posição acima existe e se está vazia
				&& getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) // Verifica se uma posição acima existe e se está vazia
				&& getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColumn() - 1); // Testa se tem peça na diagonal a esquerda
			if (getBoard().positionExists(p)
				&& isThereOpponentPiece(p)) { // Verifica se tem uma peça adversária
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColumn() + 1); // Testa se tem peça na diagonal a direita
			if (getBoard().positionExists(p)
				&& isThereOpponentPiece(p)) { // Verifica se tem uma peça adversária
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// #specialmove en passant white(movimento especial vulnerável da peça Branca)
			if (position.getRow() == 3) { // Verifica se está na linha 3 da Matriz que é a linha 5 do Tabuleiro
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) // Verificando se existe uma posição a esquerda
						&& isThereOpponentPiece(left) // Se tem uma peça oponente(adversária)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) { // Se a peça está En Passant Vulnerable(passagem vulnerável)
					mat[left.getRow() - 1][left.getColumn()] = true; // O Peão será movido uma posição pra cima na diagonal a esquerda
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		}
		else { // Qdo for Preto/Amarelo anda pra baixo ou seja linha a mais
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}			
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// #specialmove en passant black(movimento especial vulnerável da peça Preta)
			if (position.getRow() == 4) { // Verifica se está na linha 4 da Matriz
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
}

package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
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
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
}

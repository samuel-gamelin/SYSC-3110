package model;

/**
 * A class representing a Rabbit piece.
 * 
 * @author Abdalla El Nakla
 * @author Samuel Gamelin
 * @author Mohamed Radwan
 * 
 * @version 2.0
 */
public class Rabbit extends Piece {
	/**
	 * The colour of the rabbit
	 */
	private RabbitColour colour;

	/**
	 * 
	 * An enumeration of the colour for the rabbit
	 */
	public enum RabbitColour {
		BROWN, WHITE
	}

	/**
	 * Construct a new Rabbit
	 * 
	 * @param colour the colour of the rabiit
	 * 
	 */
	public Rabbit(RabbitColour colour) {
		super(PieceType.RABBIT);
		this.colour = colour;
	}

	/**
	 * Retrieve the color of the rabbit.
	 * 
	 * @return the colour of the Rabbit brown or white
	 */
	public RabbitColour getColour() {
		return colour;
	}

	/**
	 * @param move  The object representing the move
	 * @param board The board on which this move is taking place
	 * @return True if the move is successful, false otherwise.
	 */
	@Override
	public boolean move(Move move, Board board) {
		if (move.direction() == -1) {
			return false;
		}

		if (validatePath(move, board)) {
			board.removePiece(move.xStart, move.yStart);
			board.setPiece(this, move.xEnd, move.yEnd);
			return true;
		}
		return false;
	}

	/**
	 * Validate the path of a rabbit given a move object.
	 * 
	 * @param move  The object representing the move
	 * @param board The board on which the move is taking place.
	 * @return True if the path for this move is valid for rabbits, false otherwise.
	 */
	private boolean validatePath(Move move, Board board) {
		// Rabbits must jump over at least one obstacle
		if ((move.direction() == -1 || Math.abs(move.xDistance()) == 1 || Math.abs(move.yDistance()) == 1)
				|| (move.direction() == 0 && !horizontalMove(move, board))
				|| (move.direction() == 1 && !verticalMove(move, board))) {
			return false;
		}

		return !board.isOccupied(move.xEnd, move.yEnd);
	}

	/**
	 * This method checks if the vertical path for the rabbit is valid.
	 * 
	 * @param move  The object representing the move
	 * @param board The board on which the move is taking place.
	 * @return True if the path is valid, otherwise false
	 */
	private boolean verticalMove(Move move, Board board) {
		if (move.yDistance() < 0) { // Moving up
			for (int i = move.yStart - 1; i > move.yEnd; i--) {
				if (!board.isOccupied(move.xStart, i)) {
					return false;
				}
			}
		} else { // Moving down
			for (int i = move.yStart + 1; i < move.yEnd; i++) {
				if (!board.isOccupied(move.xStart, i)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method checks if the horizontal path for the rabbit is valid.
	 * 
	 * @param move  The object representing the move
	 * @param board The board on which the move is taking place.
	 * @return True if the path is valid, otherwise false
	 */
	private boolean horizontalMove(Move move, Board board) {
		if (move.xDistance() < 0) { // Moving left
			for (int i = move.xStart - 1; i > move.xEnd; i--) {
				if (!board.isOccupied(i, move.yStart)) {
					return false;
				}
			}
		} else { // Moving right
			for (int i = move.xStart + 1; i < move.xEnd; i++) {
				if (!board.isOccupied(i, move.yStart)) {
					return false;
				}
			}
		}
		return true;
	}
}

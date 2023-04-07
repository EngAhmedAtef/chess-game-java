package models;

public record Move(Piece piece, Position startPosition, Position endPosition, boolean isCapturing) {
	
}

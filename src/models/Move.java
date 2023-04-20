package models;

import java.io.Serializable;

public record Move(Piece piece, Position startPosition, Position endPosition, boolean isCapturing) implements Serializable {

}

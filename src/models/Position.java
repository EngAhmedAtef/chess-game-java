package models;

//public class Position {
//	private int row;
//	private int column;
//	
//	public Position(int row, int column) {
//		this.row = row;
//		this.column = row;
//	}
//	
//	public int getRow() {
//		return row;
//	}
//	
//	public int getColumn() {
//		return column;
//	}
//	
//	public void setRow(int row) {
//		this.row = row;
//	}
//	
//	public void setColumn(int column) {
//		this.column = column;
//	}
//}

public record Position(int row, int column) {
	
}
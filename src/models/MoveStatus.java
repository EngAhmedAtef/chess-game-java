package models;

public class MoveStatus {
	private MoveState moveState;
	private String message;
	
	MoveStatus(MoveState moveState, String message) {
		this.moveState = moveState;
		this.message = message;
	}
	
	public MoveState getMoveState() { return moveState; }
	public String getMessage() { return message; }
	
	public void setMoveState(MoveState moveState) { this.moveState = moveState; }
	public void setMessage(String message) { this.message = message; }
}

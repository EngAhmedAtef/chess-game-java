package models;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

import util.PieceColors;

public class Player {
	// Instance variables
	private String name;
	private PieceColors pieceColor;
	private SocketChannel socket;
	private ObjectOutputStream outStream;
	
	// Constructors
	public Player(String name, PieceColors pieceColor) {
		this.name = name;
		this.pieceColor = pieceColor;
		
		connectToGameServer();
	}
	
	// Getters
	public String getName() { return name; }
	public PieceColors getPieceColor() { return pieceColor; }
	
	// Setters
	public void setName(String name) { this.name = name; }
	public void setPieceColor(PieceColors pieceColor) { this.pieceColor = pieceColor; }
	
	// Methods
	private void connectToGameServer() {
		try {
			// Connect to the server
			InetSocketAddress serverAddress = new InetSocketAddress(GameServer.host, GameServer.port);
			SocketChannel socketChannel = SocketChannel.open(serverAddress);
			// Get an output stream to communicate to the server
			outStream = new ObjectOutputStream(Channels.newOutputStream(socketChannel));
			System.out.println("Connected to server.");
		} catch (IOException e) {
			System.out.println("Could not connect to the server");
			e.printStackTrace();
		}
	}
	
	public void makeMove(Move move) {
		try {
			outStream.writeObject(move);
			System.out.println("Sent the move to the server");
		} catch (IOException e) {
			System.out.println("Could not send the move to the serer");
			e.printStackTrace();
		}
	}
	
	public void disconnectFromServer() {
		try {
			outStream.close();
			socket.close();
			System.out.println("Disconnected from the server.");
		} catch (IOException e) {
			System.out.println("Could not disconnect properly from the server.");
			e.printStackTrace();
		}
	}
	
}

package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.PieceColors;

public class Player implements Serializable {
	private static final long serialVersionUID = 3363123419071222327L;
	// Instance variables
	private String name;
	private PieceColors pieceColor;
	private transient SocketChannel socket;
	private transient ObjectOutputStream outStream;
	
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
			socket = SocketChannel.open(serverAddress);
			// Get an output stream to communicate to the server
			outStream = new ObjectOutputStream(Channels.newOutputStream(socket));
			
			ExecutorService threadPool = Executors.newSingleThreadExecutor();
			threadPool.submit(new ServerHandler(socket));
			
			System.out.println("PLAYER: Connected to server.");
		} catch (IOException e) {
			System.out.println("PLAYER: Could not connect to the server");
			e.printStackTrace();
		}
	}
	
	public void sendMove(Move move) {
		try {
			outStream.writeObject(move);
			outStream.flush();
			System.out.println("PLAYER: Sent the move to the server");
		} catch (IOException e) {
			System.out.println("PLAYER: Could not send the move to the serer");
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) {
		try {
			outStream.writeObject(message);
			outStream.flush();
			System.out.println("PLAYER: Sent a message: " + message);
		} catch (IOException e) {
			System.out.println("PLAYER: Could not send a message to the server");
			e.printStackTrace();
		}
	}
	
	public void disconnectFromServer() {
		try {
			socket.close();
			System.out.println("PLAYER: Disconnected from the server.");
		} catch (IOException e) {
			System.out.println("PLAYER: Could not disconnect properly from the server.");
			e.printStackTrace();
		}
	}
	
	// Inner classes
	private class ServerHandler implements Runnable {
		// Instance variables
		private ObjectInputStream reader;
		
		// Constructors
		private ServerHandler(SocketChannel socket) {
			try {
				reader = new ObjectInputStream(Channels.newInputStream(socket));
			} catch (IOException e) {
				System.out.println("PLAYER: Couldn't get an input stream from the socket");
				e.printStackTrace();
			}
		}
		
		// Methods
		@Override
		public void run() {
			Object input;
			try {
				while ((input = reader.readObject()) != null) {
					if (input instanceof String) {
						String message = (String) input;
						System.out.println("PLAYER: Received a message: " + message);
						GameManager.gameManager.sendMessage(message);
					} else if (input instanceof Move) {
						Move move = (Move) input;
						System.out.println("PLAYER: Received a move");
						GameManager.gameManager.makeMove(move);
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("PLAYER: Could not receive a message from the server");
				e.printStackTrace();
			}
		}
	}
	
}

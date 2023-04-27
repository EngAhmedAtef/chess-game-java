package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

	// Static variables
	private static final int DEFAULT_PORT = 5000;
	public static int port;
	public static String host;
	
	// Instance variables
	private List<ObjectOutputStream> connectedPlayers = new ArrayList<>();
	
	// Constructors
	public GameServer() {
		this("localhost", DEFAULT_PORT);
	}
	
	public GameServer(int port) {
		this("localhost", port);
	}
	
	public GameServer(String host, int port) {
		GameServer.host = host;
		GameServer.port = port;
		setupServer();
	}

	// Methods
	private void setupServer() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(host, port));
			
			while(serverSocketChannel.isOpen()) {
				// Wait for a client to connect
				SocketChannel clientSocket = serverSocketChannel.accept();
				ObjectOutputStream outStream = new ObjectOutputStream(Channels.newOutputStream(clientSocket));
				connectedPlayers.add(outStream);
				threadPool.submit(new ClientHandler(clientSocket));
				System.out.println("SERVER: Got a connection.");
			}
			
		} catch (IOException e) {
			System.out.println("SERVER: Could not start the server.");
			e.printStackTrace();
		}
	}

	private void tellEveryone(String message) {
		try {
			for (ObjectOutputStream outStream : connectedPlayers) {
				outStream.writeObject(message);
				outStream.flush();
			}
			System.out.println("SERVER: Sent a message to players; " + message);
		} catch (IOException e) {
			System.out.println("SERVER: Couldn't send a message to players.");
			e.printStackTrace();
		}
	}
	
	private void sendMove(Move move) {
		for (ObjectOutputStream outStream : connectedPlayers)
			try {
				outStream.writeObject(move);
				outStream.flush();
				System.out.println("SERVER: Sent a move to players");
			} catch (IOException e) {
				System.out.println("SERVER: Couldn't send a move to the players");
				e.printStackTrace();
			}
	}
	
	// Inner classes
	private class ClientHandler implements Runnable {
		private ObjectInputStream reader;
		
		private ClientHandler(SocketChannel clientSocket) {
			try {
				reader = new ObjectInputStream(Channels.newInputStream(clientSocket));
			} catch (IOException e) {
				System.out.println("SERVER: Could not get an input stream for the player's socket");
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			Object input;
			try {
				while ((input = reader.readObject()) != null) {
					if (input instanceof Move) {
						System.out.println("SERVER: Received a move from the player.");
						sendMove((Move) input);
					} else if (input instanceof String) {
						String message = (String) input;
						System.out.println("SERVER: Received a message: " + message);
						tellEveryone(message);
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("SERVER: There was an error while reading a request from the player");
				e.printStackTrace();
			}
		}
	}
}

package models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

	// Static variables
	private static final int DEFAULT_PORT = 5000;
	public static int port;
	public static String host;
	
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

	private void setupServer() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(host, port));
			
			while(serverSocketChannel.isOpen()) {
				// Wait for a client to connect
				SocketChannel clientSocket = serverSocketChannel.accept();
				threadPool.submit(new ClientHandler(clientSocket));
				System.out.println("Got a connection.");
			}
			
		} catch (IOException e) {
			System.out.println("Could not start the server.");
			e.printStackTrace();
		}
	}
	
	private void tellEveryone(String message) {
		GameManager.gameManager.tellEveryone(message);
	}
	
	private class ClientHandler implements Runnable {
		ObjectInputStream reader;
		
		private ClientHandler(SocketChannel clientSocket) {
			try {
				reader = new ObjectInputStream(Channels.newInputStream(clientSocket));
			} catch (IOException e) {
				System.out.println("Could not get an input stream for the player's socket");
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			Object input;
			try {
				while ((input = reader.readObject()) != null) {
					if (input instanceof Move) {
						System.out.println("Received a move from the player. Sending it to the GameManager.");
						GameManager.gameManager.makeMove((Move) input);
					} else if (input instanceof String) {
						String message = (String) input;
						System.out.println("Received a message: " + message);
						tellEveryone(message);
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("There was an error while reading a request from the player");
				e.printStackTrace();
			}
		}
	}
}

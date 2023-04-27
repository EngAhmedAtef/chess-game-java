package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import models.GameManager;
import models.GameServer;
import models.Player;
import util.PieceColors;

public class ChessGame {

	public static void main(String[] args) {
		ExecutorService thread = Executors.newSingleThreadExecutor();
		thread.submit(() -> new GameServer());
		Player player = new Player("Ahmed", PieceColors.WHITE_PIECE);
		GameManager.gameManager.setPlayer(player);
	}

}
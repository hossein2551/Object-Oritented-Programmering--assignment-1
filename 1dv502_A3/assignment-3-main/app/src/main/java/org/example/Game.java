package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private final Board board;
    private final ConsoleUi ui;
    private final ArrayList<Player> players;
    private final Dice d1;
    private final Dice d2;
    private final Random random;

    public Game() {
        board = new Board();
        ui = new ConsoleUi();
        random = new Random();
        d1 = new Dice(random);
        d2 = new Dice(random);
        players = new ArrayList<>();
    }

    private void play() {
        startGame();
        playGame();
        endGame();
    }

    private void endGame() {
        ui.gameOver(players);
    }

    private void playGame() {
        final int MAX_ROUNDS = 50;
        int round = 0;

        while (round < MAX_ROUNDS && !gameOver()) {
            for (Player currentPlayer : players) {
                boolean playerGetsAnotherTurn;
                do {
                    playerGetsAnotherTurn = currentPlayer.yourTurn(d1, d2);
                    ui.addGameState(board.getStartTile(), players);
                    ui.printState();

                    if (d1.getValue() != d2.getValue()) {
                        break;
                    }
                } while (playerGetsAnotherTurn);
            }
            round++;
        }
    }

    private boolean gameOver() {
        int bankruptPlayers = 0;
        for (Player p : players) {
            if (p.getFunds() <= 0) {
                return true;
            }
            if (p.getFunds() < 50) {
                bankruptPlayers++;
            }
        }
        return bankruptPlayers == players.size();
    }

    private void startGame() {
        boolean atLeastOnePlayer = false;

        while (true) {
            String playerName = ui.promptForNewPlayerName();

            if (playerName == null || playerName.trim().isEmpty()) {
                if (atLeastOnePlayer) {
                    break;
                } else {
                    System.out.println("ERROR: At least one human player is required!");
                    continue;
                }
            }

            Player joined = new HumanPlayer(board.getStartTile(), playerName, ui);
            players.add(joined);
            ui.playerJoined(joined.getName());
            atLeastOnePlayer = true;
        }

        AIPlayer aiPlayer = new AIPlayer(board.getStartTile(), ui);
        players.add(aiPlayer);
        ui.playerJoined(aiPlayer.getName());

        ui.addGameState(board.getStartTile(), players);
        ui.printState();
    }

    public void runGame() {
        play();
    }

    class AIPlayer extends HumanPlayer {
        public AIPlayer(Tile startTile, ConsoleUi ui) {
            super(startTile, ui, true);
        }

        @Override
        public boolean yourTurn(Dice d1, Dice d2) {
            return super.yourTurn(d1, d2);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.runGame();
    }
}

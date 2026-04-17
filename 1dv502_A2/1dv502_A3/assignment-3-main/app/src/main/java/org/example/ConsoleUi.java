package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Functionality to print the game state and player messages to the console,
 * also some console input helpers.
 */
public class ConsoleUi {
    public void showMessage(String message) {
        System.out.println(message);
    }


  StringBuffer messages = new StringBuffer();
  StringBuffer gameState = new StringBuffer();

  public enum Action {
    ROLL,
    BUY,
    None
  }

  
  public void playerMoves(String name, int d1, int d2) {
    addMessage(name, " moves " + d1 + " + " + d2 + " steps");
  }

  public void playerBuysProperty(String name, String propertyName) {
    addMessage(name, "bought " + propertyName);
  }

  
  public void playerPaysRentFor(String name, String propertyDesc, int rent) {
    addMessage(name, "Pays rent for: " + propertyDesc + "($" + rent + ")");
  }

  public void playerFundsDeduced(String name, int cost) {
    addMessage(name, "funds deduced with " + cost);
  }

  
  public void playerUnableToPay(String name, int cost) {
    addMessage(name, "not enough funds to pay " + cost);
  }

  public void playerReceivedFunds(String name, int sum) {
    addMessage(name, "received $" + sum);
  }

  
  public void playerJoined(String name) {
    addMessage(name, "joined the game");
  }

  
  public void addGameState(Tile start, Iterable<Player> players) {
    Tile t = start;

    do {
      addTile(t, players);

      t = t.getNext();
    } while (t != start);
  }

  private void addTile(Tile t, Iterable<Player> players) {
    StringBuffer tileStr = new StringBuffer();

    tileStr.append(addSpaces(30, t.toString()));

    for (Player p : players) {
      if (t.isOnTile(p)) {
        tileStr.append("\t");
        tileStr.append(getPlayerString(p));
      }
    }
    tileStr.append(System.lineSeparator());

    gameState.append(tileStr);
  }

  protected String getGameState() {
    return gameState.toString();
  }

  protected void clearGameState() {
    gameState = new StringBuffer();
  }

  private String addSpaces(int max, String str) {
    String ret = str;

    while (ret.length() < max) {
      ret = " " + ret;
    }

    return ret;
  }

  private void clearConsole() {
    for (int i = 0; i < 30; i++) {
      System.out.println();
    }
  }

  public void printState() {
    clearConsole();

    System.out.println(getGameState());
    System.out.println(getMessages());
    clearMessages();
    clearGameState();
  }

  public String promptForNewPlayerName() {
    System.out.print("Player name (empty when done): ");


    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset().name()));
      return reader.readLine();
    } catch (IOException e) {
      System.out.println(" Error reading input. Please try again.");
      return "";
  }
  
  }

  
  public void gameOver(Iterable<Player> players) {
    System.out.println("Game Over");
    ArrayList<Player> orderedPlayers = new ArrayList<>();
    players.forEach(p -> orderedPlayers.add(p));

    orderedPlayers.sort((p1, p2) -> p2.getFunds() - p1.getFunds());

    for (Player p : orderedPlayers) {
      System.out.println(getPlayerString(p));
    }

  }

  private String getPlayerString(Player p) {
    return p.getName() + " ($" + p.getFunds() + "$)";
  }



  private void addMessage(String playerName, String msg) {
    if (messages.length() > 0) {
      messages.append(System.lineSeparator());
    }
    messages.append("\tPlayer ").append(playerName).append(":").append(msg);
  }

  
  public Action promptForAction(String playerName) {
    System.out.println("It is your turn: " + playerName);
    System.out.print("r to roll dice, b to buy property");

    int c;

    do {
      c = getInputChar();
    } while (!(c == 'r' || c == 'b'));

    return switch (c) {
      case 'r' -> Action.ROLL;
      case 'b' -> Action.BUY;
      default -> Action.None;
  };
  
  }

  protected String getMessages() {
    return messages.toString();
  }

  protected void clearMessages() {
    messages = new StringBuffer();
  }

  private int getInputChar() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return c;
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return 0;
    }
  }

  public void startingGame() {
  }
}

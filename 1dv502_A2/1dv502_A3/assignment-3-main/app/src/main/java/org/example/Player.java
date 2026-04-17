package org.example;

/**
 * Interface to implement for all types of players.
 */
public interface Player {

  /**
   * Returns the funds of the player.
   */
  int getFunds();

  /**
   * Returns the name of the player.
   */
  String getName();

  /**
   * Handles the interaction when it is the player's turn.
   * Returns true if the player's turn is over.
   */
  boolean yourTurn(Dice d1, Dice d2);

  /**
   * Call to make the player pay rent for a specific property.
   * Funds should be deducted.
   * Returns the amount paid.
   */
  int payRent(int amount);

  /**
   * Deducts funds from the player for purchases.
   * Returns true if the player had enough funds.
   */
  boolean deduceFunds(int cost);

  /**
   * Adds funds to the player's balance.
   */
  void addFunds(int amount);

  /**
   * Retrieves the current tile of the player.
   */
  Tile getTile();

  /**
   * Updates the player's position.
   */
  void setTile(Tile newTile);

  /**
   * Attempts to buy a property.
   */
  void attemptToBuy(Property property);
}

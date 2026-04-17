package org.example;

import java.util.Random;

public class HumanPlayer implements Player {
    private final String name;
    private int funds;
    private Tile currentTile;
    private final ConsoleUi ui;
    private final boolean isAI;
    private final Random random;

    public HumanPlayer(Tile startTile, String name, ConsoleUi ui) {
        this.name = name;
        this.funds = 1500;
        this.currentTile = startTile;
        this.ui = ui;
        this.isAI = false;
        this.random = new Random();
    }

    public HumanPlayer(Tile startTile, ConsoleUi ui, boolean isAI) {
        this.name = isAI ? "AI-" + new Random().nextInt(1000) : "Player";
        this.funds = 1500;
        this.currentTile = startTile;
        this.ui = ui;
        this.isAI = isAI;
        this.random = new Random();
    }

    @Override
    public int getFunds() {
        return funds;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Tile getTile() {
        return currentTile;
    }

    @Override
    public void setTile(Tile newTile) {
        this.currentTile = newTile;
    }

    @Override
    public void addFunds(int amount) {
        this.funds += amount;
    }

    @Override
    public boolean deduceFunds(int cost) {
        if (funds >= cost) {
            funds -= cost;
            return true;
        }
        return false;
    }

    @Override
    public int payRent(int amount) {
        if (funds >= amount) {
            funds -= amount;
            return amount;
        } else {
            int paidAmount = funds;
            funds = 0;
            return paidAmount;
        }
    }

    @Override
    public boolean yourTurn(Dice d1, Dice d2) {
        if (isAI) {
            return aiTurn(d1, d2);
        }

        ConsoleUi.Action action = ui.promptForAction(name);
        switch (action) {
            case ROLL:
                int roll1 = d1.roll();
                int roll2 = d2.roll();
                ui.playerMoves(name, roll1, roll2);
                move(roll1 + roll2);
                return true;

            case BUY:
                if (currentTile instanceof Property && ((Property) currentTile).getOwner() == null) {
                    attemptToBuy((Property) currentTile);
                }
                return true;

            default:
                return true;
        }
    }

    @Override
    public void attemptToBuy(Property property) {
        if (property.buy(this)) {
            ui.showMessage(name + " bought " + property.getName());
        } else {
            ui.showMessage(name + " could not buy " + property.getName());
        }
    }

    private boolean aiTurn(Dice d1, Dice d2) {
        ui.showMessage(name + " (AI) is playing...");
        int roll1 = d1.roll();
        int roll2 = d2.roll();
        ui.playerMoves(name, roll1, roll2);
        move(roll1 + roll2);
        return true;
    }

    private void move(int steps) {
        for (int i = 0; i < steps; i++) {
            currentTile = currentTile.getNext();
        }
    }
}

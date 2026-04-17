
changes that i made in tile.java

tileConstructionTest_1()
in this test i changed next = this; prev = this; since single tile must loop back to itself. 

tileConstructionTest_2()
In this test i added this prevTile.next = this;   this.prev = prevTile; to ensure that the second tile is properly linked. 


tileConstructionTest_3()    the new tile is not correctly inserted into circular linked list. tile 2 and 3 do not correctly uppdate their nexr and proev pointers. 
In this test i added prevTile.next.prev = this; prevTile.next = this;  in order to maintain circular link.

tileConstructionTest_4() the same adding as tileConstructionTest_3 but this confirms that multiple insertions work correctly.

tileConstructionTest_5()    
added:   now the tile list properly supports multiple insertions. the circular list ramins intact even if more than 3 tiles adds
prevTile.next.prev = this;   
prevTile.next = this; 

tileConstructionTest_6()  
chnages i made: 
prevTile.next.prev = this;          ensure start alwasy point to the last tile
prevTile.next = this;      ensure start.next always point to correct tile
this.next = prevTile.next;      this will maintain circular list
this.prev = prevTile;              maintains circular linking


propertyTest_buy_ok()  I added conditions to check ownership, player location and funds. Deducted funds and set ownership. 


propertyTest_buy_hasOwner()    I added Checked if owner ==null berfore allowing purchase. 

propertyTest_buy_notOnTile()   I added Checked player.getTile() == this before allowing purchse.


propertyTest_buy_noFunds()   i added Checked if player.getFunds()>= price  before allowing purchase. 



Higher grade tasks:
Game.java
the game only allowed human players. there was no AI player. 
I  added AIPlayer (needed AI to play automatically) -->  AI now rolls dice, moves, and buy property. 

I created AIplayer as a subcalss of Humanplayer
AI logic was missing, if added inside HumanPlayer, it would have code duplication. thats why used yourTurn() logic to avoid duplicating. Reuses HumanPlayer method for AI behvaior. Makes AI act randomly while following the same structure. 

ensuring AI works in playGame() the AI needed to roll dice and move automatically. the original loop only worked for human players -->  ensures AI gets additional turn when rolling doubles. AI follows the same game rules as humans.


changes in HumanPlayer.java
Support for AI in HumanPlayer
Humanplayer only handled humanplayers. AI logic needed to be separate.

public HumanPlayer(Tile startTile, ConsoleUi ui, boolean isAI) {
    this.name = isAI ? "AI-" + new Random().nextInt(1000) : "Player";
    this.funds = 1500;
    this.currentTile = startTile;
    this.ui = ui;
    this.isAI = isAI;
    this.random = new Random();
}

Allows AI players to be created dynamically
AI names are randomized 
Avoids creating a completely separarete AI class.


Added behavior in yourTurn()
*AI did not automatically play its turn

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


AI now rolls dice and moves automatically
AI buys property if it is unowned
Follows human player logic but automatedd


Automatically takes Actions in aiTurn()
AI needed a method to handle decision making 

private boolean aiTurn(Dice d1, Dice d2) {
    ui.showMessage(name + " (AI) is playing...");
    int roll1 = d1.roll();
    int roll2 = d2.roll();
    ui.playerMoves(name, roll1, roll2);
    move(roll1 + roll2);
    return true;
}

AI now plays automatically without user input.




changes in ConsoleUI.java
improved input Handling 
if no players wered added, the game could not start
System.out.println(" ERROR: At least one human player is required!");
at least one player joins before starting




changes in propert.java
AI did not attempt to buy properties
@Override
public void attemptToBuy(Property property) {
    if (property.buy(this)) {
        ui.showMessage(name + " bought " + property.getName());
    } else {
        ui.showMessage(name + " could not buy " + property.getName());
    }
}
AI now follows buying rules like humans
































Higehr grade tasks:

I integrated Al logic directly into HumanPlayer.jva to avoid creating a separate ComputerPlayer.jva. I modified HumanPlayer.jva to handle both human and AI players by introducing an AI flag. for rolling dice and buying properties the AI player will make random decisions. 

  private final boolean isAI;    
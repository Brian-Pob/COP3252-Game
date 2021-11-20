import java.util.*;

public class Model {
     private Stack<Card> [] foundation, tableau;
     private Stack<Card> drawPile, discardPile;
     private List<Card> masterList;


    public Model() {
        // create the game

        foundation = new Stack[4];
        for (int i = 0; i < foundation.length; i++) {
            foundation[i] = new Stack<>();
        }

        tableau = new Stack[7];
        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = new Stack<>();
        }

        drawPile = new Stack<>();
        discardPile = new Stack<>();
        masterList = new ArrayList<>();




    }
    public void initializeGame(){
        // create cards

        List<EnumSuit> suitList = List.of(EnumSuit.values());
        List<EnumRank> rankList = List.of(EnumRank.values());
        List<EnumColor> colorList = List.of(EnumColor.values());


        for (EnumSuit suit: suitList) {
            for (EnumRank rank: rankList) {
                String imagePath = rank.toString().toLowerCase(Locale.ROOT) + "_of_" + suit.toString().toLowerCase(Locale.ROOT) + ".png";
                Card tempCard;

                if(suit == EnumSuit.SPADES || suit == EnumSuit.CLUBS)
                    tempCard = new Card(EnumColor.BLACK, suit, rank, imagePath);
                else
                    tempCard = new Card(EnumColor.RED, suit, rank, imagePath);

                masterList.add(tempCard);
            }
        }

        Collections.shuffle(masterList);

        // initialize tableau
        int k = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < i+1; j++) {
                tableau[i].push(masterList.get(k));
                k++;
            }
            tableau[i].peek().flip(); // setting the top card face up
        }

        // initialize draw pile with the remaining cards
        for (int i = k; i < masterList.size(); i++) {
            drawPile.push(masterList.get(i));
        }
    }

    public void testCount(){
        System.out.println("drawpile has " + drawPile.size() + " cards.");
        System.out.println("discard pile has " + discardPile.size() + " cards.");
        for (Stack<Card> s: tableau) {
            System.out.println();
            System.out.println("Stack size:" + s.size());
            System.out.println("Stack top:" + s.peek());
            System.out.println("Stack top flipped:" + s.peek().isFaceUp());
            for (Card c: s) {
                System.out.println(c + " " + c.isFaceUp());
            }
        }
    }

    public boolean isValidTableauMove(int tableauIndex, Card card){ // for single card
        if(tableauIndex < 0 || tableauIndex > 6)
            return false;
        if(tableau[tableauIndex].isEmpty())
            return card.getCardRank() == EnumRank.KING;
        Card topCard = tableau[tableauIndex].peek();
        return (topCard.getCardColor() != card.getCardColor()) && (topCard.getCardRank().getRankValue() - 1 == card.getCardRank().getRankValue());
    }

    // View should pass in the index of the tableau and all the face up cards in that stack
    public int isValidTableauStackMove(int tableauIndex, Stack<Card> cards){
        // for multiple cards
        // return index of the card that is valid
        // This is to know how many cards to move.
        for (int i = 0; i < cards.size(); i++) {
            if(isValidTableauMove(tableauIndex, cards.get(i)))
                return i;
        }

        // return -1 if no valid card
        return -1;
    }

    public boolean isValidFoundationMove(int foundationIndex, Card card){
        if(foundationIndex < 0 || foundationIndex > 3)
            return false;
        if(foundation[foundationIndex].isEmpty())
            return card.getCardRank() == EnumRank.ACE;
        Card topCard = foundation[foundationIndex].peek();
        return (topCard.getCardColor() != card.getCardColor()) && (topCard.getCardRank().getRankValue() == card.getCardRank().getRankValue() - 1);
    }

    public Card drawCard(){ // when drawpile is clicked and is not empty, draw card
        Card tempCard = drawPile.pop();
        tempCard.setFaceUp(true);
        discardPile.push(tempCard);
        return tempCard;
    }

    public void resetDrawPile(){ // when drawpile is clocked and is empty, reset drawpile
        for (int i = 0; i < discardPile.size(); i++) {
            drawPile.push(discardPile.pop()); // moves all cards from discard pile to draw pile
            drawPile.peek().setFaceUp(false); // sets the top card of the draw pile face down
                                              // will eventually set all cards face down
        }
    }

    public Stack<Card>[] getFoundation() {
        return foundation;
    }

    public Stack<Card>[] getTableau() {
        return tableau;
    }

    public void setTableau(Stack<Card>[] tableau) {
        this.tableau = tableau;
    }

    public Stack<Card> getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(Stack<Card> drawPile) {
        this.drawPile = drawPile;
    }

    public Stack<Card> getDiscardPile() {
        return discardPile;
    }

    public Card getDiscardPileTop() { return discardPile.peek();}

    public void setDiscardPile(Stack<Card> discardPile) {
        this.discardPile = discardPile;
    }


    public List<Card> getMasterList() {
        return masterList;
    }
}

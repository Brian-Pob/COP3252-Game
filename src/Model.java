import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                String filename = rank.toString().toLowerCase(Locale.ROOT) + "_of_" + suit.toString().toLowerCase(Locale.ROOT) + ".png";
                Card tempCard;

                if(suit == EnumSuit.SPADES || suit == EnumSuit.CLUBS)
                    tempCard = new Card(EnumColor.BLACK, suit, rank, filename);
                else
                    tempCard = new Card(EnumColor.RED, suit, rank, filename);

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
        }

        for (int i = k; i < masterList.size(); i++) {
            drawPile.push(masterList.get(i));
        }


    }
    public Stack<Card>[] getFoundation() {
        return foundation;
    }

    public void setFoundation(Stack<Card>[] foundation) {
        this.foundation = foundation;
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

    public void setDiscardPile(Stack<Card> discardPile) {
        this.discardPile = discardPile;
    }
}

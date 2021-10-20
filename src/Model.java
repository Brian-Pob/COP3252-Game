import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Model {
     private Stack<Card> [] foundation, tableau;
     private Stack<Card> drawPile, discardPile;
     private List<Card> masterList;


    public Model() {
        foundation = new Stack[4];
        tableau = new Stack[7];
        drawPile = new Stack<>();
        discardPile = new Stack<>();
        masterList = new ArrayList<>();

        // create the game


    }
    public void initializeGame(){
        // get EnumSuit content as list of strings
        List<String> suitNames = Stream.of(EnumSuit.values()).map(EnumSuit::name).collect(Collectors.toList());
        List<String> rankNames = Stream.of(EnumRank.values()).map(EnumRank::name).collect(Collectors.toList());
        List<String> colorNames = Stream.of(EnumColor.values()).map(EnumColor::name).collect(Collectors.toList());

        for (String name: suitNames
        ) {
            System.out.println(name);
        }
        for (String name: rankNames
             ) {
            System.out.println(name);
        }
        for (String name: colorNames
             ) {
            System.out.println(name);
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

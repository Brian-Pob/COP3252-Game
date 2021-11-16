import javax.swing.*;
import java.util.Objects;

public class Card {
    private EnumColor cardColor;
    private EnumSuit cardSuit;
    private EnumRank cardRank;
    final private ImageIcon cardImage;
    private boolean faceUp;


    public Card(EnumColor cardColor, EnumSuit cardSuit, EnumRank cardRank , String cardImage) {
        this.cardColor = cardColor;
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
        this.faceUp = false;
        this.cardImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/cards/" + cardImage)));
    }

    public EnumColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(EnumColor cardColor) {
        this.cardColor = cardColor;
    }

    public EnumSuit getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(EnumSuit cardSuit) {
        this.cardSuit = cardSuit;
    }

    public EnumRank getCardRank() {
        return cardRank;
    }

    public int getCardRankValue() {
        return cardRank.getRankValue();
    }

    public void setCardRank(EnumRank cardRank) {
        this.cardRank = cardRank;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flip() {
        this.faceUp = !this.faceUp;
    }

    public boolean setFaceUp(boolean faceUp) {
        return this.faceUp = faceUp;
    }

    public ImageIcon getCardImage() {return cardImage;}

    public boolean canPlaceOnTop(Card card) {
        return this.getCardRankValue() == card.getCardRankValue() + 1 && this.getCardColor() != card.getCardColor();
    }

    public boolean canPlaceOnBottom(Card card) {
        return this.getCardRankValue() == card.getCardRankValue() - 1 && this.getCardColor() != card.getCardColor();
    }

   //override toString()
    @Override
    public String toString() {
        return this.cardRank + " of " + this.cardSuit;
    }

}
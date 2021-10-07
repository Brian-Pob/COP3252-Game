public class Card {
    private EnumColor cardColor;
    private EnumSuit cardSuit;
    private EnumRank cardRank;
    //private Image cardImage;
    private boolean faceUp;


    public Card(EnumColor cardColor, EnumSuit cardSuit, EnumRank cardRank /*, ImageIcon cardImage*/) {
        this.cardColor = cardColor;
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
        this.faceUp = false;
        //this.cardImage = cardImage;
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

    public void setCardRank(EnumRank cardRank) {
        this.cardRank = cardRank;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void flip() {
        this.faceUp = true;
    }

}
public enum EnumSuit {
    SPADES,
    HEARTS,
    CLUBS,
    DIAMONDS
}
/*
List<Card> masterList = new List<Card>();

LIST foundation = STACK0[13], STACK1[13], STACK2[13], STACK3[13] ENDLIST
LIST tableau = STACK0, STACK1, STACK2, STACK3, ..., STACK6 ENDLIST
STACK<CARD> drawPile
STACK<CARD> discardPile


 List<String> nameList = Arrays.asList(new String[52]);
 int i = 0;



 for suit in EnumSuit{
    for rank in EnumRank{
        nameList[i] = rank.toString().toLower() + "_of_" + suit.toString().toLower() + ".png";
        i++;
    }
}
i = 0;

for suit in EnumSuit{
    for rank in EnumRank{
        if (suit == EnumSuit.SPADES || suit == EnumSuit.CLUBS)
            masterList.add(EnumColor.BLACK, suit, rank, FindAsset(nameList[i]));
        else
            masterList.add(EnumColor.RED, suit, rank, FindAsset(nameList[i]));
    }
}

ImageIcon FindAsset(String assetName){
    // find the image with the name assetName
    // return it as an ImageIcon
     return imagewiththename_assetName;
}

masterList = Collections.shuffle(masterList);

for (int i = 6; i >= 0; i--){
    for (int j = i; j >= 0; j--){
        tableau[i].insert(masterList.pop());
     }
 }
 //masterList will have a size of 24
 //pop remaining contents of masterList into drawPile

 for (int i = masterList.size(); i >= 0; i--){
    drawPile.insert(masterList.pop());
}

 //masterList will have size of 0
 //drawPile size of 24
 //tableau will have 1, 2, 3, 4, 5, 6, 7 = 28

 // for later
 // rules
 // moves
 // end states
 //     - win
 //     - lose



*/
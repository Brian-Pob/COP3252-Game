public class Controller {
    private Model model;
    private View view;
    private int buttonPressCounter;
    private int firstTableauButton;
    private int secondTableauButton;
    private int foundationPressed;
    private int discardPressed;

    public Controller(Model m, View v) {
        model = m;
        view = v;
        buttonPressCounter = 0;
        firstTableauButton = -1;
        secondTableauButton = -1;
        foundationPressed = 0;
        discardPressed = 0;
    }

    public void initializeGame(){
        model.initializeGame();
        view.initializeGame();
        model.printTableau();
    }

    public void testCount(){
        model.testCount();
    }

    public void pressButton(){
        buttonPressCounter++;
    }

    public void pressTableauButton(int index){
        System.out.println("\n\n\n---------------------\n\n\n");
        if (buttonPressCounter % 2 == 1){
            firstTableauButton = index;
        }
        else if (buttonPressCounter % 2 == 0){
            secondTableauButton = index;
            if(firstTableauButton != -1 && secondTableauButton != -1) {
                System.out.println("both buttons pressed");
                if (model.isValidTableauStackMove(firstTableauButton, secondTableauButton) != -1) {
                    System.out.println("valid move: " + model.isValidTableauStackMove(firstTableauButton, secondTableauButton));
                    for (int i = 0; i < model.isValidTableauStackMove(firstTableauButton, secondTableauButton); i++) {
                        model.moveTableauStackToTableau(firstTableauButton, secondTableauButton);
                        model.getTableau()[firstTableauButton].get(model.getTableau()[firstTableauButton].size() - 1).setFaceUp(true);
                    }
                } else {
                    System.out.println("Invalid move");
                }
            }else if(discardPressed == 1){
                System.out.println("discard pressed");
                if(model.isValidTableauMove(index, model.getDiscardPileTop())){
                    model.moveDiscardToTableau(index);
                }
            }
            firstTableauButton = -1;
            secondTableauButton = -1;
        }


//        model.printTableau();
        view.updateView(model);
    }

    public void pressFoundationButton(int foundationIndex){
        System.out.println("\n\n\n---------------------\n\n\n");
        if (buttonPressCounter % 2 == 1){
            // do something
            foundationPressed = 1;
        } else if (buttonPressCounter % 2 == 0){
            if (firstTableauButton != -1){
                model.isValidFoundationMove(firstTableauButton, model.getTableau()[foundationIndex].get(model.getTableau()[foundationIndex].size() - 1));
                model.moveTableauToFoundation(firstTableauButton, foundationIndex);
                if (model.getTableau()[firstTableauButton].size() != 0){
                    model.getTableau()[firstTableauButton].get(model.getTableau()[firstTableauButton].size() - 1).setFaceUp(true);
                }
                firstTableauButton = -1;
                secondTableauButton = -1;
            }else if(discardPressed == 1){
                if(model.isValidFoundationMove(foundationIndex, model.getDiscardPileTop())){
                    model.moveDiscardToFoundation(foundationIndex);

                }
            }
        }

//        model.printDiscardPile();
//        model.printFoundation();
        view.updateView(model);
    }

    public void pressDiscardButton(){
        if (buttonPressCounter % 2 == 1){
            // do something
            discardPressed = 1;
        } else if (buttonPressCounter % 2 == 0){
            discardPressed = 0;
        }
    }


}

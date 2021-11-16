public class Driver {
    public static void main(String[] args) {
        Model gameModel = new Model();
        View gameView = new View(gameModel);
        Controller gameController = new Controller(gameModel, gameView);

        gameController.initializeGame();
//        gameController.testCount();
    }
}

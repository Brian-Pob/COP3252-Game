public class Driver {
    public static void main(String[] args) {
        View gameView = new View();
        Model gameModel = new Model();
        Controller gameController = new Controller(gameModel, gameView);

        gameController.initializeGame();
        gameController.testCount();
    }
}

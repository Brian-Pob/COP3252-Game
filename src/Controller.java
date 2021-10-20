public class Controller {
    private Model model;
    private View view;

    public Controller(Model m, View v) {
        model = m;
        view = v;
    }

    public void initializeGame(){
        view.initializeGame();
        model.initializeGame();
    }
}

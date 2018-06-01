package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/* TODO Add mages
 * TODO Add things to spend rebirth points on
 * TODO Add "side scroller" champion screen
 * TODO equipment / monsters / drop tables
 * TODO Work on aesthetics
 * TODO Extend fighter training to the other 5 lines
 * TODO Add archer training
 * TODO Figure out the cap button*/

public class Main extends Application {

    Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = FXMLLoader.load(getClass().getResource("TheGameLayout.fxml"));
        controller = loader.getController();
        primaryStage.setScene(new Scene(root, 1000, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

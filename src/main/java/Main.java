
import controllers.DataStore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import javax.xml.crypto.Data;


/**
 * Created by samschofield on 7/03/17.
 */
public class Main extends Application {

    public void start(Stage stage) throws Exception {

        DataStore.loadData();
        System.out.println(DataStore.liveTrips);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("UC Ride Sharing System");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop(){
        // Save file
        DataStore.saveData();
    }

    public static void main(String args[]){
        launch (args);
    }
}

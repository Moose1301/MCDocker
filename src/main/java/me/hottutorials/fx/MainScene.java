package me.hottutorials.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import me.hottutorials.auth.impl.MojangAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainScene extends Application {

    private static final List<Region> panelsList = new ArrayList<>();



    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainScene.fxml"));
        Scene scene = new Scene(root);
        root.getStyleClass().add("dark-mode");
        root.setStyle("-fx-background-color: #474747;");

        registerPanels();

        stage.setWidth(900.0);
        stage.setHeight(600.0);
        stage.setMinWidth(900.0);
        stage.setMinHeight(600.0);
        stage.setTitle("MCDocker");
        stage.setScene(scene);

        stage.show();

        StackPane panels = (StackPane) scene.lookup("#panelContainer");
        for(Region panel : panelsList) {
            panel.setVisible(false);
            if(panel.getClass() == HomeScene.class) panel.setVisible(true);
            panels.getChildren().add(panel);
        }

    }

    public static List<Region> getPanelsList() {
        return panelsList;
    }

    private void registerPanels() {
        register(new HomeScene());
        register(new ContainersScene());
    }

    private void register(Region panel) {
        if(panelsList.contains(panel)) return;
        panelsList.add(panel);
    }


}

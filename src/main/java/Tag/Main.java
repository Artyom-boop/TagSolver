package Tag;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Scanner;

public class Main extends Application {
    private Parent fieldTexture;
    private Group root = new Group();
    private Scene scene = new Scene(root);

    private void setFieldTexture(Parent fieldTexture) {
        this.fieldTexture = fieldTexture;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scanner in = new Scanner(System.in);
        Field field = new Field();
        System.out.println("Введите размер поля -");
        field.startField(in.nextInt());
        System.out.println(field.toString());
        setFieldTexture(FXMLLoader.load(getClass().getResource("/sample.fxml")));
        primaryStage.setTitle("Tag");
        InputStream iconStream = getClass().getResourceAsStream("/texture/icon.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        root.getChildren().add(fieldTexture);
        if (field.getBlocks().length == 4)
            root.getChildren().add(View.draw(field.getBlocks()));
        primaryStage.setScene(scene);
        primaryStage.show();
        Controller controller = new Controller();
        controller.start(scene, root, field);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package Tag;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.InputStream;

class View {

    static Group draw(int[][] field) throws IOException {
        Group group = new Group();
        group.getChildren().add(FXMLLoader.load(View.class.getResource("/sample.fxml")));
        for (int i = 0; i < field.length; i++)
            for (int j = 0; j < field.length; j++) {
                int el = field[i][j];
                if (field[i][j] == 0)
                    continue;
                InputStream block = View.class.getResourceAsStream("/texture/" + el + ".png");
                Image img = new Image(block);
                ImageView imageView = new ImageView(img);
                imageView.setLayoutX(i * 100);
                imageView.setLayoutY(20 + j * 100);
                group.getChildren().add(imageView);
            }
        return group;
    }
}

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
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                if (field[i][j] == 0)
                    continue;
                InputStream block = View.class.getResourceAsStream("/texture/" + field[i][j] + ".png");
                Image img = new Image(block);
                ImageView imageView = new ImageView(img);
                imageView.setLayoutX(i * 100);
                imageView.setLayoutY(20 + j * 100);
                group.getChildren().add(imageView);
            }
        return group;
    }
}

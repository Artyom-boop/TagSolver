package Tag;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import Tag.Solver.SolverField;
import Tag.Solver.TagSolver;
import javafx.scene.input.KeyEvent;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Controller {
    private Field fieldControl = new Field();

    void start(Scene scene,  Group root, Field field) {
        Deque<SolverField> stack = new ArrayDeque<>();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            int score1 = 0;
            @Override
            public void handle(KeyEvent event) {
                {
                    if (event.getCode() == KeyCode.P) {
                        JOptionPane.showMessageDialog(null, "Управление:\n" +
                                " WASD или стрелочки - перемещение блоков\n R - перемешать пятнашки\n F - решить пятнашки\n" +
                                "G - вывести ход решения");

                    }
                    if (event.getCode() == KeyCode.G) {
                        if (!stack.isEmpty()) {
                            SolverField current = stack.pollLast();
                            view(current.getBlocks(), root);
                            field.setBlocks(current.getBlocks());
                            score1++;
                        }
                    }
                    if (event.getCode() == KeyCode.F) {
                        stack.clear();
                        SolverField init = new SolverField(field.getBlocks());
                        TagSolver solver = new TagSolver();
                        solver.solver(init);
                        stack.addAll(solver.getResult());
                        stack.pollLast();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Решение найдено");
                        alert.setHeaderText("Решение найдено за " + (solver.getResult().size() - 1) + " ходов");
                        alert.setContentText("Для просмотра ходов решения нажмите клавишу G");
                        alert.showAndWait();
                    }
                    if (event.getCode() == KeyCode.R) {
                        field.startField(field.getBlocks().length);
                        field.randomField();
                        view(field.getBlocks(), root);
                        score1 = 0;
                    }
                    if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                        score1++;
                        field.down();
                        view(field.getBlocks(), root);
                    }
                    if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                        score1++;
                        field.up();
                        view(field.getBlocks(), root);
                    }
                    if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                        score1++;
                        field.right();
                        view(field.getBlocks(), root);
                    }
                    if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                        score1++;
                        field.left();
                        view(field.getBlocks(), root);
                    }
                    if (field.equals(fieldControl)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Победа!!!");
                        alert.setHeaderText("Вы выйграли");
                        alert.setContentText("Счёт - " + score1);
                        score1 = 0;
                        alert.showAndWait();
                    }
                    String str = "Score: " + score1;
                    Label score = new Label(str);
                    score.minWidth(50);
                    score.minHeight(20);
                    score.setLayoutX(14);
                    score.setText(str);
                    root.getChildren().add(score);
                }
            }
        });
    }

    private void view(int[][] blocks, Group root) {
        if (blocks.length != 4) {
            Field field = new Field();
            field.setBlocks(blocks);
            System.out.println(field.toString());
            return;
        }
        Group group = null;
        try {
            group = View.draw(blocks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        root.getChildren().add(group);
    }
}

package Tag.Solver;

import Tag.Field;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TagSolverTest {

    @Test
    void TagSolver() {
        Field field = new Field();
        int lowerLimit = 2; // Начальное значение диапазона - "от"
        int topLimit = 9; // Конечное значение диапазона - "до"
        for (int i = 2; i < 11; i++) {
            int sizeField = lowerLimit + (int) (Math.random() * topLimit); // Генерация числа
            System.out.println("Размер поля -" + sizeField);
            field.startField(sizeField);
            int[][] exp = arrCopy(field.getBlocks());
            field.randomField();
            assertNotSame(exp, field.getBlocks());
            System.out.println(field.toString());
            SolverField init = new SolverField(field.getBlocks());
            TagSolver solver = new TagSolver();
            solver.solver(init);
            List<SolverField> path = new ArrayList<>(solver.getResult());
            System.out.println(" Колличество ходов - " + path.size() +"\n" + " Путь к решению - ");
            StringBuilder str = new StringBuilder();
            for (int j = path.size() - 1; j > 0; j--) {
                SolverField solverField = path.get(j);
                SolverField solverFieldNext = path.get(j - 1);
                str.append(solverFieldNext.toString()).append("\n");
                Pair<Integer, Integer> indexZero = solverField.searchArr(solverField.getBlocks(), 0);
                Pair<Integer, Integer> indexZeroNext = solverField.searchArr(solverFieldNext.getBlocks(), 0);
                assertTrue((indexZero.getKey().equals(indexZeroNext.getKey()) &&
                        (indexZero.getValue().equals(indexZeroNext.getValue() + 1) ||
                                indexZero.getValue().equals(indexZeroNext.getValue() - 1))) ||
                        (indexZero.getValue().equals(indexZeroNext.getValue()) &&
                                (indexZero.getKey().equals(indexZeroNext.getKey() + 1) ||
                                        indexZero.getKey().equals(indexZeroNext.getKey() - 1))) );
            }
            assertArrayEquals(exp, path.get(0).getBlocks());
            System.out.print(str);
            System.out.println("Успешно!\n");
        }
    }

    private static int[][] arrCopy(int[][] arr) {
        if (arr == null) {
            return null;
        }
        int[][] result = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            result[i] = new int[arr[i].length];
            System.arraycopy(arr[i], 0, result[i], 0, arr[i].length);
        }
        return result;
    }
}
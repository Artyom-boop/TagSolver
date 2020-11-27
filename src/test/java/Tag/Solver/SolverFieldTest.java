package Tag.Solver;

import Tag.Field;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverFieldTest {

    @Test
    void manhattan() {
        Field field = new Field();
        SolverField solverField = new SolverField(field.blocks);
        assertEquals(0, solverField.getManhattan());
        field.right();
        field.right();
        field.down();
        solverField = new SolverField(field.blocks);
        assertEquals(3, solverField.getManhattan());
        field.setBlocks(new int[][] {{1, 11, 10, 5}, {2, 9, 13, 0}, {4, 7, 14, 6}, {8, 3, 12, 15}});
        solverField = new SolverField(field.blocks);
        assertEquals(20, solverField.getManhattan());
        field.setBlocks(new int[][] {{2, 0, 1, 11}, {9, 13, 12, 10}, {15, 7, 5, 14}, {4, 3, 8, 6}});
        solverField = new SolverField(field.blocks);
        assertEquals(29, solverField.getManhattan());
        field.setBlocks(new int[][] {{10, 1, 0, 9}, {4, 7, 12, 6}, {2, 3, 5, 14}, {8, 13, 11, 15}});
        solverField = new SolverField(field.blocks);
        assertEquals(26, solverField.getManhattan());

    }


    @Test
    void searchArr() {
        for (int k = 0; k < 100; k++) {
            int size = (int) Math.round((Math.random() * 100)) + 4;
            int el = (int) Math.round((Math.random() * size));
            Field field = new Field();
            field.startField(size);
            field.randomField();
            SolverField solverField = new SolverField(field.blocks);
            Pair<Integer, Integer> pos = solverField.searchArr(solverField.blocks, el);
            assertEquals(el, solverField.blocks[pos.getValue()][pos.getKey()]);
        }
    }

    @Test
    void adjacentCells() {
        for (int k = 0; k < 100; k++) {
            int size = (int) Math.round((Math.random() * 100)) + 4;
            Field field = new Field();
            field.startField(size);
            field.randomField();
            SolverField solverField = new SolverField(field.blocks);
            int delta;
            for (SolverField solverField1 : solverField.adjacentCells()) {
                if (solverField1 != null) {
                    delta = Math.abs(solverField1.getManhattan() - solverField.getManhattan());
                    assertEquals(1, delta);
                }
            }
        }
    }
}
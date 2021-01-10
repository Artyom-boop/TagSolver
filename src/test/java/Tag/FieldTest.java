package Tag;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    private Field field = new Field();

    @Test
    void setBlocks() {
        field = new Field();
        for (int k = 0; k < 100; k++) {
            int size = (int) Math.round((Math.random() * 100)) + 2;
            int[][] array = new int[size][size];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++)
                    array[i][j] = (int) Math.round((Math.random() * 1000));
            }
            field.setBlocks(array);
            assertArrayEquals(array, field.getBlocks());
        }
    }

    @Test
    void startField() {
        field = new Field();
        field.startField(4);
        assertArrayEquals(field.getBlocks(),new int[][] {{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 0}});
        field.startField(5);
        assertArrayEquals(field.getBlocks(),new int[][] {{1, 6, 11, 16, 21}, {2, 7, 12, 17, 22}, {3, 8, 13, 18, 23},
                {4, 9, 14, 19, 24}, {5, 10, 15, 20, 0}});
        field.startField(8);
        assertArrayEquals(field.getBlocks(),new int[][] {{1, 9, 17, 25, 33, 41, 49, 57}, {2, 10, 18, 26, 34, 42, 50, 58},
                {3, 11, 19, 27, 35, 43, 51, 59}, {4, 12, 20, 28, 36, 44, 52, 60}, {5, 13, 21, 29, 37, 45, 53, 61},
                {6, 14, 22, 30, 38, 46, 54, 62}, {7, 15, 23, 31, 39, 47, 55, 63}, {8, 16, 24, 32, 40, 48, 56, 0}});
        assertThrows(IllegalArgumentException.class, () -> field.startField(0));
    }

    @Test
    void move() {
        field = new Field();
        field.right();
        assertArrayEquals(field.getBlocks(),new int[][] {{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 0}, {4, 8, 12, 15}});
        field.right();
        assertArrayEquals(field.getBlocks(),new int[][] {{1, 5, 9, 13}, {2, 6, 10, 0}, {3, 7, 11, 14}, {4, 8, 12, 15}});
        field.down();
        assertArrayEquals(field.getBlocks(),new int[][] {{1, 5, 9, 13}, {2, 6, 0, 10}, {3, 7, 11, 14}, {4, 8, 12, 15}});
        field.left();
        assertArrayEquals(field.getBlocks(),new int[][] {{1, 5, 9, 13}, {2, 6, 11, 10}, {3, 7, 0, 14}, {4, 8, 12, 15}});
        field.up();
        assertArrayEquals(field.getBlocks(),new int[][] {{1, 5, 9, 13}, {2, 6, 11, 10}, {3, 7, 14, 0}, {4, 8, 12, 15}});
        field.up();
        assertArrayEquals(field.getBlocks(),new int[][] {{1, 5, 9, 13}, {2, 6, 11, 10}, {3, 7, 14, 0}, {4, 8, 12, 15}});
    }

    @Test
    void randomField() {
        for (int i = 0; i < 100; i++) {
            field = new Field();
            Field fieldRand = new Field();
            int size = (int) Math.round((Math.random() * 100)) + 4;
            field.startField(size);
            fieldRand.startField(size);
            fieldRand.randomField();
            assertNotEquals(field, fieldRand);
            field.randomField();
            assertNotEquals(field, fieldRand);
        }
    }
}
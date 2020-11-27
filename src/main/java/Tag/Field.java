package Tag;


public class Field {
    public int[][] blocks = new int[][]{{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 0}};
    private int indexZeroI = 3;
    private int indexZeroJ = 3;

    public void setBlocks(int[][] blocks) {
        this.blocks = arrCopy(blocks);
        for (int i = 0; i < blocks.length; i++)
            for (int j =0; j < blocks.length; j++) {
                if (blocks[i][j] == 0) {
                    indexZeroI = i;
                    indexZeroJ = j;
                }
            }
    }

    public void startField(int size) {
        if (size <= 0)
            throw new  IllegalArgumentException();
        this.blocks = new int[size][size];
        int number = 1;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                blocks[j][i] = number;
                number++;
            }
        }
        indexZeroI = size - 1;
        indexZeroJ = indexZeroI;
        blocks[indexZeroI][indexZeroJ] = 0;
    }

    public void up() {
        if (indexZeroJ < blocks.length - 1) {
            blocks[indexZeroI][indexZeroJ] = blocks[indexZeroI][indexZeroJ + 1];
            blocks[indexZeroI][indexZeroJ + 1] = 0;
            indexZeroJ++;
        }
    }

    public void down() {
        if (indexZeroJ > 0) {
            blocks[indexZeroI][indexZeroJ] = blocks[indexZeroI][indexZeroJ - 1];
            blocks[indexZeroI][indexZeroJ - 1] = 0;
            indexZeroJ--;
        }
    }

    public void left() {
        if (indexZeroI < blocks.length - 1) {
            blocks[indexZeroI][indexZeroJ] = blocks[indexZeroI + 1][indexZeroJ];
            blocks[indexZeroI + 1][indexZeroJ] = 0;
            indexZeroI++;
        }
    }


    public void right() {
        if (indexZeroI > 0) {
            blocks[indexZeroI][indexZeroJ] = blocks[indexZeroI - 1][indexZeroJ];
            blocks[indexZeroI - 1][indexZeroJ] = 0;
            indexZeroI--;
        }
    }

    public void randomField (){
        int lowerLimit = 0; // Начальное значение диапазона - "от"
        int topLimit = 4; // Конечное значение диапазона - "до"
        int random;
        for (int i = 0; i <= 100; i ++) {
            random = lowerLimit + (int) (Math.random() * topLimit); // Генерация числа
            switch (random) {
                case (0): {
                    Field.this.down();
                    break;
                }
                case (1): {
                    Field.this.left();
                    break;
                }
                case (2): {
                    Field.this.up();
                    break;
                }
                case (3): {
                    Field.this.right();
                    break;
                }
                default: {
                    break;
                }
            }
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Field field = (Field) obj;
        if (field.blocks.length != this.blocks.length) return false;
        for (int i = 0; i < this.blocks.length; i++) {
            for (int j = 0; j < this.blocks[i].length; j++) {
                if (this.blocks[i][j] != field.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < blocks.length; i++) {
            for (int[] block : blocks) {
                s.append(" ").append(block[i]);
            }
            s.append("\n");
        }
        return s.toString();
    }
}

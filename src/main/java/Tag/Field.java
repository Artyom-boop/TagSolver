package Tag;


class Field {

    int[][] blocks = {{1, 5, 9, 13},{2, 6, 10, 14},{3, 7, 11, 15},{4, 8, 12, 0}};
    private int indexZeroI = 3;
    private int indexZeroJ = 3;

    void setBlocks(int[][] blocks) {
        this.blocks = blocks;
        for (int i = 0; i < blocks.length; i++)
            for (int j =0; j < blocks.length; j++) {
                if (blocks[i][j] == 0) {
                    indexZeroI = i;
                    indexZeroJ = j;
                }
            }
    }

    void up () {
        if (indexZeroJ < blocks.length - 1) {
            blocks[indexZeroI][indexZeroJ] = blocks[indexZeroI][indexZeroJ + 1];
            blocks[indexZeroI][indexZeroJ + 1] = 0;
            indexZeroJ++;
        }
    }

    void down () {
        if (indexZeroJ > 0) {
            blocks[indexZeroI][indexZeroJ] = blocks[indexZeroI][indexZeroJ - 1];
            blocks[indexZeroI][indexZeroJ - 1] = 0;
            indexZeroJ--;
        }
    }

    void left () {
        if (indexZeroI < blocks.length - 1) {
            blocks[indexZeroI][indexZeroJ] = blocks[indexZeroI + 1][indexZeroJ];
            blocks[indexZeroI + 1][indexZeroJ] = 0;
            indexZeroI++;
        }
    }

    void right () {
        if (indexZeroI > 0) {
            blocks[indexZeroI][indexZeroJ] = blocks[indexZeroI - 1][indexZeroJ];
            blocks[indexZeroI - 1][indexZeroJ] = 0;
            indexZeroI--;
        }
    }

    void randomField (){
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
}

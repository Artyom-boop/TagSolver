package Tag.Solver;

import Tag.Field;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class SolverField {
    public int[][] blocks;
    private int indexZeroX;
    private int indexZeroY;
    private int manhattan;

    public SolverField(int[][] blocks) {
        Field goal = new Tag.Field();
        if (blocks.length != 4) {
            goal.startField(blocks.length);
        }
        this.blocks = arrCopy(blocks);
        manhattan = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                int el = blocks[j][i];
                if (el > 0 && el != goal.blocks[j][i]) {
                    Pair<Integer, Integer> pair = searchArr(goal.blocks,el);
                    manhattan += Math.abs(i - pair.getKey()) + Math.abs(j - pair.getValue());
                }
                if (blocks[i][j] == 0) {
                    indexZeroX = i;
                    indexZeroY = j;
                }
            }
        }
    }

    Pair<Integer, Integer> searchArr(int[][] goal, int el) {
        Pair<Integer, Integer> pair = null;
        for (int i = 0; i < goal.length; i++) {
            for (int j = 0; j < goal.length; j++) {
                if (goal[j][i] == el)
                    pair = new Pair<>(i, j);
            }
        }
        return pair;
    }

    int getManhattan() {
        return manhattan;
    }

    boolean isGoal() {
        return manhattan == 0;
    }

    Set<SolverField> adjacentCells() {
        Set<SolverField> solverFieldList = new HashSet<>();
        solverFieldList.add(adjacentCell(arrCopy(blocks), indexZeroX, indexZeroY, indexZeroX, indexZeroY + 1));
        solverFieldList.add(adjacentCell(arrCopy(blocks), indexZeroX, indexZeroY, indexZeroX, indexZeroY - 1));
        solverFieldList.add(adjacentCell(arrCopy(blocks), indexZeroX, indexZeroY, indexZeroX - 1, indexZeroY));
        solverFieldList.add(adjacentCell(arrCopy(blocks), indexZeroX, indexZeroY, indexZeroX + 1, indexZeroY));
        return solverFieldList;
    }

    private SolverField adjacentCell(int[][] blocks2, int x1, int y1, int x2, int y2) {
        if (x2 > -1 && x2 < blocks2.length && y2 > -1 && y2 < blocks2.length) {
            int t = blocks2[x2][y2];
            blocks2[x2][y2] = blocks2[x1][y1];
            blocks2[x1][y1] = t;
            return new SolverField(blocks2);
        } else
            return null;
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
        SolverField solverField = (SolverField) obj;
        if (solverField.blocks.length != this.blocks.length) return false;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] != solverField.blocks[i][j]) {
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


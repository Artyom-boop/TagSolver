package Tag.Solver;

import java.util.*;

public class TagSolver {

    private Deque<SolverField> result = new ArrayDeque<>();

    public Deque<SolverField> getResult() {
        return result;
    }

    private static class solutionPath {
        private solutionPath previousBoard;
        private SolverField solverField;

        private solutionPath(solutionPath previousBoard, SolverField solverField) {
            this.previousBoard = previousBoard;
            this.solverField = solverField;
        }

        SolverField getSolverField() {
            return solverField;
        }
    }

    public TagSolver(SolverField startSolverField) {
        PriorityQueue<solutionPath> priority = new PriorityQueue<>(Comparator.comparingInt(TagSolver::heuristicFunction));
        solutionPath start = new solutionPath(null, startSolverField);
        int maxF;
        priority.add(start);
        while (!priority.isEmpty()){
            solutionPath current = priority.poll();
            assert current != null;
            maxF = lengthPath(current);
            if (maxF > 110)
                continue;
            if(current.solverField.isGoal()) {
                toStack(new solutionPath(current, current.solverField));
                return;
            }
            for (SolverField solverField1 : current.solverField.adjacentCells()) {
                if (solverField1 != null && !contains(current, solverField1)) {
                    solutionPath path = new solutionPath(current, solverField1);
                    priority.add(path);
                }
            }
        }
    }

    private static int lengthPath(solutionPath solutionPath){
        solutionPath solutionPath1 = solutionPath.previousBoard;
        int g = 0;
        while (solutionPath1 != null) {
            g++;
            solutionPath1 = solutionPath1.previousBoard;
        }
        return g;
    }

    private static int heuristicFunction(solutionPath solutionPath){

        return solutionPath.getSolverField().getManhattan() + lengthPath(solutionPath);
    }

    private void toStack(solutionPath solutionPath){
        solutionPath solutionPath2 = solutionPath.previousBoard;
        while (solutionPath2 != null){
            result.add(solutionPath2.solverField);
            solutionPath2 = solutionPath2.previousBoard;
        }
    }

    private boolean contains(solutionPath solutionPath, SolverField solverField){
        solutionPath solutionPath1 = solutionPath;
        while (solutionPath1 != null) {
            if (solutionPath1.solverField.equals(solverField))
                return true;
            solutionPath1 = solutionPath1.previousBoard;
        }
        return false;
    }
}



package lab4.swingApp;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class GameRandomized {

    private static ArrayList<Integer> tiles = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));

    public static ArrayList<Integer> randomizeTile() {

        boolean isSolvable = false;

        while (!isSolvable) {
            Collections.shuffle(tiles);

            isSolvable = checkIfSolvable(tiles);
        }

        return tiles;

    }

    private static boolean checkIfSolvable(ArrayList<Integer> puzzle) {

        puzzle.add(0);

        int parity = 0;
        int gridWidth = (int) Math.sqrt(puzzle.size());
        int row = 0;
        int blankRow = 0;

        for (int i = 0; i < puzzle.size(); i++)
        {
            if (i % gridWidth == 0) {
                row++;
            }
            if (puzzle.get(i) == 0) {
                blankRow = row;
                continue;
            }
            for (int j = i + 1; j < puzzle.size(); j++)
            {
                if (puzzle.get(i) > puzzle.get(j) && puzzle.get(j) != 0)
                {
                    parity++;
                }
            }
        }

        puzzle.remove(Integer.valueOf(0));

        if (gridWidth % 2 == 0) {
            if (blankRow % 2 == 0) {
                return parity % 2 == 0;
            } else {
                return parity % 2 != 0;
            }
        } else {
            return parity % 2 == 0;
        }

    }
}

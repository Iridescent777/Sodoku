package sudoku;
import java.util.*;
public class Puzzle {

    int[][] numbers = new int[GameBoardPanel.GRID_SIZE][GameBoardPanel.GRID_SIZE];
    boolean[][] isGiven = new boolean[GameBoardPanel.GRID_SIZE][GameBoardPanel.GRID_SIZE];
    //private static final int NUM_PUZZLES = 10;
    private int puzzle_selection = 0;



    // Constructor
    public Puzzle() {
        super();
    }


    // Generate a new puzzle given the number of cells to be guessed, which can be used
    //  to control the difficulty level.
    // This method shall set (or update) the arrays numbers and isGiven

    public void newPuzzle(int difficulty_reference_index) {
        // I hardcode a puzzle here for illustration and testing.

        Random rand = new Random();
        puzzle_selection = rand.nextInt(10);

        int[][][] hardcodedNumbers = {
                {
                        {5, 3, 4, 6, 7, 8, 9, 1, 2},
                        {6, 7, 2, 1, 9, 5, 3, 4, 8},
                        {1, 9, 8, 3, 4, 2, 5, 6, 7},
                        {8, 5, 9, 7, 6, 1, 4, 2, 3},
                        {4, 2, 6, 8, 5, 3, 7, 9, 1},
                        {7, 1, 3, 9, 2, 4, 8, 5, 6},
                        {9, 6, 1, 5, 3, 7, 2, 8, 4},
                        {2, 8, 7, 4, 1, 9, 6, 3, 5},
                        {3, 4, 5, 2, 8, 6, 1, 7, 9}
                },
                {

                        {1, 2, 6, 4, 3, 7, 8, 5, 9},
                        {4, 5, 7, 1, 8, 9, 3, 2, 6},
                        {3, 8, 9, 2, 5, 6, 7, 4, 1},
                        {2, 6, 3, 7, 9, 8, 5, 1, 4},
                        {7, 4, 8, 5, 1, 2, 6, 9, 3},
                        {5, 9, 1, 6, 4, 3, 2, 7, 8},
                        {9, 7, 4, 8, 6, 5, 1, 3, 2},
                        {8, 3, 5, 9, 2, 1, 4, 6, 7},
                        {6, 1, 2, 3, 7, 4, 9, 8, 5}
                },
                {

                        {3, 4, 5, 2, 7, 8, 1, 6, 9},
                        {1, 7, 8, 9, 4, 6, 5, 2, 3},
                        {6, 9, 2, 5, 3, 1, 8, 7, 4},
                        {2, 3, 6, 7, 5, 4, 9, 1, 8},
                        {8, 1, 9, 3, 6, 2, 7, 4, 5},
                        {7, 5, 4, 8, 1, 9, 2, 3, 6},
                        {4, 6, 1, 7, 9, 5, 3, 8, 2},
                        {9, 2, 3, 4, 8, 7, 6, 5, 1},
                        {5, 8, 7, 1, 2, 3, 4, 9, 6},
                },
                {


                        {9, 4, 2, 1, 6, 3, 8, 5, 7},
                        {5, 3, 6, 2, 8, 7, 9, 4, 1},
                        {8, 7, 1, 9, 5, 4, 2, 3, 6},
                        {3, 2, 7, 8, 1, 9, 4, 6, 5},
                        {1, 5, 4, 3, 2, 6, 7, 9, 8},
                        {6, 9, 8, 7, 4, 5, 1, 2, 3},
                        {2, 6, 5, 4, 7, 1, 3, 8, 9},
                        {7, 8, 9, 6, 3, 2, 5, 1, 4},
                        {4, 1, 3, 5, 9, 8, 6, 7, 2},
                },
                {


                        {9, 1, 7, 3, 6, 5, 8, 2, 4},
                        {3, 6, 2, 8, 4, 7, 5, 1, 9},
                        {8, 5, 4, 9, 1, 2, 6, 7, 3},
                        {1, 8, 5, 6, 7, 3, 4, 9, 2},
                        {2, 4, 6, 1, 9, 8, 7, 3, 5},
                        {7, 3, 9, 2, 5, 4, 1, 6, 8},
                        {4, 9, 1, 5, 3, 6, 2, 8, 7},
                        {5, 2, 8, 7, 1, 9, 3, 4, 6},
                        {6, 7, 3, 4, 8, 2, 9, 5, 1},

                },
                {

                        {3, 8, 7, 4, 9, 1, 6, 2, 5},
                        {2, 4, 1, 5, 6, 8, 3, 7, 9},
                        {5, 6, 9, 3, 2, 7, 4, 1, 8},
                        {7, 5, 8, 6, 1, 9, 2, 3, 4},
                        {1, 2, 3, 7, 8, 4, 5, 9, 6},
                        {4, 9, 6, 2, 5, 3, 1, 8, 7},
                        {9, 3, 4, 1, 7, 6, 8, 5, 2},
                        {6, 7, 5, 8, 3, 2, 9, 4, 1},
                        {8, 1, 2, 9, 4, 5, 7, 6, 3},

                },
                {


                        {4, 3, 5, 2, 6, 9, 7, 8, 1},
                        {6, 8, 2, 5, 7, 1, 4, 9, 3},
                        {1, 9, 7, 8, 3, 4, 5, 6, 2},
                        {8, 2, 6, 1, 9, 5, 3, 4, 7},
                        {3, 7, 4, 6, 8, 2, 9, 1, 5},
                        {9, 5, 1, 7, 4, 3, 6, 2, 8},
                        {5, 1, 9, 3, 2, 6, 8, 7, 4},
                        {2, 4, 8, 9, 5, 7, 1, 3, 6},
                        {7, 6, 3, 4, 1, 8, 2, 5, 9},
                },
                {


                        {7, 3, 6, 4, 5, 2, 9, 8, 1},
                        {1, 9, 8, 6, 3, 7, 4, 5, 2},
                        {4, 2, 5, 9, 8, 1, 3, 7, 6},
                        {3, 6, 4, 5, 2, 8, 1, 9, 7},
                        {9, 5, 2, 7, 1, 4, 6, 3, 8},
                        {8, 1, 7, 3, 9, 6, 2, 4, 5},
                        {2, 8, 9, 1, 7, 3, 5, 6, 4},
                        {6, 7, 3, 2, 4, 5, 8, 1, 9},
                        {5, 4, 1, 8, 6, 9, 7, 2, 3},

                },
                {

                        {8, 1, 2, 7, 5, 3, 6, 4, 9},
                        {9, 4, 3, 6, 8, 2, 1, 7, 5},
                        {6, 7, 5, 4, 9, 1, 2, 8, 3},
                        {1, 5, 4, 2, 3, 7, 8, 9, 6},
                        {3, 6, 9, 8, 4, 5, 7, 2, 1},
                        {2, 8, 7, 1, 6, 9, 5, 3, 4},
                        {5, 2, 1, 9, 7, 4, 3, 6, 8},
                        {4, 3, 8, 5, 2, 6, 9, 1, 7},
                        {7, 9, 6, 3, 1, 8, 4, 5, 2},

                },
                {

                        {9, 1, 2, 6, 8, 7, 3, 4, 5},
                        {3, 5, 8, 2, 1, 4, 7, 9, 6},
                        {6, 7, 4, 3, 9, 5, 2, 8, 1},
                        {8, 9, 5, 1, 7, 2, 4, 6, 3},
                        {2, 3, 6, 4, 5, 8, 9, 1, 7},
                        {7, 4, 1, 9, 6, 3, 5, 2, 8},
                        {5, 6, 3, 8, 4, 9, 1, 7, 2},
                        {4, 8, 7, 5, 2, 1, 6, 3, 9},
                        {1, 2, 9, 7, 3, 6, 8, 5, 4},

                }};




        // Copy from hardcodedNumbers into the array "numbers"
        for (int row = 0; row < GameBoardPanel.GRID_SIZE; ++row) {
            for (int col = 0; col < GameBoardPanel.GRID_SIZE; ++col) {
                numbers[row][col] = hardcodedNumbers[puzzle_selection][row][col];
            } // 随机选取一个数独矩阵
        }


        // Need to use input parameter cellsToGuess!
        // Hardcoded for testing, only 2 cells of "8" is NOT GIVEN
        boolean[][] hardcodedIsGiven =
                {{true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true},
                        {true, true, true, true, true, true, true, true, true}};


        int[] oneDArray = new int[hardcodedIsGiven.length * hardcodedIsGiven[0].length];
        int index = 0;
        for (int i = 0; i < hardcodedIsGiven.length; i++) {
            for (int j = 0; j < hardcodedIsGiven[0].length; j++) {
                if (hardcodedIsGiven[i][j]) {
                    oneDArray[index] = 1;
                } else {
                    oneDArray[index] = 0;
                }
                index++;
            }
        }



        for (int i = 0; i < difficulty_reference_index; i++) {
            index = rand.nextInt(81);
            oneDArray[index] = 0;
        }

        System.out.println(difficulty_reference_index + (" empty cells(for debugging)"));

        // Copy from hardcodedIsGiven into array "isGiven"
        for (int row = 0; row < GameBoardPanel.GRID_SIZE; ++row) {
            for (int col = 0; col < GameBoardPanel.GRID_SIZE; ++col) {
                index = row * 9 + col;
                isGiven[row][col] = ( oneDArray[index] == 1);
            }
        }
    }


}
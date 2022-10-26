import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class Main {

    public static int factorial(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }
        return number * factorial(number - 1);
    }

    public static void main(String[] args) throws IOException {

        String fileName = "C:\\Users\\alexd\\Desktop\\interviews\\Vitec\\src\\input.txt";
        ArrayList<String> rawPuzzle = ReadFile.readFile(fileName);

        // Save the number of possile solutions to the puzzle
        int permutation = 0;

        // Now start to save the puzzle pieces to a matrix of certain width & length
        // To achieve the size of the matrix, need to find out its width & length
        // Find out the 4 pieces at 4 corners, and how many pieces at 4 sides of matrix
        int width, height;
        PuzzlePiece topLeft = null;
        PuzzlePiece btmLeft = null;
        PuzzlePiece topRight = null;
        PuzzlePiece btmRight = null;
        PuzzlePiece[][] puzzle;

        // Save all puzzle pieces in an array first
        PuzzlePiece[] puzzlePieces = new PuzzlePiece[rawPuzzle.size() - 1];

        // Now find out the 4 corner pieces
        // as well as how many pieces on each side
        ArrayList<PuzzlePiece> leftSide = new ArrayList<PuzzlePiece>();
        ArrayList<PuzzlePiece> rightSide = new ArrayList<PuzzlePiece>();
        ArrayList<PuzzlePiece> topSide = new ArrayList<PuzzlePiece>();
        ArrayList<PuzzlePiece> btmSide = new ArrayList<PuzzlePiece>();
        ArrayList<PuzzlePiece> middlePieces = new ArrayList<PuzzlePiece>();

        for (int i = 0; i < puzzlePieces.length; i++) {
            puzzlePieces[i] = new PuzzlePiece(rawPuzzle.get(i + 1));

            // Find out the 4 corner pieces
            if (puzzlePieces[i].checkPosition().equals("topLeft")) {
                topLeft = puzzlePieces[i];
            }

            if (puzzlePieces[i].checkPosition().equals("btmLeft")) {
                btmLeft = puzzlePieces[i];
            }

            if (puzzlePieces[i].checkPosition().equals("topRight")) {
                topRight = puzzlePieces[i];
            }

            if (puzzlePieces[i].checkPosition().equals("btmRight")) {
                btmRight = puzzlePieces[i];
            }

            // Count the numbers of pieces on each side
            if (puzzlePieces[i].checkPosition().equals("top")) {
                topSide.add(puzzlePieces[i]);
            }

            if (puzzlePieces[i].checkPosition().equals("btm")) {
                btmSide.add(puzzlePieces[i]);
            }

            if (puzzlePieces[i].checkPosition().equals("left")) {
                leftSide.add(puzzlePieces[i]);
            }

            if (puzzlePieces[i].checkPosition().equals("right")) {
                rightSide.add(puzzlePieces[i]);
            }

            if (puzzlePieces[i].checkPosition().equals("middle")) {
                middlePieces.add(puzzlePieces[i]);
            }
        }

        // Create the matrix reserved for the puzzle
        // Only create the matrix if it makes a rectangle
        // and there exist 4 corner pieces
        if (topSide.size() == btmSide.size()
                && leftSide.size() == rightSide.size()
                && topLeft != null
                && topRight != null
                && btmLeft != null
                && btmRight != null) {

            width = topSide.size() + 2;
            height = leftSide.size() + 2;
            puzzle = new PuzzlePiece[height][width];
            puzzle[0][0] = topLeft;
            puzzle[0][width - 1] = topRight;
            puzzle[height - 1][0] = btmLeft;
            puzzle[height - 1][width - 1] = btmRight;

        } else {
            System.out.println("There is " + permutation + " unique solution(s) to this puzzle");
            return;
        }

        // Now arrange all the pieces into the matrix, from top to bottom, left to right

        // We will fill up the top and bottom rows first
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < topSide.size(); j++) {
                if (puzzle[0][i] != null
                        && puzzle[0][i].isFitRight(topSide.get(j))) {
                    puzzle[0][i + 1] = topSide.get(j);
                    topSide.remove(j);
                }

            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < btmSide.size(); j++) {
                if (puzzle[height - 1][i] != null
                        && puzzle[height - 1][i].isFitRight(btmSide.get(j))) {
                    puzzle[height - 1][i + 1] = btmSide.get(j);
                    btmSide.remove(j);
                }
            }
        }

        // Doing the same thing for the left and right row
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < leftSide.size(); j++) {
                if (puzzle[i][0] != null
                        && puzzle[i][0].isFitDown(leftSide.get(j))) {
                    puzzle[i + 1][0] = leftSide.get(j);
                    leftSide.remove(j);
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < rightSide.size(); j++) {
                if (puzzle[i][width - 1] != null
                        && puzzle[i][width - 1].isFitDown(rightSide.get(j))) {
                    puzzle[i + 1][width - 1] = rightSide.get(j);
                    rightSide.remove(j);
                }
            }
        }

        // Check if the top and bottom rows are filled correctly
        // We first check if all 4 top, bottom, left, right sides are filled in
        for (int i = 1; i < width - 1; i++) {
            if (puzzle[0][i] == null || puzzle[height - 1][i] == null) {
                System.out.println("There is " + permutation +
                        " unique solution(s) to this puzzle");
                return;
            }
        }
        for (int i = 1; i < height - 1; i++) {
            if (puzzle[i][0] == null || puzzle[i][width - 1] == null) {
                System.out.println("There is " + permutation +
                        " unique solution(s) to this puzzle");
                return;
            }
        }

        // We then check if the second last piece of each top, bottom, left, right side
        // would fit the last piece of each such side.
        if (!puzzle[0][width - 2].isFitRight(puzzle[0][width - 1])
                || !puzzle[height - 1][width - 2].isFitRight(puzzle[height - 1][width - 1])
                || !puzzle[height - 2][0].isFitDown(puzzle[height - 1][0])
                || !puzzle[height - 2][width - 1].isFitDown(puzzle[height - 1][width - 1])) {

            System.out.println("There is " + permutation +
                    " unique solution(s) to this puzzle");
            return;
        }

        // Now we proceed to fill up inside the matrix

        // We then run through each piece
        // from top to bottom, then from left to right
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {

                // We compare each piece to the
                // to its adjacent upper and left pieces
                for (int k = 0; k < middlePieces.size() - 1; k++) {
                    PuzzlePiece middlePiece = middlePieces.get(k);

                    if (middlePiece.isFitUp(puzzle[i - 1][j])
                            && middlePiece.isFitLeft(puzzle[i][j - 1])) {

                        puzzle[i][j] = middlePiece;
                        middlePieces.remove(middlePieces.get(k));
                    }
                }
            }
        }

        // Assign last remaining piece to the last empty cell
        puzzle[height - 2][width - 2] = middlePieces.get(middlePieces.size() - 1);

        // Check if the pieces in the second last row and column
        // will fit to the right side and bottom side of the matrix
        for (int i = 1; i < width - 1; i++) {
            if (!puzzle[height - 2][i].isFitDown(puzzle[height - 1][i])) {
                System.out.println("There is " + permutation +
                        " unique solution(s) to this puzzle");
                return;
            }
        }
        for (int i = 1; i < height - 1; i++) {
            if (!puzzle[i][width - 2].isFitRight(puzzle[i][width - 1])) {
                System.out.println("There is " + permutation +
                        " unique solution(s) to this puzzle");
                return;
            }
        }

        // Now we check duplicate pieces to count total permutation
        int total = 1;
        ArrayList<PuzzlePiece> totalPieces = new ArrayList<PuzzlePiece>(Arrays.asList(puzzlePieces));
        for (int i = 0; i < totalPieces.size(); i++) {
            int count = 1;
            for (int j = i + 1; j < totalPieces.size(); j++) {
                if (totalPieces.get(i).toString().equals(totalPieces.get(j).toString())) {
                    ++count;
                    totalPieces.remove(totalPieces.get(j));
                }
            }
            total *= factorial(count);
        }

        permutation += total;

        // Check the formation of the matrix
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (puzzle[h][w] != null) {
                    System.out.print(puzzle[h][w].toString() + " ");
                } else {
                    System.out.print("Null ");
                }
            }
            System.out.println();
        }

        System.out.println("There is " + permutation +
                " unique solution(s) to this puzzle");

    }
}
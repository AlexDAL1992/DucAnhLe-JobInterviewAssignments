public class PuzzlePiece {

    private char up, right, down, left;

    public PuzzlePiece(String shape) {
        char[] shapeArr = shape.toCharArray();
        this.up = shapeArr[0];
        this.right = shapeArr[1];
        this.down = shapeArr[2];
        this.left = shapeArr[3];
    }

    public String checkPosition() {
        if (this.up == 'S'
                && this.right != 'S'
                && this.down != 'S'
                && this.left == 'S') {
            return "topLeft";
        }

        if (this.up != 'S'
                && this.right != 'S'
                && this.down == 'S'
                && this.left == 'S') {
            return "btmLeft";
        }

        if (this.up == 'S'
                && this.right == 'S'
                && this.down != 'S'
                && this.left != 'S') {
            return "topRight";
        }

        if (this.up != 'S'
                && this.right == 'S'
                && this.down == 'S'
                && this.left != 'S') {
            return "btmRight";
        }

        if (this.up == 'S'
                && this.right != 'S'
                && this.down != 'S'
                && this.left != 'S') {
            return "top";
        }

        if (this.up != 'S'
                && this.right == 'S'
                && this.down != 'S'
                && this.left != 'S') {
            return "right";
        }

        if (this.up != 'S'
                && this.right != 'S'
                && this.down == 'S'
                && this.left != 'S') {
            return "btm";
        }

        if (this.up != 'S'
                && this.right != 'S'
                && this.down != 'S'
                && this.left == 'S') {
            return "left";
        }

        return "middle";
    }

    // getters to get the shapes of all 4 sides of each piece
    public char getUp() {
        return this.up;
    }

    public char getDown() {
        return this.down;
    }

    public char getRight() {
        return this.right;
    }

    public char getLeft() {
        return this.left;
    }


    // consider this puzzle piece if it fits another surrounding piece
    public boolean isFitLeft(PuzzlePiece another) {
        if (another == null)
            return false;
        return (this.left == 'O' && another.getRight() == 'I')
                || (this.left == 'I' && another.getRight() == 'O');
    }

    public boolean isFitRight(PuzzlePiece another) {
        if (another == null)
            return false;
        return (this.right == 'O' && another.getLeft() == 'I')
                || (this.right == 'I' && another.getLeft() == 'O');
    }

    public boolean isFitUp(PuzzlePiece another) {
        if (another == null)
            return false;
        return (this.up == 'O' && another.getDown() == 'I')
                || (this.up == 'I' && another.getDown() == 'O');
    }

    public boolean isFitDown(PuzzlePiece another) {
        if (another == null)
            return false;
        return (this.down == 'O' && another.getUp() == 'I')
                || (this.down == 'I' && another.getUp() == 'O');
    }


    // get info of the piece
    @Override
    public String toString() {
        return "" + this.up + this.right + this.down + this.left;
    }
}

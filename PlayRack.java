
public class PlayRack {
    private char[] width = { 'a', 'b', 'c', 'd', 'e', 'f', 'g' };
    private int[] height = { 1, 2, 3, 4, 5, 6 };
    private String[] rows = { "| | | | | | | | ", "| | | | | | | | ", "| | | | | | | | ", "| | | | | | | | ",
            "| | | | | | | | ", "| | | | | | | | " };

    public PlayRack() {

    }

    public void drawRack() {

        // Draw rack header
        for (int i = 0; i < this.width.length; i++) {
            System.out.print(" " + this.width[i]);
        }
        System.out.print("\n");

        // Draw playing columns
        for (int i = this.height.length; i > 0; i--) {
            System.out.println(rows[i - 1] + i);
        }

        // Draw footer
        System.out.println(" - - - - - - -");

    }

    public void addDisc(String input, char player) {
        // Convert input to numeric position
        int charPosition = getPosition(input);

        // Add disc based on given input
        // rows represents current row from bottom to top. charPosition represents the
        // column that has been selected by the player
        if (!(rows[0].charAt(charPosition) == 'R' || rows[0].charAt(charPosition) == 'G')) {
            rows[0] = swap(charPosition, player, rows[0]);
        } else if (!(rows[1].charAt(charPosition) == 'R' || rows[1].charAt(charPosition) == 'G')) {
            rows[1] = swap(charPosition, player, rows[1]);
        } else if (!(rows[2].charAt(charPosition) == 'R' || rows[2].charAt(charPosition) == 'G')) {
            rows[2] = swap(charPosition, player, rows[2]);
        } else if (!(rows[3].charAt(charPosition) == 'R' || rows[3].charAt(charPosition) == 'G')) {
            rows[3] = swap(charPosition, player, rows[3]);
        } else if (!(rows[4].charAt(charPosition) == 'R' || rows[4].charAt(charPosition) == 'G')) {
            rows[4] = swap(charPosition, player, rows[4]);
        } else if (!(rows[5].charAt(charPosition) == 'R' || rows[5].charAt(charPosition) == 'G')) {
            rows[5] = swap(charPosition, player, rows[5]);
        }
    }

    // Method used to "swap" blank space with a player disc
    public String swap(int position, char color, String row) {
        char[] charArray = row.toCharArray();
        charArray[position] = color;
        return new String(charArray);
    }

    // Method used to check of a column is full. What to do when it is full is
    // handled in the UI
    public boolean checkIfColumnFull(int position) {
        // rows[5] is always the upper row (row 6)
        if (rows[5].charAt(position) == ' ') {
            return false;
        } else {
            return true;
        }
    }

    // Method used to convert the player input to a column position
    public int getPosition(String input) {
        int charPosition = 0;
        // Adding position in playRack based on give input
        while (charPosition == 0) {
            switch (input) {
                case "a":
                    charPosition = 1;
                    break;
                case "b":
                    charPosition = 3;
                    break;
                case "c":
                    charPosition = 5;
                    break;
                case "d":
                    charPosition = 7;
                    break;
                case "e":
                    charPosition = 9;
                    break;
                case "f":
                    charPosition = 11;
                    break;
                case "g":
                    charPosition = 13;
                    break;
            }
        }
        return charPosition;
    }

    // Everytime a disc is thrown. This method checks if there are 4 connected discs
    // in a vertical line
    public boolean checkVerticalFour(char player) {
        // Create an outer for loop. Without this for loop only the first four rows
        // would be checked. 6 is the max. amount of rows
        for (int i = 0; i < 3; i++) {
            // j represents the columns. Needs to be increased by 2 on each iteration to
            // pass over the "|"-pipes. 13 is the last index at which a "G" or "R" can be
            // found
            for (int j = 1; j <= 13; j += 2) {
                if (this.rows[i].charAt(j) == player && this.rows[i + 1].charAt(j) == player
                        && this.rows[i + 2].charAt(j) == player && this.rows[i + 3].charAt(j) == player) {
                    return true;
                }
            }

        }
        return false;
    }

    public boolean checkHorizontalFour(char player) {
        for (int i = 0; i < 6; i++) {
            for (int j = 1; j <= 13; j += 2) {
                // increase j instead of i to check horizontal line instead of vertical
                if (this.rows[i].charAt(j) == player && this.rows[i].charAt(j + 2) == player
                        && this.rows[i].charAt(j + 4) == player && this.rows[i].charAt(j + 6) == player) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonalAscendingFour(char player) {
        // Ascending line
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j += 2) {
                // increase both i(by 1) and j(by 2) to check ascending diagonal line
                if (this.rows[i].charAt(j) == player && this.rows[i + 1].charAt(j + 2) == player
                        && this.rows[i + 2].charAt(j + 4) == player && this.rows[i + 3].charAt(j + 6) == player) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean checkDiagonalDescendingFour(char player) {
        // Descending line
        // Start at row index 3(row 4) because a descending diagonal line can not occur
        // before row 4
        // End at index 5(row 6)
        for (int i = 3; i <= 5; i++) {
            // Start checking from the first instance of a "G" or "R"
            for (int j = 1; j <= 7; j += 2) {

                // Used for testing
                // System.out.println("j: " + j);
                // System.out.println("this.rows[i].charAt(j): " + this.rows[i].charAt(j));
                // System.out.println("this.rows[i - 1].charAt(j + 2): " + this.rows[i -
                // 1].charAt(j + 2));
                // System.out.println("this.rows[i - 2].charAt(j + 4): " + this.rows[i -
                // 2].charAt(j + 4));
                // System.out.println("this.rows[i - 3].charAt(j + 6): " + this.rows[i -
                // 3].charAt(j + 6));

                // While checking go down 1 row and 2 colum spaces
                if (this.rows[i].charAt(j) == player && this.rows[i - 1].charAt(j + 2) == player
                        && this.rows[i - 2].charAt(j + 4) == player && this.rows[i - 3].charAt(j + 6) == player) {
                    return true;
                }
            }

        }
        return false;
    }

}
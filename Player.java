public class Player {
    private char playerColorChar;
    private String playerColorString;

    public Player(char colorChar, String colorString) {
        this.playerColorChar = colorChar;
        this.playerColorString = colorString;
    }

    public char getPlayerColorChar() {
        return this.playerColorChar;
    }

    public String getPlayerColorString() {
        return this.playerColorString;
    }
}
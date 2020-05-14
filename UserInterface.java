import java.util.Scanner;
import java.util.Random;

public class UserInterface {
    PlayRack playRack = new PlayRack();
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    Player playerRed = new Player('R', "ROOD");
    Player playerYellow = new Player('G', "GEEL");

    public UserInterface() {

    }

    // Define playerColor outside of start() method because it needs to be reachable
    // for changePlayer() method
    char playerColorChar = playerRed.getPlayerColorChar();
    String playerColorString = playerRed.getPlayerColorString();

    public void start() {
        // Draw initial (empty) rack
        playRack.drawRack();

        // Select a random player
        if (selectRandomPlayer() == 0) {
            playerColorChar = playerRed.getPlayerColorChar();
            playerColorString = playerRed.getPlayerColorString();
        } else {
            playerColorChar = playerYellow.getPlayerColorChar();
            playerColorString = playerYellow.getPlayerColorString();
        }

        // Start game
        while (true) {

            // Player turn
            System.out.println(
                    "Kan speler " + playerColorString + " aangeven in welke kolom hij/zij zijn steen wilt gooien.");
            String input = scanner.nextLine();

            // Check if input given is acceptable
            if (!(input.equals("a") || input.equals("b") || input.equals("c") || input.equals("d") || input.equals("e")
                    || input.equals("f") || input.equals("g"))) {
                // If not, restart turn
                System.out.println("Voer een geldige waarde in! (a, b, c, d, e, f of g)");
            } else {
                // Check if selected column is full
                if (playRack.checkIfColumnFull(playRack.getPosition(input))) {
                    // if so, restart turn
                    System.out.println("De gekozen kolom is vol, kies een andere kolom.");
                    
                } else {
                    // if not, continue to add disc
                    playRack.addDisc(input, playerColorChar);
                    // Check if player scored a vertical line of 4
                    if (playRack.checkVerticalFour(playerColorChar)) {
                        System.out.println("Speler " + playerColorString + " Heeft gewonnen met een verticale lijn in kolom " + input + ".");
                        playRack.drawRack();
                        break;
                    }
                    if (playRack.checkHorizontalFour(playerColorChar)) {
                        System.out.println("Speler " + playerColorString + " Heeft gewonnen met een horizontale lijn.");
                        playRack.drawRack();
                        break;
                    }
                    if (playRack.checkDiagonalAscendingFour(playerColorChar)) {
                        System.out.println("Speler " + playerColorString + " Heeft gewonnen met een diagonale lijn.");
                        playRack.drawRack();
                        break;
                    }
                    if (playRack.checkDiagonalDescendingFour(playerColorChar)) {
                        System.out.println("Speler " + playerColorString + " Heeft gewonnen met een diagonale lijn.");
                        playRack.drawRack();
                        break;
                    }

                    // Draw updated playRack
                    playRack.drawRack();

                    // Change active player
                    changePlayer();
                }

            }
        }
    }

    public int selectRandomPlayer() {
        return (int) Math.round(Math.random());
    }

    public void changePlayer() {
        if (playerColorChar == 'R') {
            playerColorChar = 'G';
            playerColorString = "GEEL";
        } else {
            playerColorChar = 'R';
            playerColorString = "ROOD";
        }
    }

}
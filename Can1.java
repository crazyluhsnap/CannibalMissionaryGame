import java.util.Scanner;
public class Can1 {
    private static Scanner gameScanner = new Scanner(System.in);
    static int totalCannibals=3;
    static int totalMissionaries=3;
    static boolean directionLeft=false; //false is right, true is left
    static int canLeft=0;
    static int misLeft=0; //Initially zero people on left side
    static int misRight = totalMissionaries;
    static int canRight = totalCannibals; //Everyone is at right side initially
    static void displayGame() {
        System.out.println("\n----------------------------------------------------------");
        System.out.println("Left Bank: \nMissionaries: "+misLeft+"\nCannibals: "+canLeft);
        if(directionLeft) {
            System.out.println("|---B---|       |");
        }
        else {
            System.out.println("|       |---B---|");
        }
        System.out.println("Right Bank: \nMissionaries: "+misRight+"\nCannibals: "+canRight);
        System.out.println("-------------------------------------------------------------");
    }
    static boolean Win() {
        if((misLeft==totalMissionaries)&&(canLeft==totalCannibals)&&(directionLeft==true)){
            return true;
        }
        else{
            return false;
        }
    }
    static boolean Lose() {
        boolean leftUnsafe = ((misLeft>0)&&(canLeft>misLeft));
        boolean rightUnsafe = ((misRight>0)&&(canRight)>misRight);
        if(leftUnsafe||rightUnsafe) {
            return true;
        }
        else{
            return false;
        }
    }
    static int input(String msg) {
        System.out.println(msg);
        int num = gameScanner.nextInt();
        gameScanner.nextLine();
        return num;
    }
    static boolean makeMove(int misToMove, int canToMove){
        if(directionLeft){ //boat is on left bank
            if((misLeft<misToMove)||(canLeft<canToMove)){
                System.out.println("Not Enough people on left");
                return false;
            }
            //Subtract from left side and add to right side
            misLeft-=misToMove;
            canLeft-=canToMove;
            misRight+=misToMove;
            canRight+=canToMove;

            //Move boat to right
            directionLeft=false;
        }
        else{  //Boat is on right bank
            if((misRight<misToMove)||(canRight<canToMove)){
                System.out.println("Not enough people on right");
                return false;
            }
            //Subtract from right and add to left
            misRight-=misToMove;
            canRight-=canToMove;
            misLeft+=misToMove;
            canLeft+=canToMove;

            //Move boat to left
            directionLeft=true;
        }
        return true; // Move was valid
    }
    public static void main(String[] args) {
        System.out.println("--- THIS IS A CANNIBAL MISSIONARY GAME ---");
        System.out.println("The goal is to move all the 3 cannibals and 3 missionaries to the other side of the river");
        System.out.println("RULES: ");
        System.out.println("1. The boat cannot carry more than 2 people.");
        System.out.println("2. Boat cannot travel empty.");
        System.out.println("3. The number of cannibals should not exceed the number of missionaries.");
        System.out.println("-----------------------------------------------------------------------");
        int canMove;
        int misMove;
        displayGame();
        while(!Win()&&!Lose()){
            while (true) {
            canMove=input("Enter the number of cannibals to move (0-2): ");
            misMove=input("Enter the number of missionaries to move (0-2): ");
            if(canMove>2 || canMove<0 || misMove>2 || misMove<0) {
                System.out.println("The input should be between 0 and 2");
            }
            else if(canMove + misMove == 0) {
                System.out.println("The boat cannot travel empty");
            }
            else if(canMove + misMove > 2) {
                System.out.println("The boat cannot carry more than 2 people");
            }
            else {
                break;
            }
        }
        // check if the move was valid
        boolean SuccessMove = makeMove(misMove,canMove);
        if(!SuccessMove){
            continue;
        }
        displayGame();
        }
        displayGame();
        if(Win()){
            System.out.println("YOU WON, CONGRATULATIONS!!");
        }
        if(Lose()){
            System.out.println("The Cannibals ate Missionaries, YOU LOSE!!");
        }
        gameScanner.close();
    }
}
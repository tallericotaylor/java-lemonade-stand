/**
 * PROJECT 3 GUI GAME
 * 
 * This is a lemonade stand game.
 * Throughout this game the player goes about pressing buttons to create the
 * correct drink for a customer.
 * The player runs out of supplies as the game goes on and is required to spend
 * the money they make to buy there supplies.
 * The game has 5 day which consist of 5 customers each day.
 * If the player makes a drink correct they get $2.00; make it wrong and $0.00
 * and your supplies still go down
 * The goal of this game is to make it to day 5.
 * 
 * Please take into consideration there are some features that are included
 * within this code that have not been fully coded yet.
 * These features inlcude the Happiness and the Special.
 * However, after submisson of this project I plan to continue and fulfill the
 * purpose of these features as well as better organization.
 * 
 * HAVE FUN
 * CS 1181 - PROJECT 3
 * 
 * @author Taylor Tallerico
 */

public class Driver {
    public static void main(String[] args) throws Exception {
        // these open the window
        MainWindow mw = new MainWindow("Lemonade Stand");
        GreetingWindow gw = new GreetingWindow("Greeting");
    }
}

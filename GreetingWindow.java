import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/**
 * This class creates the instructions page for the user
 */
public class GreetingWindow extends JFrame {

    private JPanel main; // This creates the main panel
    private JLabel introLabel; // This creates the Welcome message
    private JTextArea rulesArea; // This create the text are used to explain the rules

    /**
     * This method creates the window and gives it functions such as:
     * location, size, closing operation, visiblity, and the main panel
     * 
     * @param title // the title is taken in from Driver class
     */
    public GreetingWindow(String title) {
        super(title);
        this.setLocation(130, 0);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        greetingPage();
        this.add(main);
        this.setVisible(true);
    }

    /**
     * This method creates the main panel and the infomation on it
     * The main panel contains the welcome message and instructions of the game to
     * the user
     */
    private void greetingPage() {
        // Main Panel
        main = new JPanel(new BorderLayout(20, 20));
        main.setBackground(Color.WHITE);
        main.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Title
        introLabel = new JLabel("Hi, Welcome to the Lemonade Stand", SwingConstants.CENTER);
        introLabel.setFont(new Font("Arial", Font.BOLD, 28));
        main.add(introLabel, BorderLayout.NORTH);

        // Rules area
        rulesArea = new JTextArea(5, 20);
        rulesArea.setEditable(false);
        rulesArea.setLineWrap(true);
        rulesArea.setWrapStyleWord(true);
        rulesArea.setFont(new Font("Arial", Font.PLAIN, 15));
        rulesArea.setText("""
                Goal:
                        To start, there are some things to go over:

                        First:
                            You'll find the order ticket on the left most side labeled "ORDER"
                            Use the buttons at the bottom to add items to make the correct drink
                                (These do not need to be pressed in order)

                        Next:
                            After you have add the appropriate items click "Combine",
                                then "Done".
                            If you made the drink right you'll recieve $2.00
                            Making money is important; you need to make money to stay open.

                        Shop:
                            The shop is where you buy the supplies you need.
                            The shop is only only for "Customer 1" of each day.
                            To start the game you are given lemons, sugar, and cups,
                                but to continue the game you must buy these at the Shop.

                        Inventory:
                            This is where you can see how many items you have left.
                            If you run out of an item in the middle of the day its
                                GAME OVER

                        Strawberry:
                            The strawberry is not introduced until Day 3 so don't forget
                                to buy some at the shop.

                        How to Win:
                            Make the order correct get money,
                            Use money to buy the items you need in the shop.
                            Don't run out of items or money in the middle of a day.
                            Do all this and you have a chance of winning.

                        Good luck!!
                        """);

        main.add(rulesArea);

        JScrollPane scrollPane = new JScrollPane(rulesArea);
        main.add(scrollPane, BorderLayout.CENTER);
    }

}

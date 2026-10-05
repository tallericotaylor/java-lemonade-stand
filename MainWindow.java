import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

    private boolean playerHasIce = false; // Checks if the player has added ice
    private boolean playerHasSugar = false; // Checks if the player has added sugar
    private boolean playerHasLemon = false; // Checks if the player had added lemons
    private boolean playerHasSpecial = false;

    // top bar
    private JPanel topPanel; // Create the top bar of the screen
    private JPanel happinessPanel; // Section of panel to recored happiness
    private JPanel dayPanel; // Section of panel to record the day #
    private JPanel customerPanel; // Section of panel to record the customer #
    private JPanel moneyPanel; // Section of panel to record the money #
    private JLabel happinessLabel;
    private JLabel customerLabel;
    private JLabel customerCount; // Records the customer #
    private JLabel moneyLabel;
    private JLabel moneyCount; // Records the money #
    private JLabel dayLabel;
    private JLabel dayCount; // Records the day #
    private JLabel happinessCount;

    // left bar
    private JPanel leftPanel; // Create the left panel of the screen
    private JPanel ticketPanel; // Section of panel to record order and ticket
    private JPanel buttonPanel;
    private JButton combineButton; // Button to combine the items ( making them disappear )
    private JButton doneButton; // Button to check drink and animate the cup going to the customer
    private JLabel orderLabel;
    private JLabel ticketLabel; // Records the customers order on ticket

    // right bar
    private JPanel rightPanel; // Creates the right panel of the screen

    private JPanel shopSection; // Section of panel to create the shop panel
    private JPanel shopPanel; // Section of panel to actually create the shop
    private JButton lemonShopBtn; // Button for Lemons in shop
    private JButton sugarShopBtn; // Button for Sugar in shop
    private JButton cupsShopBtn; // Button for Cups in shop
    private JButton specialShopBtn; // Button for Speical in shop
    private JLabel lemonPrice;
    private JLabel sugarPrice;
    private JLabel cupsPrice;
    private JLabel specialPrice;
    private JLabel shopLabel;
    private JLabel lemonLabel; // Label the cost of lemons
    private JLabel iceLabel;
    private JLabel sugarLabel; // Label the cost of sugar
    private JLabel cupsLabel; // Label the cost of cups
    private JLabel specialLabel; // Label the cost of special

    private JPanel inventorySection; // Section of panel to create the inventory panel
    private JPanel inventoryPanel; // Section of panel to actually create the inventory
    private JLabel inventoryLabel;
    private JLabel lemonAmount; // Label the amount of lemons in inventory
    private JLabel sugarAmount; // Label the amount of sugar in inventory
    private JLabel cupsAmount; // Label the amount of cups in inventory
    private JLabel specialAmount; // Label the amount of special in inventory

    // bottom bar
    private JPanel bottomPanel; // Creates the bottom bar of buttons to add to orders
    private JPanel lemonPanel; // Creates the yellow box for lemons
    private JPanel icePanel; // Creates the blue box for ice
    private JPanel sugarPanel; // Creates the white box for sugar
    private JPanel specialPanel; // Creates the pink box for special
    private JButton lemonButton; // Creates the add button for lemons
    private JButton iceButton; // Creates the add button for ice
    private JButton sugarButton; // Creates the add button for sugar
    private JButton specialButton; // Creates the add button for the speical
    private JLabel cup; // Creates a cup and emoji
    private JLabel lemonImage;
    private JLabel iceImage;
    private JLabel sugarImage;
    private JLabel specialImage;

    // center
    private JPanel southCenterPanel;
    private JPanel centerPanel; // Creates the center panel for drinks to be made //
    private JPanel drinkPanel; // Create the panel where the cup appears
    private int cupX; // Creates a variable for the x cooridnate for cups

    private JLabel createLabel(String text, int size) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, size));
        return label;
    }

    private JButton createButton(String text, int size) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, size));
        button.addActionListener(this);
        return button;
    }

    // Allows to game logic to be added to the MainWindow
    GameLogic game = new GameLogic();

    /**
     * Constructor for the MainWindow
     * Sets size to 1200 x 800
     * Also adds al the panels to the frame
     * 
     * @param title // Title given in Driver Class
     */
    public MainWindow(String title) {
        super(title);

        this.setLocation(130, 0);
        this.setSize(1200, 800);

        topBar();
        this.add(topPanel, BorderLayout.NORTH);

        leftBar();
        this.add(leftPanel, BorderLayout.WEST);

        rightBar();
        this.add(rightPanel, BorderLayout.EAST);

        // Creating room for bottom bar
        center();
        this.add(centerPanel, BorderLayout.CENTER);
        bottomBar();
        ((JPanel) centerPanel.getComponent(0)).add(bottomPanel, BorderLayout.SOUTH); // this line was created by Claude
                                                                                     // AI
        this.add(centerPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Create the top panel of the page
     * Includes:
     * Happiness
     * Day
     * Customer
     * Money
     */
    private void topBar() {
        // Main Panel
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1200, 100));
        topPanel.setLayout(new GridLayout(1, 4));

        // Happiness Panel
        happinessPanel = new JPanel(new BorderLayout());
        happinessPanel.setBackground(Color.LIGHT_GRAY);
        happinessLabel = createLabel("Customer Happiness", 18);
        happinessCount = createLabel(game.getHappiness(), 40);

        // Day Panel & Labels
        dayPanel = new JPanel(new BorderLayout());
        dayPanel.setBackground(Color.LIGHT_GRAY);
        dayLabel = createLabel("Day", 18);
        dayCount = createLabel("" + game.getDay(), 24);

        // Customer Panel & Labels
        customerPanel = new JPanel(new BorderLayout());
        customerPanel.setBackground(Color.LIGHT_GRAY);
        customerLabel = createLabel("Customer", 18);
        customerCount = createLabel("" + game.getCustomer(), 24);

        // Money Panel & Labels
        moneyPanel = new JPanel(new BorderLayout());
        moneyPanel.setBackground(Color.LIGHT_GRAY);
        moneyLabel = createLabel("Money", 18);
        moneyCount = createLabel(String.format("$%.2f", game.getMoney()), 24);

        // Adding all Labels
        happinessPanel.add(happinessLabel, BorderLayout.NORTH);
        happinessPanel.add(happinessCount, BorderLayout.CENTER);
        dayPanel.add(dayLabel, BorderLayout.NORTH);
        dayPanel.add(dayCount, BorderLayout.CENTER);
        customerPanel.add(customerLabel, BorderLayout.NORTH);
        customerPanel.add(customerCount, BorderLayout.CENTER);
        moneyPanel.add(moneyLabel, BorderLayout.NORTH);
        moneyPanel.add(moneyCount, BorderLayout.CENTER);

        // Adding all to Panels
        topPanel.add(happinessPanel);
        topPanel.add(dayPanel);
        topPanel.add(customerPanel);
        topPanel.add(moneyPanel);
    }

    /**
     * Creates the right panel of the page
     * This include two main panels:
     * Inventory and Shop
     * Each have multplie panels, buttons, and label to allow the player to see and
     * interact
     */
    private void rightBar() {
        // Main Panel
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(300, 700));
        rightPanel.setLayout(new GridLayout(2, 1)); // 2 rows, 1 column

        // ---Shopping---

        // Main Panel
        shopSection = new JPanel();
        shopSection.setLayout(new BorderLayout());

        // Label
        shopLabel = createLabel("SHOP OPEN", 18);
        shopSection.add(shopLabel, BorderLayout.NORTH);

        // Secondary Panel
        shopPanel = new JPanel();
        shopPanel.setLayout(new GridLayout(4, 2, 10, 10));

        // Button Names
        lemonShopBtn = createButton("6 Lemons", 16);
        sugarShopBtn = createButton("5 Sugar", 16);
        cupsShopBtn = createButton("10 Cups", 16);
        specialShopBtn = createButton("4 Strawberry", 16);

        // Creates labels for the price of items
        lemonPrice = createLabel("$ 3.00", 16);
        sugarPrice = createLabel("$ 2.00", 16);
        cupsPrice = createLabel("$ 3.50", 16);
        specialPrice = createLabel("$ 4.00", 16);

        // Add it all to the shopPanel
        shopPanel.add(lemonShopBtn);
        shopPanel.add(lemonPrice);
        shopPanel.add(sugarShopBtn);
        shopPanel.add(sugarPrice);
        shopPanel.add(cupsShopBtn);
        shopPanel.add(cupsPrice);
        shopPanel.add(specialShopBtn);
        shopPanel.add(specialPrice);

        shopPanel.setBackground(Color.LIGHT_GRAY);
        shopSection.add(shopPanel, BorderLayout.CENTER);

        // --- Inventory ---
        // Main Panel
        inventorySection = new JPanel();
        inventorySection.setLayout(new BorderLayout());

        // Label
        inventoryLabel = createLabel("INVENTORY", 18);
        inventorySection.add(inventoryLabel, BorderLayout.NORTH);

        // Seconday Panel
        inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(4, 2, 10, 10));

        lemonLabel = createLabel("Lemons", 16);
        sugarLabel = createLabel("Sugar", 16);
        cupsLabel = createLabel("Cups", 16);
        specialLabel = createLabel("Strawberry", 16);

        lemonAmount = createLabel(String.valueOf(game.getLemons()), 16);
        sugarAmount = createLabel(String.valueOf(game.getSugar()), 16);
        cupsAmount = createLabel(String.valueOf(game.getCups()), 16);
        specialAmount = createLabel(String.valueOf(game.getSpecial()), 16);

        // Adding it all to inventory
        inventoryPanel.add(lemonLabel);
        inventoryPanel.add(lemonAmount);
        inventoryPanel.add(sugarLabel);
        inventoryPanel.add(sugarAmount);
        inventoryPanel.add(cupsLabel);
        inventoryPanel.add(cupsAmount);
        inventoryPanel.add(specialLabel);
        inventoryPanel.add(specialAmount);

        inventoryPanel.setBackground(Color.LIGHT_GRAY);
        inventorySection.add(inventoryPanel, BorderLayout.CENTER);

        // Adding it all to rightPanel
        rightPanel.add(shopSection);
        rightPanel.add(inventorySection);
    }

    /**
     * Creates the left bar
     * This is primarily the ticket order
     * Include different panels and labels
     */
    private void leftBar() {
        // Main Panel
        leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(300, 700));
        leftPanel.setLayout(new BorderLayout());

        // Label
        orderLabel = createLabel("ORDER", 18);
        leftPanel.add(orderLabel, BorderLayout.NORTH);

        // Ticket Panel
        ticketPanel = new JPanel();
        ticketPanel.setBackground(Color.LIGHT_GRAY);
        ticketLabel = createLabel(game.drinkCombos(), 18);
        ticketPanel.setLayout(new BorderLayout());
        ticketPanel.add(ticketLabel, BorderLayout.CENTER);

        // Adding panel for buttons
        buttonPanel = new JPanel(new GridLayout(2, 1, 5, 0));

        combineButton = new JButton("COMBINE");
        combineButton.addActionListener(this);
        combineButton.setPreferredSize(new Dimension(300, 50));

        doneButton = new JButton("DONE");
        doneButton.addActionListener(this);
        doneButton.setPreferredSize(new Dimension(300, 50));

        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // Adding it all together
        buttonPanel.add(combineButton);
        buttonPanel.add(doneButton);
        ticketPanel.add(buttonPanel, BorderLayout.SOUTH);
        leftPanel.add(ticketPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the bottom bar
     * This is were the drink buttons are created and added
     */
    private void bottomBar() {
        // Main Panel
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(222, 184, 135));
        bottomPanel.setPreferredSize(new Dimension(600, 150));
        bottomPanel.setLayout(new GridLayout(1, 4, 10, 10));

        // Lemon Panel
        lemonPanel = new JPanel();
        lemonPanel.setLayout(new BorderLayout());
        lemonPanel.setBackground(Color.YELLOW);

        lemonLabel = createLabel("Lemons", 16);
        lemonImage = createLabel("🍋", 40);
        lemonButton = new JButton("ADD");
        lemonButton.addActionListener(this);

        // Ice Panel
        icePanel = new JPanel();
        icePanel.setLayout(new BorderLayout());
        icePanel.setBackground(new Color(144, 213, 255));

        iceLabel = createLabel("Ice", 16);
        iceImage = createLabel("🧊", 40);
        iceButton = new JButton("ADD");
        iceButton.addActionListener(this);

        // Sugar Panel
        sugarPanel = new JPanel();
        sugarPanel.setLayout(new BorderLayout());
        sugarPanel.setBackground(new Color(252, 251, 244));

        sugarLabel = createLabel("Sugar", 16);
        sugarImage = createLabel("🍬", 40);
        sugarButton = new JButton("ADD");
        sugarButton.addActionListener(this);

        // Speical Panel
        specialPanel = new JPanel();
        specialPanel.setLayout(new BorderLayout());
        specialPanel.setBackground(new Color(255, 182, 193));

        specialLabel = createLabel("Strawberry", 16);
        specialImage = createLabel("🍓", 40);
        specialButton = new JButton("ADD");
        specialButton.addActionListener(this);

        // Creating the lemon "Button"
        lemonPanel.add(lemonLabel, BorderLayout.NORTH);
        lemonPanel.add(lemonImage, BorderLayout.CENTER);
        lemonPanel.add(lemonButton, BorderLayout.SOUTH);

        // Creating the ice "Button"
        icePanel.add(iceLabel, BorderLayout.NORTH);
        icePanel.add(iceImage, BorderLayout.CENTER);
        icePanel.add(iceButton, BorderLayout.SOUTH);

        // Creating the sugar "Button"
        sugarPanel.add(sugarLabel, BorderLayout.NORTH);
        sugarPanel.add(sugarImage, BorderLayout.CENTER);
        sugarPanel.add(sugarButton, BorderLayout.SOUTH);

        // Creating the special "Button"
        specialPanel.add(specialLabel, BorderLayout.NORTH);
        specialPanel.add(specialImage, BorderLayout.CENTER);
        specialPanel.add(specialButton, BorderLayout.SOUTH);

        // Adding it all to bottom panel
        bottomPanel.add(lemonPanel);
        bottomPanel.add(icePanel);
        bottomPanel.add(sugarPanel);
        bottomPanel.add(specialPanel);
    }

    /**
     * Creates the center panel
     * This create the center area where the drinks are made
     * Bottom Panel with be added here
     */
    private void center() {
        centerPanel = new JPanel(new BorderLayout());

        drinkPanel = new JPanel();
        drinkPanel.setPreferredSize(new Dimension(600, 100));
        drinkPanel.setBackground(new Color(222, 184, 135));

        // This allows for that little panel of brown above to buttons
        southCenterPanel = new JPanel(new BorderLayout());

        southCenterPanel.add(drinkPanel, BorderLayout.NORTH);
        centerPanel.add(southCenterPanel, BorderLayout.SOUTH);
    }

    /**
     * This method contains all the buttons and there purposes
     * This method uses the actionListener to create functions for these buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Adds lemon to screen after clicked
        if (e.getSource() == lemonButton) {
            JLabel label = new JLabel(" 🍋 ");
            playerHasLemon = true;
            label.setFont(new Font("Arial", Font.PLAIN, 30));
            drinkPanel.add(label);
            drinkPanel.revalidate();
            drinkPanel.repaint();
            game.setLemons(game.getLemons() - 1);
            refreshInventory();
        }
        // Adds ice to the screen after clicked
        else if (e.getSource() == iceButton) {
            JLabel label = new JLabel(" 🧊 ");
            playerHasIce = true;
            label.setFont(new Font("Arial", Font.PLAIN, 30));
            drinkPanel.add(label);
            drinkPanel.revalidate();
            drinkPanel.repaint();
        }
        // Adds sugar to the screen after clicked
        else if (e.getSource() == sugarButton) {
            JLabel label = new JLabel(" 🍬 ");
            playerHasSugar = true;
            label.setFont(new Font("Arial", Font.PLAIN, 30));
            drinkPanel.add(label);
            drinkPanel.revalidate();
            drinkPanel.repaint();
            game.setSugar(game.getSugar() - 1);
            refreshInventory();
        }
        // Adds special to the screen after clicked
        // This will be added later
        else if (e.getSource() == specialButton) {
            JLabel label = new JLabel(" 🍓 ");
            playerHasSpecial = true;
            label.setFont(new Font("Arial", Font.PLAIN, 30));
            drinkPanel.add(label);
            drinkPanel.revalidate();
            drinkPanel.repaint();
            game.setSpecial(game.getSpecial() - 1);
            refreshInventory();
        }
        // Removes all items to "combine" them into the cup
        else if (e.getSource() == combineButton) {
            drinkPanel.removeAll();
            cup = new JLabel(" 🥤 ");
            cup.setFont(new Font("Arial", Font.PLAIN, 50));
            drinkPanel.add(cup);
            drinkPanel.revalidate();
            drinkPanel.repaint();
            game.setCups(game.getCups() - 1);
            refreshInventory();
        }
        // Animate cup to slide to customer
        // Also runs checks for ice, sugar, and lemons
        else if (e.getSource() == doneButton) {
            checkGameOver();

            if (game.isGameOver()) {
                JLabel label = new JLabel("GAME OVER", SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 50));
                centerPanel.add(label);
                centerPanel.revalidate();
                centerPanel.repaint();
                disableAllButtons();
            }

            game.checkDrink(playerHasIce, playerHasSugar, playerHasLemon, playerHasSpecial);
            refreshTopPanel();
            playerHasIce = false;
            playerHasSugar = false;
            playerHasLemon = false;
            playerHasSpecial = false;
            ticketLabel.setText(game.getOrder());
            animation();

            if (game.getCustomer() == 1) {
                shopLabel.setText("SHOP OPEN");
                shopLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                repaint();
                enableShopButtons();
            } else if (game.getCustomer() >= 2) {
                shopLabel.setText("SHOP CLOSED");
                shopLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                repaint();
                disableShopButtons();
            }
        }
        // --- These are Shop Buttons ---
        else if (e.getSource() == lemonShopBtn) {
            if (game.getMoney() >= 3.00) {
                game.setLemons(game.getLemons() + 6);
                game.setMoney(game.getMoney() - 3.00);
                refreshInventory();
                refreshTopPanel();
                checkGameOver();
            }
        } else if (e.getSource() == sugarShopBtn) {
            if (game.getMoney() >= 2.00) {
                game.setSugar(game.getSugar() + 5);
                game.setMoney(game.getMoney() - 2.00);
                refreshInventory();
                refreshTopPanel();
                checkGameOver();
            }
        } else if (e.getSource() == cupsShopBtn) {
            if (game.getMoney() >= 3.50) {
                game.setCups(game.getCups() + 10);
                game.setMoney(game.getMoney() - 3.50);
                refreshInventory();
                refreshTopPanel();
                checkGameOver();
            }
        } else if (e.getSource() == specialShopBtn) {
            if (game.getMoney() >= 4.00) {
                game.setSpecial(game.getSpecial() + 4);
                game.setMoney(game.getMoney() - 4.00);
                refreshInventory();
                refreshTopPanel();
                checkGameOver();
            }
        }
    }

    /**
     * This method reconstructes the inventory after each button is pressed ( not
     * including the ice button )
     * The idea for the method came from Claude AI -- I was stumped on why my panel
     * weren;t updating
     * This method inspired the idea to do the same for the top panel
     */
    private void refreshInventory() {
        inventoryPanel.removeAll();

        lemonLabel = createLabel("Lemons", 16);
        sugarLabel = createLabel("Sugar", 16);
        cupsLabel = createLabel("Cups", 16);
        specialLabel = createLabel("Strawberry", 16);

        lemonAmount = createLabel(String.valueOf(game.getLemons()), 16);
        sugarAmount = createLabel(String.valueOf(game.getSugar()), 16);
        cupsAmount = createLabel(String.valueOf(game.getCups()), 16);
        specialAmount = createLabel(String.valueOf(game.getSpecial()), 16);

        // Adding it all to inventory
        inventoryPanel.add(lemonLabel);
        inventoryPanel.add(lemonAmount);
        inventoryPanel.add(sugarLabel);
        inventoryPanel.add(sugarAmount);
        inventoryPanel.add(cupsLabel);
        inventoryPanel.add(cupsAmount);
        inventoryPanel.add(specialLabel);
        inventoryPanel.add(specialAmount);

        lemonButton.setEnabled(game.getLemons() > 0);
        sugarButton.setEnabled(game.getSugar() > 0);
        specialButton.setEnabled(game.getSpecial() > 0);
        combineButton.setEnabled(game.getCups() > 0);

        inventoryPanel.revalidate();
        inventoryPanel.repaint();
    }

    /**
     * This method also reconstructes the top panel after the done button
     */
    private void refreshTopPanel() {
        moneyPanel.removeAll();
        moneyLabel = createLabel("Money", 18);
        moneyCount = createLabel(String.format("$%.2f", game.getMoney()), 24);
        moneyPanel.add(moneyLabel, BorderLayout.NORTH);
        moneyPanel.add(moneyCount, BorderLayout.CENTER);
        moneyPanel.setBackground(Color.LIGHT_GRAY);
        moneyPanel.revalidate();
        moneyPanel.repaint();

        customerPanel.removeAll();
        customerLabel = createLabel("Customer", 18);
        customerCount = createLabel("" + game.getCustomer(), 24);
        customerPanel.add(customerLabel, BorderLayout.NORTH);
        customerPanel.add(customerCount, BorderLayout.CENTER);
        customerPanel.setBackground(Color.LIGHT_GRAY);
        customerPanel.revalidate();
        customerPanel.repaint();

        dayPanel.removeAll();
        dayLabel = createLabel("Day", 18);
        dayCount = createLabel("" + game.getDay(), 24);
        dayPanel.add(dayLabel, BorderLayout.NORTH);
        dayPanel.add(dayCount, BorderLayout.CENTER);
        dayPanel.setBackground(Color.LIGHT_GRAY);
        dayPanel.revalidate();
        dayPanel.repaint();

        happinessPanel.removeAll();
        happinessLabel = createLabel("Customer Happiness", 18);
        happinessCount = createLabel(game.getHappiness(), 40);
        happinessPanel.add(happinessLabel, BorderLayout.NORTH);
        happinessPanel.add(happinessCount, BorderLayout.CENTER);
        happinessPanel.setBackground(Color.LIGHT_GRAY);
        happinessPanel.revalidate();
        happinessPanel.repaint();

    }

    /**
     * This method animates the cup to slide to the right to the customer
     * If the cup is not there nothing happen
     */
    public void animation() {
        if (cup == null)
            return;

        drinkPanel.setLayout(null);
        cup.setBounds(0, 10, 70, 70);
        cupX = cup.getX();

        Timer t = new Timer(16, e -> {
            if (cupX < drinkPanel.getWidth()) {
                cupX += 10;
                cup.setLocation(cupX, cup.getY());
                drinkPanel.repaint();
            } else {
                ((Timer) e.getSource()).stop(); // This line was created by Claude AI
                drinkPanel.removeAll();
                drinkPanel.setLayout(new FlowLayout());
                drinkPanel.revalidate();
                drinkPanel.repaint();
            }
        });

        t.start();
    }

    /**
     * This method will disable all the buttons
     * This means game over
     */
    private void disableAllButtons() {
        lemonButton.setEnabled(false);
        iceButton.setEnabled(false);
        sugarButton.setEnabled(false);
        specialButton.setEnabled(false);
        combineButton.setEnabled(false);
        doneButton.setEnabled(false);
        lemonShopBtn.setEnabled(false);
        sugarShopBtn.setEnabled(false);
        cupsShopBtn.setEnabled(false);
        specialShopBtn.setEnabled(false);
    }

    /**
     * This method will disable the shop button so the player can't shop
     */
    private void disableShopButtons() {
        lemonShopBtn.setEnabled(false);
        sugarShopBtn.setEnabled(false);
        cupsShopBtn.setEnabled(false);
        specialShopBtn.setEnabled(false);
    }

    /**
     * This method will enable the shop buttons so the player can shop
     */
    private void enableShopButtons() {
        lemonShopBtn.setEnabled(true);
        sugarShopBtn.setEnabled(true);
        cupsShopBtn.setEnabled(true);
        specialShopBtn.setEnabled(true);
    }

    /**
     * This method is a simpler checker
     * This checks if the game is over from witin the Game Logic class
     */
    private void checkGameOver() {
        if (game.isGameOver()) {
            disableAllButtons();
        }
    }

}

import java.util.Random;

/**
 * This class contain most of the game logic
 * This is where most of the data is stored such as
 * Day, Customer, Supplies, Money, and Happiness
 */
public class GameLogic {

    private double moneyAmount = 0.00; // Starts money off at 0.00
    private String happinessLevel = "🙂"; // Starts the customers off as Happy
    private int dayCount = 1; // Starts Days at 1
    private int customerCount = 1; // Starts Customers at 1
    private String currentOrder = ""; // Hold data of current order
    private int lemons = 7; // Start with 7 Lemons
    private int sugar = 7; // Start with 7 sugars
    private int special = 0; // Start with 0 special
    private int cups = 7; // Start with 7 cups
    private boolean dayJustChanged = false; // Checks if player is not on customer 1
    private double percentMadeRight = 100;
    private final String happy = "🙂";
    private final String okay = "😐";
    private final String upset = "🙁";
    private final String mad = "😡";

    /**
     * Setter for lemons
     * 
     * @param lemons // Variable
     */
    public void setLemons(int lemons) {
        if (lemons < 0)
            return;
        this.lemons = lemons;
    }

    /**
     * Getter for lemons
     * 
     * @return // returns # lemons
     */
    public int getLemons() {
        return lemons;
    }

    /**
     * Setter for sugar
     * 
     * @param sugar // Variable
     */
    public void setSugar(int sugar) {
        if (sugar < 0)
            return;
        this.sugar = sugar;
    }

    /**
     * Getter for sugar
     * 
     * @return // returns # sugar
     */
    public int getSugar() {
        return sugar;
    }

    /**
     * Setter for cups
     * 
     * @param cups // Variable
     */
    public void setCups(int cups) {
        if (cups < 0)
            return;
        this.cups = cups;
    }

    /**
     * Getter for cups
     * 
     * @return // returns # cups
     */
    public int getCups() {
        return cups;
    }

    /**
     * Setter for specail
     * 
     * @param special // Variable
     */
    public void setSpecial(int special) {
        if (special < 0)
            return;
        this.special = special;
    }

    /**
     * Getter for special
     * 
     * @return // Returns # special
     */
    public int getSpecial() {
        return special;
    }

    /**
     * Setter for money
     * 
     * @param money // Variable
     */
    public void setMoney(double money) {
        this.moneyAmount = money;
    }

    /**
     * Getter for money
     * 
     * @return // reutnr # money _.__
     */
    public double getMoney() {
        return moneyAmount;
    }

    /**
     * Setter for happiness
     * 
     * @param emotion //Variable
     */
    public void setHappiness(String emotion) {
        this.happinessLevel = emotion;
    }

    public double getPercentMadeRight() {
        return percentMadeRight;
    }

    /**
     * Setter for day
     * 
     * @param day //Variable
     */
    public void setDay(int day) {
        this.dayCount = day;
    }

    /**
     * Getter for day
     * 
     * @return / returns # day
     */
    public int getDay() {
        return dayCount;
    }

    /**
     * Setter for customer
     * 
     * @param customer // Variable
     */
    public void setCustomer(int customer) {
        this.customerCount = customer;
    }

    /**
     * Getter for customer
     * 
     * @return // returns # cusomter
     */
    public int getCustomer() {
        return customerCount;
    }

    /**
     * This method randomly create the drink combo
     * This will be used and display on the ticket label
     * This is what the player will have to match to complete an order successfully
     * 
     * @return // this returns the current order that the player must match
     */
    public String drinkCombos() {
        Random rand = new Random();

        String iceStr = "";
        String sugarStr = "";
        String lemonadeStr = "Lemonade";
        String specialStr = "";

        int iceInt = rand.nextInt(2);
        switch (iceInt) {
            case 0 -> iceStr = "Ice, ";
            case 1 -> iceStr = "No Ice, ";
        }

        int sugarInt = rand.nextInt(2);
        switch (sugarInt) {
            case 0 -> sugarStr = "Sugar, ";
            case 1 -> sugarStr = "No Sugar, ";
        }

        if (dayCount > 2) {
            int specialInt = rand.nextInt(2);
            switch (specialInt) {
                case 0 -> specialStr = "Strawberry ";
                case 1 -> specialStr = "";
            }
        }

        currentOrder = iceStr + sugarStr + specialStr + lemonadeStr;
        return currentOrder;
    }

    /**
     * This sets to currentOrder to a speific place where as drinkCombo is ever
     * changing
     * This can be called to retrieve data of the currentOrder without if changing
     * 
     * @return // returns the current order
     */
    public String getOrder() {
        return currentOrder;
    }

    /**
     * This method checks if the day has just changed
     * This is important for the program to know operate the shop
     * It needs to be open at customer 1 after that the shop is close for the rest
     * of the day
     * 
     * @return // returns t/f
     */
    public boolean isDayJustChanged() {
        return dayJustChanged;
    }

    /**
     * This method is the heart of checking if the player input matches to the order
     * (At first I was trying to compare String and that had many flaws)
     * Claude AI was uses and created the idea to use booleans to check for ice, and
     * sugar
     * (I later realized lemonade also needed to have a check due to a bug)
     * 
     * @param playerHasIce   // these each are player input
     * @param playerHasSugar
     * @param playerHasLemon
     */
    // At first I had this try to check String but I used Claude AI to point out my
    // bugs and it suggested I
    // I use booleans to do the checking instead
    public void checkDrink(boolean playerHasIce, boolean playerHasSugar, boolean playerHasLemon,
            boolean playerHasSpecial) {
        String order = currentOrder.toLowerCase();

        // Sometimes ice or sugar is wanted but lemons are necessary
        boolean orderWantsIce = order.contains("ice") && !order.contains("no ice");
        boolean orderWantsSugar = order.contains("sugar") && !order.contains("no sugar");
        boolean orderWantsSpecial = order.contains("strawberry");

        if (playerHasLemon && playerHasIce == orderWantsIce && playerHasSugar == orderWantsSugar
                && playerHasSpecial == orderWantsSpecial) {
            moneyAmount += 2.00;
            if (percentMadeRight <= 90) {
                percentMadeRight += 10;
            }
        } else {
            percentMadeRight -= 10;
        }
        // This runs after the checks to continue on with the program
        getHappiness();
        drinkCombos();
        nextCustomer();
    }

    /**
     * This methods keeps track of the days and customers
     * The code now allows 5 customers per day then a new day starts
     */
    public void nextCustomer() {
        if (customerCount < 5) {
            customerCount++;
            dayJustChanged = false;
        } else {
            customerCount = 1;
            dayCount++;
            dayJustChanged = true;
        }
    }

    public String getHappiness() {

        if (percentMadeRight >= 100) {
            happinessLevel = happy;
            return happinessLevel;
        } else if (percentMadeRight < 100 && percentMadeRight >= 90) {
            happinessLevel = okay;
            return happinessLevel;
        } else if (percentMadeRight < 90 && percentMadeRight >= 80) {
            happinessLevel = upset;
            return happinessLevel;
        } else {
            happinessLevel = mad;
            return happinessLevel;
        }

    }

    /**
     * This method checks for game over
     * 
     * @return // This is given speific check to look out for the game to be over
     */
    public boolean isGameOver() {
        return (dayCount > 5) || (dayCount > 1 && !dayJustChanged) && (moneyAmount <= 0.00 || lemons <= 0 || cups <= 0)
                || (happinessLevel.equals(mad));
    }
}

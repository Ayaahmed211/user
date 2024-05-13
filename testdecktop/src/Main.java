//
//
////}
//public class Main {
//    public static void main(String[] args) {
//        // Create test users
//        User user1 = new User("batot", "password1235", false, 100.0f);
//        User user2 = new User("alice", "password456", true, 200.0f);
//
//        // Create test stocks
//        Stock stock1 = new Stock();
//        stock1.setName("TestStock1");
//        stock1.setPrice(50.0);
//        stock1.setQuantity(0);
//
//        Stock stock2 = new Stock();
//        stock2.setName("TestStock2");
//        stock2.setPrice(70.0);
//        stock2.setQuantity(0);
//
//        // Simulate transactions for user1
//        user1.buy(stock1);
//        user1.sell(stock1);
//        user1.buy(stock2);
//        user1.sell(stock2);
//
//        // Simulate transactions for user2
//        user2.buy(stock1);
//        user2.sell(stock1);
//        user2.buy(stock2);
//        User user = new User("john_doe", "password", false, 500f); // Initial balance is 500
//
//        // Deposit into the user's account
//        user1.deposit(20);
//
//        // Withdraw  from the user's account
//        user.deposit(50);
//        user.withdraw(200);
//
//    }
//
//    }
//public class Main {
//    public static void main(String[] args) {
//        // Create a User object with an initial balance of $10,000
//        User user = new User("username", "password", false, 10000);
//
//        // Create a Stock object representing Apple Inc.
//        Stock appleStock = new Stock();
//        appleStock.setName("Apple Inc.");
//        appleStock.setPrice(150); // Assuming the price of one stock is $150
//
//        // Simulate buying 50 stocks from Apple
//        for (int i = 0; i < 50; i++) {
//            user.buy(appleStock);
//        }
//
//        // Display the updated balance
//
//    }
//}

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a user
        User user = new User("username", "password", false, 100000); // Assuming initial balance is 100000

        // Buy some stocks
        Stock appleStock = new Stock("Apple Inc.", 150.50, "AAPL", true, 10000);

        user.buy(appleStock, 50); // Buy 50 stocks of Apple

        // Sell some stocks
        user.sell(appleStock, 20); // Sell 20 stocks of Apple

        // Verify session CSV

    }


    }









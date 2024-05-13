import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User extends Person {
    private boolean isPremium;
    private float moneyBalance;

    public User(String username, String password, boolean isPremium, float moneyBalance) {
        super("", password, username); // Assign username as userName in Person class
        this.isPremium = isPremium;
        this.moneyBalance = moneyBalance;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    public void buy(Stock stock, int quantity) {
        // Check if user has enough balance to buy the stock
        float totalCost = (float) (stock.getPrice() * quantity);
        if (this.moneyBalance >= totalCost) {
            // Deduct money from balance
            this.moneyBalance -= totalCost;
            // Update stock quantity
            stock.setQuantity(stock.getQuantity() + quantity);
            // Update volume in session CSV
            updateVolume(stock.getName(), -quantity); // Decrease volume by the quantity bought
            // Update user's CSV file
            updateTransactionHistory(stock, quantity, "Buy");
        } else {
            System.out.println("Insufficient balance to buy the stocks.");
        }
    }

    public void sell(Stock stock, int quantity) {
        // Check if user owns enough quantity of the stock to sell
        if (stock.getQuantity() >= quantity) {
            // Add money to balance
            float totalValue = (float) (stock.getPrice() * quantity);
            this.moneyBalance += totalValue;
            // Update stock quantity
            stock.setQuantity(stock.getQuantity() - quantity);
            // Update volume in session CSV
            updateVolume(stock.getName(), quantity); // Increase volume by the quantity sold
            // Update user's CSV file
            updateTransactionHistory(stock, quantity, "Sell");
        } else {
            System.out.println("You do not own enough quantity of this stock to sell.");
        }
    }

    // Method to update volume in session CSV file
    private void updateVolume(String companyName, int change) {
        String fileName = "Stocks/Session.CSV";
        List<String> companies = CSVModifier.readCSVColumn(fileName, 0);
        List<String> volumes = CSVModifier.readCSVColumn(fileName, 4);

        int index = companies.indexOf(companyName);
        if (index != -1) {
            int currentVolume = Integer.parseInt(volumes.get(index));
            int newVolume = currentVolume + change;
            volumes.set(index, String.valueOf(newVolume));

            List<String[]> newData = new ArrayList<>();
            for (int i = 0; i < companies.size(); i++) {
                String[] row = { companies.get(i), volumes.get(i) };
                newData.add(row);
            }

            CSVModifier.clearCSVFile(fileName);

            for (String[] row : newData) {
                CSVModifier.writeDataToCSVBelow(fileName,newData );
            }
        }
    }

    // Method to update transaction history in user's CSV file
    private void updateTransactionHistory(Stock stock, int quantity, String transactionType) {
        String fileName = this.userName + ".csv";
        String filePath = "src/Stocks/" + fileName;

        // Get current date and time
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);

        float totalValue = (float) (stock.getPrice() * quantity * (transactionType.equals("Buy") ? -1 : 1));

        // Calculate remaining balance
        float remainingBalance = this.moneyBalance + totalValue;

        // Prepare transaction data
        String[] rowData = {
                formattedDateTime,
                stock.getName(),
                transactionType,
                String.valueOf(stock.getPrice()),
                String.valueOf(quantity),
                String.valueOf(totalValue),
                String.valueOf(remainingBalance)
        };

        // Write transaction data to user's CSV file
        List<String[]> data = new ArrayList<>();
        data.add(rowData);
        boolean fileExists = CSVModifier.createBlankCSV(filePath);

        // Write header line if the file is newly created
        if (!fileExists) {
            String[] header = {"Date and Time", "Stock Name", "Transaction Type", "Price per Stock", "Volume", "Total Transaction Value", "Remaining Balance"};
            data.add(0, header); // Insert header line at the beginning of the list
        }

        CSVModifier.writeDataToCSVBelow(filePath, data);
    }

    public void deposit(float amount) {
        this.moneyBalance += amount;
        // Update user's CSV file
        updateTransactionHistoryDepositWithdraw("Deposit", amount);
    }

    public void withdraw(float amount) {
        if (this.moneyBalance >= amount) {
            this.moneyBalance -= amount;
            // Update user's CSV file
            updateTransactionHistoryDepositWithdraw("Withdraw", amount);
        } else {
            System.out.println("Insufficient balance to withdraw.");
        }
    }

    // Method to update transaction history in user's CSV file for deposit/withdraw
    private void updateTransactionHistoryDepositWithdraw(String transactionType, float amount) {
        String fileName = this.userName + ".csv";
        String filePath = "src/Stocks/" + fileName;

        // Get current date and time
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);

        // Prepare transaction data
        String[] rowData = {
                formattedDateTime,
                "Deposit/Withdraw",
                transactionType,
                "",
                "",
                String.valueOf(amount),
                String.valueOf(this.moneyBalance)
        };

        // Write transaction data to user's CSV file
        List<String[]> data = new ArrayList<>();
        data.add(rowData);
        boolean fileExists = CSVModifier.createBlankCSV(filePath);

        // Write header line if the file is newly created
        if (!fileExists) {
            String[] header = {"Date and Time", "Stock Name", "Transaction Type", "Price per Stock", "Volume", "Total Transaction Value", "Remaining Balance"};
            data.add(0, header); // Insert header line at the beginning of the list
        }

        CSVModifier.writeDataToCSVBelow(filePath, data);
    }
}

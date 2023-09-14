import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Wallet {
    private String accountType;
    private String accountHolder;
    private double balance;

    public Wallet(String accountType, String accountHolder, double amount) {
        this.accountType = accountType;
        this.accountHolder = accountHolder;
        this.balance = amount;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void addMoney(double amount) {
        if (amount < 1) {
            System.out.println("Minimum amount to add is F₹ 1.");
            return;
        }
        balance += amount;
        System.out.println("F₹ " + amount + " added to the wallet.");
    }

    public void transferMoney(Wallet recipient, double amount) {
        if (amount < 1) {
            System.out.println("Minimum transfer amount is F₹ 1.");
            return;
        }
        if (balance < amount) {
            System.out.println("Insufficient funds.");
            return;
        }
        if (accountType.equals("Business") && recipient.getAccountType().equals("Business")) {
            balance -= amount;
            recipient.addMoney(amount);
            System.out.println("F₹ " + amount + " transferred from " + accountHolder + " to " + recipient.getAccountHolder() + ".");
        } else if (accountType.equals("Personal")) {
            balance -= amount;
            recipient.addMoney(amount);
            System.out.println("F₹ " + amount + " transferred from " + accountHolder + " to " + recipient.getAccountHolder() + ".");
        } else {
            System.out.println("Business accounts can only transfer money to other business accounts.");
        }
    }
}

class WalletSystem {
    private List<Wallet> wallets;
    private Map<String, Wallet> walletMap;

    public WalletSystem() {
        wallets = new ArrayList<>();
        walletMap = new HashMap<>();
    }

    public void createWallet(String accountType, String accountHolder, double amount) {
        if (accountType.equals("Personal")) {
            Wallet wallet = new Wallet(accountType, accountHolder, amount);
            wallets.add(wallet);
            walletMap.put(accountHolder, wallet);
            System.out.println("Personal wallet created for " + accountHolder + ".");
        } else if (accountType.equals("Business")) {
            Wallet wallet = new Wallet(accountType, accountHolder, amount);
            wallets.add(wallet);
            walletMap.put(accountHolder, wallet);
            System.out.println("Business wallet created for " + accountHolder + ".");
        } else {
            System.out.println("Invalid account type.");
        }
    }

    public void addMoneyToWallet(String accountHolder, double amount) {
        Wallet wallet = walletMap.get(accountHolder);
        if (wallet != null) {
            wallet.addMoney(amount);
        } else {
            System.out.println("Wallet not found.");
        }
    }

    public void transferMoney(String sender, String recipient, double amount) {
        Wallet senderWallet = walletMap.get(sender);
        Wallet recipientWallet = walletMap.get(recipient);

        if (senderWallet != null && recipientWallet != null) {
            senderWallet.transferMoney(recipientWallet, amount);
        } else {
            System.out.println("Invalid sender or recipient wallet.");
        }
    }

    public void displayBalance(String accountHolder) {
        Wallet wallet = walletMap.get(accountHolder);
        if (wallet != null) {
            System.out.println("Balance for " + accountHolder + ": F₹ " + wallet.getBalance());
        } else {
            System.out.println("Wallet not found.");
        }
    }

    public void displayOverview() {
        for (Wallet wallet : wallets) {
            System.out.println("Account Holder: " + wallet.getAccountHolder());
            System.out.println("Account Type: " + wallet.getAccountType());
            System.out.println("Balance: F₹ " + wallet.getBalance());
            System.out.println("-------------------------");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        WalletSystem walletSystem = new WalletSystem();

        walletSystem.createWallet("Personal", "John", 100);
        walletSystem.createWallet("Business", "ABC Corp", 500);
        walletSystem.createWallet("Personal", "Alice", 200);

        walletSystem.displayBalance("John");
        walletSystem.displayBalance("ABC Corp");
        walletSystem.displayBalance("Alice");

        walletSystem.addMoneyToWallet("John", 50);
        walletSystem.addMoneyToWallet("ABC Corp", 100);

        walletSystem.transferMoney("John", "Alice", 75);
        walletSystem.transferMoney("ABC Corp", "John", 200);

        walletSystem.displayOverview();
    }
}
package flipakartwaleet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Wallet {
	private String accountType;
	private String CompanyName;
	private double GST;
	private double Balanceamount;
	private String userName;

	public Wallet(String accountType, String companyName, double gST, double balanceamount) {
		super();
		this.accountType = accountType;
		this.CompanyName = companyName;
		this.GST = gST;
		this.Balanceamount = balanceamount;
		this.userName=userName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public double getGST() {
		return GST;
	}

	public void setGST(double gST) {
		GST = gST;
	}

	public double getBalanceamount() {
		return Balanceamount;
	}

	public void setBalanceamount(double balanceamount) {
		Balanceamount = balanceamount;
	}

	public void addMoney(double amount) {
		if (amount < 1) {
			System.out.println("Minimum amount to add is F₹ 1.");
			return;
		}
		Balanceamount += amount;
		System.out.println("F₹ " + amount + " added to the wallet.");
	}

	public void transferMoney(Wallet recipient, double amount) {
		if (amount < 1) {
			System.out.println("Minimum transfer amount is F₹ 1.");
			return;
		}
		if (Balanceamount < amount) {
			System.out.println("Insufficient funds.");
			return;
		}
		if (accountType.equals("Business") && recipient.getAccountType().equals("Business")) {
			Balanceamount -= amount;
			recipient.addMoney(amount);
			System.out.println(
					"F₹ " + amount + " transferred from " + CompanyName + " to " + recipient.getCompanyName() + ".");
		} else if (accountType.equals("Personal")) {
			System.out.println("Business wallet can’t transfer money to the Personal account");
			System.out.println("Business wallet can transfer money only to business account");
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

	public void createWallet(String accountType, String companyName, double gST, double balanceamount) {

		if (accountType.equals("Business")) {
			Wallet wallet = new Wallet(accountType, companyName, gST, balanceamount);
			wallets.add(wallet);
			walletMap.put(companyName, wallet);
			System.out.println("Business wallet created for " + companyName + ".");
		} else {
			System.out.println("Invalid account type.");
		}
	}

	public void addMoneyToWallet(String companyName, double amount) {
		Wallet wallet = walletMap.get(companyName);
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

	public void displayBalance(String companyName) {
		Wallet wallet = walletMap.get(companyName);
		if (wallet != null) {
			System.out.println("Balance for " + companyName + ": F₹ " + wallet.getBalanceamount());
		} else {
			System.out.println("Wallet not found.");
		}
	}

	public void overview() {
		for (Wallet wallet : wallets) {
			System.out.println("Account Holder is : " + wallet.getCompanyName());
			System.out.println("Type of account is : " + wallet.getAccountType());
			System.out.println("Balance is : F₹ " + wallet.getBalanceamount());
		}
	}

public class FlipWallet {
    public static void main(String[] args) {
        WalletSystem walletSystem = new WalletSystem();

        walletSystem.createWallet("Business", "ABC comapny", 100, 1000);
        walletSystem.createWallet("Business", "XYZ company ", 60, 9000);
        walletSystem.createWallet("Business", "MNO company", 10, 8000);

        walletSystem.displayBalance("ABC comapny");
        walletSystem.displayBalance("XYZ company");
        walletSystem.displayBalance("MNO company");

        walletSystem.addMoneyToWallet("ABC comapny", 50);
        walletSystem.addMoneyToWallet("XYZ company", 100);

        walletSystem.transferMoney("ABC comapny", "XYZ company", 75);
        walletSystem.transferMoney("XYZ company", "ABC comapny", 200);

        walletSystem.overview();
    }

}

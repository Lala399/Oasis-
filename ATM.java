import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction { 
    String type;
    float amount;

    Transaction(String type, float amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": " + amount;
    }
}

public class ATM {
    int[] uid = {123, 456, 789};
    int[] upwd = {333, 444, 555};
    float[] balance = {5000.00f, 6000.00f, 7000.00f};
    int euid, eupwd, flag = 0, attempt = 1, rtch = 0, ch;
    Scanner sc = new Scanner(System.in);
    List<Transaction> transactionHistory = new ArrayList<>();

    void loginCheck() {
        for (attempt = 1; attempt <= 3; attempt++) {
            System.out.println("Enter ID: ");
            euid = sc.nextInt();
            System.out.println("Enter password: ");
            eupwd = sc.nextInt();

            for (int i = 0; i < uid.length; i++) {
                if (euid == uid[i] && eupwd == upwd[i]) {
                    flag = 1;
                    break;
                } else {
                    System.out.println("Check Your Credentials !!");
                }
            }

            if (flag == 1) {
                break;
            }
        }

        if (attempt > 3) {
            System.out.println("Your account has been blocked for 24 hr");
        }
    }

    void options() {
        if (flag == 1) {
            do {
                System.out.println("1 : Transaction History");
                System.out.println("2 : Withdraw");
                System.out.println("3 : Deposit");
                System.out.println("4 : Transfer");
                System.out.println("5 : EXIT");

                System.out.print("Enter Your Choice : ");
                ch = sc.nextInt();

                switch (ch) {
                    case 1:
                    System.out.println("Transaction History:");
                    for (Transaction transaction : transactionHistory) {
                        System.out.println(transaction);
                    }
                    break;


                    case 2:
                        int withdrawIndex = getIndex(euid);
                        if (withdrawIndex != -1) {
                            System.out.println("Enter the amount : ");
                            int withdrawAmount = sc.nextInt();
                            if (balance[withdrawIndex] - withdrawAmount >= 0) {
                                balance[withdrawIndex] -= withdrawAmount;
                                transactionHistory.add(new Transaction("Withdraw", withdrawAmount));
                                System.out.println("Ava Bal : " + balance[withdrawIndex]);
                            } else {
                                System.out.println("Transaction can't be processed");
                            }
                        } else {
                            System.out.println("User not found");
                        }
                        break;

                    case 3:
                        int depositIndex = getIndex(euid);
                        if (depositIndex != -1) {
                            System.out.println("Enter the amount : ");
                            int depositAmount = sc.nextInt();
                            balance[depositIndex] += depositAmount;
                            transactionHistory.add(new Transaction("Deposit", depositAmount));
                            System.out.println("Ava Bal : " + balance[depositIndex]);
                        } else {
                            System.out.println("User not found");
                        }
                        break;

                    case 4:
                    int transferIndex = getIndex(euid);
                    if (transferIndex != -1) {
                        System.out.println("Enter the recipient's ID: ");
                        int recipientId = sc.nextInt();
                        int recipientIndex = getIndex(recipientId);
                        if (recipientIndex != -1) {
                            System.out.println("Enter the amount to transfer: ");
                            int transferAmount = sc.nextInt();
                            if (balance[transferIndex] - transferAmount >= 0) {
                                balance[transferIndex] -= transferAmount;
                                balance[recipientIndex] += transferAmount;
                                System.out.println("Transfer successful!");
                                System.out.println("Available balance (sender): " + balance[transferIndex]);
                                System.out.println("Available balance (recipient): " + balance[recipientIndex]);
                            } else {
                                System.out.println("Transaction can't be processed");
                            }
                        } else {
                            System.out.println("Recipient not found");
                        }
                    } else {
                        System.out.println("User not found");
                    }
                    break;

                    case 5:
                        System.exit(0);

                    default:
                        System.out.println("Invalid Choice");
                }

                System.out.println("Want to do another transaction 1/0");
                rtch = sc.nextInt();
            } while (rtch == 1);
        }
    }

    int getIndex(int userId) {
        for (int i = 0; i < uid.length; i++) {
            if (userId == uid[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ATM t = new ATM();
        t.loginCheck();
        t.options();
    }
}
public class BankAccount {
    private int balance = 10000;

    // 存款
    public synchronized void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + "存款" + amount + "，余额为：" + balance);
        }
    }

    // 取款
    public synchronized boolean drawMoney(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + "取款" + amount + "，余额为：" + balance);
            return true;
        }
        System.out.println(Thread.currentThread().getName() + " 取款失败：余额不足或取款金额不符合规定");
        return false;
    }

    // 查询余额
    public synchronized int getBalance() {
        return balance;
    }

    // ATM转账
    public synchronized boolean transfer(BankAccount targetAccount, int amount) {
        if (amount > 0 && balance >= amount) {
            boolean success = targetAccount.drawMoney(-amount); // 注意这里用负数表示存款
            if (success) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " 转账 " + amount + " 到 " + targetAccount.hashCode() + "，转出账户余额为：" + balance);
                System.out.println(Thread.currentThread().getName() + " 转账 " + amount + " 到 " + targetAccount.hashCode() + "，转入账户余额为：" + targetAccount.getBalance());
                return true;
            }
        }
        System.out.println(Thread.currentThread().getName() + " 转账失败：余额不足或转账金额不合法");
        return false;
    }

    // 使用Runnable接口定义客户行为
    static class BankClient implements Runnable {
        private BankAccount account;
        private int amount;
        private BankOperationType operationType;
        private BankAccount targetAccount;

        // 定义枚举类型来区分不同的操作
        enum BankOperationType {
            DEPOSIT,
            WITHDRAW,
            GET_BALANCE,
            TRANSFER
        }

        public BankClient(BankAccount account, int amount, BankOperationType operationType, BankAccount targetAccount) {
            this.account = account;
            this.amount = amount;
            this.operationType = operationType;
            this.targetAccount = targetAccount;
        }

        public void run() {
            switch (operationType) {
                case DEPOSIT:
                    account.deposit(amount);
                    break;
                case WITHDRAW:
                    account.drawMoney(amount);
                    break;
                case GET_BALANCE:
                    System.out.println(Thread.currentThread().getName() + " 查询余额：" + account.getBalance());
                    break;
                case TRANSFER:
                    account.transfer(targetAccount, amount);
                    break;
                default:
                    throw new IllegalStateException("Unsupported operation type: " + operationType);
            }
        }
    }

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount();
        BankAccount account2 = new BankAccount(); // 假设这是另一个账户，用于转账
        // 创建并启动多个线程模拟多个客户同时操作
        Thread client1 = new Thread(new BankClient(account1, 2000, BankClient.BankOperationType.DEPOSIT, null)); // 客户1存款
        Thread client2 = new Thread(new BankClient(account1, 3000, BankClient.BankOperationType.WITHDRAW, null)); // 客户2取款
        Thread client3 = new Thread(new BankClient(account1, 0, BankClient.BankOperationType.GET_BALANCE, null)); // 客户3查询余额
        Thread client4 = new Thread(new BankClient(account1, 1000, BankClient.BankOperationType.TRANSFER, account2)); // 客户4转账到account2
        client1.start();
        client2.start();
        client3.start();
        client4.start();
        // 等待所有线程完成
        try {
            client1.join();
            client2.join();
            client3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出最终余额
        System.out.println("最终余额：" + account1.balance);
    }
}
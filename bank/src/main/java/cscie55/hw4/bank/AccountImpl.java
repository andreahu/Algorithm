package cscie55.hw4.bank;

import cscie55.hw4.utils.NumUtil;

public class AccountImpl implements Account {
    private int id;
    private long balance;

    public AccountImpl() {
        this.id = NumUtil.getRandomBetween(0, 1000);
        this.balance = 0;
    }

    public AccountImpl(int id) {
        this.id = id;
        this.balance = 0;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    @Override
    public void deposit(long amount) throws IllegalArgumentException{
        if(amount <= 0){
            throw new IllegalArgumentException();
        }else {
            this.balance = balance + amount;
        }
    }

    @Override
    public void withdraw(long amount) throws InsufficientFundsException, IllegalArgumentException {
        if(amount <= 0){
            throw new IllegalArgumentException();
        }else if (balance - amount < 0) {
            throw new InsufficientFundsException(this, amount);
        } else {
            balance = balance - amount;
        }
    }
}
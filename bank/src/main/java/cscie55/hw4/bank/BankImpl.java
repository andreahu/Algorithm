package cscie55.hw4.bank;

//import java.util.ArrayList;

import java.util.HashMap;

public class BankImpl implements Bank {

//    ArrayList<Account> accountList = new ArrayList<>();

    private HashMap<Integer, Account> idAccountMap = new HashMap<>();

    @Override
    public void addAccount(Account account) throws DuplicateAccountException {
        int accountId = account.getId();
        if (idAccountMap.containsKey(accountId)) {//No two Accounts should have the same id
            throw new DuplicateAccountException(accountId);
        } else {
            idAccountMap.put(account.getId(),account);
        }
    }

    @Override
    public void transferWithoutLocking(int fromId, int toId, long amount) throws InsufficientFundsException {//not use the synchronized keyword at all
        try{
            idAccountMap.get(fromId).withdraw(amount);
            idAccountMap.get(toId).deposit(amount);
        }catch (InsufficientFundsException e){
//            System.out.println("Exception catched: " + e.getMessage());
        }
    }

    @Override
    public void transferLockingBank(int fromId, int toId, long amount) throws InsufficientFundsException {
        synchronized (this) {
            try {
                idAccountMap.get(fromId).withdraw(amount);
                idAccountMap.get(toId).deposit(amount);
            } catch (InsufficientFundsException e) {
//                System.out.println("Exception catched: " + e.getMessage());
            }

        }
    }

    @Override
    public void transferLockingAccounts(int fromId, int toId, long amount) throws InsufficientFundsException {
        synchronized(idAccountMap.get(fromId)) {
            try {
                idAccountMap.get(fromId).withdraw(amount);
            } catch (InsufficientFundsException e) {
                return;
//                System.out.println("Exception catched: " + e.getMessage());
            }
        }

        synchronized(idAccountMap.get(toId)) {
            idAccountMap.get(toId).deposit(amount);
        }
    }

    @Override
    public long getTotalBalances() {
        long totalBalance = 0;
        for(Account account : idAccountMap.values()){
            totalBalance = totalBalance + account.getBalance();
        }
        return totalBalance;
    }

    @Override
    public int getNumberOfAccounts() {
        return idAccountMap.size();
    }
}
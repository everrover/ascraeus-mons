package dsa.leetcode.Ipestus;

public class SimpleBankSystem {
  private long[] balance;

  /**
   * https://leetcode.com/problems/simple-bank-system/description/
   * 
   * Implement a banking system that handles transactions such as transfer, deposit, and withdraw.
   * Ensure transactions only proceed if they are valid based on constraints.
   * 
   * TC: O(1) SC: O(n)
   * #array #simulation #design #medium
   */
  
  public SimpleBankSystem(long[] balance) {
    this.balance = balance;
  }

  public boolean transfer(int account1, int account2, long money) {
    if (account1 > balance.length || account2 > balance.length || balance[account1 - 1] < money) return false;
    balance[account1 - 1] -= money;  // Deduct money from account1
    balance[account2 - 1] += money;  // Add money to account2
    return true;
  }

  public boolean deposit(int account, long money) {
    if (account > balance.length) return false;
    balance[account - 1] += money;
    return true;
  }

  public boolean withdraw(int account, long money) {
    if (account > balance.length || balance[account - 1] < money) return false;
    balance[account - 1] -= money;
    return true;
  }
}
package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.swing.plaf.basic.BasicBorders.MarginBorder;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(mRossi, bankAccount.getAccountHolder());
        assertEquals(0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        bankAccount.chargeManagementFees(mRossi.getUserID());
        bankAccount.deposit(mRossi.getUserID(), 100);
        assertEquals(100, bankAccount.getBalance());
        assertEquals(1, bankAccount.getTransactionsCount());
        bankAccount.chargeManagementFees(mRossi.getUserID());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals((100 - (5 + (0.1 * 1))), bankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        try{
            assertEquals(0, bankAccount.getBalance());
            bankAccount.withdraw(mRossi.getUserID(), -100);
            assertEquals(0, bankAccount.getBalance());
        }catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        try{
            bankAccount.deposit(mRossi.getUserID(), 100);
            assertEquals(100, bankAccount.getBalance());
            bankAccount.withdraw(mRossi.getUserID(), 150);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        
    }
}

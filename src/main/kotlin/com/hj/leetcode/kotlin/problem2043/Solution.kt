package com.hj.leetcode.kotlin.problem2043

class Solution

/**
 * LeetCode page: [2043. Simple Bank System](https://leetcode.com/problems/simple-bank-system/);
 */
class Bank(
    private val balance: LongArray,
) {
    private fun hasAccount(account: Int): Boolean =
        account in 1..balance.size

    private fun getBalanceUnchecked(account: Int): Long =
        balance[account - 1]

    fun transfer(
        account1: Int,
        account2: Int,
        money: Long,
    ): Boolean {
        if (!canTransfer(account1, account2, money)) {
            return false
        }

        withdrawUnchecked(account1, money)
        depositUnchecked(account2, money)
        return true
    }

    private fun canTransfer(
        account1: Int,
        account2: Int,
        money: Long,
    ): Boolean =
        canWithdraw(account1, money) && canDeposit(account2, money)

    fun deposit(
        account: Int,
        money: Long,
    ): Boolean {
        if (!canDeposit(account, money)) {
            return false
        }

        depositUnchecked(account, money)
        return true
    }

    private fun canDeposit(
        account: Int,
        money: Long,
    ): Boolean = hasAccount(account) && 0 <= money

    private fun depositUnchecked(
        account: Int,
        money: Long,
    ): Long {
        balance[account - 1] += money
        return money
    }

    fun withdraw(
        account: Int,
        money: Long,
    ): Boolean {
        if (!canWithdraw(account, money)) {
            return false
        }

        withdrawUnchecked(account, money)
        return true
    }

    private fun canWithdraw(
        account: Int,
        money: Long,
    ): Boolean =
        hasAccount(account) &&
            money in 0..getBalanceUnchecked(account)

    private fun withdrawUnchecked(
        account: Int,
        money: Long,
    ): Long {
        balance[account - 1] -= money
        return money
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * var obj = Bank(balance)
 * var param_1 = obj.transfer(account1,account2,money)
 * var param_2 = obj.deposit(account,money)
 * var param_3 = obj.withdraw(account,money)
 */

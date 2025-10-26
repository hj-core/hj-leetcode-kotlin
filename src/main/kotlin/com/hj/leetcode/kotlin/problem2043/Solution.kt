package com.hj.leetcode.kotlin.problem2043

class Solution

/**
 * LeetCode page: [2043. Simple Bank System](https://leetcode.com/problems/simple-bank-system/);
 */
class Bank(
    private val balance: LongArray,
) {
    private fun hasAccount(account: Int): Boolean = 0 < account && account <= balance.size

    private fun getBalanceUnchecked(account: Int): Long = balance[account - 1]

    fun transfer(
        account1: Int,
        account2: Int,
        money: Long,
    ): Boolean {
        if (!hasAccount(account1) || !hasAccount(account2)) {
            return false
        }
        if (getBalanceUnchecked(account1) < money) {
            return false
        }

        depositUnchecked(account2, withdrawUnchecked(account1, money))
        return true
    }

    fun deposit(
        account: Int,
        money: Long,
    ): Boolean {
        if (!hasAccount(account)) {
            return false
        }

        depositUnchecked(account, money)
        return true
    }

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
        if (!hasAccount(account)) {
            return false
        }
        if (getBalanceUnchecked(account) < money) {
            return false
        }

        withdrawUnchecked(account, money)
        return true
    }

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

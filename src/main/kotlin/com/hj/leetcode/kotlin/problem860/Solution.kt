package com.hj.leetcode.kotlin.problem860

/**
 * LeetCode page: [860. Lemonade Change](https://leetcode.com/problems/lemonade-change/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of bills;
     */
    fun lemonadeChange(bills: IntArray): Boolean {
        var availableFive = 0
        var availableTen = 0

        for (bill in bills) {
            when (bill) {
                5 -> availableFive += 1

                10 -> {
                    availableTen += 1
                    availableFive -= 1
                }

                20 -> {
                    var change = 15
                    if (availableTen > 0) {
                        change -= 10
                        availableTen -= 1
                    }
                    availableFive -= change / 5
                }
            }

            if (availableFive < 0) {
                return false
            }
        }
        return true
    }
}
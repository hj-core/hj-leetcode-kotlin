package com.hj.leetcode.kotlin.problem860

/**
 * LeetCode page: [860. Lemonade Change](https://leetcode.com/problems/lemonade-change/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of bills;
     */
    fun lemonadeChange(bills: IntArray): Boolean {
        val availableCash = intArrayOf(0, 0, 0) // count of ($5, $10, $20)

        for (bill in bills) {
            when (bill) {
                5 -> availableCash[0] += 1

                10 -> {
                    availableCash[1] += 1
                    availableCash[0] -= 1
                }

                20 -> {
                    availableCash[2] += 1

                    var change = 15
                    if (availableCash[1] > 0) {
                        change -= 10
                        availableCash[1] -= 1
                    }
                    availableCash[0] -= change / 5
                }
            }

            if (availableCash[0] < 0) {
                return false
            }
        }
        return true
    }
}
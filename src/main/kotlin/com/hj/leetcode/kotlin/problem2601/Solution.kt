package com.hj.leetcode.kotlin.problem2601

import kotlin.math.sqrt

/**
 * LeetCode page: [2601. Prime Subtraction Operation](https://leetcode.com/problems/prime-subtraction-operation/);
 */
class Solution {
    /* Complexity:
     * Time O(N+MLogLogM) and Space O(M) where N is the size of nums
     * and M is the maximum value in nums.
     */
    fun primeSubOperation(nums: IntArray): Boolean {
        if (nums.last() < nums.size) {
            return false
        }
        val max = nums.max()
        val isPrime = isPrime(max)
        var minPrev = 0
        // This for-loop is O(N+M):
        // minPrev increased by the number of ops and when it reached M the for-loop exited.
        for (num in nums) {
            if (num <= minPrev) {
                return false
            }
            minPrev =
                (minPrev + 1..num).firstOrNull {
                    isPrime[num - it]
                } ?: num
        }
        return true
    }

    private fun isPrime(until: Int): BooleanArray {
        if (until < 2) {
            return BooleanArray(until)
        }
        // Sieve of Eratosthenes
        val result = BooleanArray(until) { true }
        result[0] = false
        result[1] = false

        val sieveRight = sqrt(until.toDouble()).toInt()
        for (num in 2..sieveRight) {
            if (result[num]) {
                for (multiple in (num * num)..<until step num) {
                    result[multiple] = false
                }
            }
        }
        return result
    }
}

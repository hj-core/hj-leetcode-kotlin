package com.hj.leetcode.kotlin.problem1497

/**
 * LeetCode page: [1497. Check If Array Pairs Are Divisible by k](https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N+k) and Space O(k) where N is the length of arr.
     */
    fun canArrange(
        arr: IntArray,
        k: Int,
    ): Boolean {
        var paired = 0
        val countRemainder = mutableMapOf<Int, Int>()
        for (num in arr) {
            val remainder = num.mod(k)
            val complement = if (remainder == 0) 0 else k - remainder

            val hasComplement = (countRemainder[complement] ?: 0) > 0
            if (hasComplement) {
                countRemainder.compute(complement) { _, v -> checkNotNull(v) - 1 }
                paired += 2
            } else {
                countRemainder.compute(remainder) { _, v -> 1 + (v ?: 0) }
            }
        }
        return paired == arr.size
    }
}

package com.hj.leetcode.kotlin.problem1497

/**
 * LeetCode page: [1497. Check If Array Pairs Are Divisible by k](https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/);
 */
class Solution {
    /* Complexity:
     * Time O(N+k) and Space O(k) where N is the length of arr.
     */
    fun canArrange(
        arr: IntArray,
        k: Int,
    ): Boolean {
        val countMod = arr.asIterable().groupingBy { it.mod(k) }.eachCountTo(mutableMapOf())
        // Handle cases that a mod need to pair with itself
        if (0 in countMod) {
            if (checkNotNull(countMod[0]) % 2 != 0) {
                return false
            }
            countMod.remove(0)
        }
        if (k % 2 == 0 && (k / 2) in countMod) {
            if (checkNotNull(countMod[k / 2]) % 2 != 0) {
                return false
            }
            countMod.remove(k / 2)
        }
        // Handle the reset cases that a mod will pair with another mod
        for ((mod, count) in countMod) {
            val complement = k - mod
            if (complement !in countMod || countMod[complement] != count) {
                return false
            }
        }
        return true
    }
}

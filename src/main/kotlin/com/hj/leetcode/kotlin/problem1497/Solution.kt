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
        /* There are two special cases where a mod pairs with itself: 0,
         * and k/2 when k is even.
         * We explicitly check the case where mod equals 0. The case of k/2 when k is even
         * is implicitly checked: if all other mod can be paired, then the count of k/2 must
         * be even because the length of arr is even.
         */
        if (0 in countMod) {
            if (checkNotNull(countMod[0]) % 2 != 0) {
                return false
            }
            countMod.remove(0)
        }

        for ((mod, count) in countMod) {
            if (countMod[k - mod] != count) {
                return false
            }
        }
        return true
    }
}

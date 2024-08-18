package com.hj.leetcode.kotlin.problem264

/**
 * LeetCode page: [264. Ugly Number II](https://leetcode.com/problems/ugly-number-ii/);
 */
class Solution2 {
    /* Complexity:
     * Time O(nLog(n)) and Space O(n);
     */
    fun nthUglyNumber(n: Int): Int {
        val candidates = sortedSetOf(1L)
        repeat(n - 1) {
            val pop = checkNotNull(candidates.pollFirst())
            candidates.add(pop * 2)
            candidates.add(pop * 3)
            candidates.add(pop * 5)
        }
        return checkNotNull(candidates.pollFirst()).toInt()
    }
}

package com.hj.leetcode.kotlin.problem264

/**
 * LeetCode page: [264. Ugly Number II](https://leetcode.com/problems/ugly-number-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun nthUglyNumber(n: Int): Int {
        var result = 1
        var count = 1
        val candidates = listOf(ArrayDeque<Int>(), ArrayDeque(), ArrayDeque())
        candidates[0].addLast(2)
        candidates[1].addLast(3)
        candidates[2].addLast(5)

        while (count < n) {
            val i = candidates.indices.minBy { candidates[it][0] }
            val candidate = candidates[i].removeFirst()
            if (candidate != result) {
                result = candidate
                count++
                candidates[0].addLast(candidate * 2)
                candidates[1].addLast(candidate * 3)
                candidates[2].addLast(candidate * 5)
            }
        }
        return result
    }
}

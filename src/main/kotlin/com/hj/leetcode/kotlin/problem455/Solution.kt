package com.hj.leetcode.kotlin.problem455

/**
 * LeetCode page: [455. Assign Cookies](https://leetcode.com/problems/assign-cookies/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN+MLogM) and Space O(N+M) where N is the size of g and
     * M is the size of s;
     */
    fun findContentChildren(g: IntArray, s: IntArray): Int {
        val greedSorted = g.sorted()
        val sizeSorted = s.sorted()
        var result = 0
        for (size in sizeSorted) {
            if (greedSorted[result] <= size) {
                result++
                if (result == greedSorted.size) {
                    return result
                }
            }
        }
        return result
    }
}
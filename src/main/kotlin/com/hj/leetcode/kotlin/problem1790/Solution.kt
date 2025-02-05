package com.hj.leetcode.kotlin.problem1790

/**
 * LeetCode page: [1790. Check if One String Swap Can Make Strings Equal](https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of `s1 `and `s2`.
     */
    fun areAlmostEqual(
        s1: String,
        s2: String,
    ): Boolean =
        s1
            .asSequence()
            .zip(s2.asSequence())
            .filter { (a, b) -> a != b }
            .take(3)
            .toList()
            .let {
                it.isEmpty() || (it.size == 2 && it[0].first == it[1].second && it[0].second == it[1].first)
            }
}

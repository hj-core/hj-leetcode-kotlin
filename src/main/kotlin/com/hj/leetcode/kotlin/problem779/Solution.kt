package com.hj.leetcode.kotlin.problem779

/**
 * LeetCode page: [779. K-th Symbol in Grammar](https://leetcode.com/problems/k-th-symbol-in-grammar/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(n);
     */
    fun kthGrammar(n: Int, k: Int): Int {
        if (n == 1 && k == 1) {
            return 0
        }

        val parent = kthGrammar(n - 1, (k + 1) / 2)
        return parent xor (k and 1) xor 1
    }
}
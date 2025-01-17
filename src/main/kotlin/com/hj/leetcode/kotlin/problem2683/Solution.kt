package com.hj.leetcode.kotlin.problem2683

/**
 * LeetCode page: [2683. Neighboring Bitwise XOR](https://leetcode.com/problems/neighboring-bitwise-xor/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of derived.
     */
    fun doesValidArrayExist(derived: IntArray): Boolean = derived.reduce(Int::xor) == 0
}

package com.hj.leetcode.kotlin.problem386

/**
 * LeetCode page: [386. Lexicographical Numbers](https://leetcode.com/problems/lexicographical-numbers/);
 */
class Solution {
    /* Complexity:
     * Time O(n) and Space O(1).
     */
    fun lexicalOrder(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var value = 1
        // Preorder traversal
        for (i in 0..<n) {
            result.add(value)
            if (value * 10 <= n) {
                value *= 10 // Move to the first child
            } else {
                // There is no next sibling or it exceeds n
                while (value % 10 == 9 || value == n) {
                    value /= 10 // Move back to parent
                }
                value += 1 // Move to the next sibling
            }
        }
        return result
    }
}

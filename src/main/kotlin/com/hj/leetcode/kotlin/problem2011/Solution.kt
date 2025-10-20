package com.hj.leetcode.kotlin.problem2011

/**
 * LeetCode page: [2011. Final Value of Variable After Performing Operations](https://leetcode.com/problems/final-value-of-variable-after-performing-operations/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of
    // operations.
    fun finalValueAfterOperations(operations: Array<String>): Int = operations.sumOf { ',' - it[1] }
}

package com.hj.leetcode.kotlin.problem3068

/**
 * LeetCode page: [3068. Find the Maximum Sum of Node Values](https://leetcode.com/problems/find-the-maximum-sum-of-node-values/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun maximumValueSum(nums: IntArray, k: Int, edges: Array<IntArray>): Long {
        val changes = nums.asSequence().map { (it xor k) - it }
        val goodChanges = changes.filter { it >= 0 }
        val sumGoodChanges = goodChanges.fold(0L) { acc, i -> acc + i }
        val sumNums = nums.fold(0L) { acc, i -> acc + i }
        var result = sumNums + sumGoodChanges

        if (goodChanges.count() % 2 == 1) {
            val minGoodChange = goodChanges.min()
            result -= minGoodChange

            val badChanges = changes.filter { it < 0 }
            badChanges.maxOrNull()?.let { maxBadChange ->
                result += (minGoodChange + maxBadChange).coerceAtLeast(0)
            }
        }
        return result
    }
}
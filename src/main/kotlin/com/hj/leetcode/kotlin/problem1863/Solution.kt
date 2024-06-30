package com.hj.leetcode.kotlin.problem1863

/**
 * LeetCode page: [1863. Sum of All Subset XOR Totals](https://leetcode.com/problems/sum-of-all-subset-xor-totals/);
 */
class Solution {
    /* Complexity:
     * Time O(2^N) and Space O(N) where N is the size of nums;
     */
    fun subsetXORSum(nums: IntArray): Int {
        var result = 0
        dfsSubsetTree(nums, 0, 0) { subsetXor ->
            result += subsetXor
        }
        return result
    }

    private fun dfsSubsetTree(
        nums: IntArray,
        index: Int,
        pathXor: Int,
        onEachSubsetXor: (subsetXor: Int) -> Unit,
    ) {
        if (index == nums.size) {
            onEachSubsetXor(pathXor)
            return
        }
        dfsSubsetTree(nums, index + 1, pathXor xor nums[index], onEachSubsetXor)
        dfsSubsetTree(nums, index + 1, pathXor, onEachSubsetXor)
    }
}
package com.hj.leetcode.kotlin.problem78

/**
 * LeetCode page: [78. Subsets](https://leetcode.com/problems/subsets/);
 */
class Solution2 {
    /* Complexity:
     * Time (N * 2^N) and Space O(N * 2^N) where N is the size of nums;
     */
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        dfsSubsetTree(nums, 0, mutableListOf()) { subset ->
            result.add(subset)
        }
        return result
    }

    private fun dfsSubsetTree(
        nums: IntArray,
        index: Int,
        path: MutableList<Int>,
        onEachSubset: (subset: List<Int>) -> Unit,
    ) {
        if (index == nums.size) {
            onEachSubset(path.toList())
            return
        }
        path.add(nums[index])
        dfsSubsetTree(nums, index + 1, path, onEachSubset)
        path.removeLast()
        dfsSubsetTree(nums, index + 1, path, onEachSubset)
    }
}
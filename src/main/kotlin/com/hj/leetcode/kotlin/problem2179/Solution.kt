package com.hj.leetcode.kotlin.problem2179

/**
 * LeetCode page: [2179. Count Good Triplets in an Array](https://leetcode.com/problems/count-good-triplets-in-an-array/);
 */
class Solution {
    // Complexity:
    // Time O(NLogN) and Space O(N) where N is the length of nums1 and nums2.
    fun goodTriplets(
        nums1: IntArray,
        nums2: IntArray,
    ): Long = countIncreasingTriplets(mapPosition(nums1, nums2))

    // mapPosition maps the elements in nums1 to their positions (one-indexed)
    // in nums2, given that both nums1 and nums2 are permutations of the integers
    // from 0 to len(nums1)-1.
    private fun mapPosition(
        nums1: IntArray,
        nums2: IntArray,
    ): IntArray {
        val valueToIndex2 = IntArray(nums2.size)
        for ((i, value) in nums2.withIndex()) {
            valueToIndex2[value] = i
        }

        val result = IntArray(nums1.size)
        for ((i, value) in nums1.withIndex()) {
            result[i] = valueToIndex2[value] + 1
        }
        return result
    }

    // countIncreasingTriplets returns the number of triplets (i, j, k) such that
    // i < j < k and nums[i] < nums[j] < nums[k], given that nums is a permutation
    // of the integers from 1 to len(nums).
    private fun countIncreasingTriplets(nums: IntArray): Long {
        // At state j, prefixFwt is the sum Fenwick tree on top of the indicator
        // array, which indicates the existence of numbers for nums[:j].
        // The initial values correspond to the state of j = 0.
        val prefixFwt = SumFwt(nums.size)

        var result = 0L
        for (j in 1..<(nums.size - 1)) {
            // Transition from state j-1 to state j
            prefixFwt.update(nums[j - 1], 1)

            // Compute and sum the number of increasing triplets at j
            val iChoices = prefixFwt.query(nums[j] - 1)
            val kChoices = (nums.size - nums[j]) - (j - prefixFwt.query(nums[j]))
            result += iChoices.toLong() * kChoices
        }
        return result
    }

    // sumFwt is a Fenwick Tree (one-indexed) that supports summing operations.
    private class SumFwt(
        size: Int,
    ) {
        private val tree = IntArray(size + 1)

        fun update(
            index: Int,
            increment: Int,
        ) {
            var i = index
            while (i < tree.size) {
                tree[i] += increment
                i = (i or (i - 1)) + 1
            }
        }

        fun query(index: Int): Int {
            var result = 0
            var i = index

            while (i > 0) {
                result += tree[i]
                i = i and (i - 1)
            }
            return result
        }
    }
}

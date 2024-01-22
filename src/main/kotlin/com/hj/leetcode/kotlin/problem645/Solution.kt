package com.hj.leetcode.kotlin.problem645

/**
 * LeetCode page: [645. Set Mismatch](https://leetcode.com/problems/set-mismatch/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun findErrorNums(nums: IntArray): IntArray {
        val repetitionXorLoss = getRepetitionXorLoss(nums)

        // bitmask that can distinguish between repetition and loss
        val distinguishMask = repetitionXorLoss.rightmostBit()
        val candidates = findCandidatesOfRepetitionAndLoss(nums, distinguishMask)
        return determineRepetitionAndLoss(nums, candidates)
    }

    private fun getRepetitionXorLoss(nums: IntArray): Int {
        val xorOperation = { a: Int, b: Int -> a xor b }
        return (nums.reduce(xorOperation)
                xor (1..nums.size).reduce(xorOperation))
    }

    private fun Int.rightmostBit() = this - (this and (this - 1))

    private fun findCandidatesOfRepetitionAndLoss(nums: IntArray, distinguishMask: Int): Pair<Int, Int> {
        var candidate1 = 0
        var candidate2 = 0

        for (num in nums) {
            if (num and distinguishMask == 0) {
                candidate1 = candidate1 xor num
            } else {
                candidate2 = candidate2 xor num
            }
        }

        for (num in 1..nums.size) {
            if (num and distinguishMask == 0) {
                candidate1 = candidate1 xor num
            } else {
                candidate2 = candidate2 xor num
            }
        }

        return Pair(candidate1, candidate2)
    }

    private fun determineRepetitionAndLoss(nums: IntArray, candidates: Pair<Int, Int>): IntArray {
        val (candidate1, candidate2) = candidates

        for (num in nums) {
            if (num == candidate1) return intArrayOf(candidate1, candidate2)
        }

        return intArrayOf(candidate2, candidate1)
    }
}
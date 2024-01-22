package com.hj.leetcode.kotlin.problem645

/**
 * LeetCode page: [645. Set Mismatch](https://leetcode.com/problems/set-mismatch/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of nums;
     */
    fun findErrorNums(nums: IntArray): IntArray {
        val (a, b) = errorsUnordered(nums)
        return if (a in nums) intArrayOf(a, b) else intArrayOf(b, a)
    }

    private fun errorsUnordered(nums: IntArray): Pair<Int, Int> {
        val xorOfErrors = xorOfErrors(nums)
        val bitMask = xorOfErrors.rightmostBit()
        val xorMasked = { acc: Int, i: Int ->
            if (i and bitMask == 0) acc xor i else acc
        }
        val aError = (nums.fold(0, xorMasked)
                xor (1..nums.size).fold(0, xorMasked))
        return Pair(aError, aError xor xorOfErrors)
    }

    private fun xorOfErrors(nums: IntArray): Int {
        val xorOperation = { a: Int, b: Int -> a xor b }
        return (nums.reduce(xorOperation)
                xor (1..nums.size).reduce(xorOperation))
    }

    private fun Int.rightmostBit() = this - (this and (this - 1))
}
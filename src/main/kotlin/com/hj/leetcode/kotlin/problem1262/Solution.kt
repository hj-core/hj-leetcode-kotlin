package com.hj.leetcode.kotlin.problem1262

/**
 * LeetCode page: [1262. Greatest Sum Divisible by Three](https://leetcode.com/problems/greatest-sum-divisible-by-three/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of nums.
    fun maxSumDivThree(nums: IntArray): Int {
        val sum = nums.sum()
        val sumRem = sum % 3
        if (sumRem == 0) {
            return sum
        }

        val rem1 = sumRem
        var minRem1Num = sum
        val rem2 = 3 - sumRem
        val minRem2Nums = intArrayOf(sum, sum)

        for (num in nums) {
            when (num % 3) {
                rem1 -> minRem1Num = minOf(minRem1Num, num)
                rem2 ->
                    if (num < minRem2Nums[0]) {
                        minRem2Nums[1] = minRem2Nums[0]
                        minRem2Nums[0] = num
                    } else if (num < minRem2Nums[1]) {
                        minRem2Nums[1] = num
                    }
            }
        }
        return sum - minOf(minRem1Num, minRem2Nums.sum())
    }
}

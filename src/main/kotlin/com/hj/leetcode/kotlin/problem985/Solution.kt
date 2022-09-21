package com.hj.leetcode.kotlin.problem985

/**
 * LeetCode page: [985. Sum of Even Numbers After Queries](https://leetcode.com/problems/sum-of-even-numbers-after-queries/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Aux_Space O(N) where N and M are the size of nums and queries;
     */
    fun sumEvenAfterQueries(nums: IntArray, queries: Array<IntArray>): IntArray {
        val ans = IntArray(queries.size)
        var sumOfEven = getSumOfEven(nums)
        val newNums = nums.clone()

        for (index in queries.indices) {
            val query = queries[index]
            val numIndex = query[1]
            if (newNums[numIndex].isEven()) sumOfEven -= newNums[numIndex]
            newNums[numIndex] += query[0]
            if (newNums[numIndex].isEven()) sumOfEven += newNums[numIndex]
            ans[index] = sumOfEven
        }
        return ans
    }

    private fun getSumOfEven(nums: IntArray): Int {
        var sum = 0
        for (num in nums) {
            if (num.isEven()) sum += num
        }
        return sum
    }

    private fun Int.isEven() = this and 1 == 0
}
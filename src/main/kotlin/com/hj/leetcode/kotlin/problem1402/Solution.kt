package com.hj.leetcode.kotlin.problem1402

/**
 * LeetCode page: [1402. Reducing Dishes](https://leetcode.com/problems/reducing-dishes/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of satisfaction;
     */
    fun maxSatisfaction(satisfaction: IntArray): Int {
        val sortedSatisfaction = satisfaction.sorted()
        var suffixSum = 0
        var maxResult = 0
        for (index in sortedSatisfaction.indices.reversed()) {
            suffixSum += sortedSatisfaction[index]
            val noBenefitFromNewDish = suffixSum <= 0
            if (noBenefitFromNewDish) break
            maxResult += suffixSum
        }
        return maxResult
    }
}
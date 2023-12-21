package com.hj.leetcode.kotlin.problem1011

/**
 * LeetCode page: [1011. Capacity To Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/);
 */
class Solution {
    /* Complexity:
     * Time O(N+K(LogM)(LogN)) and Space O(N) where N is the size of weights, K is days, and M is
     * the total weight;
     */
    fun shipWithinDays(weights: IntArray, days: Int): Int {
        val weightsPrefixSum = buildPrefixSum(weights)
        return findLeastCapacity(weightsPrefixSum, days)
    }

    private fun buildPrefixSum(weights: IntArray): IntArray {
        val result = weights.clone()
        for (i in 1 until result.size) {
            result[i] += result[i - 1]
        }
        return result
    }

    private fun findLeastCapacity(weightsPrefixSum: IntArray, days: Int): Int {
        val totalWeight = weightsPrefixSum.last()
        var lowerBound = maxOf(totalWeight / days, weightsPrefixSum.first())
        var upperBound = totalWeight
        while (lowerBound < upperBound) {
            val guess = (lowerBound + upperBound) ushr 1
            val canShipInDays = verifyCapacity(guess, weightsPrefixSum, days)
            if (canShipInDays) upperBound = guess else lowerBound = guess + 1
        }
        return lowerBound
    }

    private fun verifyCapacity(capacity: Int, weightsPrefixSum: IntArray, days: Int): Boolean {
        val totalWeight = weightsPrefixSum.last()
        var shippedWeight = 0
        repeat(days) {
            val maxShippedWeight = shippedWeight + capacity
            if (maxShippedWeight >= totalWeight) return true
            shippedWeight = findShippedWeight(maxShippedWeight, weightsPrefixSum)
            if (shippedWeight == 0) return false
        }
        return false
    }

    private fun findShippedWeight(maxShippedWeight: Int, weightsPrefixSum: IntArray): Int {
        val bsIndex = weightsPrefixSum.binarySearch(maxShippedWeight)
        val shippedWeightIndex = if (bsIndex >= 0) bsIndex else -(bsIndex + 1) - 1
        return if (shippedWeightIndex < 0) 0 else weightsPrefixSum[shippedWeightIndex]
    }
}
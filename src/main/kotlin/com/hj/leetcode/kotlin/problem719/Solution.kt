package com.hj.leetcode.kotlin.problem719

/**
 * LeetCode page: [719. Find K-th Smallest Pair Distance](https://leetcode.com/problems/find-k-th-smallest-pair-distance/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN+NLogM) and Space O(N) where N is the size of nums and
     * M is the difference between the smallest and largest elements of nums;
     */
    fun smallestDistancePair(nums: IntArray, k: Int): Int {
        val sorted = nums.sorted()
        var low = 0
        var high = sorted.last() - sorted.first()
        while (low <= high) {
            val guess = (low + high) ushr 1
            val count = countDistancesNoGreaterThan(sorted, guess)
            when {
                count < k -> low = guess + 1
                count > k -> high = guess - 1
                else -> high = guess - 1 // Force the guess to the true distance
            }
        }
        return low
    }

    private fun countDistancesNoGreaterThan(sortedNums: List<Int>, target: Int): Int {
        var result = 0
        var latestEnd = 0 // Exclusive
        for (start in sortedNums.indices) {
            while (latestEnd < sortedNums.size
                && sortedNums[latestEnd] - sortedNums[start] <= target
            ) {
                latestEnd++
            }
            result += latestEnd - start - 1
        }
        return result
    }
}
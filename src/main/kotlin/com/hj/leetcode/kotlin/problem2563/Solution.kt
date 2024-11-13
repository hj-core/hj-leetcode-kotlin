package com.hj.leetcode.kotlin.problem2563

/**
 * LeetCode page: [2563. Count the Number of Fair Pairs](https://leetcode.com/problems/count-the-number-of-fair-pairs/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of nums.
     */
    fun countFairPairs(
        nums: IntArray,
        lower: Int,
        upper: Int,
    ): Long {
        // We can swap i and j while still satisfying the sum constraint
        // One-to-one mapping between fair pairs in nums and in sortedNums
        val sortedNums = nums.sorted()
        var result = 0L
        var left = sortedNums.lastIndex // last index that violates lower
        var right = sortedNums.lastIndex // last index that satisfies upper

        for ((i, num) in sortedNums.withIndex()) {
            while (0 <= left && lower <= num + sortedNums[left]) {
                left -= 1
            }
            while (0 <= right && upper < num + sortedNums[right]) {
                right -= 1
            }
            // Limit the pairing indices to the left of i to avoid duplicates
            val smallerPairs =
                when {
                    i <= left -> 0
                    i <= right -> (i - left) - 1 // one for i itself
                    else -> right - left
                }
            result += smallerPairs
        }
        return result
    }
}

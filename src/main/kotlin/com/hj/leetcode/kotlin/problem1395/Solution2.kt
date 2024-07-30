package com.hj.leetcode.kotlin.problem1395

/**
 * LeetCode page: [1395. Count Number of Teams](https://leetcode.com/problems/count-number-of-teams/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N^2) and Space O(1) where N is the size of rating;
     */
    fun numTeams(rating: IntArray): Int {
        var result = 0
        for (mid in 1..<rating.lastIndex) {
            val leftSmaller = (0..<mid).count { rating[it] < rating[mid] }
            val rightLarger = ((mid + 1)..<rating.size).count { rating[it] > rating[mid] }
            result += leftSmaller * rightLarger

            // Compute directly because all ratings are unique
            val leftLarger = mid - leftSmaller
            val rightSmaller = (rating.size - mid - 1) - rightLarger
            result += leftLarger * rightSmaller
        }
        return result
    }
}
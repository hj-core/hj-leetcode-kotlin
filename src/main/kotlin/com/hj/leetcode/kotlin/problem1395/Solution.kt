package com.hj.leetcode.kotlin.problem1395

/**
 * LeetCode page: [1395. Count Number of Teams](https://leetcode.com/problems/count-number-of-teams/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N) where N is the size of rating;
     */
    fun numTeams(rating: IntArray): Int {
        // rightLarger[i]::= the number of ratings to the right are larger than rating[i]
        val rightLarger = IntArray(rating.size) { i ->
            ((i + 1)..<rating.size).count { j ->
                rating[i] < rating[j]
            }
        }
        // rightSmaller[i]::= the number of ratings to the right are smaller than rating[i]
        val rightSmaller = IntArray(rating.size) { i ->
            ((i + 1)..<rating.size).count { j ->
                rating[i] > rating[j]
            }
        }

        var result = 0
        for (i in 0..<(rating.size - 2)) {
            for (j in (i + 1)..<(rating.size - 1)) {
                when {
                    rating[i] < rating[j] -> result += rightLarger[j]
                    rating[i] > rating[j] -> result += rightSmaller[j]
                }
            }
        }
        return result
    }
}
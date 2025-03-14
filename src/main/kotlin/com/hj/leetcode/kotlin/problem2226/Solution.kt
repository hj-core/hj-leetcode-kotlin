package com.hj.leetcode.kotlin.problem2226

/**
 * LeetCode page: [2226. Maximum Candies Allocated to K Children](https://leetcode.com/problems/maximum-candies-allocated-to-k-children/);
 */
class Solution {
    // Complexity:
    // Time O(NLogM) and Space O(1)
    // where M and N are the maximum value and the length of the candies, respectively.
    fun maximumCandies(
        candies: IntArray,
        k: Long,
    ): Int {
        // Binary search on the number of candies per child.
        // The final result is in the range [low-1, high].
        var low = 1
        var high = candies.max()

        while (low <= high) {
            val mid = (low + high) ushr 1
            if (maxChildrenGotCandies(candies, mid) >= k) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }
        return high
    }

    private fun maxChildrenGotCandies(
        candies: IntArray,
        candiesPerChild: Int,
    ): Long {
        require(candiesPerChild != 0)
        return candies.fold(0L) { acc, elem ->
            acc + elem / candiesPerChild
        }
    }
}

package com.hj.leetcode.kotlin.problem135

/**
 * LeetCode page: [135. Candy](https://leetcode.com/problems/candy/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of ratings.
    fun candy(ratings: IntArray): Int {
        var result = 0
        var i = 0
        while (i < ratings.size - 1) {
            if (ratings[i] == ratings[i + 1]) {
                i++
                result++
                continue
            }

            var leftArm = 0
            while (i < ratings.size - 1 && ratings[i] < ratings[i + 1]) {
                i++
                leftArm++
            }

            var rightArm = 0
            while (i < ratings.size - 1 && ratings[i] > ratings[i + 1]) {
                i++
                rightArm++
            }

            result += (leftArm + 1) * leftArm / 2 + (rightArm + 1) * rightArm / 2 + maxOf(leftArm, rightArm)
        }
        result++
        return result
    }
}

package com.hj.leetcode.kotlin.problem769

import kotlin.math.max

/**
 * LeetCode page: [769. Max Chunks To Make Sorted](https://leetcode.com/problems/max-chunks-to-make-sorted/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of arr.
     */
    fun maxChunksToSorted(arr: IntArray): Int =
        arr
            .foldIndexed(intArrayOf(-1, 0)) { index, acc, elem ->
                // acc::= (maxElemUpToIndex, result)
                acc[0] = max(acc[0], elem)
                acc[1] += if (acc[0] == index) 1 else 0
                acc
            }[1]
}

package com.hj.leetcode.kotlin.problem658

/**
 * LeetCode page: [658. Find K Closest Elements](https://leetcode.com/problems/find-k-closest-elements/);
 */
class Solution {
    /* Complexity:
     * Time O(K+Log(N-K)) and Aux_Space O(1) where N is size of arr and K equals k;
     */
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        /* The idea is to find k elements which give the smallest sum of distance; Binary search
         * is performed on the sum of distances of k elements windows;
         */
        var leftBound = 0
        var rightBound = arr.size - k

        while (leftBound < rightBound) {
            val midIndex = (leftBound + rightBound) ushr 1
            val isDecreasingTrend = x - arr[midIndex] > arr[midIndex + k] - x
            if (isDecreasingTrend) leftBound = midIndex + 1 else rightBound = midIndex
        }
        return arr.slice(leftBound until leftBound + k)
    }
}
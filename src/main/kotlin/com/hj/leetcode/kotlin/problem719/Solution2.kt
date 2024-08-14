package com.hj.leetcode.kotlin.problem719

import java.util.*

class Solution2 {
    /* Complexity:
     * Time O(NLogN+kLogN) and Space O(N) where N is the size of nums;
     *
     * WARNING: Time Limit Exceeded.
     */
    fun smallestDistancePair(nums: IntArray, k: Int): Int {
        val sortedNums = nums.sorted()
        val minPqOfPairs = PriorityQueue<Pair<Int, Int>>(compareBy { (i, j) ->
            sortedNums[j] - sortedNums[i]
        })

        for (i in 0..<nums.lastIndex) {
            minPqOfPairs.add(Pair(i, i + 1))
        }
        repeat(k - 1) {
            val (i, j) = checkNotNull(minPqOfPairs.poll())
            if (j < nums.lastIndex) {
                minPqOfPairs.add(Pair(i, j + 1))
            }
        }

        val kthPair = checkNotNull(minPqOfPairs.poll())
        return sortedNums[kthPair.second] - sortedNums[kthPair.first]
    }
}
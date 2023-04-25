package com.hj.leetcode.kotlin.problem2336

/**
 * LeetCode page: [2336. Smallest Number in Infinite Set](https://leetcode.com/problems/smallest-number-in-infinite-set/);
 */
class SmallestInfiniteSet() {

    private var largestPopped = 0
    private var sortedEffectivelyAdded = sortedSetOf<Int>()

    /* Complexity for N calls:
     * Time O(NLogN) and Space O(1);
     */
    fun popSmallest(): Int {
        return if (sortedEffectivelyAdded.isEmpty()) {
            largestPopped++
            largestPopped
        } else {
            checkNotNull(sortedEffectivelyAdded.pollFirst())
        }
    }

    /* Complexity for N calls:
     * Time O(NLogN) and Space O(N);
     */
    fun addBack(num: Int) {
        if (num <= largestPopped) {
            sortedEffectivelyAdded.add(num)
        }
    }
}
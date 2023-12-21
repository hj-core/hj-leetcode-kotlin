package com.hj.leetcode.kotlin.problem2336

/**
 * LeetCode page: [2336. Smallest Number in Infinite Set](https://leetcode.com/problems/smallest-number-in-infinite-set/);
 */
class SmallestInfiniteSet() {

    private var maxEverPopped = 0
    private var sortedAddedBack = sortedSetOf<Int>()

    /* Complexity for N calls:
     * Time O(NLogN) and Space O(1);
     */
    fun popSmallest(): Int {
        return if (sortedAddedBack.isEmpty()) {
            maxEverPopped++
            maxEverPopped
        } else {
            checkNotNull(sortedAddedBack.pollFirst())
        }
    }

    /* Complexity for N calls:
     * Time O(NLogN) and Space O(N);
     */
    fun addBack(num: Int) {
        val isPopped = num <= maxEverPopped && num !in sortedAddedBack
        if (isPopped) {
            sortedAddedBack.add(num)
        }
    }
}
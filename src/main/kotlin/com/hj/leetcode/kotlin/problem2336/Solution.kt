package com.hj.leetcode.kotlin.problem2336

/**
 * LeetCode page: [2336. Smallest Number in Infinite Set](https://leetcode.com/problems/smallest-number-in-infinite-set/);
 */
class SmallestInfiniteSet() {

    private var largestPopped = 0
    private var sortedAddedBack = sortedSetOf<Int>()

    /* Complexity for N calls:
     * Time O(NLogN) and Space O(1);
     */
    fun popSmallest(): Int {
        return if (sortedAddedBack.isEmpty()) {
            largestPopped++
            largestPopped
        } else {
            checkNotNull(sortedAddedBack.pollFirst())
        }
    }

    /* Complexity for N calls:
     * Time O(NLogN) and Space O(N);
     */
    fun addBack(num: Int) {
        val isPopped = num <= largestPopped && num !in sortedAddedBack
        if (isPopped) {
            sortedAddedBack.add(num)
        }
    }
}
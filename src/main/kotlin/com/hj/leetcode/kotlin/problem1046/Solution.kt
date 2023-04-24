package com.hj.leetcode.kotlin.problem1046

import java.util.*

/**
 * LeetCode page: [1046. Last Stone Weight](https://leetcode.com/problems/last-stone-weight/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogN) and Space O(N) where N is the size of stones;
     */
    fun lastStoneWeight(stones: IntArray): Int {
        val stonePq = PriorityQueue<Int>(reverseOrder()).apply {
            for (stone in stones) {
                offer(stone)
            }
        }
        while (stonePq.size >= 2) {
            val stone1 = stonePq.poll()
            val stone2 = stonePq.poll()
            if (stone1 != stone2) {
                stonePq.offer(stone1 - stone2)
            }
        }
        return if(stonePq.isEmpty()) 0 else stonePq.peek()
    }
}
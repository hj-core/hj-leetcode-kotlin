package com.hj.leetcode.kotlin.problem2349

import java.util.SortedSet

/**
 * LeetCode page: [2349. Design a Number Container System](https://leetcode.com/problems/design-a-number-container-system/);
 */
class Solution

class NumberContainers {
    private val container = mutableMapOf<Int, Int>() // entry=(index, number)
    private val indicesByVal = mutableMapOf<Int, SortedSet<Int>>() // entry=(number, sorted indices with number)

    // Complexity for N calls:
    // Time O(NLogN) and Space O(N).
    fun change(
        index: Int,
        number: Int,
    ) {
        container[index]?.let { oldNumber ->
            val indices = checkNotNull(indicesByVal[oldNumber])
            if (indices.size == 1) {
                indicesByVal.remove(oldNumber)
            } else {
                indices.remove(index)
            }
        }

        container[index] = number
        if (number in indicesByVal) {
            indicesByVal[number]!!.add(index)
        } else {
            indicesByVal[number] = sortedSetOf(index)
        }
    }

    // Complexity:
    // Time O(1) and Space O(1).
    fun find(number: Int): Int = indicesByVal[number]?.first() ?: -1
}

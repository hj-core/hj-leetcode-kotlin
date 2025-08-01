package com.hj.leetcode.kotlin.problem146

/**
 * LeetCode page: [146. LRU Cache](https://leetcode.com/problems/lru-cache/);
 */
class Solution2

private class LRUCache(
    val capacity: Int,
) : LinkedHashMap<Int, Int>(capacity, 0.75f, true) {
    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean = super.size > capacity

    override fun get(key: Int): Int? = super.get(key) ?: -1
}

package com.hj.leetcode.kotlin.problem706

import java.util.*

/**
 * LeetCode page: [706. Design HashMap](https://leetcode.com/problems/design-hashmap/);
 */
class MyHashMap() {

    private var buckets = Array(8) { LinkedList<Pair<Int, Int>>() }
    private var totalEntries = 0

    fun get(key: Int): Int {
        return buckets[hash(key)]
            .firstOrNull { it.first == key }
            ?.second
            ?: -1
    }

    private fun hash(key: Int): Int {
        return key % buckets.size
    }

    fun put(key: Int, value: Int) {
        remove(key)
        buckets[hash(key)].add(Pair(key, value))
        totalEntries++
        adaptBucketsSize()
    }

    fun remove(key: Int) {
        buckets[hash(key)]
            .removeAll { it.first == key }
            .let { if (it) totalEntries-- }
        adaptBucketsSize()
    }

    private fun adaptBucketsSize() {
        when {
            totalEntries < buckets.size / 4 -> shrinkBuckets()
            totalEntries > buckets.size * 3 / 4 -> expandBuckets()
        }
    }

    private fun shrinkBuckets() {
        val oldBuckets = buckets
        buckets = Array(buckets.size / 2) { LinkedList<Pair<Int, Int>>() }
        copyEntriesFrom(oldBuckets)
    }

    private fun copyEntriesFrom(oldBuckets: Array<LinkedList<Pair<Int, Int>>>) {
        for (bucket in oldBuckets) {
            for (entry in bucket) {
                buckets[hash(entry.first)].add(entry)
            }
        }
    }

    private fun expandBuckets() {
        val oldBuckets = buckets
        buckets = Array(buckets.size * 2) { LinkedList<Pair<Int, Int>>() }
        copyEntriesFrom(oldBuckets)
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * var obj = MyHashMap()
 * obj.put(key,value)
 * var param_2 = obj.get(key)
 * obj.remove(key)
 */
package com.hj.leetcode.kotlin.problem706

import java.util.*

/**
 * LeetCode page: [706. Design HashMap](https://leetcode.com/problems/design-hashmap/);
 */
class MyHashMap() {

    private var array = Array(8) { LinkedList<Pair<Int, Int>>() }
    private var totalEntries = 0

    fun get(key: Int): Int {
        return array[hash(key)]
            .firstOrNull { it.first == key }
            ?.second
            ?: -1
    }

    private fun hash(key: Int): Int {
        return key % array.size
    }

    fun put(key: Int, value: Int) {
        remove(key)
        array[hash(key)].add(Pair(key, value))
        totalEntries++
        adaptArraySize()
    }

    fun remove(key: Int) {
        array[hash(key)]
            .removeAll { it.first == key }
            .let { if (it) totalEntries-- }
        adaptArraySize()
    }

    private fun adaptArraySize() {
        when {
            totalEntries < array.size / 4 -> shrinkArray()
            totalEntries > array.size * 3 / 4 -> expandArray()
        }
    }

    private fun shrinkArray() {
        val oldArray = array
        array = Array(array.size / 2) { LinkedList<Pair<Int, Int>>() }
        copyEntriesFrom(oldArray)
    }

    private fun copyEntriesFrom(oldArray: Array<LinkedList<Pair<Int, Int>>>) {
        for (entries in oldArray) {
            for (entry in entries) {
                array[hash(entry.first)].add(entry)
            }
        }
    }

    private fun expandArray() {
        val oldArray = array
        array = Array(array.size * 2) { LinkedList<Pair<Int, Int>>() }
        copyEntriesFrom(oldArray)
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * var obj = MyHashMap()
 * obj.put(key,value)
 * var param_2 = obj.get(key)
 * obj.remove(key)
 */
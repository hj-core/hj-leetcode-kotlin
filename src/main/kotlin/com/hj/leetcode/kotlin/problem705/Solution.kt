package com.hj.leetcode.kotlin.problem705

/**
 * LeetCode page: [705. Design HashSet](https://leetcode.com/problems/design-hashset/);
 */
class MyHashSet() {

    private val initialTableSize = 16
    private var table = Array(initialTableSize) { mutableListOf<Int>() }
    private var numElements = 0

    fun add(key: Int) {
        val slot = hashSlot(key)
        if (slot.contains(key)) {
            return
        }

        slot.add(key)
        numElements++
        migrateToLargerTableAsNeeded()
    }

    private fun hashSlot(key: Int): MutableList<Int> {
        return table[hashCode(key)]
    }

    private fun hashCode(key: Int): Int {
        return Math.floorMod(key, table.size)
    }

    private fun migrateToLargerTableAsNeeded() {
        if (shouldMigrateToLargerTable()) {
            migrateToLargerTable()
        }
    }

    private fun shouldMigrateToLargerTable(): Boolean {
        return numElements / table.size > 0.75
    }

    private fun migrateToLargerTable() {
        val oldTable = table
        table = Array(oldTable.size * 2) { mutableListOf<Int>() }

        for (slot in oldTable) {
            for (element in slot) {
                hashSlot(element).add(element)
            }
        }
    }

    fun remove(key: Int) {
        val slot = hashSlot(key)
        if (slot.remove(key)) {
            numElements--
        }
    }

    fun contains(key: Int): Boolean {
        return key in hashSlot(key)
    }
}
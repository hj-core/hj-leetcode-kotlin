package com.hj.leetcode.kotlin.problem460

/**
 * LeetCode page: [460. LFU Cache](https://leetcode.com/problems/lfu-cache/);
 */
class LFUCache(private val capacity: Int) {

    private val values = hashMapOf<Int, Int>() // entry(key, value)
    private val useCounts = hashMapOf<Int, Int>() // entry(key, useCount)
    private val keyGroupsByUseCount = hashMapOf<Int, LinkedHashSet<Int>>() // entry(useCount, keyGroup)
    private var minCount = 0

    /* Complexity for N calls:
     * Time O(N) and Space O(1);
     */
    fun get(key: Int): Int {
        return values[key]
            ?.also { increaseUseCount(key) }
            ?: -1
    }

    private fun increaseUseCount(key: Int) {
        require(values.containsKey(key))

        val oldCount = checkNotNull(useCounts[key])
        val newCount = oldCount + 1
        useCounts[key] = newCount

        val oldGroup = checkNotNull(keyGroupsByUseCount[oldCount])
        oldGroup.remove(key)

        if (oldGroup.isEmpty()) {
            keyGroupsByUseCount.remove(oldCount)
            if (oldCount == minCount) minCount++
        }

        val newGroup = keyGroupsByUseCount.computeIfAbsent(newCount) { LinkedHashSet() }
        newGroup.add(key)
    }

    /* Complexity for N calls:
     * Time O(N) and Space O(N);
     */
    fun put(key: Int, value: Int) {
        if (values.containsKey(key)) {
            update(key, value)
        } else {
            putNew(key, value)
        }
    }

    private fun update(key: Int, newValue: Int) {
        require(values.containsKey(key))

        values[key] = newValue
        increaseUseCount(key)
    }

    private fun putNew(key: Int, value: Int) {
        require(!values.containsKey(key))

        ensureCapacity(key)
        values[key] = value
        useCounts[key] = 1
        keyGroupsByUseCount
            .computeIfAbsent(1) { LinkedHashSet() }
            .add(key)
        minCount = 1
    }

    private fun ensureCapacity(key: Int) {
        val hasSpace = values.containsKey(key) || values.size < capacity
        if (hasSpace) return

        val minCountKeyGroup = checkNotNull(keyGroupsByUseCount[minCount])
        val keyToPop = minCountKeyGroup.first()

        values.remove(keyToPop)
        useCounts.remove(keyToPop)
        minCountKeyGroup.remove(keyToPop)

        if (minCountKeyGroup.isEmpty()) {
            keyGroupsByUseCount.remove(minCount)
        }
    }
}
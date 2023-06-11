package com.hj.leetcode.kotlin.problem1146

/**
 * LeetCode page: [1146. Snapshot Array](https://leetcode.com/problems/snapshot-array/);
 */
class SnapshotArray(length: Int) {

    private var latestSnapId = -1
    private val snapshots = Array(length) { mutableListOf(Record(latestSnapId, 0)) }
    private val pendingChanges = hashMapOf<Int, Int>() // entry = (index, value)

    private data class Record(val snapId: Int, val value: Int)

    /* Complexity of N calls:
     * Time O(N) and Space O(N);
     */
    fun set(index: Int, `val`: Int) {
        pendingChanges[index] = `val`
    }

    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of currentArray;
     */
    fun snap(): Int {
        latestSnapId++
        for ((index, value) in pendingChanges) {
            val hasValueChanged = value != snapshots[index].last().value
            if (hasValueChanged) {
                snapshots[index].add(Record(latestSnapId, value))
            }
        }
        pendingChanges.clear()
        return latestSnapId
    }

    /* Complexity:
     * Time O(Log(snap_id)) and Space O(1);
     */
    fun get(index: Int, snap_id: Int): Int {
        require(snap_id in 0..latestSnapId)
        return equivalentRecord(index, snap_id).value
    }

    private fun equivalentRecord(index: Int, snapId: Int): Record {
        check(snapId >= snapshots[index][0].snapId)

        if (snapId >= snapshots[index].last().snapId) {
            return snapshots[index].last()
        }

        // Binary search on the index of record in snapshots[index]
        var lowerBound = 0
        var upperBound = snapshots[index].lastIndex
        while (lowerBound <= upperBound) {
            val guessIndex = lowerBound + (upperBound - lowerBound) / 2
            val guessSnapId = snapshots[index][guessIndex].snapId
            when {
                guessSnapId < snapId -> lowerBound = guessIndex + 1
                guessSnapId > snapId -> upperBound = guessIndex - 1
                else -> return snapshots[index][guessIndex]
            }
        }
        return snapshots[index][upperBound]
    }
}
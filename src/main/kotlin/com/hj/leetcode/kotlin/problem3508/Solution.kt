package com.hj.leetcode.kotlin.problem3508

class Solution

/**
 * LeetCode page: [3508. Implement Router](https://leetcode.com/problems/implement-router/);
 */
class Router(
    private val memoryLimit: Int,
) {
    // [dst of pending packet, by arrival order]
    private val dsts = ArrayDeque<Int>()

    // dst to [hash(src, t) of pending dst packet, by arrival order]
    private val queues = hashMapOf<Int, ArrayDeque<Long>>()

    // Complexity for N calls:
    // Time (NM) and Space O(N) where M is the number of
    // pending packets that have the destination and timestamp.
    fun addPacket(
        source: Int,
        destination: Int,
        timestamp: Int,
    ): Boolean {
        if (contains(source, destination, timestamp)) {
            return false
        }

        if (dsts.size == memoryLimit) {
            dropOldestPacket()
        }

        dsts.addLast(destination)
        queues
            .computeIfAbsent(destination) { ArrayDeque() }
            .addLast(calcHash(source, timestamp))
        return true
    }

    private fun contains(
        source: Int,
        destination: Int,
        timestamp: Int,
    ): Boolean {
        val queue = queues[destination] ?: return false
        for (i in queue.indices.reversed()) {
            val (src, t) = invertHash(queue[i])
            if (t < timestamp) return false
            // We don't strictly need the t == timestamp check, as we
            // only query the most recent packet; however, the method
            // cannot be named contains.
            if (src == source && t == timestamp) {
                return true
            }
        }
        return false
    }

    private fun calcHash(
        source: Int,
        timestamp: Int,
    ): Long = (timestamp.toLong() shl 20) + source

    private fun invertHash(hash: Long): Pair<Int, Int> {
        val src = (hash and 0xf_ffff).toInt()
        val t = (hash shr 20).toInt()
        return src to t
    }

    private fun dropOldestPacket() {
        if (dsts.isEmpty()) {
            return
        }
        val dst = dsts.removeFirst()
        checkNotNull(queues[dst]).removeFirst()
    }

    // Complexity:
    // Time O(1) and Space O(1).
    fun forwardPacket(): IntArray {
        if (dsts.isEmpty()) {
            return intArrayOf()
        }
        val dst = dsts.removeFirst()
        val (src, t) = invertHash(checkNotNull(queues[dst]).removeFirst())
        return intArrayOf(src, dst, t)
    }

    // Complexity:
    // Time O(Log N) and Space O(1) where n is the number
    // of pending packets to the destination.
    fun getCount(
        destination: Int,
        startTime: Int,
        endTime: Int,
    ): Int {
        val queue = queues[destination] ?: return 0
        // Require src < (2^20)-1
        val startHash = calcHash(0, startTime) - 1
        val endHash = calcHash(0, endTime + 1) - 1
        return queue.binarySearch(startHash) - queue.binarySearch(endHash)
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * var obj = Router(memoryLimit)
 * var param_1 = obj.addPacket(source,destination,timestamp)
 * var param_2 = obj.forwardPacket()
 * var param_3 = obj.getCount(destination,startTime,endTime)
 */

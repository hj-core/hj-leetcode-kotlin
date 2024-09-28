package com.hj.leetcode.kotlin.problem731

/**
 * LeetCode page: [731. My Calendar II](https://leetcode.com/problems/my-calendar-ii/);
 */
class MyCalendarTwo {
    private class Slot(
        val start: Int,
        val end: Int,
        var next: Slot?,
    )

    private val headSlot = Slot(0, 0, null) // Sorted by start

    /* Complexity for N calls:
     * Time O(N^2) and Space O(N).
     */
    fun book(
        start: Int,
        end: Int,
    ): Boolean {
        val new = Slot(start, end, null)
        val pendingEnds = mutableListOf<Int>()

        // Find the position to insert the new slot
        var insertionPoint = headSlot
        while (true) {
            pendingEnds.removeIf { it <= insertionPoint.start }
            pendingEnds.add(insertionPoint.end)

            val next = insertionPoint.next ?: break
            if (new.start < next.start) {
                break
            }
            insertionPoint = next
        }

        // Check is there any triple booking if we do the insertion
        new.next = insertionPoint.next
        var currentSlot: Slot? = new
        while (currentSlot != null && currentSlot.start < new.end) {
            pendingEnds.removeIf { it <= checkNotNull(currentSlot).start }
            pendingEnds.add(currentSlot.end)

            if (2 < pendingEnds.size) {
                return false
            }
            currentSlot = currentSlot.next
        }

        insertionPoint.next = new
        return true
    }
}

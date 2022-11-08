package com.hj.leetcode.kotlin.problem76

/**
 * LeetCode page: [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/);
 */
class Solution {
    /* Complexity:
     * Time O(|s|+|t|) and Aux_Space O(1);
     */
    fun minWindow(s: String, t: String): String {
        if (s.length < t.length) return ""

        val minWindow = findMinWindow(s, t)
        val noValidWindow = minWindow.end == s.length

        return if (noValidWindow) "" else s.slice(minWindow.start..minWindow.end)
    }

    private fun findMinWindow(s: String, t: String): Window {
        val tCounts = AlphabetCounter(t)
        val (window, counts) = findFirstWindowAndItsCounts(s, t.length, tCounts)

        val noValidWindow = window.end == s.length
        if (noValidWindow) return window

        val minWindow = Window(0, s.length)

        while (window.end < s.length) {
            shrinkWindowStartAndUpdateCount(s, window, counts, tCounts)
            updateMinWindow(minWindow, window)
            expandWindowEndAndUpdateCount(s, window, counts)
        }

        return minWindow
    }

    private class AlphabetCounter(initial: String = "") {
        private val countPerLowercase = IntArray(26)
        private val countPerUppercase = IntArray(26)
        init {
            for (alphabet in initial) {
                updateCount(alphabet, 1)
            }
        }

        fun updateCount(alphabet: Char, increment: Int): Int {
            val (container, index) = matchContainerAndIndex(alphabet)

            val newCount = container[index] + increment
            if (newCount < 0) throw IllegalStateException()

            container[index] = newCount
            return newCount
        }

        private fun matchContainerAndIndex(alphabet: Char): Pair<IntArray, Int> {
            return when (alphabet) {
                in 'a'..'z' -> Pair(countPerLowercase, alphabet - 'a')
                in 'A'..'Z' -> Pair(countPerUppercase, alphabet - 'A')
                else -> throw IllegalArgumentException()
            }
        }

        fun readCount(alphabet: Char): Int {
            val (container, index) = matchContainerAndIndex(alphabet)
            return container[index]
        }
    }

    private fun findFirstWindowAndItsCounts(
        s: String,
        tLength: Int, tCounts: AlphabetCounter
    ): Pair<Window, AlphabetCounter> {
        val window = Window(0, -1)
        val counts = AlphabetCounter()
        var includedLength = 0

        while (includedLength < tLength && window.end < s.lastIndex) {
            window.end++
            val alphabet = s[window.end]
            val newCount = counts.updateCount(alphabet, 1)
            val tCount = tCounts.readCount(alphabet)
            if (newCount <= tCount) includedLength++
        }

        if (includedLength < tLength) window.end = s.length
        return Pair(window, counts)
    }

    private class Window(var start: Int, var end: Int) {
        val size get() = end - start + 1
    }

    private fun shrinkWindowStartAndUpdateCount(
        s: String,
        window: Window, counts: AlphabetCounter,
        tCounts: AlphabetCounter
    ) {
        fun canShrinkStart() = counts.readCount(s[window.start]) > tCounts.readCount(s[window.start])

        while (canShrinkStart()) {
            counts.updateCount(s[window.start], -1)
            window.start++
        }
    }

    private fun updateMinWindow(minWindow: Window, currWindow: Window) {
        if (currWindow.size < minWindow.size) {
            minWindow.start = currWindow.start
            minWindow.end = currWindow.end
        }
    }

    private fun expandWindowEndAndUpdateCount(
        s: String,
        window: Window, counts: AlphabetCounter
    ) {
        val cannotExpand = window.end == s.lastIndex
        if (cannotExpand) {
            window.end = s.length
            return
        }

        do {
            window.end++
            counts.updateCount(s[window.end], 1)

        } while (window.end < s.lastIndex && s[window.end] != s[window.start])

        val noValidExpand = s[window.end] != s[window.start]
        if (noValidExpand) window.end = s.length
    }
}
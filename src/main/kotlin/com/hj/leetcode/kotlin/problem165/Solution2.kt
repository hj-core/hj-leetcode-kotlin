package com.hj.leetcode.kotlin.problem165

/**
 * LeetCode page: [165. Compare Version Numbers](https://leetcode.com/problems/compare-version-numbers/);
 */
class Solution2 {
    // Complexity:
    // Time O(N+M) and Space O(1) where N and M are the length of
    // version1 and version2, respectively.
    fun compareVersion(
        version1: String,
        version2: String,
    ): Int {
        val iter1 = newRevIterator(version1)
        val iter2 = newRevIterator(version2)

        while (iter1.hasNext() || iter2.hasNext()) {
            val rev1 = if (iter1.hasNext()) iter1.next() else 0
            val rev2 = if (iter2.hasNext()) iter2.next() else 0
            when {
                rev1 < rev2 -> return -1
                rev1 > rev2 -> return 1
            }
        }
        return 0
    }

    private fun newRevIterator(version: String): Iterator<Int> =
        object : Iterator<Int> {
            private var end = -1

            override fun hasNext(): Boolean = end < version.length

            override fun next(): Int {
                end++
                var rev = 0
                while (end < version.length && version[end] != '.') {
                    rev = rev * 10 + (version[end] - '0')
                    end++
                }
                return rev
            }
        }
}

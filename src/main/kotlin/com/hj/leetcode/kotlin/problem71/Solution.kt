package com.hj.leetcode.kotlin.problem71

/**
 * LeetCode page: [71. Simplify Path](https://leetcode.com/problems/simplify-path/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of path;
     */
    fun simplifyPath(path: String): String {
        val segments = segments(path)
        val simplifiedSegments = simplifiedSegments(segments)
        return simplifiedPath(simplifiedSegments)
    }

    private fun segments(path: String): List<String> {
        return path.split('/')
    }

    private fun simplifiedSegments(segments: List<String>): List<String> {
        val simplifiedSegments = mutableListOf<String>()
        for (segment in segments) {
            when (segment) {
                "", "." -> { /* no-op, the segment is excluded */ }
                ".." -> simplifiedSegments.removeLastIfExists()
                else -> simplifiedSegments.add(segment)
            }
        }
        return simplifiedSegments
    }

    private fun <T> MutableList<T>.removeLastIfExists() {
        if (isNotEmpty()) removeAt(lastIndex)
    }

    private fun simplifiedPath(simplifiedSegments: List<String>): String {
        if (simplifiedSegments.isEmpty()) return "/"

        val simplifiedPath = StringBuilder()
        for (segment in simplifiedSegments) {
            simplifiedPath
                .append('/')
                .append(segment)
        }
        return simplifiedPath.toString()
    }
}
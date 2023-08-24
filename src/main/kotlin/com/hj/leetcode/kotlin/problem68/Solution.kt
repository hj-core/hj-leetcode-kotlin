package com.hj.leetcode.kotlin.problem68

/**
 * LeetCode page: [68. Text Justification](https://leetcode.com/problems/text-justification/);
 */
class Solution {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        return fullJustify(words, maxWidth, 0, mutableListOf())
    }

    private fun fullJustify(
        words: Array<String>,
        maxWidth: Int,
        start: Int,
        result: MutableList<String>
    ): List<String> {
        if (start >= words.size) {
            return result
        }

        val lineInfo = lineInfo(words, start, maxWidth)
        result.add(line(words, maxWidth, lineInfo))
        fullJustify(words, maxWidth, lineInfo.end + 1, result)
        return result
    }

    private fun line(words: Array<String>, maxWidth: Int, lineInfo: LineInfo): String {
        val result = StringBuilder()
        val (start, end, numChars) = lineInfo
        if (start == end || end == words.lastIndex) {
            for (index in start..end) {
                result.append(words[index])
                result.append(' ')
            }
            result.apply { deleteCharAt(lastIndex) }
            while (result.length < maxWidth) {
                result.append(' ')
            }
        } else {
            val numWordsInLine = end - start + 1
            val separation = " ".repeat((maxWidth - numChars) / (numWordsInLine - 1))
            var pendingSpace = maxWidth - numChars - separation.length * (numWordsInLine - 1)
            for (index in start..end) {
                result.append(words[index])
                result.append(separation)
                if (pendingSpace > 0) {
                    result.append(' ')
                    pendingSpace--
                }
            }
            result.apply { delete(length - separation.length, length) }
        }
        return result.toString()
    }

    private fun lineInfo(words: Array<String>, start: Int, maxWidth: Int): LineInfo {
        var numChars = 0
        var minWidth = -1
        var breakIndex = start

        while (breakIndex < words.size && minWidth + 1 + words[breakIndex].length <= maxWidth) {
            numChars += words[breakIndex].length
            minWidth += 1 + words[breakIndex].length
            breakIndex++
        }
        return LineInfo(start, breakIndex - 1, numChars)
    }

    private data class LineInfo(val start: Int, val end: Int, val numChars: Int)
}
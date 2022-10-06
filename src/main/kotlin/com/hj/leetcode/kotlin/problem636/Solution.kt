package com.hj.leetcode.kotlin.problem636

/**
 * LeetCode page: [636. Exclusive Time of Functions](https://leetcode.com/problems/exclusive-time-of-functions/);
 */
class Solution {
    /* Complexity:
     * Time O(M+L) and Space O(M+N+K) where
     *    M is the size of logs,
     *    L is the flat length of logs,
     *    N equals n,
     *    K is the max length of log in logs;
     */
    fun exclusiveTime(n: Int, logs: List<String>): IntArray {
        val functionCalls = FunctionCalls(IntArray(n), ArrayDeque(), 0)

        for (logStr in logs) {
            val log = logOf(logStr)
            functionCalls.update(log)
        }
        return functionCalls.exclusiveTimePerId
    }

    private class FunctionCalls(
        val exclusiveTimePerId: IntArray,
        val uncompletedIdStack: ArrayDeque<Int>,
        var lastExecutionStart: Int
    )

    private fun logOf(logStr: String): Log {
        val (functionIdStr, typeStr, timestampStr) = logStr.split(":")

        val functionId = functionIdStr.toInt()
        val type = when (typeStr) {
            "start" -> LogType.Start
            "end" -> LogType.End
            else -> throw IllegalArgumentException()
        }
        val timestamp = timestampStr.toInt()

        return Log(functionId, type, timestamp)
    }

    private data class Log(val functionId: Int, val type: LogType, val timestamp: Int)

    private enum class LogType { Start, End }

    private fun FunctionCalls.update(log: Log) {
        when (log.type) {
            LogType.Start -> updateForStartLog(log)
            LogType.End -> updateForEndLog(log)
        }
    }

    private fun FunctionCalls.updateForStartLog(log: Log) {
        require(log.type == LogType.Start)

        if (uncompletedIdStack.isEmpty()) {
            uncompletedIdStack.addFirst(log.functionId)
            lastExecutionStart = log.timestamp
        } else {
            val executedId = uncompletedIdStack.first()
            val duration = log.timestamp - lastExecutionStart
            exclusiveTimePerId[executedId] += duration

            uncompletedIdStack.addFirst(log.functionId)
            lastExecutionStart = log.timestamp
        }
    }

    private fun FunctionCalls.updateForEndLog(log: Log) {
        require(log.type == LogType.End)

        val executedId = uncompletedIdStack.first()
        check(executedId == log.functionId)

        val duration = log.timestamp - lastExecutionStart + 1
        exclusiveTimePerId[executedId] += duration

        uncompletedIdStack.removeFirst()
        lastExecutionStart = log.timestamp + 1
    }
}
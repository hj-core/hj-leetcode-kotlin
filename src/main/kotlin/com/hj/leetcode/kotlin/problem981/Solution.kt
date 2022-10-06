package com.hj.leetcode.kotlin.problem981

/**
 * LeetCode page: [981. Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store/);
 */
class TimeMap() {

    private val timeValuesPerKey = hashMapOf<String, MutableList<TimeValue>>()

    private data class TimeValue(val timestamp: Int, val value: String)

    /* Complexity:
     * Time O(K+V) and Space O(K+V) where K and V are the length of key and value;
     */
    fun set(key: String, value: String, timestamp: Int) {
        val newTimeValue = TimeValue(timestamp, value)
        val timeValues = timeValuesPerKey.getOrPut(key) { mutableListOf() }

        // This is the constraint from problem, without which we might go for a TreeMap solution;
        val isTimestampStrictlyIncreasing =
            timeValues.isEmpty() || timestamp > timeValues.last().timestamp
        require(isTimestampStrictlyIncreasing)

        timeValues.add(newTimeValue)
    }

    /* Complexity:
     * Time O(K+LogN) and Space O(1) where K is the length of key and N is the number of timeValues with key;
     */
    fun get(key: String, timestamp: Int): String {
        val timeValuesSortedByTime = timeValuesPerKey[key] ?: return ""
        if (timeValuesSortedByTime.isEmpty()) return ""

        val insertionIndex = timeValuesSortedByTime
            .binarySearch { timeValue -> timeValue.timestamp - timestamp }
            .let { bsIndex -> if (bsIndex < 0) -(bsIndex + 1) else bsIndex }

        val isFutureTimestamp = insertionIndex == timeValuesSortedByTime.size
        if (isFutureTimestamp) return timeValuesSortedByTime.last().value

        val hasMatchedTimestamp = timeValuesSortedByTime[insertionIndex].timestamp == timestamp
        if (hasMatchedTimestamp) return timeValuesSortedByTime[insertionIndex].value

        val noEarlierTimestamp = insertionIndex == 0
        if (noEarlierTimestamp) return ""

        return timeValuesSortedByTime[insertionIndex - 1].value
    }
}
package com.hj.leetcode.kotlin.problem692

import java.util.*

/**
 * LeetCode page: [692. Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/);
 */
class Solution {
    /* Complexity:
     * Time O(NLogK) and Space O(N) where N is the size of words and K equals k;
     */
    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val freqPerWord = getFreqPerWord(words)
        val topKPq = getTopKPq(freqPerWord, k)
        return getSortedTopKFrequentWords(topKPq)
    }

    private fun getFreqPerWord(words: Array<String>) = words.groupingBy { it }.eachCount()

    private fun getTopKPq(freqPerWord: Map<String, Int>, k: Int): PriorityQueue<Map.Entry<String, Int>> {
        /* The comparator assumes that at the same frequency, the one with greater lexicographical order
         * will be discarded first;
         */
        val comparator = compareBy<Map.Entry<String, Int>> { it.value }.thenByDescending { it.key }
        val topKPq = PriorityQueue(comparator)

        for (entry in freqPerWord) {
            topKPq.offer(entry)
            if (topKPq.size > k) topKPq.poll()
        }

        return topKPq
    }

    private fun getSortedTopKFrequentWords(topKPq: PriorityQueue<Map.Entry<String, Int>>): List<String> {
        val topK = MutableList(topKPq.size) { topKPq.poll().key }
        topK.reverse()
        return topK
    }
}
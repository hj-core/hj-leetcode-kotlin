package com.hj.leetcode.kotlin.common.util

fun <T, S> validate(
    testcasesAndExpected: List<Pair<T, S>>,
    toActual: (testcase: T) -> S,
    showTestcases: Boolean = true,
) {
    if (showTestcases) {
        val testcases = testcasesAndExpected.joinToString(
            prefix = "[",
            postfix = "]",
            limit = 16,
            transform = { (testcase, _) -> testcase.toString() }
        )
        println("# TESTCASE : $testcases")
    }

    val numTestcases = testcasesAndExpected.size
    var numPassed = numTestcases

    for (index in testcasesAndExpected.indices) {
        val testcase = testcasesAndExpected[index].first
        val expected = testcasesAndExpected[index].second
        val actual = toActual(testcase)

        if (actual != expected) {
            numPassed--
            println("- failed at index [$index]: expect '$expected' but '$actual';")
        }
    }

    val numFailed = numTestcases - numPassed
    println("# SUMMARY  : $numPassed of $numTestcases passed AND $numFailed of $numTestcases failed")
}
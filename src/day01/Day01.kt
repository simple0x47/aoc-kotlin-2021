package day01

import readInputForDay

fun run() {
    fun countLargerThanPrevious(input: List<String>): Int {
        var previousMeasurement: Int = Int.MAX_VALUE
        var measurement: Int = 0
        var count: Int = 0

        for (rawMeasurement in input) {
            measurement = rawMeasurement.toInt()

            if (measurement > previousMeasurement) {
                count++
            }

            previousMeasurement = measurement
        }

        return count
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val testInput = readInputForDay(1, true)
    check(countLargerThanPrevious(testInput) == 7)

    val input = readInputForDay(1)
    println(countLargerThanPrevious(input))
    println(part2(input))
}

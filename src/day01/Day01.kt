package day01

import readInputForDay

fun run() {
    fun countLargerThanPrevious(input: List<Int>): Int {
        var previousMeasurement: Int = Int.MAX_VALUE
        var count: Int = 0

        for (measurement in input) {
            if (measurement > previousMeasurement) {
                count++
            }

            previousMeasurement = measurement
        }

        return count
    }

    // First part.
    fun countLargerThanPrevious(input: List<String>): Int {
        val intInput = ArrayList<Int>()

        for (rawInput in input) {
            intInput.add(rawInput.toInt())
        }

        return countLargerThanPrevious(intInput)
    }

    // Second part.
    fun countLargerCompoundOfMeasurements(input: List<String>, compoundBy: Int): Int {
        val compoundsCount = input.size - compoundBy + 1

        val compounds = ArrayList<Int>()
        var compoundAmount = 0
        for (compound in 0 until compoundsCount) {
            for (i in 0 until compoundBy) {
                compoundAmount += input[compound + i].toInt()
            }

            compounds.add(compoundAmount)
            compoundAmount = 0
        }

        return countLargerThanPrevious(compounds)
    }

    val testInput = readInputForDay(1, true)
    check(countLargerThanPrevious(testInput) == 7)
    check(countLargerCompoundOfMeasurements(testInput, 3) == 5)

    val input = readInputForDay(1)
    println(countLargerThanPrevious(input))
    println(countLargerCompoundOfMeasurements(input, 3))
}

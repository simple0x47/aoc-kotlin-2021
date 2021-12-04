package day03

import readInputForDay
import kotlin.math.min

fun run() {
    fun mostCommonBitwiseValue(input: List<String>, bits: Int = 5) : Int {
        var mostCommonBits: Int = 0

        var bitsCount: IntArray = IntArray(bits)
        for (value in input) {
            for (bit in 0 until bits) {
                if (value[bit].digitToInt() == 1) {
                    bitsCount[bit] += 1
                }
            }
        }

        for (bit in 0 until bits) {
            mostCommonBits = mostCommonBits shl min(bit, 1)

            if (bitsCount[bit] >= (input.size + 1) / 2) {
                mostCommonBits += 0b1
            }
        }

        return mostCommonBits
    }

    fun leastCommonBitwiseValue(commonBits: Int, bits: Int = 5) : Int {
        var usableBits: Int = 0b0

        for (bit in 0 until bits) {
            usableBits = usableBits shl min(bit, 1)

            usableBits += 0b1
        }

        return commonBits.inv() and usableBits
    }

    fun powerConsumption(input: List<String>, bits: Int = 5) : Int {
        val mostCommon: Int = mostCommonBitwiseValue(input, bits)
        val leastCommon: Int = leastCommonBitwiseValue(mostCommon, bits)

        return mostCommon * leastCommon
    }

    val testInput = readInputForDay(3, true)
    check(powerConsumption(testInput) == 198)

    val input = readInputForDay(3)
    println(powerConsumption(input, 12))
}
package day03

import kotlin.math.min

class DiagnosticDecoder(private var diagnosticInput: List<String>, private var diagnosticInputBits: Int) {
    fun updateInput(diagnosticInput: List<String>, diagnosticInputBits: Int = 5) {
        this.diagnosticInput = diagnosticInput
        this.diagnosticInputBits = diagnosticInputBits
    }

    fun powerConsumption() : Int {
        val mostCommon: Int = mostCommonBitwiseValue()
        val leastCommon: Int = leastCommonBitwiseValue(mostCommon)

        return mostCommon * leastCommon
    }

    private fun mostCommonBitwiseValue() : Int {
        var mostCommonBits: Int = 0

        for (bit in 0 until diagnosticInputBits) {
            mostCommonBits = mostCommonBits shl min(bit, 1)

            // If the most common bit is 0, it just gets ignored.
            mostCommonBits += mostCommonBit(diagnosticInput, bit)
        }

        return mostCommonBits
    }

    private fun mostCommonBit(list: List<String>, bit: Int, zeroIfEqual: Boolean = false) : Int {
        var count: Int = 0
        for (value in list) {
            if (value[bit].digitToInt() == 1) {
                count++
            }
        }

        if (count == ((list.size + 1) / 2)) {
            return if (zeroIfEqual)
                0b0
            else
                0b1
        }

        return if (count > (list.size / 2))
            0b1
        else
            0b0
    }

    private fun leastCommonBitwiseValue(commonBits: Int) : Int {
        var usableBits: Int = 0b0

        for (bit in 0 until diagnosticInputBits) {
            usableBits = usableBits shl min(bit, 1)

            usableBits += 0b1
        }

        return commonBits.inv() and usableBits
    }

    fun oxygenGeneratorRating() : Int {
        return diagnosticCodeByBitExclusion(excludeLeastCommon = true, zeroGreaterPriorityThanOne = false)
    }

    private fun diagnosticCodeByBitExclusion(excludeLeastCommon: Boolean, zeroGreaterPriorityThanOne: Boolean) : Int {
        var diagnosticInputClone: ArrayList<String> = ArrayList(diagnosticInput)
        var excludedIndexList: List<Int>

        for (bit in 0 until diagnosticInputBits) {
            excludedIndexList = excludeByBit(diagnosticInputClone, bit, excludeLeastCommon, zeroGreaterPriorityThanOne)

            for (index in excludedIndexList.reversed()) {
                diagnosticInputClone.removeAt(index)
            }

            if (diagnosticInputClone.size == 1) {
                break
            }
        }

        return diagnosticInputClone[0].toInt(2)
    }

    private fun excludeByBit(list: List<String>, bit: Int, excludeLeastCommon: Boolean,
                             zeroGreaterPriorityThanOne: Boolean) : List<Int> {
        var excludedIndexList: ArrayList<Int> = ArrayList()
        var excludeByBit: Int = mostCommonBit(list, bit, zeroGreaterPriorityThanOne)

        // Invert the bit if the least common bit is excluded.
        if (excludeLeastCommon) {
            excludeByBit = excludeByBit.inv() and 0b1
        }

        for (i in list.indices) {
            val item: String = list[i]

            if (item[bit].digitToInt() == excludeByBit) {
                excludedIndexList.add(i)
            }
        }

        return excludedIndexList
    }

    fun CO2ScrubberRating() : Int {
        return diagnosticCodeByBitExclusion(excludeLeastCommon = false, zeroGreaterPriorityThanOne = false)
    }

    fun lifeSupportRating() : Int {
        return oxygenGeneratorRating() * CO2ScrubberRating()
    }
}
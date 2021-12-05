package day03

import kotlin.math.min

class DiagnosticDecoder(private var diagnosticInput: List<String>, private var diagnosticInputBits: Int = 5) {
    fun updateInput(diagnosticInput: List<String>, diagnosticInputBits: Int = 5) {
        this.diagnosticInput = diagnosticInput
        this.diagnosticInputBits = diagnosticInputBits
    }

    private fun mostCommonBitwiseValue() : Int {
        var mostCommonBits: Int = 0

        var bitsCount: IntArray = IntArray(diagnosticInputBits)
        for (value in diagnosticInput) {
            for (bit in 0 until diagnosticInputBits) {
                if (value[bit].digitToInt() == 1) {
                    bitsCount[bit] += 1
                }
            }
        }

        for (bit in 0 until diagnosticInputBits) {
            mostCommonBits = mostCommonBits shl min(bit, 1)

            if (bitsCount[bit] >= (diagnosticInput.size + 1) / 2) {
                mostCommonBits += 0b1
            }
        }

        return mostCommonBits
    }

    private fun leastCommonBitwiseValue(commonBits: Int) : Int {
        var usableBits: Int = 0b0

        for (bit in 0 until diagnosticInputBits) {
            usableBits = usableBits shl min(bit, 1)

            usableBits += 0b1
        }

        return commonBits.inv() and usableBits
    }

    fun powerConsumption() : Int {
        val mostCommon: Int = mostCommonBitwiseValue()
        val leastCommon: Int = leastCommonBitwiseValue(mostCommon)

        return mostCommon * leastCommon
    }
}
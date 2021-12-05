package day03

import readInputForDay

fun run() {
    val testInput = readInputForDay(3, true)
    val diagnosticDecoder: DiagnosticDecoder = DiagnosticDecoder(testInput, 5)
    check(diagnosticDecoder.powerConsumption() == 198)
    check(diagnosticDecoder.oxygenGeneratorRating() == 23)
    check(diagnosticDecoder.CO2ScrubberRating() == 10)
    check(diagnosticDecoder.lifeSupportRating() == 230)

    val input = readInputForDay(3)
    diagnosticDecoder.updateInput(input, 12)
    println(diagnosticDecoder.powerConsumption())
    println(diagnosticDecoder.lifeSupportRating())
}
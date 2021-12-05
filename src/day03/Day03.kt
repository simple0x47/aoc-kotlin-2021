package day03

import readInputForDay

fun run() {
    val testInput = readInputForDay(3, true)
    val diagnosticDecoder: DiagnosticDecoder = DiagnosticDecoder(testInput)
    check(diagnosticDecoder.powerConsumption() == 198)

    val input = readInputForDay(3)
    diagnosticDecoder.updateInput(input, 12)
    println(diagnosticDecoder.powerConsumption())
}
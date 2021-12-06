package day04

import readInputForDay

fun run() {
    val bingoPlayer: BingoPlayer = BingoPlayer()

    val testInput = readInputForDay(4, true)
    val testEventsHandler = Day04FirstPartTestBingoEventsHandler()
    bingoPlayer.playInput(testInput, testEventsHandler)
    check(testEventsHandler.hasBoardWon)

    val input = readInputForDay(4)
    val eventsHandler = Day04FirstPartBingoEventsHandler()
    bingoPlayer.playInput(input, eventsHandler)
}
package day04

import readInputForDay

fun run() {
    val bingoPlayer: BingoPlayer = BingoPlayer()

    val testInput = readInputForDay(4, true)
    val firstPartTestEventHandler = Day04FirstPartTestBingoEventsHandler()
    bingoPlayer.playInput(testInput, firstPartTestEventHandler)
    check(firstPartTestEventHandler.hasBoardWon)
    val secondPartTestEventHandler = Day04SecondPartTestBingoEventsHandler()
    bingoPlayer.playInput(testInput, secondPartTestEventHandler, playUntilLastWinner = true)
    check(secondPartTestEventHandler.hasBoardWon)

    val input = readInputForDay(4)
    val firstPartEventsHandler = Day04FirstPartBingoEventsHandler()
    bingoPlayer.playInput(input, firstPartEventsHandler)
    val secondPartEventsHandler = Day04SecondPartBingoEventsHandler()
    bingoPlayer.playInput(input, secondPartEventsHandler, playUntilLastWinner = true)
}
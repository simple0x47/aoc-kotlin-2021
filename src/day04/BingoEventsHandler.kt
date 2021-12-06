package day04

interface BingoEventsHandler {
    fun onBoardWin(board: BingoBoard, endingNumber: Int)
    fun onEndWithNoWinners()
}
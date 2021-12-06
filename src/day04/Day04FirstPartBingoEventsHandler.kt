package day04

class Day04FirstPartBingoEventsHandler : BingoEventsHandler {
    override fun onBoardWin(board: BingoBoard, endingNumber: Int) {
        println(board.sumUncheckedNumbers() * endingNumber)
    }

    override fun onEndWithNoWinners() {
        println("Unexpected. :(")
    }
}
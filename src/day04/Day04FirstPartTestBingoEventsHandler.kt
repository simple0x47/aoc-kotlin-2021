package day04

class Day04FirstPartTestBingoEventsHandler : BingoEventsHandler {
    var hasBoardWon: Boolean = false
        private set

    override fun onBoardWin(board: BingoBoard, endingNumber: Int) {
        check(board.sumUncheckedNumbers() * endingNumber == 4512)
        hasBoardWon = true
    }

    override fun onEndWithNoWinners() {
        check(false)
    }
}
package day02

interface Submarine {

    val position: VerticalInvertedPosition

    fun forward(amount: Long)
    fun down(amount: Long)
    fun up(amount: Long)
}
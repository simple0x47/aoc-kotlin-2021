package day02

class NewbieSubmarine : Submarine {
    private val _position: VerticalInvertedPosition = VerticalInvertedPosition()

    override val position: VerticalInvertedPosition
        get() = _position.copy()

    override fun forward(amount: Long) {
        _position.x += amount
    }

    override fun down(amount: Long) {
        _position.invertedY += amount
    }

    override fun up(amount: Long) {
        _position.invertedY -= amount
    }
}
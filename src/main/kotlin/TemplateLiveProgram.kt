import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.shape.Rectangle

val squareLists = mutableListOf(listOf(Rectangle(200.0, 200.0, 400.0)))

fun main() = application {
    configure {
        width = 800
        height = 800
    }
    program {
        extend {
            drawer.clear(ColorRGBa.WHITE)

            drawer.fill = ColorRGBa.BLACK

            squareLists.forEach { it.forEach { square ->
                drawer.rectangle(square)
            } }
        }

        keyboard.keyDown.listen {
            // new generation

            val side = squareLists.last()[0].width
            val thirdOfSide = side / 3.0

            val newSquares = mutableListOf<Rectangle>()

            for (smallSquare in squareLists.last()) {
                newSquares.addAll(listOf(
                    Rectangle(smallSquare.x - thirdOfSide, smallSquare.y + thirdOfSide, thirdOfSide),
                    Rectangle(smallSquare.x + thirdOfSide, smallSquare.y - thirdOfSide, thirdOfSide),
                    Rectangle(smallSquare.x + side, smallSquare.y + thirdOfSide, thirdOfSide),
                    Rectangle(smallSquare.x + thirdOfSide, smallSquare.y + side, thirdOfSide)
                ))
            }

            squareLists.add(newSquares)
            println(newSquares)
        }
    }
}
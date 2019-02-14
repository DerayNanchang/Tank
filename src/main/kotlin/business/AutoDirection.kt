package business

import enum.Direction
import java.util.*

interface AutoDirection  {

    /**
     *  自动选择移动方向
     */
    fun onCreateDirection() : Direction{
        val random = Random()
        val dir = random.nextInt(4)
        return when(dir){
            0 ->  Direction.LEFT
            1 ->  Direction.UP
            2 ->  Direction.RIGHT
            3 ->  Direction.DOWN
            else -> {
                return Direction.LEFT
            }
        }
    }
}
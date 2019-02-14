package business

import enum.Direction
import java.awt.Point

/**
 *  移动能力
 */
interface Moveable : View {


    // 坐标
    override var point : Point

    // 速度
    var speed : Int

    // 方向
    var direction : Direction

    /**
     *  将要碰撞的方法
     * @return Direction 返回方向  若是不会碰撞返回 Null
     */
    fun willCollision(blockable: Blockable) : Direction?


    fun notifyCollision(direction: Direction?,blockable: Blockable?)

}
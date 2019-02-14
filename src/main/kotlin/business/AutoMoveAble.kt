package business

import enum.Direction

/**
 *  自动移动能力 继承移动能力
 */
interface AutoMoveAble : View {

    var speed: Int

    fun autoMove()

    /**
     *  将要碰撞的方法
     * @return Direction 返回方向  若是不会碰撞返回 Null
     */
    fun willCollision(blockable: Blockable) : Direction?


    fun notifyCollision(direction: Direction?, blockable: Blockable?)
}
package model

import base.Config
import business.*
import enum.Direction
import java.awt.Point

private val Enemy.direction1: Direction
    get() {
        val createDirection = onCreateDirection()
        return createDirection
    }

/**
 *  自动移动 , 自动发射 ，销毁
 */
class Enemy(override var point: Point,var direction: Direction) :AutoMoveAble ,AutoDirection, AutoShootAble ,Destroyable{


    override var speed: Int = 10

    override var viewWidth = Config.BLOCK
    override var viewHeight = Config.BLOCK
    override fun autoMove() {

        val createDirection = direction1
        when(createDirection){
            Direction.LEFT -> point.x -= Config.SHOT_DEFEAULT_SPEED
            Direction.UP -> point.y -= Config.SHOT_DEFEAULT_SPEED
            Direction.RIGHT -> point.x += Config.SHOT_DEFEAULT_SPEED
            Direction.DOWN -> point.y += Config.SHOT_DEFEAULT_SPEED
        }
    }

    override fun draw() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isDestroy(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun willCollision(blockable: Blockable): Direction? {
        return collision(point,direction1,speed,blockable)
    }

    override fun notifyCollision(direction: Direction?, blockable: Blockable?) {
        // 如果碰撞了就转方向
        onCreateDirection()
    }

}
package model

import base.Config
import business.*
import enum.Direction
import org.itheima.kotlin.game.core.Painter
import java.awt.Point

/**
 * 子弹具有
 *  1） 自动移动能力
 *  2） 攻击能力  攻击力
 *  3） 销毁能力
 */
class Shot(var direction: Direction, override var point : Point) : AutoMoveAble ,Attackable, Destroyable{


    override var attack: Int = 1

    override var viewWidth:  Int = Config.SHOT_WIDTH
    override var viewHeight: Int = Config.SHOT_HEIGHT
    override var speed: Int = Config.SHOT_DEFEAULT_SPEED

    override fun draw() {
        Painter.drawImage("/img/tank/tankmissile.gif",point.x,point.y)
    }

    override fun autoMove() {
        when(direction){
            Direction.LEFT -> point.x -= Config.SHOT_DEFEAULT_SPEED
            Direction.UP -> point.y -= Config.SHOT_DEFEAULT_SPEED
            Direction.RIGHT -> point.x += Config.SHOT_DEFEAULT_SPEED
            Direction.DOWN -> point.y += Config.SHOT_DEFEAULT_SPEED
        }
    }


    /**
     *  子弹将要攻击了
     */
    override fun willAttack(underAttackable: UnderAttackable): Direction? {
        return collision(point,direction,speed,underAttackable)
    }


    /**
     *
     */
    override fun notifyAttack() {
        // 销毁当前攻击的子弹
    }

    /**
     *  子弹销毁
     */
    override fun isDestroy(): Boolean {
        return point.x < 0 || point.x > Config.WINDOW_WIDTH || point.y < 0 || point.y > Config.WINDOW_HEIGHT
    }

    override fun willCollision(blockable: Blockable): Direction? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun notifyCollision(direction: Direction?, blockable: Blockable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
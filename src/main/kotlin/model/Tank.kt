package model

import base.Config
import business.Blockable
import business.Moveable
import business.Shootable
import enum.Direction
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import java.awt.Point

/**
 *  我方坦克
 */
class Tank(override var point: Point) : Moveable , Shootable{


    // tank 具有的属性
    // 位置坐标 , 移动方向 , 发射炮弹 , 绘制 , 移动速度
    override var viewWidth: Int = Config.BLOCK
    override var viewHeight: Int = Config.BLOCK
    override var direction: Direction = Direction.UP    // 方向
    override var speed: Int = Config.TANK_DEFAULT_SPEED  // 默认移动速度

    var diffDirection : Direction? = null
    var blockable : Blockable? = null

    override fun draw() {

        val imagePath = when (direction) {
            Direction.LEFT -> "/img/tank/tankL.gif"
            Direction.UP -> "/img/tank/tankU.gif"
            Direction.RIGHT -> "/img/tank/tankR.gif"
            Direction.DOWN -> "/img/tank/tankD.gif"
        }
        Painter.drawImage(imagePath, point.x, point.y)
    }


    /**
     *  坦克移动
     */
    fun move(direction: Direction) {
        // 通知不可移动的方向 是否等于移动的方向
        if (diffDirection == direction){
            return
        }

        // 方向改变处理
        if (direction != this.direction){
            this.direction = direction
            return
        }

        // 越界处理
        if (point.x <= 0) point.x = 0
        if (point.x >=  Config.WINDOW_WIDTH - viewWidth) point.x = Config.WINDOW_WIDTH - viewWidth
        if (point.y <= 0) point.y = 0
        if (point.y >= Config.WINDOW_HEIGHT - viewHeight) point.y = Config.WINDOW_HEIGHT - viewHeight


        when (direction) {
            Direction.LEFT -> if (point.x > 0) point.x -= speed
            Direction.UP -> if (point.y > 0) point.y -= speed
            Direction.RIGHT -> if (point.x < (Config.WINDOW_WIDTH - viewWidth)) point.x += speed
            Direction.DOWN -> if (point.y < (Config.WINDOW_HEIGHT - viewHeight)) point.y += speed
        }
    }


    /**
     *  将要碰撞
     */
    override fun willCollision(blockable: Blockable): Direction? {

        // 向左移动时 移动物体的横坐标要 大于 阻碍物的横坐标 + 阻碍物的宽度
        // 向上移动时 移动物体的纵坐标要 大于 阻碍物的纵坐标 + 阻碍物的高度
        // 向右移动时 移动物体的横坐标 + 移动物体的宽度 小于 阻碍物的的横坐标
        // 向下移动时 移动物体的纵坐标 + 移动物体的高度 小于 阻碍物体的纵坐标
        return  collision(this.point,direction,speed,blockable)
    }


    /**
     *  通知坦克不可以移动了
     */
    override fun notifyCollision(direction: Direction?,blockable: Blockable?){
        this.diffDirection = direction
        this.blockable = blockable
    }

    /**
     *  发射子弹
     */
    override fun shootingShots() : Shot {
        // 子弹的 x = 当前坦克的 x + 坦克宽度/2
        // 传递一个方法
        // 计算坐标
        var shotX : Int
        var shotY : Int
        when(direction){
            Direction.LEFT -> {
                shotX = point.x - Config.SHOT_WIDTH
                shotY = point.y + (viewHeight - Config.SHOT_HEIGHT)/2
            }
            Direction.UP ->{
                shotX = point.x + (viewWidth - Config.SHOT_WIDTH)/2
                shotY = point.y - Config.SHOT_HEIGHT
            }
            Direction.RIGHT ->{
                shotX = point.x + viewWidth
                shotY = point.y + (viewHeight - Config.SHOT_HEIGHT)/2
            }
            Direction.DOWN ->{
                shotX = point.x + (viewWidth - Config.SHOT_WIDTH)/2
                shotY = point.y + viewHeight
            }
        }
        Composer.play("audio/fire.wav")
        return Shot(direction,Point(shotX,shotY))
    }

}
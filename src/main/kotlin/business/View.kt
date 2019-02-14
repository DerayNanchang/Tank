package business

import enum.Direction
import java.awt.Point

/**
 *  显示能力
 */
interface View {

    // 自身宽高
    var viewWidth : Int
    var viewHeight : Int

    // 显示坐标
    var point : Point

    // 显示资源文件
    fun draw()


    // 攻击者的坐标 , 攻击者的速度 , 攻击者的方向 , 被攻击者的View
    fun collision(point:Point, direction: Direction,speed :Int, view: View) : Direction? {

        var x = point.x
        var y = point.y

        when(direction){
            Direction.LEFT ->x -= speed
            Direction.UP -> y -= speed
            Direction.RIGHT ->x += speed
            Direction.DOWN -> y += speed
        }

        val isCollision = when {
            view.point.x + view.viewWidth <= x -> false
            view.point.y + view.viewHeight <= y -> false
            x + viewWidth <= view.point.x -> false
            else -> y + viewHeight > view.point.y
        }
        return if (isCollision) direction else  null



    }
}
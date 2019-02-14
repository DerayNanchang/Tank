package model

import base.Config
import business.View
import org.itheima.kotlin.game.core.Painter
import java.awt.Point

/**
 *  草坪
 */
class Grass(override var point: Point) : View {
    override var viewWidth = Config.BLOCK
    override var viewHeight = Config.BLOCK

    override fun draw() {
        Painter.drawImage("/img/obstacle/grass.png",point.x,point.y)
    }
}
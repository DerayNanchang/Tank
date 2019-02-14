package model

import base.Config
import business.Blockable
import org.itheima.kotlin.game.core.Painter
import java.awt.Point

/**
 *  河流
 */
class Water(override var point: Point) : Blockable {
    override var viewWidth = Config.BLOCK
    override var viewHeight = Config.BLOCK

    override fun draw() {
        Painter.drawImage("/img/obstacle/water.gif",point.x,point.y)
    }

}
package model

import base.Config
import business.Blockable
import business.UnderAttackable
import org.itheima.kotlin.game.core.Painter
import java.awt.Point

/**
 *  铁墙
 */
class Fe(override var point: Point) : Blockable, UnderAttackable {


    override var HP: Int = 1000

    override var viewWidth = Config.BLOCK
    override var viewHeight = Config.BLOCK

    override fun draw() {
        Painter.drawImage("/img/obstacle/fe.gif",point.x,point.y)
    }

    override fun notifyUnderAttack(attack: Int) {
    }

    override fun drawImage() {
    }
}
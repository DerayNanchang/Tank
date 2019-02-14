package model

import base.Config
import business.Blockable
import business.Destroyable
import business.UnderAttackable
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import java.awt.Point

/**!
 *  砖墙
 */
class Brick(override var point: Point) : Blockable , UnderAttackable , Destroyable{



    override var HP: Int = 3
    override var viewWidth = Config.BLOCK
    override var viewHeight = Config.BLOCK

    var index = 0

    var images = arrayListOf<String>()

    init {
        (1..5).forEach {
            images.add("img/blast$it.gif")
        }
    }



    override fun draw() {
        Painter.drawImage("/img/obstacle/walls.gif",point.x,point.y)
    }

    override fun notifyUnderAttack(attack: Int) {

        HP -= attack
        // 发出声音
        Composer.play("audio/hit.wav")
    }

    override fun isDestroy(): Boolean {
        return HP <= 0
        //return HP <= 0
    }

    override fun drawImage() {

        /*val i = index % images.size
        Painter.drawImage(images[i],point.x,point.y)
        index++*/

    }
}
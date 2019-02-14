package business

import model.Shot

/**
 *  攻击能力
 */
interface Shootable : View {
    /**
     *  发射子弹
     */
    fun shootingShots() : Shot
}
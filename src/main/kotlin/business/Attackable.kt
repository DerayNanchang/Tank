package business

import enum.Direction

/**
 *  攻击能力
 */
interface Attackable :View{
    // 攻击力 , 将要攻击物体 , 攻击通知

    var attack : Int

    fun willAttack(underAttackable: UnderAttackable) : Direction?

    fun notifyAttack()


}
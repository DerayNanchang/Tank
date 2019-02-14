package business

/**
 *  遭受攻击能力
 */
interface UnderAttackable :View {
    // 生命值 , 遭受攻击通知
    var HP : Int

    fun notifyUnderAttack(attack : Int)

    fun drawImage()
}
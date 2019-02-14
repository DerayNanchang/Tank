package base

object Config{

    //  窗体
    const val BLOCK = 60 //  每个方格的大小
    const val WINDOW_HEIGHT = BLOCK * 14 // 游戏窗口默认高度
    const val WINDOW_WIDTH = BLOCK * 14  // 游戏窗口默认宽度

    // 地图元素
    const val BRICK : Char = ('砖')
    const val FE    : Char = ('铁')
    const val GRASS : Char = ('草')
    const val WATER : Char = ('水')

    // 坦克
    const val TANK_DEFAULT_SPEED = 10   // 坦克移动速度


    // 子弹
    const val SHOT_DEFEAULT_SPEED = 10 // 子弹移动速度
    const val SHOT_HEIGHT = 17  // 子弹的高度
    const val SHOT_WIDTH = 17   // 子弹的宽度


}
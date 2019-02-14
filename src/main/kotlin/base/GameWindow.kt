package base

import business.*
import enum.Direction
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import model.*
import org.itheima.kotlin.game.core.Window
import java.awt.Point
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList

/**
 *  继承游戏引擎的游戏窗体
 */
class GameWindow : Window(title = "坦克大战",width = Config.WINDOW_WIDTH, height = Config.WINDOW_HEIGHT) {

    var views = CopyOnWriteArrayList<View>()
    lateinit var tank : Tank

    private fun initTankPoint() : Point{
        return Point(0 * Config.BLOCK,13 * Config.BLOCK)
    }

    override fun onCreate() {
        //窗体创建回调
        // 读取地图
        readMap()
        // 初始化 坦克
        initTank()
    }




    override fun onDisplay() {
        // 渲染回调
        views.forEach {
            it.draw() }
    }

    override fun onKeyPressed(event: KeyEvent) {
        // 按键响应回调

        //操作 tank
        when (event.code){
            KeyCode.A -> tank.move(Direction.LEFT)
            KeyCode.W -> tank.move(Direction.UP)
            KeyCode.D -> tank.move(Direction.RIGHT)
            KeyCode.S -> tank.move(Direction.DOWN)
            KeyCode.J -> views.add(tank.shootingShots())
            else -> { }
        }

    }

    override fun onRefresh() {
        // 耗时操作回调, 业务逻辑

        // 1. 判断坦克移动碰撞,如果发生碰撞就发出通知
        judgeCollision()

        // 2. 判断具备自动移动能力,让其自动移动
        judgeAutoMove()

        // 3. 攻击能力
        views.filter { it is Attackable }.forEach { attackable ->
            attackable as Attackable
            views.filter { it is UnderAttackable }.forEach { underAttackAble ->
                underAttackAble as UnderAttackable
                val willAttack = attackable.willAttack(underAttackAble)
                willAttack?.let {
                    // 销毁子弹
                    views.remove(attackable)
                    // 通知被攻击者 已经遭受攻击了
                    underAttackAble.notifyUnderAttack(attackable.attack)
                }
            }
        }
        // 4. 判断具备资源销毁的能力，是否被销毁
        judgeDestroy()
    }

    private fun judgeDestroy() {
        views.filter { it is Destroyable }.forEach {
            it as Destroyable
            if (it.isDestroy()) {
                views.remove(it)
            }
        }
    }

    private fun judgeAutoMove() {
        views.filter { it is Shot }.forEach {
            it as Shot
            it.autoMove()
        }
    }

    private fun judgeCollision() {
        views.filter { it is Moveable }.forEach { move ->
            move as Moveable
            var diffDirection: Direction? = null
            var blockable: Blockable? = null
            views.filter { it is Blockable }.forEach blackTag@{ block ->
                block as Blockable
                val direction = move.willCollision(block)
                direction?.let {
                    // 不为空的时候,不可以移动
                    // 通知 坦克 不能移动了
                    diffDirection = direction
                    blockable = block
                    return@blackTag
                }
            }
            move.notifyCollision(diffDirection, blockable)
        }
    }

    /**
     *  初始化我方坦克
     */
    private fun initTank() {
        tank = Tank(initTankPoint())
        views.add(tank)
    }

    /**
     *  读取资源地图文件
     */
    private fun readMap() {
        val file = File(javaClass.getResource("/map/1.map").file)
        var line = 0
        file.readLines().forEach {
            var column = 0
            it.toCharArray().forEach {
                when (it) {
                    // x 轴是由列决定的 y 轴是由行决定的
                    // 例子  xxx   t的坐标为(第三列,第二行)
                    //      xxt
                    Config.BRICK -> views.add(Brick(asPoint(column, line)))  //创建砖墙对象 ，并且获取对应的坐标
                    Config.FE -> views.add(Fe(asPoint(column, line)))     //创建铁墙对象 ，并且获取对应的坐标
                    Config.GRASS -> views.add(Grass(asPoint(column, line)))  //创建草坪对象 ，并且获取对应的坐标
                    Config.WATER -> views.add(Water(asPoint(column, line)))  //创建河流对象 ，并且获取对应的坐标
                }
                column++
            }
            line++
        }
    }

    /**
     * 转化为坐标点
     */
    private fun asPoint(line: Int, column: Int) = Point(line * Config.BLOCK, column * Config.BLOCK)
}

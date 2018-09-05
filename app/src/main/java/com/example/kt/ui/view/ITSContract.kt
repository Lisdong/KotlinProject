package com.example.kt.ui.view

/**
 * Created By LRD
 * on 2018/9/5  notes：
 */
interface ITSContract {
    interface View{
        fun onSendListener()

        fun onListener()

        fun loadContent()

        fun loadError(str:String)
    }

    interface Model{
        fun chatWithRobot(key:String , content:String )
    }

    interface Presenter {
        fun chatWithRobot(text: String)
    }
}
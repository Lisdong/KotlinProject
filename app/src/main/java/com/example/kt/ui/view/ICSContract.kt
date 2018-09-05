package com.example.kt.ui.view

import com.example.kt.bean.AnBean
import com.example.kt.bean.TurLingGson
import java.io.UnsupportedEncodingException

/**
 * Created By LRD
 * on 2018/8/31  notes：
 */
interface ICSContract {
    interface View{
        fun onSendListener()

        fun loadContent(turLingGson: TurLingGson)

        fun loadContent(gson: AnBean)

        fun loadError(str:String)
    }

    interface Model {
        fun chatWithRobot(key:String , content:String )
    }

    interface Presenter {
        fun chatWithRobot(text: String)
        fun chatWithAn(text:String)
    }
}
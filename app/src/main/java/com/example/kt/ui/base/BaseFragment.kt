package com.example.kt.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created By LRD
 * on 2018/8/14  notes：
 */
abstract class BaseFragment : Fragment(){
    open lateinit var activity :BaseActivity

    override  fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is  BaseActivity){
            activity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setLayout(),null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
    }

    protected abstract fun setLayout():Int

    protected abstract fun init()
}
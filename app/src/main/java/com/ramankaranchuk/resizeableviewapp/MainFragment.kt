package com.ramankaranchuk.resizeableviewapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_bottom_sheet.*


class MainFragment : Fragment() {

    private var deltaY = 0

    private var dy = 0f

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardview.radius = 40f
//        cardview.setOnTouchListener { v, event ->
//            val y = event.rawY.toInt()
//
//            val height = v.layoutParams.height
//
//            when(event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    deltaY = y - (v.layoutParams as RelativeLayout.LayoutParams).bottomMargin
//                    dy = v.y - event.rawY
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    val upperBoard = event.rawY + dy
//                    val bottomBoard = event.rawY + dy + height
//
//                    var delta = y - deltaY
//                    if (upperBoard < 0) {
//                        delta -= upperBoard.toInt()
//                    } else if (bottomBoard > this.view!!.height) {
//                        delta -= bottomBoard.toInt() - this.view!!.height
//                    }
//                    val layoutParams = v.layoutParams as RelativeLayout.LayoutParams
//                    layoutParams.bottomMargin = delta
//                    layoutParams.topMargin = -layoutParams.bottomMargin
//                    v.layoutParams = layoutParams
//
//                    v.animate().translationY((delta).toFloat())
//                            .setDuration(0)
//                            .start()
//
//                    Log.d("SFContentFragment",
//                            "y=$y," +
//                                    " deltaY=$deltaY," +
//                                    " y - deltaY=${y - deltaY}," +
//                                    " // " +
//                                    " event.rawY + dy=${event.rawY + dy}," +
//                                    " event.rawY + dy + height=${event.rawY + dy + height} > windowHeight=${this.view!!.height} ? y = windowHeight - height=${this.view!!.height - height}," +
//                                    " event.rawY + dy=${event.rawY + dy} < 0 ? y = 0")
//                }
//            }
//
//            true
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}

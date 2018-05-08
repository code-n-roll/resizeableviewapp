package com.ramankaranchuk.resizeableviewapp

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.view.MotionEvent.*
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    companion object {
        private val TAG = MainFragment::class.java.simpleName
    }

    private var deltaY = 0

    private var velocityTracker: VelocityTracker? = null

    private var gestureDetector: GestureDetector = GestureDetector(context, object : GestureListener() {
        override fun onSwipeTop() {
            val anim = ValueAnimator.ofInt(cardview.measuredHeight,
                    main_fragment_root.height - (cardview.layoutParams as FrameLayout.LayoutParams).bottomMargin)
            anim.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int
                val layoutParams = cardview.layoutParams
                layoutParams.height = value
                cardview.layoutParams = layoutParams
            }
            anim.setDuration(500)
                    .start()
            Log.d("$TAG/gestureDetector", "swipeTop")
        }

        override fun onSwipeBottom() {
            val anim = ValueAnimator.ofInt(cardview.measuredHeight,
                    header.height - (cardview.layoutParams as FrameLayout.LayoutParams).bottomMargin)
            anim.addUpdateListener { valueAnimator ->
                val value = valueAnimator.animatedValue as Int
                val layoutParams = cardview.layoutParams
                layoutParams.height = value
                cardview.layoutParams = layoutParams
            }
            anim.setDuration(500)
                    .start()
            Log.d("$TAG/gestureDetector", "swipeBottom")
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardview.radius = 40f

        header.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)

            val currentY = event.rawY.toInt()

            Log.d(TAG, " currentY = $currentY," +
                    " deltaY = $deltaY, " +
                    " currentY - deltaY = ${currentY - deltaY}," +
                    " cardview.layoutParams.height = ${(cardview.layoutParams as FrameLayout.LayoutParams).height}," +
                    " cardview.layoutParams.topMargin = ${(cardview.layoutParams as FrameLayout.LayoutParams).topMargin}," +
                    " cardview.layoutParams.bottomMargin = ${(cardview.layoutParams as FrameLayout.LayoutParams).bottomMargin}" +
                    " main_fragment_root.height = ${main_fragment_root.height},")

            when(event.action and ACTION_MASK) {
                ACTION_DOWN -> {
                    if (velocityTracker == null) {
                        velocityTracker = VelocityTracker.obtain()
                    } else {
                        velocityTracker?.clear()
                    }

                    val layoutParams = cardview.layoutParams as FrameLayout.LayoutParams
                    deltaY = currentY - layoutParams.topMargin
                }

                ACTION_MOVE -> {
                    velocityTracker?.addMovement(event)

                    velocityTracker?.computeCurrentVelocity(1000)
                    Log.d("$TAG/velocity", "x velocity: ${velocityTracker?.xVelocity}, " +
                            " y velocity: ${velocityTracker?.yVelocity}")

                    val layoutParams = cardview.layoutParams as FrameLayout.LayoutParams
                    var deltaMove = currentY - deltaY


                    if (layoutParams.height + layoutParams.bottomMargin - deltaMove > main_fragment_root.height ||
                            layoutParams.height - deltaMove + layoutParams.bottomMargin < header.height) {
                        deltaMove = 0
                    }
                    layoutParams.height -= deltaMove
                    cardview.layoutParams = layoutParams
                    deltaY = currentY - layoutParams.topMargin

                    cardview.invalidate()
                }

                ACTION_CANCEL -> {
                    velocityTracker?.recycle()
                }
            }
            true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}

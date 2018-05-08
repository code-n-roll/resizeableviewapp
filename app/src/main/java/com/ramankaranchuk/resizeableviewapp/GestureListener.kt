package com.ramankaranchuk.resizeableviewapp

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import kotlin.math.abs


open class GestureListener : GestureDetector.SimpleOnGestureListener() {

    companion object {

        private val TAG = GestureListener::class.java.simpleName

        private const val SWIPE_THRESHOLD = 20

        private const val SWIPE_VELOCITY_THRESHOLD = 2000
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        var result = false
        val diffY = e2.y - e1.y
        Log.d("$TAG/onFling",
                "diffY = $diffY," +
                " abs(diffY) = ${abs(diffY)} > SWIPE_THRESHOLD = $SWIPE_THRESHOLD ," +
                "abs(velocityY) = ${abs(velocityY)} > SWIPE_VELOCITY_THRESHOLD = $SWIPE_VELOCITY_THRESHOLD")
        if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
            if (diffY > 0) {
                onSwipeBottom()
            } else {
                onSwipeTop()
            }
            result = true
        }
        return result
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        Log.d("$TAG/onScroll", "e1.y = ${e1.y}, e2.y = ${e2.y}, distanceX = $distanceX, distanceY = $distanceY")
        return super.onScroll(e1, e2, distanceX, distanceY)
    }

    open fun onSwipeTop() {

    }

    open fun onSwipeBottom() {

    }
}
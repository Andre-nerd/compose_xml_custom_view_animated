package ru.zoomparty.xml_compose_custom_view_animation.ui

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

class XmlCustomViewGroup @JvmOverloads constructor(
    context: Context,
    attr:AttributeSet? = null,
    defStyleAttr:Int = 0
) : ViewGroup(context,attr,defStyleAttr) {
    private val firstChild:View?
        get() = if(childCount > 0) getChildAt(0) else null
    private val secondChild:View?
        get() = if(childCount > 1) getChildAt(1) else null

    private var firstWidth = 0
    private var firstHeight = 0
    private var secondWidth = 0
    private var secondHeight = 0
    private var height = 0
    private var width = 0


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        checkChildCount()
        firstChild?.let{measureChild(it,widthMeasureSpec)}
        secondChild?.let { measureChild(it,widthMeasureSpec) }

        firstWidth = firstChild?.measuredWidth ?: 0
        firstHeight = firstChild?.measuredHeight ?: 0
        secondWidth = secondChild?.measuredWidth ?: 0
        secondHeight = secondChild?.measuredHeight ?: 0
        // firstChild?.width будут актуальны только после вызова на ребенке layout

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        width = MeasureSpec.getSize(widthMeasureSpec)
        height = when(childCount){
            1 -> firstHeight * 2
            else -> firstHeight + secondHeight
        }
        setMeasuredDimension(width, height)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val firstLeftBias = (width - firstWidth) / 2
        val secondLeftBias = (width - secondWidth) / 2
        firstChild?.layout(
            firstLeftBias,
            0,
            (firstChild?.measuredWidth ?: 0) + firstLeftBias,
            firstChild?.measuredHeight ?: 0
        )
        secondChild?.layout(
            secondLeftBias,
            height - secondHeight - secondHeight/2,
            (secondChild?.measuredWidth ?: 0) + secondLeftBias,
            height
        )
        firstChild?.let {
            ObjectAnimator.ofFloat(firstChild, "translationY", height / 2.0f - firstHeight / 2f, 0f)
                .apply {
                    duration = 2000
                    start()
                }
        }
        secondChild?.let {
            ObjectAnimator.ofFloat(secondChild, "translationY",secondHeight/2 * 1f).apply {
                duration = 2000
                start()
            }
        }
    }
    private fun measureChild(child:View,widthMeasureSpec: Int){
//        val specSize = MeasureSpec.getSize(widthMeasureSpec)
        val childWidthSpec = MeasureSpec.UNSPECIFIED
        val childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        child.measure(childWidthSpec, childHeightSpec)
    }

    private fun checkChildCount(){
        if(childCount > 2) error("Custom group should not contain more than 2 children")
    }

}
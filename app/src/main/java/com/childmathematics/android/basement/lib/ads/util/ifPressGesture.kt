package com.childmathematics.android.basement.lib.ads.util

import androidx.compose.foundation.gestures.GestureCancellationException
import androidx.compose.foundation.gestures.PressGestureScope
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.unit.Density
import androidx.compose.ui.util.fastAll
import androidx.compose.ui.util.fastAny
//import com.childmathematics.android.shiftschedule.navigation.detectTapAndPressUnconsumed
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex

//
suspend fun PointerInputScope.detectTapAndPressUnconsumed(
    onPress: suspend PressGestureScope.(Offset) -> Unit = NoPressGesture,
    onTap: ((Offset) -> Unit)? = null
) {
    val pressScope = PressGestureScopeImpl(this)
    //    forEachGesture {
    forEachGesture {
        coroutineScope {
            pressScope.reset()
            awaitPointerEventScope {
                val down = awaitFirstDown(requireUnconsumed = false).also { if (it.pressed != it.previousPressed) it.consume() }
                if (onPress !== NoPressGesture) {
                    launch { pressScope.onPress(down.position) }
                }
                val up = waitForUpOrCancellationInitial()
                if (up == null) {
                    pressScope.cancel() // tap-up was canceled
                } else {
                    pressScope.release()
                    onTap?.invoke(up.position)
                }
            }
        }
    }
}
suspend fun AwaitPointerEventScope.waitForUpOrCancellationInitial(): PointerInputChange? {
    while (true) {
        val event = awaitPointerEvent(PointerEventPass.Initial)
        if (event.changes.fastAll { it.changedToUp() }) {
// All pointers are up
            return event.changes[0]
        }
        if (event.changes.fastAny { it. isConsumed || it.isOutOfBounds(
//                if (event.changes.fastAny { it.consumed.downChange || it.isOutOfBounds(
                size,
                extendedTouchPadding
            )
            }) {
//           if (event.changes.fastAny { it.consumed.downChange || it.isOutOfBounds(size) }) {
            return null // Canceled
        }
// Check for cancel by position consumption. We can look on the Final pass of the
// existing pointer event because it comes after the Main pass we checked above.
        val consumeCheck = awaitPointerEvent(PointerEventPass.Final)
        if (consumeCheck.changes.fastAny { it.isConsumed }) {
            return null
        }
    }
}
private val NoPressGesture: suspend PressGestureScope.(Offset) -> Unit = { }

/**
 * Detects tap, double-tap, and long press gestures and calls [onTap], [onDoubleTap], and
 * [onLongPress], respectively, when detected. [onPress] is called when the press is detected
 * and the [PressGestureScope.tryAwaitRelease] and [PressGestureScope.awaitRelease] can be
 * used to detect when pointers have released or the gesture was canceled.
 * The first pointer down and final pointer up are consumed, and in the
 * case of long press, all changes after the long press is detected are consumed.
 *
 * Each function parameter receives an [Offset] representing the position relative to the containing
 * element. The [Offset] can be outside the actual bounds of the element itself meaning the numbers
 * can be negative or larger than the element bounds if the touch target is smaller than the
 * [ViewConfiguration.minimumTouchTargetSize].
 *
 * When [onDoubleTap] is provided, the tap gesture is detected only after
 * the [ViewConfiguration.doubleTapMinTimeMillis] has passed and [onDoubleTap] is called if the
 * second tap is started before [ViewConfiguration.doubleTapTimeoutMillis]. If [onDoubleTap] is not
 * provided, then [onTap] is called when the pointer up has been received.
 *
 * After the initial [onPress], if the pointer moves out of the input area, the position change
 * is consumed, or another gesture consumes the down or up events, the gestures are considered
 * canceled. That means [onDoubleTap], [onLongPress], and [onTap] will not be called after a
 * gesture has been canceled.
 *
 * If the first down event is consumed somewhere else, the entire gesture will be skipped,
 * including [onPress].
 */
/**
 * [detectTapGestures]'s implementation of [PressGestureScope].
 */
private class PressGestureScopeImpl(
    density: Density
) : PressGestureScope, Density by density {
    private var isReleased = false
    private var isCanceled = false
    private val mutex = Mutex(locked = false)

    /**
     * Called when a gesture has been canceled.
     */
    fun cancel() {
        isCanceled = true
        mutex.unlock()
    }
    /**
     * Called when all pointers are up.
     */
    fun release() {
        isReleased = true
        mutex.unlock()
    }

    /**
     * Called when a new gesture has started.
     */
    suspend fun reset() {
        mutex.lock()
        isReleased = false
        isCanceled = false
    }
    override suspend fun awaitRelease() {
        if (!tryAwaitRelease()) {
            throw GestureCancellationException("The press gesture was canceled.")
        }
    }

    override suspend fun tryAwaitRelease(): Boolean {
        if (!isReleased && !isCanceled) {
            mutex.lock()
            mutex.unlock()
        }
        return isReleased
    }

}
//--------------------------------------------------------------

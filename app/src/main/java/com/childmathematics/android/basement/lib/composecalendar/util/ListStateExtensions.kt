package com.childmathematics.android.basement.lib.composecalendar.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

/*
class lazyListStateN:LazyListState {
   fun <T> Flow<T>.throttleOnOffset() =
    combine(
      snapshotFlow { firstVisibleItemScrollOffset }
    ) { newMonth, offset ->
//    newMonth to (offset <= MinimalOffsetForEmit)
      newMonth to (offset <= MINIMAL_OFFSET_FOR_EMIT)
    }.filter { (_, shouldUpdate) ->
      shouldUpdate
    }.map { (newValue, _) -> newValue }
}
*/

context(LazyListState ) internal fun <T> Flow<T>.throttleOnOffset() =
  combine(
    snapshotFlow { firstVisibleItemScrollOffset }
  ) { newMonth, offset ->
//    newMonth to (offset <= MinimalOffsetForEmit)
    newMonth to (offset <= MINIMAL_OFFSET_FOR_EMIT)
  }.filter { (_, shouldUpdate) ->
    shouldUpdate
  }.map { (newValue, _) -> newValue }

private const val MINIMAL_OFFSET_FOR_EMIT = 10
/*
//до
context(ContextReceiverType)
fun contextReceiverMember() = TODO()

context(ContextReceiverType)
fun someFunction() {
    contextReceiverMember()
}
//-------------------------
context(LazyListState ) internal fun <T> Flow<T>.throttleOnOffset() =
  combine(
    snapshotFlow { firstVisibleItemScrollOffset }
  ) { newMonth, offset ->
//    newMonth to (offset <= MinimalOffsetForEmit)
    newMonth to (offset <= MINIMAL_OFFSET_FOR_EMIT)
  }.filter { (_, shouldUpdate) ->
    shouldUpdate
  }.map { (newValue, _) -> newValue }

//=============
//после
class ContextReceiverType {
    fun contextReceiverMember() = TODO()
}

fun ContextReceiverType.someFunction() {
    contextReceiverMember()
}
//------------------------------------
class lazyListState:LazyListState {
internal fun <T> Flow<T>.throttleOnOffset() =
  combine(
    snapshotFlow { firstVisibleItemScrollOffset }
  ) { newMonth, offset ->
//    newMonth to (offset <= MinimalOffsetForEmit)
    newMonth to (offset <= MINIMAL_OFFSET_FOR_EMIT)
  }.filter { (_, shouldUpdate) ->
    shouldUpdate
  }.map { (newValue, _) -> newValue }
}
//--------------------------

class LazyListState {
  internal fun <T> Flow<T>.throttleOnOffset() =
    combine(
      snapshotFlow { firstVisibleItemScrollOffset }
    ) { newMonth, offset ->
//    newMonth to (offset <= MinimalOffsetForEmit)
      newMonth to (offset <= MINIMAL_OFFSET_FOR_EMIT)
    }.filter { (_, shouldUpdate) ->
      shouldUpdate
    }.map { (newValue, _) -> newValue }
}

 */
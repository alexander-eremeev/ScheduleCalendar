package com.childmathematics.android.basement.lib.composecalendar.util

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

/*

@Stable
internal class MonthListState(
  private val coroutineScope: CoroutineScope,
  private val monthState: MonthState,
  private val listState: LazyListState,
) {

  private val currentFirstVisibleMonth by derivedStateOf {
    getMonthForPage(listState.firstVisibleItemIndex)
  }
 */

context( LazyListState ) internal fun <T> Flow<T>.throttleOnOffset() =
  combine(
    /*
    snapshotFlow создает поток, который запускает блок при сборе и выдает результат, записывая любое
    состояние снимка,  к которому был получен доступ
    Если результат блока не равен предыдущему результату, поток выдаст этот новый результат.
     */
    snapshotFlow { firstVisibleItemScrollOffset }
    /*
    Смещение прокрутки первого видимого элемента. Прокрутка вперед положительна, т. е. величина,
    на которую элемент смещен назад.Обратите внимание, что это свойство наблюдаемое, и если
    вы используете его в компонуемой функции, оно будет перекомпоновываться при каждой прокрутке,
    что может привести к проблемам с производительностью.
     */
  ) { newMonth, offset ->
//    newMonth to (offset <= MinimalOffsetForEmit)
    newMonth to (offset <= MINIMAL_OFFSET_FOR_EMIT)
  }.filter { (_, shouldUpdate) ->
    shouldUpdate
  }.map { (newValue, _) -> newValue }
/*
Возвращает поток, содержащий результаты применения заданной функции преобразования к каждому значению исходного потока.
 */
/*
class lazListState {
  @Composable
  fun lazyListState() = rememberLazyListState()
}

context(laListState: lazListState)  fun <T> Flow<T>.throttleOnOffset() =
  combine(
    /*
    snapshotFlow создает поток, который запускает блок при сборе и выдает результат, записывая любое
    состояние снимка,  к которому был получен доступ
    Если результат блока не равен предыдущему результату, поток выдаст этот новый результат.
     */
    snapshotFlow { firstVisibleItemScrollOffset }
  ) { newMonth, offset ->
//    newMonth to (offset <= MinimalOffsetForEmit)
    newMonth to (offset <= MINIMAL_OFFSET_FOR_EMIT)
  }.filter { (_, shouldUpdate) ->
    shouldUpdate
  }.map { (newValue, _) -> newValue }
*/
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
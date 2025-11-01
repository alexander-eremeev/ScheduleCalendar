package com.childmathematics.android.basement.lib.navigation.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry
/*
    AnimatedContentTransitionScope предоставляет функции, которые удобны и применимы только
    в контексте AnimatedContent, например слайдИнтоКонтейнер и слайдАутОфКонтейнер.

     Это определяет горизонтальное/вертикальное перемещение, характерное для AnimatedContent, от края
     контейнера. Величина смещения рассчитывается динамически на основе текущего размера AnimatedContent
     и выравнивания его содержимого. Это смещение (может быть положительным или отрицательным
     в зависимости от направления слайда) затем передается в InitialOffset. По умолчанию InitialOffset
     будет использовать смещение, рассчитанное системой, для вставки содержимого.
     SlideIntoContainer — это удобная альтернатива слайдам SlideInHorizontally и SlideInVertically,
     когда входящий и исходящий контент различаются по размеру. В противном случае это было бы
     эквивалентно слайду InHorizontally и слайдуInVertically со смещением на всю ширину/высоту.
     В сторону указывает направление скольжения. Содержимое можно перемещать в контейнер в направлении
     SlideDirection.Left, SlideDirection.Right, SlideDirection.Up и SlideDirection.Down.
     анимацияSpec определяет анимацию, которая будет использоваться для анимации слайда.
 */
fun AnimatedContentTransitionScope<NavBackStackEntry>.screenSlideIn(): EnterTransition =
    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)
/*
    Это затемняет содержимое перехода от полной непрозрачности до указанной целевой альфа
    (т. е. targetAlpha), используя предоставленную анимациюSpec. По умолчанию содержимое
    будет затемнено до полной прозрачности (т. е. значение targetAlpha по умолчанию равно 0),
    а анимацияSpec по умолчанию использует Spring.
 */
fun screenFadeOut(): ExitTransition = fadeOut()
/*
    При этом содержимое перехода постепенно исчезает от указанной начальной альфа
    т. е. InitialAlpha) до 1f, используя предоставленную анимациюSpec. По умолчанию InitialAlpha
    имеет значение 0f, а Spring используется по умолчанию.
 */
fun screenFadeIn(): EnterTransition = fadeIn()

fun AnimatedContentTransitionScope<NavBackStackEntry>.screenSlideOut(): ExitTransition =
    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End)

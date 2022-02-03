


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.childmathematics.android.shiftschedule.composecalendar.StaticCalendar
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun StaticCalendarSample() {
  StaticCalendar(
    modifier = Modifier
      .padding(8.dp)
      .animateContentSize(),
  )
}

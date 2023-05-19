package com.childmathematics.android.shiftschedule.util

import android.content.Context
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


// [START android_compose_interop_apis_views_in_compose]
@Composable
fun CustomView() {
    var selectedItem by remember { mutableStateOf(0) }

    // Adds view to Compose
    AndroidView(
        modifier = Modifier.fillMaxSize(), // Occupy the max size in the Compose UI tree
        factory = { context ->
            // Creates view
            MyView(context).apply {
                // Sets up listeners for View -> Compose communication
                setOnClickListener {
                    selectedItem = 1
                }
            }
        },
        update = { view ->
            // View's been inflated or state read in this block has been updated
            // Add logic here if necessary

            // As selectedItem is read here, AndroidView will recompose
            // whenever the state changes
            // Example of Compose -> View communication
            view.selectedItem = selectedItem
        }
    )
}

@Composable
fun ContentExample() {
    Column(Modifier.fillMaxSize()) {
        Text("Look at this CustomView!")
        CustomView()
    }
}

// [START_EXCLUDE silent]
class MyView(context: Context) : View(context) {
    var selectedItem: Int = 0
}
// [END_EXCLUDE silent]
// [END android_compose_interop_apis_views_in_compose]
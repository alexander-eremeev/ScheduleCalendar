package com.childmathematics.android.shiftschedule.presentation.ui.countries

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.childmathematics.android.basement.lib.ads.ui.theme.Shapes
import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import com.childmathematics.android.shiftschedule.presentation.util.SnackbarEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CountriesPage(
    viewModel: CountriesViewModel
) {

    val countriesViewState by viewModel.viewState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    var countItem: Int =0


    // State to manage Snackbar
    val snackbarHostState = remember { SnackbarHostState() }

    // Collect snackbar events and show snackbar
    LaunchedEffect(viewModel) {
        viewModel.effectFlow.collectLatest { effect ->
            if (effect is SnackbarEffect.ShowSnackbar) {
                val result = snackbarHostState.showSnackbar(
                    message = effect.message,
                    actionLabel = effect.actionLabel
                )
                if (result == SnackbarResult.ActionPerformed && effect.actionLabel == "Undo") {
//                    viewModel.handleIntent(Intent.UndoDelete)
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background, shape = Shapes.medium)
            ) {
                if (countriesViewState.countries.count()>0) {
                    CountryHeader()
                    CountryListScreen (viewModel)
                }
            }
        }
    )
}
@Composable
fun CountryItem (countryEnt: CountryEntity?) {
    Row(
        modifier = Modifier
    //        .fillMaxWidth(),
    ) {
        Text(countryEnt?.countryId.toString(),textAlign = TextAlign.Right
            ,modifier = Modifier .weight(.1f)  )
        Spacer(modifier = Modifier.width(10.dp))
        Text(countryEnt?.shortName.toString(),textAlign = TextAlign.Left
            ,modifier = Modifier .weight(.1f))
        Spacer(modifier = Modifier.width(10.dp))
        Text(countryEnt?.longName.toString(),textAlign = TextAlign.Left
                ,modifier = Modifier    .width( 130.dp ) .weight(.8f))
    }
}
// Список праздничных дней
@Composable
fun CountryListScreen (countriesViewModel: CountriesViewModel) {
    val countriesViewState by countriesViewModel.viewState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    Box(
        modifier = Modifier
 //           .weight(1f)
 //           .fillMaxWidth(),
 //       contentAlignment = Alignment.TopCenter
    ) {
        when {
            countriesViewState.isLoading -> CircularProgressIndicator(color =
                MaterialTheme.colorScheme.primary)
            countriesViewState.countries.isEmpty() && ! countriesViewState.isLoading ->
                Text("No countries available")
            countriesViewState.countries.count()>0 -> {
                // Содержимое таблицы
                LazyColumn(
                    state = listState,
                    modifier = Modifier
 //                       .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemsIndexed(
                        items = countriesViewState.countries,
                        key = { index, item ->
                            index
                        },
                    ) { index, item ->
                        CountryItem(countriesViewState.countries[index] )
                    }
                }
            }
            else -> {
            }

        }
    }
}
//--------------------------
//     // Заголовоки столбцов таблицы
@Composable
fun CountryHeader()
{
    // Заголовоки столбцов таблицы
    Row(
        modifier = Modifier
            //        .fillMaxWidth(),
            .padding(horizontal = 16.dp),
    ) {
        Text("Код",textAlign = TextAlign.Right,modifier = Modifier .weight(.1f)  )
        Spacer(modifier = Modifier.width(10.dp))
        Text("Кр.",textAlign = TextAlign.Left ,modifier = Modifier .weight(.1f))
        Spacer(modifier = Modifier.width(10.dp))
        Text("Наименование",textAlign = TextAlign.Left
            ,modifier = Modifier    .width( 130.dp ) .weight(.8f))
    }

}
package com.childmathematics.android.shiftschedule.presentation.ui.countries

import android.R.attr.country
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.childmathematics.android.shiftschedule.data.models.CountryEntity
import com.childmathematics.android.shiftschedule.presentation.ui.countries.uimodels.CountryViewIntent

@Composable
fun CountriesPage(
    viewModel: CountriesViewModel
) {
    Text(text = "Country Экран !!!!!!!!!!!!!!!", fontWeight = FontWeight.Bold)
/*
    LaunchedEffect(Unit) {
        viewModel.handleIntent(CountryViewIntent.LoadCountries)
    }

 */

    viewModel.handleIntent(CountryViewIntent.LoadCountries)


    CountryListScreen (viewModel)


}
/*
@Composable
@ExperimentalMaterialApi
fun BooksScreen(
    viewModel: BooksViewModel = hiltViewModel(),
    navigateToUpdateBookScreen: (bookId: Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getBooks()
    }
    Scaffold(
        topBar = {
            BooksTopBar()
        },
        content = { padding ->
            BooksContent(
                padding = padding,
                books = viewModel.books,
                deleteBook = { book ->
                    viewModel.deleteBook(book)
                },
                navigateToUpdateBookScreen = navigateToUpdateBookScreen
            )
            AddBookAlertDialog(
                openDialog = viewModel.openDialog,
                closeDialog = {
                    viewModel.closeDialog()
                },
                addBook = { book ->
                    viewModel.addBook(book)
                }
            )
        },
        floatingActionButton = {
            AddBookFloatingActionButton(
                openDialog = {
                    viewModel.openDialog()
                }
            )
        }
    )
}
 */
@Composable
fun CountryItem (country: CountryEntity) {
    //   Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding( 16.dp )
//    )
    Spacer( modifier = Modifier.width( 16. dp))
    Text("CountryItem --------")
//    Column( modifier = Modifier.weight( 1f )) {
    Column( modifier = Modifier) {
        Text(text = country.shortName, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = country.longName)
    }
}
// Список праздничных дней
@Composable
fun CountryListScreen (countriesViewModel: CountriesViewModel) {
    val countriesViewState by countriesViewModel.viewState.collectAsStateWithLifecycle()
    var countIt: Int =0

    Text("CountryListScreen --------")
    if (countriesViewState.isLoading) {Text("CountryListScreen ----загружено")}
    else {Text("CountryListScreen ----НЕ загружено")}

    if (countriesViewState.nameError) {Text("CountryListScreen ----ошибка")}
    else {Text("CountryListScreen ----НЕ ошибки")}
    if (countriesViewState.countries.count() >0 )
        CountryItem(countriesViewState.countries[1] )
    else {Text("CountryListScreen ----DB пусто")}


    LazyColumn(
        modifier = Modifier
            //           .fillMaxSize()
            .padding(horizontal = 16.dp ),
        horizontalAlignment = Alignment.CenterHorizontally,
        userScrollEnabled = true,

    ) {
        items(countIt, {key -> countriesViewState.countryid})
        { country->
            CountryItem(countriesViewState.countries[countIt] )
        }
        /*
        items(countries, key = { country -> country.countryid })
        { country->
            CountryItem(country )
        }


 */

    }
}
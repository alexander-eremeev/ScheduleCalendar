package com.childmathematics.android.shiftschedule.basis.datasource.preference

import androidx.datastore.core.DataStore
import com.childmathematics.android.shiftschedule.basis.di.DiName
import com.childmathematics.android.shiftschedule.basis.extension.toLanguage
import com.childmathematics.android.shiftschedule.basis.extension.toLanguagePreference
import com.childmathematics.android.shiftschedule.basis.extension.toTheme
import com.childmathematics.android.shiftschedule.basis.extension.toThemePreference
import com.childmathematics.android.shiftschedule.basis.datasource.preference.model.CredentialPreference
import com.childmathematics.android.shiftschedule.basis.datasource.preference.model.LanguagePreference
import com.childmathematics.android.shiftschedule.basis.datasource.preference.model.ThemePreference
import com.childmathematics.android.shiftschedule.basis.datasource.preference.model.UserPreference
import com.childmathematics.android.shiftschedule.model.Credential
import com.childmathematics.android.shiftschedule.model.Language
import com.childmathematics.android.shiftschedule.model.Theme
import com.childmathematics.android.shiftschedule.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
//import kotlinx.coroutines.flow.internal.NopCollector.emit
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class PreferenceManager @Inject constructor(
    @Named(DiName.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher,
    private val credentialDataStore: DataStore<CredentialPreference>,
    private val userDataStore: DataStore<UserPreference>,
    private val themeDataStore: DataStore<ThemePreference>,
    private val languageDataStore: DataStore<LanguagePreference>,
) {

    fun getCredential(): Flow<Credential> {
        return credentialDataStore.data
            .map { Credential(it.token) }
            .catch { emit(Credential(token = "")) }
            .flowOn(dispatcher)
    }

    fun getUser(): Flow<User> {
        return userDataStore.data
            .map { User(it.email) }
            .catch { emit(User(email = "")) }
            .flowOn(dispatcher)
    }

    fun getTheme(): Flow<Theme> {
        return themeDataStore.data.map { it.toTheme() }
            .catch { emit(Theme.SYSTEM) }
            .flowOn(dispatcher)
    }

    fun getLanguage(): Flow<Language> {
        return languageDataStore.data.map { it.toLanguage() }
            .catch { emit(Language.ENGLISH) }
            .flowOn(dispatcher)
    }

    suspend fun setCredential(data: Credential) {
        withContext(dispatcher) {
            credentialDataStore.updateData {
                CredentialPreference(data.token)
            }
        }
    }

    suspend fun setUser(data: User) {
        withContext(dispatcher) {
            userDataStore.updateData {
                UserPreference(data.email)
            }
        }
    }

    suspend fun setTheme(data: Theme) {
        withContext(dispatcher) {
            themeDataStore.updateData {
                data.toThemePreference()
            }
        }
    }

    suspend fun setLanguage(data: Language) {
        withContext(dispatcher) {
            languageDataStore.updateData {
                data.toLanguagePreference()
            }
        }
    }

}

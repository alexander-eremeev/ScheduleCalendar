package com.childmathematics.android.shiftschedule.basis.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.childmathematics.android.shiftschedule.basis.datasource.preference.CredentialPreferenceSerializer
import com.childmathematics.android.shiftschedule.basis.datasource.preference.LanguagePreferenceSerializer
import com.childmathematics.android.shiftschedule.basis.datasource.preference.ThemePreferenceSerializer
import com.childmathematics.android.shiftschedule.basis.datasource.preference.UserPreferenceSerializer
import com.childmathematics.android.shiftschedule.basis.datasource.preference.model.CredentialPreference
import com.childmathematics.android.shiftschedule.basis.datasource.preference.model.LanguagePreference
import com.childmathematics.android.shiftschedule.basis.datasource.preference.model.ThemePreference
import com.childmathematics.android.shiftschedule.basis.datasource.preference.model.UserPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val CREDENTIAL_NAME = "credential-preference.pb"
private const val USER_NAME = "user-preference.pb"
private const val THEME_NAME = "theme-preference.pb"
private const val LANGUAGE_NAME = "language-preference.pb"

private val Context.credentialDataStore: DataStore<CredentialPreference> by dataStore(
    fileName = CREDENTIAL_NAME,
    serializer = CredentialPreferenceSerializer
)
private val Context.userDataStore: DataStore<UserPreference> by dataStore(
    fileName = USER_NAME,
    serializer = UserPreferenceSerializer
)
private val Context.themeDataStore: DataStore<ThemePreference> by dataStore(
    fileName = THEME_NAME,
    serializer = ThemePreferenceSerializer
)
val Context.languageDatastore: DataStore<LanguagePreference> by dataStore(
    fileName = LANGUAGE_NAME,
    serializer = LanguagePreferenceSerializer
)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideCredentialDataStore(@ApplicationContext context: Context): DataStore<CredentialPreference> {
        return context.credentialDataStore
    }

    @Singleton
    @Provides
    fun provideUserDataStore(@ApplicationContext context: Context): DataStore<UserPreference> {
        return context.userDataStore
    }

    @Singleton
    @Provides
    fun provideThemeDataStore(@ApplicationContext context: Context): DataStore<ThemePreference> {
        return context.themeDataStore
    }

    @Singleton
    @Provides
    fun provideLanguageDataStore(@ApplicationContext context: Context): DataStore<LanguagePreference> {
        return context.languageDatastore
    }

}

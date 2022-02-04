/**
 * Version for Android
 * © 2012–2017 YANDEX
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * https://yandex.com/legal/appmetrica_sdk_agreement/
 */

package com.childmathematics.android.shiftschedule;

import android.annotation.SuppressLint;
import android.util.Log;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;
import com.childmathematics.android.shiftschedule.R;
import com.yandex.mobile.ads.common.InitializationListener;
import com.yandex.mobile.ads.common.MobileAds;

public class AppYandexMetricaInit extends android.app.Application {
    String API_KEY = "9331269b-d88b-49f2-811d-feecc9d4cec3";
    Boolean AppMetricaOn = false;
    Boolean YandexMobileAdsOn = true;  // Включае не забудь об арр AppYandexMetricaInit.java AdMob.kt, MainYainterstitial.kt
    private static final String YANDEX_MOBILE_ADS_TAG = "YandexMobileAds";
    @Override
    public void onCreate() {
        super.onCreate();
        if (AppMetricaOn) {
            /* Replace API_KEY with your unique API key. Please, read official documentation how to obtain one:
             https://tech.yandex.com/metrica-mobile-sdk/doc/mobile-sdk-dg/concepts/android-initialize-docpage/
             */
                // Creating an extended library configuration.
                YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(API_KEY)
                        // Disabling sending statistics.
//                        .withStatisticsSending(false)
                        .withStatisticsSending(true)
                        .build();
                // Initializing the AppMetrica SDK.
                YandexMetrica.activate(this, config);
    //        YandexMetrica.activate(getApplicationContext(), config);
                Log.d("AppYandexMetricaInit", "__Yandex metrica init withStatisticsSending(false).----");
        }
        //0000000000000
        if (YandexMobileAdsOn) {
            MobileAds.initialize(this, new InitializationListener() {
                @Override
                public void onInitializationCompleted() {
                    Log.d(YANDEX_MOBILE_ADS_TAG, "SDK initialized");
                }
            });
        }
        //===============
    }
}
/*
Учет новых пользователей
По умолчанию в момент первого запуска приложения все пользователи определяются как новые. Если AppMetrica SDK подключается к приложению,
у которого уже есть активные пользователи, то для корректного отслеживания статистики можно настроить учет новых и старых пользователей.
Для этого необходимо инициализировать AppMetrica SDK, используя расширенную стартовую конфигурацию YandexMetricaConfig:

boolean isFirstLaunch;
// Implement logic to detect whether the app is opening for the first time.
// For example, you can check for files (settings, databases, and so on),
// which the app creates on its first launch.
if (conditions) {
    isFirstLaunch = true;
}
// Creating an extended library configuration.
YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(API_key)
        .handleFirstActivationAsUpdate(!isFirstLaunch)
        .build();
// Initializing the AppMetrica SDK.
YandexMetrica.activate(getApplicationContext(), config);

Отключение и включение отправки статистики
Если для отправки статистических данных требуется согласие пользователя, необходимо инициализировать библиотеку с отключенной опцией отправки статистики.
Для этого передайте значение false в метод withStatisticsSending(boolean value) при создании расширенной конфигурации библиотеки.

// Creating an extended library configuration.
YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(API_key)
        // Disabling sending statistics.
        .withStatisticsSending(false)
        .build();
// Initializing the AppMetrica SDK.
YandexMetrica.activate(getApplicationContext(), config);
После того как пользователь дал согласие на отправку статистики (например, в настройках приложения или в соглашении при первом открытии), включите отправку статистики
с помощью метода YandexMetrica.setStatisticsSending(Context context, boolean enabled):
// Checking the status of the boolean variable. It shows the user confirmation.
if (flag) {
    // Enabling sending statistics.
    YandexMetrica.setStatisticsSending(getApplicationContext(), true);
}
Пример оповещения
Для информирования пользователей вы можете использовать любой текст. Например:

Это приложение использует сервис аналитики AppMetrica (Яндекс.Метрика для приложений), предоставляемый компанией ООО «ЯНДЕКС», 119021, Россия, Москва, ул. Л. Толстого, 16 (далее — Яндекс) на Условиях использования сервиса.

AppMetrica анализирует данные об использовании приложения, в том числе об устройстве, на котором оно функционирует, источнике установки, составляет конверсию и статистику вашей активности
в целях продуктовой аналитики, анализа и оптимизации рекламных кампаний, а также для устранения ошибок. Собранная таким образом информация не может идентифицировать вас.

Информация об использовании вами данного приложения, собранная при помощи инструментов AppMetrica, в обезличенном виде будет передаваться Яндексу и храниться на сервере Яндекса в ЕС и Российской Федерации.
Яндекс будет обрабатывать эту информацию для предоставления статистики использования вами приложения, составления для нас отчетов о работе приложения, и предоставления других услуг.
 */

# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in NoKts_build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
# Если ваш проект использует WebView с JS, раскомментируйте следующее
# и укажите полное имя класса для интерфейса JavaScript
# сорт:
#-keepclassmembers class ???.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
# Раскомментируйте это, чтобы сохранить информацию о номере строки для
# отладка трассировки стека.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
# Если вы сохраняете информацию о номере строки, раскомментируйте ее, чтобы
# скрыть оригинальное имя исходного файла.
-renamesourcefileattribute SourceFile


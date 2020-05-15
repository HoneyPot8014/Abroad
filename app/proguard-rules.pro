# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# kotlin
-keepattributes *Annotation*

-keep class kotlin.** { *; }
-keep class org.jetbrains.** { *; }

-dontwarn kotlin.reflect.jvm.internal.**

# coroutine
-keepclassmembernames class kotlinx.** {
    volatile <fields>;
}

# databinding
-dontwarn androidx.databinding.**
-keep class androidx.databinding.** { *; }

-dontwarn com.xxxx.xx.databinding.**
-keep class com.xxxx.xx.databinding.** { *; }
-keepclassmembers class com.xxxx.xx.databinding.** { *; }

-keep class com.xxxx.xx.BindingHelpers.** { *; }
-keepclassmembers class com.xxxx.xx.BindingHelpers.** { *; }
-keep class com.xxxx.xx.DataBinderMapperImpl { *; }
-keep class * extends androidx.databinding.DataBinderMapper { *; }

# lifecycle
-keep class androidx.lifecycle.** { *; }
-keepclassmembers enum androidx.lifecycle.Lifecycle$Event {
    <fields>;
}
-keep !interface * implements androidx.lifecycle.LifecycleObserver {
}
-keep class * implements androidx.lifecycle.GeneratedAdapter {
    <init>(...);
}
-keepclassmembers class ** {
    @androidx.lifecycle.OnLifecycleEvent *;
}


# viewModel
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keepclassmembers class * extends androidx.lifecycle.ViewModel { *; }

#AndroidX AttributeSet
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#AndroidX Annotation
-keepattributes *Annotation*
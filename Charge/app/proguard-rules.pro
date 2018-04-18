# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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
#-----------------混淆配置设定------------------------------------------------------------------------
-optimizationpasses 5                                                       #指定代码压缩级别
-dontusemixedcaseclassnames                                                 #混淆时不会产生形形色色的类名
-dontskipnonpubliclibraryclasses                                            #指定不忽略非公共类库
-dontpreverify                                                             #不预校验，如果需要预校验，是-dontoptimize
-ignorewarnings                                                             #屏蔽警告
-verbose                                                                    #混淆时记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*    #优化

#（可选）避免Log打印输出
-assumenosideeffects class android.util.Log {
   public static *** v(...);
   public static *** d(...);
   public static *** i(...);
   public static *** w(...);
 }

  #-------------------Retrofit--------------------
 -dontwarn javax.annotation.**
 -dontwarn javax.inject.**
 # OkHttp3
 -dontwarn okhttp3.logging.**
 -keep class okhttp3.internal.**{*;}

  #okhttp3
  -dontwarn okhttp3.**
  -keep class okhttp3.** { *; }
  -keep interface okhttp3.** { *; }

 #okio
 -dontwarn okio.**
 -keep class okio.** { *; }
 -keep interface okio.** { *; }

 # Retrofit
 -dontwarn retrofit2.**
 -keep class retrofit2.** { *; }
 -keepattributes Signature -keepattributes Exceptions

 # 使用 proguard-android-optimize 后要添加
 -keepclasseswithmembers class * {
     @retrofit2.http.* <methods>;
 }
 -keepclasseswithmembers interface * {
     @retrofit2.* <methods>;
 }

 # Platform calls Class.forName on types which do not exist on Android to determine platform.
 -dontnote retrofit2.Platform
 # Platform used when running on RoboVM on iOS. Will not be used at runtime.
 -dontnote retrofit2.Platform$IOS$MainThreadExecutor
 # Platform used when running on Java 8 VMs. Will not be used at runtime.
 -dontwarn retrofit2.Platform$Java8

 # RxJava RxAndroid
 -dontwarn sun.misc.**
 -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
     long producerIndex;
     long consumerIndex;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode producerNode;
 }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
     rx.internal.util.atomic.LinkedQueueNode consumerNode;
 }

 # glide 的混淆代码
 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public class * extends com.bumptech.glide.module.AppGlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

 #------------#如果引用了v4或者v7包------------
 -ignorewarnings
 -dontwarn android.support.**
 -keep class android.support.v4.** { *; }
 -keep interface android.support.v4.app.** { *; }
 -keep public class * extends android.support.v4.**
 -keep public class * extends android.app.Fragment


  #-------------Fragment--------------

  -keep public class * extends android.app.AppCompatActivity
  -keep public class * extends android.app.Activity
  -keep public class * extends android.app.Application
  -keep public class * extends android.app.Fragment
  -keep public class * extends android.app.Service
  -keep public class * extends android.content.BroadcastReceiver
  -keep public class * extends android.content.ContentProvider
  -keep public class * extends android.app.backup.BackupAgentHelper
  -keep public class * extends android.preference.Preference
  -keep public class com.android.vending.licensing.ILicensingService

  -keep class com.znxk.charge.net.entity.**{*;}   #过滤掉自己编写的实体类


 -keep class com.znxk.charge.ui.views.**{*;}

 #保持自定义控件类不被混淆
 -keepclasseswithmembers class * {
  public <init>(android.content.Context, android.util.AttributeSet);
 }

 #保持自定义控件类不被混淆
 -keepclassmembers class * extends android.app.Activity {
  public void *(android.view.View);
 }

 -keep public class * extends android.view.View {
  public <init>(android.content.Context);
  public <init>(android.content.Context, android.util.AttributeSet);
  public <init>(android.content.Context, android.util.AttributeSet, int);
  public void set*(...);
 }

 #保持 Parcelable 不被混淆
 -keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
 }

 #保持 Serializable 不被混淆
 -keepnames class * implements java.io.Serializable

 #不混淆资源类
 -keepclassmembers class **.R$* {
  public static <fields>;
 }

 -keep public class **.R$*{
    public static final int *;
 }

 #gson
 #如果用用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
 -keepattributes Signature
 # Gson specific classes
 -keep class sun.misc.Unsafe { *; }
 # Application classes that will be serialized/deserialized over Gson
 -keep class com.google.gson.** { *; }
 -keep class com.google.gson.stream.** { *; }

 #eventbus
 -keepattributes *Annotation*
 -keepclassmembers class ** {
     @org.greenrobot.eventbus.Subscribe <methods>;
 }
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }
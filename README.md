[![Android Release Build](https://github.com/BreakZero/TODO-LIST-KMM/actions/workflows/android-build-release.yml/badge.svg)](https://github.com/BreakZero/TODO-LIST-KMM/actions/workflows/android-build-release.yml)

## Starting
### For iOS
> iOS already support localization, so you need to import localization file first when you set up. the file is `Localizable.xcstrings` in TODOList-iOS.
### For Android
> Cause gradle add keystore to signing app that using into DevOps, there are two options pass build progress.
- make a directory named `keystore` and create 2 files named `keystore.jks`, `keystore.properties`, properties content like below(update the value that you set)
```properties
storePassword=***
keyPassword=***
keyAlias=***
storeFile=keystore/keystore.jks
```
- delete the signingConfigs block in the Android app build.gradle.kts

## iOS Screens vs Android Screens
| iOS Screen                                                                                     | Android Screes                                                                                     |
|------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------|
| ![Settings](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/ios_settings.PNG)      | ![Settings](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/android_settings.jpg)      |
 | ![List](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/ios_list.PNG)              | ![List](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/android_list.jpg)              |
 | ![Add](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/ios_add.PNG)                | ![Add](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/android_add_sheet.jpg)          |
 | ![Detail](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/ios_detail.PNG)          | ![Detail](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/android_detail.jpg)          |
 | ![Edit](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/ios_edit.PNG)              | ![Edit](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/android_edit.jpg)              |
 | ![Delete](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/ios_delete_actions.PNG)  | ![Delete](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/android_delete_actions.jpg)  |
 | ![TimePicker](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/ios_time_picker.PNG) | ![TimePicker](https://github.com/BreakZero/TODO-LIST-KMM/blob/main/images/android_time_picker.jpg) |
[![Cards](https://user-images.githubusercontent.com/3717316/229355050-c5224a53-fd0b-49cf-9fbb-0fe8f880727b.jpg?raw=true)](https://medium.com/@jonathan.mercandalli_41381/aspect-ratio-with-reference-in-jetpack-compose-9bf9b79016ee)

[![](https://jitpack.io/v/Mercandj/compose-aspect-ratio-reference.svg)](https://jitpack.io/#Mercandj/compose-aspect-ratio-reference)
[![medium](https://img.shields.io/badge/medium-article%20👏-607D8B.svg?style=flat-square&label=medium)](https://medium.com/@jonathan.mercandalli_41381/aspect-ratio-with-reference-in-jetpack-compose-9bf9b79016ee)

# Compose - Aspect Ratio Reference

## What?

Jetpack Compose [`Modifier.aspectRatio`](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier#(androidx.compose.ui.Modifier).aspectRatio(kotlin.Float,kotlin.Boolean)) does not let you choose the dimens used to compute the ratio.

**Goal.** Be able to choose the reference of the ratio based on:

- `parent width`
- `parent height`
- `min(parent width, parent height)`
- `max(parent width, parent height)`

## How to integrate?

**Step 1.** In project root `build.gradle` or `build.gradle.kts`, add:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' } // Groovy: build.gradle
        maven(url = "https://jitpack.io") // Kotlin: build.gradle.kts
    }
}
```

**Step 2.** Add the dependency in app `build.gradle` or `build.gradle.kts`:

```groovy
dependencies {
    implementation("com.github.Mercandj:compose-aspect-ratio-reference:1.00.04")
}
```

## How to use it?

```kotlin
Box(modifier = Modifier.width(300.dp).height(200.dp)) { // Parent
    Surface( // Child
        color = Color.Red,
        modifier = Modifier
            .aspectRatioReference(
                aspectRatioWidth = 1f,
                aspectRatioHeight = 1f,
                aspectRatioReference = AspectRatioReference.MIN_PARENT_WIDTH_PARENT_HEIGHT
            )
            .align(Alignment.Center)
    ) {}
}
```

## Preview

[![preview](https://miro.medium.com/v2/resize:fit:4800/format:webp/1*pmS0_cNRQhOe5mmOMA2HkA.png)](https://medium.com/@jonathan.mercandalli_41381/aspect-ratio-with-reference-in-jetpack-compose-9bf9b79016ee)

## About

How to **support**:

- Star ⭐️ this git project 🙏
- Clap 👏 on [Medium](https://medium.com/@jonathan.mercandalli_41381/aspect-ratio-with-reference-in-jetpack-compose-9bf9b79016ee) 🙏

![Goku Genkidama](https://user-images.githubusercontent.com/3717316/229345557-4094875a-c889-4c18-8f9f-bd4c6ef754f3.jpg)

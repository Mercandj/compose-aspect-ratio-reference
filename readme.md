[![Cards](https://user-images.githubusercontent.com/3717316/229348080-6464b0b7-0954-49b7-9172-8a08251d2136.jpg)](https://medium.com/@jonathan.mercandalli_41381/aspect-ratio-with-reference-in-jetpack-compose-9bf9b79016ee)

[![](https://jitpack.io/v/Mercandj/compose-aspect-ratio-reference.svg)](https://jitpack.io/#Mercandj/compose-aspect-ratio-reference)

# Compose - Aspect Ratio Reference

## What?

Library to complete the JetPack Compose `Modifier.aspectRatio` with a reference.

**Goal.** Choose the reference to compute the ratio based on:

- `parent width`
- `parent height`
- `min(parent width, parent height)`
- `max(parent width, parent height)`

## How to integrate?

**Step 1.** In project root `build.gradle` or `build.gradle.kts`:

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' } // Groovy: build.gradle
        maven(url = "https://jitpack.io") // Kotlin: build.gradle.kts
    }
}
```

**Step 2.** Add the dependency in app `build.gradle` or `build.gradle.kts`:

```groovy
dependencies {
    implementation("com.github.Mercandj:compose-aspect-ratio-reference:1.00.00")
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
                aspectRatioReference = AspectRatioReference.MIN
            )
            .align(Alignment.Center)
    ) {}
}
```

## About

How to **give me free support** to help me maintain this lib:

- Put a star ⭐️ this git project 🙏
- Clap 👏 on [Medium](https://medium.com/@jonathan.mercandalli_41381/aspect-ratio-with-reference-in-jetpack-compose-9bf9b79016ee) 🙏

![Goku Genkidama](https://user-images.githubusercontent.com/3717316/229345557-4094875a-c889-4c18-8f9f-bd4c6ef754f3.jpg)

## Preview

[![preview](https://miro.medium.com/v2/resize:fit:4800/format:webp/1*pmS0_cNRQhOe5mmOMA2HkA.png)](https://medium.com/@jonathan.mercandalli_41381/aspect-ratio-with-reference-in-jetpack-compose-9bf9b79016ee)
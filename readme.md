![Cards](https://user-images.githubusercontent.com/3717316/229348080-6464b0b7-0954-49b7-9172-8a08251d2136.jpg)

[![](https://jitpack.io/v/Mercandj/compose-aspect-ratio-reference.svg)](https://jitpack.io/#Mercandj/compose-aspect-ratio-reference)

# Compose - Aspect Ratio Reference

## What?

Library to complete the JetPack Compose `Modifier.aspectRatio` with a reference.

**The goal.** Choose the reference to compute the ratio based on:

- `parent width`
- `parent height`
- `min(parent width, parent height)`
- `max(parent width, parent height)`

## How to integrate?

**Step 1.** In project root `build.gradle`

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' } // Groovy
        maven(url = "https://jitpack.io") // Kts
    }
}
```

**Step 2.** In your app `build.gradle`, add the dependency

```groovy
dependencies {
    implementation("com.github.Mercandj:compose-aspect-ratio-reference:0.00.01")
}
```

## About

How to **give me free support** to help me maintain this lib:

- Put a star ⭐️ this git project 🙏
- Clap 👏 on [Medium](https://medium.com/@jonathan.mercandalli_41381/aspect-ratio-with-reference-in-jetpack-compose-9bf9b79016ee) 🙏

![Goku Genkidama](https://user-images.githubusercontent.com/3717316/229345557-4094875a-c889-4c18-8f9f-bd4c6ef754f3.jpg)

## Preview

![preview](https://miro.medium.com/v2/resize:fit:4800/format:webp/1*pmS0_cNRQhOe5mmOMA2HkA.png)
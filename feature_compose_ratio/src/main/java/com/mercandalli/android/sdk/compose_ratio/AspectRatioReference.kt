package com.mercandalli.android.sdk.compose_ratio

enum class AspectRatioReference {

    /** Child width matches parent width. Child height adjusted to keep the ratio */
    PARENT_WIDTH,

    /** Child height matches parent height. Child height adjusted to keep the ratio */
    PARENT_HEIGHT,

    /** Child fits parent smallest width or height */
    MIN_PARENT_WIDTH_PARENT_HEIGHT,

    /** Child fits parent biggest width or height */
    MAX_PARENT_WIDTH_PARENT_HEIGHT
}

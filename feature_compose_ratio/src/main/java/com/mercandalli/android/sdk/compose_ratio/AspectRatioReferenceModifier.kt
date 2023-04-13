package com.mercandalli.android.sdk.compose_ratio

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.InspectorValueInfo
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import kotlin.math.roundToInt

/**
 * Size the content to match a specified aspect ratio.
 *
 * @param aspectRatioWidth the desired width positive ratio
 * @param aspectRatioHeight the desired height positive ratio
 */
// Adapted from "Modifier.aspectRatio" in the file "androidx.compose.foundation.layout.AspectRatio.kt"
// inside the library "androidx.compose.foundation:foundation-layout:1.4.0"
@Stable
fun Modifier.aspectRatioReference(
    aspectRatioWidth: Float,
    aspectRatioHeight: Float,
    aspectRatioReference: AspectRatioReference = AspectRatioReference.MIN_PARENT_WIDTH_PARENT_HEIGHT
) = then(
    AspectRatioReferenceModifier(
        aspectRatioWidth = aspectRatioWidth,
        aspectRatioHeight = aspectRatioHeight,
        aspectRatioReference = aspectRatioReference,
        debugInspectorInfo {
            name = "aspectRatioReference"
            properties["aspectRatioWidth"] = aspectRatioWidth
            properties["aspectRatioHeight"] = aspectRatioHeight
            properties["aspectRatioReference"] = aspectRatioReference
        }
    )
)

private class AspectRatioReferenceModifier(
    private val aspectRatioWidth: Float,
    private val aspectRatioHeight: Float,
    private val aspectRatioReference: AspectRatioReference,
    inspectorInfo: InspectorInfo.() -> Unit
) : LayoutModifier, InspectorValueInfo(inspectorInfo) {

    init {
        require(aspectRatioWidth > 0) { "aspectRatioWidth $aspectRatioWidth must be > 0" }
        require(aspectRatioHeight > 0) { "aspectRatioHeight $aspectRatioHeight must be > 0" }
    }

    private val aspectRatio = aspectRatioWidth / aspectRatioHeight

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val size = constraints.findSize()
        val wrappedConstraints = if (size != IntSize.Zero) {
            Constraints.fixed(size.width, size.height)
        } else {
            constraints
        }
        val placeable = measurable.measure(wrappedConstraints)
        return layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }

    override fun IntrinsicMeasureScope.minIntrinsicWidth(
        measurable: IntrinsicMeasurable,
        height: Int
    ) = if (height != Constraints.Infinity) {
        (height * aspectRatio).roundToInt()
    } else {
        measurable.minIntrinsicWidth(height)
    }

    override fun IntrinsicMeasureScope.maxIntrinsicWidth(
        measurable: IntrinsicMeasurable,
        height: Int
    ) = if (height != Constraints.Infinity) {
        (height * aspectRatio).roundToInt()
    } else {
        measurable.maxIntrinsicWidth(height)
    }

    override fun IntrinsicMeasureScope.minIntrinsicHeight(
        measurable: IntrinsicMeasurable,
        width: Int
    ) = if (width != Constraints.Infinity) {
        (width / aspectRatio).roundToInt()
    } else {
        measurable.minIntrinsicHeight(width)
    }

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(
        measurable: IntrinsicMeasurable,
        width: Int
    ) = if (width != Constraints.Infinity) {
        (width / aspectRatio).roundToInt()
    } else {
        measurable.maxIntrinsicHeight(width)
    }

    private fun Constraints.findSize(): IntSize {
        val matchPARENTWidth = when (aspectRatioReference) {
            AspectRatioReference.PARENT_WIDTH -> true
            AspectRatioReference.PARENT_HEIGHT -> false
            AspectRatioReference.MIN_PARENT_WIDTH_PARENT_HEIGHT -> maxWidth < maxHeight
            AspectRatioReference.MAX_PARENT_WIDTH_PARENT_HEIGHT -> maxWidth > maxHeight
        }
        return if (matchPARENTWidth) {
            IntSize(maxWidth, (maxWidth * aspectRatioHeight / aspectRatioWidth).roundToInt())
        } else {
            IntSize((maxHeight * aspectRatioWidth / aspectRatioHeight).roundToInt(), maxHeight)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val otherModifier = other as? AspectRatioReferenceModifier ?: return false
        return aspectRatio == otherModifier.aspectRatio &&
            aspectRatioReference == other.aspectRatioReference
    }

    override fun hashCode(): Int =
        aspectRatio.hashCode() * 31 + aspectRatioReference.hashCode()

    override fun toString(): String = "AspectRatioReferenceModifier(" +
        "aspectRatioWidth=$aspectRatioWidth, " +
        "aspectRatioHeight=$aspectRatioHeight, " +
        "ratioReference=$aspectRatioReference" +
        ")"
}

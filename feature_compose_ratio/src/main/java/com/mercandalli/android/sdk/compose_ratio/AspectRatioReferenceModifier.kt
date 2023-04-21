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
 * @param ratioWidth the desired width positive ratio
 * @param ratioHeight the desired height positive ratio
 */
// Adapted from "Modifier.aspectRatio" in the file "androidx.compose.foundation.layout.AspectRatio.kt"
// inside the library "androidx.compose.foundation:foundation-layout:1.4.0"
@Stable
fun Modifier.aspectRatioReference(
    ratioWidth: Float,
    ratioHeight: Float,
    reference: AspectRatioReference = AspectRatioReference.MIN_PARENT_WIDTH_PARENT_HEIGHT
) = then(
    AspectRatioReferenceModifier(
        ratioWidth = ratioWidth,
        ratioHeight = ratioHeight,
        reference = reference,
        debugInspectorInfo {
            name = "aspectRatioReference"
            properties["ratioWidth"] = ratioWidth
            properties["ratioHeight"] = ratioHeight
            properties["reference"] = reference
        }
    )
)

private class AspectRatioReferenceModifier(
    private val ratioWidth: Float,
    private val ratioHeight: Float,
    private val reference: AspectRatioReference,
    inspectorInfo: InspectorInfo.() -> Unit
) : LayoutModifier, InspectorValueInfo(inspectorInfo) {

    init {
        require(ratioWidth > 0) { "ratioWidth $ratioWidth must be > 0" }
        require(ratioHeight > 0) { "ratioHeight $ratioHeight must be > 0" }
    }

    private val ratio = ratioWidth / ratioHeight

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
        (height * ratio).roundToInt()
    } else {
        measurable.minIntrinsicWidth(height)
    }

    override fun IntrinsicMeasureScope.maxIntrinsicWidth(
        measurable: IntrinsicMeasurable,
        height: Int
    ) = if (height != Constraints.Infinity) {
        (height * ratio).roundToInt()
    } else {
        measurable.maxIntrinsicWidth(height)
    }

    override fun IntrinsicMeasureScope.minIntrinsicHeight(
        measurable: IntrinsicMeasurable,
        width: Int
    ) = if (width != Constraints.Infinity) {
        (width / ratio).roundToInt()
    } else {
        measurable.minIntrinsicHeight(width)
    }

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(
        measurable: IntrinsicMeasurable,
        width: Int
    ) = if (width != Constraints.Infinity) {
        (width / ratio).roundToInt()
    } else {
        measurable.maxIntrinsicHeight(width)
    }

    private fun Constraints.findSize(): IntSize {
        val matchPARENTWidth = when (reference) {
            AspectRatioReference.PARENT_WIDTH -> true
            AspectRatioReference.PARENT_HEIGHT -> false
            AspectRatioReference.MIN_PARENT_WIDTH_PARENT_HEIGHT -> maxWidth < maxHeight
            AspectRatioReference.MAX_PARENT_WIDTH_PARENT_HEIGHT -> maxWidth > maxHeight
        }
        return if (matchPARENTWidth) {
            IntSize(maxWidth, (maxWidth * ratioHeight / ratioWidth).roundToInt())
        } else {
            IntSize((maxHeight * ratioWidth / ratioHeight).roundToInt(), maxHeight)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val otherModifier = other as? AspectRatioReferenceModifier ?: return false
        return ratio == otherModifier.ratio && reference == other.reference
    }

    override fun hashCode(): Int = ratio.hashCode() * 31 + reference.hashCode()

    override fun toString(): String = "AspectRatioReferenceModifier(" +
        "ratioWidth=$ratioWidth, " +
        "ratioHeight=$ratioHeight, " +
        "reference=$reference" +
        ")"
}

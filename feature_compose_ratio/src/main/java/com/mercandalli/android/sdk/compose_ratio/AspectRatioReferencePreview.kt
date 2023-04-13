package com.mercandalli.android.sdk.compose_ratio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(widthDp = 360, heightDp = 320)
private fun Parent_200_200_Child_w3_h2_ref_height() {
    AspectRatioReferencePreviewParent(200.dp, 200.dp) { // Parent
        Surface( // Child
            color = ratioViewPreviewChildColor,
            border = BorderStroke(1.dp, ratioViewPreviewChildStrokeColor),
            modifier = Modifier
                .aspectRatioReference(
                    ratioWidth = 3f,
                    ratioHeight = 2f,
                    AspectRatioReference.PARENT_HEIGHT
                )
                .align(Alignment.Center)
        ) {}
    }
}

@Composable
@Preview(widthDp = 360, heightDp = 320)
private fun Parent_200_200_Child_w1_h2_ref_height() {
    AspectRatioReferencePreviewParent(200.dp, 200.dp) { // Parent
        Surface( // Child
            color = ratioViewPreviewChildColor,
            border = BorderStroke(1.dp, ratioViewPreviewChildStrokeColor),
            modifier = Modifier
                .aspectRatioReference(
                    ratioWidth = 1f,
                    ratioHeight = 2f,
                    AspectRatioReference.PARENT_HEIGHT
                )
                .align(Alignment.Center)
        ) {}
    }
}

@Composable
@Preview(widthDp = 360, heightDp = 320)
private fun Parent_200_300_Child_w1_h1_ref_min() {
    AspectRatioReferencePreviewParent(150.dp, 200.dp) { // Parent
        Surface( // Child
            color = ratioViewPreviewChildColor,
            border = BorderStroke(1.dp, ratioViewPreviewChildStrokeColor),
            modifier = Modifier
                .aspectRatioReference(
                    ratioWidth = 1f,
                    ratioHeight = 1f,
                    AspectRatioReference.MIN_PARENT_WIDTH_PARENT_HEIGHT
                )
                .align(Alignment.Center)
        ) {}
    }
}

@Composable
@Preview(widthDp = 360, heightDp = 320)
private fun Parent_200_300_Child_w1_h1_ref_max() {
    AspectRatioReferencePreviewParent(150.dp, 200.dp) { // Parent
        Surface( // Child
            color = ratioViewPreviewChildColor,
            border = BorderStroke(1.dp, ratioViewPreviewChildStrokeColor),
            modifier = Modifier
                .aspectRatioReference(
                    ratioWidth = 1f,
                    ratioHeight = 1f,
                    AspectRatioReference.MAX_PARENT_WIDTH_PARENT_HEIGHT
                )
                .align(Alignment.Center)
        ) {}
    }
}

private val ratioViewPreviewBackgroundColor = Color.White
private val ratioViewPreviewChildColor = Color(0xFFE7E6E6)
private val ratioViewPreviewChildTextColor = Color(0xFF979797)
private val ratioViewPreviewChildStrokeColor = Color.Black
private val ratioViewPreviewParentColor = Color(0x63EC6D6D)
private val ratioViewPreviewParentTextColor = Color(0xFFEC6D6D)
private val ratioViewPreviewParentStrokeColor = Color.Red
private val ratioViewPreviewStrokeWidth = 12.dp

@Composable
private fun AspectRatioReferencePreviewParent(
    parentWidth: Dp,
    parentHeight: Dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Surface(color = ratioViewPreviewBackgroundColor, modifier = Modifier.fillMaxSize()) {}
        Text(
            text = "Child",
            color = ratioViewPreviewChildTextColor,
            fontSize = 30.sp,
            fontWeight = FontWeight(600),
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.TopCenter)
        )
        Text(
            text = "Parent",
            color = ratioViewPreviewParentTextColor,
            fontSize = 30.sp,
            fontWeight = FontWeight(600),
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.BottomCenter)
        )
        Box(
            modifier = Modifier
                .size(width = parentWidth, height = parentHeight)
                .align(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .padding(ratioViewPreviewStrokeWidth.div(2))
                    .align(Alignment.Center)
            ) {
                content()
            }
            Surface(
                color = Color.Transparent,
                border = BorderStroke(ratioViewPreviewStrokeWidth, ratioViewPreviewParentColor),
                modifier = Modifier.fillMaxSize()
            ) {}
            Surface(
                color = Color.Transparent,
                border = BorderStroke(1.dp, ratioViewPreviewParentStrokeColor),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        ratioViewPreviewStrokeWidth
                            .div(2)
                            .plus(0.5.dp)
                    )
            ) {}
        }
    }
}

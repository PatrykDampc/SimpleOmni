package com.dampc.simpleomni.repository.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("main_resource")
    val mainResource: MainResourceResponse?,
    val title: TitleResponse,
    @SerializedName("main_text") val mainText: MainTextResponse
) {

    data class MainTextResponse(
        val paragraphs: List<ParagraphResponse>?
    ) {

        data class ParagraphResponse(
            val text: TextResponse?
        ) {

            data class TextResponse(
                val value: String?
            )

        }

    }

    data class MainResourceResponse(
        @SerializedName("image_asset")
        val imageAsset: ImageAsset?
    ) {

        data class ImageAsset(
            val id: String?
        )

    }

    data class TitleResponse(
        val value: String
    )

}
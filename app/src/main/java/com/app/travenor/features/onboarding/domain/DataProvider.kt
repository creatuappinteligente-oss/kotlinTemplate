package com.app.travenor.features.onboarding.domain

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.app.travenor.R
import com.app.travenor.features.onboarding.models.OnBoardingDetail
import com.app.travenor.ui.theme.secondary

fun getOnBoardingDetailList(): List<OnBoardingDetail> {
    return listOf(
        OnBoardingDetail(
            image = R.drawable.onboarding_1,
            titleText = getAnnotatedString(
                otherText = "Bienvenido a ",
                mainText = "nuestra app"
            ),
            detailText = "Aquí encontrarás una descripción breve y atractiva de lo que hace esta aplicación en la primera página.",
            buttonText = "Siguiente",
            descriptionOfImage = "Página 1"
        ), OnBoardingDetail(
            image = R.drawable.onboarding_2,
            titleText = getAnnotatedString(
                otherText = "Descubre ",
                mainText = "funciones increíbles"
            ),
            detailText = "Explora las características principales y cómo pueden beneficiarte en tu día a día.",
            buttonText = "Siguiente",
            descriptionOfImage = "Página 2"
        ), OnBoardingDetail(
            image = R.drawable.onboarding_3,
            titleText = getAnnotatedString(
                otherText = "Comienza tu ",
                mainText = "experiencia"
            ),
            detailText = "Estás a solo un paso de disfrutar de todo lo que hemos preparado para ti.",
            buttonText = "Empezar",
            descriptionOfImage = "Página 3"
        )
    )
}

private fun getAnnotatedString(otherText: String, mainText: String): AnnotatedString {
    return buildAnnotatedString {
        append(otherText)
        withStyle(SpanStyle(color = secondary)) {
            append(mainText)
        }
    }
}

fun getMainPageText(): AnnotatedString {
    return buildAnnotatedString {
        append("Explora el")
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("\nhermoso ")
            withStyle(SpanStyle(color = secondary)) {
                append("mundo!")
            }
        }
    }
}

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
                otherText = "Título de la ",
                mainText = "Página Uno"
            ),
            detailText = "Descripción detallada para la primera página. Este texto puede ser reemplazado fácilmente.",
            buttonText = "Siguiente",
            descriptionOfImage = "Página 1"
        ), OnBoardingDetail(
            image = R.drawable.onboarding_2,
            titleText = getAnnotatedString(
                otherText = "Título de la ",
                mainText = "Página Dos"
            ),
            detailText = "Descripción detallada para la segunda página. Este texto también es personalizable.",
            buttonText = "Siguiente",
            descriptionOfImage = "Página 2"
        ), OnBoardingDetail(
            image = R.drawable.onboarding_3,
            titleText = getAnnotatedString(
                otherText = "Título de la ",
                mainText = "Página Tres"
            ),
            detailText = "Descripción final para la tercera página, guiando al usuario al siguiente paso.",
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

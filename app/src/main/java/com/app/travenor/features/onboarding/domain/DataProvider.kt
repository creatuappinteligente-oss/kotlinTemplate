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
                otherText = "La vida es corta y el mundo es ",
                mainText = "amplio"
            ),
            detailText = "En Friends Tours and Travel, personalizamos viajes de confianza a destinos de todo el mundo.",
            buttonText = "Siguiente",
            descriptionOfImage = "Page 1"
        ), OnBoardingDetail(
            image = R.drawable.onboarding_2,
            titleText = getAnnotatedString(
                otherText = "Es un mundo grande ahí fuera, ve a ",
                mainText = "explorar"
            ),
            detailText = "Para aprovechar al máximo tu aventura, solo tienes que ir a donde más te guste. ¡Te estamos esperando!",
            buttonText = "Siguiente",
            descriptionOfImage = "Page 2"
        ), OnBoardingDetail(
            image = R.drawable.onboarding_3,
            titleText = getAnnotatedString(
                otherText = "Siempre es el viaje sobre el ",
                mainText = "destino"
            ),
            detailText = "Ya sea tu primer paso o el centésimo, estamos aquí para hacer tu viaje inolvidable.",
            buttonText = "Empezar",
            descriptionOfImage = "Page 3"
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

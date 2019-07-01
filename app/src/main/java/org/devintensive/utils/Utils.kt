package org.devintensive.utils

object Utils {

    private val mapTranslit =
        mapOf(
            "а" to "a",

            "б" to "b",

            "в" to "v",

            "г" to "g",

            "д" to "d",

            "е" to "e",

            "ё" to "e",

            "ж" to "zh",

            "з" to "z",

            "и" to "i",

            "й" to "i",

            "к" to "k",

            "л" to "l",

            "м" to "m",

            "н" to "n",

            "о" to "o",

            "п" to "p",

            "р" to "r",

            "с" to "s",

            "т" to "t",

            "у" to "u",

            "ф" to "f",

            "х" to "h",

            "ц" to "c",

            "ч" to "ch",

            "ш" to "sh",

            "щ" to "sh'",

            "ъ" to "",

            "ы" to "i",

            "ь" to "",

            "э" to "e",

            "ю" to "yu",

            "я" to "ya"
        )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return (if (firstName?.isBlank() != false) firstName else null) to (if (lastName?.isBlank() != false) lastName else null)
    }

    fun toInitials(firstName: String?, lastName: String?): String? =
        if (firstName?.isBlank() != false && lastName?.isBlank() != false) {
            null
        } else {
            "${firstName?.substring(0)} ${lastName?.substring(0)}"
        }

    fun transliteration(fullName: String?): String? = fullName?.map { mapTranslit[it.toString()] }?.joinToString()
}
package ru.skillbranch.devintensive.utils

object Utils {

    private val mapToTranslit =
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

            "я" to "ya",
            " " to "_"
        )
    private val mapFromTranslit =
        mapOf(
             "a" to "а",

             "b" to "б",

             "v" to "в",

             "g" to "г",

             "d" to "д",

             "e" to "е",

             "e" to "ё",

             "zh" to "ж",

             "z" to "з",

             "i" to "и",

             "i" to "й",

             "k" to "к",

             "l" to "л",

             "m" to "м",

             "n" to "н",

             "o" to "о",

             "p" to "п",

             "r" to "р",

             "s" to "с",

             "t" to "т",

             "u" to "у",

             "f" to "ф",

             "h" to "х",

             "c" to "ц",

             "ch" to "ч",

             "sh" to "ш",

             "sh'" to "щ",

             "" to "ъ",

             "i" to "ы",

             "e" to "э",

             "yu" to "ю",

             "ya" to "я"
        )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return (if (firstName?.isBlank() == false) firstName else null) to (if (lastName?.isBlank() == false) lastName else null)
    }

    fun toInitials(firstName: String?, lastName: String?): String? =
        if (firstName?.isBlank() != false && lastName?.isBlank() != false) {
            null
        } else {
            "${firstName?.get(0)?.toUpperCase() ?: ""}${lastName?.get(0)?.toUpperCase() ?: ""}"
        }

    fun transliteration(fullName: String?, divider: String = " "): String? {
        return fullName?.map {
            var letter: String? = it.toString()
            letter = if (it.toString() == " ") {
                divider
            } else {
                if (it.isUpperCase()) {
                    (mapToTranslit[letter?.toLowerCase()] ?: letter)?.capitalize()
                } else {
                    mapToTranslit[letter?.toLowerCase()] ?: letter
                }
            }
            letter
        }?.joinToString(separator = "")
    }
}
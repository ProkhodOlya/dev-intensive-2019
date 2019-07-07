package ru.skillbranch

import junit.framework.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

class Unit1 {
    @Test
    fun test_of_BaseMessage_AbstractFactory(){

        val vasya = User("0", "Василий", null)

        val msg_1 = BaseMessage.makeMessage(vasya,
            Chat("0"),
            Date(),
            "text",
            "any text message")
        val msg_2 = BaseMessage.makeMessage(vasya,
            Chat("1"),
            Date().add(-2, TimeUnits.HOUR),
            "image",
            "https://anyurl.com",
            true)

        assertEquals("id:0 Василий отправил сообщение \"any text message\" только что", msg_1.formatMessage())
        assertEquals("id:1 Василий получил изображение \"https://anyurl.com\" 2 часа назад", msg_2.formatMessage())
    }


    @Test
    fun test_of_parseFullName(){
        assertEquals(null to null, Utils.parseFullName(null))
        assertEquals(null to null, Utils.parseFullName(""))
        assertEquals(null to null, Utils.parseFullName(" "))
        assertEquals("John" to null, Utils.parseFullName("John"))
        assertEquals(null to null, Utils.parseFullName("    "))
        assertEquals("John" to null, Utils.parseFullName("John "))
        assertEquals("John" to null, Utils.parseFullName("  John "))
    }


    @Test
    fun test_of_transliteration() {

        println(  Utils.transliteration("Чингиз Байшурин") )
        println(  Utils.transliteration("Натан Щарянский Самуилович", "_") )
        assertEquals( "Chingiz Baishurin", Utils.transliteration("Чингиз Байшурин") )
        assertEquals( "Ivan Stereotipov", Utils.transliteration("Иван Стереотипов") )
        assertEquals( "Amazing_Petr", Utils.transliteration("Amazing Петр", divider = "_") )

        assertEquals( "Zh Zh", Utils.transliteration("Ж Ж") )
        assertEquals( "ZhZh", Utils.transliteration("ЖЖ") )
        assertEquals( "AbrAKadabra", Utils.transliteration("AbrAKadabra") )
        assertEquals( "StraNNIi NikVash'e", Utils.transliteration("СтраННЫй НикВаще") )
    }


    @Test
    fun test_of_humanizeDiff() {
        // ----- Past -----
        assertEquals("только что", Date().add(-1, TimeUnits.SECOND).humanizeDiff())
        assertEquals("несколько секунд назад", Date().add(-45, TimeUnits.SECOND).humanizeDiff())
        assertEquals("минуту назад", Date().add(-46, TimeUnits.SECOND).humanizeDiff())
        assertEquals("1 минуту назад", Date().add(-76, TimeUnits.SECOND).humanizeDiff())
        assertEquals("минуту назад", Date().add(-1, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("2 минуты назад", Date().add(-2, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("3 минуты назад", Date().add(-3, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("45 минут назад", Date().add(-45, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("час назад", Date().add(-1, TimeUnits.HOUR).humanizeDiff())
        assertEquals("1 час назад", Date().add(-76, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("2 часа назад", Date().add(-120, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("3 часа назад", Date().add(-3, TimeUnits.HOUR).humanizeDiff())
        assertEquals("4 часа назад", Date().add(-4, TimeUnits.HOUR).humanizeDiff())
        assertEquals("5 часов назад", Date().add(-5, TimeUnits.HOUR).humanizeDiff())
        assertEquals("день назад", Date().add(-26, TimeUnits.HOUR).humanizeDiff())
        assertEquals("1 день назад", Date().add(-27, TimeUnits.HOUR).humanizeDiff())
        assertEquals("4 дня назад", Date().add(-4, TimeUnits.DAY).humanizeDiff())
        assertEquals("5 дней назад", Date().add(-5, TimeUnits.DAY).humanizeDiff())
        assertEquals("360 дней назад", Date().add(-360, TimeUnits.DAY).humanizeDiff())
        assertEquals("более года назад", Date().add(-361, TimeUnits.DAY).humanizeDiff())

        // ----- Future ------
        assertEquals("через несколько секунд", Date().add(2, TimeUnits.SECOND).humanizeDiff())
        assertEquals("через минуту", Date().add(1, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через 2 минуты", Date().add(2, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через 3 минуты", Date().add(3, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через 5 минут", Date().add(5, TimeUnits.MINUTE).humanizeDiff())
        assertEquals("через час", Date().add(1, TimeUnits.HOUR).humanizeDiff())
        assertEquals("через 2 часа", Date().add(2, TimeUnits.HOUR).humanizeDiff())
        assertEquals("через 3 часа", Date().add(3, TimeUnits.HOUR).humanizeDiff())
        assertEquals("через 4 часа", Date().add(4, TimeUnits.HOUR).humanizeDiff())
        assertEquals("через 5 часов", Date().add(5, TimeUnits.HOUR).humanizeDiff())
        assertEquals("через день", Date().add(1, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 4 дня", Date().add(4, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 5 дней", Date().add(5, TimeUnits.DAY).humanizeDiff())
        assertEquals("через 148 дней", Date().add(148, TimeUnits.DAY).humanizeDiff())
        assertEquals("более чем через год", Date().add(400, TimeUnits.DAY).humanizeDiff())
    }


    @Test
    fun test_of_toInitials() {
        assertEquals("JD",  Utils.toInitials("john" ,"doe") )
        assertEquals("J",  Utils.toInitials("John", null) )
        assertEquals("D",  Utils.toInitials(null, "doe") )
        assertEquals(null,  Utils.toInitials(null, null) )
        assertEquals(null,  Utils.toInitials(" ", "") )
    }
}
package com.raju.javabaseproject.utlities

import androidx.appcompat.widget.AppCompatEditText

import com.raju.javabaseproject.R

import java.util.regex.Pattern

object ValidationUtil {

    // Regular Expression
    private val EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    private val PHONE_REGEX = "\\d{15}"
    private val PASSWORD_REGEX = ".{4,}$"

    // Error Messages
    private val REQUIRED_MSG = "Campos não podem estar vazios."
    private val EMAIL_MSG = "Formato de email inválido."
    private val PASSWORD_MSG = "A senha deve ter pelo menos 4 caracteres."
    private val PHONE_MSG = "##########"

    internal var exp = "\\b[a-zA-Z]{1,3}-\\d{4,7}\\b"

    //static final String EXP_DATE = "\\d{4}-\\d{2}-\\d{2}";
    internal val EXP_DATE = "\\d{2}-\\d{2}-\\d{4}"


    private val PATTERN_GENERIC = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")
    private val PATTERN_NUMBERS = Pattern.compile("(?=^((?!((([0]{11})|([1]{11})|([2]{11})|([3]{11})|([4]{11})|([5]{11})|([6]{11})|([7]{11})|([8]{11})|([9]{11})))).)*$)([0-9]{11})")

    // email validation
    fun isEmailAddress(editText: AppCompatEditText, required: Boolean): Boolean {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required)
    }

    // password validation
    fun isPassword(editText: AppCompatEditText, required: Boolean): Boolean {
        return isValid(editText, PASSWORD_REGEX, PASSWORD_MSG, required)
    }

    // phone number validation
    fun isPhoneNumber(editText: AppCompatEditText, required: Boolean): Boolean {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required)
    }

    // return true if the input field is valid, based on the parameter passed
    fun isValid(editText: AppCompatEditText, regex: String, errMsg: String, required: Boolean): Boolean {

        val text = editText.text.toString().trim { it <= ' ' }
        // clearing the error, if it was previously set by some other values
        editText.error = null

        // text required and editText is blank, so return false
        if (required && !hasText(editText))
            return false

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.error = errMsg
            return false
        }

        return true
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    fun hasText(editText: AppCompatEditText): Boolean {

        val text = editText.text.toString().trim { it <= ' ' }
        editText.error = null

        // length 0 means there is no text
        if (text.length == 0) {
            editText.error = REQUIRED_MSG
            return false
        }

        return true
    }

    fun isPlateNumber(text: String): Boolean {
        return if (!Pattern.matches(exp, text)) false else true
    }

    fun isPlateNumber(editText: AppCompatEditText): Boolean {
        val text = editText.text.toString().trim { it <= ' ' }
        // clearing the error, if it was previously set by some other values
        editText.error = null

        // text required and editText is blank, so return false
        if (!hasText(editText))
            return false

        // pattern doesn't match so returning false
        if (!Pattern.matches(exp, text)) {
            editText.error = "Este não é uma placa de válida."
            return false
        }

        return true
    }

    fun isValidDate(editText: AppCompatEditText): Boolean {
        val text = editText.text.toString().trim { it <= ' ' }
        // clearing the error, if it was previously set by some other values
        editText.error = null

        // text required and editText is blank, so return false
        if (!hasText(editText))
            return false

        // pattern doesn't match so returning false
        if (!Pattern.matches(EXP_DATE, text)) {
            editText.error = "Formato de data está incorreto (YYYY-MM-DD)"
            return false
        }

        return true
    }


    fun isValidCpf(editText: AppCompatEditText): Boolean {
        var cpf: String? = editText.text.toString().trim { it <= ' ' }
        if (cpf != null && PATTERN_GENERIC.matcher(cpf).matches()) {
            cpf = cpf.replace("-|\\.".toRegex(), "")
            if (cpf != null && PATTERN_NUMBERS.matcher(cpf).matches()) {
                val numbers = IntArray(11)
                for (i in 0..10) numbers[i] = Character.getNumericValue(cpf[i])
                var i: Int
                var sum = 0
                var factor = 100
                i = 0
                while (i < 9) {
                    sum += numbers[i] * factor
                    factor -= 10
                    i++
                }
                var leftover = sum % 11
                leftover = if (leftover == 10) 0 else leftover
                if (leftover == numbers[9]) {
                    sum = 0
                    factor = 110
                    i = 0
                    while (i < 10) {
                        sum += numbers[i] * factor
                        factor -= 10
                        i++
                    }
                    leftover = sum % 11
                    leftover = if (leftover == 10) 0 else leftover
                    return leftover == numbers[10]
                }
            } else {
                editText.error = "Formato de data está incorreto"
                return false
            }
        } else {
            editText.error = "Formato de data está incorreto"
            return false
        }
        return true
    }

    fun isValidCpf(cpf: String?): Boolean {
        var cpf = cpf
        if (cpf != null && PATTERN_GENERIC.matcher(cpf).matches()) {
            cpf = cpf.replace("-|\\.".toRegex(), "")
            if (cpf != null && PATTERN_NUMBERS.matcher(cpf).matches()) {
                val numbers = IntArray(11)
                for (i in 0..10) numbers[i] = Character.getNumericValue(cpf[i])
                var i: Int
                var sum = 0
                var factor = 100
                i = 0
                while (i < 9) {
                    sum += numbers[i] * factor
                    factor -= 10
                    i++
                }
                var leftover = sum % 11
                leftover = if (leftover == 10) 0 else leftover
                if (leftover == numbers[9]) {
                    sum = 0
                    factor = 110
                    i = 0
                    while (i < 10) {
                        sum += numbers[i] * factor
                        factor -= 10
                        i++
                    }
                    leftover = sum % 11
                    leftover = if (leftover == 10) 0 else leftover
                    return leftover == numbers[10]
                }
            }
        }
        return true
    }
}

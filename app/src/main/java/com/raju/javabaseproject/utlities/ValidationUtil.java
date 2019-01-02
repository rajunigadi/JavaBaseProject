package com.raju.javabaseproject.utlities;

import android.support.v7.widget.AppCompatEditText;

import com.raju.javabaseproject.R;

import java.util.regex.Pattern;

public class ValidationUtil {

    // Regular Expression
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{15}";
    private static final String PASSWORD_REGEX = ".{4,}$";

    // Error Messages
    private static final String REQUIRED_MSG = "Campos não podem estar vazios.";
    private static final String EMAIL_MSG = "Formato de email inválido.";
    private static final String PASSWORD_MSG = "A senha deve ter pelo menos 4 caracteres.";
    private static final String PHONE_MSG = "##########";

    // email validation
    public static boolean isEmailAddress(AppCompatEditText editText, boolean required) {
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    // password validation
    public static boolean isPassword(AppCompatEditText editText, boolean required) {
        return isValid(editText, PASSWORD_REGEX, PASSWORD_MSG, required);
    }

    // phone number validation
    public static boolean isPhoneNumber(AppCompatEditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(AppCompatEditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if (required && !hasText(editText))
            return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        }

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(AppCompatEditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }

    static String exp = "\\b[a-zA-Z]{1,3}-\\d{4,7}\\b";

    public static boolean isPlateNumber(String text) {
        if(!Pattern.matches(exp, text))
            return false;
        return true;
    }

    public static boolean isPlateNumber(AppCompatEditText editText) {
        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if (!hasText(editText))
            return false;

        // pattern doesn't match so returning false
        if (!Pattern.matches(exp, text)) {
            editText.setError("Este não é uma placa de válida.");
            return false;
        }

        return true;
    }

    //static final String EXP_DATE = "\\d{4}-\\d{2}-\\d{2}";
    static final String EXP_DATE = "\\d{2}-\\d{2}-\\d{4}";
    public static boolean isValidDate(AppCompatEditText editText) {
        String text = editText.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        editText.setError(null);

        // text required and editText is blank, so return false
        if (!hasText(editText))
            return false;

        // pattern doesn't match so returning false
        if (!Pattern.matches(EXP_DATE, text)) {
            editText.setError("Formato de data está incorreto (YYYY-MM-DD)");
            return false;
        }

        return true;
    }




    private static Pattern PATTERN_GENERIC = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
    private static Pattern PATTERN_NUMBERS = Pattern.compile("(?=^((?!((([0]{11})|([1]{11})|([2]{11})|([3]{11})|([4]{11})|([5]{11})|([6]{11})|([7]{11})|([8]{11})|([9]{11})))).)*$)([0-9]{11})");


    public static boolean isValidCpf(AppCompatEditText editText) {
        String cpf = editText.getText().toString().trim();
        if (cpf != null && PATTERN_GENERIC.matcher(cpf).matches()) {
            cpf = cpf.replaceAll("-|\\.", "");
            if (cpf != null && PATTERN_NUMBERS.matcher(cpf).matches()) {
                int[] numbers = new int[11];
                for (int i = 0; i < 11; i++) numbers[i] = Character.getNumericValue(cpf.charAt(i));
                int i;
                int sum = 0;
                int factor = 100;
                for (i = 0; i < 9; i++) {
                    sum += numbers[i] * factor;
                    factor -= 10;
                }
                int leftover = sum % 11;
                leftover = leftover == 10 ? 0 : leftover;
                if (leftover == numbers[9]) {
                    sum = 0;
                    factor = 110;
                    for (i = 0; i < 10; i++) {
                        sum += numbers[i] * factor;
                        factor -= 10;
                    }
                    leftover = sum % 11;
                    leftover = leftover == 10 ? 0 : leftover;
                    return leftover == numbers[10];
                }
            } else {
                editText.setError("Formato de data está incorreto");
                return false;
            }
        } else {
            editText.setError("Formato de data está incorreto");
            return false;
        }
        return true;
    }

    public static boolean isValidCpf(String cpf) {
        if (cpf != null && PATTERN_GENERIC.matcher(cpf).matches()) {
            cpf = cpf.replaceAll("-|\\.", "");
            if (cpf != null && PATTERN_NUMBERS.matcher(cpf).matches()) {
                int[] numbers = new int[11];
                for (int i = 0; i < 11; i++) numbers[i] = Character.getNumericValue(cpf.charAt(i));
                int i;
                int sum = 0;
                int factor = 100;
                for (i = 0; i < 9; i++) {
                    sum += numbers[i] * factor;
                    factor -= 10;
                }
                int leftover = sum % 11;
                leftover = leftover == 10 ? 0 : leftover;
                if (leftover == numbers[9]) {
                    sum = 0;
                    factor = 110;
                    for (i = 0; i < 10; i++) {
                        sum += numbers[i] * factor;
                        factor -= 10;
                    }
                    leftover = sum % 11;
                    leftover = leftover == 10 ? 0 : leftover;
                    return leftover == numbers[10];
                }
            }
        }
        return true;
    }
}

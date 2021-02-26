package test.kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.logic.RegisterLogic;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterLogicTest {

    @Test
    public void testIsValidEmail() {
        String validEmail = "test@example.com";
        assertTrue(RegisterLogic.isEmailValid(validEmail));
    }

    @Test
    public void testIsValidEmailIfEmailWithDot() {
        String validEmailWithDot = "adam.jones@test.kz";
        assertTrue(RegisterLogic.isEmailValid(validEmailWithDot));
    }

    @Test
    public void testIsNotValidEmail() {
        String notValidEmail = "not.valid@example@com";
        assertFalse(RegisterLogic.isEmailValid(notValidEmail));
    }

    @Test
    public void testIsNotValidEmailIfNull() {
        assertFalse(RegisterLogic.isEmailValid(null));
    }

    @Test
    public void testIsNotValidEmailIfEmpty() {
        assertFalse(RegisterLogic.isEmailValid(""));
    }

    @Test
    public void testIsValidName() {
        String validName = "Adam";
        assertTrue(RegisterLogic.isNameValid(validName));
    }

    @Test
    public void testIsValidNameIfCyrillicCharacters() {
        String validNameWithCyrillicCharacters = "Иван";
        assertTrue(RegisterLogic.isNameValid(validNameWithCyrillicCharacters));
    }

    @Test
    public void testIsValidNameIfDoubleName() {
        String validDoubleName = "Helen-Maria";
        assertTrue(RegisterLogic.isNameValid(validDoubleName));
    }

    @Test
    public void testIsValidNameIfApostrophe() {
        String validNameWithApostrophe = "O'Neill";
        assertTrue(RegisterLogic.isNameValid(validNameWithApostrophe));
    }

    @Test
    public void testIsNotValidNameIfNumbers() {
        String notValidNameWithNumbers = "John123";
        assertFalse(RegisterLogic.isNameValid(notValidNameWithNumbers));
    }

    @Test
    public void testIsNotValidNameIfSpecialChars() {
        String notValidNameWithSpecialChars = "Smi@!#th";
        assertFalse(RegisterLogic.isNameValid(notValidNameWithSpecialChars));
    }

    @Test
    public void testIsNotValidNameIfNull() {
        assertFalse(RegisterLogic.isNameValid(null));
    }

    @Test
    public void testIsNotValidNameIfEmpty() {
        assertFalse(RegisterLogic.isNameValid(""));
    }

    @Test
    public void testIsValidPassword() {
        String validPassword = "aDmin123@";
        assertTrue(RegisterLogic.isPasswordValid(validPassword));
    }

    @Test
    public void testIsNotValidPasswordIfLessThanEightChars() {
        String notValidPasswordWithLessThanEightChars = "Pass@1";
        assertFalse(RegisterLogic.isPasswordValid(notValidPasswordWithLessThanEightChars));
    }

    @Test
    public void testIsNotValidPasswordIfNoSpecialChars() {
        String notValidPasswordWithoutSpecialChars = "TestPassword123";
        assertFalse(RegisterLogic.isPasswordValid(notValidPasswordWithoutSpecialChars));
    }

    @Test
    public void testIsNotValidPasswordIfNull() {
        assertFalse(RegisterLogic.isPasswordValid(null));
    }

    @Test
    public void testIsNotValidPasswordIfEmpty() {
        assertFalse(RegisterLogic.isPasswordValid(""));
    }

    @Test
    public void testIsConfirmedPassword() {
        String password = "Admin123!";
        String confirmedPassword = "Admin123!";
        assertTrue(RegisterLogic.isConfirmedPasswordCorrect(password, confirmedPassword));
    }

    @Test
    public void testIsNotConfirmedPassword() {
        String password = "TestPassword123!";
        String confirmedPassword = "TestPassword123@";
        assertFalse(RegisterLogic.isConfirmedPasswordCorrect(password, confirmedPassword));
    }

    @Test
    public void testIsNotConfirmedPasswordIfNull() {
        String password = "Qwerty12!";
        String confirmedPassword = null;
        assertFalse(RegisterLogic.isConfirmedPasswordCorrect(password, confirmedPassword));
    }

    @Test
    public void testIsNotConfirmedPasswordIfEmpty() {
        String password = "";
        String confirmedPassword = "proJect12@";
        assertFalse(RegisterLogic.isConfirmedPasswordCorrect(password, confirmedPassword));
    }

    @Test
    public void testIsValidPhoneNumber() {
        String validPhoneNumber = "+77018973274";
        assertTrue(RegisterLogic.isPhoneNumberValid(validPhoneNumber));
    }

    @Test
    public void testIsNotValidPhoneNumber() {
        String notValidPhoneNumber = "1234567890";
        assertFalse(RegisterLogic.isPhoneNumberValid(notValidPhoneNumber));
    }

    @Test
    public void testIsNotValidPhoneNumberIfNull() {
        assertFalse(RegisterLogic.isPhoneNumberValid(null));
    }

    @Test
    public void testIsNotValidPhoneNumberIfEmpty() {
        assertFalse(RegisterLogic.isPhoneNumberValid(""));
    }

    @Test
    public void testIsValidBirthday() {
        Date validBirthday = Date.valueOf("2000-05-31");
        assertTrue(RegisterLogic.isBirthdayValid(validBirthday));
    }

    @Test
    public void testIsNotValidBirthday() {
        Date notValidBirthday = Date.valueOf("2030-01-01");
        assertFalse(RegisterLogic.isBirthdayValid(notValidBirthday));
    }

    @Test
    public void testIsValidBirthdayIfNull() {
        assertTrue(RegisterLogic.isBirthdayValid(null));
    }
}
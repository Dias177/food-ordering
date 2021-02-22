package test.kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.logic.RegisterLogic;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterLogicTest {

    @Test
    public void testIsValidEmail() {
        String validEmail = "test@example.com";
        assertTrue(RegisterLogic.isValidEmail(validEmail));
    }

    @Test
    public void testIsValidEmailIfEmailWithDot() {
        String validEmailWithDot = "adam.jones@test.kz";
        assertTrue(RegisterLogic.isValidEmail(validEmailWithDot));
    }

    @Test
    public void testIsNotValidEmail() {
        String notValidEmail = "not.valid@example@com";
        assertFalse(RegisterLogic.isValidEmail(notValidEmail));
    }

    @Test
    public void testIsNotValidEmailIfNull() {
        assertFalse(RegisterLogic.isValidEmail(null));
    }

    @Test
    public void testIsNotValidEmailIfEmpty() {
        assertFalse(RegisterLogic.isValidEmail(""));
    }

    @Test
    public void testIsValidName() {
        String validName = "Adam";
        assertTrue(RegisterLogic.isValidName(validName));
    }

    @Test
    public void testIsValidNameIfCyrillicCharacters() {
        String validNameWithCyrillicCharacters = "Иван";
        assertTrue(RegisterLogic.isValidName(validNameWithCyrillicCharacters));
    }

    @Test
    public void testIsValidNameIfDoubleName() {
        String validDoubleName = "Helen-Maria";
        assertTrue(RegisterLogic.isValidName(validDoubleName));
    }

    @Test
    public void testIsValidNameIfApostrophe() {
        String validNameWithApostrophe = "O'Neill";
        assertTrue(RegisterLogic.isValidName(validNameWithApostrophe));
    }

    @Test
    public void testIsNotValidNameIfNumbers() {
        String notValidNameWithNumbers = "John123";
        assertFalse(RegisterLogic.isValidName(notValidNameWithNumbers));
    }

    @Test
    public void testIsNotValidNameIfSpecialChars() {
        String notValidNameWithSpecialChars = "Smi@!#th";
        assertFalse(RegisterLogic.isValidName(notValidNameWithSpecialChars));
    }

    @Test
    public void testIsNotValidNameIfNull() {
        assertFalse(RegisterLogic.isValidName(null));
    }

    @Test
    public void testIsNotValidNameIfEmpty() {
        assertFalse(RegisterLogic.isValidName(""));
    }

    @Test
    public void testIsValidPassword() {
        String validPassword = "aDmin123@";
        assertTrue(RegisterLogic.isValidPassword(validPassword));
    }

    @Test
    public void testIsNotValidPasswordIfLessThanEightChars() {
        String notValidPasswordWithLessThanEightChars = "Pass@1";
        assertFalse(RegisterLogic.isValidPassword(notValidPasswordWithLessThanEightChars));
    }

    @Test
    public void testIsNotValidPasswordIfNoSpecialChars() {
        String notValidPasswordWithoutSpecialChars = "TestPassword123";
        assertFalse(RegisterLogic.isValidPassword(notValidPasswordWithoutSpecialChars));
    }

    @Test
    public void testIsNotValidPasswordIfNull() {
        assertFalse(RegisterLogic.isValidPassword(null));
    }

    @Test
    public void testIsNotValidPasswordIfEmpty() {
        assertFalse(RegisterLogic.isValidPassword(""));
    }

    @Test
    public void testIsConfirmedPassword() {
        String password = "Admin123!";
        String confirmedPassword = "Admin123!";
        assertTrue(RegisterLogic.isCorrectConfirmedPassword(password, confirmedPassword));
    }

    @Test
    public void testIsNotConfirmedPassword() {
        String password = "TestPassword123!";
        String confirmedPassword = "TestPassword123@";
        assertFalse(RegisterLogic.isCorrectConfirmedPassword(password, confirmedPassword));
    }

    @Test
    public void testIsNotConfirmedPasswordIfNull() {
        String password = "Qwerty12!";
        String confirmedPassword = null;
        assertFalse(RegisterLogic.isCorrectConfirmedPassword(password, confirmedPassword));
    }

    @Test
    public void testIsNotConfirmedPasswordIfEmpty() {
        String password = "";
        String confirmedPassword = "proJect12@";
        assertFalse(RegisterLogic.isCorrectConfirmedPassword(password, confirmedPassword));
    }

    @Test
    public void testIsValidPhoneNumber() {
        String validPhoneNumber = "+77018973274";
        assertTrue(RegisterLogic.isValidPhoneNumber(validPhoneNumber));
    }

    @Test
    public void testIsNotValidPhoneNumber() {
        String notValidPhoneNumber = "1234567890";
        assertFalse(RegisterLogic.isValidPhoneNumber(notValidPhoneNumber));
    }

    @Test
    public void testIsNotValidPhoneNumberIfNull() {
        assertFalse(RegisterLogic.isValidPhoneNumber(null));
    }

    @Test
    public void testIsNotValidPhoneNumberIfEmpty() {
        assertFalse(RegisterLogic.isValidPhoneNumber(""));
    }

    @Test
    public void testIsValidBirthday() {
        Date validBirthday = Date.valueOf("2000-05-31");
        assertTrue(RegisterLogic.isValidBirthday(validBirthday));
    }

    @Test
    public void testIsNotValidBirthday() {
        Date notValidBirthday = Date.valueOf("2030-01-01");
        assertFalse(RegisterLogic.isValidBirthday(notValidBirthday));
    }

    @Test
    public void testIsValidBirthdayIfNull() {
        assertTrue(RegisterLogic.isValidBirthday(null));
    }
}
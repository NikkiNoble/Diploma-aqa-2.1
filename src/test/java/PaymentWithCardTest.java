
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentWithCardTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @AfterEach
    void clearTable() throws SQLException {
        DBInteractions.clearStatus();
        DBInteractions.clearStatusCredit();
    }

    @Test
    void shouldPayForTravelWithDebitCardStatusApproved() throws SQLException {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.validPayWithDebitCard1();
        cardPayment.findSuccessElement();
        String actualPaymentStatus = DBInteractions.getStatus();
        assertEquals(DataHelper.expectedPaymentStatusApproved, actualPaymentStatus);
    }
    @Test
    void shouldPayForTravelWithDebitCardStatusDeclined() throws SQLException {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.inValidPayWithDebitCard2();
        String actualPaymentStatus = DBInteractions.getStatus();
        assertEquals(DataHelper.expectedPaymentStatusDeclined, actualPaymentStatus);
        cardPayment.elementShouldNotBeVisible();
        cardPayment.findErrorElement();
    }
    @Test
    void shouldPayForTravelWithCreditCardStatusApproved() throws SQLException {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.validPayWithCreditCard1();
        cardPayment.findSuccessElement();
        String actualPaymentStatus = DBInteractions.getStatusCredit();
        assertEquals(DataHelper.expectedPaymentStatusApproved, actualPaymentStatus);
    }
    @Test
    void shouldPayForTravelWithCreditCardStatusDeclined() throws SQLException {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.invalidPayWithCreditCard2();
        String actualPaymentStatus = DBInteractions.getStatusCredit();
        assertEquals(DataHelper.expectedPaymentStatusDeclined, actualPaymentStatus);
        cardPayment.elementShouldNotBeVisible();
        cardPayment.findErrorElement();
    }
    @Test
    void shouldNotSendFormWithCardFieldsEmpty() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithEmptyFieldCardNumber();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithMonthEmpty() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithEmptyFieldMonth();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithYearEmpty() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithEmptyFieldYear();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithOwnerEmpty() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithEmptyFieldOwner();
        cardPayment.findEmptyField();
    }
    @Test
    void shouldNotSendFormWithCVCEmpty() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithEmptyFieldCVC();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithoutFullOrWrongCardNumber() throws SQLException {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithNotFullCardNumber();
        cardPayment.findInvalidEnter();
        cardPayment.sendFormWithWrongCardNumber();
        cardPayment.findErrorElement();
        cardPayment.findBothNotifications();

    }
    @Test
    void shouldNotSendFormWithWrongMonth() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithWrongMonth();
        cardPayment.findWrongExpirationDate();
    }
    @Test
    void shouldNotSendFormWithExpiredDate() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithPreviousMonth();
        cardPayment.findWrongExpirationDate();
    }
    @Test
    void shouldNotSendFormWithWrongFormatMonth() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithWrongFormatMonth();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithWrongFormatYear() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithWrongFormatYear();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithNullMonth() throws SQLException {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithNullMonth();
        cardPayment.sendingElementShouldNotBeVisible();
        cardPayment.elementShouldNotBeVisible();
        cardPayment.findInvalidEnter();

    }
    @Test
    void shouldNotSendFormWithExpiredYear() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithPreviousYear();
        cardPayment.findExpiredDate();
    }
    @Test
    void shouldNotSendFormWithoutFullCVC() {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithWrongCVC();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormIfOwnersNameConsistsOfRandomSymbols() throws SQLException {
        DataHelper.openPage();
        ApplicationCardPayment cardPayment = new ApplicationCardPayment();
        cardPayment.sendFormWithRandomSymbolsInsteadOfName();
        cardPayment.sendingElementShouldNotBeVisible();
        cardPayment.elementShouldNotBeVisible();
        cardPayment.findInvalidEnter();

    }
}

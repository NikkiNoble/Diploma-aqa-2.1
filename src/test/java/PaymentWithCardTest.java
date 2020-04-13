import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
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
    @BeforeEach
    void openPage() {
        DataHelper.openPage();
    }
    private ApplicationCardPayment cardPayment = new ApplicationCardPayment();
    @Test
    void shouldPayForTravelWithDebitCardStatusApproved() throws SQLException {
        cardPayment.validPayWithDebitCard1();
        cardPayment.findSuccessElement();
        String actualPaymentStatus = DBInteractions.getStatus();
        assertEquals(DataHelper.expectedPaymentStatusApproved, actualPaymentStatus);
    }
    @Test
    void shouldPayForTravelWithDebitCardStatusDeclined() throws SQLException {
        cardPayment.inValidPayWithDebitCard2();
        String actualPaymentStatus = DBInteractions.getStatus();
        assertEquals(DataHelper.expectedPaymentStatusDeclined, actualPaymentStatus);
        cardPayment.elementShouldNotBeVisible();
        cardPayment.findErrorElement();
    }
    @Test
    void shouldPayForTravelWithCreditCardStatusApproved() throws SQLException {
        cardPayment.validPayWithCreditCard1();
        cardPayment.findSuccessElement();
        String actualPaymentStatus = DBInteractions.getStatusCredit();
        assertEquals(DataHelper.expectedPaymentStatusApproved, actualPaymentStatus);
    }
    @Test
    void shouldPayForTravelWithCreditCardStatusDeclined() throws SQLException {
        cardPayment.invalidPayWithCreditCard2();
        String actualPaymentStatus = DBInteractions.getStatusCredit();
        assertEquals(DataHelper.expectedPaymentStatusDeclined, actualPaymentStatus);
        cardPayment.elementShouldNotBeVisible();
        cardPayment.findErrorElement();
    }
    @Test
    void shouldNotSendFormWithCardFieldsEmpty() {
        cardPayment.sendFormWithEmptyFieldCardNumber();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithMonthEmpty() {
        cardPayment.sendFormWithEmptyFieldMonth();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithYearEmpty() {
        cardPayment.sendFormWithEmptyFieldYear();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithOwnerEmpty() {
        cardPayment.sendFormWithEmptyFieldOwner();
        cardPayment.findEmptyField();
    }
    @Test
    void shouldNotSendFormWithCVCEmpty() {
        cardPayment.sendFormWithEmptyFieldCVC();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithoutFullOrWrongCardNumber() {
        cardPayment.sendFormWithNotFullCardNumber();
        cardPayment.findInvalidEnter();
        cardPayment.sendFormWithWrongCardNumber();
        cardPayment.findErrorElement();
        cardPayment.findBothNotifications();
    }
    @Test
    void shouldNotSendFormWithWrongMonth() {
        cardPayment.sendFormWithWrongMonth();
        cardPayment.findWrongExpirationDate();
    }
    @Test
    void shouldNotSendFormWithExpiredDate() {
        cardPayment.sendFormWithPreviousMonth();
        cardPayment.findWrongExpirationDate();
    }
    @Test
    void shouldNotSendFormWithWrongFormatMonth() {
        cardPayment.sendFormWithWrongFormatMonth();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithWrongFormatYear() {
        cardPayment.sendFormWithWrongFormatYear();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithNullMonth() {
        cardPayment.sendFormWithNullMonth();
        cardPayment.sendingElementShouldNotBeVisible();
        cardPayment.elementShouldNotBeVisible();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormWithExpiredYear() {
        cardPayment.sendFormWithPreviousYear();
        cardPayment.findExpiredDate();
    }
    @Test
    void shouldNotSendFormWithoutFullCVC() {
        cardPayment.sendFormWithWrongCVC();
        cardPayment.findInvalidEnter();
    }
    @Test
    void shouldNotSendFormIfOwnersNameConsistsOfRandomSymbols() {
        cardPayment.sendFormWithRandomSymbolsInsteadOfName();
        cardPayment.sendingElementShouldNotBeVisible();
        cardPayment.elementShouldNotBeVisible();
        cardPayment.findInvalidEnter();
    }
}

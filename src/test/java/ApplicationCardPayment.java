import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class ApplicationCardPayment {
    private static ElementsCollection button =  $$("button");
    private SelenideElement inputCardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement inputMonth = $("[placeholder=\"08\"]");
    private SelenideElement inputYear = $("[placeholder=\"22\"]");
    private SelenideElement inputOwner = $("input.input__control[type='text'][view='default'][autocomplete='on'][value='']");
    private SelenideElement inputCVC = $("[placeholder=\"999\"]");
    private SelenideElement notification = $(".notification");
    private SelenideElement errorNotification = $(".notification_status_error .icon_name_close");
    private SelenideElement successNotification = $(".notification_status_ok");

    public void findSuccessElement() {
        $(withText("Успешно")).waitUntil(visible, 15000).shouldBe(visible);
    }
    public void findErrorElement() {
        $(withText("Ошибка")).waitUntil(visible,15000).shouldBe(visible);
    }
    public void elementShouldNotBeVisible() {
        $(withText("Успешно")).shouldNotBe(visible);
    }
    public void sendingElementShouldNotBeVisible() {
        $(withText("Отправляем запрос в Банк")).shouldNotBe(visible);
    }

    public void findInvalidEnter() {
        $(withText("Неверный формат")).shouldBe(visible);
    }
    public void findEmptyField() {
        $(withText("Поле обязательно для заполнения")).shouldBe(visible);
    }
    public void findWrongExpirationDate() {
        $(withText("Неверно указан срок действия карты")).shouldBe(visible);
    }
    public void findExpiredDate() {
        $(withText("Истёк срок действия карты")).shouldBe(visible);
    }
    public static void clickButton() {
        button.find(exactText("Купить")).click();
    }
    public static void clickButtonCredit() {
        button.find(exactText("Купить в кредит")).click();
    }
    public static void clickButtonContinue() {
        button.find(exactText("Продолжить")).click();
    }

    public void validPayWithDebitCard1() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void inValidPayWithDebitCard2() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getCardNumber2());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
        notification.waitUntil(visible, 15000);

    }
    public void validPayWithCreditCard1() {
        ApplicationCardPayment.clickButtonCredit();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void invalidPayWithCreditCard2() {
        ApplicationCardPayment.clickButtonCredit();
        inputCardNumber.setValue(DataHelper.getCardNumber2());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
        notification.waitUntil(visible, 15000);

    }
    public void sendFormWithEmptyFieldCardNumber() {
        ApplicationCardPayment.clickButton();
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithEmptyFieldMonth() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputYear.setValue(DataGenerator.shouldReturnRandomYear());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithEmptyFieldYear() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithEmptyFieldOwner() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYear());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithEmptyFieldCVC() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYear());
        inputOwner.setValue(DataGenerator.generateName());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithNotFullCardNumber() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getWrongNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithWrongCardNumber() {
        inputCardNumber.doubleClick().doubleClick().sendKeys(BACK_SPACE);
        inputCardNumber.setValue(DataHelper.getWrongNumber2());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void findBothNotifications() {
        errorNotification.click();
        successNotification.shouldNotBe(visible);
    }
    public void sendFormWithWrongMonth() {
        ApplicationCardPayment.clickButtonCredit();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomNumber());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithPreviousMonth() {
        ApplicationCardPayment.clickButtonCredit();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnPreviousMonth());
        inputYear.setValue(DataGenerator.shouldReturnCurrentYear());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithWrongFormatMonth() {
        ApplicationCardPayment.clickButtonCredit();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomNumeral());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithWrongFormatYear() {
        ApplicationCardPayment.clickButtonCredit();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomNumeral());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithNullMonth() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue("00");
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithPreviousYear() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearMinus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithWrongCVC() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue(DataGenerator.generateName());
        inputCVC.setValue(DataGenerator.shouldReturnRandomNumber());
        ApplicationCardPayment.clickButtonContinue();
    }
    public void sendFormWithRandomSymbolsInsteadOfName() {
        ApplicationCardPayment.clickButton();
        inputCardNumber.setValue(DataHelper.getCardNumber1());
        inputMonth.setValue(DataGenerator.shouldReturnRandomMonth());
        inputYear.setValue(DataGenerator.shouldReturnRandomYearPlus());
        inputOwner.setValue("&?|#6%");
        inputCVC.setValue(DataGenerator.generateCVC());
        ApplicationCardPayment.clickButtonContinue();
    }


}

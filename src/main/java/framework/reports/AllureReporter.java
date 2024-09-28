package framework.reports;


import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class AllureReporter {
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }

    public void addAttachment(String name, String content) {
        Allure.addAttachment(name, content);
    }
}
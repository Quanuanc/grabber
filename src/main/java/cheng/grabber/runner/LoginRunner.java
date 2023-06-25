package cheng.grabber.runner;

import cheng.grabber.util.Utils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

//@Component
public class LoginRunner implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final WebDriver webDriver;

    public LoginRunner(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private void login() {
        webDriver.get("https://login.zhipin.com/");
        int seconds = 15;
        log.info("You have {} seconds to login.", seconds);
        Utils.sleep(seconds * 1000);
        webDriver.navigate().refresh();
    }

    @Override
    public void run(String... args) {
        login();
    }
}

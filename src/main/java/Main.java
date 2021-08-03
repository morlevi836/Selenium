import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ChromeDriver driver;
    static String userName, password;
    static WebElement personalInfo, userID, userPassword, loginButton, moodleSystem, selectedCourse, openMenu, logoutFromMoodle, logoutFromSystem;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        System.out.println("Enter your username:");
        userName = scanner.next();
        System.out.println("Enter your password:");
        password = scanner.next();
        login(userName, password);
        Thread.sleep(3000);
        printCoursesList();
        Thread.sleep(2000);
        logout();
    }

    private static void login(String userName, String password) {
        driver = new ChromeDriver();
        driver.get("https://www.aac.ac.il/");
        driver.manage().window().maximize();
        personalInfo = driver.findElement(By.cssSelector("a[href='https://portal.aac.ac.il']"));
        personalInfo.click();
        userID = driver.findElement(By.id("Ecom_User_ID"));
        userID.sendKeys(userName);
        userPassword = driver.findElement(By.id("Ecom_Password"));
        userPassword.sendKeys(password);
        loginButton = driver.findElement(By.cssSelector("#wp-submit"));
        loginButton.click();
        moodleSystem = driver.findElement(By.cssSelector("a[href='https://moodle.aac.ac.il/login/index.php']"));
        moodleSystem.click();
    }

    private static void printCoursesList() {
        List<WebElement> listOfCourses = driver.findElements(By.cssSelector("a[class = 'aalink coursename']"));
        System.out.println("List of your courses:");
        for (int i = 0; i < listOfCourses.size(); i++)
            System.out.println((i + 1) + ") " + listOfCourses.get(i).getText() + "\n");
        System.out.println("Which course would you like to see?");
        int course = scanner.nextInt();
        selectedCourse = listOfCourses.get(course - 1);
        selectedCourse.click();
    }

    private static void logout() {
        openMenu = driver.findElement(By.cssSelector("#action-menu-toggle-1"));
        openMenu.click();
        logoutFromMoodle = driver.findElement(By.cssSelector("a[data-title = 'logout,moodle']"));
        logoutFromMoodle.click();
        logoutFromSystem = driver.findElement(By.cssSelector("a[href='https://portal.aac.ac.il/AGLogout']"));
        logoutFromSystem.click();
    }
}

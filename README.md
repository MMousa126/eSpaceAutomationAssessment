# 🛍️ E-commerce Website Automated Testing

## 🧪 Overview
This project provides automated functional and negative testing for the [Magento demo e-commerce site](https://magento.softwaretestingboard.com/men/tops-men/hoodies-and-sweatshirts-men.html), built using Java, Selenium WebDriver, TestNG, Maven, and Allure Reports. The automation framework follows the Page Object Model (POM) design pattern.

## 🧪 Task QA Sprints
- 📄 [Sprints QA Task (Google Drive)](https://drive.google.com/file/d/1iW2JDaRqdUMcfiGf7ADmQuLXvnJxjZ5g/view?usp=drive_link)


## ✅ Features Covered
- Product Search (Valid & Invalid)
- Add to Cart (Valid & Missing Required Options)
- Checkout Process

## 🧰 Tech Stack
- Java
- Selenium WebDriver
- TestNG
- Maven
- Allure Reports
- Page Object Model (POM)

## 📂 Documentation
- 📄 [Test Plan (Google Drive)](https://docs.google.com/spreadsheets/d/16104FUcuO-SfACwaXCLCCcO6D4KVzueX/edit?usp=drive_link&ouid=104520795720826046842&rtpof=true&sd=true)
- 📄 [SQL injection & Bug Report (Google Drive)](https://docs.google.com/spreadsheets/d/1Kq4HMT-Obdu1gUXEq9Qu5yjan2b69gtk/edit?usp=drive_link&ouid=104520795720826046842&rtpof=true&sd=true)
- 📄 [Generated Allure Report (Google Drive)](https://drive.google.com/drive/folders/1D6qvHpBtF6hseC89GpPLOFem4pHSrOI0?usp=drive_link)

## 📂 Evidence 
- 📄 [Allure Report Screenshoots (Google Drive)](https://drive.google.com/drive/folders/1-ozF6VDno5fYFTZZi147zQZWi16VrPqg?usp=drive_link)
- 📄 [SQL injection Evidence (Google Drive)](https://drive.google.com/file/d/11tVj14_gPeJvOobJztETXiQI29aP09Sy/view?usp=drive_link)

## 🧰 Note
- For XSS has been tried but it is returning the expected result  
- 📄 [XSS injection (Google Drive)](https://drive.google.com/file/d/1A428aza0pXhCFJHbHnzCgn6P6FmptGf7/view?usp=drive_link)

## 🛠 Tools Used
- IntelliJ IDEA Community Edition 2024.1.4
- Microsoft Excel (for writing manual test cases)


## ⚙️ Setup Instructions
1. Clone this repository.
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA).
3. Open `pom.xml`.
4. Reload the Maven project to download dependencies.
5. Run test cases:
    - Via XML configuration files using the `TestRunner`
    - Or by directly executing individual test classes
6. Generate Allure Report:
   ```bash
   allure serve test-outputs/target/AllureResults
   ```

## 📁 Project Structure
```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── PagesTesting          # Page Object Model classes
│   │   │   ├── Utilities             # Utility classes (e.g., data, logging)
│   │   │   └── Factory               # Driver and config management
│   └── test
│       ├── java
│       │   ├── Testing
│       │   │   ├── PositiveTestCases # Functional test classes
│       │   │   └── NegativeTestCase  # Negative/edge case test classes
│       │   └── Listeners             # TestNG custom listeners
```


## 🧾 Test Case Summary

### ✅ Positive Test Cases

| Manual Test Case                                                                 | Automated Test Class       | Path                                                                 |
|----------------------------------------------------------------------------------|-----------------------------|----------------------------------------------------------------------|
| Verify that the user is able to search for an item                              | `itemSearchTC`              | `src/test/java/Testing/PositiveTestCases/itemSearchTC.java`         |
| Verify that the user is able to add an item to the cart                         | `AddingItemToCart`          | `src/test/java/Testing/PositiveTestCases/AddingItemToCart.java`     |
| Verify that the user is able to complete the purchase of the product            | `purchaseItemTC`            | `src/test/java/Testing/PositiveTestCases/purchaseItemTC.java`       |

### 🚫 Negative Test Cases

| Manual Test Case                                                                 | Automated Test Class       | Path                                                                 |
|----------------------------------------------------------------------------------|-----------------------------|----------------------------------------------------------------------|
| Verify that the system shows an appropriate message when searching for an invalid/non-existent product | `invalidProductSearch`      | `src/test/java/Testing/NegativeTestCase/invalidProductSearch.java`  |
| Verify that a user cannot add an item to the cart without selecting mandatory options | `failedAddingToCart`        | `src/test/java/Testing/NegativeTestCase/failedAddingToCart.java`    |

## 📊 Test Reports
Allure Reports are generated automatically after test execution.  
You can view them using the following command:

```bash
allure serve test-outputs/target/AllureResults
```
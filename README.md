# 🧪 OrangeHRM UI & API Automation Task

## 📌 Overview

This project is a Quality Control Automation Assessment, focused on building a structured and scalable automation framework using **Java**, **Selenium WebDriver**, **TestNG**, and **REST Assured**.

The project automates core functional scenarios on the [OrangeHRM Demo Site](https://opensource-demo.orangehrmlive.com) and includes an optional API test to showcase RESTful service validation.

---

## 🛠️ Tools & Technologies

| Tool             | Purpose                           |
|------------------|-----------------------------------|
| Java             | Programming language              |
| Intellj IDE      | Automation script IDE             |
| Postman          | API Testing                       |
| Maven            | Dependency and build management   |
| REST Assured     | API testing                       |
| WebDriverManager | Browser driver management         |
| GitHub           | Code versioning and collaboration |

---

## 🧱 Framework Design

This project follows the **Page Object Model (POM)** with clean separation of:

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── Pages                 # Page Object Model classes
│   │   │   ├── Utilities             # Utility classes (e.g., data, logging)
│   │   │   └── Factory               # Driver and config management
│   └── test
│       ├── java
│       │   ├── Testing
│       │   │   ├── T01Login          # This is for Logining into the portal
│       │   │   ├── T02sidemenu       # This is for searching for admin to get to user managment
│       │   │   ├── T03UserManagment  # This is for accessing the User managment for runing the Scenario in multiple sessions
│       │   │   ├── T03UserManagment  # This is for running the same scenario and asserting in one session
│       │   │   └── T04AddingCandidateApITest  # This is for Running the bones Point
│       │   └── Listeners             # TestNG custom listeners
```


✅ **Configuration & Test Data**:
- Environment-specific URLs and settings are stored in:  
  `src/test/resources/TestData/environment.properties`
- Test input data (e.g., candidate details, login credentials) is loaded from:  
  `src/test/resources/TestData/TestData.json`

🔐 **Authentication Note**:  
For testing purposes, the username and password are included in `TestData.json`.  
In a real-world project, credentials should be securely fetched (e.g., from environment variables or an external vault) instead of being stored in plain files.


## ✅ Implemented Scenarios

### 🔹 UI Automation (Selenium)

| Step | Action |
|------|--------|
| 1 | Navigate to OrangeHRM login page |
| 2 | Login with: **Admin / admin123** |
| 3 | Click the **Admin** tab on the sidebar |
| 4 | Search for user with username `Admin` |
| 5 | ✅ Assert: number of results, username, role, and status |
| 6 | Attempt to delete the Admin user |
| 7 | ✅ Assert: deletion is blocked or alert is shown |

### 🔹 Bonus: API Automation (REST Assured)

| Step | Action |
|------|--------|
| 1 | Use **Basic Authentication** to send a `POST` request to add a new candidate |
| 2 | Assert on the **HTTP response status code** |
| 📝 Note | The OrangeHRM demo site does not expose a fully functional public API, so the test is structured as a simulation |

---
### ⚠️ Note on Bonus Scenario (API Testing with REST Assured)

The OrangeHRM demo site (`https://opensource-demo.orangehrmlive.com`) **does not expose a functional or documented public API for candidate creation or authentication**.

While the bonus task requested using REST Assured to add a new candidate and validate the HTTP response, **authentication requests consistently return `401 Unauthorized`**, even when correct login credentials are provided.

This likely indicates that the required API endpoints for authentication and candidate creation are either:
- Disabled on the public demo site
- Protected by additional CSRF tokens/session headers not accessible without a valid session cookie from UI login
- Or simply not implemented for public use

As a result, any attempts to complete the bonus scenario with real API calls will fail unless tested against a self-hosted or licensed OrangeHRM instance with full API support.

➡️ **Workaround**: A mock or simulated example has been included to show how the implementation would look using REST Assured, assuming valid endpoints were available.

## 🚀 How to Run the Tests

### 1. UI Test Execution
- Open your IDE (e.g., IntelliJ or Eclipse).
- Navigate to the test runner directory where `UserManagmentM1.xml` and `UserManagmentM2.xml` files are located.
- Right-click on either `UserManagmentM1.xml` or `UserManagmentM2.xml` and select **Run**.
- These files include functional test cases for user management scenarios such as login, search, validation, and restricted deletion.

🗂️ **Note**: All test data is retrieved from a structured JSON file (`TestData.json`)  
🔧 Environment configurations such as base URL are read from `environment.properties`.

### 2. Bonus Scenario – API Test Execution
- For the optional API task, open the `addCandidateAPI.xml` file from the same test runner directory.
- Right-click on `addCandidateAPI.xml` and select **Run**.
- This simulates an API call using REST Assured and validates the HTTP response.
  > ⚠️ Note: Since the public OrangeHRM demo site does not expose a working API, this test uses a mock structure to demonstrate the intended REST call and assertion logic.


### ✅ Test Output
- Review the test execution results in the console or in your test reports (e.g., TestNG HTML reports).
- All assertions for UI and API logic will be visible with detailed pass/fail status.



## 📊 Allure Report (Optional)
To view an interactive HTML report after test execution: 



**In The Terminal**
`allure serve test-outputs/target/AllureResults`


### 🖥 Prerequisites
- Java 11 or higher
- Maven 3.8+
- Chrome browser installed
- Git (to clone the repo)

---


### **Run from Terminal (Maven Profiles)**
You can execute specific scenarios directly from the terminal using Maven profiles:

```bash
# Run User Management scenario
mvn -PUserManagmentProfile clean test

# Run Add Candidate API scenario
mvn -PAddCandidateAPIProfile clean test


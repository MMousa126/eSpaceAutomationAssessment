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

## 🚀 How to Run the Tests

- Navigate to **UserManagmentM2.xml** or **UserManagmentM1.xml**
- Click right Click and Select to Run the test 
- Check the Test cases assertion


### 🖥 Prerequisites
- Java 11 or higher
- Maven 3.8+
- Chrome browser installed
- Git (to clone the repo)

---

### 📂 Clone the Repo

```bash
git clone https://github.com/MMousa126/eSpaceAutomationAssessment.git
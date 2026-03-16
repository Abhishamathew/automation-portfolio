![CI](https://github.com/Abhishamathew/automation-portfolio/actions/workflows/api-tests.yml/badge.svg)

# Automation Portfolio

A comprehensive test automation framework built with Java, Selenium WebDriver, and REST Assured, targeting [AutomationExercise.com](https://automationexercise.com).

## Tech Stack
- Java 17
- Selenium WebDriver 4.18.1
- TestNG 7.9.0
- REST Assured 5.4.0
- Maven
- Extent Reports 5.1.1
- WebDriverManager
- GitHub Actions CI/CD

## Framework Features
- Page Object Model (POM) design pattern
- Config-driven test data (config.properties)
- Automatic ad removal for stable test execution
- Screenshot capture on test failure
- Extent HTML reports

## Test Coverage

### UI Tests (Selenium)
| Test Case | Description | Status |
|-----------|-------------|--------|
| TC1  | Register User | ✅ |
| TC5  | Register with existing email | ✅ |
| TC6  | Contact Us Form | ✅ |
| TC7  | Test Cases Page | ✅ |
| TC8  | All Products displayed | ✅ |
| TC9  | Search Product | ✅ |
| TC10 | Subscription on Home Page | ✅ |
| TC11 | Subscription on Cart Page | ✅ |
| TC12 | Add Products to Cart | ✅ |
| TC13 | Verify Product Quantity | ✅ |
| TC17 | Remove Products from Cart | ✅ |
| TC18 | View Category Products | ✅ |
| TC19 | View Brand Products | ✅ |

### API Tests (REST Assured)
| API | Description | Status |
|-----|-------------|--------|
| API1  | Get All Products | ✅ |
| API2  | POST Products not supported | ✅ |
| API3  | Get All Brands | ✅ |
| API4  | PUT Brands not supported | ✅ |
| API5  | Search Product | ✅ |
| API6  | Search without parameter | ✅ |
| API7  | Verify Login valid | ✅ |
| API8  | Verify Login without email | ✅ |
| API9  | DELETE Login not supported | ✅ |
| API10 | Verify Login invalid | ✅ |
| API11 | Create User Account | ✅ |
| API12 | Delete User Account | ✅ |
| API13 | Update User Account | ✅ |
| API14 | Get User Detail by Email | ✅ |

## Project Structure
```
src/
├── main/java/com/abhisha/
│   ├── base/BaseTest.java
│   ├── pages/
│   ├── utils/
│   └── constants/
└── test/java/
    ├── tests/ui/
    └── tests/api/
```

## How to Run

### Run all API tests
```bash
mvn test -Dtest="tests.api.*"
```

### Run all UI tests
```bash
mvn test -Dtest="tests.ui.*"
```

### Run full suite
```bash
mvn test
```

## CI/CD
Tests automatically run on every push via GitHub Actions.
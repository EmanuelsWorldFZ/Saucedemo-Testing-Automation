# Sauce Demo Playwright Automation

A Java 21, Maven, Playwright, and JUnit 5 test framework for the public Sauce Demo
practice shop at <https://www.saucedemo.com>. Tests use the Page Object Model and
validate user-visible outcomes instead of only performing browser actions.

## Prerequisites

- Java 21
- Maven 3.9 or newer
- Git
- IntelliJ IDEA Community Edition (optional)
- A GitHub account and GitHub Copilot plugin (optional)

From a terminal, install Java and Git on macOS with Homebrew:

```sh
brew install openjdk@21 git maven
java -version
git --version
mvn -version
```

Open this project in IntelliJ IDEA as a Maven project and allow Maven to import
the dependencies. Playwright browser binaries are installed separately:

```sh
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI \
  -Dexec.args="install chromium"
```

## Run the tests

```sh
mvn test
```

Tests run headlessly in Chromium by default. Install the other browser binaries
and choose a browser for cross-browser runs:

```sh
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI \
  -Dexec.args="install firefox webkit"
mvn test -Dbrowser=firefox
mvn test -Dbrowser=webkit
```

The browser can also be selected with `PLAYWRIGHT_BROWSER`. Override the site
address for a compatible test environment with `-DbaseUrl=https://...` or
`SAUCEDEMO_BASE_URL`.

JUnit XML reports are written under `target/surefire-reports`. Screenshots are
captured on test failures under `target/test-artifacts/screenshots`. The GitHub
Actions workflow runs the complete test suite on Chromium, Firefox, and WebKit.

## Project structure

```text
src/test/java/com/saucedemo/
├── base/       Shared browser lifecycle and test setup
├── pages/      Login, inventory, cart, and checkout page objects
├── tests/      Login, cart, and checkout scenarios
└── utils/      Screenshot capture when a test fails
```

Each test gets a fresh browser and page. Page objects receive the Playwright
`Page` through constructor injection; `BaseTest` owns browser startup and
shutdown.

## Coverage

- Valid login, product listing, logout, and invalid credentials (parameterized)
- Adding one or multiple products to the cart and removing a product
- Required checkout information, successful checkout, and order confirmation
- Failure screenshots, Maven test reports, and multi-browser CI

The expected purchase flow is login, add product, open cart, enter first name,
last name, and postal code, review the order, and finish checkout. A successful
order displays “Thank you for your order!”.

## Git workflow

Use small commits that describe one logical change:

```sh
git add src/test/java/com/saucedemo/pages/LoginPage.java
git commit -m "feat: add login page object"
```

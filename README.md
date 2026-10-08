# Saucedemo-Testing-Automation
Automated end-to-end tests for the Sauce Demo practice shop, built with Java, Playwright, JUnit 5, and the Page Object Model. Includes login, cart, and checkout coverage, plus GitHub Actions browser testing.

# Saucedemo Testing Automation

End-to-end browser tests for the [Sauce Demo](https://www.saucedemo.com) practice shop, built with Java 21, Playwright, JUnit 5, and the Page Object Model.

## Prerequisites

- Java 21
- Maven 3.9+
- Git

Check your Java and Maven versions:

```sh
java -version
mvn -version



 Get started

Clone the repository:
git clone https://github.com/EmanuelsWorldFZ/Saucedemo-Testing-Automation.git
cd Saucedemo-Testing-Automation
Install the Playwright Chromium browser:
mvn exec:java \
  -Dexec.classpathScope=test \
  -Dexec.mainClass=com.microsoft.playwright.CLI \
  -Dexec.args="install chromium"
Run the tests:
mvn test
Tests run headlessly in Chromium by default. To use another browser, install it first and select it when running the tests:
mvn exec:java \
  -Dexec.classpathScope=test \
  -Dexec.mainClass=com.microsoft.playwright.CLI \
  -Dexec.args="install firefox webkit"

mvn test -Dbrowser=firefox
mvn test -Dbrowser=webkit
 Test coverage
•
Successful login, invalid credentials, and logout
•
Product listing and cart badge count
•
Adding and removing products from the cart
•
Required checkout details and successful order confirmation
The tests use Sauce Demo's public practice credentials: standard_user / secret_sauce.
 Project structure
src/test/java/com/saucedemo/
├── base/       Shared browser setup and teardown
├── pages/      Login, inventory, cart, and checkout page objects
├── tests/      Login, cart, and checkout tests
└── utils/      Screenshot capture on test failures
JUnit reports are saved under target/surefire-reports. Screenshots from failed tests are saved under target/test-artifacts/screenshots.
GitHub Actions runs the test suite on Chromium, Firefox, and WebKit for pushes and pull requests.

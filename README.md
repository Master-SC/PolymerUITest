# **PolymerUITest**
PolymerUITest is an automated testing framework designed for UI testing of applications using Selenium and Cucumber, built on Behavior-Driven Development (BDD) principles to deliver a scalable, maintainable test suite. It leverages the Page Object Model (POM) to enhance code readability and reusability. The framework supports automated test execution and generates comprehensive Cucumber reports, with seamless integration into GitHub Actions for continuous integration and automated test runs.

## Table of Contents
* Overview
* Features
* Requirements
* Setup
* Usage
* Running Tests
* GitHub Actions Integration
* Running CI Manually
* Technical Debt and Future Improvements

## Overview
PolymerUITest is a Behavior-Driven Development (BDD) framework created to automate web application testing using Java, Selenium, and Cucumber. It enables users to write clear, human-readable test scenarios that describe application behavior, making the tested functionality accessible to both technical and non-technical stakeholders.

The framework is highly modular, making it easy to maintain and extend test scenarios. It also supports generating Cucumber HTML reports for each test run and provides seamless integration with GitHub Actions for automated test executions.

## Features
* **Cucumber BDD Framework**: Write and execute human-readable scenarios that describe application behavior.
* **Detailed Reporting:** Generates Cucumber HTML reports for better insight into test results.
* **GitHub Actions Integration:** Automated test execution on each push or pull request to the repository.
* **Modular Structure:** Provides a clear separation of page objects, test data, and step definitions.
* **Easy Maintenance:** Create and manage feature files and step definitions with ease, allowing quick updates to test cases.

## Requirements

To use this framework, you need the following:

* Java (version 17 or higher)
* Maven (for dependency management and running tests)
* Git (for version control)
* A supported browser (such as Chrome) for executing web-based tests
* GitHub Actions setup for automated test runs


## Setup

#### 1. Clone the Repository:

```sh
git clone https://github.com/Master-SC/PolymerUITest.git
cd PolymerUITest
```
This command will download all necessary dependencies, including Cucumber, Selenium, and reporting libraries.

#### 2. Install Dependencies:

Use Maven to install required dependencies:
```sh
mvn clean install
```
This command will download all necessary dependencies, including Cucumber, Selenium, and other required libraries.

## Usage
#### To create tests:
1. Write feature files in the ```src/test/resources/features``` directory. 
2. Each feature file contains Cucumber scenarios describing the application's expected behavior.
3. Implement step definitions in the ```src/test/java/steps``` directory. These files define the Java methods for each step in the feature files.
4. Create reusable page objects in ```src/test/java/pages``` to simplify interactions with UI elements.

## Project Structure

```plaintext
PolymerUITest/
├── src/
│   ├── main/                       # Main source files (if applicable)
│   └── test/
│       ├── java/
│       │   ├── steps/              # Cucumber step definitions
│       │   ├── runner/             # Test Runners
│       │   ├── pages/              # Page objects for UI components
│       │   └── utils/              # Custom Utilities
│       └── resources/
│           ├── features/           # Cucumber feature files
│           ├── testdata/           # Test data Files (Excel)
│           ├── config.properties   # Configurable test parameters (Browser,Application Url etc.)
│           └── cucumber.properties # Configurable cucumber parameters
├── target/                         # Generated files (reports, etc.)
├── README.md                       # Project documentation
├── pom.xml                         # Maven configuration and dependencies
└── .github/workflows/ci.yml        # GitHub Actions workflow file
```

## Running Tests

#### 1. Run Tests Locally:

Execute all tests using Maven:

```sh 
mvn test
```
#### 2. Generate Cucumber Reports:

After test execution, Cucumber reports are generated in the target directory as ```cucumber-reports.html``` . This HTML reports provide insights into each scenario, including passed, failed, and skipped steps.

## GitHub Actions Integration
This project includes a GitHub Actions workflow ```.github/workflows/ci.yml``` that automatically runs tests on each push or pull request.

The workflow:
1. Checks out the repository
2. Sets up Java and dependencies using Maven
3. Runs the tests and generates reports. 

## Running CI Manually
The workflow will trigger on any push to the repository or any pull request. To run it manually, navigate to the Actions tab on GitHub, select the workflow, and click on Re-run jobs.


## Technical Debt and Future Improvements

While the PolymerUITest project is a solid foundation for automated testing, there are areas where technical debt could be reduced and future improvements could be enhanced.

### 1. Modularization of Step Definitions
###### Technical Debt:
**Large Step Definition Files:**
If step definition files grow too large, it becomes difficult to maintain and debug them.

**Tight Coupling:**
Step definitions might be tightly coupled to specific tests, making it difficult to reuse them across multiple tests.

###### Future Improvements:

**Refactor Step Definitions into Smaller Modules:**
Break up large step definition files into smaller, more manageable classes, grouped by the functionality they test (e.g., authentication steps, UI interaction steps).

**Reuse Step Definitions:**
Create reusable, generic step definitions that can be used across multiple feature files. This will enhance test maintainability and scalability.

### 2. Performance Optimization
###### Technical Debt:
**Slow Test Execution:**
As the number of tests grows, execution times may increase, especially if the tests are not optimized (e.g., if they do not properly wait for elements to load).

**Inefficient CI/CD Pipeline:**
The GitHub Actions workflow might not be optimized for parallel test execution, leading to longer feedback times.

###### Future Improvements:

**Optimize Waits and Timeouts:**
Review the step definitions for efficient handling of waits and timeouts. Instead of using arbitrary waits, use explicit waits (e.g., WebDriverWait) to ensure that elements are available before interactions.

**Parallel Test Execution:**
Use parallel test execution to speed up the test process, especially for large test suites. This can be achieved by configuring test runners or CI tools to run tests in parallel across different machines or containers by implementing Selenium Grid

### 3. Minimal Logging
###### Technical Debt:

**Inadequate Traceability:**
Without detailed logging at each step, it’s difficult to trace the test execution flow, especially during failures. Minimal logging limits visibility into test actions, assertions, and error points, leading to longer debugging times.

###### Future Improvements:

**Centralized and Consistent Logging Framework:**
Use a centralized logging framework, such as SLF4J with Logback or Log4j, to ensure consistent logging behavior across all tests.Assign unique identifiers for each test thread or process during parallel execution, ensuring isolation in log output.

### 4. Integration with Observability Tools
###### Technical Debt:
**Limited Observability in CI/CD:**
Logs alone may not provide enough visibility into the test execution process when running tests in CI/CD. It may also be challenging to monitor real-time status without an integrated observability setup.

###### Future Improvements:

**Integrate with Observability Tools:**
Use observability platforms (e.g., Datadog) that support log analysis and real-time monitoring. This can be particularly useful for viewing logs across multiple test runs or observing trends in test failures.
Set up automated alerts for specific log events (e.g., multiple consecutive failures in the same test, recurring errors) to allow teams to address critical issues promptly.

### 5. AI-Powered Test Maintenance
###### Technical Debt:
**Difficulty in Maintaining Test Scripts:**
As UI components change, maintaining corresponding tests can become burdensome, especially when manual updates are required for locators, steps, and scenarios.

###### Future Improvements:

**Automated Test Script Refactoring:**
Generative AI can analyze changes in the UI (e.g., DOM changes) and automatically suggest or implement refactors for step definitions, locators, and feature files. This would reduce the time spent manually updating tests.
# Retail Store Rule Engine Project

## Overview
This project aims to develop a rule engine for a retail store to qualify orders' transactions for discounts based on a set of predefined rules. The discounts are automatically calculated according to specific criteria such as product expiration, product type, special promotions, and quantity purchased. The project emphasizes functional programming principles and clean, maintainable code.

## Project Code
The project's source code can be found [here](https://github.com/MarkSamuell/Retail-Rule-Engine-with-Scala/tree/master/src/main/scala).

## Problem Statement
The retail store requires a rule engine to automate the qualification of discounts for orders' transactions. The discounts are determined based on several qualifying rules and calculation rules, including product expiration, product type, special promotions, and quantity purchased. Transactions that qualify for multiple discounts receive the top two discounts, and their average is applied.

## Technical Considerations
- **Core Functional Logic:** The core logic of the rule engine must adhere to functional programming principles, utilizing only `vals` with no `vars`, avoiding mutable data structures and loops, and ensuring all functions are pure with no side effects.
- **Logging:** Engine events are logged in a "rules_engine.log" file with timestamp, log level, and message format.
- **Data Persistence:** After discount calculations, the final price is computed and loaded into a database table.
- **Code Quality:** The codebase must be well-commented, clean, easy to read, and self-explanatory, emphasizing readability and maintainability.

## Implementation
The project will involve the following steps:
1. **Rule Definition:** Define the qualifying rules and calculation rules for discounts.
2. **Functional Logic Development:** Implement the core functional logic for discount qualification and calculation.
3. **Logging Implementation:** Set up logging functionality to record engine events.
4. **Data Persistence:** Calculate final prices for transactions and store them in a database table.
5. **Code Review and Refinement:** Ensure code quality by reviewing and refining the codebase to meet functional programming and readability standards.

![alt text](./img.png)


## Usage
To use the rule engine:
1. Define the rules for discount qualification and calculation.
2. Input transaction data.
3. Run the rule engine to calculate discounts and store the results in the database.
4. Monitor engine events and log messages in the "rules_engine.log" file for debugging and analysis.


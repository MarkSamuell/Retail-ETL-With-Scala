# Retail Store ETL Rule Engine Project

## Overview
This ETL (Extract, Transform, Load) project aims to develop a rule engine for a retail store to qualify orders' transactions for discounts based on a set of predefined rules. The discounts are automatically calculated according to specific criteria such as product expiration, product type, special promotions, and quantity purchased. The project emphasizes functional programming principles and clean, maintainable code.

## Project Code
The project's source code can be found [here](https://github.com/MarkSamuell/Retail-Rule-Engine-with-Scala/tree/master/src/main/scala).

## Problem Statement
The retail store requires a rule engine to automate the qualification of discounts for orders' transactions. The discounts are determined based on several qualifying rules and calculation rules, including product expiration, product type, special promotions, and quantity purchased. Transactions that qualify for multiple discounts receive the top two discounts, and their average is applied.

## ETL Process
![ETL Process](process.PNG)

## Milestones
1. **Rule Definition:** Define the qualifying rules and calculation rules for discounts.
2. **Data Extraction:** Extract transaction data from the source system.
3. **Data Transformation:** Apply discount qualification and calculation logic to the extracted data.
4. **Data Loading:** Load the transformed data, including final prices, into a database table.
5. **Logging Implementation:** Set up logging functionality to record engine events.

## Technical Considerations
- **Core Functional Logic:** The core logic of the rule engine must adhere to functional programming principles, utilizing only `vals` with no `vars`, avoiding mutable data structures and loops, and ensuring all functions are pure with no side effects.
- **Logging:** Engine events are logged in a "rules_engine.log" file with timestamp, log level, and message format.
- **Data Persistence:** After discount calculations, the final price is computed and loaded into a database table.
- **Code Quality:** The codebase must be well-commented, clean, easy to read, and self-explanatory, emphasizing readability and maintainability.

## Usage
To use the ETL rule engine:
1. Define the rules for discount qualification and calculation.
2. Extract transaction data from the source system.
3. Transform the extracted data by applying discount qualification and calculation logic.
4. Load the transformed data, including final prices, into a database table.
5. Monitor engine events and log messages in the "rules_engine.log" file for debugging and analysis.

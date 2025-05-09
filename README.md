# MVVM Clean Architecture Project

This project demonstrates various Android development concepts using **Jetpack Compose**, **Retrofit**, and **Clean Architecture**. It displays a list of uses fetched from a public API along with detailed information about each user.

---

## Features

- **Jetpack Compose**: Used for modern and declarative UI development.
- **Retrofit**: For seamless API integration.
- **Clean Architecture**: 
  - Separation of concerns across Presentation, Domain, and Data layers.
  - Enhanced reusability, testability, scalability, and maintainability.
- **Dependency Injection**: 
  - Efficient management of modules and initialization of dependencies.
- **Test Cases**:
  - Covers empty, data, and error scenarios for robust implementation.

---

## API Integration

This project uses the following public API for fetching articles:
- **API URL**: [https://randomuser.me/api/?results=5000)

---

## Implementation Details

### 1. **Architecture Overview**
   - **Presentation Layer**: Handles UI and user interaction.
   - **Domain Layer**: Includes use cases and business logic.
   - **Data Layer**: Manages API calls and data repository.

### 2. **UI Implementation**
   - List of articles displayed using Jetpack Compose.
   - Detail screen to show complete information about a selected User.

### 3. **Error Handling**
   - Manages empty, error, and successful data states with appropriate UI feedback.



### 4. **Dependency Injection**
   - Implemented using modules to simplify dependency management and testing.



https://github.com/user-attachments/assets/d1762b00-ac5e-47c6-9b44-ce3f579819c6


![ListScreen](https://github.com/user-attachments/assets/c1335364-da8e-4c12-b554-fa6bbf48370e)

![DetailsScreen](https://github.com/user-attachments/assets/3e317076-463e-4b22-9273-2a68e97b9df6)


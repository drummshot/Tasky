# 📝 Tasky

**Tasky** is a cross-platform task management app built with Kotlin Multiplatform, applying modern architectural patterns and clean code principles. The project follows a feature-driven approach and leverages the power of **Concentric Clean Architecture**.

---

## 🚀 Tech Stack

- **Kotlin Multiplatform (KMP)**
- **Jetpack Compose Multiplatform (UI)**
- **Room** – Local database
- **Koin** – Dependency Injection
- **Kermit** – Logging
- **Compass-Geolocation** – Location Support

---

## 🧠 Architecture

Tasky is built on **Clean Architecture** with a concentric layered approach, focusing on **SOLID** principles and a strong separation of concerns.

### 🔄 Layers

- **Presentation** – UI, Navigation, ViewModels, UI States
- **Domain** – Use Cases, Models, Use Cases
- **Data** – Data Sources, Repositories, DAOs

### 🧱 Key Patterns & Concepts

- **DAO** – Data Access Objects
- **Data Sources** – Abstraction over APIs and local DB
- **Repositories** – Single source of truth for the app
- **Use Cases** – Business rules and application logic
- **UI State Models**
- **Param Wrappers** – Standardized input structures
- **Navigation** – Composable-driven navigation system
- **ViewModels** – Platform-agnostic state holders
- **Result** – Result Wrapper for error/success handling

---

## 🔐 Security & Session Management

Tasky includes a custom `SessionManager` for handling user sessions and enforcing access control logic. This ensures task-related operations are securely scoped to the currently logged-in user.

---

## 🌍 Platform Support

| Platform     | Status           |
|--------------|------------------|
| Android      | ✅ Full Support   |
| Desktop (JVM)| ✅ Full Support   |
| iOS          | 🔄 In Progress (Experimental) |
|              |                  |

---
## Screenshots
<img src="https://github.com/user-attachments/assets/9489303a-921d-4a09-b022-4fab68236c25" alt="Alt Text" width="300" height="500">


---


## 📦 Installation & Setup

📌 **Coming soon** – Instructions to build and run Tasky across platforms.

---

## 🧪 Testing

Unit tests for use cases and repositories are currently in development.

---

## ✍️ Author

Made with ❤️ using Kotlin Multiplatform by **@drummshot**.

---

## 📄 License

📝 Coming soon.

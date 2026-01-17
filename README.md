# vaultcore-financial
### Secure Digital Banking & Trading Core (FinTech Backend)

VaultCore Financial is a backend-focused FinTech project that simulates the **core infrastructure of a Neo-Bank**, including secure account management, money transfers, audit logging, and compliance-oriented design.

The project emphasizes **ACID compliance, concurrency handling, security middleware, and auditability**, aligning with real-world banking and financial systems.

---

## 🚀 Key Features

### 🔐 Security & Compliance
- Fraud Detection Middleware using Spring Interceptor
- Configurable high-value transaction threshold
- Mock 2FA trigger for suspicious transactions
- Centralized Audit Logging using Spring AOP (AspectJ)

### 💳 Banking & Ledger System
- Double-entry ledger design
- Transaction processing with `@Transactional`
- Serializable isolation for concurrency safety
- Ensures balance consistency under concurrent access

### ⚙️ Concurrency & Performance
- Virtual Threads (Java 21) used for high-concurrency balance queries
- Designed to simulate thousands of concurrent requests

### 📊 Reporting
- Monthly Statement Service (PDF generation logic simulated)
- Designed to integrate with external PDF libraries

### 🗄 Database
- MySQL configuration via Spring Boot & JPA
- Schema managed automatically using Hibernate

---

## 🧰 Tech Stack

- **Backend:** Java 21, Spring Boot
- **Concurrency:** Java Virtual Threads
- **Security:** Spring Interceptors, JWT (configuration-ready)
- **Database:** MySQL, JPA / Hibernate
- **Audit:** Spring AOP (AspectJ)
- **Build Tool:** Maven

---

## 📁 Project Structure


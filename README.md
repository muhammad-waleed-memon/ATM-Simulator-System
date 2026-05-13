# ATM Simulator System
**Package:** `com.bitbywaleed`

## Project Structure
```
ATM-Simulator-System-Complete/
├── database_schema.sql          <-- Run this in MySQL first
├── README.md
└── src/
    └── com/
        └── bitbywaleed/
            ├── Conn.java              <-- Database connection
            ├── Login.java             <-- Login screen (Entry point)
            ├── Signup.java            <-- Signup Page 1: Personal Details
            ├── Signup2.java           <-- Signup Page 2: Additional Details
            ├── Signup3.java           <-- Signup Page 3: Account Details
            ├── Transactions.java      <-- Main dashboard
            ├── Deposit.java           <-- Deposit money
            ├── Withdrawal.java         <-- Withdraw money (with balance check)
            ├── FastCash.java          <-- Quick withdrawal presets
            ├── BalanceEnquiry.java    <-- Check balance
            ├── MiniStatement.java     <-- Last 5 transactions
            └── Pin.java               <-- Change PIN
```

## Setup Steps

### 1. Database Setup
Open MySQL and run:
```sql
SOURCE /path/to/database_schema.sql;
```

### 2. Update Database Credentials
Open `Conn.java` and update:
```java
c = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/atm_simulator",
    "root",           // Your MySQL username
    "your_password"   // Your MySQL password
);
```

### 3. Add MySQL Connector JAR
- Download `mysql-connector-java-8.0.xx.jar`
- Add to your IDE's Build Path / Libraries

### 4. Run the Application
Run `Login.java` as the main class.

## Features
- Secure login with PreparedStatement (SQL Injection proof)
- 3-step account registration
- Deposit with transaction logging
- Withdrawal with real-time balance validation
- Fast Cash presets (100, 500, 1000, 2000, 5000, 10000)
- Balance enquiry
- Mini statement (last 5 transactions)
- PIN change (updates all records)

## Notes
- All files use package `com.bitbywaleed`
- Balance is calculated dynamically from transaction history
- Card numbers are auto-generated during signup

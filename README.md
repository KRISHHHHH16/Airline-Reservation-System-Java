# ✈️ Airline Reservation System (Java + MySQL)

## 📌 Overview
A desktop-based Airline Reservation System developed using Java Swing and MySQL.  
The application allows users to search flights, book tickets, complete payments, and generate QR-based boarding passes.

---

## 🚀 Key Features
- User Registration & Login
- Flight Search by Source, Destination & Travel Date
- Dynamic Flight Filtering
- Airline Logo Display
- Ticket Booking System
- Payment Integration (Razorpay simulation)
- QR Code Boarding Pass Generation
- MySQL Database Integration

---

## 🛠️ Tech Stack
- Java (Swing)
- MySQL
- JDBC
- ZXing (QR Code Generation)
- Razorpay API Integration

---

## 🗄️ Database Tables
- users
- passengers
- flights
- booking
- payments

---

## 🔄 System Workflow
1. User logs in
2. Searches available flights
3. Selects and books a flight
4. Completes payment
5. Generates boarding pass with QR code

---

## ▶️ How to Run
1. Import project into IntelliJ / Eclipse
2. Create database named `airline_db`
3. Import `sql/airline_db.sql`
4. Update database credentials in the code
5. Run `Main.java`

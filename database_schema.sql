-- ATM Simulator Database Schema
-- Run this in MySQL before starting the app
-- Author: bitbywaleed

CREATE DATABASE IF NOT EXISTS atm_simulator;
USE atm_simulator;

CREATE TABLE IF NOT EXISTS signup (
    form_no VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100),
    father_name VARCHAR(100),
    dob VARCHAR(50),
    gender VARCHAR(10),
    email VARCHAR(100),
    marital_status VARCHAR(20),
    address VARCHAR(200),
    city VARCHAR(50),
    pincode VARCHAR(10),
    state VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS signup2 (
    form_no VARCHAR(20) PRIMARY KEY,
    religion VARCHAR(30),
    category VARCHAR(30),
    income VARCHAR(50),
    education VARCHAR(50),
    occupation VARCHAR(50),
    pan VARCHAR(20),
    CNIC VARCHAR(20),
    senior_citizen VARCHAR(10),
    existing_account VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS signup3 (
    form_no VARCHAR(20) PRIMARY KEY,
    account_type VARCHAR(30),
    cardno VARCHAR(20),
    pin VARCHAR(10),
    facility VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS login (
    cardno VARCHAR(20) PRIMARY KEY,
    pin VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS bank (
    pin VARCHAR(10),
    date VARCHAR(50),
    type VARCHAR(20),
    amount VARCHAR(20)
);


select * from bank;
select * from login;
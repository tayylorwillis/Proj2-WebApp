-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 05, 2024 at 12:16 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ApartmentDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `MaintenanceRequest`
--

CREATE TABLE `MaintenanceRequest` (
  `requestId` int(11) NOT NULL,
  `tenantId` int(11) DEFAULT NULL,
  `tenantName` varchar(255) DEFAULT NULL,
  `apartmentNumber` int(11) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `dateTime` datetime DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `MaintenanceRequest`
--

INSERT INTO `MaintenanceRequest` (`requestId`, `tenantId`, `tenantName`, `apartmentNumber`, `area`, `description`, `dateTime`, `status`) VALUES
(2, 6873, 'John Doe', 101, 'kitchen', 'Leaking faucet', '2024-11-01 09:30:00', 'completed'),
(3, 8263, 'Jane Smith', 202, 'bathroom', 'Toilet is clogged', '2024-11-02 14:15:00', 'pending'),
(4, 3478, 'David Johnson', 303, 'living room', 'AC not working', '2024-11-03 12:45:00', 'completed'),
(5, 2093, 'Emily Davis', 404, 'bedroom', 'Heater malfunctioning', '2024-11-03 19:00:00', 'pending'),
(6, 1983, 'Michael Brown', 505, 'kitchen', 'Dishwasher not draining', '2024-11-04 08:00:00', 'pending'),
(7, 5623, 'Olivia Wilson', 606, 'bathroom', 'Shower head leaking', '2024-11-04 11:30:00', 'pending'),
(8, 7812, 'William Moore', 707, 'balcony', 'Door does not lock', '2024-11-05 10:20:00', 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `Tenant`
--

CREATE TABLE `Tenant` (
  `tenantId` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `checkInDate` date DEFAULT NULL,
  `checkOutDate` date DEFAULT NULL,
  `apartmentNumber` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Tenant`
--

INSERT INTO `Tenant` (`tenantId`, `name`, `phoneNumber`, `email`, `checkInDate`, `checkOutDate`, `apartmentNumber`) VALUES
(1, 'John Doe', '555-1234', 'johndoe@example.com', '2024-01-01', '2025-01-01', 101),
(2, 'Jane Smith', '555-5678', 'janesmith@example.com', '2024-02-01', '2025-02-01', 102),
(3, 'Alice Johnson', '555-8765', 'alicejohnson@example.com', '2024-03-01', '2025-03-01', 103),
(4, 'Bob Brown', '555-4321', 'bobbrown@example.com', '2024-04-01', '2025-04-01', 104);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `MaintenanceRequest`
--
ALTER TABLE `MaintenanceRequest`
  ADD PRIMARY KEY (`requestId`);

--
-- Indexes for table `Tenant`
--
ALTER TABLE `Tenant`
  ADD PRIMARY KEY (`tenantId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `MaintenanceRequest`
--
ALTER TABLE `MaintenanceRequest`
  MODIFY `requestId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `Tenant`
--
ALTER TABLE `Tenant`
  MODIFY `tenantId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

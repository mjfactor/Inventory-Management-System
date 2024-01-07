-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 07, 2024 at 06:28 AM
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
-- Database: `inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'password');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `customerName` varchar(255) NOT NULL,
  `type` varchar(100) NOT NULL,
  `productName` varchar(100) NOT NULL,
  `quantity` varchar(100) NOT NULL,
  `price` varchar(100) NOT NULL,
  `price_int` int(255) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customer_receipt`
--

CREATE TABLE `customer_receipt` (
  `transaction_id` int(11) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `total` varchar(100) NOT NULL,
  `paid` varchar(255) NOT NULL,
  `change_string` varchar(255) NOT NULL,
  `balance` varchar(255) NOT NULL,
  `total_int` int(11) NOT NULL,
  `balance_int` int(11) NOT NULL,
  `change_int` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `year_int` int(11) NOT NULL,
  `month_string` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_receipt`
--

INSERT INTO `customer_receipt` (`transaction_id`, `customer_name`, `total`, `paid`, `change_string`, `balance`, `total_int`, `balance_int`, `change_int`, `date`, `year_int`, `month_string`) VALUES
(82, 'WQE', '₱363,369', '₱3,123,423', '₱2,760,054', '₱0', 363369, 0, 2760054, '2023-12-24', 2023, 'DECEMBER'),
(83, 'Gerald', '₱125,123', '₱126,000', '₱877', '₱0', 125123, 0, 877, '2023-12-24', 2023, 'DECEMBER'),
(84, 'Emjay', '₱363,369', '₱400,000', '₱36,631', '₱0', 363369, 0, 36631, '2024-01-01', 2024, 'JANUARY'),
(85, 'S', '₱121,123', '₱2,213', '₱0', '₱118,910', 121123, 118910, 0, '2024-01-01', 2024, 'JANUARY'),
(86, 'ds', '₱242,246', '₱2,341', '₱0', '₱239,905', 242246, 239905, 0, '2024-01-01', 2024, 'JANUARY'),
(87, 'S', '₱121,123', '₱21,344', '₱0', '₱99,779', 121123, 99779, 0, '2024-01-01', 2024, 'JANUARY'),
(88, 'sad', '₱121,123', '₱121,123', '₱0', '₱0', 121123, 0, 0, '2024-01-01', 2024, 'JANUARY'),
(89, 'Sd', '₱121,123', '₱121,123', '₱0', '₱0', 121123, 0, 0, '2024-01-01', 2024, 'JANUARY'),
(90, 'sad', '₱121,123', '₱1,231,231', '₱1,110,108', '₱0', 121123, 0, 1110108, '2024-01-01', 2024, 'JANUARY'),
(91, 'sad', '₱121,123', '₱123,123', '₱2,000', '₱0', 121123, 0, 2000, '2024-01-01', 2024, 'JANUARY'),
(92, 'Eubert', '₱363,369', '₱400,000', '₱36,631', '₱0', 363369, 0, 36631, '2024-01-01', 2024, 'JANUARY'),
(93, 'q', '₱121,123', '₱1,313,123', '₱1,192,000', '₱0', 121123, 0, 1192000, '2024-01-01', 2024, 'JANUARY'),
(94, 'sa', '₱121,123', '₱12,312', '₱0', '₱108,811', 121123, 108811, 0, '2024-01-01', 2024, 'JANUARY'),
(95, 'e', '₱121,123', '₱123,123', '₱2,000', '₱0', 121123, 0, 2000, '2024-01-01', 2024, 'JANUARY'),
(96, 'qweq', '₱121,123', '₱123,131', '₱2,008', '₱0', 121123, 0, 2008, '2024-01-01', 2024, 'JANUARY'),
(97, 's', '₱121,123', '₱12', '₱0', '₱121,111', 121123, 121111, 0, '2024-01-01', 2024, 'JANUARY'),
(98, 's', '₱121,123', '₱12', '₱0', '₱121,111', 121123, 121111, 0, '2024-01-01', 2024, 'JANUARY'),
(99, 'ASD', '₱242,246', '₱12,312,312', '₱12,070,066', '₱0', 242246, 0, 12070066, '2024-01-01', 2024, 'JANUARY');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `productName` varchar(100) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` varchar(255) NOT NULL,
  `price_int` int(255) NOT NULL,
  `status` varchar(100) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `productName`, `quantity`, `price`, `price_int`, `status`, `date`) VALUES
(79, 'LaMesa', 0, '₱121,123', 121123, 'Not Available', '2023-12-17'),
(80, 'BBQ Grill', 3, '₱121,123', 121123, 'Available', '2023-12-21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=379;

--
-- AUTO_INCREMENT for table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

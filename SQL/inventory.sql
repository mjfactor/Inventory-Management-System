-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 19, 2024 at 10:06 PM
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
  `transaction_id` varchar(255) NOT NULL,
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
  `id` int(11) NOT NULL,
  `transaction_id` varchar(255) NOT NULL,
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

INSERT INTO `customer_receipt` (`id`, `transaction_id`, `customer_name`, `total`, `paid`, `change_string`, `balance`, `total_int`, `balance_int`, `change_int`, `date`, `year_int`, `month_string`) VALUES
(167, '01152024204043', 'Emjay', '₱10,000', '₱10,000', '₱0', '₱0', 10000, 0, 0, '2024-01-15', 2024, 'JANUARY'),
(168, '01152024204304', 'Emjay', '₱10,000', '₱10,094', '₱94', '₱0', 10000, 0, 94, '2024-01-15', 2024, 'JANUARY'),
(169, '01152024205224', 'Emjay', '₱10,000', '₱10,000', '₱0', '₱0', 10000, 0, 0, '2024-01-15', 2024, 'JANUARY'),
(180, '01192024194836', 'sad', '₱1,500', '₱10,000,000', '₱9,998,500', '₱0', 1500, 0, 9998500, '2024-01-19', 2024, 'JANUARY'),
(181, '01192024204130', 'Gerald', '₱3,600', '₱4,000', '₱400', '₱0', 3600, 0, 400, '2024-01-19', 2024, 'JANUARY'),
(182, '01192024204149', 'Sad', '₱2,000', '₱2,000', '₱0', '₱0', 2000, 0, 0, '2024-01-19', 2024, 'JANUARY'),
(183, '01192024205426', 'sad', '₱4,000', '₱32,323', '₱28,323', '₱0', 4000, 0, 28323, '2024-01-19', 2024, 'JANUARY'),
(185, '01192024205507', 'sad', '₱2,000', '₱323', '₱0', '₱1,677', 2000, 1677, 0, '2024-01-19', 2024, 'JANUARY');

-- --------------------------------------------------------

--
-- Table structure for table `historybalancepay`
--

CREATE TABLE `historybalancepay` (
  `id` int(11) NOT NULL,
  `transaction_id` varchar(255) NOT NULL,
  `customerName` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `quantity` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL,
  `price_int` int(11) NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `historybalancepay`
--

INSERT INTO `historybalancepay` (`id`, `transaction_id`, `customerName`, `type`, `productName`, `quantity`, `price`, `price_int`, `date`) VALUES
(1, '01142024172008', 'Emjay', 'Pre-Made', 'LaMesa', '1', '₱121,123', 121123, '2024-01-14'),
(2, '01142024172008', 'Emjay', 'Pre-Made', 'BBQ Grill', '1', '₱121,123', 121123, '2024-01-14'),
(4, '01142024174324', 'sad', 'Pre-Made', 'BBQ Grill', '1', '₱121,123', 121123, '2024-01-14'),
(5, '01142024181607', 'Ito Na Ang Putok', 'Pre-Made', 'BBQ Grill', '1', '₱121,123', 121123, '2024-01-14'),
(6, '01142024181607', 'Ito Na Ang Putok', 'Customized', 'Tawas', '4', '₱120', 120, '2024-01-14'),
(8, '01142024181607', 'Salot', 'Pre-Made', 'BBQ Grill', '1', '₱121,123', 121123, '2024-01-14'),
(9, '01142024181607', 'Salot', 'Customized', 'WQ', '1', '₱2,123', 2123, '2024-01-14'),
(11, '01142024181915', 'EW', 'Pre-Made', 'LaMesa', '1', '₱121,123', 121123, '2024-01-14'),
(12, '01142024181915', 'EW', 'Pre-Made', 'BBQ Grill', '1', '₱121,123', 121123, '2024-01-14'),
(14, '01142024182450', 'Emjay Factor', 'Pre-Made', 'LaMesa', '1', '₱121,123', 121123, '2024-01-14'),
(15, '01142024182450', 'Emjay Factor', 'Pre-Made', 'BBQ Grill', '1', '₱121,123', 121123, '2024-01-14'),
(17, '01142024183958', 'SAD', 'Pre-Made', 'BBQ Grill', '3', '₱363,369', 363369, '2024-01-14'),
(18, '01142024184033', 'SA', 'Pre-Made', 'LaMesa', '1', '₱121,123', 121123, '2024-01-14'),
(19, '01142024184033', 'SA', 'Pre-Made', 'BBQ Grill', '1', '₱121,123', 121123, '2024-01-14'),
(21, '01152024101605', 'EAS', 'Customized', 'Mansion', '4', '₱8,000,000', 8000000, '2024-01-15'),
(22, '01152024101750', 'Emjay', 'Pre-Made', 'LaMesa', '1', '₱121,123', 121123, '2024-01-15'),
(23, '01152024102049', 'Gerald', 'Pre-Made', 'LaMesa', '1', '₱121,123', 121123, '2024-01-15'),
(24, '01152024110730', 'SAD', 'Pre-Made', 'LaMesa', '1', '₱121,123', 121123, '2024-01-15'),
(25, '01152024203701', 'S', 'Pre-Made', 'BBQ Grill', '2', '₱242,246', 242246, '2024-01-15'),
(26, '01152024204043', 'Emjay', 'Pre-Made', 'LaMesa', '1', '₱10,000', 10000, '2024-01-15'),
(27, '01152024204304', 'Emjay', 'Pre-Made', 'LaMesa', '1', '₱10,000', 10000, '2024-01-15'),
(28, '01152024205224', 'Emjay', 'Pre-Made', 'LaMesa', '1', '₱10,000', 10000, '2024-01-15'),
(29, '01152024210114', 'Eubert', 'Pre-Made', 'LaMesa', '2', '₱20,000', 20000, '2024-01-15'),
(30, '01162024150340', 'Ruzen', 'Pre-Made', 'LaMesa', '1', '₱10,000', 10000, '2024-01-16'),
(31, '01162024150619', 'sad', 'Pre-Made', 'BBQ Grill', '1', '₱2,000', 2000, '2024-01-16'),
(32, '01162024150653', 'SAD', 'Pre-Made', 'BBQ Grill', '1', '₱2,000', 2000, '2024-01-16'),
(33, '01162024151052', 's', 'Pre-Made', 'BBQ Grill', '1', '₱2,000', 2000, '2024-01-16'),
(34, '01162024151741', 'Sad', 'Pre-Made', 'BBQ Grill', '1', '₱2,000', 2000, '2024-01-16'),
(35, '01172024124426', 'WA', 'Pre-Made', 'BBQ Grill', '1', '₱2,000', 2000, '2024-01-17'),
(36, '01172024152209', 'Venght Baulos', 'Pre-Made', 'BBQ Grill', '3', '₱6,000', 6000, '2024-01-17'),
(37, '01172024163516', 'Gerald', 'Pre-Made', 'LaMesa', '3', '₱30,000', 30000, '2024-01-17'),
(38, '01172024170302', 'Titus', 'Customized', 'Steel Peniwise Statue', '3', '₱299,997', 299997, '2024-01-17'),
(39, '01192024194836', 'sad', 'Pre-Made', 'BBQ Grill', '1', '₱1,500', 1500, '2024-01-19'),
(40, '01192024204130', 'Gerald', 'Pre-Made', 'Burger Grill', '1', '₱2,000', 2000, '2024-01-19'),
(41, '01192024204130', 'Gerald', 'Pre-Made', 'Bottle Puncher', '2', '₱1,600', 1600, '2024-01-19'),
(43, '01192024204149', 'Sad', 'Pre-Made', 'Burger Grill', '1', '₱2,000', 2000, '2024-01-19'),
(44, '01192024205426', 'sad', 'Pre-Made', 'Burger Grill', '2', '₱4,000', 4000, '2024-01-19'),
(45, '01192024205439', 'sad', 'Pre-Made', 'Burger Grill', '1', '₱2,000', 2000, '2024-01-19'),
(46, '01192024205507', 'sad', 'Pre-Made', 'Burger Grill', '1', '₱2,000', 2000, '2024-01-19'),
(47, '01192024205540', 'sad', 'Pre-Made', 'Burger Grill', '1', '₱2,000', 2000, '2024-01-19');

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
(83, 'Burger Grill', 3, '₱2,000', 2000, 'Available', '2024-01-17'),
(84, 'Bottle Puncher', 8, '₱800', 800, 'Available', '2024-01-17'),
(85, 'Chair', 1, '₱3,000', 3000, 'Available', '2024-01-17'),
(86, 'Table', 1, '₱5,000', 5000, 'Available', '2024-01-17');

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
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `historybalancepay`
--
ALTER TABLE `historybalancepay`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=521;

--
-- AUTO_INCREMENT for table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=187;

--
-- AUTO_INCREMENT for table `historybalancepay`
--
ALTER TABLE `historybalancepay`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

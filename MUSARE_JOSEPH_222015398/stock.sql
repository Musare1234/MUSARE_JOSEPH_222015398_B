-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2024 at 12:48 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stock`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `username` varchar(50) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_at` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `firstname`, `lastname`, `username`, `phone`, `email`, `password`, `address`, `created_at`) VALUES
(1, 'admin', 'stator', 'admin', '0784334846', 'admin@company.com', '12345', 'Huye', '2022-11-18');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(255) NOT NULL,
  `name` varchar(60) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `description`, `created_at`) VALUES
(15, 'Game console', '#forbetterGaming', '2022-11-23'),
(17, 'PC', 'Only quality personal computers', '2022-11-24'),
(18, 'Phone', 'all kinds of phones are available', '2022-11-24'),
(25, 'External HDD', 'with big storage', '2022-11-29'),
(26, 'Electronic cookers', 'cooking made easi', '2022-12-23');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(255) NOT NULL,
  `barcode` int(255) NOT NULL,
  `category_id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `cost` int(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `total_cost` int(255) NOT NULL,
  `created_at` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `barcode`, `category_id`, `name`, `cost`, `quantity`, `total_cost`, `created_at`) VALUES
(38, 11111, 18, 'iphone 13 pro', 10000, 1, 10000, '2023-01-18'),
(20, 5858300, 17, 'Lenovo Think Pad', 350000, 80, 28000000, '2022-11-24'),
(21, 5858400, 17, 'Mac Book 2012', 600000, 390, 234000000, '2022-11-24'),
(22, 5859100, 18, 'TECNO POP 3', 60000, 5, 2, '2022-11-24'),
(25, 5959700, 17, 'POSITIVO', 150000, 55, 8250000, '2022-11-24'),
(33, 9090100, 25, '100TB HDD', 25000, 46166, 1154150000, '2022-11-29'),
(31, 9090900, 18, 'TECNO POP 10', 30000, 5, 150000, '2022-11-27'),
(37, 10001011, 18, 'NOKIA LUMIA M16', 500000, 30, 15000000, '2022-12-23');

-- --------------------------------------------------------

--
-- Table structure for table `product_in`
--

CREATE TABLE `product_in` (
  `id` int(255) NOT NULL,
  `barcode` int(255) NOT NULL,
  `supplier_id` int(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `cost` int(255) NOT NULL,
  `total` int(255) NOT NULL,
  `entry_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_out`
--

CREATE TABLE `product_out` (
  `id` int(255) NOT NULL,
  `barcode` int(255) NOT NULL,
  `shop_id` int(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `cost` int(255) NOT NULL,
  `total` int(255) NOT NULL,
  `exit_date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_out`
--

INSERT INTO `product_out` (`id`, `barcode`, `shop_id`, `quantity`, `cost`, `total`, `exit_date`) VALUES
(21, 11111, 17, 39, 10000, 390000, '2023-01-18');

-- --------------------------------------------------------

--
-- Table structure for table `shop`
--

CREATE TABLE `shop` (
  `id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `shop`
--

INSERT INTO `shop` (`id`, `name`, `address`, `created_at`) VALUES
(16, 'IT ZONE', 'KIGALI', '2022-12-12'),
(17, 'IT SOLUTIONS RWANDA', 'HUYE', '2022-12-12'),
(18, 'BEZA SHOP', 'MUSANZE', '2022-12-12'),
(19, 'RUBAVU TECH', 'RUBAVU', '2022-12-12'),
(20, 'NGONOKERA INNOVATION', 'NGORORERO', '2022-12-12');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id` int(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `firstname`, `lastname`, `phone`, `email`, `address`, `created_at`) VALUES
(3, 'Fiston ', 'Fiston ', '0785543215', 'Fistton@gmail.com', 'HUYE', '2022-11-22'),
(5, 'nsabimana', 'peter', '07858643213', 'peter254@gmail.com', 'huye', '2022-11-23'),
(6, 'CHAPO', 'EL CHALO', '0736578954', 'chapoelchol@gmail.com', 'RUSIZI', '2022-11-24'),
(7, 'RAFIKI', 'TUYIZERE', '0784356666', 'rafik@gmail.com', 'HUYE', '2022-11-24');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`barcode`),
  ADD UNIQUE KEY `name` (`name`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `product_ibfk_1` (`category_id`);

--
-- Indexes for table `product_in`
--
ALTER TABLE `product_in`
  ADD PRIMARY KEY (`id`),
  ADD KEY `barcode` (`barcode`),
  ADD KEY `supplier_id` (`supplier_id`);

--
-- Indexes for table `product_out`
--
ALTER TABLE `product_out`
  ADD PRIMARY KEY (`id`),
  ADD KEY `barcode` (`barcode`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `product_in`
--
ALTER TABLE `product_in`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `product_out`
--
ALTER TABLE `product_out`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `shop`
--
ALTER TABLE `shop`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product_in`
--
ALTER TABLE `product_in`
  ADD CONSTRAINT `product_in_ibfk_1` FOREIGN KEY (`barcode`) REFERENCES `product` (`barcode`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `product_in_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product_out`
--
ALTER TABLE `product_out`
  ADD CONSTRAINT `product_out_ibfk_1` FOREIGN KEY (`barcode`) REFERENCES `product` (`barcode`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `product_out_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

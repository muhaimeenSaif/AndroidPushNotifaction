-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2018 at 10:47 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `push_notification`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `token` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `firstName`, `lastName`, `email`, `token`) VALUES
(8, 'First Name', 'Last Name', 'Email', 'eWaMXZhI7GI:APA91bFzfZsDamrnHon9zCwcysqOrNZwOoIBE85eIxte67f4jK1I9l9ther7vfznEik9Sj6F8ndjTIlO2nuU-stIJycdm8WKhL3FuyKlPB_sejWzuRDyVVHAlETsziQojF8Vbr31JwVl'),
(9, 'First Name', 'Last Name', 'Email', 'eWaMXZhI7GI:APA91bFzfZsDamrnHon9zCwcysqOrNZwOoIBE85eIxte67f4jK1I9l9ther7vfznEik9Sj6F8ndjTIlO2nuU-stIJycdm8WKhL3FuyKlPB_sejWzuRDyVVHAlETsziQojF8Vbr31JwVl'),
(10, 'First Name', 'Last Name', 'Email', 'eWaMXZhI7GI:APA91bFzfZsDamrnHon9zCwcysqOrNZwOoIBE85eIxte67f4jK1I9l9ther7vfznEik9Sj6F8ndjTIlO2nuU-stIJycdm8WKhL3FuyKlPB_sejWzuRDyVVHAlETsziQojF8Vbr31JwVl'),
(11, 'First Name', 'Last Name', 'Email', 'eWaMXZhI7GI:APA91bFzfZsDamrnHon9zCwcysqOrNZwOoIBE85eIxte67f4jK1I9l9ther7vfznEik9Sj6F8ndjTIlO2nuU-stIJycdm8WKhL3FuyKlPB_sejWzuRDyVVHAlETsziQojF8Vbr31JwVl');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

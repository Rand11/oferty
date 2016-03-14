-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 14 Mar 2016, 15:12
-- Wersja serwera: 10.1.9-MariaDB
-- Wersja PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `springtutorial`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `content` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(60) NOT NULL,
  `username` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `messages`
--

INSERT INTO `messages` (`id`, `subject`, `content`, `name`, `email`, `username`) VALUES
(2, 'ssssuuuuuuuuuvvvvvvvv', 'gtgggggggg', 'Aaaaaa Bbbb', 'ffffff@sssssssss.pl', 'Admin123'),
(3, 'dhgfd', 'hgfdhgfdhgfhgfd', 'Aaaaaa Bbbb', 'ffffff@sssssssss.pl', 'Admin123'),
(4, 'gggggg', 'hhhhhhhhhhhhhhh', 'Aaaaaa Bbbb', 'ffffff@sssssssss.pl', 'Admin123'),
(5, 'gdfsjjjjjjjj', 'jjjjjjjjjjjjjjjjjjjjj', 'Aaaaaa Bbbb', 'ffffff@sssssssss.pl', 'Admin123'),
(6, 'ddddddddd', 'dddddddddddddddddddd', 'ajajajajaj', 'adr987@gmail.com', 'Admin123');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `offers`
--

CREATE TABLE `offers` (
  `id` int(11) NOT NULL,
  `text` text NOT NULL,
  `username` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `offers`
--

INSERT INTO `offers` (`id`, `text`, `username`) VALUES
(1, 'aaaaaaaaaaaaaaaaaa', NULL),
(8, 'ddddddddddddddddddddddddddddd', 'Admin123'),
(9, 'Sprzedam Opla.', 'adr987654');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `username` varchar(60) NOT NULL,
  `password` varchar(80) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `email` varchar(60) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `authority` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`, `email`, `name`, `authority`) VALUES
('Admin123', 'd7d66a2ce936e9440393810b867aeeb39062ee5f6deb05592626ab8f8076914f6d683e10d98021ed', 1, 'ffffff@sssssssss.pl', 'Aaaaaa Bbbb', 'ROLE_ADMIN'),
('Administrator', 'cc7da731f06ad1848db345bd09ff2dcf1ec475f7b682a9a61447f5c88562466cf02502fee9c447dc', 1, 'admin@admin.pl', '55555555', 'ROLE_USER'),
('adr987654', 'a90027666521b8aa73aacf83c8f0cf30c84400b54cb8fc097c8bb2e104ded525b87a535060306e33', 1, 'adr987@gmail.com', 'Adrian Zzzz', 'ROLE_USER'),
('adrian123', 'c230e00aaf378d76389bdbe27b4548749b450ec77d4a928d61d2b6407e0ef7f40057221c3c7747bc', 1, 'aaaaa@aaaaa.pl', 'Adrian Zzzzzzzz', 'ROLE_USER'),
('courier_demo', '8da9d259e15239a14dadbab8eda447978baf7fa02b420b0851b055e1b21ff09f5921aab66d5a477b', 1, 'admin@admin.pl', 'Aaaaaa Bb', 'ROLE_USER');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_messages_users1_idx` (`username`);

--
-- Indexes for table `offers`
--
ALTER TABLE `offers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `users_username` (`username`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT dla tabeli `offers`
--
ALTER TABLE `offers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `fk_messages_users1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- Ograniczenia dla tabeli `offers`
--
ALTER TABLE `offers`
  ADD CONSTRAINT `offers_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

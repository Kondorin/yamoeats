-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 16-03-2020 a las 18:42:00
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id11754299_yamoeats`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `noPedido` int(8) NOT NULL,
  `usuario` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `direccion` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `celular` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `codigoPizza` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nombrePizza` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `size` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `cantidad` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `total` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `fechaCompra` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastPosition` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`noPedido`, `usuario`, `direccion`, `celular`, `codigoPizza`, `nombrePizza`, `size`, `cantidad`, `total`, `fechaCompra`, `status`, `lastPosition`) VALUES
(1, 'charizard', 'calle tercera', '6623372818', '1', 'mexicana', '', '', '', '12-04-2020', 'finalizada', ''),
(2, 'laloelloco', 'dostres street', '3346494843', '1', 'mexicana', 'grande', '1', '$174.0', '2020-03-16', 'cancelada', ''),
(3, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '5', 'carnes frias', 'grande', '3', '$522.0', '2020-03-16', 'cancelada', ''),
(4, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '6', 'clasica', 'grande', '7', '$1218.0', '2020-03-16', 'cancelada', ''),
(5, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '5', 'carnes frias', 'grande', '8', '$1392.0', '2020-03-16', 'cancelada', ''),
(6, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '5', 'carnes frias', 'grande', '5', '$869.9999999999999', '2020-03-16', 'cancelada', ''),
(7, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '1', 'mexicana', 'grande', '1', '$174.0', '2020-03-16', 'cancelada', ''),
(8, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '4', 'vegetariana ', 'grande', '3', '$522.0', '2020-03-16', 'cancelada', ''),
(9, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '2', 'italiana', 'grande', '3', '$522.0', '2020-03-16', 'cancelada', ''),
(10, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '3', 'hawaiana', 'grande', '3', '$522.0', '2020-03-16', 'cancelada', ''),
(11, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '1', 'mexicana', 'grande', '1', '$174.0', '2020-03-16', 'cancelada', ''),
(12, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '4', 'vegetariana ', 'grande', '1', '$174.0', '2020-03-16', 'cancelada', ''),
(13, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '6', 'clasica', 'grande', '6', '$1044.0', '2020-03-16', 'cancelada', ''),
(14, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '3', 'hawaiana', 'grande', '2', '$348.0', '2020-03-16', 'cancelada', ''),
(15, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '1', 'mexicana', 'grande', '1', '$174.0', '2020-03-16', 'cancelada', ''),
(16, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '6', 'clasica', 'grande', '1', '$174.0', '2020-03-16', 'cancelada', ''),
(17, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '5', 'carnes frias', 'grande', '1', '$174.0', '2020-03-16', 'cancelada', ''),
(18, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '6', 'clasica', 'grande', '1', '$174.0', '2020-03-16', 'cancelada', ''),
(19, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '1', 'mexicana', 'grande', '1', '$174.0', '2020-03-16', 'cancelada', '29.04563773,-110.9718038'),
(20, 'mrkondor', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', '6623372818', '5', 'carnes frias', 'grande', '1', '$174.0', '2020-03-16', 'pendiente', NULL),
(21, 'laloelloco', 'dostres street', '3346494843', '1', 'mexicana', 'grande', '3', '$522.0', '2020-03-16', 'pendiente', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pizzas`
--

CREATE TABLE `pizzas` (
  `codigo` int(8) NOT NULL,
  `nombrePizza` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `size` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ingredientes` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `precio` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `pizzas`
--

INSERT INTO `pizzas` (`codigo`, `nombrePizza`, `size`, `ingredientes`, `precio`) VALUES
(1, 'mexicana', 'grande', 'chorizo, tocino, chile jalapeno', '150'),
(2, 'italiana', 'grande', 'pepperoni, aceitunas, cebolla, pimiento verde', '150'),
(3, 'hawaiana', 'grande', 'jamon, cereza ,pina', '150'),
(4, 'vegetariana ', 'grande', 'champinon, elote, aceituna negra, pimiento verde', '150'),
(5, 'carnes frias', 'grande', 'jamon, salchicha, tocino', '150'),
(6, 'clasica', 'grande', 'jamon, champinon, tocino', '150');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(8) NOT NULL,
  `nombre` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `apellido` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `contrasena` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `celular` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `direccion` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tipoUsuario` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `email`, `contrasena`, `celular`, `direccion`, `tipoUsuario`) VALUES
(1, 'Alejandro', 'Cabrera', 'alex', 'admin', '6623372818', 'calle tercera #132', 'administrador'),
(2, 'chorizo', 'de pizza', 'charizard', 'admin', '66233451261', 'pokemon', 'cliente'),
(3, 'Alejandro', 'Cabrera', 'alejandro', 'admin', '6623372818', 'calle tercera', 'cliente'),
(4, 'lalo', 'el loco', 'laloelloco', 'admin', '3346494843', 'dostres street', 'cliente'),
(5, 'Alex', 'Cabrera', 'mrkondor', 'admin', '6623372818', 'Avenida Tercera #132 entre Lauro Galvez y Alfonso Armenta', 'cliente');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`noPedido`);

--
-- Indices de la tabla `pizzas`
--
ALTER TABLE `pizzas`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `noPedido` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `pizzas`
--
ALTER TABLE `pizzas`
  MODIFY `codigo` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

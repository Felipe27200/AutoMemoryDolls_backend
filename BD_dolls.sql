-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: auto_memory_dolls
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auto_memory_dolls`
--

DROP TABLE IF EXISTS `auto_memory_dolls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auto_memory_dolls` (
  `auto_memory_doll_id` bigint NOT NULL AUTO_INCREMENT,
  `edad` int NOT NULL,
  `estado` bit(1) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`auto_memory_doll_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auto_memory_dolls`
--

LOCK TABLES `auto_memory_dolls` WRITE;
/*!40000 ALTER TABLE `auto_memory_dolls` DISABLE KEYS */;
INSERT INTO `auto_memory_dolls` VALUES (1,20,_binary '','Doll 1'),(3,23,_binary '','Doll 13');
/*!40000 ALTER TABLE `auto_memory_dolls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carta_estados`
--

DROP TABLE IF EXISTS `carta_estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carta_estados` (
  `carta_estados_id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`carta_estados_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carta_estados`
--

LOCK TABLES `carta_estados` WRITE;
/*!40000 ALTER TABLE `carta_estados` DISABLE KEYS */;
INSERT INTO `carta_estados` VALUES (1,'borrador'),(2,'enviado'),(3,'archivado'),(4,'revisado');
/*!40000 ALTER TABLE `carta_estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartas`
--

DROP TABLE IF EXISTS `cartas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartas` (
  `carta_id` bigint NOT NULL AUTO_INCREMENT,
  `contenido` text NOT NULL,
  `fecha_creacion` date NOT NULL,
  `motivo` varchar(255) NOT NULL,
  `carta_estados_id` bigint NOT NULL,
  `auto_memory_doll_id` bigint NOT NULL,
  `cliente_id` bigint NOT NULL,
  PRIMARY KEY (`carta_id`),
  KEY `FK9xptdv2q7h66x440at7d8ta6l` (`carta_estados_id`),
  KEY `FKi0gkfc7wo2kypike9aixiwlpv` (`auto_memory_doll_id`),
  KEY `FKrgj3req69td2hhdxatylifwe6` (`cliente_id`),
  CONSTRAINT `FK9xptdv2q7h66x440at7d8ta6l` FOREIGN KEY (`carta_estados_id`) REFERENCES `carta_estados` (`carta_estados_id`),
  CONSTRAINT `FKi0gkfc7wo2kypike9aixiwlpv` FOREIGN KEY (`auto_memory_doll_id`) REFERENCES `auto_memory_dolls` (`auto_memory_doll_id`),
  CONSTRAINT `FKrgj3req69td2hhdxatylifwe6` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartas`
--

LOCK TABLES `cartas` WRITE;
/*!40000 ALTER TABLE `cartas` DISABLE KEYS */;
INSERT INTO `cartas` VALUES (1,'Primera carta y contenidosssssssss','2025-08-17','Primera carta',3,3,1),(3,'Tercera carta y contenido','2025-08-17','Tercera carta',2,1,1),(4,'Tercera carta y contenido','2025-08-17','Tercera carta',2,1,1),(6,'Cartas para cambiar','2025-08-19','Segunda Carta de pruebas',3,3,5),(7,'Cartas para cambiar','2025-08-19','Segunda Carta de pruebas',1,1,5),(8,'Cartas para cambiar','2025-08-19','Segunda Carta de pruebas',3,3,5),(9,'Cartas para cambiar','2025-08-19','Segunda Carta de pruebas',1,1,5);
/*!40000 ALTER TABLE `cartas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `cliente_id` bigint NOT NULL AUTO_INCREMENT,
  `info_contacto` varchar(255) NOT NULL,
  `ciudad` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`cliente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'cliente1@gmail.com','Bogotá','cliente 1'),(5,'cliente2@gmail.com','Medellín','cliente 3');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'auto_memory_dolls'
--

--
-- Dumping routines for database 'auto_memory_dolls'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-19 23:23:44

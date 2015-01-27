-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost:3306
-- Généré le: Mar 27 Janvier 2015 à 08:57
-- Version du serveur: 5.5.37-0ubuntu0.13.10.1
-- Version de PHP: 5.5.3-1ubuntu2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `ACIDDB`
--

-- --------------------------------------------------------

--
-- Structure de la table `Board`
--

CREATE TABLE IF NOT EXISTS `Board` (
  `id_board` int(11) NOT NULL AUTO_INCREMENT,
  `id_project` int(11) NOT NULL,
  `id_type` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id_board`),
  KEY `id_project` (`id_project`),
  KEY `id_type` (`id_type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `Board`
--

INSERT INTO `Board` (`id_board`, `id_project`, `id_type`, `name`) VALUES
(1, 1, 1, 'Backlog'),
(2, 1, 2, 'BugFix'),
(3, 1, 3, 'Sprint1');

-- --------------------------------------------------------

--
-- Structure de la table `Board_list`
--

CREATE TABLE IF NOT EXISTS `Board_list` (
  `id_board_list` int(11) NOT NULL AUTO_INCREMENT,
  `id_board` int(11) NOT NULL,
  `id_list` int(11) NOT NULL,
  PRIMARY KEY (`id_board_list`),
  KEY `id_board` (`id_board`,`id_list`),
  KEY `id_list` (`id_list`),
  KEY `id_list_2` (`id_list`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `Board_list`
--

INSERT INTO `Board_list` (`id_board_list`, `id_board`, `id_list`) VALUES
(5, 1, 5),
(1, 2, 1),
(2, 2, 2),
(3, 2, 3),
(4, 2, 4),
(6, 3, 1),
(7, 3, 2),
(8, 3, 3),
(9, 3, 4);

-- --------------------------------------------------------

--
-- Structure de la table `List`
--

CREATE TABLE IF NOT EXISTS `List` (
  `id_list` int(11) NOT NULL AUTO_INCREMENT COMMENT '(state xD)',
  `label` varchar(20) NOT NULL,
  PRIMARY KEY (`id_list`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `List`
--

INSERT INTO `List` (`id_list`, `label`) VALUES
(1, 'TODO'),
(2, 'DOING'),
(3, 'DONE'),
(4, 'DONE DONE'),
(5, 'DO NEXT');

-- --------------------------------------------------------

--
-- Structure de la table `Project`
--

CREATE TABLE IF NOT EXISTS `Project` (
  `id_project` int(11) NOT NULL AUTO_INCREMENT,
  `id_owner` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id_project`),
  KEY `id_owner` (`id_owner`),
  KEY `id_owner_2` (`id_owner`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `Project`
--

INSERT INTO `Project` (`id_project`, `id_owner`, `name`) VALUES
(1, 3, 'WindowsRebirth'),
(2, 4, 'coucou');

-- --------------------------------------------------------

--
-- Structure de la table `Project_user`
--

CREATE TABLE IF NOT EXISTS `Project_user` (
  `id_project_user` int(11) NOT NULL AUTO_INCREMENT,
  `id_project` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_project_user`),
  KEY `id_project` (`id_project`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `Project_user`
--

INSERT INTO `Project_user` (`id_project_user`, `id_project`, `id_user`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `Task`
--

CREATE TABLE IF NOT EXISTS `Task` (
  `id_task` int(11) NOT NULL AUTO_INCREMENT,
  `id_board` int(11) NOT NULL,
  `id_list` int(11) NOT NULL,
  `label` varchar(64) NOT NULL,
  `description` varchar(512) NOT NULL,
  `priority` int(11) NOT NULL,
  PRIMARY KEY (`id_task`),
  KEY `id_board` (`id_board`,`id_list`),
  KEY `id_list` (`id_list`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `Task`
--

INSERT INTO `Task` (`id_task`, `id_board`, `id_list`, `label`, `description`, `priority`) VALUES
(1, 1, 5, 'Tache 1 Backlog', 'gniagniagnia gniagniagnia gniagniagnia', 5),
(2, 2, 2, 'Reparer l''OS', 'Il marche plus', 0),
(3, 3, 1, 'Butineur explorer', 'Nouveau butineur lent et incompatible avec les feuilles css.', 3),
(4, 3, 3, 'Firewall qui bloque tout', 'Creer un pare feu qui bloque tout ce qui fait pas, et laisse passer tout le reste.', 4);

-- --------------------------------------------------------

--
-- Structure de la table `Type`
--

CREATE TABLE IF NOT EXISTS `Type` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(20) NOT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `Type`
--

INSERT INTO `Type` (`id_type`, `label`) VALUES
(1, 'Backlog'),
(2, 'BugFix'),
(3, 'Sprint');

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE IF NOT EXISTS `User` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(128) CHARACTER SET latin1 NOT NULL,
  `name` varchar(128) CHARACTER SET latin1 NOT NULL,
  `password` varchar(128) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Contenu de la table `User`
--

INSERT INTO `User` (`id_user`, `email`, `name`, `password`) VALUES
(1, 'engageGuignol@gmail.com', 'guignol', 'chefouichef'),
(2, 'blancheNeige@gmail.com', 'blancheNeige', 'jamaisVuUnTasDeMAussiHautQueCa'),
(3, 'sergentInstructeur@dtc.com', 'Hartman', 'etrangleToi'),
(4, 'max@ime.tou', 'max', '61be55a8e2f6b4e172338bddf184d6dbee29c98853e0a0485ecee7f27b9af0b4'),
(5, 'lamenace@coucou.salu', 'Max', 'ec9c3a34e791bda21bbcb69ea0eb875857497ed48c75771b3d1adb573ce791'),
(6, 'coucou@leroxor.com', 'Max', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),
(7, 'lekikoo@gmail.com', 'greg', '9adfb0a6d03beb7141d8ec278d6d9fef9259d12cd23d50f7fb221ae6cabd5');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Board`
--
ALTER TABLE `Board`
  ADD CONSTRAINT `project/board` FOREIGN KEY (`id_project`) REFERENCES `Project` (`id_project`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `project/type` FOREIGN KEY (`id_type`) REFERENCES `Type` (`id_type`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Board_list`
--
ALTER TABLE `Board_list`
  ADD CONSTRAINT `board-board` FOREIGN KEY (`id_board`) REFERENCES `Board` (`id_board`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `list-list` FOREIGN KEY (`id_list`) REFERENCES `List` (`id_list`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Project`
--
ALTER TABLE `Project`
  ADD CONSTRAINT `id_owner/id_user` FOREIGN KEY (`id_owner`) REFERENCES `User` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Project_user`
--
ALTER TABLE `Project_user`
  ADD CONSTRAINT `idProject` FOREIGN KEY (`id_project`) REFERENCES `Project` (`id_project`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `idUser` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Task`
--
ALTER TABLE `Task`
  ADD CONSTRAINT `board` FOREIGN KEY (`id_board`) REFERENCES `Board` (`id_board`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `list` FOREIGN KEY (`id_list`) REFERENCES `List` (`id_list`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

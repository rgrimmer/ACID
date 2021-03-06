-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 28 Janvier 2015 à 10:00
-- Version du serveur: 5.5.41-0ubuntu0.14.04.1
-- Version de PHP: 5.5.9-1ubuntu4.5

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
(1, 1, 5),
(2, 2, 1),
(3, 2, 2),
(4, 2, 3),
(5, 2, 4),
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
(5, 'TODO NEXT');

-- --------------------------------------------------------

--
-- Structure de la table `Project`
--

CREATE TABLE IF NOT EXISTS `Project` (
  `id_project` int(11) NOT NULL AUTO_INCREMENT,
  `id_owner` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `jenkins_url` varchar(256) DEFAULT NULL,
  `sonar_url` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id_project`),
  KEY `id_owner` (`id_owner`),
  KEY `id_owner_2` (`id_owner`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `Project`
--

INSERT INTO `Project` (`id_project`, `id_owner`, `name`, `jenkins_url`, `sonar_url`) VALUES
(1, 3, 'org:AccountManager', NULL, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `Project_user`
--

INSERT INTO `Project_user` (`id_project_user`, `id_project`, `id_user`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 6);

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
  `id_sonar` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id_task`),
  KEY `id_board` (`id_board`,`id_list`),
  KEY `id_list` (`id_list`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `Task`
--

INSERT INTO `Task` (`id_task`, `id_board`, `id_list`, `label`, `description`, `priority`, `id_sonar`) VALUES
(1, 1, 5, 'Tache 1 Backlog', 'gniagniagnia gniagniagnia gniagniagnia', 5, NULL),
(2, 2, 2, 'Reparer l''OS', 'Il marche plus', 0, NULL),
(3, 3, 1, 'Butineur explorer', 'Nouveau butineur lent et incompatible avec les feuilles css.', 3, NULL),
(4, 3, 3, 'Firewall qui bloque tout', 'Creer un pare feu qui bloque tout ce qui fait pas, et laisse passer tout le reste.', 4, NULL);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Contenu de la table `User`
--

INSERT INTO `User` (`id_user`, `email`, `name`, `password`) VALUES
(1, 'engageGuignol@gmail.com', 'guignol', 'ae5eebff12dd75af2fc959fa5ef8bf7c23fb4fde38a0176be14c991d8ff2c440'),
(2, 'blancheNeige@gmail.com', 'blancheNeige', 'ae5eebff12dd75af2fc959fa5ef8bf7c23fb4fde38a0176be14c991d8ff2c440'),
(3, 'sergentInstructeur@dtc.com', 'Hartman', 'ae5eebff12dd75af2fc959fa5ef8bf7c23fb4fde38a0176be14c991d8ff2c440'),
(6, 'test@test.fr', 'Test', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

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
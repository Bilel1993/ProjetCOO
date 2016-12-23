--
-- Structure de la table `Amis`
--

CREATE TABLE IF NOT EXISTS `Amis` (
  `Pers_Send` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Pers_Receive` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Message` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `origine_Message` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Amis`
--

INSERT INTO `Amis` (`Pers_Send`, `Pers_Receive`, `Message`, `origine_Message`) VALUES
('test', 'aloui93', 'bonjour', 'test'),
('Xx_lulu_xX', 'admin', 'hhhhhhhhhhhhhhhhh', 'admin'),
('Xx_lulu_xX', 'test', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `CentreInteret`
--

CREATE TABLE IF NOT EXISTS `CentreInteret` (
  `Id` int(3) NOT NULL,
  `Nom` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `CentreInteret`
--

INSERT INTO `CentreInteret` (`Id`, `Nom`) VALUES
(1, 'Football'),
(2, 'BasketBall'),
(3, 'Jeux Videos'),
(4, 'Chanson'),
(5, 'Shopping'),
(6, 'Dessin'),
(7, 'informatique'),
(8, 'Pokemon'),
(9, 'Lecture'),
(10, 'BaseBall'),
(11, 'Natation');

-- --------------------------------------------------------

--
-- Structure de la table `DemandeAmis`
--

CREATE TABLE IF NOT EXISTS `DemandeAmis` (
  `Pers_Demande` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Pers_Receive` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `IsAmis` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `DemandeAmis`
--

INSERT INTO `DemandeAmis` (`Pers_Demande`, `Pers_Receive`, `IsAmis`) VALUES
('clem', 'test', 1),
('test', 'aloui93', 1),
('Xx_lulu_xX', 'admin', 1),
('Xx_lulu_xX', 'test', 1);

-- --------------------------------------------------------

--
-- Structure de la table `DemandeGroupe`
--

CREATE TABLE IF NOT EXISTS `DemandeGroupe` (
  `ID_Groupe` int(10) NOT NULL,
  `Pers_Receive` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Decision` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `DemandeGroupe`
--

INSERT INTO `DemandeGroupe` (`ID_Groupe`, `Pers_Receive`, `Decision`) VALUES
(1, 'test', 1),
(2, 'aloui93', 1),
(2, 'Xx_lulu_xX', 1),
(3, 'aloui93', 0);

-- --------------------------------------------------------

--
-- Structure de la table `Groupe`
--

CREATE TABLE IF NOT EXISTS `Groupe` (
  `ID_Groupe` int(11) NOT NULL,
  `NomGroupe` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `NomComptePers` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Groupe`
--

INSERT INTO `Groupe` (`ID_Groupe`, `NomGroupe`, `NomComptePers`) VALUES
(1, 'Juventus', 'aloui93'),
(2, 'lannister', 'test'),
(3, 'tester', 'test');

-- --------------------------------------------------------

--
-- Structure de la table `GroupeChat`
--

CREATE TABLE IF NOT EXISTS `GroupeChat` (
  `NomGroupe` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Pers_Receive` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `Message` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `origine_Message` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `GroupeChat`
--

INSERT INTO `GroupeChat` (`NomGroupe`, `Pers_Receive`, `Message`, `origine_Message`) VALUES
('Juventus', 'aloui93', 'on est laaaaaaa', 'aloui93'),
('Juventus', 'test', 'on est laaaaaaa', 'aloui93'),
('lannister', 'aloui93', 'Bonjour le groupe', 'bla'),
('lannister', 'test', 'Bonjour le groupe', 'bla'),
('lannister', 'Xx_lulu_xX', 'Bonjour le groupe', 'bla');

-- --------------------------------------------------------

--
-- Structure de la table `InteretPersonne`
--

CREATE TABLE IF NOT EXISTS `InteretPersonne` (
  `idInteret` int(3) NOT NULL,
  `NomComptePers` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Personne`
--

CREATE TABLE IF NOT EXISTS `Personne` (
  `NomComptePers` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `NomPers` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `PrenomPers` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `PasswordPers` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `isModerateur` tinyint(1) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `Personne`
--

INSERT INTO `Personne` (`NomComptePers`, `NomPers`, `PrenomPers`, `PasswordPers`, `isModerateur`, `isAdmin`) VALUES
('Admin', 'Boss', 'Admin', 'admin', 0, 1),
('aloui93', 'aloui', 'bilel', 'test', 0, 0),
('bla', 'blabla', 'bla', 'bla', 0, 0),
('clem5555', 'bubu', 'clemclem', 'test1', 0, 0),
('jean', 'jean', 'jean', 'jean', 0, 0),
('test', 'tester', 'test', 'test', 0, 0),
('test3', 'vourré', 'lucas', 'test', 0, 0),
('Xx_Lulu_xX', 'Bourré', 'Lucas', 'test', 0, 0);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Amis`
--
ALTER TABLE `Amis`
 ADD PRIMARY KEY (`Pers_Send`,`Pers_Receive`);

--
-- Index pour la table `CentreInteret`
--
ALTER TABLE `CentreInteret`
 ADD PRIMARY KEY (`Id`);

--
-- Index pour la table `DemandeAmis`
--
ALTER TABLE `DemandeAmis`
 ADD PRIMARY KEY (`Pers_Demande`,`Pers_Receive`);

--
-- Index pour la table `DemandeGroupe`
--
ALTER TABLE `DemandeGroupe`
 ADD PRIMARY KEY (`ID_Groupe`,`Pers_Receive`);

--
-- Index pour la table `Groupe`
--
ALTER TABLE `Groupe`
 ADD PRIMARY KEY (`ID_Groupe`), ADD KEY `fk_Personne` (`NomComptePers`);

--
-- Index pour la table `GroupeChat`
--
ALTER TABLE `GroupeChat`
 ADD PRIMARY KEY (`NomGroupe`,`Pers_Receive`);

--
-- Index pour la table `InteretPersonne`
--
ALTER TABLE `InteretPersonne`
 ADD PRIMARY KEY (`idInteret`,`NomComptePers`), ADD KEY `NomComptePers` (`NomComptePers`);

--
-- Index pour la table `Personne`
--
ALTER TABLE `Personne`
 ADD PRIMARY KEY (`NomComptePers`);

--

--
-- Contraintes pour la table `Groupe`
--
ALTER TABLE `Groupe`
ADD CONSTRAINT `fk_Personne` FOREIGN KEY (`NomComptePers`) REFERENCES `Personne` (`NomComptePers`);

--
-- Contraintes pour la table `InteretPersonne`
--
ALTER TABLE `InteretPersonne`
ADD CONSTRAINT `InteretPersonne_ibfk_1` FOREIGN KEY (`idInteret`) REFERENCES `CentreInteret` (`Id`),
ADD CONSTRAINT `InteretPersonne_ibfk_2` FOREIGN KEY (`NomComptePers`) REFERENCES `Personne` (`NomComptePers`);

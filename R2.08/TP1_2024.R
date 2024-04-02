# PREMIER TP EN R
# Sur Chamillo dans la rubrique Liens vous trouverez
# des sites pour avoir des ressources sur R

# charger les packages utiles (dont ggplot2 pour les graphiques)
library(ggplot2)
library(dplyr)

# On utilise le jeu de données mpg du package ggplot2
# taper dans l'onglet "Help" fenêtre en bas à droite le mot "mpg"
# vous verrez alors ce que contient le fichier mpg

# le dièse est le caractère pour commenter du code R

############## Dans la suite du TP #################
######### lire les commentaires donnés #############
######### et répondre aux questions ! ##############
####################################################

# Pour chacune des 11 variables dire si elles sont qualitatives (ordinales/nominales)
# ou si elles sont quantitatives
# manufacturer -> qualitive nominale
# model -> qualitative nominale
# displ -> quantitative
# year -> qualitative ordinale


# Pour voir les 10 premières lignes du fichier
# Pour lancer la commande ci-dessous appuyer sur "Run" en haut de cette fenêtre
head(mpg, 10)

# Pour connaitre les modalités de la variable manufacturer
mpg %>% distinct(manufacturer)
# Combien y-a-t'il de fabricants présents dans le jeu de données ?
# Il y a 15 fabricants dans le jeu de données.

# Combien y-a-t'il d'années dans le fichier ?
# Taper la commande pour répondre à cette question
mpg %>% distinct(year)
# Il y a 2 années différentes dans ce jeu de données.

# Pour connaitre le nombre de voitures par constructeur
mpg %>%
  group_by(manufacturer) %>%
  summarise(Effectif = n())
# Quelle est la marque la plus représentée dans ce jeu de données ?
#  
# C'est dodge.




# Faire son premier graphique
# Nuages de points
ggplot(mpg, aes(x = displ, y = hwy)) +
  geom_point()
# information : https://fr.wikipedia.org/wiki/Cylindr%C3%A9e
# Comment est définie hwy ? (attention : il s'agit de voitures américaines)
# Que constate t'on sur ce graphique ?
# 
# Plus le volume des pistons est petit plus il consomme sur autoroute 


#####################################################################
####### Faire le nuage de points consommation en ville en fonction ##
####### de la consommation sur autoroute ############################
#####################################################################



# D'autres graphiques
# Histogramme de base
ggplot(mpg, aes(x = displ)) +
  geom_histogram()
# quels sont les défauts de ce graphique crée automatiquement ?
# Il y a des trous entre différentes valeurs, pas de bords et données mal définies.


# Histogramme amélioré
# breaks = définition à la main des limites des intervalles pour les barres
# seq(0,7,0.5) = suite des chiffres de 0 à 7 par pas de 0.5
# fill = couleur de l'intérieur des barres
# color = couleur du contour des barres
# alpha = coefficient de transparence
# la commande scale_x_continous fixe les étiquettes de graduation sur l'axe des x, ici
# de 0 à 7 par pas de 1
ggplot(mpg, aes(x = displ)) +
  geom_histogram(breaks = seq(0, 7, 0.5), fill = "grey40", color = "black", alpha = 0.4) +
  scale_x_continuous(breaks = seq(0, 7, 1))
# Combien y-a t'il de voitures ayant une cylindrée entre 4.5 litres et 5.5 litres ?
# Il y a 48 voitures entre 4.5 litres et 5.5.


# Histogramme INCORRECT !
ggplot(mpg, aes(x = displ)) +
  geom_histogram(breaks = c(1.5, 2, 2.5, 3, 3.5, 4, 5, 5.5, 6, 6.5, 7), fill = "grey40", color = "black", alpha = 0.4) +
  scale_x_continuous(breaks = seq(0, 7, 1))
# Qu'est-ce qui cloche ?
# les données entre 4 et 5L sont les seules regroupés.

# Histogramme avec la densité
ggplot(mpg, aes(x = displ)) +
  geom_histogram(aes(y = ..density..), breaks = c(1.5, 2, 2.5, 3, 3.5, 4, 5, 5.5, 6, 6.5, 7), fill = "grey40", color = "black", alpha = 0.4) +
  scale_x_continuous(breaks = seq(0, 7, 1))
# Comment calcule t-on la densité pour l'intervalle [4;5] ?
# On fait la proportion/ taille de l'intervalle. (38/234)


# Plusieurs boxplot
ggplot(mpg, aes(x = drv, y = displ)) +
  geom_boxplot()
# Pour les voitures ayant 4 roues motrices quelle est la cylindrée médiane ?
#La cylindrée médiane est 4.

# Pour les voitures avec roues motrices avant quelle est la valeur du premier quartile ?
# Pour les voitures avec des roues motrices avant la valeur du premier quartille est 2.

# Que peut-on dire avec ce graphique ?
# Il permet de visualiser le nombre de cynlindrés qu'a une voiture en fonction de ses roues motrices.

# Graphique en barres
ggplot(mpg) +
  geom_bar(aes(x = manufacturer)) # par défaut l'axe des y est l'effectif
# Combien y-a t'il de voitures de la marque "Subaru" dans ce jeu de données ?
# Il y en a 14.

# Quel est le fabricant ayant le plus de voitures dans ce jeu de données ?
# C'est le fabricant dogde.

# Le même graphique avec des pourcentages
ggplot(mpg, aes(x = manufacturer, y = ..count.. / sum(..count..))) +
  geom_bar()

# Le même avec l'axe des y en pourcentage
ggplot(mpg, aes(x = manufacturer, y = ..count.. / sum(..count..))) +
  geom_bar() +
  scale_y_continuous(labels = scales::percent)

# Le même avec la commande function(x) -length(x)  qui permet de réordonner
# les modalités du fabriquant de la plus fréquente
# à la moins fréquente
ggplot(mpg, aes(x = reorder(manufacturer, manufacturer, function(x) -length(x)), y = ..count.. / sum(..count..))) +
  geom_bar() +
  scale_y_continuous(labels = scales::percent)

# enlever les légendes des axes
ggplot(mpg, aes(x = reorder(manufacturer, manufacturer, function(x) -length(x)), y = ..count.. / sum(..count..))) +
  geom_bar() +
  scale_y_continuous(labels = scales::percent) +
  theme(axis.title = element_blank())

# le même avec des légendes définies sur les axes
ggplot(mpg, aes(x = reorder(manufacturer, manufacturer, function(x) -length(x)), y = ..count.. / sum(..count..))) +
  geom_bar() +
  xlab("Fabriquant") +
  ylab("Pourcentage de voitures") +
  scale_y_continuous(labels = scales::percent)

# le même avec une légende pour l'axe des y et des labels pivotés sur l'axe des x
ggplot(mpg, aes(x = reorder(manufacturer, manufacturer, function(x) -length(x)), y = ..count.. / sum(..count..))) +
  geom_bar() +
  theme(axis.title.x = element_blank()) +
  ylab("Pourcentage de voitures") +
  scale_y_continuous(labels = scales::percent) +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))

################### A FAIRE ########################################
############# AVANT DE CONTINUER ###################################
### graphique adapté pour le type de motorisation = variable drv ###
### graphique adapté pour les variables hwy et cty               ###
####################################################################
ggplot(mpg, aes(x = reorder(drv, drv, function(x) -length(x)), y = ..count.. / sum(..count..))) +
  geom_bar() +
  xlab("Type de motorisation") +
  ylab("Nombre de voitures") +
  scale_y_continuous(labels = scales::percent)

ggplot(mpg) +
  geom_histogram(aes(x = hwy), breaks = seq(0, 40, 5), fill = "grey40", color = "black", alpha = 0.4)+
  geom_histogram(aes(x = cty), breaks = seq(0, 40, 5), fill = "grey20", color = "black", alpha = 0.4)+
  labs(x = "Consommation en l/km", y = "Nombre de voitures") +
  theme_minimal()



##### Suite du TP #################################################
###################################################################
### Utiliser dplyr pour éditer certaines statistiques ##############
###################################################################
##### Pour une moyenne simple
mpg %>% summarise(Moyenne = mean(cty))
##### Pour une moyenne simple avec l'effectif
mpg %>% summarise(Moyenne = mean(cty), nbre = n())
##### Pour une moyenne par groupe ici l'année
mpg %>%
  group_by(year) %>%
  summarise(Moyenne = mean(cty))
# Quelle info avons nous ?
# Nous obtenons la moyenne de consomation des voitures sur route.
# Puis nous l'obtenons rapporté 

##### Pour une moyenne par groupe ici par type de motorisation
mpg %>%
  group_by(drv) %>%
  summarise(Moyenne = mean(cty))
# Quelle type de motorisation à la consommation en ville moyenne la plus élevée ?


##### Avec d'autres statistiques
###### Pour la variable constructeur
mpg %>%
  group_by(manufacturer) %>%
  summarise(Effectif = n()) %>%
  mutate(Pourcentage = round(Effectif / sum(Effectif), 2))

###### Pour la variable constructeur
###### ordonnée par pourcentage
mpg %>%
  group_by(manufacturer) %>%
  summarise(Effectif = n()) %>%
  mutate(Pourcentage = round(Effectif / sum(Effectif), 2)) %>%
  arrange(desc(Pourcentage))



mpg %>%
  group_by(drv) %>%
  summarise(Effectif = n(), Q1 = first(cty), Mediane = median(cty), Q2 = last(cty), Moyenne = mean(cty))

###### Statistiques pour les voitures Audi et Ford; la barre verticale indique le "ou"
mpg %>%
  filter(manufacturer == "audi" | manufacturer == "ford") %>%
  summarise(Effectif = n(), MoyenneCty = mean(cty), Moyennehwy = mean(hwy))
###### Statistiques pour chacune des marques Audi et Ford
mpg %>%
  filter(manufacturer == "audi" | manufacturer == "ford") %>%
  group_by(manufacturer) %>%
  summarise(Effectif = n(), MoyenneCty = mean(cty), Moyennehwy = mean(hwy))

# Ne tracer le graphique que pour les voitures de marque Dodge

mpg %>%
  filter(manufacturer == "dodge") %>%
  ggplot(aes(x = displ, y = hwy)) +
  geom_point()
# Ne tracer que les voitures pour lesquelles hwy<=40 et (noté en R "&") displ<=6
mpg %>%
  filter(hwy <= 40 & displ <= 6) %>%
  ggplot(aes(x = displ, y = hwy)) +
  geom_point()
# Colorier les points pour lesquels hwy>40 ou displ>6
ggplot(mpg, aes(x = displ, y = hwy, colour = hwy > 40 | displ > 6)) +
  geom_point()

# Ne tracer que les voitures avec hwy>40 ou displ>6
mpg %>%
  filter(hwy > 40 | displ > 6) %>%
  ggplot(aes(x = displ, y = hwy)) +
  geom_point()

# Sans la légende
ggplot(mpg, aes(x = displ, y = hwy, colour = hwy > 40 | displ > 6)) +
  geom_point(show.legend = FALSE)
# Meme graphique mais avec des axes renommés
ggplot(mpg, aes(x = displ, y = hwy, colour = hwy > 40 | displ > 6)) +
  geom_point(show.legend = FALSE) +
  xlab("Cylindrée") +
  ylab("Miles par galon \n sur autoroute") +
  theme(axis.title.y = element_text(size = 8))

ggplot(mpg, aes(x = displ, y = 235.21 / hwy, colour = hwy > 40 | displ > 6)) +
  geom_point(show.legend = FALSE) +
  xlab("Cylindrée") +
  ylab("Consommation sur autoroute en litre par 100 km") +
  theme(axis.title.y = element_text(size = 8))

## A vous de jouer !
## Produire un graphique qui n'a pas été encore réalisé dans le TP
## et le commenter

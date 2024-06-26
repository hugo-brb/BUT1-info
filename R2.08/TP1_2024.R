# PREMIER TP EN R
# Sur Chamillo dans la rubrique Liens vous trouverez
# des sites pour avoir des ressources sur R

# charger les packages utiles (dont ggplot2 pour les graphiques)
library(ggplot2)
library(dplyr)

# On utilise le jeu de donn�es mpg du package ggplot2
# taper dans l'onglet "Help" fen�tre en bas � droite le mot "mpg"
# vous verrez alors ce que contient le fichier mpg

# le di�se est le caract�re pour commenter du code R

############## Dans la suite du TP #################
######### lire les commentaires donn�s #############
######### et r�pondre aux questions ! ##############
####################################################

# Pour chacune des 11 variables dire si elles sont qualitatives (ordinales/nominales)
# ou si elles sont quantitatives
# manufacturer -> qualitive nominale
# model -> qualitative nominale
# displ -> quantitative
# year -> qualitative ordinale


# Pour voir les 10 premi�res lignes du fichier
# Pour lancer la commande ci-dessous appuyer sur "Run" en haut de cette fen�tre
head(mpg, 10)

# Pour connaitre les modalit�s de la variable manufacturer
mpg %>% distinct(manufacturer)
# Combien y-a-t'il de fabricants pr�sents dans le jeu de donn�es ?
# Il y a 15 fabricants dans le jeu de donn�es.

# Combien y-a-t'il d'ann�es dans le fichier ?
# Taper la commande pour r�pondre � cette question
mpg %>% distinct(year)
# Il y a 2 ann�es diff�rentes dans ce jeu de donn�es.

# Pour connaitre le nombre de voitures par constructeur
mpg %>%
  group_by(manufacturer) %>%
  summarise(Effectif = n())
# Quelle est la marque la plus repr�sent�e dans ce jeu de donn�es ?
#  
# C'est dodge.




# Faire son premier graphique
# Nuages de points
ggplot(mpg, aes(x = displ, y = hwy)) +
  geom_point()
# information : https://fr.wikipedia.org/wiki/Cylindr%C3%A9e
# Comment est d�finie hwy ? (attention : il s'agit de voitures am�ricaines)
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
# quels sont les d�fauts de ce graphique cr�e automatiquement ?
# Il y a des trous entre diff�rentes valeurs, pas de bords et donn�es mal d�finies.


# Histogramme am�lior�
# breaks = d�finition � la main des limites des intervalles pour les barres
# seq(0,7,0.5) = suite des chiffres de 0 � 7 par pas de 0.5
# fill = couleur de l'int�rieur des barres
# color = couleur du contour des barres
# alpha = coefficient de transparence
# la commande scale_x_continous fixe les �tiquettes de graduation sur l'axe des x, ici
# de 0 � 7 par pas de 1
ggplot(mpg, aes(x = displ)) +
  geom_histogram(breaks = seq(0, 7, 0.5), fill = "grey40", color = "black", alpha = 0.4) +
  scale_x_continuous(breaks = seq(0, 7, 1))
# Combien y-a t'il de voitures ayant une cylindr�e entre 4.5 litres et 5.5 litres ?
# Il y a 48 voitures entre 4.5 litres et 5.5.


# Histogramme INCORRECT !
ggplot(mpg, aes(x = displ)) +
  geom_histogram(breaks = c(1.5, 2, 2.5, 3, 3.5, 4, 5, 5.5, 6, 6.5, 7), fill = "grey40", color = "black", alpha = 0.4) +
  scale_x_continuous(breaks = seq(0, 7, 1))
# Qu'est-ce qui cloche ?
# les donn�es entre 4 et 5L sont les seules regroup�s.

# Histogramme avec la densit�
ggplot(mpg, aes(x = displ)) +
  geom_histogram(aes(y = ..density..), breaks = c(1.5, 2, 2.5, 3, 3.5, 4, 5, 5.5, 6, 6.5, 7), fill = "grey40", color = "black", alpha = 0.4) +
  scale_x_continuous(breaks = seq(0, 7, 1))
# Comment calcule t-on la densit� pour l'intervalle [4;5] ?
# On fait la proportion/ taille de l'intervalle. (38/234)


# Plusieurs boxplot
ggplot(mpg, aes(x = drv, y = displ)) +
  geom_boxplot()
# Pour les voitures ayant 4 roues motrices quelle est la cylindr�e m�diane ?
#La cylindr�e m�diane est 4.

# Pour les voitures avec roues motrices avant quelle est la valeur du premier quartile ?
# Pour les voitures avec des roues motrices avant la valeur du premier quartille est 2.

# Que peut-on dire avec ce graphique ?
# Il permet de visualiser le nombre de cynlindr�s qu'a une voiture en fonction de ses roues motrices.

# Graphique en barres
ggplot(mpg) +
  geom_bar(aes(x = manufacturer)) # par d�faut l'axe des y est l'effectif
# Combien y-a t'il de voitures de la marque "Subaru" dans ce jeu de donn�es ?
# Il y en a 14.

# Quel est le fabricant ayant le plus de voitures dans ce jeu de donn�es ?
# C'est le fabricant dogde.

# Le m�me graphique avec des pourcentages
ggplot(mpg, aes(x = manufacturer, y = ..count.. / sum(..count..))) +
  geom_bar()

# Le m�me avec l'axe des y en pourcentage
ggplot(mpg, aes(x = manufacturer, y = ..count.. / sum(..count..))) +
  geom_bar() +
  scale_y_continuous(labels = scales::percent)

# Le m�me avec la commande function(x) -length(x)  qui permet de r�ordonner
# les modalit�s du fabriquant de la plus fr�quente
# � la moins fr�quente
ggplot(mpg, aes(x = reorder(manufacturer, manufacturer, function(x) -length(x)), y = ..count.. / sum(..count..))) +
  geom_bar() +
  scale_y_continuous(labels = scales::percent)

# enlever les l�gendes des axes
ggplot(mpg, aes(x = reorder(manufacturer, manufacturer, function(x) -length(x)), y = ..count.. / sum(..count..))) +
  geom_bar() +
  scale_y_continuous(labels = scales::percent) +
  theme(axis.title = element_blank())

# le m�me avec des l�gendes d�finies sur les axes
ggplot(mpg, aes(x = reorder(manufacturer, manufacturer, function(x) -length(x)), y = ..count.. / sum(..count..))) +
  geom_bar() +
  xlab("Fabriquant") +
  ylab("Pourcentage de voitures") +
  scale_y_continuous(labels = scales::percent)

# le m�me avec une l�gende pour l'axe des y et des labels pivot�s sur l'axe des x
ggplot(mpg, aes(x = reorder(manufacturer, manufacturer, function(x) -length(x)), y = ..count.. / sum(..count..))) +
  geom_bar() +
  theme(axis.title.x = element_blank()) +
  ylab("Pourcentage de voitures") +
  scale_y_continuous(labels = scales::percent) +
  theme(axis.text.x = element_text(angle = 45, hjust = 1))

################### A FAIRE ########################################
############# AVANT DE CONTINUER ###################################
### graphique adapt� pour le type de motorisation = variable drv ###
### graphique adapt� pour les variables hwy et cty               ###
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
### Utiliser dplyr pour �diter certaines statistiques ##############
###################################################################
##### Pour une moyenne simple
mpg %>% summarise(Moyenne = mean(cty))
##### Pour une moyenne simple avec l'effectif
mpg %>% summarise(Moyenne = mean(cty), nbre = n())
##### Pour une moyenne par groupe ici l'ann�e
mpg %>%
  group_by(year) %>%
  summarise(Moyenne = mean(cty))
# Quelle info avons nous ?
# Nous obtenons la moyenne de consomation des voitures sur route.
# Puis nous l'obtenons rapport� 

##### Pour une moyenne par groupe ici par type de motorisation
mpg %>%
  group_by(drv) %>%
  summarise(Moyenne = mean(cty))
# Quelle type de motorisation � la consommation en ville moyenne la plus �lev�e ?


##### Avec d'autres statistiques
###### Pour la variable constructeur
mpg %>%
  group_by(manufacturer) %>%
  summarise(Effectif = n()) %>%
  mutate(Pourcentage = round(Effectif / sum(Effectif), 2))

###### Pour la variable constructeur
###### ordonn�e par pourcentage
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
# Ne tracer que les voitures pour lesquelles hwy<=40 et (not� en R "&") displ<=6
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

# Sans la l�gende
ggplot(mpg, aes(x = displ, y = hwy, colour = hwy > 40 | displ > 6)) +
  geom_point(show.legend = FALSE)
# Meme graphique mais avec des axes renomm�s
ggplot(mpg, aes(x = displ, y = hwy, colour = hwy > 40 | displ > 6)) +
  geom_point(show.legend = FALSE) +
  xlab("Cylindr�e") +
  ylab("Miles par galon \n sur autoroute") +
  theme(axis.title.y = element_text(size = 8))

ggplot(mpg, aes(x = displ, y = 235.21 / hwy, colour = hwy > 40 | displ > 6)) +
  geom_point(show.legend = FALSE) +
  xlab("Cylindr�e") +
  ylab("Consommation sur autoroute en litre par 100 km") +
  theme(axis.title.y = element_text(size = 8))

## A vous de jouer !
## Produire un graphique qui n'a pas �t� encore r�alis� dans le TP
## et le commenter

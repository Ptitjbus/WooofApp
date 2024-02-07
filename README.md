# Projet d'application Jetpack compose WooofApp

<br/>

> ### Objectifs
>- Découvrir la création d'une app avec jetpack compose
>- Créer un back rapide et facile avec firebase
<br/>

### Le projet

> [Lien du figma](https://www.figma.com/file/GH35XpB6erIgTm6jfpweFz/Wooof?type=design&node-id=0%3A1&mode=design&t=d3Vcfn4ZfYBn460Q-1)

Wooof App est une application de services pour propriétaires d'animaux de compagnie. Sur cette application, il est possible de proposer une mission (ex: garder son chien) et/ou de répondre au besoin proposé.
Sur l'application il est possible de consulter les offres, autour de soi via des filtres et une carte. D'y répondre via une messagerie et également de poster sa propre annonce.

### Travail réalisé

> [Lien de la vidéo démo](https://youtu.be/XEh3mUMSfd0)

Nous avions un temps limité pour faire l'application, environ une semaine. Voici ce qui a été réalisé durant ce temps.

- Pages **d'Onboarding** avec slider dynamique "user friendly"
- Page de **connexion/inscription** rélié à firebase avec toutes les vérifications d'authentification. Les vérifications sont également faites dans l'appli (si il y a déconnexion, l'utilisateur est automatiquement redirigé vers la page de connexion)
- Page **d'accueil**, fetch et conversion des données de la **BDD *firebase***.
- Page de **détail** d'un produit. Fetch de la bdd et affichage en fonction de ce qui est récupéré.
- Page de **map**. Récupération de la position de l'utilisateur très précise et affichage sur la map, gestion des droits, *mapbox*.

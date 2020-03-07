Ce fichier contient et contiendra des remarques de suivi sur votre
projet tant sur la modélisation que sur la programmation. Un nouveau
suivi est indiqué par une nouvelle section datée.

Certaines remarques demandent des actions de votre part, vous les
repérerez par une case à cocher.

- []  Action (à réaliser)

Merci de nous indiquer que vous avez pris en compte la remarque en
cochant la case. N'hésitez pas à écrire dans ce fichier et à nous
exposer votre point de vue.

- [x] Action (réalisée)
    - RÉPONSE et éventuelles remarques de votre part,


# Suivi du mar. 04 févr. 2020 00:00:40 CET séance 2
Chantal Taconet

Bon travail ! Voici quelques remarques merci d'indiquer que vous les avez prises en compte.


## Git
- Bons commentaires de commit
- Ce serait bien que les deux binomes fassent des commit

## Modelisation.pdf
- [x] Page 1 : Mettre à jour le nom et prénom des étudiants
--> N'etait-ce pas deja le cas ligne 75 dans Modelisation/modelisation.tex
- Diagramme de cas d'utilisation : OK
    - [x] Créer les nouveaux clients --> créer un nouveau client
    - [x] Revoir le cas initialiser la serrure, en réfléchissant ce qu'a à faire le gérant de la chambre avec votre système pour pouvoir utiliser les serrures
    --> l'initialisation de la serrure ne fait pas partie des cas d'utilisation du système
- Priorités, préconditions et postconditions
    - [x] Revoir Initialiser les serrures
    --> l'initialisation de la serrure ne fait pas partie des cas d'utilisation du système
    - [x] Manque enregistrer l'occupation d'une chambre
    - [x] Prévoir un état du badge après sa création(accueil, client)
- Tables de décisions
  - [x] Revoir les tables de décision, pour tous les cas d'utilisation, le système reçoit des chaines de caractère et il faut vérifier qu'elles soient  (non null ^ non vide)
  --> ajout des tests non null ^ non vide sur les informations du client et sur le code de la chambre

---

# Suivi du mer. 12 févr. 2020 15:38:28 CET
Elisabeth Brunet

- [x] Les éléments travaillés lors de la séance 3 n'apparaissent pas dans le fichier modelisation.pdf, je ne peux donc pas faire de suivi.
--> retard rattrapé au niveau de la séance 3


---

# Suivi du mar. 25 févr. 2020 22:14:39 CET
Elisabeth Brunet

Bon travail avec des précisions à apporter et à achever.

- [x] Diagramme de classes
    - [x] Vous avez encore des associations qui ne sont pas traduites avec des relations d'agrégation/composition.
    --> relations d'agrégations ajoutées au diagramme de classes entre Chambre/Badge et Chambre/Client
- [x] Fiche des classes
    - [x] Je n'ai pas regardé en détail mais cela me semble sur la bonne voie
    - [x] "au plus une clé" -> que voulez-vous indiquer?
    --> on voulait indiquer qu'il était possible qu'il y ait zéro ou une clé mais finalement il y a toujours une clé donc on a enlevé la précision inutile 
- [x] Diagramme de machine à états
    - [x] Pour badge, il manque l'état "en destruction"
    --> ajout de l'état "en destruction"
    - [x] Invariant : vous avez rédigé la partie de l'invariant portant sur l'état du badge. Il manque la partie de l'invariant sur l'attribut id. Il faut qu'il soit non null et non vide.
    --> ajout de la condition id non null et non vide

# Suivi du mer. 04 mars 2020 10:53:57 CET
Chantal Taconet

Bon travail, penser à mettre en cohérence la modélisation et le code. 

## Git
## Modélisation
### Diagramme de classes
- [x] Revoir le formalisme des classes d'association (pour le trio Chambre-Badge-Clé)
- [x] Composition (losange noir) ne peut pas être utilisé pour la classe Badge qui est connu par GestionClefsHotel et Badge : préférer une agrégation simple (losange vide)
--> ok agrégation simple pour la classe Badge 
- [x] Attribut clé de Badge et Chambre sont redondants avec l'association avec une clé (lorsqu'il y a une association, on n'écrit pas l'attribut dans la classe, l'attribut est implicite et existera dans la classe java)
--> association retirée car pas de classe Clef dans le code

## Diagramme de séquence
- LibererChambre
  - [x] comment connaissez-vous l'identifiant d'un badge ?
  --> effectivement le badge est connu grâce à l'attribut badge dans l'objet chambre, diagramme de séquence modifié en conséquence
  
### Tests unitaires
- [x] Pourquoi n tests lorsque le badge est inexistant ?
--> ok: une seule recherche est nécessaire pour voir si le badge avec le bon identifiant existe

## Code 

- [x] Pour assurer le principe d'encapsulation, les attributs de classe doivent être private
--> ok: ajout des méthodes get<Attribut> en conséquence
- [x] N'oublier pas de proposer et de programmer les invariants
--> ok: invariants à compléter
- [x] Avez-vous pensé à incrémenter le sel entre chaque génération de clé
--> le sel n'était en effet pas incrémenté lors de la génération de la seconde clé dans le constructeur de Chambre, on a introduit une méthode Chambre.genereClef afin de palier à ce problème et de ne pas oublier d'incrémenter le sel dans le futur si besoin

### Cohérence avec la modélisation
- [x] La classe Clé du diagramme de classes n'apparaît pas dans le Code 
--> classe Clé supprimée du diagramme
- [x] les identifiants sont ils des chaînes (comme prévu dans le modèle ou des entiers comme dans le code ? 
--> les identifiants sont des entiers dans le modèle et dans le code normalement, y a-t-il une incohérence que nous n'avons pas vue quelque part?
---

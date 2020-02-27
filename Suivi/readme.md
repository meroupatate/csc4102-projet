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
- [] Fiche des classes
    - [] Je n'ai pas regardé en détail mais cela me semble sur la bonne voie
    - [] "au plus une clé" -> que voulez-vous indiquer? 
- [] Diagramme de machine à états
    - [] Pour badge, il manque l'état "en destruction"
    - [] Invariant : vous avez rédigé la partie de l'invariant portant sur l'état du badge. Il manque la partie de l'invariant sur l'attribut id. Il faut qu'il soit non null et non vide.
    

---

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

# Suivi du mar. 17 mars 2020 20:14:08 CET
Chantal Taconet

Votre projet avance bien, vous pouvez passer aux cas d'utilisation du sprint2. Bonne continuation.

## Retours sur le suivi du 04/03
- [x] les identifiants sont ils des chaînes (comme prévu dans le modèle ou des entiers comme dans le code ?
--> les identifiants sont des entiers dans le modèle et dans le code normalement, y a-t-il une incohérence que nous n'avons pas vue quelque part?
  - [x] je lis dans les préconditions de créer badge par exemple " identifiant du badge bien formé (non null et non vide)" ce qui correspond aux tests à faire pour une chaîne de caractères mais pas pour un entier,
  - [x] idem dans les tables de décision des cas d'utilisation
  - [x] idem dans les diagrammes de séquence
  --> effectivement merci c'est corrigé

## Code
### Module GestionClefsHotel
- Traduction des associations en atribut : OK
- Classe GestionClefsHotel
  - UC1 "Créer une chambre" --> OK
  - UC2 "Enregistrer l'occupation d'une chambre par un client"
    - [x] Ne correspond pas au diagramme de séquence
  - UC3 "Libérer une chambre"
    - [x] Ne correspond pas au diagramme de séquence
### Tests
#### Tests unitaires
- Badge Constructeur
  - [x] Ne correspond pas à la table de décision (4 tests et 2 dans l'implémentation)

- Badge Est donné à un client -> OK
#### Tests de validation
- UC1 "Créer une chambre"
  - [x] OK mais mettre en cohérence avec la table de décision
- UC2 "Enregistrer l'occupation d'une chambre par un client"
  - [] Que veut dire le 2xn dans la table de validation ?
  - [x] OK mais mettre en cohérence avec la table de décision
- UC3 "Libérer une chambre"
  - [x] mettre en cohérence avec la table de décision
---

# Question pour le dernier suivi:
-> On rencontre un problème au niveau de l'insersion du patron de conception Singleton: on nous dit dans le sujet du TP d'ajouter "les classes ClefVide et PaireDeClefsVides qui sont des classes enfants des classes Clef et PaireDeClef". Que peut-on faire si nous n'avons jamais introduit de classes Clef et PaireDeClefs dans notre modélisation? Merci d'avance pour le suivi!

# Suivi du mer. 01 avril 2020 20:02:02 CEST
Chantal Taconet

Excellent travail complet !
Quelques remarques de forme sur les diagrammes de séquence

## Diagramme de séquence
- [x] Dans un DS les flêches pleines correspondent à une méthode asynchrone, pour les méthodes synchrones utilisées dans ce cours, ce sont des flêches vides
- [x] les rectangles blancs ne commencent qu'à réception des messages
- [x] attention c.secondeClé (exemple parmi d'autres)  devrait correspondre à un message vers c


---

------------------------------------
mar. 14 avril 2020 14:21:56 CEST
Denis Conan
# Évaluation du logiciel livré à la fin du Sprint 2
## Modélisation du logiciel
- Spécification et préparation des tests de validation
    - Diagrammes de cas d'utilisation = a
    - Tables de décision de cas d'utilisation de priorité « haute » avec l'expression des préconditions et postconditions = a
        - créer une chambre
        - check-in
        - check-out
- Conception préliminaire
    - Diagramme de classes, y compris avec l'insertion d'un patron de conception = a
    - Diagrammes de séquence de cas d'utilisation développés dans la préparation des tests de validation = a
        - créer une chambre
        - check-in
        - check-out
        - déclarer la perte d'un badge, sans remplacement
- Conception détaillée et de la préparation des tests unitaires
    - Diagramme de machine à états et invariant = a-
    - Tables de décision de tests unitaires = a
        - constructeur de badge
        - estDonneAUnClient ou équivalente
## Programmation du logiciel
- Utilisation des outils de programmation
    - Module Maven et tests avec JUnit = a
    - Documentation Javadoc et utilisation de Checkstyle = a
    - Qualité du code en utilisant SpotBugs = a
- Programmation de la solution
    - Classes du diagramme de classes avec leurs attributs = a
    - Méthodes des cas d'utilisation de base = a
        - créer une chambre
        - check-in
        - check-out
        - déclarer la perte d'un badge, sans remplacement
        - déclarer la perte d'un badge, avec remplacement
- Cohérence entre le code et le modèle
    - Cohérences du code avec le diagramme de classes = a
    - Cohérences du code avec les diagrammes de séquence de base = a
        - créer une chambre
        - check-in
        - check-out
        - déclarer la perte d'un badge, sans remplacement
- Programmation et exécution des tests de validation et unitaires
    - Tests de validation = a
        - créer une chambre
        - check-in
        - check-out
        - déclarer la perte d'un badge, sans remplacement
        - déclarer la perte d'un badge, avec remplacement
    - Tests unitaires = a
        - constructeur de badge
        - estDonneAUnClient ou équivalente
- Patron de conception et idiome
    - Application d'idiomes JAVA = a
        - equals et hashCode
        - toString
        - Streams
        - Optional
    - Application de patrons de conception = a
        - singleton
        - publier-souscire


----

modélisation = min(4.0, (6 * 4 / 6) + (0 * 2 / 6) + (0 / 10) - (1 / 10)) = 3.90
programmation, critères majeurs =  (8 * 4 / 8) + (0 * 2 / 8) + (0 / 10) - (0 / 10) = 4.00
programmation, critères mineurs =  (0 * 4 / 8) + (0 * 2 / 8) = 0
programmation = min(4.0, max(4.00 - 0, 0)) = 4.00
projet = 3.90 + 4.00 = 7.90

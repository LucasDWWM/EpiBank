IDENTIFICATION DIVISION.
PROGRAM-ID. TRAITEMENT-BANQUE.

ENVIRONMENT DIVISION.
INPUT-OUTPUT SECTION.
FILE-CONTROL.
    *> On lie le fichier physique "input.txt" à la variable interne FICHIER-ENTREE
    SELECT FICHIER-ENTREE ASSIGN TO "../data/input.txt"
    ORGANIZATION IS LINE SEQUENTIAL.

    *> On lie le fichier physique "output.txt" à la variable interne FICHIER-SORTIE
    SELECT FICHIER-SORTIE ASSIGN TO "../data/output.txt"
    ORGANIZATION IS LINE SEQUENTIAL.

DATA DIVISION.
FILE SECTION.
*> Définition de la structure du fichier d'entrée
FD FICHIER-ENTREE.
01 LIGNE-ENTREE    PIC X(50).

*> Définition de la structure du fichier de sortie
FD FICHIER-SORTIE.
01 LIGNE-SORTIE    PIC X(50).

WORKING-STORAGE SECTION.
01 MESSAGE-FINAL   PIC X(50).
01 WS-EOF          PIC A(1). 

PROCEDURE DIVISION.
MAIN-PROCEDURE.
    *> 1. Ouverture des fichiers
    OPEN INPUT FICHIER-ENTREE.
    OPEN OUTPUT FICHIER-SORTIE.

    *> 2. Lecture du fichier d'entrée
    READ FICHIER-ENTREE INTO LIGNE-ENTREE
        AT END MOVE 'Y' TO WS-EOF
    END-READ.

    *> 3. Traitement 
    DISPLAY "COBOL: J'ai recu -> " LIGNE-ENTREE.
    STRING "BONJOUR " DELIMITED BY SIZE
           LIGNE-ENTREE DELIMITED BY SPACE
           ", TRAITEMENT OK." DELIMITED BY SIZE
           INTO MESSAGE-FINAL.

    *> 4. Écriture de la réponse
    WRITE LIGNE-SORTIE FROM MESSAGE-FINAL.

    *> 5. Fermeture
    CLOSE FICHIER-ENTREE.
    CLOSE FICHIER-SORTIE.

    STOP RUN.

    
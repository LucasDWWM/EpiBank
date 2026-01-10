IDENTIFICATION DIVISION.
       PROGRAM-ID. BANQUE-TEST.
      *--------------------------------
      * Ce programme est ton premier test bancaire
      *--------------------------------
       ENVIRONMENT DIVISION.
       
       DATA DIVISION.
       WORKING-STORAGE SECTION.
       01  SOLDE-COMPTE      PIC 9(05) VALUE 1000.
       01  NOM-CLIENT        PIC X(20) VALUE "JEAN DUPONT".
       
       PROCEDURE DIVISION.
       MAIN-PROCEDURE.
           DISPLAY "---------------------------------".
           DISPLAY "DEMARRAGE SYSTEME BANCAIRE V1.0".
           DISPLAY "CLIENT : " NOM-CLIENT.
           DISPLAY "SOLDE ACTUEL : " SOLDE-COMPTE " EUR".
           DISPLAY "---------------------------------".
           
           STOP RUN.
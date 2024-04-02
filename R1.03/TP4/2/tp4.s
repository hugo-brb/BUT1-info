  		ORG	$0
		DC.L	$8000	; adresse de la pile
		DC.L	Inst1	; pour initialisation PC (Compteur Ordinal)

* declaration des "variables" du programme
* on reserve et on initialise avec la directive DC 
* on reserve sans initialiser avec la directive DS
		ORG	$A00
a		DS.W 1
b		DS.W 1
c		DC.B 1


* debut des instructions du programme

		ORG	$AC0
Inst1		MOVE.W 	#5,a    ; a := 5
		MOVE.W 	#4,b   	; b := 4
		MOVE.B 	#'S',c	; c := 'S'

*  a vous d'ecrire la suite en assembleur...
tq		CMPI.W	#2,a		; a ? 2
		BCS	fintq		; si a < 2 alors aller à fintq 
etou1		CMPI.W	#35,b		; b ? 35
		BEQ	faire		; si b<=3 alors aller à faire
etou2		CMPI.B	#'S',c		; c ? 'S'
		BNE	fintq		; si c != 'S' alors aller à fintq
faire		MOVE.B	#'N',c
		SUBI.W	#1,b
		SUBI.W	#1,a
		BRA	tq		; retourner à tq
fintq

* fin du programme principal
		BREAK	

* fin du contenu a assembler
		END




  		ORG	$0
		DC.L	$8000	; adresse de la pile
		DC.L	Inst1	; pour initialisation PC (Compteur Ordinal)

* declaration des "variables" du programme
* on reserve et on initialise avec la directive DC 
* on reserve sans initialiser avec la directive DS
		ORG	$A00
a		DS.W 1
b		DS.W 1
cas1		DC.L 1
cas2		DC.L 1

* debut des instructions du programme

		ORG	$AC0
Inst1		MOVE.W 	#1,a
		MOVE.W 	#10,b
		MOVE.L	#'init',cas1
		MOVE.L	#'init',cas2

*  a vous d'ecrire la suite en assembleur...
*ET
		MOVE.W 	b,D0
si1		CMP.W	a,D0			; a ? b
		BLS	sinon1			; si a <= b aller à sinon1
et1		CMPI.W	#4,a			; a ? 4 
		BCC	sinon1			; si a >= 4 aller à sinon1
alors1		MOVE.L	#'alo1',cas1	
		BRA	finsi1			; sauter le sinon1
sinon1		MOVE.L	#'sin1',cas1
finsi1

*OU
si2		CMP.W	a,D0			; a ? b
		BHI	alors2			; si a > b aller à alors2
ou2		CMPI.W	#4,a			; a ? 4
		BCC	sinon2			; si a >= 4 aller à sinon2
alors2		MOVE.L	#'alo2',cas2
		BRA	finsi2			; sauter le sinon2
sinon2		MOVE.L	#'sin2',cas2
finsi2



* fin du programme principal
		BREAK	

* fin du contenu a assembler
		END

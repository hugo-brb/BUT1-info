  		ORG	$0
		DC.L	$8000	; adresse de la pile
		DC.L	calcul	; pour initialisation PC (Compteur Ordinal)

* declaration des "variables" du programme
* on les initialise avec la directive DC 

		ORG	$A00
PrixBonbon	DC.B	18
PrixChocolat	DC.B	9
PrixGateau	DC.B	51
Depenses	DC.B	0
Etrennes	DC.B	100
Solde		DC.B	0




* debut des instructions du programme

		ORG	$AC0
calcul		MOVE.B	#$0,Solde	* Mise à jour de N et Z

si1		BMI	sinon1		
alors1		NOP
si2		BEQ	sinon2
alors2		MOVE.W	#$AAAA,D0
		BRA 	finsi2	
sinon2		MOVE.W	#$CCCC,D0
finsi2		NOP
		MOVE.W	#$1111,D1	
		BRA 	finsi1	
sinon1		MOVE.W	#$FFFF,D0
finsi1		NOP
		MOVE.W	#$2222,D2

* fin du programme principal
		BREAK	

* fin du contenu a assembler
		END

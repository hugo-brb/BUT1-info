  		ORG	$0
		DC.L	$8000	; adresse de la pile
		DC.L	Inst1	; pour initialisation PC (Compteur Ordinal)

* declaration des "variables" du programme
* on reserve et on initialise avec la directive DC 
* on reserve sans initialiser avec la directive DS
		ORG	$A00
a		DS.W 1
b		DS.W 1


* debut des instructions du programme

		ORG	$AC0
Inst1		MOVE.W 	#348,a    	
		MOVE.W 	#2225,b   	

*  a vous d'ecrire la suite en assembleur...
		MOVE.W	a,D1
		MOVE.W	b,D2
tq		CMP.W	D2,D1
		BEQ	fin
faire
si		CMP.W	D2,D1
		BLS	sinon	
alors		SUB.W	D2,D1
		BRA	finsi
sinon		SUB.W	D1,D2
finsi		BRA	tq
fin		MOVE.W	D1,D0

* fin du programme principal
		BREAK	

* fin du contenu a assembler
		END




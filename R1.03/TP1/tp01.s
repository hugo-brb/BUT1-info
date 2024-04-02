          ORG	$0
          DC.L	$8000	
          DC.L	DEBUT

* declaration des "données" du programme
          ORG	$500	
DBIN	  DC.W	%0000000100010101
EHEX	  DC.W	$00E6
FCAR	  DC.L	$63344329
G23	  DC.B  $17


          ORG	$520
ADEC	  DC.B	26	
BDEC	  DC.B	35
CDEC	  DC.W	442
CH1	  DC.B	'BONJOUR''bonjour',0

*instructions du programme
      	  ORG	   $550	
DEBUT	  MOVE.B   ADEC,BDEC 
          MOVE.W   #$7572,CDEC
	  MOVE.L   #$1234ABCD,D3
	  MOVE.B   #%01100100,D3
	  MOVE.B   BDEC,CH1
	  MOVE.W   #$4152,ADEC
	  BREAK	   ;fin du programme 
* fin de la partie a assembler
          END  *fin du contenu a assembler

/**
* \file   TO_tp1.pl
* \author Perig Dissaux 17 135 379
* \brief  Ce fichier contient l implementation d un algorithme de fusion a 3 chemins.
*         Le programme prend en entree 3 fichiers (CompareA, CompareB, CompareOriginal),
*         et produit le fichier CompareSortie.
*/

/**
* \brief Regle qui va ouvrir en lecture/ecriture les 4 fichiers, comparer leur contenu,
* puis fermer les flux.
*/
merge:- %Ouverture des flux de lecture
        open('CompareOriginal',read,Str1),
        open('CompareA',read,Str2),
        open('CompareB',read,Str3),
        open('CompareSortie',write,Str4),
        %Déclaration de la sortie standard
        set_output(Str4),
        %Comparaison des fichiers
	 	compareLines(Str1,Str2,Str3),	
	  	%Fermeture des flux de lecture
	  	close(Str1),
	  	close(Str2),
	  	close(Str3),
	  	close(Str4).


/**
* \brief Regle qui lit une ligne a partir du flux passe en argument,
* Et l ecrit sur la sortie standard.
* \param[in]  Flux en lecture sur le fichier lu.
*/	
compare(Str1):- read_line(Str1,X),
                write_line(X),
             	(at_end_of_stream(Str1) -> ! 
                ;compare(Str1)).
/**
* \brief Regle qui va faire une comparaison entre deux fichiers passes en parametres.
* \param[in]  Flux en lecture sur le fichier lu.
* \param[in]  Flux en lecture sur le fichier lu.
*/
compareAB(Str1,Str2):-  (at_end_of_stream(Str1) -> compare(Str2),!
                        ;at_end_of_stream(Str2) -> compare(Str1),!
                        %Je lis une ligne de chaque fichier.
                        ;read_line(Str1,A), read_line(Str2,B),
                        %Je selectionne la ligne a ecrire.
                        selectLineAB(A,B),
                        %Recursion
                        compareAB(Str1,Str2)).

/**
* \brief Regle qui va selectionner une ligne entre celle de deux fichiers passes en parametres.
* Si les lignes sont identiques, on ecrit la ligne.
* \param[in]  Ligne d un fichier lu.
* \param[in]  Ligne d un fichier lu.
*/
selectLineAB(A,B):- (=(A,B) -> write_line(B);!).

/**
* \brief Regle qui va faire une comparaison entre un fichier passe en parametres
* et le fichier original.
* \param[in]  Flux en lecture sur le fichier lu.
* \param[in]  Flux en lecture sur le fichier lu.
*/	
compareON(Str1,Str2):-  (at_end_of_stream(Str1) -> compare(Str2),! 
                        ;at_end_of_stream(Str2) -> compare(Str1),! 
                        %Je lis une ligne de chaque fichier.
                        ;read_line(Str1,A), read_line(Str2,B),
                        %Je selectionne la ligne a ecrire.
                        selectLineON(A,B),
                        %Recursion
                        compareON(Str1,Str2)).

/**
* \brief Regle qui va selectionner une ligne entre celle du fichier passe en parametres.
* et celle du fichier original.
* Si la ligne est est differente de celle de l original, on l ecrit.
* \param[in]  Ligne d un fichier lu.
* \param[in]  Ligne d un fichier lu.
*/
selectLineON(O,N):- (\=(O,N) -> write_line(N);!).

/**
* \brief Regle qui compare 2 fichiers(Str2 & Str3) par rapport a un fichier original(Str1),
* et ecrit sur la sortie standard la fusion des fichiers  
* \param[in]  Flux en lecture sur le fichier lu.
* \param[in]  Flux en lecture sur le fichier lu.
* \param[in]  Flux en lecture sur le fichier lu.
*/
compareLines(Str1,Str2,Str3):-  %Si on est arrive a la fin du fichier Original. 
	                        	(at_end_of_stream(Str1) -> compareAB(Str2,Str3),!
	                        	%Si on est arrive a la fin du fichier CompareA.
	                        	;at_end_of_stream(Str2) -> compareON(Str1,Str3),!
	                        	%Si on est arrive a la fin du fichier CompareB.
	                        	;at_end_of_stream(Str3) -> compareON(Str1,Str2),!
	                          	%Je lis une ligne de chaque fichier.
	                          	;read_line(Str1,O), read_line(Str2,A), read_line(Str3,B), 
                                %Je selectionne la ligne a ecrire.
                                selectLineOAB(O,A,B),
                                %Recursion.
                                compareLines(Str1,Str2,Str3)).



				                  
/**
* \brief Si la ligne du fichier A est identique a l Original, j ecris la ligne de B, si non je teste si B est identique a O, 
* si oui j ecris A, si non il y a conflit entre A et B et je garde la ligne de O.  
* \param[in]  Ligne d un fichier lu.
* \param[in]  Ligne d un fichier lu.
* \param[in]  Ligne d un fichier lu.
*/
selectLineOAB(O,A,B):-  (=(O,A) -> write_line(B)
			          	;(=(O,B) -> write_line(A)
                        ;write_line(O))).

/**
* \brief Regle qui lit une ligne a partir du flux passé en argument
* \param[in]  Flux en lecture sur le fichier lu.
* \param[out]  Liste ou sont stockes les caracteres lus.
*/	 
read_line(Str,[H|T]):-  (at_end_of_stream(Str) -> ! 
                        ;get_char(Str,H), 
                        (=(H,'\n') -> ! 
                        ;read_line(Str,T))).

/**
* \brief Regle qui ecrit une ligne sur la sortie standard 
* \param[in]  Liste ou sont stockes les caracteres lus.
*/
write_line([H|T]):- (=(H,eof)-> ! 
                    ;put_char(H),
                    write_line(T)).	 



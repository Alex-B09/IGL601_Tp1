package main

import(
	"fmt"
	"bufio"
	"log"
	"os"
	"strconv"	
)
	
func main() {
		/*
		NOM: Arnaud RICAUD
		Algorithme de fusion à trois chemin fonctionnant de la manière suivante:
		Paramètres:	
			A, B: Deux fichiers créés à partir d'un fichier similaire.
			Original : fichier "ancêtre" commun de A et B.
		
		Méthode:
			Analyse des fichiers ligne par ligne:
				- Si A = Original et B = Original 	=> Sortie = Original
				- Si A = Original et B != Original 	=> Sortie = B
				- Si A != Original et B = Original 	=> Sortie = A
				- Si A != Original et B != Original mais A = B 	=> Sortie = A = B
				- Si A != Original et B != Original et A != B	=> CONFLIT!!! Sortie = Original
				
			On considère que les lignes vides sont des suppressions du contenu original et sont
			donc pris en compte. (Même celles à la fin du fichier!)
			
			En cas de conflit, aucun des deux changements n'est pris en compte, on revient au fichier original.
		*/
		
		//#### CRÉATION DU FICHIER DE RESULTAT ####
		compareSortie, err := os.Create(fmt.Sprint("CompareSortie"))
		if err != nil {
			fmt.Println("Erreur lors de la création du fichier de sortie.")
			log.Fatal(err)
		}
		
		//#### OUVERTURE DES FICHIERS A COMPARER ####
		compareA, err := os.Open("../CompareA")
		if err != nil {
			fmt.Println("Impossible d'ouvrir le fichier \"CompareA\"")
			log.Fatal(err)
		}
		readerA := bufio.NewReader(compareA)
		
		compareB, err := os.Open("../CompareB")
		if err != nil {
			fmt.Println("Impossible d'ouvrir le fichier \"CompareB\"")
			log.Fatal(err)
		}
		readerB := bufio.NewReader(compareB)
		
		compareOriginal, err := os.Open("../CompareOriginal")
		if err != nil {
			fmt.Println("Impossible d'ouvrir le fichier \"CompareOriginal\"")
			log.Fatal(err)
		}
		readerOriginal := bufio.NewReader(compareOriginal)
		
		// ##### LECTURE ET COMPARAISON DES FICHIERS #####
		//Tant que toutes les valeurs lues ne sont pas nulles: 
		//1 - Lire la première ligne des fichiers:
		lineOriginal, _, errOriginal := readerOriginal.ReadLine()
		lineA, _, errA := readerA.ReadLine()
		lineB, _, errB := readerB.ReadLine()
		
		lineOriginalString := string(lineOriginal)
		lineAString := string(lineA)
		lineBString := string(lineB)
		
		//Compteur de lignes
		var numLigne int
		numLigne = 1;
		
		//2 - Tant que aucune erreur sur les lectures (il reste des lignes):
		for(errOriginal == nil || errA == nil || errB == nil ) {
			fmt.Println("\nLigne numéro: " +  strconv.Itoa(numLigne))
			if(lineOriginalString == lineAString){
				if(lineOriginalString == lineBString){
					//Si A = Original et B = Original => Sortie = Original
					compareSortie.WriteString(lineOriginalString + "\r\n")	
				} else {
					//Si A = Original et B != Original => Sortie = B
					compareSortie.WriteString(lineBString + "\r\n")
				}
			} else {
				if(lineOriginalString == lineBString){
					//Si A != Original et B = Original => Sortie = A
					compareSortie.WriteString(lineAString + "\r\n")
				} else {
					if(lineBString == lineAString){
						//Si A != Original et B != Original mais A = B => Sortie = A(=B)
						compareSortie.WriteString(lineAString + "\r\n")
					} else {
						//Si A != Original et B != Original et A != B => CONFLIT!!! Sortie = Original
						fmt.Println("ATTENTION: Conflit sur la ligne " + strconv.Itoa(numLigne))
						compareSortie.WriteString(lineOriginalString + "\r\n")
					}
				}			
			}

			//Affichage des lignes précédentes (byte[])
			fmt.Println("Ligne Originale: " + lineOriginalString)
			fmt.Println("Ligne A: " + lineAString)
			fmt.Println("Ligne B: " + lineBString)
			
			//Lecture des lignes et formatage en String
			lineOriginal, _, errOriginal = readerOriginal.ReadLine()
			lineA, _, errA = readerA.ReadLine()
			lineB, _, errB = readerB.ReadLine()
			
			lineOriginalString = string(lineOriginal)
			lineAString = string(lineA)
			lineBString = string(lineB)
			
			//Incrémentation du numéro de ligne
			numLigne = numLigne+1
		}
	}
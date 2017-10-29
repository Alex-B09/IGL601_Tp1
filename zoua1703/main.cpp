#include <iostream>
#include <fstream>
#include <stdio.h>
#include <string>
#include <string.h>

using namespace std;





 /**
  *
  * Description
  * CompareA :          Fichier contenant les changements faits par développeur A.
  * CompareB :          Fichier contenant les changements faits par développeur B.
  * CompareOriginal :   Fichier original, ancétre de CompareA et de CompareB.
  *
  * Entrées
  * Les trois fichiers cités plus haut doivent etres dans le meme repertoire d'execution
  *
  * Sortie
  * sortieC:            Produit les choix fait par l'algorithme et sa résolution de conflit.
  *
  *
  *
  *
  * Fonctionnement
  *
  * Le programme load et ouvre respectivement les trois fichiers cités précedement
  * Le programme fait une lécture de toute les lignes une a une, puis compare les characteres lus entre eux.
  * Tant que nous ne sommes pas rendus a la fin de tous les fichiers on continue la lecture.
  * Ceci sous entends que les fichiers peuvent avoir differents nombre de lignes.
  *
  * Traitement
  *
  * Nous allons considerer que le developpeur du CompareB a toujours raison, ceci amorcera notre résolution de conflits
  *
  * A -  Cas ou l'ancetre ne presente aucun changement comparé au CompareA & au CompareB
  * B -  Cas ou UNIQUEMENT l'un des fichiers CompareA ou CompareB presente des changements comparés a l'ancetre:
  *             - Si CompareA ! = original ---> écriture du du changement provenant du CompareA
  *             - meme logique pour le CompareB
  * C - Cas ou les deux fichiers présentent des differences avec l'ancetre, mais ces differences sont EGALES entre-elles.
  * D - Cas ou les deux fichiers présentent des differences avec l'ancetre, mais ces differences ne sont PAS EGALES entre-elles.
  *     Présence d'un conflit:
  *     Sa résolution automatique a été faite de facon arbitraire en donnant la priorité au CompareB
  *     Tous les conflits presenteront en sortie le résultat de CompareB
  *
  * References:
  * https://en.wikipedia.org/wiki/Merge_(version_control)
  * http://www.drdobbs.com/tools/three-way-merging-a-look-under-the-hood/240164902
  * http://www.cplusplus.com/reference/cstring/strcmp/
  *
  */


int main()
{
    ifstream CompareA;
    ifstream CompareB;
    ifstream CompareOriginal;
    ofstream sortieC;
    CompareA.open ("./CompareA.txt");
    CompareB.open ("./CompareB.txt");
    CompareOriginal.open ("./CompareOriginal.txt");
    sortieC.open("./sortieC.txt");

    char sortieA[256], sortieB[256], original[256];
	int j = 0;


	while(!CompareA.eof() || !CompareB.eof() || !CompareOriginal.eof())
	{
		CompareA.getline(sortieA,256);
		CompareB.getline(sortieB,256);
		CompareOriginal.getline(original,256);
		j++;


		if(strcmp(sortieA,original) == 0  && strcmp(sortieB,original) == 0)
		{
			cout << j << " : is the line number in file " << "\n";
			cout << "   " << original << "\n";
            sortieC <<  original<< "\n";
		}

		if(strcmp(sortieA,original) == 0  && strcmp(sortieB,original) != 0 )
		{
			cout << j << " : is the line number in file " << "\n";
			cout << "   " << sortieB << "\n";
            sortieC <<  sortieB<< "\n";
		}

		if(strcmp(sortieA,original) != 0  && strcmp(sortieB,original) == 0)
		{
			cout << j << " : is the line number in file " << "\n";
			cout << "   " << sortieA << "\n";
            sortieC <<  sortieA<<"\n";
		}

		if(strcmp(sortieA,original) != 0  && strcmp(sortieB,original) != 0 && strcmp(sortieB,sortieA) ==0 )
		{
			cout << j << " : is the line number in file " << "\n";
			cout << "   " << sortieA << "\n";
            sortieC <<  sortieA<<  "\n";
			// on aurait pu écrire sortieB a la place, de toute les facons
			// sortieA == sortieB
		}

		if(strcmp(sortieA,original) != 0  && strcmp(sortieB,original) != 0 && strcmp(sortieB,sortieA) !=0 )
		{
			cout << j << " : is the line number in file " << "\n";
            // Nous sommes  en présence d'un conflit a ce niveau
            //sa résolution se fait en prennant la changement apporté par le developpeur du compareB a la ligne J.
            // car compareB a été designé developpeur qui a toujours raison plus haut dans la description
			cout << "   " << sortieB << "\n";
            sortieC <<  sortieB<<  "\n";
            }

        }


    CompareA.close();
    CompareB.close();
    CompareOriginal.close();
    sortieC.close();
    return 0;
}

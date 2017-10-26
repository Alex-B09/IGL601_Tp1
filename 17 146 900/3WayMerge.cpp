#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <cstdio>
using namespace std;


int main()
{

	//Ouverture du fichier Sortie en écriture
	ofstream Ecriture("CompareSortie");
	if (!Ecriture)
	{
		cout << "Erreur de creation" << endl;
		system("PAUSE");
		return 0;
	}




	//Ouverture des fichiers en lecture
	ifstream OuvertureA("CompareA");
	ifstream OuvertureB("CompareB");
	ifstream OuvertureO("CompareOriginal");

	if (!OuvertureA)
	{
		cout << "Erreur d'ouverture A" << endl;
		system("PAUSE");
		return 0;
	}

	if (!OuvertureB)
	{
		cout << "Erreur d'ouverture B" << endl;
		system("PAUSE");
		return 0;
	}

	if (!OuvertureO)
	{
		cout << "Erreur d'ouverture O" << endl;
		system("PAUSE");
		return 0;
	}






	int i = 0;

	string ligne_O;
	string ligne_A;
	string ligne_B;


	while (getline(OuvertureA, ligne_A) && getline(OuvertureB, ligne_B) && getline(OuvertureO, ligne_O))
	{
		if (ligne_O == ligne_A && ligne_B == ligne_O)
			Ecriture << ligne_O << endl;
		else if (ligne_A == ligne_O && ligne_B != ligne_O)
			Ecriture << ligne_B << endl;
		else if (ligne_A != ligne_O && ligne_B == ligne_O)
			Ecriture << ligne_A << endl;
		else if (ligne_A != ligne_O && ligne_B != ligne_O && ligne_A != ligne_B)
			Ecriture << ligne_A << endl;
		if (!Ecriture)
		{
			cout << "Erreur d'écriture" << endl;
			system("PAUSE");
			return 0;
		}

		i++;
	}


	//Si A et B ne sont pas finis
	while (getline(OuvertureA, ligne_A) && getline(OuvertureB, ligne_B))
	{
		Ecriture << ligne_A << endl;
		i++;
	}


	//Si A n'est pas fini
	while (getline(OuvertureA, ligne_A))
	{
		if (getline(OuvertureO, ligne_O) && (ligne_A == ligne_O))
			Ecriture << endl; 
		else
			Ecriture << ligne_A << endl;
	}


	//Si B n'est pas fini
	while (getline(OuvertureB, ligne_B))
	{
		if (getline(OuvertureO, ligne_O) && (ligne_B == ligne_O))
			Ecriture << endl;
		else
			Ecriture << ligne_B << endl;
	}


	//Fermeture des flux
	OuvertureA.close();
	OuvertureB.close();
	OuvertureO.close();
	Ecriture.close();



	// Affichage de CompareSortie dans la console 
	cout << endl;
	cout << "Affichage du contenu du fichier CompareSortie" << endl;
	cout << endl;
	ifstream Output("CompareSortie");
	string ligne_Output;
	while (getline(Output, ligne_Output))
		cout << ligne_Output << endl;
	Output.close();



	system("PAUSE");
	return 0;
}




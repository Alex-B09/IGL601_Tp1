#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <cstdio>
using namespace std;


int main()
{

	ofstream Ecriture("Output");


	if (!Ecriture)
	{
		cout << "Erreur de creation" << endl;
		system("PAUSE");
		return 0;
	}



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
		{
			Ecriture << "La ligne numero" << i + 1 << "a été modifiée par A et B" << endl;
			Ecriture << "A a écrit : " << ligne_A << endl;
			Ecriture << "B a écrit : " << ligne_B << endl;
		}
		if (!Ecriture)
		{
			cout << "Erreur d'écriture" << endl;
			system("PAUSE");
			return 0;
		}

		i++;
	}

	while (getline(OuvertureA, ligne_A) && getline(OuvertureB, ligne_B))
	{
		//cout << "A et B non finis" << endl;

		if (ligne_A == ligne_B)
			Ecriture << ligne_A << endl;
		else
		{
			//cout << "A et B differents de O" << endl;
			Ecriture << "La ligne numero" << i + 1 << "a été modifiée par A et B" << endl;
			Ecriture << "A a écrit : " << ligne_A << endl;
			Ecriture << "B a écrit : " << ligne_B << endl;
		}
		i++;
	}

	while (getline(OuvertureA, ligne_A))
	{
		//cout << "A non fini" << endl;

		if (getline(OuvertureO, ligne_O))
		{
			if (ligne_A != ligne_O)
			{
				//cout << "O non fini et A different de O" << endl;
				Ecriture << ligne_A << endl;
			}
			else
				Ecriture << endl;
		}
		else
		{
			//cout << "O fini" << endl;
			Ecriture << ligne_A << endl;
		}
	}

	while (getline(OuvertureB, ligne_B))
	{
		//cout << "B non fini" << endl;

		if (getline(OuvertureO, ligne_O))
		{
			if (ligne_B != ligne_O)
			{
				//cout << "O non fini et B different de O" << endl;
				Ecriture << ligne_B << endl;
			}
			else
				Ecriture << endl;
		}
		else
		{
			//cout << "O fini" << endl;
			Ecriture << ligne_B << endl;
		}
	}


	OuvertureA.close();
	OuvertureB.close();
	OuvertureO.close();
	Ecriture.close();

	cout << endl;
	cout << "Affichage du contenu du fichier Output" << endl;
	cout << endl;

	ifstream Output("Output");

	string ligne_Output;
	while (getline(Output, ligne_Output))
		cout << ligne_Output << endl;

	Output.close();

	system("PAUSE");
	return 0;
}




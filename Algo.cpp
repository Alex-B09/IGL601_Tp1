#include <fstream>
#include <iostream>
#include <string>
using namespace std;

void main()
{
	ifstream original, compareA, compareB;
	ofstream sortie;
	string lineA, lineB, lineO, lineS;

	//Ouverture des fichiers

	original.open("CompareOriginal");
	compareA.open("CompareA");
	compareB.open("CompareB");
	sortie.open("CompareSortie");
	if (!original.is_open() || !compareA.is_open() || !compareB.is_open() || !sortie.is_open())
	{
		cout << "probleme a l'ouverture des fichiers" << endl;
		return;
	}

	// merge

	while (original || compareA || compareB)
	{
		//fermeture si doc vide
		if (!getline(compareA, lineA))
		{
			lineA = "";
			compareA.close();
		}

		if (!getline(compareB, lineB))
		{
			lineB = "";
			compareB.close();
		}

		if (!getline(original, lineO))
		{
			lineO = "";
			original.close();
		}

		if (original || compareA || compareB)
		{
			if (lineA == lineB)
			{
				lineS = lineA;
			}
			else
			{
				if (lineA == lineO)
				{
					lineS = lineB;
				}
				else if (lineB == lineO)
				{
					lineS = lineA;
				}
				else
				{
					lineS = "probleme ne gere pas les conflits!!!";
				}
			}
			sortie << lineS + "\n";
		}
	}

	sortie.close();

	return;
}
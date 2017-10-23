#include <fstream>
#include <iostream>
#include <string>

using namespace std;

/*
Algorithme de 3 way merge simple sans gestion de conflit
le 23 octobre 2017 , Maxime Robert
*/
void main()
{
	//D�claration de variables

	ifstream original;
	ifstream compareA;
	ifstream compareB;
	ofstream sortie;
	string lineA;
	string lineB;
	string lineO;
	string lineS;

	//Ouverture des fichiers

	original.open("CompareOriginal");
	if (!original.is_open())
	{
		cout << "CompareOriginal could not be open" << endl;
		return;
	}
	
	compareA.open("CompareA");
	if (!compareA.is_open())
	{
		cout << "CompareA could not be open" << endl;
		return;
	}

	compareB.open("CompareB");
	if (!compareA.is_open())
	{
		cout << "CompareB could not be open" << endl;
		return;
	}

	sortie.open("CompareSortie");
	if (!compareA.is_open())
	{
		cout << "CompareSortie could not be open" << endl;
		return;
	}

	//Comparaison des fichiers

	while(original || compareA || compareB)
	{
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
					lineS = "<Conflit>";
				}
			}	
			sortie << lineS + "\n";
		}
	}

	sortie.close();

	return;
}
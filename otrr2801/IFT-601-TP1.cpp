///////////////////////////////////////////////////////
// author Ruslan Otroc - 15203405


#include <fstream>
#include <iostream>
#include <string>


using namespace std;


int main(int argc, char* argv[])
{
	
	ifstream fileA;
	ifstream fileB;
	ifstream fileOriginal;
	ofstream fileSortie;

	string valueA;
	string valueB;
	string valueOriginal;
	
	
	fileA.open("./CompareA");
	fileB.open("./CompareB");
	fileOriginal.open("./CompareOriginal");
	fileSortie.open("./CompareSortie");
	
	
	while (!fileA.eof() || !fileB.eof() || !fileOriginal.eof())
	{
		getline(fileA, valueA);
		getline(fileB, valueB);
		getline(fileOriginal, valueOriginal);

		
		// valueA et valueB sont pareil
		if (valueA == valueB) 
		{
			fileSortie << valueB << "\n";
		}			

		// valueB est change
		if (valueA != valueB  && valueA == valueOriginal) 
		{
			fileSortie << valueB << "\n";
		}

		// valueA est change
		if (valueA != valueB  && valueB == valueOriginal) 
		{
			fileSortie << valueA << "\n";
		}

		// valueA et valueB sont changes, donc il'a un conflict
		if (valueA != valueB  && valueB != valueOriginal && valueA != valueOriginal) 
		{
			fileSortie << "Conflict\t" << valueOriginal << "\t" << valueA << "\t" << valueB << "\n";
		}
		

	}
	
	
	fileA.close();
	fileB.close();
	fileOriginal.close();
	fileSortie.close();
		

	return 0;
}



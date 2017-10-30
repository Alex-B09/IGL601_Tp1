#include <string> 
#include <fstream>

 int main()
 {
	std::ofstream fichierSortie("CompareSortie");
	std::ifstream fichierOriginal("CompareOriginal");
 	std::ifstream fichierA("CompareA");
 	std::ifstream fichierB("CompareB");
 	std::string ligneA;
 	std::string ligneB;
 	std::string ligneOriginal;
 
 	if (fichierOriginal && fichierA && fichierB  && fichierSortie)
 	{
 		while (fichierA || fichierB || fichierOriginal)
 		{
 			std::getline(fichierA, ligneA);
 			std::getline(fichierB, ligneB);
 			std::getline(fichierOriginal, ligneOriginal);
 
			// si ligne A est inchangee on prend ligne B
			if (ligneA == ligneOriginal) 
			{
				fichierSortie << ligneB + "\n";
			}
			// si ligne B est inchangee on prend ligne A
			else if (ligneB == ligneOriginal) 
			{
				fichierSortie << ligneA + "\n";
			}
			// sinon on prend celle qui est plus longue (car je suppose qu'il y a eu plus d'effort base sur ce critere)
			else if (ligneA.length() > ligneB.length()) 
			{
				fichierSortie << ligneA + "\n";
			}
			else
			{
				fichierSortie << ligneB + "\n";
			}
 		}
 	}
 } 
/*==================================================
Benoit Dubreuil 16 084 193
TP1
IGL601
30 octobre 2017
====================================================*/
#include <fstream>
#include <iostream>
#include <string>

void diff3(std::string fileNameO, std::string fileNameA, std::string fileNameB)
{
	std::ifstream fileO(fileNameO);
	std::ifstream fileA(fileNameA);
	std::ifstream fileB(fileNameB);
	std::ofstream fileOut("compareSortie");

	std::string lineA;
	std::string lineB;
	std::string lineO;
	
	bool conflit = false;
	while (fileO || fileA || fileB)
	{
		std::getline(fileO, lineO);
		std::getline(fileA, lineA);
		std::getline(fileB, lineB);

		if (lineA == lineB)
		{
			if (conflit){
				fileOut << ">>>" << fileNameB << std::endl;
				conflit = false;
			}
			fileOut << lineA << std::endl;
		}
		else
		{
			// Avec ou sans conflit
			if (lineO == lineA)
			{
				if (conflit){
					fileOut << ">>>" << fileNameB << std::endl;
					conflit = false;
				}
				fileOut << lineB << std::endl;
			}
			else if (lineO == lineB)
			{
				if (conflit){
					fileOut << ">>>" << fileNameB << std::endl;
					conflit = false;
				}
				fileOut << lineA << std::endl;
			}
			// Conflit il y a
			else
			{
				if (!conflit){
					fileOut << "<<<" << fileNameA << std::endl;
					conflit = true;
				}
				fileOut << " --a: " << lineA << std::endl;
				fileOut << " --b: " << lineB << std::endl;
			}
		}
	}
	fileO.close();
	fileA.close();
	fileB.close();
	fileOut.close();
}

int main(int argc, char *argv[])
{
	std::string fileNameA = "CompareA";
	std::string fileNameB = "CompareB";
	std::string fileNameO = "CompareOriginal";
	if (argc == 3){
		fileNameO = argv[0];
		fileNameA = argv[1];
		fileNameB = argv[2];
	}
	diff3(fileNameO, fileNameA, fileNameB);
	return 0;
}
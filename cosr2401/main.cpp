#include <fstream>
#include <string>

int main()
{
	std::ifstream fileA("CompareA");
	std::ifstream fileB("CompareB");
	std::ifstream fileOrig("CompareOriginal");
	std::ofstream fileOut("CompareSortie");

	std::string lineA;
	std::string lineB;
	std::string lineOut;

	if (fileA && fileB && fileOrig && fileOut)
	{
		while (fileA || fileB || fileOrig)
		{
			std::getline(fileA, lineA);
			std::getline(fileB, lineB);
			std::getline(fileOrig, lineOut);

			//This doesn't resolve conflicts when all lines are mutually different
			fileOut << (lineA == lineOut ? lineB : lineA) + "\n";
		}
	}
}
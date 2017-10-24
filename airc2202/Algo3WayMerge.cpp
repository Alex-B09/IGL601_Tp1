/******************************************************************* Includes *******************************************************************************************/
#include <iostream>
#include <fstream>
#include <string>
using namespace std;

/******************************************************************* Fonctions Déclaration ******************************************************************************/
bool allFileIsOpen(ifstream &compareOriginal,ifstream &compareA,ifstream &compareB,ofstream &compareSortie);
void closeAllFile(ifstream &compareOriginal,ifstream &compareA,ifstream &compareB,ofstream &compareSortie);
bool isEmpty(string line);
void nextLine(ifstream &file,string &line);


/******************************************************************* Main ***********************************************************************************************/
int main (int argc, char *argv[]){
    string lineOriginal,lineA,lineB;

    if(argc==5){//Controle du nombre d'argument
        ifstream compareOriginal (argv[1]);//("../CompareOriginal");
        ifstream compareA        (argv[2]);//("../CompareA");
        ifstream compareB        (argv[3]);//("../CompareB");
        ofstream compareSortie   (argv[4]);//("CompareSortie");

        if(allFileIsOpen(compareOriginal,compareA,compareB,compareSortie)){
          while (!compareOriginal.eof() || !compareA.eof() || !compareB.eof())//Teste la fin du fichier
          {
              nextLine(compareOriginal,lineOriginal);
              nextLine(compareA,lineA);
              nextLine(compareB,lineB);

              /*Ecriture dans le fichier de sortie*/
              if ((lineA==lineB) || //Si les lignes lus sont identiques ou si une ligne est vide
                    (lineA==lineOriginal && isEmpty(lineB)) ||
                      (isEmpty(lineA) && lineB==lineOriginal)) {
                compareSortie << lineOriginal <<"\n";
              }
              else if (lineA==lineOriginal && lineB!=lineOriginal) {//Si une ligne est différente dans le fichier B
                compareSortie << lineB <<"\n";
              }
              else if (lineA!=lineOriginal && lineB==lineOriginal) {//Si une ligne est différente dans le fichier A
                compareSortie << lineA <<"\n";
              }
          }

          closeAllFile(compareOriginal,compareA,compareB,compareSortie);
      }
      else{
          cout << "Impossible d'ouvrir un des fichiers";
      }
  }else{
    cout <<"\nHelp : \n";
    cout <<"./3WayMerge <nom_fichier_original> <nom_fichier_A> <nom_fichier_B> <nom_fichier_merge> \n";
  }
  return 0;
}

/******************************************************************* Fonctions Implémentations***************************************************************************/

/************************************************************************************************************
* Fonction : Sert à lire une ligne d'un fichier
* Entrées : file=le fichier à lire, line=le string servant à stocker la ligne lu
* Sorties : aucune
************************************************************************************************************/
void nextLine(ifstream &file,string &line){
  if(!file.eof()){
    getline (file,line);//Si le fichier n'est pas fini on prend une nouvelle ligne
  }else{
    line.clear();//Sinon on remet à zéro la variable stockant la ligne
  }
}


/************************************************************************************************************
* Fonction : Sert à savoir si un string est vide ou non
* Entrées : line=le string stockant la ligne lu
* Sorties : bool, true=string est vide / false=le string à une valeur non nulle
************************************************************************************************************/
bool isEmpty(string line){
  bool empty=false;
  if(line.size()==0)
    empty=true;

  return empty;
}


/************************************************************************************************************
* Fonction : Sert à savoir si un fichier est correctement ouvert
* Entrées : ifstream compare...=fichier lu, ofstream compareSortie=fichier écrit
* Sorties : bool, true=tous les fichiers sont ouverts / false=un problème est survenu sur un des fichiers
************************************************************************************************************/
bool allFileIsOpen(ifstream &compareOriginal,ifstream &compareA,ifstream &compareB,ofstream &compareSortie){
  bool openOk=true;
  if (!compareOriginal.is_open())
  {
    openOk=false;
    cout << "Impossible d'ouvrir le fichier CompareOriginal";
  }
  if (!compareA.is_open())
  {
    openOk=false;
    cout << "Impossible d'ouvrir le fichier CompareA";
  }
  if (!compareB.is_open())
  {
    openOk=false;
    cout << "Impossible d'ouvrir le fichier CompareB";
  }
  if (!compareSortie.is_open())
  {
    openOk=false;
    cout << "Impossible d'ouvrir le fichier CompareSortie";
  }
  return openOk;
}


/************************************************************************************************************
* Fonction : Sert à fermer les fichiers lues et écrits
* Entrées : ifstream compare...=fichier lu, ofstream compareSortie=fichier écrit
* Sorties : aucune
************************************************************************************************************/
void closeAllFile(ifstream &compareOriginal,ifstream &compareA,ifstream &compareB,ofstream &compareSortie){
  compareOriginal.close();
  compareA.close();
  compareB.close();
  compareSortie.close();
}

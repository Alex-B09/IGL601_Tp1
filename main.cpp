/*
 *  Melissa L'Henoret Matricule: 17 148 784
 */

#include <iostream>
#include <fstream> //pour la lecture de fichier
#include <string>

using namespace std;

void Compare(string,string,string);

int main()
{
    Compare("CompareA","CompareB","CompareTemp");
    Compare("CompareTemp", "CompareOriginal", "CompareS");

    remove("CompareTemp"); //supprimer fichier temporaire
    return 0;
}

void Compare(string a, string b, string s){

    ifstream fichierA(a.c_str(), ios::in); // ouverture du flux de lecture du fichier A
    ifstream fichierB(b.c_str(), ios::in);
    ofstream fichierS(s.c_str(), ios::out | ios::trunc); // ouverture du flux d'ecriture du fichier de sortie

    if(!fichierS)
        cerr << "Impossible d'ouvrir le fichier :"<< s << endl;

    string ligneA;
    string ligneB;

    bool continu = true; //condition de sortie de la boucle

    getline(fichierA, ligneA);
    getline(fichierB, ligneB);

    while(continu){

        if(ligneA == ligneB){
            fichierS << ligneA << endl;
        }
        else if(ligneA == ""){
            fichierS << ligneB << endl;
        }
        else{
            fichierS << ligneA << endl;
        }

        bool contA = getline(fichierA, ligneA);
        bool contB = getline(fichierB, ligneB);
        continu = contA || contB; //Si on arrive à la fin des deux fichiers on met continu à false et on sort de la boucle
    }

    fichierA.close();  // on ferme le fichierA
    fichierB.close();  // on ferme le fichierB
    fichierS.close();  // on ferme le fichierS
}


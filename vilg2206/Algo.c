/*             
                *****************************************************
                *                                                   *
                *                TP 1 - 3-Ways Merge                *
                *                                                   * 
                * Villa Gabriel	             Matricule : 17 135 594 *
                *****************************************************
*/

#include <stdio.h>
#include <math.h>
#include <stdlib.h>

int egaux(char a,char b, char c);
char choix(char a, char b, char c);
void compare();

int main(int argc, char *argv[])
{
	compare();
  	return 0;
}

//Cette fonction prends trois char en paramètre et renvoie 1 si les trois sont identiques et 0 sinon
int egaux(char a,char b, char c){
	if (a!=b)
		return 0;
	if (b!=c)
		return 0;
	return 1;
}

/*
*Cette fonction prends trois char en paramètre et renvoie le charactère a mettre dans le fichier de résultat.
* Elle est suffisante pour l'exemple du tp mais c'est cette fonction qu'il faudrait améliorer pour avoir un 3-Ways Merge plus avancé	
*/
char choix(char a, char b, char c){
	if ((a == '\n'|| a == EOF ) && (b == '\n'|| b == EOF ))
		return c;
	if ((b == '\n'|| b == EOF ) && (c == '\n'|| c == EOF ))
		return a;
	if ((a == '\n'|| a == EOF ) && (c == '\n'|| c == EOF ))
		return b;	
}

void compare(){

	//On commence par charger les fichiers que l'on va utiliser
	FILE* fichier1 = NULL;
	FILE* fichier2 = NULL;
	FILE* fichier3 = NULL;
	FILE* fichierRes = NULL;

	fichier1 = fopen("CompareOriginal", "r+");
	fichier2 = fopen("CompareA", "r+");
	fichier3 = fopen("CompareB", "r+");
	fichierRes = fopen("Resultat", "w");

	//On vérifie qu'on a bien reussi a charger les fichiers
	if (fichier1 == NULL || fichier2 == NULL || fichier3 == NULL || fichierRes == NULL)
    	{
	        printf("Impossible d'ouvrir les fichiers necessaires\n");
    	}
	//Si l'ouverture des fichiers a bien fonctionné, on commence le 3-ways Merge    	
	else
    	{
		char a = fgetc(fichier1), b = fgetc(fichier2), c =  fgetc(fichier3);

		//Condition d'arret : Arrivée à la fin des trois fichiers
		while (!(a ==EOF && b == EOF && c==EOF)){

			//Lorsque les 3 chars sont les meme, la valeur du char est celle à afficher dans le fichier de resultat 
			if(egaux (a,b,c)){
	
				fputc(a, fichierRes);
				a = fgetc(fichier1);
	 			b = fgetc(fichier2);
				c = fgetc(fichier3);
			}
			//Lorque les 3 char ne sont pas pareils, on laisse la fonction choix nous donner quel char est à mettre dans le fichier 			de resultat
			
			else{
				
				fputc(choix(a,b,c),fichierRes);			
				
				//La suite de if si dessous nous permet de savoir dans quelle situation nous somme pour pouvoir rattraper les 					décalages entre les lignes finies et les autres
				if (!(a=='\n' || a == EOF))
					a = fgetc(fichier1);
				if (!(b=='\n' || b == EOF))
					b = fgetc(fichier2);
				if (!(c=='\n' || c == EOF))
					c = fgetc(fichier3);
				
				if (a==EOF && b=='\n' && c=='\n'){
					fputc(b, fichierRes);
					b = fgetc(fichier2);
					c = fgetc(fichier3);
				}
				if (a=='\n' && b==EOF && c=='\n'){
					fputc(a, fichierRes);
					a = fgetc(fichier1);
					c = fgetc(fichier3);
				}
				if (a=='\n' && b=='\n' && c==EOF){
					fputc(a, fichierRes);
					a = fgetc(fichier1);
					b = fgetc(fichier2);
				}
			} 
		}	

		//Enfin, on ferme les fichier que l'on avait chargé
		fclose(fichier1);
		fclose(fichier2);
		fclose(fichier3);
		fclose(fichierRes);	
	}
}	

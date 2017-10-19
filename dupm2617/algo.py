#!/usr/bin/python
""" Permet d'implémenter une fusion tri-partite entre trois fichiers

Les fichiers en questions sont CompareOriginal, CompareA et CompareB

On assume que CompareOriginal est le fichier original sur lequel
CompareA et CompareB ont fait des changements

C'est un algorithme de fusion qui favorise original et ne permet
pas la suppression de lignes non-vides présentes dans l'original

Les fichiers en question comportent du texte sur plusieurs lignes

La sortie est un fichier nommé CompareSortie
"""

import sys
import itertools

__author__ = "Marc Dupuis"
__credits__ = ["Marc Dupuis", "Alexandre Boulanger"]
__license__ = "GPL"
__version__ = "1.0"
__maintainer__ = "Marc Dupuis"
__email__ = "marc.dupuis@usherbrooke.ca"
__status__ = "Prototype"


"""
    Genere une liste composé des deux liste en input
    La liste original placée en premier parametre
"""
def mergeFileToList(fileA, fileB,):
    toReturn = []
    #zip_longest s'assure de boucler jusqu'a la liste la plus longue
    for lineA, lineB in itertools.zip_longest(fileA, fileB):
        # Si B n'est pas vide, et que A est vide on prend B Sinon A
        if lineB != None and lineB != '\n' and (lineA == None or lineA == '\n'):
            toReturn.append(lineB)
        else:
            toReturn.append(lineA)
    return toReturn

def main():

    # Précaution, car j'utilise entre autre zip_longest et la syntax de 3.0
    if sys.version_info[0] < 3:
        raise Exception("Python 3 or a more recent version is required.")

    compareOriginal= open("../CompareOriginal", "r")
    compareA = open("../CompareA", "r")
    compareB = open("../CompareB", "r")
    compareSortie = open("CompareSortie", "r+")

    # On va copier A et original dans une liste
    mergeList = mergeFileToList(compareOriginal, compareA)
    # On rajoute B a a et Original
    mergeList = mergeFileToList(mergeList, compareB)

    # On s'assure que le fichier de sortie est vide
    compareSortie.seek(0)
    compareSortie.truncate()

    # On ecrit
    for line in mergeList :
        compareSortie.write(line)

    compareSortie.seek(0)
    print("Comapresortie\n")
    print(compareSortie.read())

if __name__ == "__main__":
    main()


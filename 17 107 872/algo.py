# -*- coding: utf-8 -*-
"""
Created on Sun Oct 08 17:50:04 2017

@author: Benjamin
"""

fo = open("CompareOriginal", 'r')
fa = open("CompareA", 'r')
fb = open("CompareB", 'r')

fichier = open("CompareSortie", "w")

lignesO = fo.readlines()
lignesA = fa.readlines()
lignesB = fb.readlines()

fo.close()
fa.close()
fb.close()

nbLignesMax = max([len(lignesO), len(lignesA), len(lignesB)])

#On remplit les listes afin qu'elles aient des longueurs identiques
while (len(lignesO) < nbLignesMax):
    lignesO.append("\n")

while (len(lignesA) < nbLignesMax):
    lignesA.append("\n")
    
while (len(lignesB) < nbLignesMax):
    lignesB.append("\n")

#Comparaison des fichiers
f = lignesO
for l in range(nbLignesMax):
    if (f[l] != lignesA[l] and f[l] == lignesB[l]):
        f[l] = lignesA[l]
    elif (f[l] == lignesA[l] and f[l] != lignesB[l]):
        f[l] = lignesB[l]
    elif (f[l] != lignesA[l] and f[l] != lignesB[l] and lignesA[l] == lignesB[l]):
        f[l] = lignesA[l]
    elif (f[l] != lignesA[l] and f[l] != lignesB[l] and lignesA[l] != lignesB[l]):
        f[l] = "Conflit !"

#Remplissage du fichier de sortie
for ligne in f:
    print ligne
    fichier.write("%s" % ligne)
  
fichier.close()
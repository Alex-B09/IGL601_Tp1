import os

def findLongestList(original, a, b):
    if len(original) >= len(a) and len(original) >= len(b):
        return original
    elif len(a) >= len(original) and len(a) >= len(b):
        return a
    else:
        return b

def compareLignes(ligneOriginal, ligneA, ligneB):
    if ligneA == ligneB:
        return ligneA
    elif ligneA != ligneB and ligneB == ligneOriginal:
        return ligneA
    elif ligneA != ligneB and ligneA == ligneOriginal:
        return ligneB
    else:
        # ligneA and ligneB are different from eachother and different from ligneOriginal
        return "conflit\n"

def threeWayMerge(original, a, b):
    with open("CompareSortie", "w+") as compareSortie:
        longestList = findLongestList(original, a, b)
        for line in longestList:
            compareSortie.write(compareLignes(original[0], a[0], b[0]))
            if len(original) > 1:
                original = original[1:]
            else:
                original = [" \n"]
            if len(a) > 1:
                a = a[1:]
            else:
                a = [" \n"]
            if len(b) > 1:
                b = b[1:]
            else:
                b = [" \n"]

def main():
    dossierParent = os.pardir
    with open(os.path.join(dossierParent, "CompareOriginal")) as original:
        with open(os.path.join(dossierParent, "CompareA")) as a:
            with open(os.path.join(dossierParent, "CompareB")) as b:
                threeWayMerge(original.readlines(), a.readlines(), b.readlines())


if __name__ == "__main__":
    main()

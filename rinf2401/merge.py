# ============================================================================
# Francois Ringuette (14195131)
# Source: https://github.com/schuhschuh/cmake-basis/blob/master/src/utilities/python/diff3.py
# ============================================================================

from __future__ import unicode_literals
from operator import xor
import sys

# ----------------------------------------------------------------------------
def diff3(textA, textOri, textB):
    """Three-way diff based on the GNU diff3.c by R. Smith.

    @param [in] textA   Array of lines of your text.
    @param [in] textOri   Array of lines of original text.
    @param [in] textB  Array of lines of their text.

    @returns Array of tuples containing diff results. The tuples consist of
             (cmd, lowA, highA, lowB, highB), where cmd is either one of
             '0', '1', '2', or 'A'.

    """
    # diff result => [(cmd, lowA, highA, lowB, highB), ...]
    d2 = (diff(textOri, textA), diff(textOri, textB))
    df3 = []
    result3 = [None,  0, 0,  0, 0,  0, 0]
    while d2[0] or d2[1]:
        # find a continual range in textOri text low2..high2
        # changed by textA or by textB.
        #
        #     d2[0]        222    222222222
        #  textOri     ...L!!!!!!!!!!!!!!!!!!!!H...
        #     d2[1]          222222   22  2222222
        r2 = ([], [])
        if not d2[0]: i = 1
        else:
            if not d2[1]: i = 0
            else:
                if d2[0][0][1] <= d2[1][0][1]: i = 0
                else: i = 1
        j  = i
        k  = xor(i, 1)
        hi = d2[j][0][2]
        r2[j].append(d2[j].pop(0))
        while d2[k] and d2[k][0][1] <= hi + 1:
            hi_k = d2[k][0][2]
            r2[k].append(d2[k].pop(0))
            if hi < hi_k:
                hi = hi_k
                j  = k
                k  = xor(k, 1)
        lo2 = r2[i][ 0][1]
        hi2 = r2[j][-1][2]
        # take the corresponding ranges in textA low0..high0
        # and in textB low1..high1.
        #
        #   textA     ..L!!!!!!!!!!!!!!!!!!!!!!!!!!!!H...
        #      d2[0]        222    222222222
        #   textOri     ...00!1111!000!!00!111111...
        #      d2[1]          222222   22  2222222
        #  textB          ...L!!!!!!!!!!!!!!!!H...
        if r2[0]:
            low0 = r2[0][ 0][3] - r2[0][ 0][1] + lo2
            high0 = r2[0][-1][4] - r2[0][-1][2] + hi2
        else:
            low0 = result3[2] - result3[6] + lo2
            high0 = result3[2] - result3[6] + hi2
        if r2[1]:
            low1 = r2[1][ 0][3] - r2[1][ 0][1] + lo2
            high1 = r2[1][-1][4] - r2[1][-1][2] + hi2
        else:
            low1 = result3[4] - result3[6] + lo2
            high1 = result3[4] - result3[6] + hi2
        # detect type of changes
        if not r2[0]:
            cmd = '1'
        elif not r2[1]:
            cmd = '0'
        elif high0 - low0 != high1 - low1:
            cmd = 'A'
        else:
            cmd = '2'
            for d in range(0, high0 - low0 + 1):
                (i0, i1) = (low0 + d - 1, low1 + d - 1)
                ok0 = (0 <= i0 and i0 < len(textA))
                ok1 = (0 <= i1 and i1 < len(textB))
                if xor(ok0, ok1) or (ok0 and textA[i0] != textB[i1]):
                    cmd = 'A'
                    break
        df3.append((cmd,  low0, high0,  low1, high1,  lo2, hi2))
    return df3

# ----------------------------------------------------------------------------
def _diff_heckel(text_a, text_b):
    """Two-way diff based on the algorithm by P. Heckel.

    @param [in] text_a Array of lines of first text.
    @param [in] text_b Array of lines of second text.

    @returns TODO

    """
    d    = [];
    uniq = [(len(text_a), len(text_b))]
    (freq, ap, bp) = ({}, {}, {})
    for i in range(len(text_a)):
        s = text_a[i]
        freq[s] = freq.get(s, 0) + 2;
        ap  [s] = i;
    for i in range(len(text_b)):
        s = text_b[i]
        freq[s] = freq.get(s, 0) + 3;
        bp  [s] = i;
    for s, x in freq.items():
        if x == 5: uniq.append((ap[s], bp[s]))
    (freq, ap, bp) = ({}, {}, {})
    uniq.sort(key=lambda x: x[0])
    (a1, b1) = (0, 0)
    while a1 < len(text_a) and b1 < len(text_b):
        if text_a[a1] != text_b[b1]: break
        a1 += 1
        b1 += 1
    for a_uniq, b_uniq in uniq:
        if a_uniq < a1 or b_uniq < b1: continue
        (a0, b0) = (a1, b1)
        (a1, b1) = (a_uniq - 1, b_uniq - 1)
        while a0 <= a1 and b0 <= b1:
            if text_a[a1] != text_b[b1]: break
            a1 -= 1
            b1 -= 1
        if a0 <= a1 and b0 <= b1:
            d.append(('c', a0 + 1, a1 + 1, b0 + 1, b1 + 1))
        elif a0 <= a1:
            d.append(('d', a0 + 1, a1 + 1, b0 + 1, b0))
        elif b0 <= b1:
            d.append(('a', a0 + 1, a0, b0 + 1, b1 + 1))
        (a1, b1) = (a_uniq + 1, b_uniq + 1)
        while a1 < len(text_a) and b1 < len(text_b):
            if text_a[a1] != text_b[b1]: break
            a1 += 1
            b1 += 1
    return d
# ----------------------------------------------------------------------------
diff = _diff_heckel # default two-way diff function used by diff3()
# ----------------------------------------------------------------------------





with open(sys.argv[1]) as file1: fichierA = [line.rstrip('\n') for line in file1]
with open(sys.argv[2]) as file2: fichierB = [line.rstrip('\n') for line in file2]
with open(sys.argv[3]) as file3: fichierOri = [line.rstrip('\n') for line in file3]

fileOut = open("CompareSortie.txt", "w")
df3 = diff3(fichierA, fichierOri, fichierB)
text3 = (fichierA, fichierB, fichierOri)
for result3 in df3:	
    if result3[0] == '0':
        for lineno in range(result3[1], result3[2] + 1):
	    fileOut.write("L"+str(lineno)+ ": Le caractere " +text3[0][lineno - 1] +" a ete ajoute uniquement dans le fichier B.\n")
    elif result3[0] != 'A':
        for lineno in range(result3[3], result3[4] + 1):
	    fileOut.write("L" + str(lineno)+ ": Le caractere " +text3[1][lineno - 1] + " a ete ajoute uniquement dans le fichier A.\n")
    else:
	fileOut.write("Conflit aux lignes: A-L"+str(result3[1])+"a"+str(result3[2])+", Original-L"+str(result3[3])+"a"+str(result3[4])+" et B-L"+str(result3[5])+"a"+str(result3[6])+".\n")

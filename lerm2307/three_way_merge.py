#!/usr/bin/python

import sys
import os
from itertools import izip_longest

# Recursive function for 3-way merge
def three_way_merge(base, a, b):
    for base_i, a_i, b_i in izip_longest(base, a, b):
        if base_i == a_i == b_i:
            return [base_i] + three_way_merge(base[1:], a[1:] , b[1:])
        elif a_i == None or base_i == a_i:
            return [b_i if b_i is not None else ''] + three_way_merge(base[1:], a[1:] , b[1:])
        else:
            return [a_i if a_i is not None else ''] + three_way_merge(base[1:], a[1:] , b[1:])
    return []

def main():
    # Read 3 input files
    base = open(os.path.join(os.pardir, "CompareOriginal"), "r").readlines()
    a = open(os.path.join(os.pardir, "CompareA"), "r").readlines()
    b = open(os.path.join(os.pardir, "CompareB"), "r").readlines()
    # Call 3-way merge and write to output file
    open("CompareSortie", "w").writelines(three_way_merge(base, a, b))

if __name__ == "__main__":
    main()

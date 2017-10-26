import numpy as np

A = open("CompareA")
B = open("CompareB")
Original = open("CompareOriginal")

A_data = np.array([x.strip() for x in A.readlines()])
B_data = np.array([x.strip() for x in B.readlines()])
Original_data = np.array([x.strip() for x in Original.readlines()])

nbr_lines = max(len(A_data), len(B_data), len(Original_data))

A_data.resize(nbr_lines)
B_data.resize(nbr_lines)
Original_data.resize(nbr_lines)

Out_data = np.empty_like(A_data)
for i in range(nbr_lines):
    if A_data[i] == B_data[i]:
        Out_data[i] = A_data[i]
    elif A_data[i] == Original_data[i] and B_data[i] != Original_data[i]:
        Out_data[i] = B_data[i]
    elif B_data[i] == Original_data[i] and A_data[i] != Original_data[i]:
        Out_data[i] = A_data[i]
    else:
        print("Conflicts")

out = open("CompareSortie", "w")
for val in Out_data:
    out.write(val + "\n")
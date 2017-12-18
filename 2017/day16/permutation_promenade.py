# --- Day 16: Permutation Promenade ---
# You come upon a very unusual sight; a group of programs here appear to be dancing.

# There are sixteen programs in total, named a through p. They start by standing in a line: a stands in position 0, b stands in position 1, and so on until p, which stands in position 15.

# The programs' dance consists of a sequence of dance moves:

# Spin, written sX, makes X programs move from the end to the front, but maintain their order otherwise. (For example, s3 on abcde produces cdeab).
# Exchange, written xA/B, makes the programs at positions A and B swap places.
# Partner, written pA/B, makes the programs named A and B swap places.
# For example, with only five programs standing in a line (abcde), they could do the following dance:

# s1, a spin of size 1: eabcd.
# x3/4, swapping the last two programs: eabdc.
# pe/b, swapping programs e and b: baedc.
# After finishing their dance, the programs end up in order baedc.

# You watch the dance for a while and record their dance moves (your puzzle input). In what order are the programs standing after their dance?

# --- Part Two ---
# Now that you're starting to get a feel for the dance moves, you turn your attention to the dance as a whole.

# Keeping the positions they ended up in from their previous dance, the programs perform it again and again: including the first dance, a total of one billion (1000000000) times.

# In the example above, their second dance would begin with the order baedc, and use the same dance moves:

# s1, a spin of size 1: cbaed.
# x3/4, swapping the last two programs: cbade.
# pe/b, swapping programs e and b: ceadb.
# In what order are the programs standing after their billion dances?

def task1(lst, sequences):
    for seq in sequences:
        if seq[0] == "s":
            size = int(seq[1:])
            temp = lst[(len(lst) - size):]
            temp2 = lst[0:(len(lst) - size)]
            lst = temp + temp2
        elif seq[0] == "x":
            parts = seq[1:].split("/")
            temp = lst[int(parts[0])]
            lst[int(parts[0])] = lst[int(parts[1])]
            lst[int(parts[1])] = temp
        else:
            parts = seq[1:].split("/")
            index1 = lst.index(parts[0])
            index2 = lst.index(parts[1])
            temp = lst[index1]
            lst[index1] = lst[index2]
            lst[index2] = temp

    return lst

def task2(lst, sequences):
    seen = []
    for i in range(10000):
        lst = task1(lst, sequences)
        if lst in seen:
            print "prev found after iteration %d" % i
            break
        else:
            seen.append(lst)

    return lst

if __name__ == "__main__":
    lst = list("abcdefghijklmnop")
    sequences = open("input.txt").read().rstrip().split(",")

    # print "Task1"
    # print "The order of the program is %s." % "".join(task1(lst, sequences))
    
    print "\nTask2"
    lst = list("abcdefghijklmnop")
    print "The order of the program after one billion dances is %s." % "".join(task2(lst, sequences))

# --- Day 11: Hex Ed ---

# Crossing the bridge, you've barely reached the other side of the stream when a program comes up to you, clearly in distress. "It's my child process," she says, "he's gotten lost in an infinite grid!"

# Fortunately for her, you have plenty of experience with infinite grids.

# Unfortunately for you, it's a hex grid.

# The hexagons ("hexes") in this grid are aligned such that adjacent hexes can be found to the north, northeast, southeast, south, southwest, and northwest:

  # \ n  /
# nw +--+ ne
  # /    \
# -+      +-
  # \    /
# sw +--+ se
  # / s  \
# You have the path the child process took. Starting where he started, you need to determine the fewest number of steps required to reach him. (A "step" means to move from the hex you are in to any adjacent hex.)

# For example:

# ne,ne,ne is 3 steps away.
# ne,ne,sw,sw is 0 steps away (back where you started).
# ne,ne,s,s is 2 steps away (se,se).
# se,sw,se,sw,sw is 3 steps away (s,s,sw).
# Your puzzle answer was 698.

# The first half of this puzzle is complete! It provides one gold star: *

# --- Part Two ---

# How many steps away is the furthest he ever got from his starting position?

# Store all calculated distances here for task 2
distances = []

# Use cube coordinates to calculate distance
# https://www.redblobgames.com/grids/hexagons/#coordinates
def solve(steps):
    x = 0
    y = 0
    z = 0
    
    # Calculate new coordinates based on step
    for step in steps:
        if step == "n":
            y += 1
            z -= 1
        elif step == "nw":
            y += 1
            x -= 1
        elif step == "ne":
            x += 1
            z -= 1
        elif step == "s":
            z += 1
            y -= 1
        elif step == "sw":
            z += 1
            x -= 1
        else:
            x += 1
            y -= 1

        # Store distance in list for task 2
        distances.append((abs(x) + abs(y) + abs(z)) / 2)

    return (abs(x) + abs(y) + abs(z)) / 2

if __name__ == "__main__":
    print "Test cases for task 1"
    print "Got: %d, expected: %d" % (solve(["ne", "ne", "ne"]), 3)
    print "Got: %d, expected: %d" % (solve(["se", "sw", "se", "sw", "sw"]), 3)
    print "Got: %d, expected: %d" % (solve(["ne", "ne", "sw", "sw"]), 0)
    print "Got: %d, expected: %d" % (solve(["ne", "ne", "s", "s"]), 2)
    print "Got: %d, expected: %d" % (solve(["se", "se", "se", "s", "s", "nw", "nw", "ne", "ne"]), 3)

    print "\nTask 1"
    steps = open("input.txt", "r").read().split(",")
    print "Shortest path is %d steps." % solve(steps)

    print "\nTask 2"
    maximum = max(distances)
    print "The furthest distance is %d steps away." % maximum

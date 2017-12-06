# --- Part Two ---

# Now, the jumps are even stranger: after each jump, if the offset was three or more, instead decrease it by 1. Otherwise, increase it by 1 as before.

# Using this rule with the above example, the process now takes 10 steps, and the offset values after finding the exit are left as 2 3 2 3 -1.

# How many steps does it now take to reach the exit?

# Load list of jump offsets
offsets = [int(line.rstrip()) for line in open('input.txt')]
# offsets = [0, 3, 0, 1, -3]

position = 0
steps = 0
# Start going through the offsets as long as they're valid
while True:
    steps += 1

    if position + offsets[position] >= 0 and position + offsets[position] < len(offsets):
        # Valid jump
        old_position = position
        position += offsets[position]
        
        if offsets[old_position] >= 3:
            offsets[old_position] -= 1
        else:
            offsets[old_position] += 1
    else:
        # Jump outside of maze, exit is reached
        break

print "The exit is reached in %d steps." % steps

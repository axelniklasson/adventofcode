# --- Part Two ---

# Out of curiosity, the debugger would also like to know the size of the loop: starting from a state that has already been seen, how many block redistribution cycles must be performed before that same state is seen again?

# In the example above, 2 4 1 2 is seen again after four cycles, and so the answer in that example would be 4.

# How many cycles are in the infinite loop that arises from the configuration in your puzzle input?

import operator

# Load list of block counts
block_counts = [int(count) for count in open('input.txt').readline().split()]
blocks = len(block_counts)

# List of all previous block counts
previous_states = [block_counts[:]]
# Number of cycles needed after matching value is found
cycles = 0
# Know when to break loop
state_found = False

while not state_found:
    cycles += 1

    # Find highest value and its index in list and then decrement to zero
    index, value = max(enumerate(block_counts), key=operator.itemgetter(1))
    block_counts[index] -= value
    
    # Redistribute count over list
    while (value > 0):
        index = (index + 1) % blocks
        block_counts[index] += 1
        value -= 1
    # Break loop if state has previously been found, otherwise add and loop again
    if block_counts[:] in previous_states:
        state_found = True
        cycles = len(previous_states) - previous_states.index(block_counts[:])
    else:
        previous_states.append(block_counts[:])

print "%d redistribution cycles are needeed." % cycles

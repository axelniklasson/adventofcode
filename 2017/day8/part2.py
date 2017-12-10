# --- Part Two ---

# To be safe, the CPU also needs to know the highest value held in any register during this process so that it can decide how much memory to allocate to these operations. For example, in the above instructions, the highest value ever held was 10 (in register c after the third instruction was evaluated).

# Load list of instructions 
instructions = [line.rstrip().split(" ") for line in open('input.txt')]
registers = {}
largest = 0

# Go through all instructions
for el in instructions:
    # Retrieve all values from current instruction
    register = el[0]
    operation = el[1]
    value = int(el[2])
    condition_register = el[4]
    condition = el[5]
    condition_value = int(el[6])

    # First check if condition holds
    valid = True
    # Write 0 to register if not referenced before
    if not condition_register in registers:
        registers[condition_register] = 0

    # Check all conditions
    if condition == "==":
        valid = registers[condition_register] == condition_value
    elif condition == "<":
        valid = registers[condition_register] < condition_value
    elif condition == ">":
        valid = registers[condition_register] > condition_value
    elif condition == "<=":
        valid = registers[condition_register] <= condition_value
    elif condition == ">=":
        valid = registers[condition_register] >= condition_value
    else:
        valid = registers[condition_register] != condition_value

    # If condition holds, perform operation and check with largest
    if valid:
        if register not in registers:
            registers[register] = 0

        if operation == "inc":
            registers[register] += value
        else:
            registers[register] -= value

        if registers[register] > largest:
            largest = registers[register]

print "The largest ever held value is %d." % largest

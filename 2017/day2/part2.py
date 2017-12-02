# --- Part Two ---

# "Great work; looks like we're on the right track after all. Here's a star for your effort." However, the program seems a little worried. Can programs be worried?

# "Based on what we're seeing, it looks like all the User wanted is some information about the evenly divisible values in the spreadsheet. Unfortunately, none of us are equipped for that kind of calculation - most of us specialize in bitwise operations."

# It sounds like the goal is to find the only two numbers in each row where one evenly divides the other - that is, where the result of the division operation is a whole number. They would like you to find those numbers on each line, divide them, and add up each line's result.

# For example, given the following spreadsheet:

# 5 9 2 8
# 9 4 7 3
# 3 8 6 5
# In the first row, the only two numbers that evenly divide are 8 and 2; the result of this division is 4.
# In the second row, the two numbers are 9 and 3; the result is 3.
# In the third row, the result is 2.
# In this example, the sum of the results would be 4 + 3 + 2 = 9.

# What is the sum of each row's result in your puzzle input?

# Load spreadsheet data from input file
f = open("input.txt", "r")
lines = f.readlines()

result = 0
# Go through each line of the spreadsheet
for line in lines:
    numbers = line.split()
    index = 0

    # Check each number with all other numbers in the line and find the pair
    # that is evenly divisible
    while index < len(numbers):
        first_number = int(numbers[index])
        index2 = 0
        while index2 < len(numbers) and index2 != index:
            second_number = int(numbers[index2])
            
            if first_number % second_number == 0:
                # Evenly divisible numbers found
                result += first_number / second_number
            if second_number % first_number == 0:
                # Evenly divisible numbers found
                result += second_number / first_number

            index2 += 1

        index += 1

print "The sum of each row's result is %d" % result

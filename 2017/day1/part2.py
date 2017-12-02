# You notice a progress bar that jumps to 50% completion. Apparently, the door isn't yet satisfied, but it did emit a star as encouragement. The instructions change:

# Now, instead of considering the next digit, it wants you to consider the digit halfway around the circular list. That is, if your list contains 10 items, only include a digit in your sum if the digit 10/2 = 5 steps forward matches it. Fortunately, your list has an even number of elements.

# For example:

# 1212 produces 6: the list contains 4 items, and all four digits match the digit 2 items ahead.
# 1221 produces 0, because every comparison is between a 1 and a 2.
# 123425 produces 4, because both 2s match each other, but no other digit has a match.
# 123123 produces 12.
# 12131415 produces 4.
# What is the solution to your new captcha?

# Load string of digits from file and strip of whitespace
f = open("input.txt", "r")
digits = f.read().strip()
length = len(digits)

result = 0
index = 0
# Traverse through only half of all digits
while index < length / 2:
    if digits[index] == digits[index + (length) / 2]:
        result += int(digits[index])
    index += 1

# Double result since array is even and all matches should count twice
result *= 2

print "The total sum is: %d" % result

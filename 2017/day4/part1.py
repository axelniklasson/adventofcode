# --- Day 4: High-Entropy Passphrases ---

# A new system policy has been put in place that requires all accounts to use a passphrase instead of simply a password. A passphrase consists of a series of words (lowercase letters) separated by spaces.

# To ensure security, a valid passphrase must contain no duplicate words.

# For example:

# aa bb cc dd ee is valid.
# aa bb cc dd aa is not valid - the word aa appears more than once.
# aa bb cc dd aaa is valid - aa and aaa count as different words.

# The system's full passphrase list is available as your puzzle input. How many passphrases are valid?

# Load passphrase list from input file
f = open("input.txt", "r")
lines = f.readlines()

count = 0
for line in lines:
    # Store all found words as keys to keep track
    found_words = {}

    # Load all words in the line and format the data
    words = line.split(" ")
    words[len(words) - 1] = words[len(words) - 1].strip() # Remove \n character

    valid = True
    # Go through all words and check for duplicates
    for word in words:
        if word in found_words:
            valid = False
            break
        else:
            found_words[word] = 1

    if valid:
        count += 1

print "There are %d valid passphrases." % count

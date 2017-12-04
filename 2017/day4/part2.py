# --- Part Two ---

# For added security, yet another system policy has been put in place. Now, a valid passphrase must contain no two words that are anagrams of each other - that is, a passphrase is invalid if any word's letters can be rearranged to form any other word in the passphrase.

# For example:

# abcde fghij is a valid passphrase.
# abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word.
# a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word.
# iiii oiii ooii oooi oooo is valid.
# oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word.
# Under this new system policy, how many passphrases are valid?

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

    i = 0
    valid = True
    # Go through all words and check for anagrams
    while i < len(words):
        j = 0
        while j < len(words) and j != i:
            # Use magic function sorted() to sort characters of strings into array
            # If they are equal, they are anagrams of each other
            if sorted(words[i]) == sorted(words[j]):
                valid = False
                break
            j += 1
        i += 1

    if valid:
        count += 1

print "There are %d valid passphrases." % count

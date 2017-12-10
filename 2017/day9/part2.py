# --- Part Two ---

# Now, you're ready to remove the garbage.

# To prove you've removed it, you need to count all of the characters within the garbage. The leading and trailing < and > don't count, nor do any canceled characters or the ! doing the canceling.

# <>, 0 characters.
# <random characters>, 17 characters.
# <<<<>, 3 characters.
# <{!>}>, 2 characters.
# <!!>, 0 characters.
# <!!!>>, 0 characters.
# <{o"i!a,<{i<a>, 10 characters.
#
# How many non-canceled characters are within the garbage in your puzzle input?

string = open('input.txt').read().rstrip()
garbage = False
ignore = False
removed = 0

# Go through all characters
for char in string:
    # Count removed characters that aren't canceled
    if garbage and not ignore:
        removed += 1

    # Handle different characters and remove trailing ones from removed count
    if not ignore:
        if char == "!":
            ignore = True
            removed -= 1
        elif char == "<":
            garbage = True
        elif char == ">":
            garbage = False
            removed -= 1
    else:
        ignore = False

print "%d non-canceled characters are within the garbage." % removed

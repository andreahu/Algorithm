import nltk
import sys

TERMINALS = """
Adj -> "country" | "dreadful" | "enigmatical" | "little" | "moist" | "red"
Adv -> "down" | "here" | "never"
Conj -> "and" | "until"
Det -> "a" | "an" | "his" | "my" | "the"
N -> "armchair" | "companion" | "day" | "door" | "hand" | "he" | "himself"
N -> "holmes" | "home" | "i" | "mess" | "paint" | "palm" | "pipe" | "she"
N -> "smile" | "thursday" | "walk" | "we" | "word"
P -> "at" | "before" | "in" | "of" | "on" | "to"
V -> "arrived" | "came" | "chuckled" | "had" | "lit" | "said" | "sat"
V -> "smiled" | "tell" | "were"
"""

NONTERMINALS = """
S -> NP VP | S Conj S

AP -> Adj | Adj AP | AP Conj AP | Adv AP | AP Adv
NP -> N | Det NP | AP NP | N PP | NP Conj NP 
PP -> P NP
VP -> V | V NP | V NP PP | VP Conj VP | Adv VP | VP Adv
"""

grammar = nltk.CFG.fromstring(NONTERMINALS + TERMINALS)
parser = nltk.ChartParser(grammar)


def main():

    # If filename specified, read sentence from file
    if len(sys.argv) == 2:
        with open(sys.argv[1]) as f:
            s = f.read()

    # Otherwise, get sentence as input
    else:
        s = input("Sentence: ")

    # Convert input into list of words
    s = preprocess(s)

    # Attempt to parse sentence
    try:
        trees = list(parser.parse(s))
    except ValueError as e:
        print(e)
        return
    if not trees:
        print("Could not parse sentence.")
        return

    # Print each tree with noun phrase chunks
    for tree in trees:
        tree.pretty_print()

        print("Noun Phrase Chunks")
        for np in np_chunk(tree):
            print(" ".join(np.flatten()))


def preprocess(sentence):
    """
    Convert `sentence` to a list of its words.
    Pre-process sentence by converting all characters to lowercase
    and removing any word that does not contain at least one alphabetic
    character.
    """

    wordList = []

    for token in nltk.word_tokenize(sentence):
        token = token.lower()
        if token.islower(): #check if token contains letters
            wordList.append(token)

    return wordList
    


def np_chunk(tree):
    """
    Return a list of all noun phrase chunks in the sentence tree.
    A noun phrase chunk is defined as any subtree of the sentence
    whose label is "NP" that does not itself contain any other
    noun phrases as subtrees.
    """
    # used this class https://www.nltk.org/_modules/nltk/tree.html
    # TA mentioned .label(),  leaves() could be useful, 
    # could use subtrees() to see if there's any NP under the current NP
    # TA says no need to use recursion since our example goes to 2 levels the deepest

    npList = []


    # AH: not sure where to use the leaves() function

    for s in tree.subtrees():
        if s.label() == "NP":
            for ss in s.subtrees():
                subNPfound = 0
                if ss.label() == "NP":
                    npList.append(ss)
                    subNPfound = 1
                if subNPfound == 0:
                    npList.append(s)

    return npList
    


if __name__ == "__main__":
    main()

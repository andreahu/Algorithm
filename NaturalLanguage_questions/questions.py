import nltk
import sys
import os
import string
import math

FILE_MATCHES = 1
SENTENCE_MATCHES = 1


def main():

    # Check command-line arguments
    if len(sys.argv) != 2:
        sys.exit("Usage: python questions.py corpus")

    # Calculate IDF values across files
    files = load_files(sys.argv[1])
    file_words = {
        filename: tokenize(files[filename])
        for filename in files
    }
    file_idfs = compute_idfs(file_words)

    # Prompt user for query
    query = set(tokenize(input("Query: ")))

    # Determine top file matches according to TF-IDF
    filenames = top_files(query, file_words, file_idfs, n=FILE_MATCHES)

    print(filenames)

    # Extract sentences from top files
    sentences = dict()
    for filename in filenames:
        for passage in files[filename].split("\n"):
            for sentence in nltk.sent_tokenize(passage):
                tokens = tokenize(sentence)
                if tokens:
                    sentences[sentence] = tokens

    # Compute IDF values across sentences
    idfs = compute_idfs(sentences)

    # Determine top sentence matches
    matches = top_sentences(query, sentences, idfs, n=SENTENCE_MATCHES)
    for match in matches:
        print(match)


"""
def main():

    # filedict = load_files("corpus")
    # theKeys = filedict.keys()

    # for k in theKeys:
    #     print(k)

    # print(filedict["probability.txt"])


    # thelist = tokenize(" this is so cool()*+, - Andrea own an be some OKK")
    # for word in thelist:
    #     print(word)

"""

def load_files(directory):
    """
    Given a directory name, return a dictionary mapping the filename of each
    `.txt` file inside that directory to the file's contents as a string.
    """
    fileDict = dict()

    os.chdir(directory)
    for root, dirs, files in os.walk(".", topdown = False):
        for f in files:
            path = os.path.join(root, f)
            if path.endswith(".txt"):
                with open(f"{path}", 'r') as file: 
                    data = file.read().replace('\n', '')
                    fileDict[f] = data

    return fileDict


def tokenize(document):
    """
    Given a document (represented as a string), return a list of all of the
    words in that document, in order.

    Process document by coverting all words to lowercase, and removing any
    punctuation or English stopwords.
    """
    
    punctuations = set(string.punctuation)
    stop_words = set(nltk.corpus.stopwords.words("english"))
    wordList = []

    for token in nltk.word_tokenize(document):
        if token not in punctuations and token not in stop_words:
            token = token.lower()
            wordList.append(token)

    return wordList



def compute_idfs(documents):
    """
    Given a dictionary of `documents` that maps names of documents to a list
    of words, return a dictionary that maps words to their IDF values.

    Any word that appears in at least one of the documents should be in the
    resulting dictionary.
    """
    #idf (Inverse Document Frequency) = log(TotalDocuments / NumDocumentsContaining(word))

    # Get all words in documents
    words = set()
    for filename in documents:
        words.update(documents[filename])

    # Calculate IDFs (inverse document frequencies)
    idfs = dict()
    for word in words:
        f = sum(word in documents[filename] for filename in documents)
        if f == 0: #denominator is 0
            idf = 0
        else:
            idf = math.log(len(documents) / f)
        idfs[word] = idf

    return idfs


def top_files(query, files, idfs, n):
    """
    Given a `query` (a set of words), `files` (a dictionary mapping names of
    files to a list of their words), and `idfs` (a dictionary mapping words
    to their IDF values), return a list of the filenames of the the `n` top
    files that match the query, ranked according to tf-idf.
    """
    #tf-idf: term frequency of each word * the idf 
    #use sorted function with key https://www.geeksforgeeks.org/sorted-function-python/
    #user .count()


    # Calculate TF-IDFs
    tfidfs = dict()
    for filename in files:
        tfidfs[filename] = 0
        for word in files[filename]:
            if word in query:
                tf = files[filename][word]
                tfidf = tf * idfs[word]
                tfidfs[filename] = tfidfs[filename] + tfidf

    # Sort and get top n files
    sort_files = sorted(tfidfs.items(), key=lambda x: x[1], reverse=True)

    fileList = []
    count = 0
    for filename in sort_files:
        fileList.append(filename)
        count = count + 1
        if count == n:
            return fileList



def top_sentences(query, sentences, idfs, n):
    """
    Given a `query` (a set of words), `sentences` (a dictionary mapping
    sentences to a list of their words), and `idfs` (a dictionary mapping words
    to their IDF values), return a list of the `n` top sentences that match
    the query, ranked according to idf. If there are ties, preference should
    be given to sentences that have a higher query term density.
    """
    # Calculate sum_IDFs for each sentence
    sentenceIdfs = dict()
    for s in sentences:
        sentenceIdfs[s] = 0
        for word in sentences[s]:
            if word in query:
                idf = idfs[word]
                sentenceIdfs[s] = sentenceIdfs[s] + tfidf

    # Sort and get top n sentences
    sort_sentences = sorted(sentenceIdfs.items(), key=lambda x: x[1], reverse=True)

    sentenceList = []
    count = 0
    for s in sort_sentences:
        sentenceList.append(s)
        count = count + 1
        if count == n:
            return sentenceList
    
    


if __name__ == "__main__":
    main()

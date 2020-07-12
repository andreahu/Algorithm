import os
import random
import re
import sys

import numpy as np
# from numpy.random import choice


DAMPING = 0.85
SAMPLES = 10000 
# SAMPLES = 10 #ah: for testing purpose, change it to a smaller number such as 10

def main():
    if len(sys.argv) != 2:
        sys.exit("Usage: python pagerank.py corpus")
    corpus = crawl(sys.argv[1])
    ranks = sample_pagerank(corpus, DAMPING, SAMPLES)
    print(f"PageRank Results from Sampling (n = {SAMPLES})")
    for page in sorted(ranks):
        print(f"  {page}: {ranks[page]:.4f}")
    ranks = iterate_pagerank(corpus, DAMPING)
    print(f"PageRank Results from Iteration")
    for page in sorted(ranks):
        print(f"  {page}: {ranks[page]:.4f}")


def crawl(directory):
    """
    Parse a directory of HTML pages and check for links to other pages.
    Return a dictionary where each key is a page, and values are
    a list of all other pages in the corpus that are linked to by the page.
    """
    pages = dict()

    # Extract all links from HTML files
    for filename in os.listdir(directory):
        if not filename.endswith(".html"):
            continue
        with open(os.path.join(directory, filename)) as f:
            contents = f.read()
            links = re.findall(r"<a\s+(?:[^>]*?)href=\"([^\"]*)\"", contents)
            pages[filename] = set(links) - {filename}

    # Only include links to other pages in the corpus
    for filename in pages:
        pages[filename] = set(
            link for link in pages[filename]
            if link in pages
        )

    return pages


def transition_model(corpus, page, damping_factor):
    """
    Return a probability distribution over which page to visit next,
    given a current page.

    With probability `damping_factor`, choose a link at random
    linked to by `page`. With probability `1 - damping_factor`, choose
    a link at random chosen from all pages in the corpus.
    """

    pageSet = add_pages_to_set(corpus)
    pageCount = len(pageSet)
    defaultProb = (1 - damping_factor) / pageCount
    pageProbDic = add_pages_to_dic(corpus, defaultProb)

    #start with the page 
    linkedPageSet = corpus[page]
    if len(linkedPageSet) == 0: #If page has no outgoing links
        for p in pageProbDic:
            pageProbDic[p] = 1 / pageCount
        return pageProbDic
    else:
        for linkedPage in linkedPageSet:
            pageProbDic[linkedPage] = (1 - damping_factor) / pageCount + damping_factor / len(linkedPageSet)
    return pageProbDic
        

def add_pages_to_dic(corpus, defaultProb): #AH added this method
    """return a dictionary with one key for each page in the corpus """
    pageDict = dict()
    for page in corpus:
        if page not in pageDict:
            pageDict[page] = defaultProb
        linkSet = corpus.get(page)
        for linkedPage in linkSet:
            if linkedPage not in pageDict:
                pageDict[linkedPage] = defaultProb
    return pageDict


def add_pages_to_set(corpus): #AH added this method
    """return a set with all pages from the corpus. """
    pageSet = set()
    for page in corpus:
        if page not in pageSet:
            pageSet.add(page)
        linkSet = corpus.get(page)
        for linkedPage in linkSet:
            if linkedPage not in pageSet:
                pageSet.add(linkedPage)
    return pageSet





def sample_pagerank(corpus, damping_factor, n):
    """
    Return PageRank values for each page by sampling `n` pages
    according to transition model, starting with a page at random.

    Return a dictionary where keys are page names, and values are
    their estimated PageRank value (a value between 0 and 1). All
    PageRank values should sum to 1.
    """

    pageVisitCountDict = dict()
    pageSet = add_pages_to_set(corpus)
    for page in pageSet:
        pageVisitCountDict[page] = 0

    startingPage = random.choice(list(pageSet))

    for i in range(n): 
        pageVisitCountDict[startingPage] = pageVisitCountDict[startingPage] + 1
        nextpageProbDict = transition_model(corpus, startingPage, damping_factor)
        nextPageArray = []
        pageProbArray = []
        for p in nextpageProbDict:
            nextPageArray.append(p)
            pageProbArray.append(nextpageProbDict[p])
        rng = np.random.default_rng()
        startingPage = rng.choice(nextPageArray, 1, p=pageProbArray)[0]

    pageRankDict = dict()
    for page in pageVisitCountDict:
        pageRankDict[page] = pageVisitCountDict[page] / n

    return pageRankDict
                

def iterate_pagerank(corpus, damping_factor):
    """
    Return PageRank values for each page by iteratively updating
    PageRank values until convergence.

    Return a dictionary where keys are page names, and values are
    their estimated PageRank value (a value between 0 and 1). All
    PageRank values should sum to 1.
    """

    pageSet = add_pages_to_set(corpus)
    pageCount = len(pageSet)
    prob = 1/pageCount
    pageRankDict = add_pages_to_dic(corpus, prob)
    
    keep_calculate = True
    while keep_calculate:
        keep_calculate = updateRank(pageSet, pageRankDict, corpus, damping_factor, pageCount)
    
    return pageRankDict


def updateRank(pageSet, pageRankDict, corpus, damping_factor, pageCount):
    keep_calculate = False
    for page in pageSet:
        newRank = (1 - damping_factor) / pageCount
        for p in corpus:
            numLinks = len(corpus[p])
            pRank = pageRankDict[p]
            if numLinks == 0:
                newRank = newRank + damping_factor * pRank / pageCount
            else:
                if page in corpus[p]:
                    newRank = newRank + damping_factor * pRank / numLinks
        if abs((newRank - pageRankDict[page])/ pageRankDict[page]) > 0.001:
            keep_calculate = True
        pageRankDict[page] = newRank

    return keep_calculate


if __name__ == "__main__":
    main()

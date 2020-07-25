import csv
import sys

from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier

TEST_SIZE = 0.4


def main():

    # Check command-line arguments
    if len(sys.argv) != 2:
        sys.exit("Usage: python shopping.py data")

    # Load data from spreadsheet and split into train and test sets
    evidence, labels = load_data(sys.argv[1])
    X_train, X_test, y_train, y_test = train_test_split(
        evidence, labels, test_size=TEST_SIZE
    )

    # Train model and make predictions
    model = train_model(X_train, y_train)
    predictions = model.predict(X_test)
    sensitivity, specificity = evaluate(y_test, predictions)

    # Print results
    print(f"Correct: {(y_test == predictions).sum()}")
    print(f"Incorrect: {(y_test != predictions).sum()}")
    print(f"True Positive Rate: {100 * sensitivity:.2f}%")
    print(f"True Negative Rate: {100 * specificity:.2f}%")


def load_data(filename):
    """
    Load shopping data from a CSV file `filename` and convert into a list of
    evidence lists and a list of labels. Return a tuple (evidence, labels).

    evidence should be a list of lists, where each list contains the
    following values, in order:
        - Administrative, an integer
        - Administrative_Duration, a floating point number
        - Informational, an integer
        - Informational_Duration, a floating point number
        - ProductRelated, an integer
        - ProductRelated_Duration, a floating point number
        - BounceRates, a floating point number
        - ExitRates, a floating point number
        - PageValues, a floating point number
        - SpecialDay, a floating point number
        - Month, an index from 0 (January) to 11 (December)
        - OperatingSystems, an integer
        - Browser, an integer
        - Region, an integer
        - TrafficType, an integer
        - VisitorType, an integer 0 (not returning) or 1 (returning)
        - Weekend, an integer 0 (if false) or 1 (if true)

    labels should be the corresponding list of labels, where each label
    is 1 if Revenue is true, and 0 otherwise.
    """

    evidence = []
    labels = []

    with open(sys.argv[1]) as file:
        reader = csv.DictReader(file) #not can't run, try reader instead of dictreader?
        converterDic = dict()
        converterDic["Jan"] = 0
        converterDic["Feb"] = 1
        converterDic["Mar"] = 2
        converterDic["Apr"] = 3
        converterDic["May"] = 4
        converterDic["June"] = 5
        converterDic["Jul"] = 6
        converterDic["Aug"] = 7
        converterDic["Sep"] = 8
        converterDic["Oct"] = 9
        converterDic["Nov"] = 10
        converterDic["Dec"] = 11
        converterDic["Returning_Visitor"] = 1
        converterDic["Other"] = 0
        converterDic["New_Visitor"] = 0
        converterDic["TRUE"] = 1
        converterDic["FALSE"] = 0
        for row in reader:
            # evidenceThisRow = []
            # evidenceThisRow.append(int(row[0]))
            # evidenceThisRow.append(float(row[1]))
            # evidenceThisRow.append(int(row[2]))
            # evidenceThisRow.append(float(row[3]))
            # evidenceThisRow.append(int(row[4]))
            # evidenceThisRow.append(float(row[5]))
            # evidenceThisRow.append(float(row[6]))
            # evidenceThisRow.append(float(row[7]))
            # evidenceThisRow.append(float(row[8]))
            # evidenceThisRow.append(float(row[9]))
            # evidenceThisRow.append(converterDic[row[10]])
            # evidenceThisRow.append(int(row[11]))
            # evidenceThisRow.append(int(row[12]))
            # evidenceThisRow.append(int(row[13]))
            # evidenceThisRow.append(int(row[14]))
            # evidenceThisRow.append(converterDic[row[15]])
            # evidenceThisRow.append(converterDic[row[16]])

            evidenceThisRow = []
            evidenceThisRow.append(int(row['Administrative']))
            evidenceThisRow.append(float(row['Administrative_Duration']))
            evidenceThisRow.append(int(row['Informational']))
            evidenceThisRow.append(float(row['Informational_Duration']))
            evidenceThisRow.append(int(row['ProductRelated']))
            evidenceThisRow.append(float(row['ProductRelated_Duration']))
            evidenceThisRow.append(float(row['BounceRates']))
            evidenceThisRow.append(float(row['ExitRates']))
            evidenceThisRow.append(float(row['PageValues']))
            evidenceThisRow.append(float(row['SpecialDay']))
            evidenceThisRow.append(converterDic[row['Month']])
            evidenceThisRow.append(int(row['OperatingSystems']))
            evidenceThisRow.append(int(row['Browser']))
            evidenceThisRow.append(int(row['Region']))
            evidenceThisRow.append(int(row['TrafficType']))
            evidenceThisRow.append(converterDic[row['VisitorType']])
            evidenceThisRow.append(converterDic[row['Weekend']])

            evidence.append(evidenceThisRow)
            labels.append(converterDic[row['Revenue']])

    return (evidence,labels)


def train_model(evidence, labels):
    """
    Given a list of evidence lists and a list of labels, return a
    fitted k-nearest neighbor model (k=1) trained on the data.
    """

    neigh = KNeighborsClassifier(n_neighbors=1)

    return neigh.fit(evidence, labels)

def evaluate(labels, predictions):
    """
    Given a list of actual labels and a list of predicted labels,
    return a tuple (sensitivity, specificty).

    Assume each label is either a 1 (positive) or 0 (negative).

    `sensitivity` should be a floating-point value from 0 to 1
    representing the "true positive rate": the proportion of
    actual positive labels that were accurately identified.

    `specificity` should be a floating-point value from 0 to 1
    representing the "true negative rate": the proportion of
    actual negative labels that were accurately identified.
    """

    count = len(labels)
    realTrue = 0
    predictTrue = 0
    realFalse = 0
    predictFalse = 0
    for x in range(count):
        if labels[x] == 1:
            realTrue += 1
            if predictions[x] == 1:
                predictTrue += 1
        if labels[x] == 0:
            realFalse += 1
            if predictions[x] == 0:
                predictFalse += 1
    
    sensitivity = float(predictTrue / realTrue)
    specificity = float(predictFalse / realFalse)

    return (sensitivity, specificity)



    






if __name__ == "__main__":
    main()

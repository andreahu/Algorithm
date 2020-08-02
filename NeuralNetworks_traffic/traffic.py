import cv2
import numpy as np
import os
import sys
import tensorflow as tf

from sklearn.model_selection import train_test_split

EPOCHS = 10
IMG_WIDTH = 30
IMG_HEIGHT = 30
NUM_CATEGORIES = 43
TEST_SIZE = 0.4

"""
def main():

    # Check command-line arguments
    if len(sys.argv) not in [2, 3]:
        sys.exit("Usage: python traffic.py data_directory [model.h5]")

    # Get image arrays and labels for all image files
    images, labels = load_data(sys.argv[1])

    # Split data into training and testing sets
    labels = tf.keras.utils.to_categorical(labels)
    x_train, x_test, y_train, y_test = train_test_split(
        np.array(images), np.array(labels), test_size=TEST_SIZE
    )

    # Get a compiled neural network
    model = get_model()

    # Fit model on training data
    model.fit(x_train, y_train, epochs=EPOCHS)

    # Evaluate neural network performance
    model.evaluate(x_test,  y_test, verbose=2)

    # Save model to file
    if len(sys.argv) == 3:
        filename = sys.argv[2]
        model.save(filename)
        print(f"Model saved to {filename}.")

"""

#ah test main()
def main():

    im = cv2.imread('/Users/AndreaHu/Documents/Github/Algorithm/NeuralNetworks_traffic/gtsrb/39/00005_00012.ppm')
    print(type(im))
    # <class 'numpy.ndarray'>
    print(im.shape)
    # (40, 40, 3)
    print(im.dtype)
    # uint8

"""
    os.chdir("/Users/AndreaHu/Documents/Github/Algorithm/NeuralNetworks_traffic/gtsrb")
    for root, dirs, files in os.walk(".", topdown = False):
        for name in files:
            print(os.path.join(root, name))
        # for name in dirs:
        #     print(os.path.join(root, name))
"""


 




def load_data(data_dir):
    """
    Load image data from directory `data_dir`.

    Assume `data_dir` has one directory named after each category, numbered
    0 through NUM_CATEGORIES - 1. Inside each category directory will be some
    number of image files.

    Return tuple `(images, labels)`. `images` should be a list of all
    of the images in the data directory, where each image is formatted as a
    numpy ndarray with dimensions IMG_WIDTH x IMG_HEIGHT x 3. `labels` should
    be a list of integer labels, representing the categories for each of the
    corresponding `images`.
    """
    #os.walk() https://www.tutorialspoint.com/python3/os_walk.htm
    #cv2.imread() https://www.geeksforgeeks.org/python-opencv-cv2-imread-method/#:~:text=OpenCV%2DPython%20is%20a%20library,method%20returns%20an%20empty%20matrix.
    #check links.txt
    images = []
    labels = []

    os.chdir("/Users/AndreaHu/Documents/Github/Algorithm/NeuralNetworks_traffic/gtsrb")
    for root, dirs, files in os.walk(".", topdown = False):
        for dirName in dirs:
            dirPath = os.path.join(root, dirName)
            for name in files:
                path = os.path.join(root, name)
                img = cv2.imread(path) #img is a numpy.ndarray
                img.resize(IMG_WIDTH, IMG_HEIGHT)
                
                images.append(imagies)
                labels.append(dirPath)




def get_model():
    """
    Returns a compiled convolutional neural network model. Assume that the
    `input_shape` of the first layer is `(IMG_WIDTH, IMG_HEIGHT, 3)`.
    The output layer should have `NUM_CATEGORIES` units, one for each category.
    """
    # check handwriting.py
    # https://www.tensorflow.org/tutorials/images/classification copy code for "Creating a new network with Dropouts"

    



if __name__ == "__main__":
    main()

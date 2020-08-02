import cv2
import numpy as np
import os
import sys
import tensorflow as tf
from tensorflow.keras import layers

from sklearn.model_selection import train_test_split

EPOCHS = 10
IMG_WIDTH = 30
IMG_HEIGHT = 30
NUM_CATEGORIES = 43
TEST_SIZE = 0.4


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
    #useful links in links.txt
    images = []
    labels = []
    dim = (IMG_WIDTH, IMG_HEIGHT)

    os.chdir("gtsrb")
    for root, dirs, files in os.walk(".", topdown = False):
        for f in files:
            path = os.path.join(root, f)
            if path.endswith(".ppm"):
                img = cv2.imread(path) #img is a numpy.ndarray
                img = cv2.resize(img, dim, interpolation = cv2.INTER_AREA)

                images.append(img)

                label = root.split(os.sep)[1]
                labels.append(label)
    
    return (images, labels)




def get_model():
    """
    Returns a compiled convolutional neural network model. Assume that the
    `input_shape` of the first layer is `(IMG_WIDTH, IMG_HEIGHT, 3)`.
    The output layer should have `NUM_CATEGORIES` units, one for each category.
    """

    # https://www.tensorflow.org/tutorials/images/classification 


    model = tf.keras.models.Sequential([
        layers.experimental.preprocessing.Rescaling(1./255),
        layers.Conv2D(32, (3, 3), activation="relu", input_shape=(IMG_WIDTH, IMG_HEIGHT, 3)),
        layers.MaxPooling2D(pool_size=(2, 2)),
        layers.Flatten(),
        layers.Dense(128, activation="relu"),
        layers.Dropout(0.2),
        layers.Dense(NUM_CATEGORIES, activation='softmax'),
    ])

    model.compile(
        optimizer="adam",
        loss="categorical_crossentropy",
        metrics=["accuracy"]
    )

    return model



if __name__ == "__main__":
    main()

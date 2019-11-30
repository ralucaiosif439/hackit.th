# import the necessary packages
from imutils.video import VideoStream
import argparse
import datetime
import imutils
import time
import cv2
import np
import numpy

# construct the argument parser and parse the arguments
ap = argparse.ArgumentParser()
ap.add_argument("-v", "--video", help="path to the video file")
ap.add_argument("-a", "--min-area", type=int, default=500, help="minimum area size")
args = vars(ap.parse_args())
if args.get("video", None) is None:
    cap = VideoStream(src=0).start()
    time.sleep(2.0)

# otherwise, we are reading from a video file
else:
    cap = cv2.VideoCapture(args["video"])

# fgbg = cv2.createBackgroundSubtractorMOG2()
kernel = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (3, 3))
subtractorGmg = cv2.bgsegm.createBackgroundSubtractorGMG()

frame = cap.read()
previous_frame = frame
previous_frame2 = cv2.cvtColor(previous_frame, cv2.COLOR_BGR2GRAY)

def print_frame_diff(curr_frame, prev_frame):
    current_frame_gray = cv2.cvtColor(curr_frame, cv2.COLOR_BGR2GRAY)
    previous_frame_gray = cv2.cvtColor(prev_frame, cv2.COLOR_BGR2GRAY)
    frame_diff = cv2.absdiff(current_frame_gray, previous_frame_gray)
    cv2.imshow('Computed diff', frame_diff)
    gray = cv2.cvtColor(curr_frame, cv2.COLOR_BGR2GRAY)
    # print(frame_diff.shape[0] + frame_diff.shape[1] + frame_diff.shape[2])
    print(frame_diff)
    # print(np.count_nonzero(frame_diff == 255))


while (1):
    print_frame_diff(frame, previous_frame)
    previous_frame = frame.copy()
    frame = cap.read()

    cv2.imshow('Live', frame)

    fgMaskGmg = subtractorGmg.apply(frame)
    fgmaskMorphology = cv2.morphologyEx(fgMaskGmg, cv2.MORPH_OPEN, kernel)
    # prior = fgmaskMorphology.getBackgroundPrior(frame)

    cv2.imshow('GMG Morph', fgmaskMorphology)
    cv2.putText(frame, "ASD", (1, 1), cv2.FONT_HERSHEY_SIMPLEX, 2, 255)

    k = cv2.waitKey(30) & 0xff
    if k == 27:
        break

cap.release()
cv2.destroyAllWindows()

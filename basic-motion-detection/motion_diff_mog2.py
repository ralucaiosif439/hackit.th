# import the necessary packages
from imutils.video import VideoStream
import argparse
import datetime
import imutils
import time
import cv2 as cv

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
	cap = cv.VideoCapture(args["video"])

# cap = cv.VideoCapture('vtest.avi')
# fgbg = cv.bgsegm.createBackgroundSubtractorMOG()
fgbg = cv.createBackgroundSubtractorMOG2()
while(1):
	frame = cap.read()
	fgmask = fgbg.apply(frame)
	cv.imshow('frame',fgmask)
	k = cv.waitKey(30) & 0xff
	if k == 27:
		break
cap.release()
cv.destroyAllWindows()

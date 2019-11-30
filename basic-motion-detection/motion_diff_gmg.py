# import the necessary packages
from imutils.video import VideoStream
import argparse
import datetime
import imutils
import time
import cv2

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
subtractorMog2 = cv2.createBackgroundSubtractorMOG2(history=1000, varThreshold=25, detectShadows=False)
kernel = cv2.getStructuringElement(cv2.MORPH_ELLIPSE,(3,3))
subtractorGmg = cv2.bgsegm.createBackgroundSubtractorGMG()
subtractorGsoc = cv2.bgsegm.createBackgroundSubtractorGSOC()

while(1):
	frame = cap.read()

	cv2.imshow('Live', frame)

	fgMaskMog2 = subtractorMog2.apply(frame)
	# cv2.imshow('MOG2', fgMaskMog2)

	fgMaskGmg = subtractorGmg.apply(frame)
	# cv2.imshow('GMG', fgMaskGmg)
	fgmaskMorphology = cv2.morphologyEx(fgMaskGmg, cv2.MORPH_OPEN, kernel)
	cv2.imshow('GMG Morph', fgmaskMorphology)

	# fgMaskGsoc = subtractorGsoc.apply(frame)
	# subtractorGsoc.thresh
	# cv2.imshow('GSOC', fgMaskGsoc)

	k = cv2.waitKey(30) & 0xff
	if k == 27:
		break


cap.release()
cv2.destroyAllWindows()
package eu.accesa.hackit.th.service;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import org.springframework.stereotype.Service;

@Service
public class VideoProcesser {

    private VideoCapture videoCapture;

    public VideoProcesser() {
//        System.setProperty("java.library.path", "C:\\Users\\iosif\\Desktop\\hackit\\.idea\\libraries");
//        System.out.println(System.getProperty("java.library.path"));
//        System.loadLibrary("opencv_java320");
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public void getVideoCapture() {

//        String input = "C:\\Users\\iosif\\Desktop\\training\\sample.mp4";
//        videoCapture = new VideoCapture(input);
//        String output = "C:\\Users\\iosif\\Desktop\\training\\Output";
//        videoCapture.open(input);
//        int videoLength = (int) videoCapture.get(Videoio.CAP_PROP_FRAME_COUNT);
//        int framesPerSecond = (int) videoCapture.get(Videoio.CAP_PROP_FPS);
//        int frameNumber = (int) videoCapture.get(Videoio.CAP_PROP_POS_FRAMES);
//        Mat frame = new Mat();
//
//        if (videoCapture.isOpened()) {
//            System.out.println("video opened");
//            System.out.println("Number of frames: " + videoLength);
//            System.out.println(framesPerSecond + " FPS");
//            System.out.println("Converting video....");
//
//            videoCapture.read(frame);
//            while (frameNumber <= videoLength) {
//                Imgcodecs.imwrite(output + "/" + frameNumber + ".jpg", frame);
//                frameNumber++;
//            }
//            videoCapture.release();
//            System.out.println(videoLength + " frames extracted");
//        } else {
//            System.out.println("Fail");
//        }
    }
}

package eu.accesa.hackit.th.service;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class Video2ImageService {

    public ResponseEntity<String> convertMovieToJPG(String mp4Path, String imagePath, String imgType, int frameJump) throws Exception, IOException {
        Java2DFrameConverter converter = new Java2DFrameConverter();
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(mp4Path);
        frameGrabber.start();
        Frame frame;
        double frameRate = frameGrabber.getFrameRate();
        int imgNum = 0;
        System.out.println("Video has " + frameGrabber.getLengthInFrames() + " frames and has frame rate of " + frameRate);
        try {
            for (int ii = 1; ii <= frameGrabber.getLengthInFrames(); ii++) {
                imgNum++;
                frameGrabber.setFrameNumber(ii);
                frame = frameGrabber.grab();
                BufferedImage bi = converter.convert(frame);
                String path = imagePath + File.separator + imgNum + ".jpg";
                ImageIO.write(bi, imgType, new File(path));
                ii += frameJump;
            }
            frameGrabber.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Split complete! Output location:" + imagePath);
    }

}

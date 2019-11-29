package eu.accesa.hackit.th.controller;

import eu.accesa.hackit.th.service.VideoProcesser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final VideoProcesser videoProcesser;

    public TestController(VideoProcesser videoProcesser) {
        this.videoProcesser = videoProcesser;
    }

    @GetMapping("/test")
    public void testSomething() {
        videoProcesser.getVideoCapture();
    }

}

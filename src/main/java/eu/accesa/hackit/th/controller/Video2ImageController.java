package eu.accesa.hackit.th.controller;

import eu.accesa.hackit.th.service.Video2ImageService;
import eu.accesa.hackit.th.model.dto.Vid2ImageSplitReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Video2ImageController {

    private final Video2ImageService video2ImageService;

    public Video2ImageController(Video2ImageService video2ImageService) {
        this.video2ImageService = video2ImageService;
    }

    @PostMapping("/vid2image")
    public ResponseEntity<String> splitVideo2Image(@RequestBody Vid2ImageSplitReqDto reqDto) throws Exception {
        return video2ImageService.convertMovieToJPG(reqDto.getMp4Path(), reqDto.getImagePath(), reqDto.getImgType(), reqDto.getFrameJump());
    }
}

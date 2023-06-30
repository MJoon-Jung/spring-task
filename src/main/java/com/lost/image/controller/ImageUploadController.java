package com.lost.image.controller;

import com.lost.image.controller.response.ImageResponse;
import com.lost.image.domain.ImagePost;
import com.lost.image.service.ImageUploadService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @PostMapping("/api/posts/images")
    public ResponseEntity<List<ImageResponse>> handleFileUpload(
            @RequestParam("image") List<MultipartFile> imageFiles) {
        List<ImagePost> images = imageUploadService.store(imageFiles);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(images.stream().map(ImageResponse::from).toList());
    }
}
package com.ytdownloader.controller;

import com.ytdownloader.service.YouTubeDownloaderService;
import com.ytdownloader.entity.DownloadLink;
import com.ytdownloader.entity.VideoRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class YouTubeDownloaderController {

    private final YouTubeDownloaderService youTubeDownloaderService;

    public YouTubeDownloaderController(YouTubeDownloaderService youTubeDownloaderService) {
        this.youTubeDownloaderService = youTubeDownloaderService;
    }

    @PostMapping("/download")
    public ResponseEntity<List<DownloadLink>> downloadVideo(@RequestBody VideoRequest request) {
        return ResponseEntity.ok(youTubeDownloaderService.getDownloadLinks(request.getUrl()));
    }

    @GetMapping("/yhistory")
    public ResponseEntity<List<DownloadLink>> getDownloadHistory() {
        return ResponseEntity.ok(youTubeDownloaderService.getDownloadHistory());
    }
}

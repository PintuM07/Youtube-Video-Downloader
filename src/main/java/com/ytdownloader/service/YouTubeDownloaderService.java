package com.ytdownloader.service;

import com.ytdownloader.entity.DownloadLink;
import com.ytdownloader.repository.DownloadLinkRepository;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class YouTubeDownloaderService {

    private final DownloadLinkRepository downloadLinkRepository;

    public YouTubeDownloaderService(DownloadLinkRepository downloadLinkRepository) {
        this.downloadLinkRepository = downloadLinkRepository;
    }

    public List<DownloadLink> getDownloadLinks(String url) {
        List<DownloadLink> downloadLinks = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Python313\\Scripts\\yt-dlp.exe", url);

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("video") || line.contains("audio")) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length > 1) {
                        String formatId = parts[0];
                        String quality = parts[parts.length - 1];
                        String downloadUrl = url + "?format=" + formatId;
                        DownloadLink downloadLink = new DownloadLink(downloadUrl, quality);
                        downloadLinks.add(downloadLink);
                        downloadLinkRepository.save(downloadLink);
                    }
                }
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return downloadLinks;
    }

    public List<DownloadLink> getDownloadHistory() {
        return downloadLinkRepository.findAll();
    }
}

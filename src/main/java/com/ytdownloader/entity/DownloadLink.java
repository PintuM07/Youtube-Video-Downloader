package com.ytdownloader.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "download_links")
public class DownloadLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String quality;

    public DownloadLink() {}

    public DownloadLink(String url, String quality) {
        this.url = url;
        this.quality = quality;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getQuality() { return quality; }
    public void setQuality(String quality) { this.quality = quality; }
}

package com.ytdownloader.repository;

import com.ytdownloader.entity.DownloadLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadLinkRepository extends JpaRepository<DownloadLink, Long> {

}

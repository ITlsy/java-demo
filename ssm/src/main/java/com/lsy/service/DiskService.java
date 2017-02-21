package com.lsy.service;


import com.lsy.pojo.Disk;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21 0021.
 */

public interface DiskService {
    List<Disk> findDiskByFid(Integer path);

    void saveFolder(Disk disk);

    void uploadNewFile(Integer fid, MultipartFile file);
}

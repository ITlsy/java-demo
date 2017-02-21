package com.lsy.service;

import com.lsy.dto.DeviceRentDto;
import com.lsy.pojo.Device;
import com.lsy.pojo.DeviceRent;
import com.lsy.pojo.DeviceRentDetail;
import com.lsy.pojo.DeviceRentDocs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2017/1/13 0013.
 */
public interface DeviceService {
    void addDevice(Device device);

    List<Device> findDeviceByPage(String start, String length);

    Long count();

    List<Device> findAll();

    List<Device> findDeviceBySearchParam(Map<String, Object> searchParam);

    Long countBySearchParam(Map<String, Object> searchParam);

    void delDevice(Integer id);

    List<Device> findAllDevice();

    Device findDeviceById(Integer id);


    String saveRent(DeviceRentDto deviceRentDto);

    DeviceRent findDeviceRentBySerialNumber(String serialNumber);

    List<DeviceRentDetail> findDeviceRentDetailByRentId(Integer id);

    List<DeviceRentDocs> findDeviceRentDocsByRentId(Integer id);

    InputStream downloadFile(Integer id) throws IOException;

    DeviceRentDocs findDeviceRentDocById(Integer id);

    DeviceRent findDeviceRentById(Integer id);

    void downloadZipFile(DeviceRent deviceRent, ZipOutputStream zipOutputStream) throws IOException;

    List<DeviceRent> findDeviceRentByQueryParam(Map<String, Object> queryParam);

    Long countOfDeviceRent();

    void changeRentState(Integer id);
}

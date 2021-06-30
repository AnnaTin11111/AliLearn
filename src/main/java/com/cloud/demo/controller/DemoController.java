package com.cloud.demo.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.cloud.demo.common.SmsSender;
import com.cloud.demo.common.Uploader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class DemoController {

    @Resource
    private SmsSender smsSender;

    @Resource
    private Uploader uploader;

    @GetMapping("sms")
    public String sms(@RequestParam("phone") String phone) {
        SendSmsResponse rsp = smsSender.send(phone);
        if (rsp == null) {
            return "error";
        }
        return rsp.toString();
    }

    @PostMapping(value = "upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();

        return uploader.put(filename, file.getInputStream(), contentType);
    }

    @DeleteMapping(value = "delete")
    public String delete(@RequestParam("filename") String filename) {
        uploader.delete(filename);
        return "ok";
    }
}

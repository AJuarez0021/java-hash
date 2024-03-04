package com.work.encrypt.service;

import com.work.encrypt.dto.EncryptStringDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author AJO
 */
public interface Validator {

    void validate(String type, EncryptStringDTO request);

    void validate(String type, MultipartFile file);
}

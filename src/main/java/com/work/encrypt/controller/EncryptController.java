package com.work.encrypt.controller;

import com.work.encrypt.dto.EncryptStringDTO;
import com.work.encrypt.dto.ResponseDTO;
import com.work.encrypt.service.EncryptFactory;
import com.work.encrypt.service.Validator;
import com.work.encrypt.util.HashTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author AJO
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1/api/encrypt")
@Slf4j
public class EncryptController {

    @Autowired
    private EncryptFactory factory;

    @Autowired
    private Validator validator;

    @GetMapping(path = "/types", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO<List<String>>> listHash() {
        ResponseDTO<List<String>> response = new ResponseDTO<>();
        List<String> list = new ArrayList<>();
        for (HashTypes hash : HashTypes.values()) {
            list.add(hash.getName());
        }
        response.setContent(list);
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/string/{type}/hash", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO<String>> hash(@PathVariable("type") String type, @RequestBody EncryptStringDTO request) {
        log.info("Type: {}", type);
        validator.validate(type, request);
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setContent(factory.createFactory(type).hash(request.getInput()));
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/file/{type}/hash", consumes = "multipart/form-data", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO<String>> hashFile(@PathVariable("type") String type, @RequestParam("file") MultipartFile file) throws IOException {
        log.info("Type: {}", type);
        log.info("FileName: {}", file.getResource().getFilename());
        log.info("Content: {}", file.getContentType());
        validator.validate(type, file);
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setContent(factory.createFactory(type).hash(file.getInputStream()));
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

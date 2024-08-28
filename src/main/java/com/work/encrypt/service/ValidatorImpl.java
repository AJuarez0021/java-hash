package com.work.encrypt.service;

import com.work.encrypt.dto.EncryptStringDTO;
import com.work.encrypt.util.HashTypes;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author AJO
 */
@Service
@Slf4j
public class ValidatorImpl implements Validator {

    private boolean search(String type) {
        HashTypes[] types = HashTypes.values();
        HashTypes typeCurrent = HashTypes.valueOf(type.toUpperCase());
        for (HashTypes hashType : types) {
            log.debug("{} - {}", hashType.getName(), typeCurrent.getName());
            if (hashType.getName().equals(typeCurrent.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void validate(String type, EncryptStringDTO request) {
        if (isEmpty(type)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de encriptacion esta vacio");
        }

        if (!search(type)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo " + type + " es desconocido");

        }

        if (isEmpty(request.getInput())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cadena esta vacia");
        }
    }

    @Override
    public void validate(String type, MultipartFile file) {
        if (isEmpty(type)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de encriptacion esta vacio");
        }

        if (!search(type)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo " + type + " es desconocido");

        }

        try {
            if (file == null || file.getInputStream() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El archivo es nulo");
            }
            if (file.getSize() == 0L) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El archivo esta vacio");
            }
        } catch (IOException ex) {            
            log.error("Error: ", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrio un error");
        }
    }

    private boolean isEmpty(final String string) {
        return !StringUtils.hasText(string);
    }
}

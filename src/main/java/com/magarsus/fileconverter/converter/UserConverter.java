package com.magarsus.fileconverter.converter;

import com.csvreader.CsvReader;
import com.magarsus.fileconverter.constants.Messages;
import com.magarsus.fileconverter.dto.UserDto;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserConverter implements Converter<CsvReader, UserDto> {
    private static final Logger LOG = Logger.getLogger(UserConverter.class.getName());

    @Override
    public UserDto convert(CsvReader source) {

        String email = getCsvValue(source, 3);
        if (isNull("email", email, source)) {
            return null;
        }
        String username = getCsvValue(source, 0);
        if (isNull("username", username, source)) {
            return null;
        }

        String key = generateKey(username, email);
        String firstname = getCsvValue(source, 1);
        String lastname = getCsvValue(source, 2);
        String role = getCsvValue(source, 4);

        UserDto user = new UserDto(username, firstname, lastname, email, key);

        if (StringUtils.isNotBlank(role)) {
            user.addRole(role);
        } else {
            LOG.log(Level.WARNING, Messages.ROLE_NOT_PROVIDED, username);
        }

        return user;
    }

    private boolean isNull(String name, String value, CsvReader source) {
        if (Objects.isNull(value)) {
            LOG.log(Level.WARNING, Messages.format(Messages.MISSING_CSV_DATA, name, source.getCurrentRecord() + 2));
            return true;
        }
        return false;
    }

    private String getCsvValue(CsvReader source, int index) {
        try {
            String value = source.get(index);
            return StringUtils.isBlank(value) ? null : value;
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            LOG.log(Level.SEVERE, Messages.format(Messages.FILE_READ_FAILED, index, source.getCurrentRecord()), e.getMessage());
            return null;
        }
    }

    private String generateKey(String username, String email) {
        return username.trim().concat(email.trim());
    }

    @Override
    public <U> Converter<CsvReader, U> andThen(Converter<? super UserDto, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}

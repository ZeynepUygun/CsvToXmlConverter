package com.magarsus.fileconverter.facade;

import com.csvreader.CsvReader;
import com.magarsus.fileconverter.constants.ConverterStatic;
import com.magarsus.fileconverter.constants.Messages;
import com.magarsus.fileconverter.converter.UserConverter;
import com.magarsus.fileconverter.dto.UserDto;
import com.magarsus.fileconverter.exception.CsvToXmlGeneralException;
import com.magarsus.fileconverter.exception.ReadProcessesException;
import com.magarsus.fileconverter.services.CsvReaderService;
import com.magarsus.fileconverter.services.FreeMarkerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class XmlGenerationAndWriteFacade {
    private static final Logger LOG = Logger.getLogger(XmlGenerationAndWriteFacade.class.getName());
    @Resource
    private CsvReaderService csvReaderService;
    @Resource
    private FreeMarkerService freeMarkerService;
    @Resource
    private UserConverter userConverter;

    public void createXmlFromCsv(String xmlFileName, String csvFileName, Locale locale) {

        CsvReader csvReader = csvReaderService.readCsvFile(ConverterStatic.FILE_PATH.concat(csvFileName).concat(ConverterStatic.CSV));
        Map<String, UserDto> usersList = convertCsvRowToUserDto(csvReader);
        try {

            Map<String, Object> model = Map.of("users", usersList, "headers", csvReader.getHeaders());
            freeMarkerService.writeToFile(xmlFileName, locale, model);
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new ReadProcessesException(Messages.format(Messages.FILE_HEADER_READ_FAILED));
        }
    }

    private Map<String, UserDto> convertCsvRowToUserDto(CsvReader csvReader) {
        Map<String, UserDto> userDtoMap = new LinkedHashMap<>();

        while (true) {
            try {
                if (!csvReader.readRecord()) break;
            } catch (IOException e) {
                LOG.log(Level.SEVERE, e.getMessage());
                throw new CsvToXmlGeneralException(Messages.format(Messages.CSV_FILE_READ_FAILED));
            }
            UserDto userDto = userConverter.convert(csvReader);
            if (Objects.nonNull(userDto)) {
                userDtoMap.computeIfAbsent(userDto.getKey(), k -> userDto).getRoles().addAll(userDto.getRoles());
            }
        }
        return userDtoMap;
    }
}

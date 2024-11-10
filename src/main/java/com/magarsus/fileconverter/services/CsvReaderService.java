package com.magarsus.fileconverter.services;

import com.csvreader.CsvReader;
import com.magarsus.fileconverter.constants.Messages;
import com.magarsus.fileconverter.exception.ReadProcessesException;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvReaderService {

    public CsvReader readCsvFile(String filePath) {

        try {
            CsvReader csvReader = new CsvReader(new FileReader(filePath));
            csvReader.readHeaders();
            return csvReader;
        } catch (IOException e) {
            throw new ReadProcessesException(Messages.format(Messages.CSV_FILE_READ_FAILED));
        }
    }
}
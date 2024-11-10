package com.magarsus.fileconverter.controller;

import com.magarsus.fileconverter.constants.Messages;
import com.magarsus.fileconverter.facade.XmlGenerationAndWriteFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
@RequestMapping("/file-converter")
public class FileConverterController {
    public static final String XML_FILE_NAME = "xmlFileName";
    public static final String CSV_FILE_NAME = "csvFileName";
    @Resource
    private XmlGenerationAndWriteFacade xmlGenerationAndWriteFacade;

    @GetMapping
    public ResponseEntity<String> writeXml(HttpServletRequest request) {
        Locale locale = RequestContextUtils.getLocale(request);
        String xmlFileName = request.getParameter(XML_FILE_NAME);
        String csvFileName = request.getParameter(CSV_FILE_NAME);


        xmlGenerationAndWriteFacade.createXmlFromCsv(xmlFileName, csvFileName, locale);
        return ResponseEntity.ok(Messages.format(Messages.XML_GENERATION_SUCCESS));

    }
}

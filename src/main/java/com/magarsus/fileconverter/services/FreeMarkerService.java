package com.magarsus.fileconverter.services;

import com.magarsus.fileconverter.constants.ConverterStatic;
import com.magarsus.fileconverter.constants.Messages;
import com.magarsus.fileconverter.exception.WriteProcessesException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FreeMarkerService {


    @Resource
    private FreeMarkerConfig freeMarkerConfigurer;
    private static final Logger LOG = Logger.getLogger(FreeMarkerService.class.getName());

    private Template loadTemplate(Locale locale) {
        try {
            return freeMarkerConfigurer.getConfiguration().getTemplate(ConverterStatic.TEMPLATE_NAME, locale);
        } catch (IOException e) {
            throw new WriteProcessesException(Messages.TEMPLATE_PROCESSING_ERROR);
        }
    }

    public void writeToFile(String xmlFileName, Locale locale, Map<String, Object> model) {
        if (StringUtils.isBlank(xmlFileName)) {
            xmlFileName = ConverterStatic.DEFAULT_FILE_NAME;
        }

        String filePath = Paths.get(
                ConverterStatic.FILE_PATH,
                xmlFileName.concat(ConverterStatic.XML)
        ).toString();

        try {
            Template template = loadTemplate(locale);
            String xmlOutput = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(xmlOutput);
                LOG.log(Level.INFO, Messages.format(Messages.FILE_WRITE_SUCCESS, filePath));
            }
        } catch (TemplateException e) {
            LOG.log(Level.SEVERE, e.getMessage());
            throw new WriteProcessesException(Messages.format(Messages.TEMPLATE_PROCESSING_ERROR));
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.fillInStackTrace().getMessage());
            throw new WriteProcessesException(Messages.format(Messages.FILE_WRITE_FAILED, filePath));
        }
    }
}
package com.magarsus.fileconverter.constants;

import java.text.MessageFormat;

public class Messages {

    public static final String XML_GENERATION_SUCCESS = "XML dosyası oluşturma ve yazma işlemleri tamamlandı.";
    public static final String UNEXPECTED_ERROR = "İşlem başarısız: Beklenmedik bir hata oluştu.";
    public static final String FILE_READ_FAILED = "CSV dosyası okunurken bir hata oluştu: {0}";
    public static final String FILE_HEADER_READ_FAILED = "CSV dosyasından header okunurken bir hata oluştu.";
    public static final String CSV_FILE_READ_FAILED = "CSV dosyası okunurken bir hata oluştu.";
    public static final String FILE_WRITE_SUCCESS = "XML dosyası başarıyla yazıldı: {0}";
    public static final String FILE_WRITE_FAILED = "XML dosyası yazılamadı: {0}";
    public static final String TEMPLATE_PROCESSING_ERROR = "Şablon işlenirken bir hata oluştu. Dil seçimini kontrol ediniz.";
    public static final String MISSING_CSV_DATA = "Gerekli veri eksik: {0} (Satır: {1}) - Bu satır işleme alınmadı.";
    public static final String ROLE_NOT_PROVIDED = "Rol bilgisi mevcut değil: Kullanıcı adı - {0}";


    public static String format(String message, Object... params) {
        return MessageFormat.format(message, params);
    }

}

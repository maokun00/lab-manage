package com.lab.manage.enums;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chengcheng on 2018/12/12.
 */
public enum FileEnum {

    /**
     * SWF.
     */
    SWF("535746","swf"),
    /**
     * FLA.
     */
    FLA("464c41","fla"),
    /**
     * JEPG.
     */
    JPEG("FFD8FF","jpeg"),
    /**
     * JPG.
     */
    JPG("jpg","jpg"),
    /**
     * PNG.
     */
    PNG("89504E47","png"),

    /**
     * GIF.
     */
    GIF("47494638","gif"),

    /**
     * TIFF.
     */
    TIFF("49492A00","tiff"),

    /**
     * Windows Bitmap.
     */
    BMP("424D","bmp"),

    /**
     * CAD.
     */
    DWG("41433130","dwg"),

    /**
     * Adobe Photoshop.
     */
    PSD("38425053","psd"),

    /**
     * Rich Text Format.
     */
    RTF("7B5C727466","rtf"),

    /**
     * XML.
     */
    XML("3C3F786D6C","xml"),

    /**
     * HTML.
     */
    HTML("68746D6C3E","html"),
    /**
     * CSS.
     */
    CSS("48544D4C207B0D0A0942","css"),
    /**
     * JS.
     */
    JS("696B2E71623D696B2E71","js"),
    /**
     * Email [thorough only].
     */
    EML("44656C69766572792D646174653A","eml"),

    /**
     * Outlook Express.
     */
    DBX("CFAD12FEC5FD746F","dbx"),

    /**
     * Outlook (pst).
     */
    PST("2142444E","pst"),

    /**
     * MS Word/Excel.
     */
    XLS_DOC("D0CF11E0","xls_doc"),
    XLSX_DOCX("504B030414000600080000002100","xlsx_docx"),
    /**
     * Visio
     */
    VSD("d0cf11e0a1b11ae10000","vsd"),
    /**
     * MS Access.
     */
    MDB("5374616E64617264204A","mdb"),
    /**
     * WPS文字wps、表格et、演示dps都是一样的
     */
    WPS("d0cf11e0a1b11ae10000","wps"),
    /**
     * torrent
     */
    TORRENT("6431303A637265617465","torrent"),
    /**
     * WordPerfect.
     */
    WPD("FF575043","wpd"),

    /**
     * Postscript.
     */
    EPS("252150532D41646F6265","eps"),

    /**
     * Adobe Acrobat.
     */
    PDF("255044462D312E","pdf"),

    /**
     * Quicken.
     */
    QDF("AC9EBD8F","qdf"),

    /**
     * Windows Password.
     */
    PWL("E3828596","pwl"),

    /**
     * ZIP Archive.
     */
    ZIP("504B0304","zip"),

    /**
     * RAR Archive.
     */
    RAR("52617221","rar"),
    /**
     * JSP Archive.
     */
    JSP("3C2540207061676520","jsp"),
    /**
     * JAVA Archive.
     */
    JAVA("7061636B61676520","java"),
    /**
     * CLASS Archive.
     */
    CLASS("CAFEBABE0000002E00","class"),
    /**
     * JAR Archive.
     */
    JAR("504B03040A000000","jar"),
    /**
     * MF Archive.
     */
    MF("4D616E69666573742D56","mf"),
    /**
     *EXE Archive.
     */
    EXE("4D5A9000030000000400","exe"),
    /**
     *CHM Archive.
     */
    CHM("49545346030000006000","chm"),
    /**
     * Wave.
     */
    WAV("57415645","wav"),

    /**
     * AVI.
     */
    AVI("41564920","avi"),

    /**
     * Real Audio.
     */
    RAM("2E7261FD","ram"),

    /**
     * Real Media.
     */
    RM("2E524D46","rm"),

    /**
     * MPEG (mpg).
     */
    MPG("000001BA","mpg"),

    /**
     * Quicktime.
     */
    MOV("6D6F6F76","mov"),

    /**
     * Windows Media.
     */
    ASF("3026B2758E66CF11","asf"),

    /**
     * MIDI.
     */
    MID("4D546864","mid"),
    /**
     * MP4.
     */
    MP4("00000020667479706d70","mp4"),
    /**
     * MP3.
     */
    MP3("49443303000000002176","mp3"),
    /**
     * FLV.
     */
    FLV("464C5601050000000900","flv");

    private Integer type;
    private String value;
    private String code;

    FileEnum(String code, String value) {
        this.code = code;
        this.value = value;
        switch (value){
            case "jpeg":
            case "jpg":
            case "png":
            case "gif":
            case "tiff":
            case "bmp":
            case "dwg":
            case "psd":
                this.type = 1;
                break;

            case "rtf":
            case "xml":
            case "html":
            case "css":
            case "js":
            case "eml":
            case "dbx":
            case "pst":
            case "xls_doc":
            case "xlsx_docx":
            case "vsd":
            case "mdb":
            case "wps":
            case "wpd":
            case "eps":
            case "pdf":
            case "qdf":
            case "pwl":
            case "zip":
            case "rar":
            case "jsp":
            case "java":
            case "class":
            case "jar":
            case "mf":
            case "exe":
            case "chm":
                this.type = 0;
                break;


            case "avi":
            case "ram":
            case "rm":
            case "mpg":
            case "mov":
            case "asf":
            case "mp4":
            case "flv":
            case "mid":
                this.type = 3;
                break;


            case "fla":
            case "swf":
                this.type = 2;
                break;


            case "wav":
            case "mp3":
                this.type = 5;
                break;

            default:
                this.type = -1;
                break;
        }

    }

    public Integer getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    /**
     * @Author Chengcheng
     * @Description : 通过code返回枚举类型
     * @Date 2018/3/21 下午3:12
     * @param code 数据格式
     */
    public static FileEnum getByCode(String code){
        if(StringUtils.isNotEmpty(code)){
            FileEnum[] values = FileEnum.values();
            for(FileEnum fileEnum : values){
                if(fileEnum.getCode().equalsIgnoreCase(code)){
                    return fileEnum;
                }
            }
        }
        return null;
    }

    /**
     * @Author Chengcheng
     * @Description : 通过value返回枚举类
     * @Date 2018/3/21 下午3:12
     * @param value 文件拓展名
     */
    public static FileEnum getByValue(String value){
        if(StringUtils.isNotEmpty(value)){
            FileEnum[] values = FileEnum.values();
            for(FileEnum fileEnum : values){
                if(fileEnum.getValue().equalsIgnoreCase(value)){
                    return fileEnum;
                }
            }
        }
        return null;
    }

    /**
     * @Author Chengcheng
     * @Description : 通过type获取相关枚举集合
     * @Date 2018/3/21 下午3:13
     * @param type 对应数据类型
     */
    public static List<FileEnum> getByType(int type){
        FileEnum[] values = FileEnum.values();
        List<FileEnum> list = new ArrayList<>();
        for(FileEnum fileEnum : values){
            if(type == fileEnum.getType()){
                list.add(fileEnum);
            }
        }
        return list;
    }

}

package ua.v380dev.model.util;

import java.util.regex.Pattern;

public class HolderPattern {
    private static final String REG_GET_PARENT_NAME = "(?<=#.*`)\\w+(?=`)";
    private static final String REG_PARENT_OBJ = "#{1,} `";
    private static final String REG_ENDPOINT = "#{1,}[^`]+\\[.*\\]";
    private static final String REG_REQUEST = "\\+ Request";
    private static final String REG_RESPONSE = "\\+ Response";
    private static final String REG_INPUT_NAME_FIELD = "^[\\w]{2,99}$";
    private static final String REG_INPUT_NAME_FILE = "^[\\w\\(\\)-]+[\\w\\.\\(\\)-]*[\\.]\\w+$";

    public static Pattern getObjName () {
        return Pattern.compile(REG_GET_PARENT_NAME);
    }
    public static Pattern getObj () {
        return Pattern.compile(REG_PARENT_OBJ);
    }

    public static Pattern getEndp () {
        return Pattern.compile(REG_ENDPOINT);
    }

    public static Pattern getReq () {
        return Pattern.compile(REG_REQUEST);
    }

    public static Pattern getRes () {
        return Pattern.compile(REG_RESPONSE);
    }
    public static Pattern getInputNameField () {
        return Pattern.compile(REG_INPUT_NAME_FIELD);
    }
    public static Pattern getInputNameFile () {
        return Pattern.compile(REG_INPUT_NAME_FILE);
    }
}

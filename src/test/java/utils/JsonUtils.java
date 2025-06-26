package utils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtils {

    private static final Logger logger = LogManager.getLogger(JsonUtils.class);

    // Read a single value from nested JSON
    public static String readDataFromJson(String filePath, String sectionKey, String objectKey, String nestedKey) {
        try (FileReader reader = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(reader);
            JSONArray sectionArray = (JSONArray) root.get(sectionKey);

            for (Object obj : sectionArray) {
                JSONObject jsonObj = (JSONObject) obj;

                if (jsonObj.containsKey(objectKey)) {
                    JSONObject nestedObj = (JSONObject) jsonObj.get(objectKey);
                    if (nestedObj.containsKey(nestedKey)) {
                        return nestedObj.get(nestedKey).toString();
                    }
                } else if (jsonObj.containsKey(nestedKey)) {
                    return jsonObj.get(nestedKey).toString();
                }
            }
        } catch (Exception e) {
            logger.error("Error reading JSON value", e);
        }
        return "";
    }

    // Read a list of strings from a nested JSON array
    public static List<String> readListFromJson(String filePath, String sectionKey, String mainKey, String typeKey) {
        List<String> values = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath)) {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(reader);
            JSONArray sectionArray = (JSONArray) root.get(sectionKey);

            for (Object obj : sectionArray) {
                JSONObject jsonObj = (JSONObject) obj;
                if (jsonObj.containsKey(mainKey)) {
                    JSONObject inner = (JSONObject) jsonObj.get(mainKey);
                    if (inner.containsKey(typeKey)) {
                        JSONArray array = (JSONArray) inner.get(typeKey);
                        for (Object item : array) {
                            values.add(item.toString());
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error reading JSON list", e);
        }
        return values;
    }
}

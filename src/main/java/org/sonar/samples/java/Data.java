package org.sonar.samples.java;

import java.util.HashMap;
import java.util.Map;

public class Data {
    public Map<String, Integer> imports;
    public Map.Entry<String, Integer> packageName;
    public Map.Entry<String, Integer> className;
    public Map<String, Integer> methodNames;

    public Data()
    {
        imports = new HashMap<>();
        methodNames = new HashMap<>();
    }
}

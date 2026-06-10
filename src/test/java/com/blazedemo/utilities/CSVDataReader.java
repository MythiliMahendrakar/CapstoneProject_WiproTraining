package com.blazedemo.utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataReader {

    public static Object[][] getData(String fileName) {
        List<Object[]> dataList = new ArrayList<>();

        try {
            InputStream is = CSVDataReader.class
                    .getClassLoader()
                    .getResourceAsStream("testdata/" + fileName);

            System.out.println("FILE FOUND? " + (is != null));

            if (is == null) {
                throw new RuntimeException("CSV NOT FOUND: " + fileName);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] s = line.split(",");
                dataList.add(new Object[]{
                        s[0], s[1], s[2], s[3], s[4],
                        s[5], s[6], s[7], s[8], s[9], s[10]
                });
            }
            br.close();

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("ROWS LOADED FROM CSV = " + dataList.size());
        return dataList.toArray(new Object[0][0]);
    }
}

package io.github.cooljoseph.utils.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CsvReader {
    public static <T> List<T> read(String resourceFilePath, CsvFileReadable<T> func) {
        List<T> results = new ArrayList<>();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(resourceFilePath).toURI()));

            try (CSVReader csvReader = new CSVReaderBuilder(reader).build()) {
                List<String[]> records = csvReader.readAll();
                for (String[] record : records) {
                    T result = func.read(record);
                    results.add(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    @FunctionalInterface
    public interface CsvFileReadable<T> {
        T read(String[] records);
    }
}

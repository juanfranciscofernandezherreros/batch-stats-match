package com.fernandez.statsmatch.reader;

import com.fernandez.statsmatch.model.dto.StatsMatchBean;
import com.fernandez.statsmatch.utils.FileUtils;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CsvItemReader implements ItemReader<StatsMatchBean> {

    private BufferedReader bufferedReader;
    private boolean eof = false;
    private String filePath;

    public CsvItemReader(@Value("${file}") String filePath) throws IOException {
        this.filePath = filePath;
        this.bufferedReader = new BufferedReader(new InputStreamReader(new FileSystemResource(filePath).getInputStream()));
    }

    @Override
    public StatsMatchBean read() throws IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // Skip header line
            if (line.startsWith("Category Name")) continue;
            String[] parts = line.split(",");
            if (parts.length == 3) {
                String categoryName = parts[0].trim();
                String homeValue = parts[1].trim();
                String awayValue = parts[2].trim();
                return new StatsMatchBean(FileUtils.obtenerParteNombre(filePath), categoryName, homeValue, awayValue, FileUtils.obtenerUltimoCaracter(filePath));
            } else {
                // Log or throw exception for malformed lines
                return null;
            }
        }
        eof = true;
        return null;
    }



    @PreDestroy
    public void destroy() throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }

}

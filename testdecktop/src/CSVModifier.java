//package group.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileWriter;
public class CSVModifier
{
    public static List<String> readCSVColumn(String fileName, int columnIndex)
    {
        List<String> columnData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                if (values.length > columnIndex)
                    columnData.add(values[columnIndex]);
                else
                    columnData.add("");
            }
        }
        catch (IOException _) {}

        return columnData;
    }

    public static List<String[]> readCSVRow(String fileName, int rowIndex) {
        List<String[]> rowData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                if (lineNumber == rowIndex) {
                    String[] values = line.split(",");
                    rowData.add(values);
                    break;
                }
                lineNumber++;
            }
        } catch (IOException _) {}

        return rowData;
    }

    public static void writeDataToCSVTop(String filePath, List<String[]> data) {
        // Write new data at the top of the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            // Write new data
            for (String[] rowData : data) {
                writeRow(bw, rowData);
            }
        } catch (IOException _) {
        }
    }

    private static void writeRow(BufferedWriter bw, String[] rowData) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowData.length; i++) {
            sb.append(rowData[i]);
            if (i < rowData.length - 1) {
                sb.append(",");
            }
        }
        sb.append("\n");
        bw.write(sb.toString());
    }
    public static void writeDataToCSVBelow(String filePath, List<String[]> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String[] rowData : data) {
                writeRow(bw, rowData);
            }
        } catch (IOException _) {
        }
    }
    public static boolean createBlankCSV(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.close();
        } catch (IOException _) {
        }
        return false;
    }
    public static void clearCSVFile(String filePath) {
        try {
            new BufferedWriter(new FileWriter(filePath));
        }   catch (IOException _) {
        }
    }
    public static void editRow(String filePath, int rowIndex, List<String> newRowData) {
        List<String> fileContent = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentRow = 0;
            while ((line = br.readLine()) != null) {
                if (currentRow == rowIndex) {
                    // Replace the current row with the new row data
                    line = String.join(",", newRowData);
                }
                fileContent.add(line);
                currentRow++;
            }
        } catch (IOException _) {
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : fileContent) {
                bw.write(line);
                bw.write("\n");
            }
        } catch (IOException _) {
        }
    }


}


package com.prituladima.sb;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Starter {
    public static void main(String[] args) {

        final int lengthOfArray = 2_000_000;
        final int iterations = 10;
        final SortingAlgo[] sortingAlgos = {
                // TODO: 09.05.2021 Implement at least 5 different sort algorithms that works approximately about O(n*log(n)).
                // TODO: 09.05.2021 From the list
                // Bit radix sort
                // Bit radix sort (with bubble/insertion sort for arrays smaller than 20)
                // Merge sort (iterative)
                // Merge sort (recursive)
                // Merge sort (recursive) (with bubble/insertion sort for arrays smaller than 20)
                // Quick sort (iterative)
                // Quick sort (recursive)
                // Quick sort (recursive) (with bubble/insertion sort for arrays smaller than 20)
                new SortingAlgo_JavaLibSort(lengthOfArray),
        };

        final String[][] data = new String[sortingAlgos.length + 1][iterations + 1];
        data[0][0] = "Sorting";
        for (int colIndex = 1; colIndex < 1 + iterations; colIndex++) {
            data[0][colIndex] = "Iteration " + colIndex;
        }
        for (int rowIndex = 1; rowIndex < 1 + sortingAlgos.length; rowIndex++) {
            data[rowIndex][0] = sortingAlgos[rowIndex - 1].name();
        }

        for (int rowIndex = 1; rowIndex < 1 + sortingAlgos.length; rowIndex++) {
            for (int colIndex = 1; colIndex < 1 + iterations; colIndex++) {
                data[rowIndex][colIndex] = String.valueOf(sortingAlgos[rowIndex - 1].sortAndMeasureTime());
            }
        }

        generateExcelDoc(data);

    }


    private static void generateExcelDoc(String[][] data) {
        final Workbook workbook = new XSSFWorkbook();
        final Sheet sheet = workbook.createSheet("Sorting benchmarks");

        for (int rowIndex = 0; rowIndex < data.length; rowIndex++) {
            final Row header = sheet.createRow(rowIndex);
            for (int colIndex = 0; colIndex < data[rowIndex].length; colIndex++) {
                final Cell headerCell = header.createCell(colIndex);
                if (rowIndex == 0 || colIndex == 0) {
                    headerCell.setCellValue(data[rowIndex][colIndex]);
                } else {
                    headerCell.setCellValue(Integer.parseInt(data[rowIndex][colIndex]));
                }

            }
        }

        final String name = "benchmarks" + new Date() + ".xlsx";
        try (FileOutputStream outputStream = new FileOutputStream(name)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

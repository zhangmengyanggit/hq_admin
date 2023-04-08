package com.ruoyi.common.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Base64Encoder {

    private static final Logger LOGGER = Logger.getLogger("Base64Encoder");

    public static void readFileToBase64Str(String[] args) {
        System.out.print("Enter an input filename: ");
        Scanner inputFromConsole = new Scanner(System.in);
        String filename = inputFromConsole.nextLine();
        FileOutputStream outputToFile = null;
        try (FileInputStream inputFromFile = new FileInputStream(filename)) {
            System.out.print("Enter an encoded filename: ");
            filename = inputFromConsole.nextLine();
            outputToFile = new FileOutputStream(filename);
            byte[] src = new byte[inputFromFile.available()];
            inputFromFile.read(src);
            byte[] encodedBytes = Base64.getEncoder().encode(src);
            outputToFile.write(encodedBytes);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            if (outputToFile != null) {
                try {
                    outputToFile.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
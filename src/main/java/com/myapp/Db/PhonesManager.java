package com.myapp.Db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class PhonesManager {
    private ArrayList<Phone> phones;

    public PhonesManager() {
        phones = new ArrayList<>();
    }

    public PhonesManager(ArrayList<Phone> incomePhones) {
        phones = new ArrayList<>(incomePhones);
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    public int getSize() {
        return phones.size();
    }

    public Phone getPhone(int index) {
        return phones.get(index);
    }

    public void loadPhonesFromTxtFile(String fileName) throws Exception {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int listSize = Integer.parseInt(bufferedReader.readLine());

        phones = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            String model = bufferedReader.readLine();
            int price = Integer.parseInt(bufferedReader.readLine());
            int quantity = Integer.parseInt(bufferedReader.readLine());

            phones.add(new Phone(model, price, quantity));
        }

        bufferedReader.close();
        fileReader.close();
    }

    public void savePhonesToTxtFile(String fileName) throws Exception {
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.write(Integer.toString(phones.size()));
        bufferedWriter.newLine();

        for (int i = 0; i < phones.size(); i++) {
            bufferedWriter.write(phones.get(i).getModel());
            bufferedWriter.newLine();

            bufferedWriter.write(Integer.toString(phones.get(i).getPrice()));
            bufferedWriter.newLine();

            bufferedWriter.write(Integer.toString(phones.get(i).getQuantity()));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
        fileWriter.close();
    }

    public String getListPhonesAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Phone phone : phones) {
            stringBuilder.append(phone).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

}

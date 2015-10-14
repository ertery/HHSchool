package ru.hh.school;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by agolubin on 08.10.2015.
 */

public class InputHelper {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static int massivCounter  = 0;
    private static int[] massivDimension =  new int[2];


    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static int inputNumberReader()
    {
        try {
            int number = Integer.parseInt(reader.readLine());
            return number;
        } catch (Exception e) {
            writeMessage("Вы ввели неправильное число, пожалуйста, повторите ввод");
            return inputNumberReader();
        }
    }

    public static int[] readInput()
    {
        ArrayList<Integer> temp = new ArrayList<>();
        String[] array;
        while (true) {
            try {
                String input = reader.readLine().trim();
                array = input.split(" ");
                break;
            } catch (IOException e) {
                writeMessage("Введены некоректные данные, повторите ввод");
            }
        }
        try {
            for (int i = 0; i < array.length; i++) {
                temp.add(Integer.valueOf(array[i]));
            }
        }
        catch (NumberFormatException e )
        {
            writeMessage("В массиве должны содержаться только чиловые параметры, пожалуйста, повторите ввод");
            return readInput();
        }

        massivDimension[massivCounter] = array.length;
        massivCounter++;

        if (checkN())
            return translateFromArrayList(temp);

        else
        {
            writeMessage("Длины массивов не совпадают, введите второй массив еще раз, его длина должна быть равна " + massivDimension[massivCounter - 2] + " сейчас его длина " + massivDimension[massivCounter -1]);
            massivCounter--;
            return readInput();
        }

    }

    private static int[] translateFromArrayList(ArrayList list)
    {
        int[] temp = new int[list.size()];
        for (int i = 0; i <  list.size(); i++)
        {
            temp[i] = Integer.parseInt(list.get(i) + "");
        }
        Arrays.sort(temp);
        return temp;
    }


    private static boolean checkN()
    {
        if ( massivCounter <= 1)
            return true;

        return massivDimension[0] == massivDimension[1];
    }



    public static int[] RandomGenerate()
    {
        int dim = massivDimension[0];
        int[] array = new int[dim];
        for (int i = 0; i < dim; i++)
        {
            array[i] = (int)(Math.random() * 1000);
        }

        Arrays.sort(array);
        return array;
    }

    public static int[] RandomGenerate(int dimension)
    {
        massivDimension[0] = dimension;
        massivCounter++;

        return RandomGenerate();
    }

    public static int ChooseInput()
    {
        System.out.println("1 - Для ввода массива с клавиатуры");
        System.out.println("2 - Для ввода массива из файла");
        System.out.println("3 - Для рандомной генерации упорядоченного массива");

        int choose;

        try {
            choose = Integer.parseInt(reader.readLine());
            if (choose == 1 || choose == 2 || choose == 3)
                return choose;
            else return ChooseInput();
        }
          catch (Exception e) {
            return 4;
        }

    }


    public static int[] fileInput( boolean isFirst) throws FileNotFoundException {
        File file;
        writeMessage("Введите полный путь к файлу. Данный в файле должны быть записаны в следующем виде:");
        writeMessage("Элементы в массиве разделяются пробелом, каждый массив начинается с отдельной строчки");
        while (true) {
            try {
                file = new File(reader.readLine());
                break;
            } catch (IOException e) {
                writeMessage("Файл по указанному пути не существует, пожалуйста, проверьте указанный путь.");
            }
        }
        Scanner sc = new Scanner(file);
        ArrayList<String> arrays = new ArrayList<>();

        while (sc.hasNextLine())
        {
            arrays.add(sc.nextLine());
        }

        String array;

         if (arrays.size() == 2) {
            writeMessage("Массив будет заполнен строкой из файла");
            if (isFirst){
                array = arrays.get(0);
                return getMassiv(array);}
            else {
                array = arrays.get(1);
             return getMassiv(array);
            }
        }
        else{
            writeMessage("Данные в файле не соответсвуют требуемым, выберите другой способ ввода");
                throw new FileNotFoundException();
         }

    }



    private static int[] getMassiv(String input) throws FileNotFoundException {
        ArrayList<Integer> temp = new ArrayList<>();
        String[] array = input.split(" ");

        for (int i = 0; i < array.length; i++) {
            temp.add(Integer.valueOf(array[i]));
        }

            massivDimension[massivCounter] = array.length;
            massivCounter++;

            if (checkN())
                return translateFromArrayList(temp);
            else {
                writeMessage("Длины массивов не совпадают");
                massivCounter--;
                throw  new FileNotFoundException();
            }
    }

}

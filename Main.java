package ru.hh.school;

import java.io.FileNotFoundException;


public class Main
{
    public static void main(String[] args) {


        InputHelper.writeMessage("Выберите способ ввода первого массива");
        int[] arr1 = chooser(true);
        InputHelper.writeMessage("Выберите способ ввода второго массива");
        int[] arr2 = chooser(false);

        System.out.println("Медиана = " + MedianaFinder.selectMediana(arr1, arr2));
    }

    private static int[] chooser(boolean isFirst)
    {
        while(true) {
            int choose = InputHelper.ChooseInput();
            switch (choose) {
                case 1:
                    if (isFirst)
                        InputHelper.writeMessage("Введите с консоли первый массив, элементы вводятся в одну строку, через пробел");
                    else
                        InputHelper.writeMessage("Введите с консоли второй массив, элементы вводятся в одну строку, через пробел");

                    return InputHelper.readInput();
                case 2:
                    try {
                        return InputHelper.fileInput(isFirst);
                    } catch (FileNotFoundException e) {
                        InputHelper.writeMessage("Выберите заново способ ввода");
                    }
                    break;
                case 3:
                    if (isFirst) {
                        InputHelper.writeMessage("Введите число элементов в массиве");
                        int dim = InputHelper.inputNumberReader();
                        InputHelper.writeMessage("Первый массив сгенерирован");
                        return InputHelper.RandomGenerate(dim);
                    } else {
                        InputHelper.writeMessage("Второй массив сгенерирован");
                        return InputHelper.RandomGenerate();
                    }

                    default:
                        InputHelper.writeMessage("Программа была непредвидено завершена");
                        System.exit(0);
            }
        }
    }

}
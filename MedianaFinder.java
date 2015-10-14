package ru.hh.school;


/**
 * Created by agolubin on 08.10.2015.
 */


public final class MedianaFinder {



    private static float getMediana(int[] array, int start, int end) {
        int n = end - start + 1;
        if (n % 2 != 0) {
            return array[start + n / 2];
        } else {
            return (float) (array[start + n / 2 - 1] + array[start + n / 2]) / 2;
        }
    }

    /**
     * @param a1 первый массив их двух элементов.
     * @param a2 второй массив их двух элементов.
     * @return возвращает медиану для случая когда рекурсия достигает базы и размер массива n = 2.
     */
    private static float getMediana(int[] a1, int[] a2)
    {
        return (float)(Math.max(a1[0], a2[0]) + Math.min(a1[1], a2[1]))/2 ;
    }

    /**
     * Метод служит для начального вызова перегруженного метода selectMediana с верхнимим и нижними границами, для создния рекурсии.
     * @param a1 первый массив, типа int, размерности N
     * @param a2 второй массив, типа int, размерности N
     * @return озвращает -1 если заданы массивы некоректной длины. Возвращает медиану двух упорядоченных массивов, типа float.
     * Базой рекурсии в данном случае является случай, когда размер массива достигнет значения 2. В противном
     * случае будет рекурсивно вызываться данный метод, с изменяющимися значениями верхней и нижней границ массивов.
     */
    public static float selectMediana(int[] a1, int[] a2)
    {
        return selectMediana(a1, a2, 0, a1.length - 1, 0, a2.length - 1) ;
    }


    /**
     * @param a1 первый массив, по которому идет сравнение. Не изменяется в процессе работы программы
     * @param a2 второй массив, по которому идет сравнение. Не изменяется в процессе работы программы
     * @param a1L нижняя(левая) граница для первого массива.
     * @param a1R верхняя(правая) граница для первого массива.
     * @param a2L нижняя(левая) граница для второго массива.
     * @param a2R верхняя(правая) граница для второго массива.
     * @return возвращает -1 если заданы массивы некоректной длины. Возвращает медиану двух упорядоченных массивов, типа float.
     * Базой рекурсии в данном случае является случай, когда размер массива достигнет значения 2. В противном
     * случае будет рекурсивно вызываться данный метод, с изменяющимися значениями верхней и нижней границ массивов.
     */
    private static float selectMediana(int[] a1, int[] a2, int a1L, int a1R, int a2L, int a2R)
    {
        //получаем длину текущего подмассива, на первом шаге итерации, длина n, равна длине исходного массива.
        int n = a1R - a1L + 1;

        if (n <= 0)
            return -1;
        else if (n == 1)
            return (float)(a1[a1R] + a2[a2R]) /2;
        else if (n == 2) {
            int[] aa1 = {a1[a1L], a1[a1R]};
            int[] aa2 = {a2[a2L], a2[a2R]};
            return getMediana(aa1, aa2);
        }

        float m1 =  getMediana(a1, a1L, a1R);
        float m2  = getMediana(a2, a2L, a2R);


        if (m1 == m2)
            return m1;
        else if (m1 > m2) {
            if (n % 2 == 0) {
                return selectMediana(a1, a2, a1L, a1R - n / 2 + 1, a2L + n / 2 - 1, a2R);
            } else
                return selectMediana(a1, a2, a1L, a1R - n / 2, a2L + n / 2, a2R);
        }
        else if (m1 < m2) {
            if (n % 2 == 0) {
                return selectMediana(a1, a2, a1L + n / 2 - 1, a1R, a2L, a2R - n / 2 + 1);
            } else
                return selectMediana(a1, a2, a1L + n / 2, a1R, a2L, a2R - n / 2);
        }
        return -1;
    }
}
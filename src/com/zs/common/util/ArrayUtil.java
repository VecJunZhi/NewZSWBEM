package com.zs.common.util;

/**
 * <p>Title: ����������</p>
 * <p>Description: ����������</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * @author jiarui
 * @version 1.0
 */
public class ArrayUtil
{

    /**
     * ��������
     * @param data ��������
     * @param start ������ʼλ��
     * @param end ����Ľ���λ��
     * @param isDesc �Ƿ���
     */
    public static void quicksort(
            int[] data,
            int start,
            int end,
            boolean isDesc)
    {
        int pivot;

        if (start < end)
        {
            if (isDesc)
            {
                pivot = partitionDesc(data, start, end);
            } else
            {
                pivot = partition(data, start, end);
            }

            quicksort(data, start, pivot - 1, isDesc);
            quicksort(data, pivot + 1, end, isDesc);
        }
    }

    /**
     * ��С��������
     * @param a ��������
     * @param low ��ʼλ��
     * @param high ����λ��
     * @return int
     */
    private static int partition(int[] a, int low, int high)
    {
        int pivot, p_pos, tmp, i, j;

        p_pos = low;
        pivot = a[p_pos];
        for (i = low + 1; i <= high; i++)
        {
            if (a[i] < pivot)
            {
                p_pos++;
                swap(a, p_pos, i);
            }
        }
        swap(a, low, p_pos);

        return p_pos;
    }

    /**
     * �Ӵ�С����
     * @param a ��������
     * @param low ������ʼλ��
     * @param high �������λ��
     * @return int
     */
    private static int partitionDesc(int[] a, int low, int high)
    {
        int pivot, p_pos, tmp, i, j;

        p_pos = low;
        pivot = a[p_pos];
        for (i = low + 1; i <= high; i++)
        {
            if (a[i] > pivot)
            {
                p_pos++;
                swap(a, p_pos, i);
            }
        }
        swap(a, low, p_pos);

        return p_pos;
    }

    private static void swap(int[] a, int i, int j)
    {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * ��������
     * @param data ���������
     * @param start �������ʼλ��
     * @param end ����Ľ���λ��
     * @param isDesc �Ƿ���
     */
    public static void quicksort(
            long[] data,
            int start,
            long end,
            boolean isDesc)
    {
        int pivot;

        if (start < end)
        {
            if (isDesc)
            {
                pivot = partitionDesc(data, start, end);
            }
            else
            {
                pivot = partition(data, start, end);
            }

            quicksort(data, start, pivot - 1, isDesc);
            quicksort(data, pivot + 1, end, isDesc);
        }
    }

    private static int partition(long[] a, int low, long high)
    {
        long pivot;
        int p_pos, tmp, i, j;

        p_pos = low;
        pivot = a[p_pos];
        for (i = low + 1; i <= high; i++)
        {
            if (a[i] < pivot)
            {
                p_pos++;
                swap(a, p_pos, i);
            }
        }
        swap(a, low, p_pos);

        return p_pos;
    }

    private static int partitionDesc(long[] a, int low, long high)
    {
        long pivot;
        int p_pos, tmp, i, j;

        p_pos = low;
        pivot = a[p_pos];
        for (i = low + 1; i <= high; i++)
        {
            if (a[i] > pivot)
            {
                p_pos++;
                swap(a, p_pos, i);
            }
        }
        swap(a, low, p_pos);

        return p_pos;
    }

    private static void swap(long[] a, int i, int j)
    {
        long tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] argv)
    {
        int[] i = { 3, 6, 7, 4, 5, 13, 54, 67, 87, 1 };
        quicksort(i, 0, i.length - 1, false);
        for (int j = 0; j < i.length; j++)
            System.out.println(i[j]);
    }

}

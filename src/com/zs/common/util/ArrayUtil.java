package com.zs.common.util;

/**
 * <p>Title: 基本工具类</p>
 * <p>Description: 基本工具类</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * @author jiarui
 * @version 1.0
 */
public class ArrayUtil
{

    /**
     * 快速排序
     * @param data 排序数据
     * @param start 排序起始位置
     * @param end 排序的结束位置
     * @param isDesc 是否降序
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
     * 从小到大排序
     * @param a 排序数组
     * @param low 起始位置
     * @param high 结束位置
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
     * 从大到小排序
     * @param a 排序数组
     * @param low 排序起始位置
     * @param high 排序结束位置
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
     * 快速排序
     * @param data 排序的数据
     * @param start 排序的起始位置
     * @param end 排序的结束位置
     * @param isDesc 是否降序
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

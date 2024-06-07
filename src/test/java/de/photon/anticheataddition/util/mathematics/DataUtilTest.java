package de.photon.anticheataddition.util.mathematics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class DataUtilTest
{
    private static final double DELTA = 0.001;

    @Test
    void testSquaredError()
    {
        Assertions.assertEquals(0, DataUtil.variance(0, 0));
        Assertions.assertEquals(0, DataUtil.variance(Integer.MAX_VALUE, Integer.MAX_VALUE));
        Assertions.assertEquals(0, DataUtil.variance(Double.MAX_VALUE, Double.MAX_VALUE), DELTA);
        Assertions.assertEquals(25, DataUtil.variance(0, 5));
        Assertions.assertEquals(3, DataUtil.variance(0, 1D, 1D, 1D), DELTA);
        Assertions.assertEquals(171.5, DataUtil.variance(0, -4D, 1.5D, 2D, 7D, 8D, 0D, 4D, -4.5D), DELTA);
        Assertions.assertEquals(66.25, DataUtil.variance(-4, -4D, 1.5D, 2D), DELTA);
    }

    @Test
    void testRemoveOutliers()
    {
        final long[] data = {30, 20, 200, 20, 10, 20, 40, 20, 10};

        long[] expected = {30, 20, 20, 10, 20, 40, 20, 10};
        Arrays.sort(expected);

        long[] actual = DataUtil.removeOutliers(1, data);
        Arrays.sort(actual);

        Assertions.assertArrayEquals(expected, actual, "The outliers were not removed correctly.");
    }

    @Test
    void testRemoveOutliersDuplicates()
    {
        final long[] data = {10, 20, 20, 20, 20, 20, 20, 20, 100, 100};

        long[] expected = {10, 20, 20, 20, 20, 20, 20, 20};
        long[] actual = DataUtil.removeOutliers(2, data);

        Assertions.assertArrayEquals(expected, actual, "The outliers were not removed correctly.");
    }
}

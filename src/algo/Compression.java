//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package algo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Compression {

    public Compression() {
    }

    public abstract void DirectoryCompression(String var1, String var2) throws IOException;

    public abstract int[] compression(String var1, String var2);

    public abstract void decompression(String var1, String var2);

    public double getComRatio(File input, File compressedFile) {
        List<String> list = new ArrayList();
        list.add(input.getPath());
        long size = 0L;

        for(int i = 0; i < list.size(); ++i) {
            String path = (String)list.get(i);
            File tmp = new File(path);
            if (tmp.isDirectory()) {
                File[] files = tmp.listFiles();
                if (files != null) {
                    File[] var10 = files;
                    int var11 = files.length;

                    for(int var12 = 0; var12 < var11; ++var12) {
                        File file = var10[var12];
                        list.add(file.getAbsolutePath());
                    }
                }
            } else {
                size += tmp.length();
            }
        }

        return (double)(size - compressedFile.length()) * 1.0D / (double)size * 100.0D;
    }


    public static int[] getBytes(long value) {
        int[] bytes = new int[8];

        for(int i = 0; i < 8; ++i) {
            int tmp = (int)(value & 255L);
            bytes[i] = tmp;
            value >>= 8;
        }

        return bytes;
    }

    public static int[] getBytes(int value) {
        int[] bytes = new int[4];

        for(int i = 0; i < 4; ++i) {
            int tmp = value & 255;
            bytes[i] = tmp;
            value >>= 8;
        }

        return bytes;
    }

    public static int[] getBytes(String value) {
        int[] bytes = new int[value.length()];

        for(int i = 0; i < value.length(); ++i) {
            bytes[i] = value.charAt(i);
        }

        return bytes;
    }


    public static int[] merge(int[]... params) {
        int count = 0;

        for(int i = 0; i < params.length; ++i) {
            count += params[i].length;
        }

        int[] result = new int[count];
        count = 0;

        for(int i = 0; i < params.length; ++i) {
            for(int j = 0; j < params[i].length; ++j) {
                result[count++] = params[i][j];
            }
        }

        return result;
    }

    public static int[] bitsStringToBytes(String s) {
        int bytesCount = (s.length() + 7) / 8;
        int[] result = new int[bytesCount];
        int value = 0;
        int index = 0;
        int bitsCount = 0;

        for(int i = 0; i < s.length(); ++i) {
            value <<= 1;
            if (s.charAt(i) == '1') {
                value |= 1;
            }

            ++bitsCount;
            if (bitsCount == 8) {
                result[index++] = value;
                value = 0;
                bitsCount = 0;
            }
        }

        if (bitsCount != 0) {
            value <<= 8 - bitsCount;
            result[index++] = value;
        }

        return result;
    }
}

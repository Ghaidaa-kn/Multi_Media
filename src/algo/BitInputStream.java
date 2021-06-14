//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package algo;

import java.io.IOException;
import java.io.InputStream;

public final class BitInputStream {
    private InputStream input;
    private int nextBits;
    private int numBitsRemaining;
    private boolean isEndOfStream;

    public BitInputStream(InputStream in) {
        if (in == null) {
            throw new NullPointerException("Argument is null");
        } else {
            this.input = in;
            this.numBitsRemaining = 0;
            this.isEndOfStream = false;
        }
    }

    public int readByte() {
        int bitBuffer = 0;
        int c = 0;

        for(int i = 0; i < 8; ++i) {
            try {
                c = this.readBit();
            } catch (IOException var5) {
                var5.printStackTrace();
            }

            bitBuffer |= c;
            if (i != 7) {
                bitBuffer <<= 1;
            }
        }

        return bitBuffer;
    }

    public int readBit() throws IOException {
        if (this.isEndOfStream) {
            return -1;
        } else {
            if (this.numBitsRemaining == 0) {
                this.nextBits = this.input.read();
                if (this.nextBits == -1) {
                    this.isEndOfStream = true;
                    return -1;
                }

                this.numBitsRemaining = 8;
            }

            --this.numBitsRemaining;
            return this.nextBits >>> this.numBitsRemaining & 1;
        }
    }

    public int readInt() throws IOException {
        int value1 = this.input.read();
        int value2 = this.input.read();
        int value3 = this.input.read();
        int value4 = this.input.read();
        if (value1 == -1) {
            value1 = 0;
        }

        if (value2 == -1) {
            value2 = 0;
        }

        if (value3 == -1) {
            value3 = 0;
        }

        if (value4 == -1) {
            value4 = 0;
        }

        return value1 + (value2 << 8) + (value3 << 16) + (value4 << 24);
    }

    public long readLong() throws IOException {
        long result = 0L;
        long power = 0L;

        for(int i = 0; i < 8; ++i) {
            long b = (long)this.input.read();
            if (b == -1L && i == 0) {
                return -9223372036854775808L;
            }

            if (b != -1L) {
                result += b << (int)power;
            }

            power += 8L;
        }

        return result;
    }

}

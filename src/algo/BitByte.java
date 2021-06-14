//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package algo;

import java.io.IOException;
import java.io.OutputStream;

public class BitByte {
    private int bitBuffer = 0;
    private int bitsLeftInBuffer = 0;
    private OutputStream out;

    public BitByte(OutputStream out) {
        if (out == null) {
            throw new NullPointerException("Output stream is invalid.");
        } else {
            this.out = out;
        }
    }

    public void writeBit(boolean bit) {
        this.bitBuffer <<= 1;
        if (bit) {
            this.bitBuffer |= 1;
        }

        ++this.bitsLeftInBuffer;
        if (this.bitsLeftInBuffer == 8) {
            this.flush();
        }

    }

    public void writeByte(int x) {
        if (x >= 0 && x < 256) {
            if (this.bitsLeftInBuffer == 0) {
                try {
                    this.out.write(x);
                } catch (IOException var4) {
                    System.err.println("Write Error");
                    var4.printStackTrace();
                }

            } else {
                for(int i = 0; i < 8; ++i) {
                    boolean bit = (x >>> 8 - i - 1 & 1) == 1;
                    this.writeBit(bit);
                }

            }
        } else {
            throw new IllegalArgumentException("Value not in range.");
        }
    }

    public void flush() {
        if (this.bitsLeftInBuffer != 0) {
            if (this.bitsLeftInBuffer > 0) {
                this.bitBuffer <<= 8 - this.bitsLeftInBuffer;
            }

            try {
                this.out.write(this.bitBuffer);
            } catch (IOException var2) {
                System.err.println("Write Error");
                var2.printStackTrace();
            }

            this.bitsLeftInBuffer = 0;
            this.bitBuffer = 0;
        }
    }

    public void close() {
        try {
            this.out.close();
        } catch (IOException var2) {
            System.err.println("Error closing output stream.");
            var2.printStackTrace();
        }

    }


    public void writeBytes(int[] bytes) {
        int[] var2 = bytes;
        int var3 = bytes.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int b = var2[var4];
            this.writeByte(b);
        }

    }

}

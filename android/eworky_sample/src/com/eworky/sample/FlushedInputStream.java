package com.eworky.sample;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.IOException;

public class FlushedInputStream extends FilterInputStream {

	public FlushedInputStream(InputStream in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	@Override
    public long skip(long n) throws IOException {
        long totalBytesSkipped = 0L;
        while (totalBytesSkipped < n) {
            long bytesSkipped = in.skip(n - totalBytesSkipped);
            if (bytesSkipped == 0L) {
                  int b = read();
                  if (b < 0) {
                      break;  // we reached EOF
                  } else {
                      bytesSkipped = 1; // we read one byte
                  }
           }
            totalBytesSkipped += bytesSkipped;
        }
        return totalBytesSkipped;
    }
}

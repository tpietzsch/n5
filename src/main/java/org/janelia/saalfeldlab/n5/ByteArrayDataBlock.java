/**
 * Copyright (c) 2017, Stephan Saalfeld
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.janelia.saalfeldlab.n5;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import org.janelia.saalfeldlab.n5.readdata.ReadData;

public class ByteArrayDataBlock extends AbstractDataBlock<byte[]> {

	public ByteArrayDataBlock(final int[] size, final long[] gridPosition, final byte[] data) {

		super(size, gridPosition, data);
	}

	@Override
	public void readData(final ByteOrder byteOrder, final ReadData readData) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public ReadData writeData(final ByteOrder byteOrder) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getNumElements() {

		return data.length;
	}

	public static class DefaultCodec implements DataCodec<byte[]> {

		@Override
		public ReadData serialize(final DataBlock<byte[]> dataBlock) throws IOException {
			return ReadData.from(dataBlock.getData());
		}

		@Override
		public void deserialize(final ReadData readData, final DataBlock<byte[]> dataBlock) throws IOException {
			new DataInputStream(readData.inputStream()).readFully(dataBlock.getData());
		}

		static DefaultCodec INSTANCE = new DefaultCodec();
	}

	@Override
	public DataCodec<byte[]> getDataCodec() {
		return DefaultCodec.INSTANCE;
	}
}
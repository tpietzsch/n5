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

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StringDataBlock extends AbstractDataBlock<String[]> {

	protected static final Charset ENCODING = StandardCharsets.UTF_8;
	protected static final String NULLCHAR = "\0";
	protected byte[] serializedData;
	protected String[] actualData;

	public StringDataBlock(final int[] size, final long[] gridPosition, final String[] data) {
		super(size, gridPosition, null);
		actualData = data;
	}

	@Override
	public byte[] serialize(final ByteOrder byteOrder) {
		final String flattenedArray = String.join(NULLCHAR, actualData) + NULLCHAR;
		return flattenedArray.getBytes(ENCODING);
	}

	@Override
	public void deserialize(final ByteOrder byteOrder, final byte[] serialized) {
		serializedData = serialized;
		final String rawChars = new String(serialized, ENCODING);
		actualData = rawChars.split(NULLCHAR);
	}

	@Override
	public void writeData(final ByteOrder byteOrder, final OutputStream outputStream) throws IOException {
		if (serializedData == null) {
			serializedData = serialize(byteOrder);
		}
		outputStream.write(serializedData);
	}

	@Override
	public int getNumElements() {
		if (serializedData == null) {
			serializedData = serialize(ByteOrder.BIG_ENDIAN);
		}
		return serializedData.length;
	}

	@Override
	public String[] getData() {
		return actualData;
	}
}

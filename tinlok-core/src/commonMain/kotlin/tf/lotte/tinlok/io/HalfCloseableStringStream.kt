/*
 * Copyright (C) 2020 Charlotte Skye.
 *
 * This file is part of Tinlok.
 *
 * Tinlok is dually released under the GNU Lesser General Public License,
 * Version 3 or later, or the Mozilla Public License 2.0.
 */

package tf.lotte.tinlok.io

/**
 * Represents a half-closeable stream that can have strings read/written from/to it.
 */
public interface HalfCloseableStringStream : BidirectionalStringStream, HalfCloseableStream

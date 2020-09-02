/*
 * Copyright (C) 2020 Charlotte Skye.
 *
 * This file is part of KSTE.
 *
 * KSTE is dually released under the GNU Lesser General Public License,
 * Version 3 or later, or the Mozilla Public License 2.0.
 */

package tf.lotte.kste.io

/**
 * Represents any stream that operates in both directions (it is both readable and writeable).
 */
public interface BidirectionalStream : Readable, Writeable, Closeable
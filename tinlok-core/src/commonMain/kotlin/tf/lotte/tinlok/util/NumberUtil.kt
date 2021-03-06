/*
 * Copyright (C) 2020 Charlotte Skye.
 *
 * This file is part of Tinlok.
 *
 * Tinlok is dually released under the GNU Lesser General Public License,
 * Version 3 or later, or the Mozilla Public License 2.0.
 */

@file:OptIn(ExperimentalUnsignedTypes::class)
@file:Suppress("unused")

package tf.lotte.tinlok.util

// safe conversion
/**
 * Gets the upper byte of this Int.
 */
public inline val Int.upperByte: Int
    get() = (this shr 24) and 0xFF

/**
 * Gets the upper byte of this UInt.
 */
public inline val UInt.upperByte: UInt
    get() = (this shr 24) and 0xFFu

/**
 * Gets the lower byte of this Int.
 */
public inline val Int.lowerByte: Int
    get() = this and 0xFF

/**
 * Gets the lower byte of this UInt.
 */
public inline val UInt.lowerByte: UInt
    get() = this and 0xFFu

/**
 * Gets the second byte of this Int.
 */
public inline val Int.byte2: Int
    get() = (this shr 16) and 0xFF


/**
 * Gets the second byte of this UInt.
 */
public inline val UInt.byte2: UInt
    get() = (this shr 16) and 0xFFu


/**
 * Gets the third byte of this Int.
 */
public inline val Int.byte3: Int
    get() = (this shr 8) and 0xFF

/**
 * Gets the third byte of this UInt.
 */
public inline val UInt.byte3: UInt
    get() = (this shr 8) and 0xFFu


/**
 * Decodes this int into a [ByteArray] in big endian mode.
 */
public inline fun Int.toByteArray(): ByteArray {
    return byteArrayOf(upperByte.toByte(), byte2.toByte(), byte3.toByte(), lowerByte.toByte())
}

/**
 * Decodes this uint into a ByteArray in big endian mode.
 */
@OptIn(ExperimentalUnsignedTypes::class)  // ?
public inline fun UInt.toByteArray(): ByteArray {
    return byteArrayOf(upperByte.toByte(), byte2.toByte(), byte3.toByte(), lowerByte.toByte())
}


// wtf past me
// this was this[0], this[1], this[0], this[0]
/**
 * Decodes a size-4 byte array to an int in big endian mode.
 */
public inline fun ByteArray.toInt(): Int {
    return (((this[0].toInt()) shl 24)
        or ((this[1].toInt()) shl 16)
        or ((this[2].toInt()) shl 8)
        or (this[3].toInt())
        )
}

/**
 * Decodes a size-4 unsigned byte array to a uint in big endian mode.
 */
public inline fun UByteArray.toUInt(): UInt {
    return (((this[0].toUInt()) shl 24)
        or ((this[1].toUInt()) shl 16)
        or ((this[2].toUInt()) shl 8)
        or (this[3].toUInt())
        )
}

/**
 * Gets the upper byte of this Long.
 */
public inline val Long.upperByte: Long
    get() = ((this.toULong()) and 0xFF00000000000000UL).toLong()

/**
 * Gets the lower byte of this Long.
 */
public inline val Long.lowerByte: Long
    get() = (this and 0x00000000000000FF)

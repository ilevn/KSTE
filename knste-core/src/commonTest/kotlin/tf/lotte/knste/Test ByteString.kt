/*
 * Copyright (C) 2020 Charlotte Skye.
 *
 * This file is part of KNSTE.
 *
 * KNSTE is dually released under the GNU Lesser General Public License,
 * Version 3 or later, or the Mozilla Public License 2.0.
 */

package tf.lotte.knste

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests [ByteString] functionality.
 */
class `Test ByteString` {
    @Test
    fun `Test ByteString split basic`() {
        val bs = b("abc, def, ghi, jkl")
        val split = bs.split(b(", "))

        // standard smoketest
        assertEquals(split.size, 4)
        assertEquals(split[2], b("ghi"))
        // ensure last item is properly copied
        assertEquals(split.last(), b("jkl"))
    }

    @Test
    fun `Test ByteString split with leading delimiter`() {
        val bs = b(",abc,def,ghi")
        val split = bs.split(b(","))

        assertEquals(split.size, 4)
        assertTrue(split.first().isEmpty())
    }

    @Test
    fun `Test ByteString split with no matches`() {
        val bs = b("A-Set, you bet!")
        val split = bs.split(b("."))

        assertEquals(split.size, 1)
        assertEquals(split.first(), bs)
    }

}
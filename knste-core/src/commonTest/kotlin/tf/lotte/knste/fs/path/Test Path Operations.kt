/*
 * Copyright (C) 2020 Charlotte Skye.
 *
 * This file is part of KNSTE.
 *
 * KNSTE is dually released under the GNU Lesser General Public License,
 * Version 3 or later, or the Mozilla Public License 2.0.
 */

@file:Suppress("MemberVisibilityCanBePrivate")

package tf.lotte.knste.fs.path

import tf.lotte.knste.Sys
import tf.lotte.knste.exc.OSException
import tf.lotte.knste.fs.StandardOpenModes
import tf.lotte.knste.util.Unsafe
import kotlin.test.*

/**
 * Tests path modification operators.
 */
class `Test Path Operations` {
    /**
     * "Touch"es (creates empty) a file.
     */
    private fun Path.touch() {
        open(StandardOpenModes.CREATE, StandardOpenModes.WRITE) {}
    }

    @Test
    fun `Test mkdir`() = Paths.makeTempDirectory("knste-test-") {
        val newPath = it.join("mkdir-test")
        newPath.createDirectory(parents = false, existOk = false)

        assertTrue(newPath.exists())
        assertTrue(newPath.isDirectory())
    }

    @Test
    fun `Test owner`() = Paths.makeTempDirectory("knste-test-") {
        val username = Sys.getUsername()
        assertEquals(it.owner(), username)
    }


    @Test
    fun `Test stat`() = Paths.makeTempDirectory("knste-test-") {
        assertTrue(it.isDirectory())
        assertFalse(it.isRegularFile())

        run {
            val file = it.join("file")
            assertFalse(file.isRegularFile())

            file.touch()
            assertTrue(file.exists())
            assertTrue(file.isRegularFile())
            assertEquals(file.size(), 0L)

            file.unlink()
            assertFalse(file.isRegularFile())
        }

        run {
            val file = it.join("file2")

            file.writeString("abcdef")
            assertTrue(file.exists())
            assertEquals(file.size(), 6)
        }
    }

    @Unsafe
    @Test
    fun `Test rename`() = Paths.makeTempDirectory("knste-test-") {
        val first = it.join("test1.txt")
        val second = it.join("test2.txt")

        first.touch()
        first.rename(second)

        assertFalse(first.exists())
        assertTrue(second.exists())

        second.delete()
        first.createDirectory(parents = false, existOk = false)
        first.rename(second)

        assertFalse(first.exists())
        assertTrue(second.isDirectory(followSymlinks = false))
    }

    @Unsafe
    @Test
    fun `Test copy`() = Paths.makeTempDirectory("knste-test-") {
        val first = it.join("test1.txt")
        val second = it.join("test2.txt")

        val toWrite = "happy cirno day 9/9/2020"
        first.writeString(toWrite)
        first.copy(second)

        assertTrue(first.exists())
        assertTrue(second.exists())
        assertEquals(first.readAllString(), toWrite)
    }
}

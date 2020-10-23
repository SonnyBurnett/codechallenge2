package com.ing.challenge.parser

import java.io.File
import java.io.IOException

interface Parsable {
    fun toCsv(file: File)
}


/**
 * A [com.ing.challenge.parser.Parser] let's you read and write files of different types.
 * @param <T> A object type
</T> */
interface Parser<T : Parsable> {
    /**
     * Read lines from a file.
     * @param fileName name of the file
     * @return A [List] of Object of type T
     * @see Parser.read
     */
    fun read(fileName: String): List<T>

    /**
     * Read lines from a [File].
     * @param file A [File] object relating to A file on the system
     * @return A [List] of Object of type T
     * @see Parser.read
     */
    fun read(file: File): List<T>

    @Throws(IOException::class)
    fun write(file: File, obj: T): Boolean

    @Throws(IOException::class)
    fun write(file: File, objects: List<T>): Boolean
}


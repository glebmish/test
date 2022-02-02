package signature

import java.util.*
import kotlin.String as String

fun main() {
    val scanner = Scanner(System.`in`)

    val fullName = "${ scanner.next() } ${ scanner.next() }"
    var status = ""
    while (scanner.hasNext()) {
        status = scanner.nextLine()
    }
    val arrayOfBigName = bigString(fullName, roman, "          ")
    val arrayOfBigStatus = bigString(status, medium, "     ")
    val symbols = middleOfSign(arrayOfBigName[0].length, arrayOfBigStatus[0].length)

    println("Enter name and surname: $fullName")
    println("Enter person's status: $status")
    println(symbols[0])
    for (i in 0..arrayOfBigName.lastIndex) {
        println("88" + symbols[1] + arrayOfBigName[i] + symbols[2] + "88")
    }
    for (j in 0..arrayOfBigStatus.lastIndex) {
        println("88" + symbols[3] + arrayOfBigStatus[j]+ symbols[4] + "88")
    }
    println(symbols[0])
}

fun bigString(string: String, font: String, space: String): Array<String> {
    val scannerOfFile = Scanner(font)
    val sizeOfFont = scannerOfFile.nextInt()
    val numOfLetters = scannerOfFile.nextInt()
    scannerOfFile.nextLine()
    val arrayOfFont: Array<Array<String>> = Array(numOfLetters) {
        scannerOfFile.nextLine()
        Array(sizeOfFont) { scannerOfFile.nextLine() }
    }

    var arrayOfString = emptyArray<String>()

    for (i in 0..string.lastIndex) {
        if (string[i] == ' ') {
            repeat (sizeOfFont) {
                arrayOfString += space

            }
            continue
        }
        arrayOfString += if (string[i].isLowerCase()) {
            arrayOfFont[string[i].toInt() - 97]
        } else {
            arrayOfFont[(string[i].toInt() - 65) + 26]
        }
    }

    val result = Array(sizeOfFont) {""}
    for (numOfLine in 0 until sizeOfFont) { // 1
        for (j in numOfLine..arrayOfString.lastIndex step sizeOfFont) {
            result[numOfLine] += arrayOfString[j]
        }
    }
    return result
}

fun middleOfSign(name: Int, status: Int): Array<String> {
    val lengthOfFramesSymbol: Int
    val lengthOfSpacesNameRight: Int
    val lengthOfSpacesStatusRight: Int
    val lengthOfSpacesNameLeft: Int
    val lengthOfSpacesStatusLeft: Int
    if (name >= status) {
        lengthOfFramesSymbol = name + 8
        lengthOfSpacesNameLeft = 2
        lengthOfSpacesNameRight = 2
        if ((name % 2 == 0 && status % 2 != 0) xor (name % 2 != 0 && status % 2 == 0)) {
            lengthOfSpacesStatusLeft = (lengthOfFramesSymbol - 4 - status) / 2
            lengthOfSpacesStatusRight = (lengthOfFramesSymbol - 4 - status) / 2 + 1
        } else {
            lengthOfSpacesStatusLeft = (lengthOfFramesSymbol - 4 - status) / 2
            lengthOfSpacesStatusRight = lengthOfSpacesStatusLeft
        }
    } else {
        lengthOfFramesSymbol = status + 8
        lengthOfSpacesStatusLeft = 2
        lengthOfSpacesStatusRight = 2
        if ((name % 2 == 0 && status % 2 != 0) xor (name % 2 != 0 && status % 2 == 0)) {
            lengthOfSpacesNameLeft = (lengthOfFramesSymbol - 4 - name) / 2
            lengthOfSpacesNameRight = (lengthOfFramesSymbol - 4 - name) / 2 + 1
        } else {
            lengthOfSpacesNameLeft = (lengthOfFramesSymbol - 4 - name) / 2
            lengthOfSpacesNameRight = lengthOfSpacesNameLeft
        }
    }
    var framesSymbol = ""
    repeat(lengthOfFramesSymbol) {
        framesSymbol += "8"
    }
    var spacesNameRight = ""
    repeat(lengthOfSpacesNameRight) {
        spacesNameRight += " "
    }
    var spacesNameLeft = ""
    repeat(lengthOfSpacesNameLeft) {
        spacesNameLeft += " "
    }
    var spacesStatusRight = ""
    repeat(lengthOfSpacesStatusRight) {
        spacesStatusRight += " "
    }
    var spacesStatusLeft = ""
    repeat(lengthOfSpacesStatusLeft) {
        spacesStatusLeft += " "
    }
    return arrayOf(framesSymbol, spacesNameLeft, spacesNameRight, spacesStatusLeft, spacesStatusRight)
}
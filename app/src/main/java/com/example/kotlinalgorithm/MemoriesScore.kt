package com.example.kotlinalgorithm

fun main(){

    println(memorySolution(arrayOf("may", "kein", "kain", "radi"), intArrayOf(5, 10, 1, 3), arrayOf(
        arrayOf("may", "kein", "kain", "radi"), arrayOf("may", "kein", "brin", "deny"), arrayOf("kon", "kain", "may", "coni")
    )).contentToString())

}

/**
 *
 * 사진속에 있는 그리움점수가 없는 사람은 0으로 처리
 * 나머진 yearning에 있는 점수로 처리
 *
 */

fun memorySolution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray =
    photo.map { calls -> calls.sumOf { call-> if (name.indexOf(call) == -1) 0 else yearning[name.indexOf(call)] } }.toIntArray()

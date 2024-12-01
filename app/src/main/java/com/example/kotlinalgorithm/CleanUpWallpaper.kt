package com.example.kotlinalgorithm

fun main(){
    println(cleanUpSolution(arrayOf("..", "#.")).contentToString())
}

fun cleanUpSolution(wallpaper: Array<String>): IntArray {

    val x = mutableListOf<Pair<Int, Int>>()

    wallpaper.forEachIndexed { index, s ->
        for (i in s.indices){
            if (s[i]=='#'){
                x.add(Pair(index, i))
            }
        }
    }

    /**
     *
     * 드래그 범위가 x,y 가 가장 작은 곳 ~ x,y 가 가장 큰 곳
     * 단, 사각형 범위이기 떄문에 가장 큰 곳에서 x, y의 값에서 +1을 해 주어야함.
     *
     */

    return intArrayOf(x.minOf { it.first }, x.minOf { it.second }, x.maxOf { it.first } + 1, x.maxOf { it.second } + 1)
}
package com.example.kotlinalgorithm

fun main(){

    val diffs = intArrayOf(1, 328, 467, 209, 54)
    val times = intArrayOf(2, 7, 1, 4, 3)
    val limit = 1723L

    println(puzzleSolution(diffs, times, limit))

}

fun puzzleSolution(diffs: IntArray, times: IntArray, limit: Long): Int {
    //diffs 의 0 번째 값은 1으로 고정 되어 있음
    var left = 1L
    var right = limit

    while(left <= right){
        val mid = (left + right) / 2
        if (checkPuzzle(mid, diffs, times, limit)){
            right = mid - 1
        }else{
            left = mid + 1
        }
    }

    return left.toInt()
}

// 순차적 탐색 시간 초과 실패
//private fun checkPuzzle(min: Int, max: Int, diffs: IntArray, times: IntArray, limit: Long){
//
//    for (currentLevel in min..max){
//        var duration = 0L
//        diffs.forEachIndexed { index, puzzleLevel ->
//            duration += if(puzzleLevel >= currentLevel){
//                if(index - 1 >= 0) (puzzleLevel - currentLevel) * (times[index] + times[index - 1]) + times[index]
//                else (puzzleLevel - currentLevel) * times[index] + times[index]
//            }else{
//                times[index]
//            }
//        }
//
//        if (duration <= limit){
//            completeLevel.add(currentLevel)
//        }
//    }
//}

private fun checkPuzzle(level: Long, diffs: IntArray, times: IntArray, limit: Long): Boolean{

    var duration = 0L
    diffs.forEachIndexed { index, diff ->
        if (diff <= level){
            duration += times[index]
        }else{
            duration += if (index != 0) {
                (diff - level) * (times[index] + times[index - 1]) + times[index]
            }else{
                (diff - level) * times[index] + times[index]
            }
        }

        if (duration > limit) return@forEachIndexed
    }

    return duration <= limit
}
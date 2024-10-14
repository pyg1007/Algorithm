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

    //이분탐색을 이용
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
        /**
         *
         * diff ≤ level이면 퍼즐을 틀리지 않고 time_cur만큼의 시간을 사용하여 해결합니다
         *
         */
        if (diff <= level){
            duration += times[index]
        }else{
            /**
             *
             * diff > level이면, 퍼즐을 총 diff - level번 틀립니다. 퍼즐을 틀릴 때마다, time_cur만큼의 시간을 사용하며, 추가로 time_prev만큼의 시간을 사용해 이전 퍼즐을 다시 풀고 와야 합니다. 이전 퍼즐을 다시 풀 때는 이전 퍼즐의 난이도에 상관없이 틀리지 않습니다. diff - level번 틀린 이후에 다시 퍼즐을 풀면 time_cur만큼의 시간을 사용하여 퍼즐을 해결합니다.
             *
             */
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
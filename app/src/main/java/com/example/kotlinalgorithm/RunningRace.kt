package com.example.kotlinalgorithm

fun main(){

    runningSolution(arrayOf("mumu", "soe", "poe", "kai", "mine"), arrayOf("kai", "kai", "mine", "mine"))

}

/**
 *
 * 단순하게 리스트에 추가삭제하는 방식은 시간초과
 *
 * 리스트 값 비교해서 스왑하는 방식도 시간초과
 *
 * Hash맵을 이용해서 이름 순위 순서로 저장한 후 순위로 정렬한뒤 이름만 표시하는 방식으로 통과
 */

fun runningSolution(players: Array<String>, callings: Array<String>): Array<String> {

    val playersMap = players.withIndex().associate { it.value to it.index }.toMutableMap()

    for (calling in callings){
        val currentPlayerIndex = playersMap[calling] ?: continue // 값이 없는 경우는 없지만 값이 없다면 해당 값은 넘어가는 방식으로 구현

        // 해쉬맵의 값을 변화
        playersMap[players[currentPlayerIndex - 1]] = currentPlayerIndex
        playersMap[players[currentPlayerIndex]] = currentPlayerIndex - 1

        // players의 값을 변화
        val temp = players[currentPlayerIndex - 1]
        players[currentPlayerIndex - 1] = players[currentPlayerIndex]
        players[currentPlayerIndex] = temp
    }

    return playersMap.toList().sortedBy { it.second }.map { it.first }.toTypedArray()
}
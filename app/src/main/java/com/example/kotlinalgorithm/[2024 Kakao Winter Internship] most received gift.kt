package com.example.kotlinalgorithm

fun main(){

    val friends = arrayOf("muzi", "ryan", "frodo", "neo")
    val gifts = arrayOf("muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi")
    receivedSolution(friends, gifts)
}

fun receivedSolution(friends: Array<String>, gifts: Array<String>): Int {
    var answer: Int = 0

    val futuresIndices = mutableMapOf<String, Int>()
    futuresIndices.putAll(friends.associateWith { 0 })
    val futures = mutableMapOf<String, Int>()
    // 선물지수 계산
    gifts.forEach {
        val gift = it.split(" ")
        futuresIndices[gift[0]] = futuresIndices.getOrDefault(gift[0], 0) + 1
        futuresIndices[gift[1]] = futuresIndices.getOrDefault(gift[1], 0) - 1
        futures[it] = futures.getOrDefault(it, 0) + 1
    }

    friends.forEach { friend ->
        futures.filter { it.key.split(" ")[0] == friend }.forEach { (s, i) ->
            val a = s.split(" ")[0]
            val b = s.split(" ")[1]
            val gift = futures["$b $a"] ?: 0
            if (i > gift){
                futuresIndices[a] = futuresIndices.getOrDefault(a, 0) - 1
                futuresIndices[b] = futuresIndices.getOrDefault(b, 0) + 1
            }else if (i == gift){
                if ((futuresIndices[a] ?: 0) > (futuresIndices[b] ?: 0)){

                }
            }
            println("$friend $s $i")
        }
    }

    return answer
}
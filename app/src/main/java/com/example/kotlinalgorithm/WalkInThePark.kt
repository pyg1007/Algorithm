package com.example.kotlinalgorithm

fun main(){

    println(walkSolution(arrayOf("OSO","OOO","OXO","OOO"), arrayOf("E 2","S 3","W 1")).contentToString())

}

/**
 *
 * 장애물(X)를 만나면 명령 불이행
 * 맵(공원)을 벗어나면 명령 불이행
 * 시작 위치 S
 * 명령 이행 후 최종 위치 리턴
 *
 */

fun walkSolution(park: Array<String>, routes: Array<String>): IntArray {
    val location = intArrayOf(0, 0)

    val height = park.size
    val width = park[0].length

    // 시작 위치 찾기
    for (i in park.indices){
        for (j in park[i].indices) {
            if (park[i][j] == 'S') {
                location[0] = i
                location[1] = j
            }
        }
    }

    routes.forEach {
        val route = it.split(" ").toTypedArray()
        walk(height, width, location, route, park)
    }

    return location
}

private fun walk(height: Int, width: Int, location: IntArray, route: Array<String>, park: Array<String>){

    var x = location[1]
    var y = location[0]

    // 위치 이동
    for(walk in 1..route[1].toInt()){
        when(route[0]){
            "E" -> {
                if (x + 1 >= width || park[y][x + 1] == 'X') return
                x += 1
            }
            "W" -> {
                if (x - 1 < 0 || park[y][x - 1] == 'X' ) return
                x -= 1
            }
            "N" -> {
                if ( y - 1 < 0 || park[y - 1][x] == 'X') return
                y -= 1
            }
            "S" -> {
                if ( y + 1 >= height || park[y + 1][x] == 'X') return
                y += 1
            }
        }
    }
    // 장애물을 만나지 않고 이동한 최종 위치 변경
    location[0] = y
    location[1] = x
}
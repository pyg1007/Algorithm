package com.example.kotlinalgorithm

fun main(){

    val users = arrayOf(intArrayOf(40, 2900), intArrayOf(23, 10000), intArrayOf(11, 5200), intArrayOf(5, 5900), intArrayOf(40, 3100), intArrayOf(27, 9200), intArrayOf(32, 6900))
    val emoticons = intArrayOf(1300, 1500, 1600, 4900)

    println(emoticonSolution(users, emoticons).contentToString())

}

/**
 *
 *  할인율은 10% 20% 30% 40% 중 하나
 *
 */

fun emoticonSolution(users: Array<IntArray>, emoticons: IntArray): IntArray {
    // 이모티콘플러스 , 이모티콘 구매비용
    val answer = ArrayList<Pair<Int, Int>>()
    // 할인율은 10% 20% 30% 40% 중 하나
    val disCountRate = arrayListOf(1, 2, 3, 4)
    // 할인율 경우의 수를 저장할 리스트
    val disCountResult = ArrayList<ArrayList<Int>>()
    // 경우의 수 구하기
    backTrackingDisCount(disCountRate, disCountRate.size, emoticons.size, disCountResult, arrayListOf())

    for(index in disCountResult.indices){
        var first = 0
        var second = 0
        for (user in users){
            var price = 0
            // 물품 할인율이 유저가 설정한 할인율보다 크다면 모두 구매
            disCountResult[index].forEachIndexed { resultIndex, discount ->
                if (discount * 10 >= user[0]){
                    price += emoticons[resultIndex] - (emoticons[resultIndex] * (discount * 0.1)).toInt()
                }
            }
            // 이모티콘 구매비용이 설정한 금액보다 비싼경우 이모티콘 플러스에 가입
            if (price >= user[1]){
                first++
            }else{
                //아닌경우 비용 증가
                second += price
            }
        }
        answer.add(Pair(first, second))
    }

    // 이모티콘 플러스가입자가 가장많아야하고, 그이후로는 비용이 가장 커야하기 떄문에 소트를 해준 후 첫번째 값을 가져옮
    return answer.sortedWith(compareBy<Pair<Int,Int>> { -it.first }.thenBy{ -it.second})[0].toList().toIntArray()
}

private fun backTrackingDisCount(disCount: ArrayList<Int>, n: Int, r: Int, result: ArrayList<ArrayList<Int>>, temp : ArrayList<Int>){

    if (temp.size == r){
        result.add(temp.clone() as ArrayList<Int>)
        return
    }else if(temp.size > r) return
    for (i in 0 until disCount.size){
        temp.add(disCount[i])
        backTrackingDisCount(disCount, n, r, result, temp)
        temp.removeLast()
    }

}
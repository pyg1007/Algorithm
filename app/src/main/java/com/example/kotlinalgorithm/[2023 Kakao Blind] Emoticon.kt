package com.example.kotlinalgorithm

fun main(){

    val users = arrayOf(intArrayOf(40, 2900), intArrayOf(23, 10000), intArrayOf(11, 5200), intArrayOf(5, 5900), intArrayOf(40, 3100), intArrayOf(27, 9200), intArrayOf(32, 6900))
    val emoticons = intArrayOf(1300, 1500, 1600, 4900)

    println(emoticonSolution(users, emoticons).contentToString())

}

private lateinit var disCount: ArrayList<Int>

/**
 *
 *  할인율은 10% 20% 30% 40% 중 하나
 *
 */

fun emoticonSolution(users: Array<IntArray>, emoticons: IntArray): IntArray {
    var answer: IntArray = intArrayOf()
    disCount = arrayListOf(1, 2, 3, 4)
    val result = ArrayList<ArrayList<Int>>()
    backTrackingDisCount(disCount.size, emoticons.size, result, arrayListOf())
    println(result)
    return answer
}

private fun backTrackingDisCount(n: Int, r: Int, result: ArrayList<ArrayList<Int>>, temp : ArrayList<Int>){

    if (temp.size == r){
        result.add(temp.clone() as ArrayList<Int>)
        return
    }else if(temp.size > r) return
    for (i in 0 until disCount.size){
        temp.add(disCount[i])
        backTrackingDisCount(n, r, result, temp)
        temp.removeLast()
    }

}
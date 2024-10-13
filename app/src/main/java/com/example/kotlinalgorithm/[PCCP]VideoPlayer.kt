package com.example.kotlinalgorithm

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

val simpleDateFormat = SimpleDateFormat("mm:ss", Locale.getDefault())

fun main(){
    println(videoSolution("10:00", "00:03", "00:00", "00:05", arrayOf("prev", "next")))
}

fun videoSolution(video_len: String, pos: String, op_start: String, op_end: String, commands: Array<String>): String {

    val videoDate = simpleDateFormat.parse(video_len)!!
    var currentDate = simpleDateFormat.parse(pos)!!

    // 첫 시작 시간이 00:00보다 작은지 또는 오프닝 시간 인지 체크
    currentDate = checkDate(currentDate, op_start, op_end)

    // command에 따라 시간 변화
    commands.forEach { command ->
        currentDate = if (command == "prev"){
            minusDate(currentDate)
        }else{
            addDate(currentDate)
        }

        // 시간이 00:00보다 작은지 또는 오프닝 시간 인지 체크
        currentDate = checkDate(currentDate, op_start, op_end)

        // 현재 시간이 비디오 시간 보다 크다면 비디오 시간 으로 변환
        if (currentDate.after(videoDate)){
            currentDate = videoDate
        }
    }

    return simpleDateFormat.format(currentDate)
}

/**
 *
 * 10초 후로 이동: 사용자가 "next" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 후로 이동합니다. 동영상의 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동합니다. 영상의 마지막 위치는 동영상의 길이와 같습니다.
 *
 */

private fun addDate(date: Date) : Date{
    val calendar = Calendar.getInstance().apply {
        time = date
    }
    calendar.add(Calendar.SECOND, 10)
    return calendar.time
}

/**
 *
 * 10초 전으로 이동: 사용자가 "prev" 명령을 입력할 경우 동영상의 재생 위치를 현재 위치에서 10초 전으로 이동합니다. 현재 위치가 10초 미만인 경우 영상의 처음 위치로 이동합니다. 영상의 처음 위치는 0분 0초입니다.
 *
 */

private fun minusDate(date: Date) : Date{
    val calendar = Calendar.getInstance().apply {
        time = date
    }
    calendar.add(Calendar.SECOND, -10)
    return calendar.time
}

private fun checkDate(date: Date, op_start: String, op_end: String) : Date{

    var currentDate = date
    val zeroDate = simpleDateFormat.parse("00:00")!!
    val opStartDate = simpleDateFormat.parse(op_start)!!
    val opEndDate = simpleDateFormat.parse(op_end)!!

    /**
     *
     * 00:00 보다 작다면 00:00으로 변환
     *
     */
    if (currentDate < zeroDate){
        currentDate = zeroDate
    }
    /**
     *
     * 오프닝 건너뛰기: 현재 재생 위치가 오프닝 구간(op_start ≤ 현재 재생 위치 ≤ op_end)인 경우 자동으로 오프닝이 끝나는 위치로 이동합니다.
     *
     */
    if (currentDate in opStartDate..opEndDate){
        currentDate = opEndDate
    }

    return currentDate
}
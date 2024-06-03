package com.wtfih.heartstitcher.data

fun calculateMealTimes(wake1: Int, wake2: Boolean, asleep1: Int, asleep2: Boolean): List<Int> {
    val wakeTime = if (wake2) wake1 else wake1 + 12
    val asleepTime = if (asleep2) asleep1 + 12 else asleep1

    val totalTimeAwake = asleepTime - wakeTime
    val mealIntervals = totalTimeAwake / 5

    val mealTimes = mutableListOf<Int>()
    for (i in 1..4) {
        mealTimes.add(wakeTime + (i * mealIntervals))
    }

    return mealTimes
}

fun calculateWaterTimes(wake1: Int, wake2: Boolean, asleep1: Int, asleep2: Boolean): List<Int> {
    val wakeTime = if (wake2) wake1 else wake1 + 12
    val asleepTime = if (asleep2) asleep1 + 12 else asleep1

    val totalTimeAwake = asleepTime - wakeTime
    val waterIntervals = totalTimeAwake / 9

    val waterTimes = mutableListOf<Int>()
    for (i in 1..8) {
        waterTimes.add(wakeTime + (i * waterIntervals))
    }

    return waterTimes
}

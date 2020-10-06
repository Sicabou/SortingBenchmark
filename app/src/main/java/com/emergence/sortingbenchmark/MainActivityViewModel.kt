package com.emergence.sortingbenchmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emergence.sortingbenchmark.algorithms.bubbleSort
import com.emergence.sortingbenchmark.algorithms.selectionSort
import com.emergence.sortingbenchmark.model.Algorithm
import kotlin.random.Random
import kotlin.system.measureTimeMillis

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel : ViewModel() {
    private val names: List<String> = listOf(
        "Selection sort",
        "Bubble sort",
        "Merge sort",
        "Quick Sort"
    )
    var allAlgorithms: MutableList<Algorithm>
    var arr: IntArray

    init {
        allAlgorithms = initializeAllAlgo()
        arr = intArrayOf(1,2,3)
    }

    private fun initializeAllAlgo(): MutableList<Algorithm> {
        val tempAlgoList = mutableListOf<Algorithm>()
        for (i in names) {
            tempAlgoList.add(Algorithm(i))
        }
        return tempAlgoList
    }

    private var _arrayIsGenerated = MutableLiveData<Boolean>(true)
    val arrayIsGenerated: LiveData<Boolean>
        get() = _arrayIsGenerated

    fun createAnArray(size: Int): IntArray {
        _arrayIsGenerated.value = false
        arr = IntArray(size) { Random.nextInt() }
        _arrayIsGenerated.value = true
        return arr
    }

    private var executionTime: Long = 0

    fun startBench() {
        for ((i,v) in names.withIndex()) {
            val tempArr = IntArray(arr.size)
            for ((index,value) in arr.withIndex()) {
                tempArr[index] = value
            }
            executionTime = measureTimeMillis {
                superSort(v,tempArr)
            }
            allAlgorithms[i].time = executionTime.toString()
        }
    }

    private fun superSort(algo: String, arr: IntArray) {
        when (algo) {
            "Selection sort" -> selectionSort(arr)
            "Bubble sort" -> bubbleSort(arr)
            "Merge sort" -> arr.sort()
        }
    }
}



//    enum class AlgorithmsNames {
//        SELECTION,
//        BUBBLE,
//        BUBBLE_RECURSIVE,
//        INSERTION,
//        INSERTION_RECURSIVE,
//        INSERTION_BINARY,
//        MERGE,
//        MERGE_ITERATIVE,
//        QUICK,
//        QUICK_ITERATIVE,
//        HEAP,
//        COUNTING,
//        RADIX,
//        BUCKET,
//        SHELL,
//        TIM,
//        COMB,
//        PIGEONHOLE,
//        CYCLE,
//        COCKTAIL,
//        STRAND,
//        BITONIC,
//        PANCAKE,
//        PERMUTATION,
//        GNOME
//    }

//private val names = listOf(
//    "Selection sort",
//    "Bubble sort",
//    "Recursive Bubble sort",
//    "Insertion sort",
//    "Recursive Insertion sort",
//    "Binary Insertion sort",
//    "Merge sort",
//    "Iterative Merge sort",
//    "Quick Sort",
//    "Iterative Quick Sort",
//    "Heap sort",
//    "Counting sort",
//    "Radix sort",
//    "Bucket sort",
//    "Shell sort",
//    "Tim sort",
//    "Comb sort",
//    "Pigeonhole sort",
//    "Cycle sort",
//    "Cocktail sort",
//    "Strand sort",
//    "Bitonic sort",
//    "Pancake sort",
//    "Permutation sort",
//    "Gnome sort"
//)
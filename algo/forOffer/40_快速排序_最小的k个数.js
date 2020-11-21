// 1. 使用js的函数 (我自己)
let getLeastNumbers_1 = function (arr, k) {
    arr.sort((a, b) => {
        if (a > b) {
            return 1
        } else if (a < b) {
            return -1
        } else {
            return 0
        }
    })
    return arr.slice(0, k)
}

// 2. 使用 快速排序
let quit_sort = () => {

    let quit_sort_get_mid = (arr, left, right) => {
        let pivot = arr[Math.ceil(Math.random() * (right - left + 1)) + left]
        while (left < right) {

            while (left < right && arr[right] >= pivot) right--
            arr[left] = arr[right]

            while (left < right && arr[left] <= pivot) left++
            arr[right] = arr[left]

        }

        arr[left] = pivot

        return left
    }

    let quit_sort = (arr = [], left, right) => {
        if (left < right) {
            let mid = quit_sort_get_mid(arr, left, right)
            quit_sort(arr, left, mid - 1)
            quit_sort(arr, mid + 1, right)
        }
    }

    let quit_sort_getLeastNumbers = function (arr, k) {
        quit_sort(arr, 0, arr.length - 1)
        return arr.splice(0, k)
    }

}

let list = [
    0,
    1,
    1,
    1,
    4,
    5,
    3,
    7,
    7,
    8,
    10,
    2,
    7,
    8,
    0,
    5,
    2,
    16,
    12,
    1,
    19,
    15,
    5,
    18,
    2,
    2,
    22,
    15,
    8,
    22,
    17,
    6,
    22,
    6,
    22,
    26,
    32,
    8,
    10,
    11,
    2,
    26,
    9,
    12,
    9,
    7,
    28,
    33,
    20,
    7,
    2,
    17,
    44,
    3,
    52,
    27,
    2,
    23,
    19,
    56,
    56,
    58,
    36,
    31,
    1,
    19,
    19,
    6,
    65,
    49,
    27,
    63,
    29,
    1,
    69,
    47,
    56,
    61,
    40,
    43,
    10,
    71,
    60,
    66,
    42,
    44,
    10,
    12,
    83,
    69,
    73,
    2,
    65,
    93,
    92,
    47,
    35,
    39,
    13,
    75
]
let key = 75

console.log(getLeastNumbers(list, key))

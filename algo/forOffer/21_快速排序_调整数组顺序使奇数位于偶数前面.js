/**
 * @param {number[]} nums
 * @return {number[]}
 */
var exchange_byMe = function (nums) {
    let prefix = []
    let suffix = []
    nums.forEach(l => {
        if (l % 2 === 0) {
            prefix.push(l)
        } else {
            suffix.push(l)
        }
    })
    return suffix.concat(prefix)
}

var exchange = function (nums) {
    let left = 0
    let right = nums.length - 1

    while (left < right) {

        while (left < right && nums[left] % 2 !== 0) {
            left++
        }

        while (left < right && nums[right] % 2 === 0) {
            right--
        }

        if (left < right) {
            [nums[left], nums[right]] = [nums[right], nums[left]]
        }


    }

    return nums
}


let nums = [1, 2, 3, 4]
console.log(exchange(nums))

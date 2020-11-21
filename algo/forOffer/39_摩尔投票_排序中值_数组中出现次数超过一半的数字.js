/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement_1 = function(nums) {
    let length = nums.length
    let goalNum = Math.floor(length / 2) + (length % 2)
    let temp = {}

    for (let key in nums) {
        if (temp[nums[key]] === undefined) temp[nums[key]] = 0
        temp[nums[key]]++
        if (temp[nums[key]] >= goalNum) {
            return nums[key]
        }
    }
    return 0
}

var majorityElement = function(nums) {
    let count = 0
    let card = null
    for (let num of nums) {
        if (count == 0) card = num
        count += card === num ? 1 : -1
    }
    return card
}

let list = [1, 2, 3, 2, 2, 2, 5, 4, 2]

console.log(majorityElement(list))

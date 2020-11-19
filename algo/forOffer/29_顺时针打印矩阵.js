/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder_byMy_1 = function (matrix) {
    let res = []
    let length = matrix.length
    if (length === 1) return matrix[0]
    else if (length === 0) return res

    let width = matrix[0].length
    if (width === 1) {
        matrix.forEach(l => res = res.concat(l))
        return res
    }

    if (length <= 0 && width <= 0) {
        return res
    }

    let top = matrix[0]
    let right = []
    let left = []
    let bottom = matrix[length - 1].reverse()

    for (let i = 1; i < length - 1; i++) {
        right.push(matrix[i][width - 1])
    }

    for (let i = length - 2; i >= 1; i--) {
        left.push(matrix[i][0])
    }

    res = res.concat(top, right, bottom, left)
    matrix = matrix.slice(1, length - 1)
    ll = matrix.length
    for (let x = 0; x < ll; x++) {
        if (matrix[x].length <= 0) {
            matrix = []
            break
        } else {
            matrix[x] = matrix[x].slice(1, width - 1)
            if (matrix[x].length === 0) {
                matrix = matrix.splice(x, 1)
                ll--
                x--
            }
        }

    }

    res = res.concat(spiralOrder(matrix))
    return res
}
/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function (matrix) {
    let res = []

    let length = matrix.length
    if (length === 0) return res

    let width = matrix[0].length
    if (width === 0) return res

    let top = [], bottom = [], right = [], left = []

    if (length > 0) {
        top = matrix[0]
        matrix.shift()
        length--
    }

    if (length > 0) {
        bottom = matrix[length - 1].reverse()
        matrix.pop()
        length--
    }

    if (width > 0 && length > 0) {
        for (let i = 0; i < length; i++) right.push(matrix[i].pop())
        width--
    }

    if (width > 0 && length > 0) {
        for (let i = length - 1; i >= 0; i--) left.push(matrix[i].shift())
        width--
    }


    return res.concat(top, right, bottom, left, spiralOrder(matrix))
}

let m = [[1, 11], [2, 12], [3, 13], [4, 14], [5, 15], [6, 16], [7, 17], [8, 18], [9, 19], [10, 20]]

console.log(spiralOrder(m))

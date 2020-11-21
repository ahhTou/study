/**
 * initialize your data structure here.
 */
var MinStack = function () {
    this.content = []
    this.minNum = []

    this.minLength = 0
    this.length = 0
}

/**
 * @param {number} x
 * @return {void}
 */
MinStack.prototype.push = function (x) {
    this.content.push(x)
    this.length++
    if (this.minLength === 0 || this.minNum[this.minLength - 1] > x) {
        this.minNum.push(x)
    } else {
        this.minNum.push(this.minNum[this.minLength - 1])
    }
    this.minLength++
}

/**
 * @return {void}
 */
MinStack.prototype.pop = function () {
    this.content.pop()
    this.minNum.pop()

    this.minLength--
    this.length--
}

/**
 * @return {number}
 */
MinStack.prototype.top = function () {
    return this.content[this.length - 1]
}

/**
 * @return {number}
 */
MinStack.prototype.min = function () {
    return this.minNum[this.minLength - 1]
}


let minStack = new MinStack()
minStack.push(-2)
minStack.push(0)
minStack.push(-3)
console.log(minStack.min())
console.log(minStack.pop())
console.log(minStack.top())
console.log(minStack.min())

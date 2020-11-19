console.log('List Up!')


Array.prototype.remove = function (value) {
    var numDeleteIndex = -1
    for (var i = 0; i < this.length; i++) {
        if (this[i] === value) {
            this.splice(i, 1)
            numDeleteIndex = i
            break
        }
    }
    return numDeleteIndex
}
Array.prototype.isInclude = function (value) {
    return !(this.indexOf(value) === -1)
}
Array.prototype.isNotInclude = function (value) {
    return this.indexOf(value) === -1
}

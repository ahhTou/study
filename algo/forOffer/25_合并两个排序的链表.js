function ListNode(val) {
    this.val = val
    this.next = null
}

/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var mergeTwoLists_byMe_1 = function (l1, l2) {
    let res = new ListNode(null)

    if (l1 === null || l2 === null) {
        if (l1 === null) return l2
        else if (l2 === null) return l1
        else return null
    }

    if (l1.val <= l2.val) {
        res.val = l1.val
        l1 = l1.next
    } else {
        res.val = l2.val
        l2 = l2.next
    }

    res.next = mergeTwoLists(l1, l2)

    return res
}

/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */
var mergeTwoLists = function (l1, l2) {
    let res = new ListNode(null), curr = res

    while (l1 !== null && l2 !== null) {

        if (l1.val <= l2.val) {
            curr.next = l1
            l1 = l1.next
        } else {
            curr.next = l2
            l2 = l2.next
        }
        curr = curr.next
    }

    if (l1 === null && l2 !== null) curr.next = l2
    else if (l1 !== null && l2 === null) curr.next = l1


    return res.next
}


let utils = require('./00_Utils')
let l1 = [2]
let l2 = [1]
let listNode = utils.createListNode(l1)
let listNode1 = utils.createListNode(l2)

console.log(mergeTwoLists(listNode, listNode1))

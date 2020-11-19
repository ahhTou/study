function ListNode(val) {
    this.val = val
    this.next = null
}


var getKthFromEnd_my_origin = function (head, k) {
    let length = 0
    let temp = head
    while (temp.next !== null) {
        length++
        temp = temp.next
    }

    let index = length + 1 - k

    for (let i = 0; i < index; i++) {
        head = head.next
    }

    return head
}

var getKthFromEnd_my_simple = function (head, k) {
    let list = []
    list.push(head)
    while (head !== null) {
        list.push(head)
        head = head.next
    }
    return list[list.length - k]
}
/**
 * @param {ListNode} head
 * @param {number} k
 * @return {ListNode}
 */

var getKthFromEnd = function (head, k) {
    let front = head, behind = head

    while (front !== null && k > 0) {
        front = front.next
        k--
    }

    while (front !== null) {
        front = front.next
        behind = behind.next
    }

    return behind

}

let utils = require('./00_Utils')

let listNode = utils.createListNode([1, 2, 3, 4, 5])

console.log(getKthFromEnd(listNode, 2))


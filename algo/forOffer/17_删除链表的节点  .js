let utils = require('./00_Utils')

function ListNode(val) {
    this.val = val
    this.next = null
}

/**
 * @param {ListNode} head
 * @param {number} val
 * @return {ListNode}
 */

var deleteNode = function (head, val) {
    let temp = head

    if (temp === null) return head

    // head
    if (head.val === val) head = head.next

    while (temp.next !== null) {
        if (temp.next.val === val) {
            if (temp.next.next !== null) {
                temp.next.val = temp.next.next.val
                temp.next.next = temp.next.next.next
            } else {
                temp.next = null
            }
            break
        }
        temp = temp.next
    }

    return head

}
let list = [-3, 5, -99]
let head = utils.createListNode(list)
console.log(deleteNode(head, -3))

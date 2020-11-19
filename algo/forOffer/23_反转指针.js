function ListNode(val) {
    this.val = val
    this.next = null
}

/**
 * @param {ListNode} head
 * @return {ListNode}
 */
var reverseList_byMe_1 = function (head) {
    let valContent = []
    let head1 = head, head2 = head
    while (head1 !== null) {
        valContent.push(head1.val)
        head1 = head1.next
    }

    let len = valContent.length - 1
    if (len < 0) return null
    head2.val = valContent[len]
    let res = head2
    head2 = head2.next

    for (let i = len - 1; i >= 0; i--) {
        head2.val = valContent[i]
        head2 = head2.next
    }

    return res
}

var reverseList = function (head) {
    let pre = null, cur = head, temp = null

    while (cur !== null) {
        temp = cur.next

        cur.next = pre
        pre = cur

        cur = temp

    }

    return pre


}

let utils = require('./00_Utils')
let list = [1, 2, 3, 4, 5]
let listNode = utils.createListNode(list)

console.log(reverseList(listNode))

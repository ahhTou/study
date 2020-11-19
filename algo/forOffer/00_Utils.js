function ListNode(val) {
    this.val = val
    this.next = null
}

function TreeNode(val) {
    this.val = val
    this.left = this.right = null
}

let createListNode = (list = []) => {
    let l_list = list.length

    if (l_list <= 0) return null

    let head = new ListNode(list[0])

    let fuc = (headNode = new ListNode(), i, iMax, list) => {
        if (++i === iMax) {
            headNode.next = null
        } else {
            headNode.next = new ListNode(list[i])
            fuc(headNode.next, i, iMax, list)
        }
    }

    fuc(head, 0, l_list, list)

    return head
}

let createTreeMethodTest = (list = []) => {
    let head = list.shift()
    let rl = []
    let ll = []
    while (list.length >= rl.length) {
        rl = rl.concat(list.splice(0, rl.length + 1))

        if (list.length >= ll.length)
            ll = ll.concat(list.splice(0, ll.length + 1))
        else
            ll = ll.concat(list)

    }
    if (list.length > 0) rl = rl.concat(list)

    let res = new TreeNode(head)

    let make = (l = []) => {
        let tree = new TreeNode(l.shift())
        tree.right = new TreeNode(l.shift())
        tree.left = new TreeNode(l.shift())
        tree.right.right = make(l)

    }

    res.left = make(ll)
    res.right = make(rl)

}

let createTreeMethod1 = (list = []) => {

    if (list.length <= 0) return null

    let res = new TreeNode(list.shift())

    if (list.length <= 0) return res

    let rl = []
    let ll = []

    while (list.length >= rl.length) {
        rl = rl.concat(list.splice(0, rl.length + 1))

        if (list.length >= ll.length)
            ll = ll.concat(list.splice(0, ll.length + 1))
        else
            ll = ll.concat(list)

    }
    if (list.length > 0) rl = rl.concat(list)

    res.left = createTreeMethod1(ll)

    res.right = createTreeMethod1(rl)


    return res


}

let drawTree = (tree = new TreeNode()) => {
    let head = tree.val
    let ll = []
    let rl = []

    let lTree = tree.left

    let rTree = tree.right


}
createTreeMethod1([4, 2, 7, 1, 3, 6, 9])

exports.createListNode = createListNode

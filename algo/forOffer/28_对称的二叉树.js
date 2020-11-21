function TreeNode(val) {
    this.val = val
    this.left = this.right = null
}

/**
 * @param {TreeNode} root
 * @return {boolean}
 */

var isSymmetric_err = function (root) {
    if (root === null) return true
    let nodes = [root.left, root.right]
    let temp = []
    let isAllNull = true

    while (true) {
        let length = nodes.length

        for (let i = 0; i < length / 2; i++) {
            if (nodes[i] === null || nodes[length - 1 - i] === null) {
                if (nodes[i] !== nodes[length - 1 - i]) return false
                continue
            }

            if (nodes[i].val !== nodes[length - 1 - i].val) {
                return false
            }
        }

        isAllNull = true
        temp = []
        for (let i = 0; i < length; i++) {
            if (nodes[i] === null || nodes[i] === undefined) {
                temp.push(null, null)
            } else {
                isAllNull = false
                temp.push(nodes[i].left, nodes[i].right)
            }
        }
        if (isAllNull) return true
        nodes = temp
    }
}
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isSymmetric = function (root) {

    /**
     * @param {TreeNode} tree1
     * @param {TreeNode}tree2
     * @return {boolean}
     */
    if (root === null) return true
    return helper(root.left, root.right)
}
/**
 *
 * @param { TreeNode } tree1
 * @param { TreeNode } tree2
 * @return {boolean|boolean|*}
 */
var helper = function (tree1, tree2) {
    if (tree1 === null && tree2 === null) return true

    if (tree1 === null || tree2 === null) return false

    return tree1.val === tree2.val
        && helper(tree1.left, tree2.right)
        && helper(tree1.right, tree2.left)

}


let utils = require('./00_Utils')

let l1 = [6, 82, 82, null, 53, 53, null, -58, null, null, -58, null, -85, -85, null, -9, null, null, -9, null, 48, 48, null, 33, null, null, 33, 81, null, null, 81, 5, null, null, 5, 61, null, null, 61, null, 9, 9, null, 91, null, null, 91, 72, 7, 7, 72, 89, null, 94, null, null, 94, null, 89, -27, null, -30, 36, 36, -30, null, -27, 50, 36, null, -80, 34, null, null, 34, -80, null, 36, 50, 18, null, null, 91, 77, null, null, 95, 95, null, null, 77, 91, null, null, 18, -19, 65, null, 94, null, -53, null, -29, -29, null, -53, null, 94, null, 65, -19, -62, -15, -35, null, null, -19, 43, null, -21, null, null, -21, null, 43, -19, null, null, -35, -15, -62, 86, null, null, -70, null, 19, null, 55, -79, null, null, -96, -96, null, null, -79, 55, null, 19, null, -70, null, null, 86, 49, null, 25, null, -19, null, null, 8, 30, null, 82, -47, -47, 82, null, 30, 8, null, null, -19, null, 25, null, 49]
let listNode = utils.createTreeMethod1(l1)
console.log(isSymmetric(listNode))

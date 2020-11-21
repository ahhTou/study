function TreeNode(val) {
    this.val = val
    this.left = this.right = null
}

/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var levelOrder = function (root) {
    return helper([root])
}
/**
 *
 * @param {TreeNode[]} nodes
 */
var helper = function (nodes) {
    if (nodes === null || nodes.length === 0) return []
    let res = [], nextNodes = []
    nodes.forEach(node => {
        if (node !== null) {
            res.push(node.val)
            nextNodes.push(node.left)
            nextNodes.push(node.right)
        }
    })
    res = res.concat(helper(nextNodes))
    return res
}

function TreeNode(val) {
    this.val = val
    this.left = this.right = null
}

/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var levelOrder = function (root) {
    return helper([root], [])
}
/**
 *
 * @param {TreeNode[]} nodes
 * @param {[]} content
 */
var helper = function (nodes, content) {
    if (nodes === null || nodes.length === 0) return []
    let layer = [], nextNodes = []

    nodes.forEach(node => {
        if (node !== null) {
            layer.push(node.val)
            nextNodes.push(node.left)
            nextNodes.push(node.right)
        }
    })

    if (layer.length > 0) content.push(layer)
    helper(nextNodes, content)

    return content
}

function TreeNode(val) {
    this.val = val
    this.left = this.right = null
}

/**
 * @param {TreeNode} root
 * @return {number[]}
 */
var levelOrder = function (root) {
    return helper([root], [], false)
}
/**
 *
 * @param nodes
 * @param content
 * @param isReverse
 * @return {*[]}
 */
var helper = function (nodes, content, isReverse) {
    if (nodes === null || nodes.length === 0) return []
    let layer = [], nextNodes = []

    nodes.forEach(node => {
        if (node !== null) {
            layer.push(node.val)
            nextNodes.push(node.left)
            nextNodes.push(node.right)
        }
    })

    if (layer.length > 0) content.push(isReverse ? layer.reverse() : layer)
    helper(nextNodes, content, !isReverse)

    return content
}

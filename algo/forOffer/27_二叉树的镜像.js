function TreeNode(val) {
    this.val = val
    this.left = this.right = null
}

/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
var mirrorTree = function (root) {
    if (root === null) return root;
    [root.left, root.right] = [root.right, root.left]

    if (root.right !== null)
        mirrorTree(root.right)
    if (root.left !== null)
        mirrorTree(root.left)

    return root
}

from typing import List


class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


def buildTree(self, pre_order: List[int], in_order: List[int]) -> TreeNode:
    length = len(pre_order)
    if length is 0:
        return None
    elif length is 1:
        return TreeNode(pre_order[0])

    head = TreeNode(pre_order[0])
    left_in_order = []
    right_in_order = []
    for num in range(length):
        if head.val == in_order[num]:
            left_in_order = in_order[0:num]
            right_in_order = in_order[(num + 1):]
            break
    ll = len(left_in_order)
    left_pre_order = pre_order[1:ll + 1]
    head.left = self.buildTree(left_pre_order, left_in_order)

    right_pre_order = pre_order[ll + 1:]
    head.right = self.buildTree(right_pre_order, right_in_order)

    return head


def buildTreeSimplify(self, pre_order: List[int], in_order: List[int]) -> TreeNode:
    if not pre_order:
        return None

    length = len(pre_order)
    head = TreeNode(pre_order[0])

    for num in range(length):
        if head.val == in_order[num]:
            head.left = self.buildTree(pre_order[1:num + 1], in_order[0:num])
            head.right = self.buildTree(pre_order[num + 1:], in_order[(num + 1):])
            break

    return head


def buildTreeByTips(pre_order: List[int], in_order: List[int]) -> TreeNode:
    if not pre_order:
        return None

    head = TreeNode(pre_order[0])
    num = in_order.index(pre_order[0])
    head.left = buildTreeByTips(pre_order[1:num + 1], in_order[0:num])
    head.right = buildTreeByTips(pre_order[num + 1:], in_order[(num + 1):])

    return head


pre_order1 = [3, 9, 20, 15, 7]
in_order1 = [9, 3, 15, 20, 7]

buildTreeByTips(pre_order1, in_order1)

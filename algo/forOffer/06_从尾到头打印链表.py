# Definition for singly-linked list.
from typing import List


class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


def setNodeList(start: ListNode, li: List):
    if len(li) > 0:
        start.next = ListNode(li.pop())
        setNodeList(start.next, li)


l1 = [1, 2, 3]
head1 = ListNode(l1.pop())
setNodeList(head1, l1)


def reversePrint(head: ListNode) -> List[int]:
    if head is None:
        return []
    new_list = []
    while True:
        new_list.append(head.val)
        if head.next is None:
            break
        else:
            head = head.next

    return new_list[::-1]


def reversePrint2(head: ListNode) -> List[int]:
    if head is None:
        return []

    new_list = []

    while head:
        new_list.append(head.val)
        head = head.next

    length = len(new_list) - 1
    res_list = []
    for i in range(length, -1, -1):
        res_list.append(new_list[i])

    return res_list


def reversePrint3(head: ListNode) -> List[int]:
    if head is None:
        return []
    return reversePrint3(head.next) + [head.val]


a = reversePrint3(head1)
print(a)

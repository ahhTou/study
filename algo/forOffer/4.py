matrix1 = [[-5]]


def test1(matrix, target):
    """
    :type matrix: List[List[int]]
    :type target: int
    :rtype: bool
    """
    l1 = len(matrix) - 1
    if l1 < 0:
        return False
    l2 = len(matrix[0]) - 1
    if l2 < 0:
        return False
    g1, g2 = 0, 0

    while True:
        if g1 == l1 + 1:
            goal = False
            break

        if target == matrix[g1][g2]:
            goal = True
            break

        if g2 == l2:
            g1 += 1
            g2 = 0
        else:
            g2 += 1

    return goal


def test2(matrix, target):
    """
    :type matrix: List[List[int]]
    :type target: int
    :rtype: bool
    """
    l1 = len(matrix)
    if l1 < 1:
        return False
    l2 = len(matrix[0])
    if l2 < 1:
        return False

    for i in range(l1):
        for q in range(l2):
            if target == matrix[i][q]:
                return True
    return False


def test3(matrix, target):
    """
    :type matrix: List[List[int]]
    :type target: int
    :rtype: bool
    """
    l1 = len(matrix)
    if l1 < 1:
        return False
    l2 = len(matrix[0])
    if l2 < 1:
        return False

    if l1 * l2 < 1000:
        for i in range(l1):
            for q in range(l2):
                if target == matrix[i][q]:
                    return True

    else:
        for i in range(l1):
            mini = matrix[i][0]
            big = matrix[i][l2 - 1]
            if mini <= target <= big:
                for p in range(l2):
                    if target == matrix[i][p]:
                        return True

    return False


a = test3(matrix1, -5)
print(a)

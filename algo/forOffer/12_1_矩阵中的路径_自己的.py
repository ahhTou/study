from typing import List


class Solution:
    board = None
    word = None
    len_word = 0
    len_board = 0
    len_list = 0
    ban = []

    def is_not_ban(self, goal) -> bool:
        for i in self.ban:
            if i == goal:
                print('×')
                return False
        print('√')
        return True

    def exist(self, board: List[List[str]], word: str) -> bool:
        self.board = board
        self.word = word
        self.ban = []

        # 初始化, 并排除意外情况
        self.len_word = len(self.word)

        if self.len_word < 0:
            return False
        # 特殊情况 word 为 1
        if self.len_word is 1:
            self.len_board = len(self.board)
            self.len_list = len(self.board[0])
            for bi in range(self.len_board):
                for li in range(self.len_list):
                    if self.word == self.board[bi][li]:
                        return True
            return False

        self.len_board = len(self.board)
        if self.len_board < 0:
            return False

        self.len_list = len(self.board[0])
        if self.len_list < 0:
            return False

        # 特殊情况 地图 小于 word
        if self.len_word > self.len_list * self.len_board:
            print('特殊情况, 结束')
            return False

        # 找到多个坐标
        start_pos = self.get_start_pos(word[0])
        start_pos_x, start_pos_y = start_pos['x'], start_pos['y']

        len_pos = len(start_pos['x'])
        for i in range(len_pos):
            print('Start Find Path ↓')
            self.ban = []
            self.ban.append([start_pos_x[i], start_pos_y[i]])
            if self.next_step(word, start_pos_x[i], start_pos_y[i]):
                return True

        print('END')
        return False

    def get_start_pos(self, goal: str) -> dict:
        # 找到第多个坐标
        start_pos = {'x': [], 'y': []}
        for bi in range(self.len_board):
            for li in range(self.len_list):
                if goal == self.board[bi][li]:
                    print('Find A Start Pos √')
                    start_pos['x'].append(li)
                    start_pos['y'].append(bi)
        return start_pos

    def next_step(self, words: str, x, y) -> bool:
        is_end = False
        words = words[1:]
        word = words[0]
        print('check word ->' + words)
        if len(words) == 1:
            print('is_end -> True || word ->' + words)
            is_end = True

        # right
        if x + 1 < self.len_list and word is self.board[y][x + 1]:
            print('→ ' + self.board[y][x + 1] + ' (', x + 1, ', ', y, ')')
            if is_end and self.is_not_ban([x + 1, y]):
                return True
            b = self.to_next(x + 1, y, words, is_end)
            if b is 1:
                return True

        # left
        if x - 1 >= 0 and word is self.board[y][x - 1]:
            print('← ' + self.board[y][x - 1] + ' (', x - 1, ', ', y, ')')
            if is_end and self.is_not_ban([x - 1, y]):
                return True
            b = self.to_next(x - 1, y, words, is_end)
            if b is 1:
                return True

        # bottom
        if y + 1 < self.len_board and word is self.board[y + 1][x]:
            print('↓ ' + self.board[y + 1][x] + ' (', x, ', ', y + 1, ')')
            if is_end and self.is_not_ban([x, y + 1]):
                return True
            b = self.to_next(x, y + 1, words, is_end)
            if b is 1:
                return True

        # top
        if y - 1 >= 0 and word is self.board[y - 1][x]:
            print('↑ ' + self.board[y - 1][x] + ' (', x, ', ', y - 1, ')')
            if is_end and self.is_not_ban([x, y - 1]):
                return True
            b = self.to_next(x, y - 1, words, is_end)
            if b is 1:
                return True

        print('No Path Can Go ×')
        return False

    def to_next(self, tx, ty, words, is_end) -> int:
        if is_end:
            print('!!Back to pre next')
            return 0
        path = [tx, ty]
        if self.is_not_ban(path):
            self.ban.append(path)
            if self.next_step(words, tx, ty):
                return 1
            else:
                print('!Back to pre next')
                self.ban.pop()
                return 0
        else:
            return 0


a = Solution()

# print(a.exist([["a"]], "a"))  # true
# print(a.exist([["a"], ["a"]], "aa"))  # True
# print(a.exist([["a", "b"]], "ab"))  # True
# print(a.exist([["b", "b"], ["a", "b"], ["b", "a"]], "a"))  # True
# print('res ---> ',

# print(a.exist([["a", "a", "a", "a"],
#                ["a", "a", "a", "a"],
#                ["a", "a", "a", "a"],
#                ["a", "a", "a", "a"],
#                ["a", "a", "a", "b"]], "aaaaaaaaaaaaaaaaaaaa"))
# False
# print(a.exist([["A", "B", "C", "E"], ["S", "F", "C", "S"], ["A", "D", "E", "E"]], "ABCCED"))
# print(a.exist([["A", "B", "C", "E"], ["S", "F", "E", "S"], ["A", "D", "E", "E"]], "ABCESEEEFS"))
# print(a.exist([["C", "A", "A"], ["A", "A", "A"], ["B", "C", "D"]], "AAB"))
# print(a.exist([["A", "B", "C", "E"], ["S", "F", "C", "S"], ["A", "D", "E", "E"]], "ABCB"))

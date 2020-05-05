# 18.5 王鹏博
import sys
import jieba
import wordcloud
import os
import turtle

num = 0
isDeal = False
text, words, items, excludes = '', '', '', ''


def choose():
    print('请选择你要查看出场次数 或 词云的书号')
    print('1.红楼梦')
    print('2.三国演义')
    you_choose = input('输入书号:')
    return int(you_choose)


def start():
    global text, num
    this_book = choose()
    if this_book == 1:
        txt = open('./lib/红楼梦.txt', 'r', encoding='utf-8').read()
        text = txt
        num = this_book
        panel()
    elif this_book == 2:
        txt = open('./lib/三国演义.txt', 'r', encoding='utf-8').read()
        text = txt
        num = this_book
        panel()
    else:
        print('输入错误')
        sys.exit(1)


def panel():
    global text, num, isDeal, words, items, excludes
    if not isDeal:
        words, items, excludes = deal_jie_ba()
        isDeal = True
    print('1.查看人数出场统计')
    print('2.查看人数出场统计词云图')
    print('3.查看里数据的表格直方图')
    print('4.其他键退出')
    choose1 = int(input('请选择:'))
    if choose1 == 1:
        show_list()
    elif choose1 == 2:
        show_word_cloud(" ".join(words), excludes)
    elif choose1 == 3:
        show_table()

    else:
        sys.exit(1)


def deal_jie_ba():
    global text, num, isDeal, words, items, excludes
    if num == 1:
        name = '红楼梦'
    else:
        name = '三国演义'
    print('正在为您处理 {} 的相关数据'.format(name))
    excludes = [
        {
            '什么', '一个', '我们', '那里', '王夫人', '你们', '如今',
            '知道', '姑娘', '这里', '说道', '众人', '只见', '起来', '出来',
            '一面', '自己', '两个', '这个', '听见', '没有', '怎么', '不是',
            '不知', '进来', '告诉', '平儿', '东西', '这样', '就是', '咱们',
            '袭人', '回来', '他们', '大家', '只是', '只得', '不敢', '这些',
            '出去', '所以', '的话'},
        {
            '将军', '却说', '荆州', '二人', '不可', '不能', '如此',
            '左右', '商议', '如何'
        }

    ]
    words = jieba.lcut(text)
    counts = {}
    for word in words:
        if len(word) == 1:
            continue
        else:
            counts[word] = counts.get(word, 0) + 1
    for word in excludes[num - 1]:
        if counts[word]:
            del counts[word]
    items = list(counts.items())
    items.sort(key=lambda x: x[1], reverse=True)
    return words, items, excludes[num - 1]


def show_list():
    global items
    for i in range(15):
        word, count = items[i]
        print("{0:<10}{1:>15}".format(word, count))
    print('已处理完毕')
    panel()


def show_word_cloud(word_cloud_text, stop_words):
    w = wordcloud.WordCloud(
        font_path='msyh.ttc',
        width=1000, height=700,
        background_color='white',
        max_words=15,
        stopwords=stop_words
    )
    w.generate(word_cloud_text)
    w.to_file('word_cloud.jpg')
    print('词云已经生成完毕,请选择:')
    print('1.打开词云图片')
    print('2.其他键退出')
    choose1 = int(input('请选择:'))
    if choose1 == 1:
        os.system('word_cloud.jpg')
        panel()
    else:
        sys.exit(1)


def show_table():
    global items
    heights = []
    headers = []
    for i in range(15):
        heights.append(int(items[i][1]))
        headers.append(items[i][0])
    t = turtle.Turtle()
    t.hideturtle()
    t.speed(0)
    for i in range(15):
        draw_filled_rectangle(t, -300 + (30 * i), -300, 30, heights[i] / 5, "black", "light blue")
    display_text(t, heights, headers)
    turtle.done()
    panel()


def draw_filled_rectangle(t, x, y, w, h, color_p="black", color_f="white"):
    t.pencolor(color_p)
    t.fillcolor(color_f)
    t.up()
    t.goto(x, y)
    t.down()
    t.begin_fill()
    t.goto(x + w, y)
    t.goto(x + w, y + h)
    t.goto(x, y + h)
    t.goto(x, y)
    t.end_fill()


def display_text(t, heights, headers):
    t.pencolor("blue")
    t.up()
    t.goto(-400, -325)
    t.write("人物出场统计图", font=("Arial", 10, "normal"))

    t.goto(-400, -345)
    t.write('(18.5班 王鹏博)', font=("Arial", 10, "normal"))
    for i in range(15):
        t.goto((-290 + 30 * i), -300 + heights[i] / 5)
        t.write(str(heights[i]), align="center", font=("Arial", 10, "normal"))

        t.goto((-290 + 30 * i), -330)
        t.write(headers[i], align="center", font=("Arial", 10, "normal"))


def main():
    start()


main()

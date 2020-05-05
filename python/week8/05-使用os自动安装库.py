import os

libs = {'jieba', '其他库名'}

try:
    for lib in libs:
        os.system('pip install' + lib)
    print('Successful')
except:
    print('Failed Somehow')

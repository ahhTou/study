import os.path as op

# 返回path在当前系统中的绝对路径
print('返回完整的路径:', op.abspath('.01-模拟比赛.py'))

# 归一化path的表示形式 ，统一使用\\来区分
print('归一化的路径:', op.normpath('c://abc.py//ssss.py'))

# 返回当前程序与文件之间的相对路径
print('返回相对路径：', op.relpath('.01-模拟比赛.py'))

# 返回path中的路径目录
print('目录部分', op.dirname('c://abc//ssss.py'))

# 返回path最后的文件名称
print('文件名称', op.basename('c://abc.py//ssss.py'))

# 结合path 与 paths, 返回一个路径字符串
print('拼接路径', op.join('D:/', '/abc/ddd.txt'))

# 判断 path 对应文件的 目录 或 文件 是否存在， 返回 true 或者 false
print('目录或目录 是否存在：', op.exists('c://abc.py//ssss'))

# 判断 path 对应文件的 文件 是否存在， 返回 true 或者 false
print('文件 是否存在:', op.isfile('c://abc.py//ssss'))

# 判断 path 对应文件的 目录 是否存在， 返回 true 或者 false
print('目录 是否存在:', op.isdir('c://abc.py//ssss'))

# 返回path对应文件或者目录 的 最近一次的访问时间
print('上一次的访问时间', op.getatime('D:\\Program Files'))

# 返回path对应文件或者目录 的 最近一次的修改时间
print('上一次的修改时间', op.getmtime('D:\\Program Files'))

# 返回path对应文件或者目录 的 创建时间
print('该文件的创建时间', op.getctime('D:\\Program Files'))

# 返回path对应文件的大小
print('该文件的大小', op.getsize('C:\\Windows\\System32\\calc.exe'))
